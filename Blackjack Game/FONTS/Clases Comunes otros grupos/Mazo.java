/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.*;

/**
 * @author Jaume Rodrigo Roig
 * Cluster y Grupo 8.1
 * Creado el 14-abr-2010
 *
 * Objetivo de la clase: crear una lista mazo de 52(+2 si hay comodines)*n
 *                       de cartas, con sus consultoras y operaciones.
 *
 * Usa clase: Carta.
 */
public class Mazo implements Serializable{

    /** Definición de los atributos */

    // vamzo: lista de cartas que forman un mazo.
    List vmazo = new ArrayList();

    // NUM_CARTAS: numero de cartas de un mazo.
    private static final int NUM_CARTAS = 52;

    // puntos: puntos asignados a una carta;
    private int puntos;

    // com: variable que contiene el numero de comodines a añadir a cada mazo.
    private int com = 0;

    // palo: variable que define los tipos de palos de un mazo.
    private String palo;

    // i: variable contador.
    int i;

    // j: variable contador.
    int j;

    // x: variable contador.
    int x;
    /** Definició de los metodos */

    /**
     * Descripción: Creador mazo, asigna el valor y palo a cada carta.
     * @param n: número de mazos a crear.
     * @param comodin: si es necesario crear comodines en el mazo.
     */
    Mazo(int n, boolean comodin)
    {
        if (comodin)
        {
            com = 2;
        }//FIN IF
        for (j = 0; j < n; j++)
        {
            x = 0;

            for (i = 0; i < NUM_CARTAS+com; i++)
            {
                if(x>=13)
                {
                    x = 1;
                }
                else
                {
                    x++;
                }
                Carta c = new Carta();

                puntos = x;
                if (x==11 | x==12 | x==13)//J,Q,K
                {
                    puntos = 10;
                }

                c.setValor(x, puntos);
                if (i+1 < 14)
                {
                    palo = "Picas";
                }
                else if (i+1 < 27)
                {
                    palo = "Corazones";
                }
                else if (i+1 < 40)
                {
                    palo = "Diamantes";
                }
                else if (i+1 < 53)
                {
                    palo = "Treboles";
                }//FIN IF
                else
                {
                    palo = "Comodin";
                    c.setValor(0,0);
                }//FIN IF

                c.setPalo(palo);
                this.vmazo.add(c);
            }//FIN FOR.2
        }//FIN FOR.1
    }//FIN METODO Mazo

    /**
     * Descripción: mezcla las cartas de la lista vmazo aleatoriamente
     *
     */
    public void barajar()
    {
        Collections.shuffle(this.vmazo);

    }//FIN METODO barajar
     /**
     * -Precondición: El mazo, parámetro implícito, existe.
     * -Poscondición: Situia las cartas de forma aletoria y sin duplicar en las
     *                52 posiciones de la lista.
     */

    /**
     * Descripción: coge la primera carta de la lista vmazo, eliminandola del
     *              mismo.
     * @return carta en posicion 0 de la lista.
     */
    public Carta sacar_carta()
    {
        Carta c = new Carta();
        c = (Carta) this.vmazo.get(0);
        this.vmazo.remove(0);
        return c;
    }//FIN METODO sacar_carta
     /**
     * -Precondición: El mazo, parámetro implícito, existe.
     * -Poscondición: Devuelve la primera carta del mazo y la elimina del mismo.
     */

    /**
     * Descripción: Selecciona la carta situada en la posicion deseada y
     *              devuelve su palo.
     * @param i: posicion de la carta escogida en el mazo.
     * @return palo de la carta seleccionada.
     */
    public String consultar_cpalo(int i)
    {
        i--;
        Carta c = new Carta();
        c = (Carta) this.vmazo.get(i);
        return c.getPalo();
    }//FIN METODO consultar_cpalo
     /**
     * -Precondición: El mazo, parámetro implícito, existe.
     * -Poscondición: Devuelve el palo de la carta situada en la posicion i
     *                del mazo, se resta 1 a i para salvar la posicion 0 de la
     *                lista.
     */

