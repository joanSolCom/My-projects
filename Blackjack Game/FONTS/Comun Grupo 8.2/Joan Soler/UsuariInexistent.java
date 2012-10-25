package dominio;




/**
 * @author Joan
 * Cluster i Grup 8.2
 * Creat el 14-abr-2010, 18:50
 *
 * Objectiu de la classe: Crear una excepció
 * Classes Utilitzades: Exception
 */

public class UsuariInexistent extends Exception {
    
    /**
     * Descripció: Creadora
     */
    public UsuariInexistent() {
        
    }//END_METODE

    /**
     * Descripció: Creadora
     */
    public UsuariInexistent(String msg) {

        super(msg);

    }//END_METODE

}//END_CLASS


