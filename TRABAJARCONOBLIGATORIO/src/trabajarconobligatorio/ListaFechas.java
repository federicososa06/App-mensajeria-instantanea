package trabajarconobligatorio;

import java.util.Date;

public class ListaFechas implements IListaFechas {

    NodoFecha primero;
    NodoFecha ultimo;
    int cantNodos;
    int numFecha = 1;

    public ListaFechas() {
        this.primero = null;
        this.ultimo = null;
        this.cantNodos = 0;
    }

    public NodoFecha getPrimero() {
        return primero;
    }

    public void setPrimero(NodoFecha primero) {
        this.primero = primero;
    }

    public NodoFecha getUltimo() {
        return ultimo;
    }

    public void setUltimo(NodoFecha ultimo) {
        this.ultimo = ultimo;
    }

    public int getCantNodos() {
        return cantNodos;
    }

    @Override
    public Retorno esVacia() {
        Retorno ret = new Retorno(Retorno.Resultado.OK);

        ret.valorbooleano = this.primero == null;

        return ret;
    }

    @Override
    public Retorno agregarInicio(Date f) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);

        NodoFecha nuevo = new NodoFecha(f);
        nuevo.num = 1;

        if (this.esVacia().valorbooleano) {
            this.setPrimero(nuevo);
            this.setUltimo(nuevo);
        } else {

            this.primero.setAnterior(nuevo);
            nuevo.setSiguiente(this.primero);
            nuevo.setAnterior(null);
            this.primero = nuevo;

            NodoFecha aux = this.ultimo;
            while (aux != null) {
                if (aux.getSiguiente() != null) {
                    aux.getSiguiente().num++;
                }
                aux = aux.getAnterior();
            }

        }

        this.numFecha++;
        this.cantNodos = this.cantNodos + 1;
        return ret;
    }

    @Override
    public Retorno agregarFinal(Date f) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);

        NodoFecha nuevo = new NodoFecha(f);
        nuevo.num = this.numFecha;

        if (this.esVacia().valorbooleano) {
            this.setPrimero(nuevo);
            this.setUltimo(nuevo);
        } else {
            nuevo.setAnterior(this.ultimo);
            this.ultimo.setSiguiente(nuevo);
            this.ultimo = nuevo;
        }

        this.numFecha++;
        this.cantNodos = this.cantNodos + 1;
        return ret;
    }

    @Override
    public Retorno agregarOrd(Date f) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);

        NodoFecha nuevo = new NodoFecha(f);
        nuevo.num = this.numFecha;

        if (this.esVacia().valorbooleano || f.before(this.getPrimero().getFecha()) || this.primero.fecha.equals(f)) {
            this.agregarInicio(nuevo.getFecha());

        } else if (f.after(this.getUltimo().getFecha()) || this.ultimo.fecha.equals(f)) {
            this.agregarFinal(nuevo.getFecha());

        } else {

            //desplazar las siguientes +1
            NodoFecha aux = this.ultimo;
            while (aux.getAnterior() != null && aux.fecha.after(f)) {

                if (aux.getSiguiente() != null) {
                    aux.getSiguiente().num++;
                }
                aux = aux.getAnterior();
            }
            aux.getSiguiente().num++;

            aux.getSiguiente().setAnterior(nuevo);
            nuevo.setSiguiente(aux.getSiguiente());
            nuevo.setAnterior(aux);
            aux.setSiguiente(nuevo);

            ret.valorbooleano = true;
            this.numFecha++;
            this.cantNodos++;

        }

        return ret;
    }

    @Override
    public Retorno borrarInicio() {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorbooleano = false;

        if (!this.esVacia().valorbooleano) {
            if (this.primero == this.ultimo) {
                this.setPrimero(null);
                this.setUltimo(null);
            } else {
                this.setPrimero(this.primero.getSiguiente());
                this.primero.setAnterior(null);

            }
            ret.valorbooleano = true;
            this.numFecha--;
            this.cantNodos = this.cantNodos - 1;
        }

        return ret;
    }

    @Override
    public Retorno borrarFinal() {
        Retorno ret = new Retorno(Retorno.Resultado.OK);

        if (!this.esVacia().valorbooleano) {
            if (this.primero == this.ultimo) {
                this.setPrimero(null);
                this.setUltimo(null);
            } else {
                this.ultimo = this.ultimo.getAnterior();
                this.ultimo.setSiguiente(null);
            }
            this.numFecha--;
            this.cantNodos = this.cantNodos - 1;
        }

        return ret;
    }

    @Override
    public Retorno buscarElemento(Date f) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorbooleano = false;

        NodoFecha aux = getPrimero();
        while (aux != null) {
            if (aux.getFecha() == f) {
                ret.valorbooleano = true;
            }
            aux = aux.getSiguiente();
        }

        return ret;
    }

    @Override
    public Retorno vaciar() {
        Retorno ret = new Retorno(Retorno.Resultado.OK);

        this.setPrimero(null);
        this.setUltimo(null);
        this.cantNodos = 0;

        return ret;
    }

    @Override
    public Retorno mostrar() {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorString = "";

        NodoFecha aux = this.getPrimero();
        while (aux != null) {
            ret.valorString = ret.valorString + aux.getFecha().toString() + ": \n";
            aux = aux.getSiguiente();
        }

        return ret;
    }


    @Override
    public Retorno cantElementos() {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorEntero = this.getCantNodos();
        return ret;
    }

    @Override
    public Retorno borrarElemento(int num) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorbooleano = false;

        NodoFecha buscado = this.obtenerElemento(num);

        if (buscado != null) {
            if (buscado == this.primero) {
                this.borrarInicio();
            } else if (buscado == this.ultimo) {
                this.borrarFinal();
            } else {
                buscado.getAnterior().setSiguiente(buscado.getSiguiente());
                buscado.getSiguiente().setAnterior(buscado.getAnterior());

                this.numFecha--;
                this.cantNodos--;
            }
        }
        return ret;
    }

    @Override
    public NodoFecha obtenerElemento(int n) {
        NodoFecha ret = null;

        if (!this.esVacia().valorbooleano) {
            NodoFecha aux = this.primero;
            while (aux != null && ret == null) {
                if (aux.num == n) {
                    return aux;
                }
                aux = aux.getSiguiente();
            }
        }

        return ret;
    }

    @Override
    public NodoFecha obtenerPunteroElemento(Date f) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorbooleano = false;

        NodoFecha aux = this.getPrimero();
        NodoFecha punteroANodo = null;

        while (aux != null) {
            if (aux.getFecha() == f) {
                punteroANodo = aux;
            }
            aux = aux.getSiguiente();
        }

        return punteroANodo;
    }

    @Override
    public Retorno mostrarRec() {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorString = mostrarRec(this.primero, this.ultimo);
        return ret;
    }

    @Override
    public Retorno mostrarRecInverso() {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorString = mostrarRecInverso(this.primero, this.ultimo);
        return ret;
    }

    public String mostrarRec(NodoFecha inicio, NodoFecha ultimo) {

        if (inicio == ultimo) {
            return inicio.getFecha() + "-";
        }
        return inicio.getFecha() + "-" + mostrarRec(inicio.getSiguiente(), ultimo);
    }

    public String mostrarRecInverso(NodoFecha inicio, NodoFecha ultimo) {

        if (inicio == ultimo) {
            return inicio.getFecha() + "-";
        }
        return mostrarRecInverso(inicio.getSiguiente(), ultimo) + inicio.getFecha() + "-";
    }

    public ListaFechas borrarFinal(ListaFechas ln) {

        if (ln != null) {
            if (ln.primero == ln.ultimo) {
                ln.setPrimero(null);
                ln.setUltimo(null);
            } else {
                ln.ultimo = ln.ultimo.getAnterior();
                ln.ultimo.setSiguiente(null);
            }
            ln.numFecha--;
            ln.cantNodos = ln.cantNodos - 1;
        } else {
            return null;
        }

        return ln;
    }

    public ListaFechas borrarRec(ListaFechas ln) {

        if (ln == null) {
            return null;
        } else {
            if (ln.cantNodos == 0) {
                return ln;
            } else {
                return borrarRec(ln.borrarFinal(ln));
            }

        }
    }


    @Override
    public void borrarOcurrenciaElemento(Date n) {

        NodoFecha aux = this.getPrimero();
        while (aux != null) {
            if (aux.getFecha() == n) {
                this.borrarElemento(aux.num);
            }
            aux = aux.getSiguiente();
        }
    }


    @Override
    public void eliminarDesde(int pos, int cant) {
        int cantRecorrida = 0;
        int cantEliminados = 0;

        NodoFecha aux = this.getPrimero();
        while (aux.siguiente != null) {

            if (cantRecorrida >= pos && cant > cantEliminados) {
                this.primero.siguiente = aux.siguiente;
                this.primero = aux.siguiente;
                cantEliminados++;
            }

            cantRecorrida++;
            aux = aux.getSiguiente();
        }
        this.cantNodos = this.cantNodos - cantEliminados;
    }


}
