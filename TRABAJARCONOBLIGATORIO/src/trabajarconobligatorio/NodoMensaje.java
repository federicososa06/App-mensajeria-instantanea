package trabajarconobligatorio;

import java.util.Date;

public class NodoMensaje {

    int dato; //numMensaje
    NodoContacto origen;
    NodoContacto destino;
    Date fecha;
    NodoMensaje siguiente;
    ListaLinea listaLinea;

    public NodoMensaje(NodoContacto or, NodoContacto ds, Date fch) {
        this.siguiente = null;
        this.origen = or;
        this.destino = ds;
        this.fecha = fch;
        this.listaLinea = new ListaLinea();
        
    }

    public NodoMensaje getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoMensaje siguiente) {
        this.siguiente = siguiente;
    }

    public ListaLinea getListaLinea() {
        return listaLinea;
    }

    public void setListaLinea(ListaLinea listaLinea) {
        this.listaLinea = listaLinea;
    }

    public NodoContacto getOrigen() {
        return origen;
    }

    public void setOrigen(NodoContacto origen) {
        this.origen = origen;
    }

    public NodoContacto getDestino() {
        return destino;
    }

    public void setDestino(NodoContacto detino) {
        this.destino = detino;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }


    
    

}
