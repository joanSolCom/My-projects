
package dominio;
import java.io.Serializable;

/**
 *
 * @author Victor
 *
 * Cluster y Grupo: 8.2
 *
 * Creado el 27 de Mayo de 2010, 20:15
 *
 * Descripcion: Modificacion del controlador Ranking, para adecuarlo
 * a nuestro sistema de puntuacion
 *
 */
public class ControladorRankingE extends ControladorRanking implements Serializable{

    public ControladorRankingE(){
        super();
    }//END_METODE

    @Override

    /**
      * Actualiza la clasificacion de los 10 mejores jugadores
      * @param - login: login del jugador
      * @param - mediana: puntuacion del jugador
      * @return -
      * @throws -
      */
    public void actualizarRanking(String login, int mediana){

        int temp=0;
        for(int i=0;i<10;i++){
            if((clasificacion[i].getLogin()).equals(login)){ //si el jugador ya esta en los 10 mejores jugadores actualizo su punctuacion
                temp = mediana;
                clasificacion[i].setMediana(temp);
                ordenarRanking(); //ordeno el ranking con la puntuacion del jugador actualizada
                return;
            }//END_IF
            
        }//END_FOR

           
        for(int j=0;j<10;j++){

            if((clasificacion[j].getMediana()) < mediana){//si existe en el ranking una puntuacion menor de la que se passa a la funcion por parametro
                System.arraycopy(clasificacion, j, clasificacion, j+1, clasificacion.length-j-1);//reordena las puntuaciones menores que mediana
                Ranking aux=new Ranking();
                aux.setLogin(login);
                aux.setMediana(mediana);
                clasificacion[j]=aux;
                return;
                }//END_IF
        }//END_FOR

    }//END_METHOD

}//END_CLASS
