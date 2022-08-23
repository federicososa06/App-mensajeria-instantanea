package trabajarconobligatorio;

import java.util.Date;

public class ListaMensajes implements IListaMensajes {

    int dato;
    NodoMensaje primero;
    NodoMensaje ultimo;
    int cantNodos;

    ListaFechas listaFechas = new ListaFechas();
    ListaContactos listaContactos = new ListaContactos();

    int numMensaje = 1;

    public ListaMensajes() {
        this.primero = null;
        this.ultimo = null;
        this.cantNodos = 0;
    }

    public NodoMensaje getPrimero() {
        return primero;
    }

    public void setPrimero(NodoMensaje primero) {
        this.primero = primero;
    }

    public NodoMensaje getUltimo() {
        return ultimo;
    }

    public void setUltimo(NodoMensaje ultimo) {
        this.ultimo = ultimo;
    }

    public int getCantNodos() {
        return cantNodos;
    }

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    @Override
    public Retorno esVacia() {
        Retorno ret = new Retorno(Retorno.Resultado.OK);

        ret.valorbooleano = this.primero == null;

        return ret;
    }

    @Override
    public Retorno agregarInicio(NodoContacto or, NodoContacto ds, Date fch) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);

        NodoMensaje nuevo = new NodoMensaje(or, ds, fch);
        nuevo.dato = this.numMensaje;

        if (this.esVacia().valorbooleano) {
            this.setPrimero(nuevo);
            this.setUltimo(nuevo);
        } else {
            nuevo.setSiguiente(primero);
            this.setPrimero(nuevo);
        }

        this.numMensaje++;
        this.cantNodos = this.cantNodos + 1;
        return ret;
    }

    @Override
    public Retorno agregarFinal(NodoContacto or, NodoContacto ds, Date fch) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);

        NodoMensaje nuevo = new NodoMensaje(or, ds, fch);
        nuevo.dato = this.numMensaje; 

        if (this.esVacia().valorbooleano) {
            this.setPrimero(nuevo);
            this.setUltimo(nuevo);
        } else {
            ultimo.setSiguiente(nuevo);
            this.setUltimo(nuevo);
        }

        this.numMensaje++;
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
                NodoMensaje aux = this.getPrimero();
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
    public Retorno buscarElemento(int dato) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorbooleano = false;

        NodoMensaje aux = getPrimero();
        while (aux != null) {
            if (aux.getDato() == dato) {
                ret.valorbooleano = true;
            }
            aux = aux.getSiguiente();
        }

        return ret;
    }

    @Override
    public NodoMensaje obtenerElemento(int n) {
        NodoMensaje ret = null;

        if (this.buscarElemento(n).valorbooleano) {
            NodoMensaje aux = this.primero;
            while (aux != null && ret == null) {
                if (aux.getDato() == n) {
                    ret = aux;
                }
                aux = aux.getSiguiente();
            }
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

        NodoMensaje aux = this.getPrimero();
        while (aux != null) {
            ret.valorString = ret.valorString + "Mensaje:" + aux.getDato() + "\n" + aux.listaLinea.mostrar().valorString + "\n";
            aux = aux.getSiguiente();
        }

        return ret;
    }

    public Retorno buscarFecha(Date f) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorbooleano = false;

        NodoMensaje aux = getPrimero();
        while (aux != null) {
            if (aux.fecha == f) {
                ret.valorbooleano = true;
            }
            aux = aux.getSiguiente();
        }
        return ret;
    }

    public int cantFecha() {
        NodoMensaje aux = this.primero;

        while (aux != null) {
            if (!this.listaFechas.buscarElemento(aux.fecha).valorbooleano) {
                listaFechas.agregarFinal(aux.fecha);
            }
            aux = aux.siguiente;
        }
        return this.listaFechas.cantNodos;
    }

    public int cantDest() {
        NodoMensaje aux = this.primero;

        while (aux != null) {
            if (!this.listaContactos.buscarElemento(aux.destino.nombre).valorbooleano) {
                listaContactos.agregarFinal(aux.dato, aux.destino.nombre);
            }
            aux = aux.siguiente;
        }
        return this.listaContactos.cantNodos;
    }

    public int cantMensajesPorFechaPorContacto(String cntDestino, Date fch) {
        int ret = 0;

        NodoMensaje aux = this.primero;
        while (aux != null) {

            if (aux.destino.nombre.equals(cntDestino) && aux.fecha.equals(fch)) {
                ret++;
            }

            aux = aux.siguiente;
        }

        return ret;
    }

  
    public Object[][] llenarMatriz() {

        int cantFechas = this.cantFecha() + 1;
        int cantDestinos = this.cantDest() + 1;

        Object[][] m = new Object[cantFechas][cantDestinos];

        for (int i = 0; i <= cantFechas - 1; i++) {
            for (int j = 0; j <= cantDestinos - 1; j++) {

                if (i == 0 && j == 0) {
                    m[i][j] = "         ";

                } else {

                    NodoContacto cnt = this.listaContactos.obtenerElementoPorPosicion(j);
                    NodoFecha fch = this.listaFechas.obtenerElemento(i);
                    
                    if (i == 0) {
                        if (cnt != null) {
                            m[i][j] = cnt.nombre;
                        }
                    }

                    if (j == 0) {
                        if (fch != null) {
                            m[i][j] = fch.fecha.toString();//this.listaFechas.mostrarFecha(fch).valorString;
                        }
                    }

                    if (i != 0 && j != 0) {
                        m[i][j] = this.cantMensajesPorFechaPorContacto(cnt.nombre,fch.fecha);
                    }
                }
            }
        }
        return m;
    }

    
    public Retorno mostrarMatriz() {

        Retorno ret = new Retorno(Retorno.Resultado.OK);
        Object[][] m = this.llenarMatriz();

        int filas = m.length;
        int columnas = m[0].length;

        ret.valorString = "\n";

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {

                ret.valorString = ret.valorString + m[i][j] + "   ";

            }
            ret.valorString = ret.valorString + "\n";
        }
        return ret;

    }

    @Override
    public Retorno cantElementos() {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorEntero = this.getCantNodos();
        return ret;
    }

