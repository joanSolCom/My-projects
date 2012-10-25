package dominio;




/**
 * @author Joan
 * Cluster i Grup 8.2
 * Creat el 14-abr-2010, 18:20
 *
 * Objectiu de la classe: Crear una excepció
 * Classes Utilitzades: Exception
 */
public class CaracteresInvalidos extends Exception{

    /**
     * Descripció: Creadora
     */
    public CaracteresInvalidos(){

        System.err.println("Detectados Caracteres no validos");

    }//END_METODE

    /**
     * Descripció: Creadora
     */
    public CaracteresInvalidos(String msg){

        super(msg);

    }//END_METODE
       
}//END_CLASS