     /**
     * Descripción: Selecciona la carta situada en la posicion deseada y
     *              devuelve su puntuacion.
     * @param i: posicion de la carta escogida en el mazo.
     * @return puntuacion de la carta seleccionada.
     */
    public int consultar_cpuntuacion(int i)
    {
        i--;
        Carta c = new Carta();
        c = (Carta) this.vmazo.get(i);
        return c.getPuntuacion();
    }//FIN METODO consultar_cvalor
     /**
     * -Precondición: El mazo, parámetro implícito, existe.
     * -Poscondición: Devuelve la puntuacion de la carta situada en la posicion
     *                i del mazo, se resta 1 a i para salvar la posicion 0 de la
     *                lista.
     */

    /**
     * Descripción: Selecciona la carta situada en la posicion deseada y
     *              devuelve su valor.
     * @param i: posicion de la carta escogida en el mazo.
     * @return valor de la carta seleccionada.
     */
    public int consultar_cvalor(int i)
    {
        i--;
        Carta c = new Carta();
        c = (Carta) this.vmazo.get(i);
        return c.getValor();
    }//FIN METODO consultar_cvalor
     /**
     * -Precondición: El mazo, parámetro implícito, existe.
     * -Poscondición: Devuelve el valor de la carta situada en la posicion i
     *                del mazo, se resta 1 a i para salvar la posicion 0 de la
     *                lista.
     */


    /**
     * Descripción: Selecciona la carta situada en la posicion deseada y la
     *              devuelve.
     * @param i: posicion de la carta escogida en el mazo.
     * @return carta seleccionada.
     */
    public Carta consultar_carta(int i)
    {
        i--;
        Carta c = new Carta();
        c = (Carta) this.vmazo.get(i);
        return c;
    }//FIN METODO consultar_carta
     /**
     * -Precondición: El mazo, parámetro implícito, existe.
     * -Poscondición: Devuelve la carta situada en la posicion i del mazo, se
     *                resta 1 a i para salvar la posicion 0 de la lista.
     */

    /**
     * Descripción: añade una carta al mazo
     * @param c: Carta a añadir al mazo.
     */
    public void introducir_carta(int i,Carta c)
    {
        i--;
        this.vmazo.add(i, c);
    }//FIN METODO introducir_carta
    /**
     * -Precondición: El mazo, parámetro implícito, existe.
     * -Poscondición: Añande la carta c al mazo en la posicion i, se resta 1 a
     *                i para salvar la posicion 0 de la lista.
     */

    /**
     * Descripción: elimina una carta del mazo.
     * @param i: posicion de la lista mazo a eliminar.
     */
    public void eliminar_carta(int i)
    {
        i--;
        this.vmazo.remove(i);
    }//FIN METODO eliminar_carta
    /**
     * -Precondición: El mazo, parámetro implícito, existe.
     * -Poscondición: Elimina la carta situada en la posicion i de la lista, se
     *                resta 1 a i para salvar la posicion 0 de la lista.
     */

    /**
     * Descripción: intercambia la posicion de dos cartas del mazo.
     * @param i: posicion de una carta en el mazo.
     * @param j: posicion de una carta en el mazo.
     */
    public void intercambiar_carta(int i, int j)
    {
        i--;
        j--;
        Carta c1 = new Carta();

        c1 = (Carta) this.vmazo.get(j);
        this.vmazo.set(j,this.vmazo.get(i));
        this.vmazo.set(i,c1);
    }//FIN METODO intercambiar_carta
    /**
     * -Precondición: El mazo, parámetro implícito, existe.
     * -Poscondición: Cambia la carta situada en la poscion i de la lista mazo
     *                por la de la posción j, se resta 1 a i y j para salvar la
     *                posicion 0 de la lista.
     */

    /**
     * Descripción: comprueba si el mazo esta vacio.
     * @return estado del mazo
     */
    public boolean mazo_vacio()
    {
        return this.vmazo.isEmpty();
    }//FIN METODO mazo_vacio
    /**
     * -Precondición: El mazo, parámetro implícito, existe.
     * -Poscondición: Devuelve Cierto en caso que el mazo no contenga cartas
     *                en caso contrario False.
     */
}

