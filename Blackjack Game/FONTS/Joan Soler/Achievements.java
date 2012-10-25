package dominio;



import java.io.Serializable;

/**
 * @author Joan
 * Cluster i Grup 8.2
 * Creat el 14-abr-2010, 16:40
 *
 * Objectiu de la classe: Definir els achievements d'un usuari
 * Classes Utilitzades: EstadisticasConcretas
 */
public class Achievements implements Serializable{

    /** Definicio dels atributs **/


    /**
     * primerBJ: Ha fet l'usuari el seu primer blackjack?
     */
    boolean primerBJ;


    /**
     * PrimeraMaGuanyada: Ha guanyat el usuari la seva primera ma?
     */
    boolean PrimeraMaGuanyada;


    /**
     * empatBJ: Ha empatat amb el croupier a blackjack?
     */
    boolean empatBJ;


    /**
     * QuinzeMansGuanyades: Ha guanyat 15 mans?
     */
    boolean QuinzeMansGuanyades;

    /**
     * QuinzeBJs: Ha tret 15 blackjacks?
     */
    boolean QuinzeBJs;


    /**
     * GuanyarMenysCatorze: Ha guanyat amb menys de 14 de puntuació?
     */
    boolean GuanyarMenysCatorze;


    /**
     * PerdreAmbVint: Ha perdut amb 20 de puntuacio?
     */
    boolean PerdreAmbVint;


    /**
     * DobleSplit: Ha fet un doble split?
     */
    boolean DobleSplit;


    /**
     * AllIn: S'ha apostat totes les fitxes en una ma?
     */
    boolean AllIn;


    /**
     * TotsAchievements: te tots els achievements?
     */
    boolean TotsAchievements;


    /** Definició dels mètodes **/

    /**
     * Descripció: Creadora, posa tots els atributs a fals.
     */
    public Achievements(){

        primerBJ = false;
        PrimeraMaGuanyada = false;
        empatBJ = false;
        QuinzeMansGuanyades = false;
        QuinzeBJs = false;
        GuanyarMenysCatorze = false;
        PerdreAmbVint = false;
        DobleSplit = false;
        AllIn = false;
        TotsAchievements = false;

    }//END_METODE


    public boolean getPrimerBJ(){

        return primerBJ;

    }//END_METODE

    public void setPrimerBJ(){

        primerBJ = true;

    }//END_METODE

    public boolean getPrimeraMaGuanyada(){

        return PrimeraMaGuanyada;

    }//END_METODE

    public void setPrimeraMaGuanyada(){

        PrimeraMaGuanyada = true;

    }//END_METODE

    public boolean getEmpatBJ(){

        return empatBJ;

    }//END_METODE

    public void setEmpatBJ(){

        empatBJ = true;

    }//END_METODE

    public boolean getQuinzeMansGuanyades(){

        return QuinzeMansGuanyades;

    }//END_METODE

    public void setQuinzeMansGuanyades(){

        QuinzeMansGuanyades = true;

    }//END_METODE

    public boolean getQuinzeBJs(){

        return QuinzeBJs;

    }//END_METODE

    public void setQuinzeBJs(){

        QuinzeBJs = true;

    }//END_METODE

    public boolean getGuanyarMenysCatorze(){

        return GuanyarMenysCatorze;

    }//END_METODE

    public void setGuanyarMenysCatorze(){

        GuanyarMenysCatorze = true;

    }//END_METODE

    public boolean getPerdreAmbVint(){

        return PerdreAmbVint;

    }//END_METODE

    public void setPerdreAmbVint(){

        PerdreAmbVint = true;

    }//END_METODE

    public boolean getDobleSplit(){

        return DobleSplit;

    }//END_METODE

    public void setDobleSplit(){

        DobleSplit = true;

    }//END_METODE

    public boolean getAllIn(){

        return AllIn;

    }//END_METODE

    public void setAllIn(){

        AllIn = true;

    }//END_METODE

    public boolean getTotsAchievements(){

        return TotsAchievements;

    }//END_METODE

    public void setTotsAchievements(){

        TotsAchievements = true;

    }//END_METODE
    
}//END_CLASS
