package dominio;



import java.io.Serializable;

/**
 * @author Joan
 * Cluster i Grup 8.2
 * Creat el 14-abr-2010,11:20
 *
 * Objectiu de la classe: Definir un tipus concret d'usuari, l'humà
 * Classes Utilitzades: Usuario, CaracteresInvalidos
 */

public class Humano extends Usuario implements Serializable{

        /** Definicio dels atributs **/

        /**
        * password: Contrasenya del usuari humà
        */
        String password;


        /** Definició dels mètodes **/

        /**
         * Descripció: Creadora
         */
        public Humano(){

        }//END_METODE

       /**
        * Descripció: Retorna el password del usuari humà
        * @return password de l'usuari humà
        */
        public String getPassword(){

            return password;

        }//END_METODE

       /**
        * Descripció: Posa com a password, el parametre que entra.
        * @param1 pass: password que s'assignarà a l'usuari
        * @throws CaracteresInvalidos: S'han introduit caracters no valids al pass.
        * @override setPassword(String pass) de Usuario
        */
        public void setPassword(String pass) throws CaracteresInvalidos{


              comprobar(pass);
              password = pass;

        }//END_METODE

}//END_CLASS
