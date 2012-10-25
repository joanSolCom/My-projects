
package dominio;
import java.io.Serializable;

/**
 * @author Joan
 * Cluster i Grup 8.2
 * Creat el 14-abr-2010,11:12
 *
 * Objectiu de la classe: Clase generica i abstracta amb els camps comuns de totes les
 * subclasses.
 * Classes Utilitzades: Estadisticas,CaracteresInvalidos
 * 
 */

public abstract class Usuario implements Serializable {

    /** Definicio dels atributs **/

    /**
     * login: Identificador del usuari
     */
    String login;

    /**
     * est: Estadistiques del usuari
     */
    Estadisticas est = new Estadisticas();


    /** Definició dels mètodes **/
    
   /**
    * Descripció: Creadora
    */
    public Usuario(){

    }

    /**
    * Descripció: Retorna les estadistiques del usuari
    * @return estadistiques del usuari
    */
    public Estadisticas getEst(){

        return est;
        
    }//END_METODE

   /**
    * Descripció: Retorna el login del usuari
    * @return login del usuari
    */
    public String getLogin(){

        return login;

    }//END_METODE

   /**
    * Descripció: Posa com a login, el parametre que entra.
    * @param1 log: login que s'assignarà a l'usuari
    * @throws CaracteresInvalidos: S'han introduit caracters no valids al login.
    */
    public void setLogin(String log) throws CaracteresInvalidos{
    
        comprobar(log);
        login = log;
            
    }//END_METODE

   /**
    * Descripció: Comprueba que no haya caracteres no validos en el String s
    * @param1 s: String que se quiere chequear
    * @throws CaracteresInvalidos: Se han introducido caracteres no validos
    */
    public void comprobar(String s) throws CaracteresInvalidos{

        char c[] = s.toCharArray();
        int length = s.length();
        int contador = 0;

        while(contador<length){

           boolean b1 = es_incorrecte(c[contador]);

           if (b1) {

               throw new CaracteresInvalidos();

           }//END_IF
               contador++;

         }//END_WHILE

    }//END_METODE

   /**
    * Descripció: Comprueba que c, no sea un caracter no valido
    * @param1 c: caracter a chequear
    * @return booleano que indica si es correcto o no.
    */
    public boolean es_incorrecte(char c){

        boolean b = false;

        switch(c){
            
             case '#':b=true;
             case '*': b=true;
             case '!' : b=true;
             case '¡' : b=true;
             case '?' : b=true;
             case '¿' : b=true;
             case '´' : b=true;
             case '@' : b=true;
             case '-' : b=true;
             case ',' : b=true;
             case '$' : b=true;
             case '%' : b=true;
             case '/' : b=true;
             case '|' : b=true;
             case 'º' : b=true;
             case 'ª' : b=true;
             case '~' : b=true;
             case '(' : b=true;
             case ')' : b=true;
             case '·' : b=true;
             case '¬' : b=true;
             case '&' : b=true;
             case '=' : b=true;
             case '+' : b=true;
             case '[' : b=true;
             case ']' : b=true;
             case '{' : b=true;
             case '}' : b=true;
             case ':' : b=true;
             case ';' : b=true;
             case '¨' : b=true;
             case '^' : b=true;
             case '`' : b=true;
             case '_' : b=true;
             case '<' : b=true;
             case '>' : b=true;
             case '€' : b=true;      

        }//END_SWITCH

        return b;

    }//END_METODE

}//END_CLASS
