package dominio;



import java.io.Serializable;

/**
 * @author Joan
 * Cluster i Grup 8.2
 * Creat el 14-abr-2010, 16:30
 *
 * Objectiu de la classe: Definir les estadistiques concretes del blackjack d'un usuari
 * Classes Utilitzades: Estadisticas
 */
public class EstadisticasConcretas implements Serializable{

    /** Definicio dels atributs **/

    /**
     * numBJs: Blackjacks que ha fet l'usuari
     */
    int numBJs;

    /**
     * numEmpats: Vegades que s'ha empatat amb el croupier
     */
    int numEmpats;

    /**
     * numSplits: Vegades que s'ha fet split
     */
    int numSplits;

    /**
     * numApostesDoblades: Vegades que s'ha doblat l'aposta
     */
    int numApostesDoblades;

    /**
     * maxNumFitxes: Maxim num de fitxes que s'han tingut a una partida
     */
    int maxNumFitxes;

    /**
     * numSegurosEmprats: numero de seguros utilitzats
     */
    int numSegurosEmprats;
    
    /**
     * a: Achievements asociats a aquest usuari.
     */
    Achievements a = new Achievements();


    /** Definició dels mètodes **/

    /**
     * Descripció: Creadora, posa tots els atributs a zero.
     */
    public EstadisticasConcretas(){

        numBJs = 0;
        numEmpats = 0;
        numSplits = 0;
        numApostesDoblades = 0;
        maxNumFitxes = 0;
        numSegurosEmprats = 0;


    }//END_METODE

    public Achievements getAchievements(){
        
        return a;

    }//END_METODE

    /**
     * Descripció: Retorna el numero de Blackjacks de l'usuari
     * @return Blackjacks fets per l'usuari
     */
    public int getNumBJs(){

        return numBJs;

    }//END_METODE

    /**
     * Descripció: Posa com a numBJs, el parametre que entra.
     * @param1: nBJs numero de blackjacks que se li asignaran a l'usuari.
     */
    public void setNumBJs(int nBJs){

        numBJs = nBJs;

    }//END_METODE

    /**
     * Descripció: Retorna el numero de empats de l'usuari
     * @return empats fets per l'usuari
     */
    public int getNumEmpats(){

        return numEmpats;

    }//END_METODE

    /**
     * Descripció: Posa com a numEmpats, el parametre que entra.
     * @param1: nEMp numero de empats que se li asignaran a l'usuari.
     */
    public void setNumEmpats(int nEMp){

        numEmpats = nEMp;

    }//END_METODE

    /**
     * Descripció: Retorna el numero de splits de l'usuari
     * @return splits fets per l'usuari
     */
    public int getNumSplits(){

        return numSplits;

    }//END_METODE

    /**
     * Descripció: Posa com a numSplits, el parametre que entra.
     * @param1: numSP numero de splits que se li asignaran a l'usuari.
     */
    public void setNumSplits(int numSp){

        numSplits = numSp;

    }//END_METODE

    /**
     * Descripció: Retorna el numero de apostes doblades de l'usuari
     * @return apostes doblades per l'usuari
     */
    public int getNumApostesDoblades(){

        return numApostesDoblades;

    }//END_METODE

    /**
     * Descripció: Posa com a numApostesDoblades, el parametre que entra.
     * @param1: ap numero de apostes doblades que se li asignaran a l'usuari.
     */
    public void setNumApostesDoblades(int ap){

        numApostesDoblades = ap;

    }//END_METODE

    /**
     * Descripció: Retorna el maxim numero de fitxes de l'usuari
     * @return maxim numero de fitxes de l'usuari
     */
    public int getMaxNumFitxes(){

        return maxNumFitxes;

    }//END_METODE

    /**
     * Descripció: Posa com a maxNumFitxes, el parametre que entra.
     * @param1: nF numero maxim de fitxes que se li asignaran a l'usuari.
     */
    public void setMaxNumFitxes(int nF){

        maxNumFitxes = nF;

    }//END_METODE

    /**
     * Descripció: Retorna el numero de seguros emprats per l'usuari
     * @return numero de seguros emprats per l'usuari
     */
    public int getNumSegurosEmprats(){

        return numSegurosEmprats;

    }//END_METODE

    /**
     * Descripció: Posa com a numSegurosEmprats, el parametre que entra.
     * @param1: nSe numero de seguros emprats que se li asignaran a l'usuari.
     */
    public void setNumSegurosEmprats(int nSe){

        numSegurosEmprats = nSe;

    }//END_METODE

}//END_CLASS
