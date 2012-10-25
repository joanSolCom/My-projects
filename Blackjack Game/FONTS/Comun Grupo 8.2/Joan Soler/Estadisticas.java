package dominio;



import java.io.Serializable;

/**
 * @author Joan
 * Cluster i Grup 8.2
 * Creat el 14-abr-2010,16:01
 *
 * Objectiu de la classe: Definir les estadistiques d'un usuari
 * Classes Utilitzades: EstadisticasConcretas
 */
public class Estadisticas implements Serializable{

    /** Definicio dels atributs **/

    /**
     * partidasGanadas: partides que ha guanyat l'usuari
     */
    int partidasGanadas;

    /**
     * partidasJugadas: partides que ha jugat l'usuari
     */
    int partidasJugadas;

    /**
     * puntuacionMedia: puntuació mitjana de l'usuari
     */
    int puntuacionMedia;

    /**
     * partidasPerdidas: partides que ha perdut usuari
     */
    int partidasPerdidas;

    EstadisticasConcretas estc = new EstadisticasConcretas();

    /** Definició dels mètodes **/

    /**
     * Descripció: Creadora, posa tots els atributs a zero.
     */
    public Estadisticas(){

        partidasGanadas = 0;
        partidasPerdidas = 0;
        partidasJugadas = 0;
        puntuacionMedia = 0;

    }//END_METODE

    public EstadisticasConcretas getEstC(){

        return estc;

    }//END_METODE

    /**
     * Descripció: Retorna numero de partides guanyades de l'usuari
     * @return partides guanyades per l'usuari
     */
    public int getPartidasGanadas(){

        return partidasGanadas;

    }//END_METODE

    /**
     * Descripció: Posa com a partides_ganadas, el parametre que entra.
     * @param1: part_g partides guanyades que se li asignaran a l'usuari.
     */
    public void setPartidasGanadas(int part_g){
        
        partidasGanadas = part_g;

    }//END_METODE

    /**
     * Descripció: Retorna numero de partides jugades de l'usuari
     * @return partides jugades per l'usuari
     */
    public int getPartidasJugadas(){

        return partidasJugadas;

    }//END_METODE

    /**
     * Descripció: Posa com a partides_jugadas, el parametre que entra.
     * @param1: part_j partides jugades que se li asignaran a l'usuari.
     */
    public void setPartidasJugadas(int part_j){

        partidasJugadas = part_j;

    }//END_METODE

    /**
     * Descripció: Retorna la puntuacio mitjana de l'usuari
     * @return puntuacio mitjana de l'usuari
     */
    public int getPuntuacionMedia(){

        return puntuacionMedia;

    }//END_METODE

    /**
     * Descripció: Posa com a puntuacionMedia, el parametre que entra.
     * @param1: punt_m puntuacio mitjana que se li asignaran a l'usuari.
     */
    public void setPuntuacionMedia(int punt_m){

        puntuacionMedia = punt_m;

    }//END_METODE

    /**
     * Descripció: Retorna numero de partides perdudes de l'usuari
     * @return partides perdudes per l'usuari
     */
    public int getPartidasPerdidas(){

        return partidasPerdidas;

    }//END_METODE

    /**
     * Descripció: Posa com a partides_perdidas, el parametre que entra.
     * @param1: part_p partides perdudes que se li asignaran a l'usuari.
     */
    public void setPartidasPerdidas(int part_p){

        partidasPerdidas = part_p;

    }//END_METODE

}//END_CLASS
