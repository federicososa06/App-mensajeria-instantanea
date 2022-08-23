package trabajarconobligatorio;

public class Diccionario implements IDiccionario {

    NodoDiccionario primero;
    NodoDiccionario ultimo;
    int cantNodos;
    int numPalabra = 1;
    ListaPalabra listaPalabras;

    public Diccionario() {
        this.primero = null;
        this.ultimo = null;
        this.cantNodos = 0;
        this.listaPalabras = new ListaPalabra();
    }

    public NodoDiccionario getPrimero() {
        return primero;
    }

    public void setPrimero(NodoDiccionario primero) {
        this.primero = primero;
    }

    public NodoDiccionario getUltimo() {
        return ultimo;
    }

    public void setUltimo(NodoDiccionario ultimo) {
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
    public Retorno agregarInicio(String dato) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);

        NodoDiccionario nuevo = new NodoDiccionario(dato);

        if (this.esVacia().valorbooleano) {
            this.setPrimero(nuevo);
            this.setUltimo(nuevo);
        } else {
            nuevo.setSiguiente(primero);
            this.setPrimero(nuevo);
        }

        this.cantNodos = this.cantNodos + 1;
        return ret;
    }

    @Override
    public Retorno agregarFinal(String dato) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);

        NodoDiccionario nuevo = new NodoDiccionario(dato);

        if (this.esVacia().valorbooleano) {
            this.setPrimero(nuevo);
            this.setUltimo(nuevo);
        } else {
            ultimo.setSiguiente(nuevo);
            this.setUltimo(nuevo);
        }

        this.cantNodos = this.cantNodos + 1;
        return ret;
    }

    @Override
    public Retorno agregarPalabra(String texto) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);

        NodoDiccionario nuevo = new NodoDiccionario(texto);
        NodoDiccionario aux = this.getPrimero();

//        compareTo()
//        Si cadena1>cadena2: devuelve un entero positivo
//        Si cadena1<cadena2: devuelve un entero negativo
//        Si cadena1 == cadena2: devuelve 0
        if (this.esVacia().valorbooleano || texto.compareTo(this.getPrimero().getPalabra()) < 1) {
            this.agregarInicio(texto);
        } else if (texto.compareTo(this.getUltimo().palabra) > 1) {
            this.agregarFinal(texto);
        } else {
            while (aux.getSiguiente() != null && texto.compareTo(aux.getSiguiente().getPalabra()) > 1) {
                aux = aux.getSiguiente();
            }
            this.cantNodos = this.cantNodos + 1;
            nuevo.setSiguiente(aux.getSiguiente());
            aux.setSiguiente(nuevo);
        }

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
                NodoDiccionario aux = this.getPrimero();
                while (aux.getSiguiente() != this.ultimo) {
                    aux = aux.getSiguiente();
                }
                aux.siguiente = null;
                this.setUltimo(aux);
            }

            this.cantNodos = this.cantNodos - 1;
        }

        return ret;
    }

    @Override
    public Retorno buscarElemento(String dato) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorbooleano = false;

        NodoDiccionario aux = getPrimero();
        while (aux != null) {
            if (aux.palabra.equals(dato)) {
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

        if (this.esVacia().valorbooleano) {
            ret.valorString = "Diccionario vacio";
        } else {

            NodoDiccionario aux = this.getPrimero();
            while (aux != null) {
                ret.valorString = ret.valorString + aux.getPalabra() + "\n";
                aux = aux.getSiguiente();
            }
        }

        return ret;
    }



    public NodoLinea borrarIncorrecto(ListaLinea ln, NodoLinea nl, NodoDiccionario dc) {

        if (ln == null) {
            return null;
        } else {
            if (nl == null) {
                return nl;
            } else {
                NodoDiccionario aux = this.primero;
                while (aux != null) {

                    if (nl.listaPalabras.buscarElemento(aux.palabra).valorbooleano) {

                        nl.listaPalabras.borrarOcurrenciaElemento(aux.palabra);
                        return borrarIncorrecto(ln, nl.siguiente, dc);

                    } else {
                        aux = aux.siguiente;
                    }
                }
                return borrarIncorrecto(ln, nl.siguiente, dc);
            }
        }
    }

    public Retorno imprimirIncorrecto(ListaLinea ln, NodoDiccionario dc){
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        
        ListaLinea linea = ln; //se copia la linea que se pasa para borrar las palabras que no existen
        this.borrarIncorrecto(linea, linea.primero, dc);
        
        ret.valorString = linea.mostrar().valorString; 
        
        return ret;
    }
    
    
    @Override
    public Retorno cantElementos() {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorEntero = this.getCantNodos();
        return ret;
    }

//    @Override
    public Retorno agregarOrd(String dato) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);

        NodoDiccionario nuevo = new NodoDiccionario(dato);
        NodoDiccionario aux = this.getPrimero();

