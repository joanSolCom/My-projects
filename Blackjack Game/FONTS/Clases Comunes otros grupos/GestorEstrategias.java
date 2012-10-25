package dominio;

/**
 * @author Aleix Fernández Donis
 * Cluster y Grupo C8G3
 * Creado el 5-May-2010
 *
 * Objetivo de la clase: interpreta un ast bien tipado y decorado
 */


import java.io.*;


public class GestorEstrategias {

    private final String path = "Estrategias/";
    /**
     * Descripció: Creadora
     */
    public GestorEstrategias(){

    }//END_METODE


    /**
     * Descripció: devuelve true si y sólo si existe el fichero de reglas
     * @param1 nombre: nombre del fichero de reglas
     */
    public boolean existe(String nombre) {

        boolean b = false;
        File s = new File(path);
        if (!s.exists()) {
            return false;
        }

        s = new File(path + nombre);
        if (!s.exists()) {
            return false;
        }
        return true;
    }//END_METODE

    /**
     * Descripció: devuelve uan string con el contenido del fichero de reglas
     * @param1 nombre: nombre del fichero de reglas
     * @FileNotFoundException: no existe el fichero
     * @IOException: fallo al leer el fichero
     */
    public String estrategia(String nombre) throws FileNotFoundException, IOException {
        FileInputStream file = new FileInputStream(path+nombre);       
        byte[] b = new byte[file.available()];
        file.read(b);
        file.close ();
        return new String(b);
    }//END_METODE

}//END_CLASS

