package dominio;




/**
 * @author Joan
 * Cluster i Grup 8.2
 * Creat el 14-abr-2010, 19:01
 *
 * Objectiu de la classe: Crear una excepció
 * Classes Utilitzades: Exception
 */

public class UsuariExistent extends Exception {

    /**
     * Descripció: Creadora
     */
    public UsuariExistent() {

    }//END_METODE

    /**
     * Descripció: Creadora
     */
    public UsuariExistent(String msg) {

        super(msg);

    }//END_METODE

}//END_CLASS