//        Si cadena1>cadena2: devuelve un entero positivo
//        Si cadena1<cadena2: devuelve un entero negativo
//        Si cadena1 == cadena2: devuelve 0
        if (this.esVacia().valorbooleano || dato.compareTo(this.getPrimero().getPalabra()) < 1) {
            this.agregarInicio(dato);
        } else if (dato.compareTo(this.getUltimo().palabra) > 1) {
            this.agregarFinal(dato);
        } else {
            while (aux.getSiguiente() != null && dato.compareTo(aux.getSiguiente().getPalabra()) > 1) {
                aux = aux.getSiguiente();
            }
            this.cantNodos = this.cantNodos + 1;
            nuevo.setSiguiente(aux.getSiguiente());
            aux.setSiguiente(nuevo);
        }

        return ret;
    }

    @Override
    public Retorno borrarElemento(String dato) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorbooleano = false;

        NodoDiccionario aux = this.getPrimero();

        if (!this.esVacia().valorbooleano) {

            if (dato == this.primero.getPalabra()) {
                this.borrarInicio();

            } else if (dato == this.ultimo.getPalabra()) {
                this.borrarFinal();

            } else {
                while (aux.getSiguiente() != null) {
                    if (aux.getSiguiente().getPalabra() == dato) {
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
    public NodoDiccionario obtenerPunteroElemento(int dato) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorbooleano = false;

        NodoDiccionario aux = this.getPrimero();
        NodoDiccionario punteroANodo = null;

        while (aux != null) {
            if (aux.getDato() == dato) {
                punteroANodo = aux;
            }
            aux = aux.getSiguiente();
        }

        return punteroANodo;
    }

    @Override
    public NodoDiccionario insertarRecursivo(NodoDiccionario primero, String dato) {
        if (primero == null) {
            return new NodoDiccionario(dato);
        } else {
            primero.siguiente = insertarRecursivo(primero.siguiente, dato);
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

    public String mostrarRec(NodoDiccionario inicio, NodoDiccionario ultimo) {

        if (inicio == ultimo) {
            return inicio.getDato() + "-";
        }
        return inicio.getDato() + "-" + mostrarRec(inicio.getSiguiente(), ultimo);
    }

    public String mostrarRecInverso(NodoDiccionario inicio, NodoDiccionario ultimo) {

        if (inicio == ultimo) {
            return inicio.getDato() + "-";
        }
        return mostrarRecInverso(inicio.getSiguiente(), ultimo) + inicio.getDato() + "-";
    }


    
    @Override
    public void borrarOcurrenciaElemento(String n) {

        NodoDiccionario aux = this.getPrimero();
        while (aux.getSiguiente() != null) {
            if (aux.getPalabra() == n) {
                this.borrarElemento(n);
            }
            aux = aux.getSiguiente();
        }
    }


    @Override
    public int sumaPares() {
        int suma = 0;
        int cantRecorridas = 0;

        NodoDiccionario aux = this.getPrimero();
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

        NodoDiccionario aux = this.getPrimero();
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
