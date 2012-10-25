/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dominio;
import java.io.*;
/**
 * @author Jaume Rodrigo Roig
 * Cluster y Grupo 8.1
 * Creado el 14-abr-2010
 * 
 * Objetivo de la clase: Definir la estructura y atributos de las cartas de un
 *                       mazo.
 *
 */
public class Carta implements Serializable{

    /** Definición de los atributos */

    // palo: palo del mazo asignado a la carta.
    private String palo;

    // valor: numeracion de la carta dentro del mazo
    private int valor;

    // puntuacion: puntuacion asignada a una carta dentro del mazo.
    private int puntuacion;

    // estado: estado de la carta dentro del mazo.
    private boolean estado;

    /** Definició de los metodos */

    // Creadora: inicializa el estado de la carta.
    public Carta()
    {
       this.estado =  true;
    }

    /**
     * Descripción: Configura la carta con un palo de la baraja.
     * @param p: es un palo de la baraja.
     */
    public void setPalo(String p)
    {
        this.palo = p;
    }
    /**
     * -Precondición: La carta, parámetro implícito, existe.
     * -Poscondición: Modifica el atributo palo con el valor del parametro p.
     */

    /**
     * Descripción: devuelve el valor del palo al cual pertenece esta carta.
     * @return palo que compone esta carta.
     */
    public String getPalo()
    {
         return this.palo;
    }
    /**
     * -Precondición: La carta, parámetro implícito, existe.
     * -Poscondición: devuelve el valor del atributo palo de la carta
     *                seleccionada.
     */

    /**
     * Descripción: guarda el numero de carta en el campo valor.
     * @param v: numero asignado a la carta.
     * @param puntos: puntos a asignar a la carta.
     */
    public void setValor(int v, int puntos)
    {
        this.valor = v;
        this.puntuacion = puntos;
    }
    /**
     * -Precondición: La carta, parámetro implícito, existe.
     * -Poscondición: Modifica el atributo valor con el valor del parametro v
     *                asignando la puntuacion del parametro puntos.
     */

     /**
     * Descripción: devuevle la puntuacion de la carta.
     * @return puntuacion de la carta.
     */
    public int getPuntuacion()
    {
        return this.puntuacion;
    }
    /**
     * -Precondición: La carta, parámetro implícito, existe.
     * -Poscondición: devuelve el valor del atributo puntuacion de la carta
     *                seleccionada.
     */

    /**
     * Descripción: devuevle el numero de carta.
     * @return valor de la carta.
     */
    public int getValor()
    {
        return this.valor;
    }
    /**
     * -Precondición: La carta, parámetro implícito, existe.
     * -Poscondición: devuelve el valor del atributo valor de la carta
     *                seleccionada.
     */


     /**
     * Descripción: devuevle el estado de la carta.
     * @return estado de la carta.
     */
    public boolean getEstado()
    {
        return this.estado;
    }
    /**
     * -Precondición: La carta, parámetro implícito, existe.
     * -Poscondición: devuelve el estado actual de la carta
     *                seleccionada.
     */

    /**
     * Descripción: Cambia el estado de la carta.
     */
    public void cambio_estado()
    {
        if (this.estado)
        {
        this.estado = false;
        }
        else
        {
        this.estado = true;
        }//Fin if
    }
    /**
     * -Precondición: La carta, parámetro implícito, existe.
     * -Poscondición: Se cambia el estado de cierto a falso y al contrario.
     */

}