//    @Override
//    public Retorno agregarOrd(NodoContacto or, NodoContacto ds, Date fch) {
//        Retorno ret = new Retorno(Retorno.Resultado.OK);
//
//        NodoMensaje nuevo = new NodoMensaje( or, ds, fch);
//        NodoMensaje aux = this.getPrimero();
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
    @Override
    public Retorno borrarElemento(int dato) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorbooleano = false;

        NodoMensaje aux = this.getPrimero();

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
    public NodoMensaje obtenerPunteroElemento(int dato) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorbooleano = false;

        NodoMensaje aux = this.getPrimero();
        NodoMensaje punteroANodo = null;

        while (aux != null) {
            if (aux.getDato() == dato) {
                punteroANodo = aux;
            }
            aux = aux.getSiguiente();
        }

        return punteroANodo;
    }

    @Override
    public NodoMensaje insertarRecursivo(NodoMensaje primero, NodoContacto or, NodoContacto ds, Date fch) {
        if (primero == null) {
            return new NodoMensaje(or, ds, fch);
        } else {
            primero.siguiente = insertarRecursivo(primero.siguiente, or, ds, fch);
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

    public String mostrarRec(NodoMensaje inicio, NodoMensaje ultimo) {

        if (inicio == ultimo) {
            return inicio.getDato() + "-";
        }
        return inicio.getDato() + "-" + mostrarRec(inicio.getSiguiente(), ultimo);
    }

    public String mostrarRecInverso(NodoMensaje inicio, NodoMensaje ultimo) {

        if (inicio == ultimo) {
            return inicio.getDato() + "-";
        }
        return mostrarRecInverso(inicio.getSiguiente(), ultimo) + inicio.getDato() + "-";
    }

   
  
    @Override
    public void borrarOcurrenciaElemento(int n) {

        NodoMensaje aux = this.getPrimero();
        while (aux != null) {
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

        NodoMensaje aux = this.getPrimero();
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

        NodoMensaje aux = this.getPrimero();
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
