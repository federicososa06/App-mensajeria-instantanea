
package trabajarconobligatorio;

public class NodoLinea {
    
    
    NodoLinea siguiente;
    NodoLinea anterior;
    ListaPalabra listaPalabras;
    int numLinea;
    
    public NodoLinea() {
        this.siguiente = null;
        this.anterior = null;
        this.listaPalabras = new ListaPalabra();
        
    }



    public NodoLinea getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoLinea siguiente) {
        this.siguiente = siguiente;
    }

    public NodoLinea getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoLinea anterior) {
        this.anterior = anterior;
    }

    
    
    
    public int getNumLinea() {
        return numLinea;
    }

    public void setNumLinea(int numLinea) {
        this.numLinea = numLinea;
    }
    
    
}
