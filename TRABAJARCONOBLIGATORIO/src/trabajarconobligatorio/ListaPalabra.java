package trabajarconobligatorio;

public class ListaPalabra implements IListaPalabras {

    NodoPalabra primero;
    NodoPalabra ultimo;
    int cantNodos;
    int numPalabra = 1;

    public ListaPalabra() {
        this.primero = null;
        this.ultimo = null;
        this.cantNodos = 0;
    }

    public NodoPalabra getPrimero() {
        return primero;
    }

    public void setPrimero(NodoPalabra primero) {
        this.primero = primero;
    }

    public NodoPalabra getUltimo() {
        return ultimo;
    }

    public void setUltimo(NodoPalabra ultimo) {
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
    public Retorno agregarInicio(String texto) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);

        NodoPalabra nuevo = new NodoPalabra(texto);
        nuevo.numPalabra = 1;

        if (this.esVacia().valorbooleano) {
            this.setPrimero(nuevo);
            this.setUltimo(nuevo);
        } else {

            this.primero.setAnterior(nuevo);
            nuevo.setSiguiente(this.primero);
            nuevo.setAnterior(null);
            this.primero = nuevo;

            NodoPalabra aux = this.ultimo;
            while (aux != null) {
                if (aux.getSiguiente() != null) {
                    aux.getSiguiente().numPalabra++;
                }
                aux = aux.getAnterior();
            }
        }

        this.numPalabra++;
        this.cantNodos = this.cantNodos + 1;
        return ret;
    }

    @Override
    public Retorno agregarFinal(String texto) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);

        NodoPalabra nuevo = new NodoPalabra(texto);
        nuevo.numPalabra = this.numPalabra++; 

        if (this.esVacia().valorbooleano) {
            this.setPrimero(nuevo);
            this.setUltimo(nuevo);
        } else {
            nuevo.setAnterior(this.ultimo);
            this.ultimo.setSiguiente(nuevo);
            this.ultimo = nuevo;
        }

        this.numPalabra++;
        this.cantNodos = this.cantNodos + 1;
        return ret;
    }

    @Override
    public Retorno agregarEnPosicion(int posicion, String texto) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);

        NodoPalabra nuevo = new NodoPalabra(texto);
        nuevo.numPalabra = posicion;

        if (this.ultimo == null || posicion >= this.ultimo.numPalabra) {
            this.agregarFinal(texto);

        } else if (this.primero == null || posicion <= this.primero.numPalabra) {
            this.agregarInicio(texto);

        } else {

            //desplazar las siguientes +1
            NodoPalabra aux = this.ultimo;
            while (aux.getAnterior() != null && posicion <= aux.numPalabra) {
                if (aux.getSiguiente() != null) {
                    aux.getSiguiente().numPalabra++;
                }
                aux = aux.getAnterior();
            }
            aux.getSiguiente().numPalabra++;

            aux.getSiguiente().setAnterior(nuevo);
            nuevo.setSiguiente(aux.getSiguiente());
            nuevo.setAnterior(aux);
            aux.setSiguiente(nuevo);

            ret.valorbooleano = true;
            this.numPalabra++;
            this.cantNodos++;
        }

        return ret;
    }

