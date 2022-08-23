package trabajarconobligatorio;

public class ListaLinea implements IListaLineas {

    NodoLinea primero;
    NodoLinea ultimo;
    int cantNodos;
    int numLinea = 1;

    public ListaLinea() {
        this.primero = null;
        this.ultimo = null;
        this.cantNodos = 0;
    }

    public NodoLinea getPrimero() {
        return primero;
    }

    public void setPrimero(NodoLinea primero) {
        this.primero = primero;
    }

    public NodoLinea getUltimo() {
        return ultimo;
    }

    public void setUltimo(NodoLinea ultimo) {
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
    public Retorno agregarInicio() {
        Retorno ret = new Retorno(Retorno.Resultado.OK);

        NodoLinea nuevo = new NodoLinea();
        nuevo.numLinea = 1;

        if (this.esVacia().valorbooleano) {
            this.setPrimero(nuevo);
            this.setUltimo(nuevo);
        } else {

            this.primero.setAnterior(nuevo);
            nuevo.setSiguiente(this.primero);
            nuevo.setAnterior(null);
            this.primero = nuevo;

            NodoLinea aux = this.ultimo;
            while (aux != null) {
                if (aux.getSiguiente() != null) {
                    aux.getSiguiente().numLinea++;
                }
                aux = aux.getAnterior();
            }
        }

        this.numLinea++;
        this.cantNodos = this.cantNodos + 1;
        return ret;
    }

    @Override
    public Retorno agregarFinal() {
        Retorno ret = new Retorno(Retorno.Resultado.OK);

        NodoLinea nuevo = new NodoLinea();
        nuevo.numLinea = this.numLinea; 

        if (this.esVacia().valorbooleano) {
            this.setPrimero(nuevo);
            this.setUltimo(nuevo);
        } else {
            nuevo.setAnterior(this.ultimo);
            this.ultimo.setSiguiente(nuevo);
            this.ultimo = nuevo;
        }

        this.numLinea++;
        this.cantNodos = this.cantNodos + 1;
        return ret;
    }

    public NodoLinea agregarLineaFinalRec(NodoLinea inicio) {

        NodoLinea nuevo = new NodoLinea();
        nuevo.numLinea = this.numLinea;

        if (inicio == null) {
            this.setPrimero(nuevo);
            this.setUltimo(nuevo);
            this.numLinea++;
            this.cantNodos = this.cantNodos + 1;
            return nuevo;
        } else if (inicio.siguiente == null) {

            nuevo.setAnterior(this.ultimo);
            this.ultimo.setSiguiente(nuevo);
            this.ultimo = nuevo;

            this.numLinea++;
            this.cantNodos = this.cantNodos + 1;
            return nuevo;
        } else {

            return agregarLineaFinalRec(inicio.siguiente);
        }

    }

    @Override //desplaza las lineas siguientes
    public Retorno agregarEnPosicion(int posicion) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);

        NodoLinea nuevo = new NodoLinea();
        nuevo.numLinea = posicion;

        if (this.ultimo == null || posicion >= this.ultimo.numLinea) {
            this.agregarFinal();

        } else if (this.primero == null || posicion <= this.primero.numLinea) {
            this.agregarInicio();

        } else {

            //desplazar las siguientes +1
            NodoLinea aux = this.ultimo;
            while (aux.getAnterior() != null && posicion <= aux.numLinea) {
                if (aux.getSiguiente() != null) {
                    aux.getSiguiente().numLinea++;
                }
                aux = aux.getAnterior();
            }
            aux.getSiguiente().numLinea++;

            aux.getSiguiente().setAnterior(nuevo);
            nuevo.setSiguiente(aux.getSiguiente());
            nuevo.setAnterior(aux);
            aux.setSiguiente(nuevo);

            ret.valorbooleano = true;
            this.numLinea++;
            this.cantNodos++;
        }

        return ret;
    }

        public Retorno agregarPalabraEnLineaYDesplazar(int posLinea, int posPalabra, String texto, int maxCantPalabras) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);

        NodoLinea linea = this.obtenerElemento(posLinea);
        linea.listaPalabras.agregarEnPosicion(posPalabra, texto);

        while (linea.siguiente!=null && linea.listaPalabras.cantNodos > maxCantPalabras) {

            linea.siguiente.listaPalabras.agregarInicio(linea.listaPalabras.ultimo.texto);
            linea.listaPalabras.borrarFinal();
            
            linea = linea.getSiguiente();
        }

        return ret;
    }


    //    @Override
