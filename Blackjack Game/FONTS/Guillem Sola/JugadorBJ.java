
package dominio;

import java.util.Vector;
import java.io.Serializable;

/**
 * @author Guillem
 * Cluster i Grup 8.2
 * Creat el 19/04/10 i 12:00
 *
 * Objectiu de la classe:
 *
 * Classes Utilitzades:
 *
 */
public class JugadorBJ extends Jugador implements Serializable{
    

    /** Definicio dels atributs **/

    /**
     * Usuario: Usuario que juega una partida de BlackJack.
     */
    Usuario usuario;

    /**
     * Manos: Vector que contiene las manos de ese jugador en la partida.
     */
    Vector manos;

    /**
     * chips: Fichas del jugador en la partida.
     */
    int chips;

    /**
     * bet: Apuesta del jugador en la partida (en la mano, o en caso de split en las manos).
     */
    int bet;

    /**
     * balance: Balance de fichas ganadas/perdidad en la partida.
     */
    int balance;

     /**
     * num_manos: Numero de manos que esta jugando el jugador.
     */
    int num_manos;

    boolean seguro;
    boolean indagame;
    IEBJ iebj;

    /** Definició dels mètodes **/

    /**
     * Descripció: Creadora. Inicialitza un JugadorBJ, es a dir els seus atributs, i inciliatiza una mà
     * amb identificador 0 i l'introdueix al vector de mans.
     */
    public JugadorBJ(){

        /* Cridem la constructora generica*/
        super();
        /* Creem un usuari que sera el nostre jugador de BJ */
        //this.usuario = new Humano();
        /* Incialitzem el Vector on guardarem les mans del jugador*/
        manos = new Vector();
        /* Inicialitzem les fitxes del jugador a 1000 */
        this.chips = 1000;
        /* Inicialitzem l'atribut d'aposta a 0 */
        this.bet = 0;
        /* Inicialitzem l'atribut de balance a 0 */
        this.balance = 0;
        /* Inicialitzem l'atribut de num_manos a 0 */
        this.num_manos = 0;

        this.seguro = false;
        this.indagame = true;
        this.iebj = null;

    }//END_METODE

    /**
     * Descripció: Realitza les accions pertinents de quan el jugador fa un split.
     * Es a dir treu una de les cartes (la segona en aquest cas) de la mà. Crea una
     * nova mà on afegeix aquesta carta que em tret. S'afegeix la nova mà al vector de mans.
     * S'actualitzen els atributs respecte a l'aposta.
     * @param1: id_mano és l'identificador de la mà.
     * @return: puntuacio de la nova mà.
     */
   public int splitting(int id_mano){

       /* Conté el tamany del vector manos */
       int size;
       /* Conté la puntuació de la nova mà */
       int nova_punt;
       /* Conté l'aposta de la nova mà (la mateixa que la mà splittada) */
       int aux_apuesta;
       /* Carta que afegirem a la nova ma */
       Carta carta = new Carta();

       /* Agafem la mà que hem de splittar del vector manos en la posicio id_mano*/
       Mano aux_mano = (Mano) manos.elementAt(id_mano);
       /* Treiem la carta de la posicio 1, es a dir la segona carta. Sempre serà la segona
        * ja que l'split solament es pot fer en el moment que tenim una mà amb unicament dos cartes
        * amb el mateix valor, mai després de demanar un altra carta */
       carta = aux_mano.treure_carta(1);
       /* Agafem el valor de l'aposta de la mà splittada, ja que nova mà tindrà la mateixa aposta. */
       aux_apuesta = aux_mano.getApuesta();
       /* Tornem al vector manos la mà splittada en la mateix posicio en que es trobava. */
       manos.setElementAt(aux_mano,id_mano);
       /* Agafem el tamany del vector manos. */
       size = manos.size();
       /* Creem una nova mà amb id_mano el tamany del vector, ja que les posicions del vector es corresponen
        * amb l'identificador de la mà */
       Mano mano = new Mano(size);
       /* Afegim la carta a la nova mà, i guardem la puntuació d'aquesta. */
       nova_punt = mano.afegir_carta(carta);
       /* Posem l'aposta a la nova mà */
       mano.setApuesta(aux_apuesta);
       /* Afegim la nova mà a la última posició del vecctor manos */
       manos.addElement(mano);
       /* Incrementem l' atribut bet amb el valor de la nova aposta */
       this.bet += aux_apuesta;
       /* Decrementem les fitxes del jugador */
       this.chips -= aux_apuesta;
       /* Incrementem el numero de mans actives */
       this.num_manos++;
       /* Retornem la puntuació de la nova mà */
       return size;
       
    }//END_METODE

    /**
     * Descripció: Realitza les accions pertinents de quan el jugador fa un hit.
     * Busca la mà indicada al vector i afegeix la carta que ens passen. Torna a 
     * guradar la mà al vector.
     * @param1: id_mano és l'identificador de la mà.
     * @param2: Carta que ens dóna el croupier i afegirem a la mà.
     * @return: puntuacio de la nova mà.
     */

