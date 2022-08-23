
package trabajarconobligatorio;

import java.util.Date;

public class NodoFecha {
    
    NodoFecha siguiente;
    NodoFecha anterior;
    Date fecha;
    int num;
    
    public NodoFecha(Date fecha) {
        this.siguiente = null;
        this.anterior = null;
        this.fecha = fecha;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    
    
    
    public NodoFecha getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoFecha siguiente) {
        this.siguiente = siguiente;
    }

    public NodoFecha getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoFecha anterior) {
        this.anterior = anterior;
    }
    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date f) {
        this.fecha = f;
    }
    
    
}