//    @Override
//    public void agregarOrd(int n) {
//
//        NodoDoble nuevo = new NodoDoble(n);
//        if (this.esVacia() || n < this.primero.getDato()) {
//            this.agregarInicio(n);
//        } else if (n > this.ultimo.getDato()) {
//            this.agregarFinal(n);
//        } else {
//            NodoDoble aux = this.primero;
//            while (aux != null && aux.getDato() < n) {
//                aux = aux.getSig();
//            }
//            aux.getAnt().setSig(nuevo);
//            aux.setAnt(nuevo);
//            nuevo.setSig(aux);
//            nuevo.setAnt(aux.getAnt());
//
//            this.cantNodos++;
//        }
//
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

                NodoPalabra aux = this.ultimo;
                while (aux != null && 1 <= aux.numPalabra) {
                    if (aux.getSiguiente() != null) {
                        aux.getSiguiente().numPalabra--;
                    }
                    aux = aux.getAnterior();
                }

            }
            ret.valorbooleano = true;
            this.numPalabra--;
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
            this.numPalabra--;
            this.cantNodos = this.cantNodos - 1;
        }

        return ret;
    }

    @Override
    public Retorno buscarElemento(int dato
    ) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorbooleano = false;

        NodoPalabra aux = getPrimero();
        while (aux != null) {
            if (aux.getNumPalabra() == dato) {
                ret.valorbooleano = true;
            }
            aux = aux.getSiguiente();
        }

        return ret;
    }

    public Retorno buscarElemento(String texto) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorbooleano = false;

        NodoPalabra aux = getPrimero();
        while (aux != null) {
            if (aux.texto == texto) {
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
        this.numPalabra = 1;
        return ret;
    }

    @Override
    public Retorno mostrar() {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorString = "";

        NodoPalabra aux = this.getPrimero();
        while (aux != null) {
            ret.valorString = ret.valorString + aux.texto + " ";
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
    public Retorno borrarElemento(int numPalabra) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorbooleano = false;

        NodoPalabra buscado = this.obtenerElemento(numPalabra);

        if (buscado != null) {
            if (buscado == this.primero) {
                this.borrarInicio();
            } else if (buscado == this.ultimo) {
                this.borrarFinal();
            } else {

                buscado.getAnterior().setSiguiente(buscado.getSiguiente());
                buscado.getSiguiente().setAnterior(buscado.getAnterior());

                NodoPalabra aux = this.ultimo;
                while (aux != null && numPalabra <= aux.numPalabra) {
                    if (aux.getSiguiente() != null) {
                        aux.getSiguiente().numPalabra++;
                    }
                    aux = aux.getAnterior();
                }

                this.numPalabra--;
                this.cantNodos--;
            }
        }
        return ret;
    }

    @Override
    public Retorno borrarElemento(String texto) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorbooleano = false;

        NodoPalabra buscado = this.obtenerElemento(texto);

        if (buscado != null) {
            if (buscado == this.primero) {
                this.borrarInicio();
            } else if (buscado == this.ultimo) {
                this.borrarFinal();
            } else {
                buscado.getAnterior().setSiguiente(buscado.getSiguiente());
                buscado.getSiguiente().setAnterior(buscado.getAnterior());

                NodoPalabra aux = this.ultimo;
                while (aux != null && buscado.numPalabra <= aux.numPalabra) {
                    if (aux.getSiguiente() != null) {
                        aux.getSiguiente().numPalabra++;
                    }
                    aux = aux.getAnterior();
                }

                this.numPalabra--;
                this.cantNodos--;
            }
        }
        return ret;
    }

    @Override
    public NodoPalabra obtenerElemento(int n) {
        NodoPalabra ret = null;

        if (!this.esVacia().valorbooleano) {
            NodoPalabra aux = this.primero;
            while (aux != null && ret == null) {
                if (aux.numPalabra == n) {
                    ret = aux;
                }
                aux = aux.getSiguiente();
            }
        }
        return ret;
    }

    @Override
    public NodoPalabra obtenerElemento(String texto) {
        NodoPalabra ret = null;

        if (!this.esVacia().valorbooleano) {
            NodoPalabra aux = this.primero;
            while (aux != null && ret == null) {
                if (aux.texto == texto) {
                    ret = aux;
                }
                aux = aux.getSiguiente();
            }
        }

        return ret;
    }

    @Override
    public NodoPalabra obtenerPunteroElemento(int dato) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorbooleano = false;

        NodoPalabra aux = this.getPrimero();
        NodoPalabra punteroANodo = null;

        while (aux != null) {
            if (aux.getNumPalabra() == dato) {
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

    public String mostrarRec(NodoPalabra inicio, NodoPalabra ultimo) {

        if (inicio == ultimo) {
            return inicio.getNumPalabra() + "-";
        }
        return inicio.getNumPalabra() + "-" + mostrarRec(inicio.getSiguiente(), ultimo);
    }

    public String mostrarRecInverso(NodoPalabra inicio, NodoPalabra ultimo) {

        if (inicio == ultimo) {
            return inicio.getNumPalabra() + "-";
        }
        return mostrarRecInverso(inicio.getSiguiente(), ultimo) + inicio.getNumPalabra() + "-";
    }

    @Override
    public void borrarOcurrenciaElemento(String texto) {

        NodoPalabra aux = this.getPrimero();
        while (aux != null) {
            if (aux.texto.equals(texto)) {
                borrarElemento(aux.numPalabra);
            }
            aux = aux.getSiguiente();
        }
    }


    
    @Override
    public void borrarOcurrenciaElemento(int n) {

        NodoPalabra aux = this.getPrimero();
        while (aux != null) {
            if (aux.getNumPalabra() == n) {
                this.borrarElemento(n);
            }
            aux = aux.getSiguiente();
        }
    }


    @Override
    public int sumaPares() {
        int suma = 0;
        int cantRecorridas = 0;

        NodoPalabra aux = this.getPrimero();
        while (aux != null) {
            if (cantRecorridas % 2 == 0) {
                suma = suma + aux.getNumPalabra();
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

        NodoPalabra aux = this.getPrimero();
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
