
package trabajarconobligatorio;

import java.util.Date;


public interface IListaMensajes {
    public Retorno esVacia();

    public Retorno agregarInicio(NodoContacto or, NodoContacto ds, Date fch);

    public Retorno agregarFinal(NodoContacto or, NodoContacto ds, Date fch);


    public Retorno borrarInicio();

    public Retorno borrarFinal();

    public Retorno borrarElemento(int dato);

    public Retorno buscarElemento(int dato);
    
    public NodoMensaje obtenerElemento(int n);

    public NodoMensaje obtenerPunteroElemento(int dato);

    public Retorno vaciar();

    public Retorno mostrar();

    public Retorno cantElementos();

    public Retorno mostrarRec();

    public Retorno mostrarRecInverso();
    
    public NodoMensaje insertarRecursivo(NodoMensaje primero, NodoContacto or, NodoContacto ds, Date fch);

    //Pre: recibe un número entero
    //Post: Elimina todas las ocurrencias de dicho elemento en la lista   
    public void borrarOcurrenciaElemento(int n);

    //Pre:
    //Post: Retorna la suma de todos los elementos que se encuentran en pos. pares en la lista
    public int sumaPares();

    //Pre: recibe una posición (>0) y una cantidad (>0)
    //Post: Elimina esa cantidad de elementos desde la posición indicada
    public void eliminarDesde(int pos, int cant);

    //pre: recibe una posición desde (>0) y una posición hasta (>0), con desde <= hasta
    //post:retorna una nueva lista que incluye los elementos que se encuentran entre desde y hasta
//    public ListaLinea obtenerSubLista(int desde, int hasta);
}
