package trabajarconobligatorio;

public class ListaContactos implements IListaContacto {

    NodoContacto primero;
    NodoContacto ultimo;
    int cantNodos;
    int posicion = 1;

    public ListaContactos() {
        this.primero = null;
        this.ultimo = null;
        this.cantNodos = 0;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public NodoContacto getPrimero() {
        return primero;
    }

    public void setPrimero(NodoContacto primero) {
        this.primero = primero;
    }

    public NodoContacto getUltimo() {
        return ultimo;
    }

    public void setUltimo(NodoContacto ultimo) {
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
    public Retorno agregarInicio(int dato, String nom) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);

        NodoContacto nuevo = new NodoContacto(dato, nom);
        nuevo.posicion = this.posicion;

        if (this.esVacia().valorbooleano) {
            this.setPrimero(nuevo);
            this.setUltimo(nuevo);
        } else {
            nuevo.setSiguiente(primero);
            this.setPrimero(nuevo);
        }

        this.posicion++;
        this.cantNodos = this.cantNodos + 1;
        return ret;
    }

    @Override
    public Retorno agregarFinal(int dato, String nom) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);

        NodoContacto nuevo = new NodoContacto(dato, nom);
        nuevo.posicion = this.posicion;

        if (this.esVacia().valorbooleano) {
            this.setPrimero(nuevo);
            this.setUltimo(nuevo);
        } else {
            ultimo.setSiguiente(nuevo);
            this.setUltimo(nuevo);
        }

        this.posicion++;
        this.cantNodos = this.cantNodos + 1;
        return ret;
    }

    @Override
    public Retorno borrarInicio() {
        Retorno ret = new Retorno(Retorno.Resultado.OK);

        if (!this.esVacia().valorbooleano) {
            if (this.primero == this.ultimo) {
                this.setPrimero(null);
                this.setUltimo(null);
            } else {
                this.setPrimero(this.primero.getSiguiente());
            }

            this.posicion--;
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
                NodoContacto aux = this.getPrimero();
                while (aux.getSiguiente() != this.ultimo) {
                    aux = aux.getSiguiente();
                }
                aux.siguiente = null;
                this.setUltimo(aux);
            }
            this.posicion--;
            this.cantNodos = this.cantNodos - 1;
        }

        return ret;
    }

    @Override
    public Retorno buscarElemento(int dato) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorbooleano = false;

        NodoContacto aux = getPrimero();
        while (aux != null) {
            if (aux.getDato() == dato) {
                ret.valorbooleano = true;
            }
            aux = aux.getSiguiente();
        }

        return ret;
    }

    @Override
    public Retorno buscarElemento(String nom) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorbooleano = false;

        NodoContacto aux = getPrimero();
        while (aux != null) {
            if (aux.getNombre() == nom) {
                ret.valorbooleano = true;
            }
            aux = aux.getSiguiente();
        }

        return ret;
    }

    @Override
    public NodoContacto obtenerElemento(int n) {
        NodoContacto ret = null;

        if (this.buscarElemento(n).valorbooleano) {
            NodoContacto aux = this.primero;
            while (aux != null && ret == null) {
                if (aux.getDato() == n) {
                    ret = aux;
                }
                aux = aux.getSiguiente();
            }
        }

        return ret;
    }

    public NodoContacto obtenerElementoPorPosicion(int n) {
        NodoContacto ret = null;

        NodoContacto aux = this.primero;
        while (aux != null && ret == null) {
            if (aux.posicion == n) {
                return aux;
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
        this.posicion = 1;
        return ret;
    }

    @Override
    public Retorno mostrar() {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorString = "";

        NodoContacto aux = this.getPrimero();
        while (aux != null) {
            ret.valorString = ret.valorString + aux.getDato() + "-" + aux.getNombre() + "\n";
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
    public Retorno agregarOrd(int dato, String nom) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);

        NodoContacto nuevo = new NodoContacto(dato, nom);
        NodoContacto aux = this.getPrimero();

        if (this.esVacia().valorbooleano || dato < this.getPrimero().getDato()) {
            this.agregarInicio(nuevo.getDato(), nom);
        } else if (dato > this.getUltimo().getDato()) {
            this.agregarFinal(nuevo.getDato(), nom);
        } else {
            while (aux.getSiguiente() != null && dato > aux.getSiguiente().getDato()) {
                aux = aux.getSiguiente();
            }
            this.cantNodos = this.cantNodos + 1;
            nuevo.setSiguiente(aux.getSiguiente());
            aux.setSiguiente(nuevo);
        }

        return ret;
    }

    @Override
    public Retorno borrarElemento(int dato) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorbooleano = false;

        NodoContacto aux = this.getPrimero();

        if (!this.esVacia().valorbooleano) {

            if (dato == this.primero.getDato()) {
                this.borrarInicio();

            } else if (dato == this.ultimo.getDato()) {
                this.borrarFinal();

            } else {
                while (aux.getSiguiente() != null) {
                    if (aux.getSiguiente().getDato() == dato) {
                        aux.siguiente = aux.siguiente.siguiente;
                        ret.valorbooleano = true;
                        this.cantNodos = this.cantNodos - 1;
                        return ret;
                    }
                    aux = aux.getSiguiente();
                }
            }

            this.cantNodos = this.cantNodos - 1;
        }

        return ret;
    }

    @Override
    public NodoContacto obtenerPunteroElemento(int dato) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorbooleano = false;

        NodoContacto aux = this.getPrimero();
        NodoContacto punteroANodo = null;

        while (aux != null) {
            if (aux.getDato() == dato) {
                punteroANodo = aux;
            }
            aux = aux.getSiguiente();
        }

        return punteroANodo;
    }

    @Override
    public NodoContacto insertarRecursivo(NodoContacto primero, int dato, String nom) {
        if (primero == null) {
            return new NodoContacto(dato, nom);
        } else {
            primero.siguiente = insertarRecursivo(primero.siguiente, dato, nom);
            return primero;
        }
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

    public String mostrarRec(NodoContacto inicio, NodoContacto ultimo) {

        if (inicio == ultimo) {
            return inicio.getDato() + "-";
        }
        return inicio.getDato() + "-" + mostrarRec(inicio.getSiguiente(), ultimo);
    }

    public String mostrarRecInverso(NodoContacto inicio, NodoContacto ultimo) {

        if (inicio == ultimo) {
            return inicio.getDato() + "-";
        }
        return mostrarRecInverso(inicio.getSiguiente(), ultimo) + inicio.getDato() + "-";
    }


    @Override
    public void borrarOcurrenciaElemento(int n) {

        NodoContacto aux = this.getPrimero();
        while (aux.getSiguiente() != null) {
            if (aux.getDato() == n) {
                this.borrarElemento(n);
            }
            aux = aux.getSiguiente();
        }
    }


    @Override
    public int sumaPares() {
        int suma = 0;
        int cantRecorridas = 0;

        NodoContacto aux = this.getPrimero();
        while (aux != null) {
            if (cantRecorridas % 2 == 0) {
                suma = suma + aux.getDato();
            }
            cantRecorridas++;
            aux = aux.getSiguiente();
        }

        return suma;
    }


    @Override
    public void eliminarDesde(int pos, int cant) {
        int cantRecorrida = 0;
        int cantEliminados = 0;

        NodoContacto aux = this.getPrimero();
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
