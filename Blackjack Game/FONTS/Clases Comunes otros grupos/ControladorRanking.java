package dominio;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;


/**
*
* @author Vincenzo Sellitti
* Cluster y Grupo: C8G1
* Creado el 18 de abril de 2010, 10:00
*
* Objetivo de la clase: gestiona la clasificacion de los 10 mejores jugadores
*
* Clases utilizadas: Ranking,GestorRanking
*
*/

public class ControladorRanking implements Serializable{
//Definicion de los atributos

    //El vector que contiene la clasificacion
    Ranking [] clasificacion;
    //Un objecte GestorRanking
    GestorRanking gest=new GestorRanking();
    
    /**
      * Constructor que inicializa el parametro de la clase
      * @param -
      * @return  -
      * @throws -
      */
    public ControladorRanking(){
    clasificacion = new Ranking[10];
    for(int i=0;i<10;i++){
        clasificacion[i]=new Ranking();
        clasificacion[i].setLogin(" ");
        clasificacion[i].setMediana(0);
        }//END_FOR
    }//END_CONSTRUCTOR


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
                temp=(clasificacion[i].getMediana()) + mediana;
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

    /**
      * Ordena las puntuaciones en el ranking de mayor a menor
      * @param -
      * @return  -
      * @throws -
      */

    public void ordenarRanking(){
         for(int i = 0; i < clasificacion.length; i++) {
            boolean flag = false;
            for(int j = 0; j < clasificacion.length-1; j++) {

                /*Si el elemento de la posicion j+1 es mayor que el anterior se intercambian
                los valores de esas posiciones*/
                if((clasificacion[j].getMediana())<(clasificacion[j+1].getMediana())) {
                    Ranking k = new Ranking();
                    k=clasificacion[j];
                    clasificacion[j] = clasificacion[i];
                    clasificacion[i] = k;
                    flag=true; //Se pone a true para indicar que se ha hecho un cambio
                    }//END_IF
            }//END_FOR

            if(!flag) break; //Si flag es igual a falso quiere decir que en la ultima iteracion
                             //no hubo ningun intercambio y por lo tanto el metodo puede terminar
                             //ya que el vector esta ordenado
        }//END_FOR

    }//END_METHOD
   
    /**
      * Vacia el vector de la clasificacion
      * @param -
      * @return  - la clasificacion de los jugadores vacia
      * @throws -
      */
    public Ranking[] vaciarRanking()
    {
        for(int i=0;i<10;i++){
            clasificacion[i].setLogin(" ");
            clasificacion[i].setMediana(0);
        }//END_FOR
    return clasificacion;
    }//END_METHOD

    /**
      * Devuelve la clasificacion de los 10 mejores jugadores
      * @param -
      * @return  - clasificacion.
      * @throws -
      */

    public Ranking[] mostrarRanking(){
        return clasificacion;
    }//END_METHOD


    /**
      * Guarda la clasificacion actualizada
      * @param -
      * @return  -
      * @throws - FileNotFoundException,IOException
      */

    public void guardarRanking() throws FileNotFoundException, IOException{
        gest.guardarRanking(clasificacion);
    }//END_METHOD


    /**
      * Carga el ultimo ranking guardado y actualiza el atributo clasificacion
      * @param -
      * @return  -
      * @throws - ClassNotFoundException, FileNotFoundException , IOException ,RankingNotFoundException
      */

    public void cargarRanking() throws ClassNotFoundException, FileNotFoundException, IOException, RankingNotFoundException {
        
        try{
            clasificacion=gest.cargarRanking();
        }//END_TRY
        catch (RankingNotFoundException e){
           
        }//END_CATCH

    }//END_METHOD
     
}//END_CLASS
