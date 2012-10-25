
package dominio;
import java.io.*;


/**
 *
 * @author Guillem
 *
 * Cluster y Grupo: 8.2
 *
 * Creado el 27 de Mayo de 2010, 20:15
 *
 * Descripcion: Modificacion del Gestor de Usuario para poder
 * cargar usuarios de tipo maquina correctamente
 *
 */

public class GestorUsuariosC extends GestorUsuarios implements Serializable{


    public GestorUsuariosC(){

    }//END_METODE


    /**
      * Descripcio: carga un usuario de tipo maquina, esta funcion
      * es necesaria para saltarse el proceso de validacion, cosa que no
      * se necesita hacer en caso de maquina, ya que eso se gestiona desde
      * el programa
      * @param1 log: login del usuario
      * @throws UsuariInexistent: No existe el usuario
      * @return El objeto maquina
      */
    public Object carregarMaquina(String log) throws UsuariInexistent{

         Maquina o1= new Maquina();

         try{

            File s = new File("UsuarisBlackjack");

            if(!s.exists()){

                s.mkdir();

            }//END_IF

            String p = s.getAbsolutePath();

            File f = new File(p,log);
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream in = new ObjectInputStream(fis);
            o1 = (Maquina)in.readObject();
            in.close();

        }//END_TRY

        catch(FileNotFoundException f2){

        }//END_CATCH

        catch(IOException e){

        }//END_CATCH

        catch(ClassNotFoundException e3){

        }//END_CATCH

        return o1;

    }//END_METODE



}//END_CLASS
