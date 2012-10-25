
package dominio;
import java.io.Serializable;

/**
 * @author Joan
 * Cluster i Grup 8.2
 * Creat el 14-abr-2010,11:15
 *
 * Objectiu de la classe: Definir un tipus concret d'usuari, la Maquina
 * Classes Utilitzades: Usuario
 */
public class Maquina extends Usuario implements Serializable{

    /** Definicio dels atributs **/

    String nomEstrategia;
    
    /** Definició dels mètodes **/

    /**
     * Descripció: Creadora
     */
    public Maquina(){

    }//END_METODE

    /**
     * Descripció: posa el parametre com a nom de estrategia
     * @param estr nom de estrategia asignat.
     */
    public void setNomEstrategia(String estr){

        nomEstrategia = estr;

    }//END_METODE

    /**
     * Descripció: retorna el nom de la estrategia
     */
    public String getNomEstrategia(){

        return nomEstrategia;

    }//END_METODE
    
}//END_CLASS
