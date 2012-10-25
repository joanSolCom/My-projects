/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dominio;

import java.util.Vector;
import java.io.*;

/**
 * @author Guillem
 * Cluster i Grup 8.2
 * Creat el 18/04/10 i 12:00
 *
 * Objectiu de la classe:
 *
 * Classes Utilitzades:
 *
 */

public class Mano implements Serializable {

    /** Definicio dels atributs **/

    /**
    * cartas: Mano del juagdor.
    */
    Vector cartas = new Vector();

    /**
    * puntuacion: Indica la suma de los valores de las cartas del croupier.
    */
    private int puntuacion;

    /**
    * num_cartas: Numero de cartas que tiene el croupier.
    */
    private int num_cartas;

    /**
    * apuesta: Apuesta del jugador en esa mano.
    */
    private int apuesta;

    /**
    * id_mano: Identificador de la mano.
    */
    private int id_mano;

    /** Definició dels mètodes **/

    /**
    *Descripció: Creadora. Inicializa algunos atributos a 0.
     */

    public Mano(int id){

        this.puntuacion = 0;
        this.num_cartas = 0;
        this.apuesta = 0;
        this.id_mano = id;
    }

    /**
    * Descripció: Calcula el valor de la mano.
    * @param1: Indica a quina posicio del vector Cartas accedim.
    * @param2: Indica la suma parcial del vector Cartas més bona fins el moment.
    * @param3: Cartas a sumar.
    * @return: -
    */
    public int calcular_puntuacion(int i, int valor, Vector c){

        int vaux;
        int v1 = 0;
        int v2 = 0;
        Carta caux = new Carta();

        if(i<c.size())
        {
            caux = (Carta) c.elementAt(i);
            vaux = caux.getPuntuacion();
            
            if(vaux == 1){

                v1 = calcular_puntuacion(i+1,valor + 1,c);
                v2 = calcular_puntuacion(i+1,valor + 11,c);
                
            }else{
            
                v1 = calcular_puntuacion(i+1,valor + vaux ,c);
                
            }//END_IF

            if(v2>21)
            {
                return v1;

            }else{

                if(v2 != 0)
                {
                    return v2;

                }else{

                    return v1;
                }
            }//END_IF

        }else{
              return valor;

        }///END_IF

    }//END_METODE

    /**
    * Descripció: Devuelve el valor del atributo puntuacion.
    * @return: puntuacion. Valor de la mano del croupier.
    */
    public int getPuntuacion(){

        return this.puntuacion = calcular_puntuacion(0,0,this.cartas);

    }//END_METODE

    /**
    * Descripció: Afegeix una carta al vector cartas ( a la mà).
    * @param1: Li passem la carta que volem afegir a la mà.
    * @return: -
    */
    public int afegir_carta(Carta carta){

        this.cartas.addElement(carta);
        this.num_cartas++;
        return this.puntuacion = calcular_puntuacion(0,0,this.cartas);
        
    }//END_METODE

    /**
    * Descripció: Afegeix una carta al vector cartas ( a la mà).
    * @param1: Li passem la posicio del vector d'on em de treure la carta.
    * @return: -
    */
    public Carta treure_carta(int i){

        Carta carta = (Carta) this.cartas.remove(i);
        this.num_cartas--;
        this.puntuacion = calcular_puntuacion(0,0,this.cartas);
        return carta;

    }//END_METODE

    /**
    * Descripció: Devuelve el valor de apuesta.
    * @return: apuesta. Apuesta del jugador en esa mano.
    */
    public int getApuesta(){

        return this.apuesta;

    }//END_METODE

      /**
    * Descripció:
    * @return:
    */
    public void setApuesta(int ap){

        this.apuesta = ap;

    }//END_METODE

     /**
    * Descripció:
    * @return: 
    */
    public int getIdMano(){

        return this.id_mano;

    }//END_METODE

   
}//END_CLASS
