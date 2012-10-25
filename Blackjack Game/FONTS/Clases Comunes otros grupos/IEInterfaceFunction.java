package ie;

/**
 * @author Aleix Fernández Donis
 * Cluster y Grupo C8G3
 * Creado el 5-May-2010
 *
 * Objetivo de la clase: Permitir añadir primitivas
 */


/*
 * Interface para que el intérprete pueda llamar a las funciones primitivas
 * Se puede extender el motor, mediante herencia, y añadir nuevas funciones
 * primitivas creando clases concretas de IEAF que implementa IEF.
 * Se ha hecho una interface para que la solución fuera más extensible.
 */





interface IEInterfaceFunction {
  IEValue app(int n, IEValue[] args) throws IEError;
}