//    public Retorno agregarOrd(int dato) {
//        Retorno ret = new Retorno(Retorno.Resultado.OK);
//
//        NodoLinea nuevo = new NodoLinea(dato);
//        NodoLinea aux = this.getPrimero();
//
//        if (this.esVacia().valorbooleano || dato < this.getPrimero().getDato()) {
//            this.agregarInicio(nuevo.getDato());
//        } else if (dato > this.getUltimo().getDato()) {
//            this.agregarFinal(nuevo.getDato());
//        } else {
//            while (aux.getSiguiente() != null && dato > aux.getSiguiente().getDato()) {
//                aux = aux.getSiguiente();
//            }
//            this.cantNodos = this.cantNodos + 1;
//            nuevo.setSiguiente(aux.getSiguiente());
//            aux.setSiguiente(nuevo);
//        }
//
//        return ret;
//    }
//    
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

                NodoLinea aux = this.ultimo;
                while (aux != null && 1 <= aux.numLinea) {
                    if (aux.getSiguiente() != null) {
                        aux.getSiguiente().numLinea--;
                    }
                    aux = aux.getAnterior();
                }

            }
            ret.valorbooleano = true;
            this.numLinea--;
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
            this.numLinea--;
            this.cantNodos = this.cantNodos - 1;
        }

        return ret;
    }

    @Override
    public Retorno buscarElemento(int dato) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorbooleano = false;

        NodoLinea aux = getPrimero();
        while (aux != null) {
            if (aux.getNumLinea() == dato) {
                ret.valorbooleano = true;
            }
            aux = aux.getSiguiente();
        }

        return ret;
    }

    public Retorno buscarElemento(String texto) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorbooleano = false;

        NodoLinea aux = getPrimero();
        while (aux != null) {
            if (aux.listaPalabras.buscarElemento(texto).valorbooleano) {
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
        this.numLinea = 1;
        return ret;
    }

    @Override
    public Retorno mostrar() {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorString = "";

        NodoLinea aux = this.getPrimero();
        while (aux != null) {
            ret.valorString = ret.valorString + aux.getNumLinea() + ":" + aux.listaPalabras.mostrar().valorString + "\n";
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
    public Retorno borrarElemento(int numLinea) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorbooleano = false;

        NodoLinea buscado = this.obtenerElemento(numLinea);

        if (buscado != null) {
            if (buscado == this.primero) {
                this.borrarInicio();
            } else if (buscado == this.ultimo) {
                this.borrarFinal();
            } else {
                buscado.getAnterior().setSiguiente(buscado.getSiguiente());
                buscado.getSiguiente().setAnterior(buscado.getAnterior());

                NodoLinea aux = this.ultimo;
                while (aux != null && numLinea <= aux.numLinea) {
                    if (aux.getSiguiente() != null) {
                        aux.getSiguiente().numLinea--;
                    }
                    aux = aux.getAnterior();
                }

                this.numLinea--;
                this.cantNodos--;
            }
        }
        return ret;
    }

    @Override
    public NodoLinea obtenerElemento(int n) {
        NodoLinea ret = null;

        if (!this.esVacia().valorbooleano) {
            NodoLinea aux = this.primero;
            while (aux != null && ret == null) {
                if (aux.numLinea == n) {
                    ret = aux;
                }
                aux = aux.getSiguiente();
            }
        }

        return ret;
    }

    @Override
    public NodoLinea obtenerPunteroElemento(int dato) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorbooleano = false;

        NodoLinea aux = this.getPrimero();
        NodoLinea punteroANodo = null;

        while (aux != null) {
            if (aux.getNumLinea() == dato) {
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

    public String mostrarRec(NodoLinea inicio, NodoLinea ultimo) {

        if (inicio == ultimo) {
            return inicio.getNumLinea() + "-";
        }
        return inicio.getNumLinea() + "-" + mostrarRec(inicio.getSiguiente(), ultimo);
    }

    public String mostrarRecInverso(NodoLinea inicio, NodoLinea ultimo) {

        if (inicio == ultimo) {
            return inicio.getNumLinea() + "-";
        }
        return mostrarRecInverso(inicio.getSiguiente(), ultimo) + inicio.getNumLinea() + "-";
    }

    public ListaLinea borrarFinal(ListaLinea ln) {

        if (ln != null) {
            if (ln.primero == ln.ultimo) {
                ln.setPrimero(null);
                ln.setUltimo(null);
            } else {
                ln.ultimo = ln.ultimo.getAnterior();
                ln.ultimo.setSiguiente(null);
            }
            ln.numLinea--;
            ln.cantNodos = ln.cantNodos - 1;
        } else {
            return null;
        }

        return ln;
    }

    public ListaLinea borrarRec(ListaLinea ln) {

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

    public NodoLinea borrarOcurrenciaElementoRec(ListaLinea ln, NodoLinea nl, String texto) {

        if (ln == null) {
            return null;
        } else {
            if (nl == null) {
                return nl;
            } else {

                if (nl.listaPalabras.buscarElemento(texto).valorbooleano) {

                    nl.listaPalabras.borrarOcurrenciaElemento(texto);
                    return borrarOcurrenciaElementoRec(ln, nl.siguiente, texto);

                } else {
                    return borrarOcurrenciaElementoRec(ln, nl.siguiente, texto);

                }
            }
        }
    }

    @Override
    public void borrarOcurrenciaElemento(String texto) {

        NodoLinea aux = this.getPrimero();
        while (aux != null) {
            aux.listaPalabras.borrarOcurrenciaElemento(texto);
            aux = aux.getSiguiente();
        }
    }


    @Override
    public void borrarOcurrenciaElemento(int n
    ) {

        NodoLinea aux = this.getPrimero();
        while (aux != null) {
            if (aux.getNumLinea() == n) {
                this.borrarElemento(n);
            }
            aux = aux.getSiguiente();
        }
    }


    @Override
    public int sumaPares() {
        int suma = 0;
        int cantRecorridas = 0;

        NodoLinea aux = this.getPrimero();
        while (aux != null) {
            if (cantRecorridas % 2 == 0) {
                suma = suma + aux.getNumLinea();
            }
            cantRecorridas++;
            aux = aux.getSiguiente();
        }

        return suma;
    }


    @Override
    public void eliminarDesde(int pos, int cant
    ) {
        int cantRecorrida = 0;
        int cantEliminados = 0;

        NodoLinea aux = this.getPrimero();
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
