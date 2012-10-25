/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dominio;
import java.io.*;
/**
*
* @author Laura Rivera Sanchez
* Cluster i Grupo: C8G1
* Creado el 14 de abril de 2010, 17:30
*
* Objetivo de la clase: Controlar las funcionalidades de la partida.
* Clases utilizadas: Jugador, Mazo.
*/

public abstract class Partida implements Serializable{

/** Definición atributos */

    /**
     * Jugadores que participan en la partida.
     */
   
   Jugador jugadores[];
    /**
     * Contiene las cartas que se utilizan en el juego.
     */
    Mazo baraja;
    /**
     * Indica la posición en el vector de el jugador actual.
     */
   int jugador_actual;
    /**
     * Contiene el numero total de jugadores en la partida.
     */
   int num_jugadores;

    /** Definición de los métodos */

  /**
      * Constructor vacío
      * @param -
      * @param -
      * @return  -
      * @throws -
      */
   public Partida ()
   {
   }

      /**
      * Crea una partida nueva con n jugadores, tantos mazos como num_mazos con o sin comodines.
      * @param n indica el numero de jugadores.
      * @param num_mazos indica cuantos mazos necesita la partida.
      * @param comodin indica si se incluyen (true) o no (false) comodines.
      * @return  -
      * @throws -
      */
    public Partida(int n, int num_mazos, boolean comodin)
    {
        this.setNumJugadores(n);
        this.jugador_actual=1;
        jugadores = new Jugador[n];
        this.InicializarMazo(num_mazos,comodin);
    }//END_METODE

   /**
      * Devuelve la información de el jugador que se encuentra en la posición i del vector de jugadores.
      * @param -
      * @return  el Jugador que se encuentra en la posición i del vector.
      * @throws -
      */
    public abstract Jugador VerJugador (int i)throws PartidaException;
    //END_METODE

      /**
      * Modifica el valor del atributo Jugador_actual por el numéro que corresponde al consecutivo.
      * @param -
      * @return  -
      * @throws -
      */
    public void CalcularSiguienteJugador()
    {
        if (jugador_actual == num_jugadores-1){
            jugador_actual=0;
        }
        else{
            jugador_actual++;
        }//END_IF

    }//END_METODE

      /**
      * Devuelve el entero que corresponde a la posición de el jugador actual.
      * @param -
      * @return  Devuelve el valor int que corresponde al jugador actual.
      * @throws -
      */
    public int getJugadorActual()
    {
        return jugador_actual;

    }//END_METODE


    /**
      * Devuelve el entero que corresponde al numero de jugadores.
      * @param -
      * @return  Devuelve el valor int que corresponde al numero de jugadores.
      * @throws -
      */
    public int getNumJugadores()
    {
        return this.num_jugadores;

    }//END_METODE

     /**
      * Modifica el valor de el numero de jugadores.
      * @param -
      * @return  -
      * @throws -
      */
    public void setNumJugadores(int n)
    {
        this.num_jugadores=n;

    }//END_METODE

     /**
      * Añade un jugador a la partida.
      * @param -
      * @return  -
      * @throws -
      */
    public void AddJugador(Jugador j, int i)throws PartidaException
    {
        if (i<0 || i > this.num_jugadores-1)
        {
            throw new PartidaException("Indice fuera de rango", "i integer", "Partida");
        }
        else
        {
            jugadores[i]=j;
        }//END_IF

    }//END_METODE


      /**
      * Crea el mazo necesario para la partida (sin mezclar).
      * @param num_mazos Contiene el numero de mazos que se necesitan
      * @param comodin Dice si se necesitan (true) o no (false) los comodines.
      * @return  -
      * @throws -
      */
    public void InicializarMazo(int num_mazos, boolean comodin)
    {
       this.baraja= new Mazo(num_mazos, comodin);

    }//END_METHOD


     /**
      * Baraja el mazo de la partida.
      * @param -
      * @return  -
      * @throws -
      */
    public void BarajarMazo ()
    {
        //this.baraja=this.baraja.barajar();

    }//END_METODE

}//END_CLASS





