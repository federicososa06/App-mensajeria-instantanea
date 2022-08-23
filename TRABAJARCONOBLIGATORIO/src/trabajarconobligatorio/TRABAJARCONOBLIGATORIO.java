package trabajarconobligatorio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TRABAJARCONOBLIGATORIO {

    public static void main(String[] args) throws ParseException {

        Obligatorio obl = new Obligatorio();
        Prueba p = new Prueba();

        obl.crearSistemaMensajes(3);
        juegodeprueba1(obl, p);
        
        //listado(obl);

    }

    //JUEGO DE PRUEBA - OFICIAL
    public static void juegodeprueba1(Obligatorio obl, Prueba p) throws ParseException {

        //Creamos sistema
        p.ver(obl.crearSistemaMensajes(3).resultado, Retorno.Resultado.OK, "Se creara sistma para 3 palabras por linea");

        //Agregamos contactos
        //no se consideró contacto 0
        //p.ver(obl.agregarContacto(0, "Yo").resultado, Retorno.Resultado.OK, "Contacto 0");
        p.ver(obl.agregarContacto(1, "Carlos Perez").resultado, Retorno.Resultado.OK, "se agrega contacto Carlos Perez al sistema");
        p.ver(obl.agregarContacto(1, "Carlos Perez").resultado, Retorno.Resultado.ERROR, "se intenta agregar contacto Carlos Perez que ya existe");

        p.ver(obl.eliminarContacto(1).resultado, Retorno.Resultado.OK, "se elimina contacto Carlos Perez del sistema");

        p.ver(obl.agregarContacto(1, "Juan").resultado, Retorno.Resultado.OK, "se agrega contacto Juan  al sistema");
        p.ver(obl.agregarContacto(2, "Pedro").resultado, Retorno.Resultado.OK, "se agrega contacto Pedro al sistema");
        p.ver(obl.agregarContacto(3, "Ana").resultado, Retorno.Resultado.OK, "se agrega contacto Ana al sistema");
        p.ver(obl.agregarContacto(4, "Maria").resultado, Retorno.Resultado.OK, "se agrega contacto Maria al sistema");

        // Mostramos contactos
        System.out.println(obl.lc.mostrar().valorString);

        // definimos una fecha        
        Date fecha = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        fecha = formato.parse("23/03/2022");

        // Agregamos 3 mensajes a contacto Pedro que Existe
        p.ver(obl.agregarMensaje(1, 2, fecha).resultado, Retorno.Resultado.OK, "Se agrega primer mensaje para Pedro ");
        p.ver(obl.agregarMensaje(1, 2, fecha).resultado, Retorno.Resultado.OK, "Se agrega segundo mensaje para Pedro ");
        p.ver(obl.agregarMensaje(1, 2, fecha).resultado, Retorno.Resultado.OK, "Se agrega tercer  mensaje para Pedro ");

        // Agregamos 3 mensajes a contacto 8 que NO Existe
        p.ver(obl.agregarMensaje(0, 8, fecha).resultado, Retorno.Resultado.ERROR, "Se agrega mensaje a contacto inexistente ");

        // Agregamos lineas al mensaje 2 de pedro
        p.ver(obl.insertarLineaEnPosicion(1, 2, 1).resultado, Retorno.Resultado.OK, "Se agrega linea 1 en blanco al mensaje 2 de Pedro");
        p.ver(obl.insertarLineaEnPosicion(1, 2, 2).resultado, Retorno.Resultado.OK, "Se agrega linea 2 en blanco al mensaje 2 de Pedro");
        p.ver(obl.insertarLineaEnPosicion(1, 2, 3).resultado, Retorno.Resultado.OK, "Se agrega linea 3 en blanco al mensaje 2 de Pedro");
        System.out.println(obl.lc.mostrar().valorString);

        // Agregamos palabras al mensaje 2 de pedro en la linea 1
        p.ver(obl.insertarPalabraEnLinea(1, 2, 1, 1, "Hola").resultado, Retorno.Resultado.OK, "Se agrega palabra Hola linea 1 mensaje 2");
        p.ver(obl.insertarPalabraEnLinea(1, 2, 1, 2, "Pedro").resultado, Retorno.Resultado.OK, "Se agrega palabra Pedro linea 1 mensaje 2");

        System.out.println(obl.lc.mostrar().valorString);

        p.ver(obl.insertarPalabraEnLinea(1, 2, 2, 1, "Te").resultado, Retorno.Resultado.OK, "Se agrega palabra Te linea 2 mensaje 2");
        p.ver(obl.insertarPalabraEnLinea(1, 2, 2, 2, "LLamo").resultado, Retorno.Resultado.OK, "Se agrega palabra Te linea 2 mensaje 2");
        p.ver(obl.insertarPalabraEnLinea(1, 2, 2, 3, "En").resultado, Retorno.Resultado.OK, "Se agrega palabra Llamo linea 2 mensaje 2");
        p.ver(obl.insertarPalabraEnLinea(1, 2, 3, 1, "5min").resultado, Retorno.Resultado.OK, "Se agrega palabra 5min linea 3 mensaje 2");

        // Imprimimos mensaje 2 de pedro
        p.ver(obl.imprimirTexto(1, 2).resultado, Retorno.Resultado.OK, "Se imprime mensaje 2 de Pedro \n" + obl.imprimirTexto(1, 2).valorString);

        p.ver(obl.insertarPalabraYDesplazar(1, 2, 2, 2, "puedo").resultado, Retorno.Resultado.OK, "Se agrega palabra puedo en linea 2 "
                + "mensaje 2 posicion 2");

        // Imprimimos mensaje 2 de pedro
        p.ver(obl.imprimirTexto(1, 2).resultado, Retorno.Resultado.OK, "Se imprime mensaje 2 de Pedro \n" + obl.imprimirTexto(1, 2).valorString);

        p.ver(obl.borrarPalabra(1, 2, 2, 2).resultado, Retorno.Resultado.OK, "Se elimina palabra pos 2 de mensaje 2 linea 2 de pedro");

        // Imprimimos mensaje 2 de pedro
        p.ver(obl.imprimirTexto(1, 2).resultado, Retorno.Resultado.OK, "Se imprime mensaje 2 de Pedro \n" + obl.imprimirTexto(1, 2).valorString);

        //-------------------------------        
        fecha = formato.parse("24/03/2022");
        //-------------------------------

        p.ver(obl.agregarMensaje(1, 2, fecha).resultado, Retorno.Resultado.OK, "Se agrega  mensaje  4 para Pedro en  fecha 24/03/2022 ");
        p.ver(obl.agregarMensaje(1, 2, fecha).resultado, Retorno.Resultado.OK, "Se agrega  mensaje 5 para Pedro en  fecha 24/03/2022 ");

        // Agregamos lineas al mensaje 1 de pedro
        p.ver(obl.insertarLineaEnPosicion(1, 1, 1).resultado, Retorno.Resultado.OK, "Se agrega linea 1 en blanco al mensaje 1 de Pedro");
        p.ver(obl.insertarLineaEnPosicion(1, 1, 2).resultado, Retorno.Resultado.OK, "Se agrega linea 2 en blanco al mensaje 1 de Pedro");
        p.ver(obl.insertarLineaEnPosicion(1, 1, 3).resultado, Retorno.Resultado.OK, "Se agrega linea 3 en blanco al mensaje 1 de Pedro");

        // Agregamos palabras al mensaje 1 de pedro en la linea 1
        p.ver(obl.insertarPalabraEnLinea(1, 1, 1, 1, "Voy").resultado, Retorno.Resultado.OK, "Se agrega palabra voy linea 1 mensaje 2");
        p.ver(obl.insertarPalabraEnLinea(1, 1, 1, 2, "llegando").resultado, Retorno.Resultado.OK, "Se agrega palabra llegando linea 1 mensaje 2");

        p.ver(obl.agregarMensaje(1, 3, fecha).resultado, Retorno.Resultado.OK, "Se agrega  mensaje  4 para Ana en fecha 24/03/2022 ");
        p.ver(obl.agregarMensaje(1, 3, fecha).resultado, Retorno.Resultado.OK, "Se agrega  mensaje 5 para Ana en fecha 24/03/2022");

        //-------------------------------        
        fecha = formato.parse("25/03/2022");
        //-------------------------------

        p.ver(obl.agregarMensaje(1, 2, fecha).resultado, Retorno.Resultado.OK, "Se agrega  mensaje para Pedro en  fecha 25/03/2022 ");
        p.ver(obl.agregarMensaje(1, 4, fecha).resultado, Retorno.Resultado.OK, "Se agrega  mensaje para Maria en  fecha 25/03/2022 ");
        p.ver(obl.insertarLineaEnPosicion(1, 3, 1).resultado, Retorno.Resultado.OK, "Se agrega linea 1 en blanco al mensaje 2 de Pedro");
        p.ver(obl.insertarPalabraEnLinea(1, 3, 1, 1, "Estoy").resultado, Retorno.Resultado.OK, "Se agrega palabra Estoy linea 1 mensaje 2");
        p.ver(obl.insertarPalabraEnLinea(1, 3, 1, 2, "estacionando").resultado, Retorno.Resultado.OK, "Se agrega palabra "
                + "estacionando linea 1 mensaje 2");

        p.ver(obl.agregarMensaje(1, 4, fecha).resultado, Retorno.Resultado.OK, "Se agrega  mensaje para Maria en fecha 25/03/2022 ");
        p.ver(obl.agregarMensaje(1, 4, fecha).resultado, Retorno.Resultado.OK, "Se agrega  mensaje para Maria en fecha 25/03/2022");

        // Agregamos lineas al mensaje 2 de Maria
        p.ver(obl.insertarLineaEnPosicion(1, 2, 1).resultado, Retorno.Resultado.OK, "Se agrega linea 1 en blanco al mensaje 2 de Pedro");
        p.ver(obl.insertarLineaEnPosicion(1, 2, 2).resultado, Retorno.Resultado.OK, "Se agrega linea 2 en blanco al mensaje 2 de Pedro");

        // Agregamos palabras al mensaje 2 de maria en la linea 1
        p.ver(obl.insertarPalabraEnLinea(1, 2, 1, 1, "Hola").resultado, Retorno.Resultado.OK, "Se agrega palabra Hola linea 1 mensaje 2");
        p.ver(obl.insertarPalabraEnLinea(1, 2, 1, 2, "Maria").resultado, Retorno.Resultado.OK, "Se agrega palabra Maria linea 1 mensaje 2");
        p.ver(obl.insertarPalabraYDesplazar(1, 2, 1, 3, "llamo").resultado, Retorno.Resultado.OK, "Se agrega palabra llamo a mensaje "
                + "2 linea 1 de maria");
        p.ver(obl.insertarPalabraYDesplazar(1, 2, 1, 3, "Te").resultado, Retorno.Resultado.OK, "Se agrega palabra Te a mensaje 2 linea 1 de maria");
        p.ver(obl.insertarPalabraYDesplazar(1, 2, 2, 2, "y salimos").resultado, Retorno.Resultado.OK, "Se agrega palabra Te a "
                + "mensaje 2 linea 1 de maria");

        p.ver(obl.insertarPalabraEnLinea(1, 2, 2, 1, "Te").resultado, Retorno.Resultado.OK, "Se agrega palabra Te linea 2 mensaje 2");//<-
        p.ver(obl.insertarPalabraEnLinea(1, 2, 2, 2, "LLamo").resultado, Retorno.Resultado.ERROR, "Se agrega palabra LLamo linea 2 mensaje 2");

        p.ver(obl.cantidadDeMensajes(1).resultado, Retorno.Resultado.OK, "Mensajes de pedro \n" + obl.cantidadDeMensajes(1).valorString);

        p.ver(obl.ingresarPalabraDiccionario("hola").resultado, Retorno.Resultado.OK, "Se agrega palabra al diccionario");
        p.ver(obl.ingresarPalabraDiccionario("Estacionando").resultado, Retorno.Resultado.OK, "Se agrega palabra al diccionario");
        p.ver(obl.ingresarPalabraDiccionario("llegando").resultado, Retorno.Resultado.OK, "Se agrega palabra al diccionario");
        p.ver(obl.ingresarPalabraDiccionario("Te").resultado, Retorno.Resultado.OK, "Se agrega palabra al diccionario");
        p.ver(obl.ingresarPalabraDiccionario("Estoy").resultado, Retorno.Resultado.OK, "Se agrega palabra al diccionario");

        p.ver(obl.imprimirDiccionario().resultado, Retorno.Resultado.OK, "palabras del diccionario \n" + obl.imprimirDiccionario().valorString);
        p.ver(obl.ImprimirTextoIncorrecto().resultado, Retorno.Resultado.OK, "Palabras que no estan en el diccionario\n"
                + obl.ImprimirTextoIncorrecto().valorString);

        p.ver(obl.cantidadDeMensajes(1).resultado, Retorno.Resultado.OK, "Mensajes de juan \n" + obl.cantidadDeMensajes(1).valorString);
        p.ver(obl.cantidadDeMensajes(2).resultado, Retorno.Resultado.OK, "Mensajes de Pedro\n" + obl.cantidadDeMensajes(2).valorString);
        p.ver(obl.cantidadDeMensajes(3).resultado, Retorno.Resultado.OK, "Mensajes de Ana\n" + obl.cantidadDeMensajes(3).valorString);
        p.ver(obl.cantidadDeMensajes(4).resultado, Retorno.Resultado.OK, "Mensajes de Maria\n" + obl.cantidadDeMensajes(4).valorString);

        p.ver(obl.agregarMensaje(1, 2, fecha).resultado, Retorno.Resultado.OK, "Se agrega  mensaje   para Juan en fecha 25/03/2022 ");
        p.ver(obl.imprimirTexto(1, 1).resultado, Retorno.Resultado.OK, "Se Imprime mensaje 1 de Juan 25/03/2022 \n"
                + obl.imprimirTexto(1, 1).valorString);

        p.ver(obl.agregarMensaje(2, 4, fecha).resultado, Retorno.Resultado.OK, "Se agrega mensaje de contacto 2 a contacto 4");
        p.ver(obl.insertarLineaEnPosicion(2, 1, 1).resultado, Retorno.Resultado.OK, "Se agrega linea 1 en blanco al mensaje 1 de Juan");
        p.ver(obl.insertarLineaEnPosicion(2, 1, 2).resultado, Retorno.Resultado.OK, "Se agrega linea 2 en blanco al mensaje 1 de Juan");
        p.ver(obl.insertarLineaEnPosicion(2, 1, 3).resultado, Retorno.Resultado.OK, "Se agrega linea 3 en blanco al mensaje 1 de Juan");
        p.ver(obl.imprimirTexto(2, 1).resultado, Retorno.Resultado.OK, "Se Muestra  mensaje   para Juan en fecha 25/03/2022 ");

        p.ver(obl.insertarPalabraYDesplazar(2, 1, 1, 1, "P2").resultado, Retorno.Resultado.OK, "Se agrega P2 en linea 1 mensaje 1 posicion 1");
        p.ver(obl.insertarPalabraYDesplazar(2, 1, 1, 2, "P3").resultado, Retorno.Resultado.OK, "Se agrega P3 en linea 1 mensaje 1 posicion 1");
        p.ver(obl.insertarPalabraYDesplazar(2, 1, 1, 1, "P1").resultado, Retorno.Resultado.OK, "Se agrega P1 en linea 1 mensaje 1 posicion 1");
        p.ver(obl.imprimirTexto(2, 1).resultado, Retorno.Resultado.OK, "Se Muestra  mensaje   para Juan en fecha 25/03/2022 \n"
                + obl.imprimirTexto(2, 1).valorString);
        p.ver(obl.insertarPalabraYDesplazar(2, 1, 2, 1, "P4").resultado, Retorno.Resultado.OK, "Se agrega P4 en linea 2 mensaje 1 posicion 1");

        p.ver(obl.insertarPalabraYDesplazar(2, 1, 2, 2, "P1").resultado, Retorno.Resultado.OK, "Se agrega P1 en linea 2 mensaje 1 posicion 2");
        p.ver(obl.insertarPalabraYDesplazar(2, 1, 2, 3, "P5").resultado, Retorno.Resultado.OK, "Se agrega P5 en linea 3 mensaje 1 posicion 3");
        p.ver(obl.insertarPalabraYDesplazar(2, 1, 3, 1, "P6").resultado, Retorno.Resultado.OK, "Se agrega P6 en linea 2 mensaje 1 posicion 1");
        p.ver(obl.insertarPalabraYDesplazar(2, 1, 3, 2, "P7").resultado, Retorno.Resultado.OK, "Se agrega P7 en linea 2 mensaje 1 posicion 2");
        p.ver(obl.insertarPalabraYDesplazar(2, 1, 3, 3, "P1").resultado, Retorno.Resultado.OK, "Se agrega P1 en linea 3 mensaje 1 posicion 3");
        p.ver(obl.imprimirTexto(2, 1).resultado, Retorno.Resultado.OK, "Se Muestra  mensaje   para Juan en fecha 25/03/2022 \n"
                + obl.imprimirTexto(2, 1).valorString);

        p.ver(obl.borrarOcurrenciasPalabraEnTexto(2, 1, "P1").resultado, Retorno.Resultado.OK, "Se elimina P1 del mensaje 1 de Juan ");
        p.ver(obl.imprimirLinea(2, 1, 1).resultado, Retorno.Resultado.OK, "Se Muestra  la linea 1 para el mensaje 1 de Juan en fecha 25/03/2022 \n" + obl.imprimirLinea(1, 1, 1).valorString);
        p.ver(obl.imprimirTexto(2, 1).resultado, Retorno.Resultado.OK, "Se Muestra  mensaje   para Juan en fecha 25/03/2022 \n"
                + obl.imprimirTexto(2, 1).valorString);
        p.ver(obl.borrarLinea(2, 1, 2).resultado, Retorno.Resultado.OK, "Se elimina la línea 2 del mensaje 1 de Juan ");
        p.ver(obl.imprimirTexto(2, 1).resultado, Retorno.Resultado.OK, "Se Muestra  mensaje   para Juan en fecha 25/03/2022 \n"
                + obl.imprimirTexto(2, 1).valorString);
        p.ver(obl.borrarLinea(2, 1, 1).resultado, Retorno.Resultado.OK, "Se elimina la línea 1 del mensaje 1 de Juan ");
        p.ver(obl.imprimirTexto(2, 1).resultado, Retorno.Resultado.OK, "Se Muestra  mensaje   para Juan en fecha 25/03/2022 \n"
                + obl.imprimirTexto(2, 1).valorString);

        //Destruir sistema
        p.ver(obl.destruirSistemaMensajes().resultado, Retorno.Resultado.OK, " sistema eliminado");

        p.imprimirResultadosPrueba();
    }

    
    //PRUEBAS 
    public static void listado(Obligatorio obl) throws ParseException {

        Date fecha1 = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        fecha1 = formato.parse("12/03/2020");

        Date fecha2 = new Date();
        fecha2 = formato.parse("14/03/2021");

        Date fecha3 = new Date();
        fecha3 = formato.parse("16/03/2022");

        Date fecha4 = new Date();
        fecha4 = formato.parse("18/03/2023");

        Date fecha5 = new Date();
        fecha5 = formato.parse("20/03/2024");

        Date fecha6 = new Date();
        fecha6 = formato.parse("22/03/2025");

        obl.lc.agregarInicio(1, "Federico");
        obl.lc.agregarInicio(2, "Sebastian");
        obl.lc.agregarInicio(3, "Rodrigo");
        obl.lc.agregarInicio(4, "Jorge");
        obl.lc.agregarInicio(5, "Rossana");
        obl.agregarContacto(6, "Esther");

        obl.agregarMensaje(1, 1, fecha1);

        obl.agregarMensaje(1, 2, fecha1);
        obl.agregarMensaje(1, 2, fecha1);
        obl.agregarMensaje(1, 2, fecha1);

        obl.agregarMensaje(1, 3, fecha1);
        obl.agregarMensaje(1, 3, fecha2);
        obl.agregarMensaje(1, 3, fecha2);
        obl.agregarMensaje(1, 3, fecha2);
        obl.agregarMensaje(1, 3, fecha2);
        obl.agregarMensaje(1, 3, fecha6);

        obl.agregarMensaje(1, 4, fecha3);
        obl.agregarMensaje(1, 4, fecha3);

        obl.agregarMensaje(1, 5, fecha4);

        obl.agregarMensaje(1, 6, fecha5);

        obl.agregarMensaje(3, 4, fecha1);
        obl.agregarMensaje(3, 4, fecha1);

        obl.insertarLinea(3, 1);
        obl.insertarLinea(3, 1);
        obl.insertarLinea(3, 1);

        obl.insertarPalabraEnLinea(3, 1, 1, 2, "anana1");
        obl.insertarPalabraEnLinea(3, 1, 2, 3, "anana22");
        obl.insertarPalabraEnLinea(3, 1, 1, 1, "anana3");
        System.out.println(obl.imprimirTexto(3, 1).valorString);

        obl.insertarLinea(1, 1);
        obl.insertarLinea(1, 1);
        obl.insertarLinea(1, 1);
        obl.insertarLinea(1, 1);
        obl.insertarLinea(1, 5);
        obl.insertarLinea(1, 6);

//AGREGA LA palabra en la posicion, no DESPLAZA las siguientes lineas
        obl.insertarPalabraEnLinea(1, 4, 1, 1, "Palabra2");

        obl.insertarPalabraEnLinea(1, 1, 1, 2, "Palabra1");
        obl.insertarPalabraEnLinea(1, 1, 1, 3, "Palabra2");
        obl.insertarPalabraEnLinea(1, 1, 1, 1, "Palabra3");
        obl.insertarPalabraEnLinea(1, 1, 1, 4, "Palabra4");
        obl.insertarPalabraEnLinea(1, 1, 1, 5, "Palabra5");

        obl.insertarPalabraEnLinea(1, 1, 2, 1, "Palabra1");

        // obl.insertarPalabraEnLinea(1, 1, 2, 2, "Palabra2");
        obl.insertarPalabraEnLinea(1, 1, 3, 2, "Palabra2");
        obl.insertarPalabraEnLinea(1, 1, 3, 1, "Palabra1");

        System.out.println(obl.imprimirTexto(1, 1).valorString);

//AGREGA LA linea en la posicion y DESPLAZA
        obl.insertarPalabraEnLinea(1, 1, 5, 1, "Palabra1");
        obl.insertarLineaEnPosicion(1, 1, 2);

        System.out.println(obl.imprimirTexto(1, 1).valorString);

// AGREGARYDESPLAZAR NO FUNCIONA CORRECTAMENTE
        obl.insertarPalabraYDesplazar(1, 1, 4, 2, "desplazar");
        System.out.println(obl.imprimirTexto(1, 1).valorString);

//MOSTRAR CONTACTOS
        System.out.println(obl.lc.mostrar().valorString);
        obl.eliminarContacto(1);
        System.out.println(obl.lc.mostrar().valorString);
        System.out.println(obl.imprimirTexto(1, 1).valorString);

//BORRAR OCURRENCIAS REC
        obl.borrarOcurrenciasPalabraEnTexto(1, 1, "Palabra2");
        System.out.println(obl.imprimirTexto(1, 1).valorString);
//BORRAR TODO REC
        obl.borrarTodo(1, 1);
        System.out.println(obl.imprimirTexto(1, 1).valorString);
        System.out.println(obl.imprimirTexto(1, 1).valorString);
//BORRAR PALABRA
        obl.borrarPalabra(1, 1, 1, 1);
        System.out.println(obl.imprimirTexto(1, 1).valorString);
//DICCIONARIO
        obl.ingresarPalabraDiccionario("anana");
        obl.ingresarPalabraDiccionario("banana");
        obl.ingresarPalabraDiccionario("manzana");
        obl.ingresarPalabraDiccionario("Palabra2");
        obl.ingresarPalabraDiccionario("zzdas");
        obl.ingresarPalabraDiccionario("arbol");
        obl.ingresarPalabraDiccionario("arbol");
        System.out.println(obl.imprimirDiccionario().valorString);

        obl.borrarPalabraDiccionario("anana");
        System.out.println(obl.imprimirDiccionario().valorString);

        //IMPRIMIR INCORRECTO
        System.out.println(obl.ImprimirTextoIncorrecto().valorString);
        System.out.println(obl.imprimirTexto(1, 1).valorString);

        //MOSTRAR MATRIZ
        System.out.println(obl.cantidadDeMensajes(1).valorString);
    }
}
