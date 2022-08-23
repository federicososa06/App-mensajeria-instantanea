
package trabajarconobligatorio;

public class NodoContacto {
    
    //dato es el numero de telefonico
    int dato;
    int numMensajes = 1;
    String nombre;
    NodoContacto siguiente;
    ListaMensajes listaMensaje;
    int posicion; 
    

    public NodoContacto(int dato, String nombre) {
        this.dato = dato;
        this.nombre = nombre;
        this.siguiente = null;
        this.listaMensaje = new ListaMensajes();
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
    
    

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    public NodoContacto getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoContacto siguiente) {
        this.siguiente = siguiente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ListaMensajes getListaMensaje() {
        return listaMensaje;
    }

    public void setListaMensaje(ListaMensajes listaMensaje) {
        this.listaMensaje = listaMensaje;
    }

    public int getNumMensajes() {
        return numMensajes;
    }

    public void setNumMensajes(int numMensajes) {
        this.numMensajes = numMensajes;
    }
    
    
    
    
}