    public int hitting (int id_mano, Carta carta){

        /* Conté la puntuació després d'afegir la carta */
        int puntuacion;

        /* Agafem la mà id_mano del vector manos */
        Mano maux = (Mano) manos.elementAt(id_mano);
        /* Afegim la carta a la mà, i ens retorna la nova puntuació */
        puntuacion = maux.afegir_carta(carta);
        /* Guardem la mà al vector en la posició en que es trobava */
        manos.setElementAt(maux,id_mano);
        /* Mirem que amb aquesta nova carta no es passi de la puntuació permesa (21) */
        if(puntuacion>21){
            /* La mà a sobrepassat la puntuació permesa, per tant el jugador perd la mà */

            /* Li restem les fitxes apostades al balanç. */
            this.balance -= maux.getApuesta();
            /* Borrem la ma */
            //maux = null;
            /* La introduim al vector per no perdre la posició en el vector de les altres mans */
            this.manos.setElementAt(maux,id_mano);
            
        }//END_IF

        /* Retornem la puntuació*/
        return puntuacion;

    }//END_METODE

    public void setUser(Usuario u)
    {
        this.usuario = u;
    }

    /**
     * Descripció: Realitza les accions pertinents de quan el jugador fa un insurance.
     * Simplement es modifiquen els atributs corresponents a l'aposta.
     * @param1: id_mano és l'identificador de la mà.
     * @param2: bet és l'aposta que es fa pel seguro.
     * @return: -
     */
    public void insurance(int id_mano){

        /* Conté l'aposta de la mà id_mano */
        int apuesta;

        this.seguro=true;
        /* Accedim a la mà id_mano */
        Mano maux = (Mano) manos.elementAt(id_mano);
        /* Agafem l'aposta inicial de la mà */
        apuesta = maux.getApuesta();
        /* Incrementem l'aposta amb el valor de bet */
        maux.setApuesta(apuesta);
        /* Guardem la mà al meteix lloc on es trobava en el vector */
        manos.setElementAt(maux, id_mano);

        this.chips -= apuesta/2;
        /* Incrementem l'atribut this.bet en bet */
        this.bet += bet;

    }//END_METODE

    /**
     * Descripció: Acaba la mà. Es fan els pagament/cobrament de la mà.
     * Això es realitzara per cada mà del jugador en aquella ronda.
     * @param1: id_mano és l'identificador de la mà.
     * @param2: valor de ganancies/perdues de la mà.
     * @return: -
     */
    public void end(int id_mano, int cuenta){

            /* Conté l'aposta de la mà id_mano */
            int apuesta;

            /* Agafem la mà id_mano del vector de mans */
            Mano maux = (Mano) manos.elementAt(id_mano);
            /* Agafem l'aposta de la mà */
            apuesta = maux.getApuesta();

            Mano maux2 = null;

            manos.setElementAt(maux2, id_mano);
            /*  Incrementem les fitxes en el valor de cuenta */
            this.chips = this.chips + cuenta;
            /* Decrementem el valor de l'aposta en el balance */
            this.balance = this.balance - apuesta;
            /* Sumen el valor de cuenta en el balance */
            this.balance = this.balance + cuenta;

    
    }//END_METODE

    /**
     * Descripció: Realitza les accions pertinents de quan el jugador fa un double_down.
     * @param1: id_mano és l'identificador de la mà.
     * @return: -
     */
    public void double_down(int id_mano){

        /* Conté el valor de l'aposta */
        int apuesta;
        
        /* Agafem la mà id_mano del vector manos */
        Mano maux = (Mano) manos.elementAt(id_mano);
        /*  Agafem l'aposta de la mà */
        apuesta = maux.getApuesta();
        /* Doblem el valor de aposta i el tornem a guardar */
        maux.setApuesta(apuesta*2);
        /* Guardem la mà modificada al vector de mans */
        manos.setElementAt(maux, id_mano);
        /* Decrementem les chips */
        this.chips -= apuesta;
        /* Incrementem el valor de bet */
        this.bet += apuesta;

    }//END_METODE

    /**
     * Descripció: Modifica el valor de chips.
     * @param1: c es el valor que tindrà l'atribut chips.
     * @return: -
     */
    public void setChips(int c){

        this.chips = c;
    }//END_METODE

    /**
     * Descripció: Retorna el valor de chips.
     * @return: chips, fitxes del jugador en la partida
     */
    public int getChips(){

        return this.chips;

    }//END_METODE

    /**
     * Descripció: Elimina el contingut del vector de mans.
     */
    public void reset(){

        this.seguro = false;
        manos.removeAllElements();
        this.bet = 0;

    }//END_METODE

    /**
     * Descripció: Inicialitza el camps corresponents per començar una nova mà.
     * @param1: apuesta_incial, es el valor de la apuesta_incial.
     * @return: retonra 0 que sempre serà l'identidicador de la primera mà.
     */
    public int start(int apuesta_inicial){

        /* Crea una nova mà amb id_mano igual a 0 */
        Mano mano = new Mano(0);
        /* Modifiquem el camp de apuesta */
        mano.setApuesta(apuesta_inicial);
        /* Afegim la mà al vector de mans */
        manos.addElement(mano);
        
        this.chips -= apuesta_inicial;
        /* Incrementem el numero de mans actives */
        this.num_manos++;
        /* Retornem el id_mano de la primera mà */
        return 0;
    }//END_METODE

}//END_CLASS
