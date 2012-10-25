
package dominio;
import java.io.*;
/**
 * @author Guillem
 * Cluster i Grup 8.2
 * Creat el 18/04/10 i 12:00
 *
 * Objectiu de la classe: Gestionar les operacions propies de una partida de blackjack
 *
 * Classes Utilitzades: Partida,Jugador,JugadorBJ,Croupier
 *
 */
public class PartidaBJ extends Partida implements Serializable {

    Croupier croupier;
    JugadorBJ jugadores[];
    int apostaMinima;
    int apostaMaxima;
    int Descarte[] = new int[10];

   /**
    * Descripció: Creadora.
    */
    public PartidaBJ(){

    }//END_METODE

   /**
    * Descripció: Creadora.
    */
    public PartidaBJ(int n, int num_mazos, boolean comodin){

        super(n,num_mazos, comodin);

        croupier = new Croupier();


    }//END_METODE

   /**
    * Descripció: Retorna el jugador i.
    * @param i: identificador del jugador a retornar
    * @return el jugador
    */
    public Jugador VerJugador (int i){

        if(jugadores[i]!= null){

            return jugadores[i];

        }//END_IF

        else{

            return null;
            
        }//END_ELSE

    }//END_METODE

   /**
    * Descripció: Afegeix el jugador j a la partida
    * @param j: jugador a insertar
    * @param i: posició on insertar-lo
    */
   public void AddJugador2(JugadorBJ j, int i){

       jugadores[i]=j;

   }//END_METODE

   /**
    * Descripció: Retorna la aposta minima
    * @return valor de la aposta minima
    */
   public int getApostaMinima(){

       return this.apostaMinima;

   }//END_METODE

   /**
    * Descripció: posa ap com a aposta minima
    * @param ap: valor de l'aposta minima
    */
   public void setApostaMinima(int ap){

       this.apostaMinima = ap;

   }//END_METODE

   /**
    * Descripció: Retorna la aposta maxima
    * @return valor de la aposta maxima
    */
   public int getApostaMaxima(){

       return this.apostaMaxima;

   }//END_METODE

   /**
    * Descripció: posa ap com a aposta maxima
    * @param ap: valor de l'aposta maxima
    */
   public void setApostaMaxima(int ap){

       this.apostaMaxima = ap;

   }//END_METODE

    @Override
    public void CalcularSiguienteJugador()
    {
        int siguiente = this.jugador_actual + 1;
        if(siguiente == num_jugadores){
            siguiente = 1;
        }
        int cont = 1;
        while(this.jugadores[siguiente].indagame != true){

            
            if (jugador_actual == num_jugadores-1){
                jugador_actual=1;
                siguiente = jugador_actual;
                if(this.jugadores[siguiente].indagame != true){
                    this.jugadores[siguiente].start(0);
                }
            }
            else{
                jugador_actual++;
                siguiente = jugador_actual;
                if(this.jugadores[siguiente].indagame != true){
                    this.jugadores[siguiente].start(0);
                }
            }//END_IF
            cont++;
            if(cont == num_jugadores){
                break;
            }
        }
        if(siguiente == num_jugadores){
            siguiente = 1;
        }
        if(this.jugadores[siguiente].indagame == true){
            jugador_actual = siguiente;

        }
    }//END_METODE

    public void calcularSiguiente(){

        int cont = 1;

        while(this.jugadores[this.jugador_actual].indagame != true){

            if (jugador_actual == num_jugadores-1){
                jugador_actual=1;

            }
            else{
                jugador_actual++;

            }//END_IF
            cont++;
            if(cont == num_jugadores){
                break;
            }
        }
    }

    

}//END_CLASS
