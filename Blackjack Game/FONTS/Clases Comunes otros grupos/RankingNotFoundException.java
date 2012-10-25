package dominio;

/**
*
* @author Vincenzo Sellitti
* Cluster y Grupo: C8G1
* Creado el 30 de abril de 2010, 20:30
*
* Objetivo de la clase: gestionar una exception cuando se ententa de cargar un Ranking inexistente
* Clases utilizadas: Exception
*
*/

public class RankingNotFoundException extends Exception{

    /**
      * Constructor 
      * @param -
      * @return  -
      * @throws -
      */
    public RankingNotFoundException(){
        System.err.println("No hay ningun ranking guardado.");
        
    }//END_METHOD

}//END CLASS
