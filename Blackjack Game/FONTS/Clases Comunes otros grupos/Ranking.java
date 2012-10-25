package dominio;

/**
*
* @author Vincenzo Sellitti
* Cluster y Grupo: C8G1
* Creado el 17 de abril de 2010, 18:15
*
* Objetivo de la clase: gestiona el ranking de un jugador
*
* Clases utilizadas:
*
*/

import java.io.Serializable;


public class Ranking implements Serializable{
//Definicion de los parametros

    //El login de usuario
    private String login;

    //La puntuacion conseguida en el ranking
    private int mediana;


     /**
      * Constructor vacio
      * @param -
      * @return  -
      * @throws -
      */
    public Ranking(){ 
        
    }//END_CONSTRUCTOR

    /**
      * Constructor
      * @param - s:login del jugador; m:mediana del jugador;
      * @return  -
      * @throws -
      */
    public Ranking(String s,int m){
        this.login=s;
        this.mediana=m;
    }//END_CONSTRUCTOR
     /**
      * Devuelve el login de un jugador
      * @param -
      * @return  - login
      * @throws -
      */
    public String getLogin(){
        return login;
    }//END_METHOD

     /**
      * Devuelve la mediana de un jugador
      * @param -
      * @return  - mediana
      * @throws -
      */
    public int getMediana(){
        return mediana;
    }//END_METHOD

     /**
      * Modifica el login de un jugador
      * @param - s:Nuevo login
      * @return  - 
      * @throws -
      */
    public void setLogin(String s){
        login=s;
    }//END_METHOD

     /**
      * Modifica la mediana de un jugador
      * @param - punt:nueva mediana
      * @return  -
      * @throws -
      */
    public void setMediana(int punt){
        mediana=punt;
    }//END_METHOD

}//END_CLASS
