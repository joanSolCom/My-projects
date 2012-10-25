package ie;
import java.io.Serializable;

/**
 * @author Aleix Fernández Donis
 * Cluster y Grupo C8G3
 * Creado el 5-May-2010
 *
 * Objetivo de la clase: Definir clase para lanzar las excepciones propias
 */


/*
* Clase para el manejo de excepciones del inference engine
*/





public class IEError extends Exception implements Serializable {

  IEError() {
  }

  IEError(String s) {
    super(s);
  }
}
