
package trabajarconobligatorio;
import java.util.Date;

public interface IObligatorio {

//Pre: MAX_CANT_PALABRAS_X_LINEA debe ser un número mayor que 0     
//Post: se crea el sistema
Retorno crearSistemaMensajes(int MAX_CANT_PALABRAS_X_LINEA);

//Post se destruye el sitema
Retorno destruirSistemaMensajes();

//Pre: el numero debe ser un numero mayor a 0
//Post: se inserta un nuevo contacto en el sistema
Retorno agregarContacto(int numContacto, String nomContacto);

//Pre: el numero debe ser un numero mayor a 0
//Post: se elimina el contacto
Retorno eliminarContacto(int numContacto);

//Pre: os numeros deben ser mayor a 0
//Post: se crea y vincula el mensaje entre los contactos
Retorno agregarMensaje(int numContactoOrigen, int numContactoDestino, Date fecha);

//Pre: los numeros deben ser mayor a 0
//Post: se borra el mensaje del contacto ingresado
Retorno eliminarMensaje(int numContactoOrigen, int numMensaje);

//Pre: los numeros deben ser mayor a 0
//Post: se muestran las lineas del mensaje
Retorno imprimirTexto(int numContactoOrigen, int numMensaje);

//Pre: los numeros deben ser mayor a 0
//Post: se inserta una nueva lineas en el mensaje
Retorno insertarLinea(int numContactoOrigen, int numMensaje);

//Pre: los numeros deben ser mayor a 0, 
//      posicionLinea debe ser menor o igual a la cantidad de lineas actuales+1
//Post: se inserta una nueva lineas en el mensaje en la posición indicada
Retorno insertarLineaEnPosicion(int numContactoOrigen, int numMensaje, int posicionLinea);

//Pre: los numeros deben ser mayor a 0, posicionLinea debe ser menor o 
//      igual a la cantidad de lineas actuales+1
//Post: se borra la lineas en la posición indicada de ese mensaje
Retorno borrarLinea(int numContactoOrigen, int numMensaje, int posicionLinea);

//Pre: los numeros deben ser mayor a 0
//Post: se borran todas las lineas del mensaje
Retorno borrarTodo(int numContactoOrigen, int numMensaje);


//Pre: los numeros deben ser mayor a 0
//Post: se borran todas las ocurrencias en el mensaje indicado
Retorno borrarOcurrenciasPalabraEnTexto(int numContactoOrigen, int numMensaje, String palabraABorrar);


//Pre: los numeros deben ser mayor a 0, posicionLinea es válida solamente si existe en el texto
//      posicionPalabra debe ser menor o igual a la cantidad de las palabras en la linea+1
//Post: Inserta la palabra palabraAIngresar en la línea posicionLinea 
//      y dentro de la línea en la posición posicionPalabra
Retorno insertarPalabraEnLinea(int numContactoOrigen, int numMensaje, int posicionLinea, 
        int posicionPalabra, String palabraAIngresar);

//Pre: los numeros deben ser mayor a 0, posicionLinea es válida solamente si existe en el texto
//      posicionPalabra debe ser menor o igual a la cantidad de las palabras en la linea+1
//Post: Inserta la palabra palabraAIngresar en la línea posicionLinea 
//      y dentro de la línea en la posición posicionPalabra, desplaza todas las palabras siguientes 
Retorno insertarPalabraYDesplazar(int numContactoOrigen, int numMensaje, int posicionLinea, 
        int posicionPalabra, String palabraAIngresar);


//Pre: los numeros deben ser mayor a 0, posicionLinea debe existir en el texto y 
//      posicionPalabra debe existir en la linea
//Post: borra la palabra en la posición posicionPalabra de la línea posicionLinea 
//      y mueve todas las palabras
Retorno borrarPalabra(int numContactoOrigen, int numMensaje, int posicionLinea, 
        int posicionPalabra);

//Pre:los numeros deben ser mayor a 0, posicionLinea debe existir en el texto
//Post:Borra todas las ocurrencias de la palabra palabraABorrar en la línea indicada 
//      y  mueve todas las palabras una posicion hacia adelante
Retorno borrarOcurrenciasPalabraEnLinea(int numContactoOrigen, int numMensaje, 
        int posicionLinea , String palabraABorrar);

//Pre: los numeros deben ser mayor a 0, posicionLinea debe existir en el texto
//Post: imprime la linea con todas sus palabras
Retorno imprimirLinea(int numContactoOrigen, int numMensaje, int posicionLinea);

//Post: se ingresa una palabra en el diccionario si no existe
Retorno ingresarPalabraDiccionario(String palabraAIngresar);

//Post: muestra las palabras del diccionario ordenadas alfabéticamente
Retorno imprimirDiccionario();

//Post: muestra las palabras del texto que no existen en el diccionario
Retorno ImprimirTextoIncorrecto();


//Post: borra una palabra del diccionario si existe
Retorno borrarPalabraDiccionario(String palabraABorrar);

//Pre: el numero debe ser mayor a 0
//Post: muestra en una matriz la cantidad de mensajes que fueron enviados 
//      por día desde un contacto indicado
Retorno cantidadDeMensajes(int numContactoOrigen);


}
