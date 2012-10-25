package ie;
import java.io.Serializable;

/**
 * @author Aleix Fernández Donis
 * Cluster y Grupo C8G3
 * Creado el 5-May-2010
 *
 * Objetivo de la clase: Permitir añadir primitivas
 */


/**
 * Clase abstracta para que el intérprete pueda llamar a las funciones
 * primitivas.
 * Se puede extender el motor, mediante herencia, y añadir nuevas funciones
 * primitivas creando clases concretas de IEAF.
 */





public abstract class IEAbstractFunction implements IEInterfaceFunction,Serializable {
  public abstract IEValue app(int n, IEValue[] args) throws IEError; 
}
