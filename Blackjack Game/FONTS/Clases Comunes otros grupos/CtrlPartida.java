/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dominio;
//import DiscoException;




/**
*
* @author Laura Rivera Sanchez
* Cluster y Grupo: C8G1
* Creado el 15 de abril de 2010, 8:38
*
* Objetivo de la clase: Controlar las funcionalidades de la partida.
* Clases utilizadas: Partida.
*/
public abstract class CtrlPartida
{
    /** Definición atributos */

    /**
     * Directorio donde se guardaran las partidas.
     */
    String PartidasFolder="";
     /**
     * Objeto partida.
     */
    Partida partida;

     /** Definición de los métodos */

     /**
      * Constructor vacío.
      * @param -
      * @return  -
      * @throws -
      */
    public CtrlPartida()
    {

    }

    public CtrlPartida(Partida p)
    {
        this.partida=p;
    }
     /**
      * Guarda la partida con el formato y en el fichero que corresponda, el cual se llamara como nombrepartida y serà único.
      * @param nombrepartida Nombre de la partida para guardar el fichero.
      * @return  boolean que indica si se ha guardado correctamente.
      * @throws -
      */
    public abstract boolean GuardarPartida (Partida p,String nombrepartida)throws DiscoException;
    

    /**
      * Carga la partida del fichero con el nombre especificado.
      * @param nombrepartida Nombre de la partida para cargar.
      * @return  -
      * @throws -
      */
    public abstract void CargarPartida (String nombrepartida)throws DiscoException;


    /**
      * Destruye la partida actual.
      * @param -
      * @return  -
      * @throws -
      */
    public abstract void AbandonarPartida ();

    /**
     * Inicializa una nueva partida teniendo en cuenta los parametros introducidos.
     * @param num_jugadors Contiene el numero total de jugadores de la partida (maquinas y humanos)
     * @param num_mazos Contiene el numero de mazos que se necesitan
     * @param comodin Dice si se necesitan (true) o no (false) los comodines.
     * @return  -
     * @throws -
     */
    public abstract void InicializarPartida (int num_jugadors, int num_mazos, boolean comodin);


    /**
      * Actualiza las Estadisticas de todos los jugadores de la partida.
      * @param -
      * @return  -
      * @throws -
      */
    public abstract void ActualizarEstadisticas ();

    /**
      * Modifica el String que corresponde a la carpeta donde se guardarán las partidas.
      * @param -
      * @return  -
      * @throws -
      */
    public void setFolder(String f)
    {
    this.PartidasFolder=f;

    }

    /**
      * Devuelve el String que corresponde a la carpeta donde se guardan las partidas.
      * @param -
      * @return  String correspondiente al path de la carpeta.
      * @throws -
      */
    public String getFolder ()
    {
    return this.PartidasFolder;

    }//END_METHOD

/**
      * Devuelve el objeto partida del controlador.
      * @param -
      * @return  Partida actual.
      * @throws -
      */
    public Partida getPartida()
    {
    return this.partida;

    }//END_METHOD

    /**
      * Modifica el objeto partida por el que se le pasa
      * @param Partida p, nueva partida.
      * @return  -
      * @throws -
      */
    public void setPartida(Partida p)
    {
    this.partida=p;

    }//END_METHOD



}//END_CLASS
