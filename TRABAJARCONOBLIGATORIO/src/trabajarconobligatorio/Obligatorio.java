package trabajarconobligatorio;

import java.util.Date;

public class Obligatorio implements IObligatorio {

    public ListaContactos lc;
    public Diccionario dc;
    public int MAX_CANT_PALABRAS_X_LINEA;
    int cantMensajes = 0;

    //DICCIONARIO ES UNA LISTA DE PALABRAS QUE NO SE REPITEN
    public Obligatorio() {
        this.lc = lc;
        this.dc = dc;
        this.MAX_CANT_PALABRAS_X_LINEA = 0;
    }

    public ListaContactos getLc() {
        return lc;
    }

    public void setLc(ListaContactos lc) {
        this.lc = lc;
    }

    public Diccionario getDc() {
        return dc;
    }

    public void setDc(Diccionario dc) {
        this.dc = dc;
    }

    @Override
    public Retorno crearSistemaMensajes(int MAX_CANT_PALABRAS_X_LINEA) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorbooleano = false;

        this.setLc(new ListaContactos());
        this.setDc(new Diccionario());
        this.MAX_CANT_PALABRAS_X_LINEA = MAX_CANT_PALABRAS_X_LINEA;
        ret.valorbooleano = true;

        return ret;
    }

    @Override
    public Retorno destruirSistemaMensajes() {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorbooleano = false;

        this.setLc(null);
        this.setDc(null);
        this.MAX_CANT_PALABRAS_X_LINEA = 0;
        ret.valorbooleano = true;

        return ret;
    }

    
    @Override
    public Retorno agregarContacto(int numContacto, String nomContacto) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorbooleano = false;

        //validar que no exista
        if (!this.lc.buscarElemento(numContacto).valorbooleano && !this.lc.buscarElemento(nomContacto).valorbooleano) {
            this.lc.agregarInicio(numContacto, nomContacto);
            ret.valorbooleano = true;
        } else {
            ret = new Retorno(Retorno.Resultado.ERROR);
        }

        return ret;
    }

    @Override
    public Retorno eliminarContacto(int numContacto) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorbooleano = false;

        //validar que exista
        if (this.lc.buscarElemento(numContacto).valorbooleano) {
            this.lc.borrarElemento(numContacto);
            ret.valorbooleano = true;
        } else {
            ret = new Retorno(Retorno.Resultado.ERROR);
        }

        return ret;
    }

    @Override
    public Retorno agregarMensaje(int numContactoOrigen, int numContactoDestino, Date fecha) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorbooleano = false;

        //validar que existen
        NodoContacto origen = this.lc.obtenerElemento(numContactoOrigen);
        NodoContacto destino = this.lc.obtenerElemento(numContactoDestino);
        
        if (origen != null && destino != null) {
            origen.listaMensaje.agregarFinal(origen, destino, fecha);
            ret.valorbooleano = true;
            this.cantMensajes++;
        } else {
            ret = new Retorno(Retorno.Resultado.ERROR);
        }

        return ret;
    }

    @Override
    public Retorno eliminarMensaje(int numContactoOrigen, int numMensaje) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorbooleano = false;

        //validar que existe el contacto
        NodoContacto contacto = this.lc.obtenerElemento(numContactoOrigen);
        if (contacto != null) {
            //validar que existe el mensaje para ese contacto
            if (contacto.listaMensaje.buscarElemento(numMensaje).valorbooleano) {
                contacto.listaMensaje.borrarElemento(numMensaje);
                ret.valorbooleano = true;
                this.cantMensajes--;
            } else {
                ret = new Retorno(Retorno.Resultado.ERROR);
            }
        } else {
            ret = new Retorno(Retorno.Resultado.ERROR);
        }

        return ret;
    }

    @Override
    public Retorno imprimirTexto(int numContactoOrigen, int numMensaje) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorbooleano = false;

        //validar que exista el contacto
        NodoContacto contacto = this.lc.obtenerElemento(numContactoOrigen);
        if (contacto != null) {
            //validar que exista el mensaje de ese contacto
            NodoMensaje mensaje = contacto.listaMensaje.obtenerElemento(numMensaje);
            if (mensaje != null) {
                ret.valorString = mensaje.listaLinea.mostrar().valorString;
                ret.valorbooleano = true;
            } else {
                ret = new Retorno(Retorno.Resultado.ERROR);
                ret.valorString = "Texto vacío";
            }
        } else {
            ret = new Retorno(Retorno.Resultado.ERROR);
            ret.valorString = "No existe el contacto";
        }

        return ret;
    }

  
    @Override
    public Retorno insertarLinea(int numContactoOrigen, int numMensaje) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorbooleano = false;

        //validar que exista el contacto
        NodoContacto contacto = this.lc.obtenerElemento(numContactoOrigen);
        if (contacto != null) {

            //validar que exista el mensaje de ese contacto
            NodoMensaje mensaje = contacto.listaMensaje.obtenerElemento(numMensaje);
            if (mensaje != null) {

                mensaje.listaLinea.agregarLineaFinalRec(mensaje.listaLinea.primero);
                //mensaje.listaLinea.agregarFinal();
                ret.valorbooleano = true;

            } else {
                ret = new Retorno(Retorno.Resultado.ERROR);
                ret.valorbooleano = false;
            }
        } else {
            ret = new Retorno(Retorno.Resultado.ERROR);
        }

        return ret;
    }

    @Override
    public Retorno insertarLineaEnPosicion(int numContactoOrigen, int numMensaje, int posicionLinea) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorbooleano = false;

        //validar que exista el contacto
        NodoContacto contacto = this.lc.obtenerElemento(numContactoOrigen);
        if (contacto != null) {

            //validar que exista el mensaje de ese contacto
            NodoMensaje mensaje = contacto.listaMensaje.obtenerElemento(numMensaje);
            if (mensaje != null) {
                mensaje.listaLinea.agregarEnPosicion(posicionLinea);
                ret.valorbooleano = true;
            } else {
                ret = new Retorno(Retorno.Resultado.ERROR);
                ret.valorbooleano = false;
            }
        } else {
            ret = new Retorno(Retorno.Resultado.ERROR);
            ret.valorbooleano = false;
        }

        return ret;
    }

    @Override
    public Retorno borrarLinea(int numContactoOrigen, int numMensaje, int posicionLinea) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorbooleano = false;

        //validar que exista el contacto
        NodoContacto contacto = this.lc.obtenerElemento(numContactoOrigen);
        if (contacto != null) {

            //validar que exista ese mensaje de ese contacto
            NodoMensaje mensaje = contacto.listaMensaje.obtenerElemento(numMensaje);
            if (mensaje != null) {

                //validar que exista esa linea en ese mensaje
                if (mensaje.listaLinea.buscarElemento(posicionLinea).valorbooleano) {
                    mensaje.listaLinea.borrarElemento(posicionLinea);
                    ret.valorbooleano = true;
                } else {
                    ret = new Retorno(Retorno.Resultado.ERROR);
                    ret.valorbooleano = false;
                }
            } else {
                ret = new Retorno(Retorno.Resultado.ERROR);
                ret.valorbooleano = false;
            }
        } else {
            ret = new Retorno(Retorno.Resultado.ERROR);
            ret.valorbooleano = false;
        }

        return ret;
    }

    @Override
    public Retorno borrarTodo(int numContactoOrigen, int numMensaje) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorbooleano = false;

        //validar que exista el contacto
        NodoContacto contacto = this.lc.obtenerElemento(numContactoOrigen);
        if (contacto != null) {

            //validar que exista el mensaje del contacto
            NodoMensaje mensaje = contacto.listaMensaje.obtenerElemento(numMensaje);
            if (mensaje != null) {
                mensaje.listaLinea.borrarRec(mensaje.listaLinea);
                ret.valorbooleano = true;
            } else {
                ret = new Retorno(Retorno.Resultado.ERROR);
                ret.valorbooleano = false;
            }
        } else {
            ret = new Retorno(Retorno.Resultado.ERROR);
            ret.valorbooleano = false;
        }

        return ret;
    }

    
    @Override
    public Retorno borrarOcurrenciasPalabraEnTexto(int numContactoOrigen, int numMensaje, String palabraABorrar) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);

        //validar que exista el contacto
        NodoContacto contacto = this.lc.obtenerElemento(numContactoOrigen);
        if (contacto != null) {

            //validar que exista el mensaje del contacto
            NodoMensaje mensaje = contacto.listaMensaje.obtenerElemento(numMensaje);
            if (mensaje != null) {

                //validar que exista la palabra
                if (mensaje.listaLinea.buscarElemento(palabraABorrar).valorbooleano) {
                    mensaje.listaLinea.borrarOcurrenciaElementoRec(mensaje.listaLinea, mensaje.listaLinea.primero, palabraABorrar);
                } else {
                    ret = new Retorno(Retorno.Resultado.ERROR);
                }
            } else {
                ret = new Retorno(Retorno.Resultado.ERROR);
            }
        } else {
            ret = new Retorno(Retorno.Resultado.ERROR);
        }

        return ret;
    }

    @Override
    public Retorno insertarPalabraEnLinea(int numContactoOrigen, int numMensaje,
            int posicionLinea, int posicionPalabra, String palabraAIngresar) {

        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorbooleano = false;

        //validar que exista el contacto
        NodoContacto contacto = this.lc.obtenerPunteroElemento(numContactoOrigen);
        if (contacto != null) {

            //validar que exista el mensaje en el contacto
            NodoMensaje mensaje = contacto.listaMensaje.obtenerElemento(numMensaje);
            if (mensaje != null) {

                //validar que exista la linea en el mensaje y la cantidad de palabras
                NodoLinea linea = mensaje.listaLinea.obtenerElemento(posicionLinea);
                if (linea != null) {

                    //validar que no haya mas palabras de las permitidas
                    if (linea.listaPalabras.cantNodos < this.MAX_CANT_PALABRAS_X_LINEA) {

                        linea.listaPalabras.agregarEnPosicion(posicionPalabra, palabraAIngresar);
                        ret.valorbooleano = true;
                        ret.valorString = "Se ingresó";

                    } else {
                        ret = new Retorno(Retorno.Resultado.ERROR);
                        ret.valorString = "Se superó el max de palabras por lineas";
                    }
                } else {
                    ret = new Retorno(Retorno.Resultado.ERROR);
                    ret.valorString = "La linea no existe";
                }
            } else {
                ret = new Retorno(Retorno.Resultado.ERROR);
                ret.valorString = "No existe el mensaje";
            }
        } else {
            ret = new Retorno(Retorno.Resultado.ERROR);
            ret.valorString = "No existe el contacto";
        }

        return ret;
    }

    @Override
    public Retorno insertarPalabraYDesplazar(int numContactoOrigen, int numMensaje,
            int posicionLinea, int posicionPalabra, String palabraAIngresar) {

        Retorno ret = new Retorno(Retorno.Resultado.OK);

        //validar contacto
        NodoContacto contacto = this.lc.obtenerElemento(numContactoOrigen);
        if (contacto != null) {

            //validar mensaje del contacto
            NodoMensaje mensaje = contacto.listaMensaje.obtenerElemento(numMensaje);
            if (mensaje != null) {

                //validar linea del mensaje
                NodoLinea linea = mensaje.listaLinea.obtenerElemento(posicionLinea);
                if (linea != null) {

                    //validar posicionPalabra
                    if (posicionPalabra >= 1 && posicionPalabra <= MAX_CANT_PALABRAS_X_LINEA) {

                        mensaje.listaLinea.agregarPalabraEnLineaYDesplazar(posicionLinea, posicionPalabra, palabraAIngresar, MAX_CANT_PALABRAS_X_LINEA);

                    } else {
                        ret = new Retorno(Retorno.Resultado.ERROR);
                    }
                } else {
                    ret = new Retorno(Retorno.Resultado.ERROR);
                }
            } else {
                ret = new Retorno(Retorno.Resultado.ERROR);
            }
        } else {
            ret = new Retorno(Retorno.Resultado.ERROR);
        }

        return ret;
    }

    @Override
    public Retorno borrarPalabra(int numContactoOrigen, int numMensaje, int posicionLinea, int posicionPalabra) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorbooleano = false;

        //validar contacto
        NodoContacto contacto = this.lc.obtenerElemento(numContactoOrigen);
        if (contacto != null) {

            //validar mensaje del contacto
            NodoMensaje mensaje = contacto.listaMensaje.obtenerElemento(numMensaje);
            if (mensaje != null) {

                //validar linea en mensaje
                NodoLinea linea = mensaje.listaLinea.obtenerElemento(posicionLinea);
                if (linea != null) {

                    //validar posicion de la palabra en la linea
                    if (linea.listaPalabras.buscarElemento(posicionPalabra).valorbooleano) {
                        linea.listaPalabras.borrarElemento(posicionPalabra);
                        ret.valorbooleano = true;
                    }
                } else {
                    ret = new Retorno(Retorno.Resultado.ERROR);
                }
            } else {
                ret = new Retorno(Retorno.Resultado.ERROR);
            }
        } else {
            ret = new Retorno(Retorno.Resultado.ERROR);
        }

        return ret;
    }

    @Override
    public Retorno borrarOcurrenciasPalabraEnLinea(int numContactoOrigen, int numMensaje,
            int posicionLinea, String palabraABorrar
    ) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorbooleano = false;

        //validar contacto
        NodoContacto contacto = this.lc.obtenerElemento(numContactoOrigen);
        if (contacto != null) {

            //validar mensaje del contacto
            NodoMensaje mensaje = contacto.listaMensaje.obtenerElemento(numMensaje);
            if (mensaje != null) {

                //validar linea del mensaje
                NodoLinea linea = mensaje.listaLinea.obtenerElemento(posicionLinea);
                if (linea != null) {

                    linea.listaPalabras.borrarOcurrenciaElemento(palabraABorrar);
                    ret.valorbooleano = true;
                } else {
                    ret = new Retorno(Retorno.Resultado.ERROR);
                }
            } else {
                ret = new Retorno(Retorno.Resultado.ERROR);
            }
        } else {
            ret = new Retorno(Retorno.Resultado.ERROR);
        }

        return ret;
    }

    @Override
    public Retorno imprimirLinea(int numContactoOrigen, int numMensaje, int posicionLinea
    ) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorbooleano = false;
        ret.valorString = "";

        //validar contacto
        NodoContacto contacto = this.lc.obtenerElemento(numContactoOrigen);
        if (contacto != null) {

            //validar mensaje del contacto
            NodoMensaje mensaje = contacto.listaMensaje.obtenerElemento(numMensaje);
            if (mensaje != null) {

                //validar linea del mensaje
                NodoLinea linea = mensaje.listaLinea.obtenerElemento(posicionLinea);
                if (linea != null) {

                    ret.valorString = linea.numLinea + ": " + linea.listaPalabras.mostrar().valorString;
                    ret.valorbooleano = true;
                } else {
                    ret = new Retorno(Retorno.Resultado.ERROR);
                    ret.valorString = "no existe la linea";
                }
            } else {
                ret = new Retorno(Retorno.Resultado.ERROR);
                ret.valorString = "no existe el mensaje";
            }
        } else {
            ret = new Retorno(Retorno.Resultado.ERROR);
            ret.valorString = "no existe el contacto";
        }

        return ret;
    }

    @Override
    public Retorno ingresarPalabraDiccionario(String palabraAIngresar) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorbooleano = false;

        //validar que no exista la palabra
        if (!this.dc.buscarElemento(palabraAIngresar).valorbooleano) {
            this.dc.agregarPalabra(palabraAIngresar);
            ret.valorbooleano = true;
        } else {
            ret = new Retorno(Retorno.Resultado.ERROR);
        }

        return ret;
    }

    @Override
    public Retorno borrarPalabraDiccionario(String palabraABorrar
    ) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorbooleano = false;

        //validar que exista la palabra
        if (this.dc.buscarElemento(palabraABorrar).valorbooleano) {
            this.dc.borrarElemento(palabraABorrar);
            ret.valorbooleano = true;
        } else {
            ret = new Retorno(Retorno.Resultado.ERROR);
        }

        return ret;
    }

    @Override
    public Retorno imprimirDiccionario() {

        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorString = "";
        ret.valorString = this.dc.mostrar().valorString;

        return ret;
    }

    @Override
    public Retorno ImprimirTextoIncorrecto() {
        Retorno ret = new Retorno(Retorno.Resultado.OK);
        ret.valorString = "";

        NodoContacto contacto = this.lc.primero;
        while (contacto != null) {
            NodoMensaje mensaje = contacto.listaMensaje.primero;
            while (mensaje != null) {

                ret.valorString = ret.valorString + this.dc.imprimirIncorrecto(mensaje.listaLinea, this.dc.primero).valorString;

                mensaje = mensaje.getSiguiente();
            }
            contacto = contacto.getSiguiente();
        }

        return ret;
    }

    @Override
    public Retorno cantidadDeMensajes(int numContactoOrigen) {
        Retorno ret = new Retorno(Retorno.Resultado.OK);

        NodoContacto contacto = this.lc.obtenerElemento(numContactoOrigen);
        if (contacto != null) {
            ListaMensajes msj = contacto.listaMensaje;
            ret.valorString = contacto.listaMensaje.mostrarMatriz().valorString;
        } else {
            ret = new Retorno(Retorno.Resultado.ERROR);
        }

        return ret;
    }

}
