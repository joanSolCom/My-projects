
package dominio;

import java.util.Vector;
import java.io.*;
/**
 * @author Guillem
 * Cluster i Grup 8.2
 * Creat el 18/04/10 i 12:00
 *
 * Objectiu de la classe: Aquesta classe simula el croupier de la partida, amb la seva mà.
 * 
 * Classes Utilitzades: Mano
 * 
 */
public class Croupier implements Serializable {

    /** Definicio dels atributs **/

    /**
    * seguro: Indica la posibilidad de pedir seguro, es decir el croupier tienes un As como primera carta.
    */
    private boolean seguro;
    /**
    * cartas: Mano del croupier.
    */
    Vector cartas = new Vector();
    /**
    * num_cartas: Numero de cartas que tiene el croupier.
    */
    private int num_cartas;
    /**
    * puntuacion: Indica la suma de los valores de las cartas del croupier.
    */
    private int puntuacion;
    

    /** Definició dels mètodes **/

    /**
    * Descripció: Creadora.
    */
    public Croupier(){

        this.puntuacion = 0;
        this.seguro = false;
        this.num_cartas = 0;
        //as = false;

    }//END_METODE

    /**
    * Descripció: Afegeix una carta al vector cartas ( a la mà).
    * @param1: Li passem la carta que volem afegir.
    * @return: -
    */
    public void afegir_carta(Carta carta){

        if(cartas.isEmpty() && carta.getValor()==1)
        {
            this.seguro = true;

        }//END_IF
        
        this.cartas.addElement(carta);
        this.num_cartas++;

    }//END_METODE

    public void reset(){

        this.seguro = false;
        this.num_cartas = 0;
        this.puntuacion = 0;
        this.cartas.removeAllElements();
    }

     /**
    * Descripció: Fem un get de la variable seguro.
    * @return: seguro.
    */
    public boolean getSeguro(){

        return this.seguro;

    }//END_METODE

     /**
    * Descripció: Calcula el valor de la mano del croupier.
    * @return: puntuacion. Valor de la mano del croupier.
    */
    public int getPuntuacion(){

        return this.puntuacion = calcular_puntuacion(0,0,this.cartas);

    }//END_METODE

    /**
    * Descripció: Calcula el valor de la mano.
    * @param1: Indica a quina posicio del vector Cartas accedim.
    * @param2: Indica la suma parcial del vector Cartas més bona fins el moment.
    * @param3: Cartas a sumar.
    * @return: -
    */
    public int calcular_puntuacion(int i, int valor, Vector cartas){

        int vaux;
        int v1 = 0;
        int v2 = 0;
        Carta caux = new Carta();

        if(i<cartas.size()){
            caux = (Carta) cartas.elementAt(i);
            vaux = caux.getPuntuacion();

            if(vaux == 1){

                v1 = calcular_puntuacion(i+1,valor + 1,cartas);
                v2 = calcular_puntuacion(i+1,valor + 11,cartas);

            }//END_IF
            else{

                caux = (Carta) cartas.elementAt(i);
                vaux = caux.getPuntuacion();
                v1 = calcular_puntuacion(i+1,valor + vaux ,cartas);

            }//END_ELSE

            if(v2>21){
                return v1;

            }
            else{

                if(v2 != 0){
                    return v2;

                }//END_IF
                else{

                    return v1;
                }//END_ELSE
            }//END_ELSE

        }//END_IF
        else{
              return valor;

        }///END_ELSE

    }//END_METODE

}//END_CLASS
