/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dominio;
import java.io.*;
/**
 *
 * @author Administrador
 */
public  class GestorDiscoPartida {

/**
 * Guarda en un archivo un objeto serializado.
 * @param Partida contiene la partida que queremos guardar
 * @param nombre Contiene el nombre que le queremos dar ala partida a guardar.

 * @throws DiscoException   Error al Guardar el objeto.
 */
    public static void guardarPartida(Partida u,String nombre)throws DiscoException{

        try{

 

            File s = new File("Partidas");
            if(!s.exists()){s.mkdir();}          
            String p = s.getAbsolutePath();    

            File f = new File(p,nombre);                 
            if(f.exists())
            {
                throw new DiscoException("Error:Ya existe un fichero con ese nombre", "file", "Disco");

            }//END_IF

            FileOutputStream fos = new FileOutputStream(f);            
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(u);                                                         
            out.close();

        }//END_TRY

        catch(FileNotFoundException f){
            throw new DiscoException("Error: No se ha encontrado el archivo.", "file", "Disco");
        }//END_CATCH

        catch(IOException e){
            throw new DiscoException("Error al Guardar el objeto.", "file", "Disco");
        }//END_CATCH

    }//END_METODE

    /**
 * Carga una partida desde un fichero concreto.
 * @param nombre contiene el nombre de la partida
 * @return Devuelve la partida
 */
    public static Partida CargarPartida(String nombre) throws DiscoException{

        boolean b1 = false;
        File f= null;
        Partida o = new PartidaBJ();
         try{

            File s = new File("Partidas");

            if(!s.exists()){

                s.mkdir();

            }//END_IF
            else
            {

            String p = s.getAbsolutePath();

            f = new File(p,nombre);
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream in = new ObjectInputStream(fis);
            o = (Partida)in.readObject();
            System.out.println(o.num_jugadores);
            in.close();
            }
        }//END_TRY

        catch(FileNotFoundException f2){
            throw new DiscoException("Error: No se ha encontrado el archivo.", "file", "Disco");
        }//END_CATCH

        catch(IOException e){
            throw new DiscoException("Error al Cargar el objeto.", "file", "Disco");
        }//END_CATCH

        catch(ClassNotFoundException e3){

        }//END_CATCH
        BorrarFichero( f.getAbsolutePath());
        return o;

    }//END_METODE

    /**
 * Borra el archivo que corresponde a la ruta.
 * @param location Contiene la ruta del fichero a borrar
 * @return Devuelve si se a borrado correctamente (true) o no (false)
 * @throws DiscoException No existe el fichero que desea borrar.  No se puede accedecer al fichero.
 */
    public static boolean BorrarFichero(String location)throws DiscoException
     {
         if (location.equalsIgnoreCase(""))
        {
        throw new DiscoException("Ruta vacia", "file", "Disco");
        }//END_IF
         boolean result=false;
         java.io.File file = new java.io.File(location);
         if (!file.exists())
         {
          throw new DiscoException("No existe el fichero que desea borrar.", "file", "Disco");

         }
         else

         {
            try{
                file.delete();
                result= true;
            }//END_TRY
            catch (java.security.AccessControlException e)
            {
                throw new DiscoException("No se puede accedecer al fichero.", "file", "Disco");

            }//END_CATCH


         }//END_IF
         return result;
     }//END_METHOD

   /**
 * Devuelve el nombre de los ficheros que estan en la carpeta de partidas.

 * @return Devuelve un String[] con los nombres.
 * @throws DiscoException 
 */
    public static String[] GetPartidas ()throws DiscoException
    {
        String[] Archivos;

    try{
     File directorio = new File("Partidas");
     Archivos = directorio.list();
    }
    catch(Exception e)
    {
     throw new DiscoException("Error al cargar las partidas ya guardadas", "file", "Disco");

    }
     return Archivos;

    }//END_METHOD
}//END_CLASS
