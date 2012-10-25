
package dominio;
import java.io.*;

/**
 * @author Joan
 * Cluster i Grup 8.2
 * Creat el 15-abr-2010, 19:35
 *
 * Objectiu de la classe: Gestionar l'interacció de la capa de domini
 * amb la capa de gestió de dades respecte al sistema de usuaris.
 * Classes Utilitzades: Humano,UsuariExistent,UsuariInexistent,CaracteresInvalidos
 */

public class GestorUsuarios {

    /**
     * Descripció: Creadora
     */
    public GestorUsuarios(){

    }//END_METODE

     /**
      * Descripció: Guarda un objecte jugador, en un fitxer de text que
      * tindrà com a nom, el login del usuario
      * @param1 u: L'objecte usuari
      * @param2 log: login del usuari, per saber quin nom posar al fitxer
      * @throws: UsuariExistent: L'usuari que es vol crear, ja existeix.
      */
    public void guardarJugador(Object u,String log)throws UsuariExistent{

        try{
            File s = new File("UsuarisBlackjack");
            if(!s.exists()){s.mkdir();}
            String p = s.getAbsolutePath();

            File f = new File(p,log);
            if(f.exists())
            {
                throw new UsuariExistent();

            }//END_IF

            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(u);
            out.close();

        }//END_TRY

        catch(FileNotFoundException f){

        }//END_CATCH

        catch(IOException e){
            
        }//END_CATCH
        
    }//END_METODE

    /**
      * Descripció: Actualitza el fitxer de un usuari, això s'emprarà quan
      * s'hagin d'actualitzar les estadistiques del mateix per exemple.
      * @param1 u: L'objecte usuari
      * @param2 log: login del usuari, per saber quin fitxer carregar.
      * @throws: UsuariExistent: L'usuari que es vol crear, ja existeix.
      */
    public void actualizarJugador(Object u,String log)throws UsuariInexistent{

        try{
            File s = new File("UsuarisBlackjack");
            if(!s.exists()){s.mkdir();}
            String p = s.getAbsolutePath();

            File f = new File(p,log);
            if(!f.exists())
            {
                throw new UsuariInexistent();

            }//END_IF

            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(u);
            out.close();

        }//END_TRY

        catch(FileNotFoundException f){

        }//END_CATCH

        catch(IOException e){

        }//END_CATCH

    }//END_METODE

    /**
     * Descripció: Diu si existeix un fitxer de text amb el nom log, en el que
     * un usuari log tengui una contrasenya pass
     * @param1 log: login de l'usuari
     * @param2 pass: password de l'usuari
     * @return b: Booleà que ens diu si es correcte la validació o no
     * @throws: UsuariInexistent: La dupla usuari-contrasenya no existeix al sistema
     */
   public boolean validar(String log, String pass) throws UsuariInexistent{

        boolean b = false;
        File s = new File("UsuarisBlackjack");
            if(!s.exists()){s.mkdir();}
            String p = s.getAbsolutePath();

            File f = new File(p,log);

        if(!f.exists()){

            throw new UsuariInexistent();

        }//END_IF

        try{
        
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream in = new ObjectInputStream(fis);
            Humano o1 = new Humano();
            o1 = (Humano)in.readObject();
            in.close();
            if(o1.getLogin().equals(log)){

                if(o1.getPassword().equals(pass)){

                    b = true;
                

                }//END_IF

            }//END_IF

        }//END_TRY

        catch(FileNotFoundException f2){

        }//END_CATCH

        catch(IOException e){

        }//END_CATCH

        catch(ClassNotFoundException e3){

        }//END_CATCH
        
        return b;

    }//END_METODE

    /**
     * Descripció: Elimina de disc a l'usuari log, amb contrasenya pass
     * @param1 log: login de l'usuari
     * @param2 pass: password de l'usuari
     * @return b: Booleà que ens diu si es correcte l'eliminació o no
     * @throws: UsuariInexistent: La dupla usuari-contrasenya no existeix al sistema
     */
    public boolean eliminar(String log,String pass)throws UsuariInexistent{

        boolean b1 = validar(log,pass);

        if(b1 == false){

            throw new UsuariInexistent();

        }//END_IF

           File s = new File("UsuarisBlackjack");
            if(!s.exists()){s.mkdir();}
            String p = s.getAbsolutePath();

            File f = new File(p,log);
            boolean b = f.delete();
                
            
        return b;

    }//END_METODE

    /**
     * Descripció: Modifica la contrasenya pass, de l'usuari log, canviant-la
     * per pass_n
     * @param1 log: login de l'usuari
     * @param2 pass: password de l'usuari
     * @param3 pass_n: Nova contrasenya
     * @throws: UsuariInexistent: La dupla usuari-contrasenya no existeix al sistema
     * @throws: CaracteresInvalidos: S'han introduit caracters prohibits a la nova
     * contrasenya.
     */
    public void modificar(String log, String pass, String pass_n)throws UsuariInexistent,CaracteresInvalidos{

        boolean b1 = validar(log,pass);

        if(b1 == false)
        {
            throw new UsuariInexistent();

        }//END_IF

        try{
            File s = new File("UsuarisBlackjack");
            if(!s.exists()){s.mkdir();}
            String p = s.getAbsolutePath();

            File f = new File(p,log);
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream in = new ObjectInputStream(fis);
            Humano o1 = new Humano();
            o1 = (Humano)in.readObject();
            o1.setPassword(pass_n);
            in.close();

            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(o1);
            out.close();

        }//END_TRY

        catch(FileNotFoundException f2){

        }//END_CATCH

        catch(IOException e){

        }//END_CATCH

        catch(ClassNotFoundException e3){

        }//END_CATCH
    
    }//END_METODE

    /**
      * Descripcio: carga un usuario
      * @param1 log: login del usuario
      * @param2 pass: password del usuario
      * @throws UsuariInexistent: No existe el usuario
      * @return L'objecte usuari
      */
    public Object carregarUsuari(String log, String pass) throws UsuariInexistent{

        boolean b1 = false;

        try{

             b1= validar(log,pass);

         }//END_TRY

         catch(Exception e){

         }//END_CATCH

         Humano o1= new Humano();

         if(b1 == false){

            throw new UsuariInexistent();

         }//END_IF

         try{

            File s = new File("UsuarisBlackjack");

            if(!s.exists()){

                s.mkdir();

            }//END_IF

            String p = s.getAbsolutePath();

            File f = new File(p,log);
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream in = new ObjectInputStream(fis);
            o1 = (Humano)in.readObject();
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

