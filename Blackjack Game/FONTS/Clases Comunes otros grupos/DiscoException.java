/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dominio;

/**
*
* @author Laura Rivera Sanchez
* Cluster i Grupo: C8G1
* Creado el 18 de abril de 2010, 10:57
*
* Objetivo de la clase: Lanza excepciones de Disco.
* Clases utilizadas: Exception.
*/
public class DiscoException extends java.lang.Exception {
  /** Definición de los atributos */

    /**
     * Atributo que contiene el nombre del atributo que ha generado la
     * excepción
     
     */

    private String atributo;

    /**
     * Atributo que contiene el nombre de la clase que ha generado la
     * excepción
     */

    private String clase;

    /* Definición de los métodos */

    /**
     * Constructora de la clase por defecto, genera una nueva
     * instancia de la clase DiscoException. Solo hace falta llamar al
     * constructor de la clase superior
     *
     * @param -
     * @return -
     * @throws -
     */


    public DiscoException() {

        super();
        this.atributo = new String();
        this.clase = new String();

    } //END_CONSTRUCTOR

      /**
     * Constructora de la clase con parametros, genera una nueva
     * isntancia de la clase DiscoException con un mensaje, atributo y clase.
     *
     * @param Un String que representa el mensaje (mens), un String que
     * representa el atributo y otro String que representa la clase.
     * @return -
     * @throws -
     */

    public DiscoException(String mens, String atr, String clase) {

        super(mens);
        this.atributo = atr;
        this.clase = clase;

    } //END_CONSTRUCTOR

    /**
     * Metodo que devuelve el mensaje de error de la
     * clase Throwable de la que hereda Exception
     *
     * @param -
     * @return devuelve un string con el valor del mensaje
     * @throws -
     */

    public String getMensaje(){

        return(this.getMessage());

    }//END_METODE

    /**
     * metodo que devuelve el nombre del atributo que ha producido
     * la excepción
     *
     * @param -
     * @return devuelve un string con el valor del atributo atributo
     * @throws -
     */

    public String getAtributo(){

        return (this.atributo);

    }//END_METODE

    /**
     * Metodo que devuelve el nombre de la clase que ha producido la excepción
     *
     * @param -
     * @return devuelve un string con el valor del atributo clase.
     * @throws -
     */

    public String getClase(){

        return (this.clase);

    }//END_METODE


}//END_CLASS
