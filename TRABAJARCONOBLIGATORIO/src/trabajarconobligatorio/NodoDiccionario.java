
package trabajarconobligatorio;

public class NodoDiccionario {
    
    String palabra;
    int dato;
    NodoDiccionario siguiente;
    ListaPalabra listaPalabras;

    public NodoDiccionario(String palabra) {
        this.palabra = palabra;
        this.siguiente = null;
        this.listaPalabras = new ListaPalabra();
        this.dato = 1;
    }

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    public NodoDiccionario getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoDiccionario siguiente) {
        this.siguiente = siguiente;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }
    
    
    
    
}
