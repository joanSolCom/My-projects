
package dominio;
import ie.IEngine;
import ie.IEPair;
import ie.IEValue;


import java.io.Serializable;

/**
 * @author Victor
 * Cluster y Grupo 8.2
 * Creado el 18-maig-2010
 *
 * Objetivo de la clase: Tener un motor de inferencia especifico
 * para el caso del blackjack
 *
 * Usa clase: -.
 */
public class IEBJ extends IEngine implements Serializable {

    String Es;
    String nomEstrategia;

   /*Creadora*/

   public IEBJ(String s) throws Exception {
    super(s);
    this.Es = s;

  }//END_METODE

   /*Pone como nombre de estrategia el string que se le pasa*/
   public void setEstrategia(String s){

       this.nomEstrategia = s;
   }//END_METODE


  /**
    * Descripció: Funcion para pasar los parametros al motor de inferencia. Se usa
    * en las estrategias facil y medio
    * @param1: pt: Tu puntuacion
    * @param2: pc: puntuacion del croupier
    * @return: Un entero que nos indica, si es 0 que pide carta, si es 1 que doble
    * la apuesta y si es 2 que se plante
   */

  public int elegir(int pt, int pc) {
    IEValue v = new IEValue(TC.ti);
    IEValue v2 = new IEValue(TC.ti);
    v.i = pt;
    v2.i = pc;
    IEPair[] k = new IEPair[2];
    k[0] = new IEPair("pTeva", v);
    k[1] = new IEPair("pCroup", v2);

    setKnowledge(k);

    try {

      return resolution("elegir").i;
    }//END_TRY
    catch (Exception e) {
      e.printStackTrace();
    }//END_CATCH
    return 2;
  }//END_METODE

   /**
    * Descripció: Funcion para pasar los parametros al motor de inferencia. Se usa
    * en las estrategia dificil, o el "cuentacartas". Basicamente calcula la media
    * de puntuacion de las cartas que no han salido, y se lo pasa al motor de inferencia.
    * @param1: pt: La puntuacion que te falta para llegar a 21
    * @param2: Descarte: Vector con todas las cartas que han aparecido
    * @return: Un entero que nos indica, si es 0 que pide carta, si es 1 que doble
    * la apuesta y si es 2 que se plante
    */
  public int elegirHard(int pt, int Descarte[]) {

    int cartastotales = 0;
    int i;
    int Descarte2 [] = new int[10];
    Descarte2 = Descarte.clone();

    for(i=0;i<10;i++) {
        cartastotales = cartastotales + Descarte2[i];

    }//END_FOR
    cartastotales = 312 - cartastotales;
    


    int ii;
    for(ii = 0; ii < 9; ii++){
        Descarte2[ii] = 24 - Descarte2[ii];
    }//END_FOR

    Descarte2[ii] = 96 - Descarte2[ii];

    int media = 0;
    int x=0;
    while(x<10){

        media += Descarte2[x] * (x+1);
        x++;
    }//END_WHILE

    media = media / cartastotales;
    IEValue v = new IEValue(TC.ti);
    IEValue v2 = new IEValue(TC.ti);
    v.i = 21 - pt;
    v2.i = media;
    IEPair[] k = new IEPair[2];
    k[0] = new IEPair("pNeeded", v);
    k[1] = new IEPair("media", v2);

    setKnowledge(k);

    try {
      return resolution("elegir").i;
    }//END_TRY
    catch (Exception e) {
      e.printStackTrace();
    }//END_CATCH
    return 2;
  }//END_METODE


}//END_CLASS

