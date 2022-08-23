
package trabajarconobligatorio;

public class NodoPalabra {
    
    
    NodoPalabra siguiente;
    NodoPalabra anterior;
    ListaMensajes listaMensaje;
    int numPalabra;
    String texto;
    
    public NodoPalabra(String texto) {
        this.siguiente = null;
        this.anterior = null;
        this.listaMensaje = new ListaMensajes();
        this.texto = texto;
        
    }

    public NodoPalabra getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoPalabra siguiente) {
        this.siguiente = siguiente;
    }

    public NodoPalabra getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoPalabra anterior) {
        this.anterior = anterior;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    
    
    
    public int getNumPalabra() {
        return numPalabra;
    }

    public void setNumPalabra(int numPalabra) {
        this.numPalabra = numPalabra;
    }
    
    
}
