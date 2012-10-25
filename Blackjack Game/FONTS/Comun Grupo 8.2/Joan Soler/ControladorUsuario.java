
package dominio;
//import datos.GestorUsuarios;
//import datos.UsuariExistent;
//import datos.UsuariInexistent;

/**
 * @author Joan
 * Cluster i Grup 8.2
 * Creat el 13-abr-2010, 22:05
 *
 * Objectiu de la classe: Permetre donar d'alta, donar de baixa
 * i modificar informació dels usuaris del sistema
 * Classes Utilitzades: Usuario,Maquina,Invitado,Humano,GestorUsuarios
 */
public class ControladorUsuario{

    /** Definició dels mètodes **/

    /**
     * Descripció: Creadora
     */
    public void ControladoUsuario(){
        
    }

    /**
     * Descripció: Dona d'alta un usuari al sistema
     * @param1: log login que se li assignarà a l'usuari, sigui del tipus
     * que sigui.
     * @param2: pass. Password que se li asignarà a l'usuari, que serà de tipus
     * humà, i tendrà el tercer parametre a null.
     * @param3: estrategia nom de l'estrategia asignada al usuari de tipus màquina,
     * que tindrà el param2 a null.
     * @throws: UsuariExistent,CaracteresInvalidos
     */
    

     public void altaUser(String log,String pass,String estrategia) throws CaracteresInvalidos,UsuariExistent{

        GestorUsuarios g = new GestorUsuarios();

        if (estrategia !=null){

            Maquina u = new Maquina();
            u.setLogin(log);
            u.setNomEstrategia(estrategia);
            g.guardarJugador(u,log);

        }//END_IF

        else if(pass != null){

            Humano u1 = new Humano();
            u1.setLogin(log);
            u1.setPassword(pass);
            g.guardarJugador(u1,log);

        }//END_ELSE_IF

        else{

            Invitado u2 = new Invitado();
            u2.setLogin(log);
            g.guardarJugador(u2,log);

        }//END_ELSE

    }//END_METODE



    /**
     * Descripció: Verificarà que log és un usuari del sistema, i que,
     * de tenir-ne, la seva contrasenya es igual a pass
     * @param1: log login que es vol verificar
     * @param2: pass password a verificar.
     * @return: booleà que indica si es correcte o no.
     */
    public boolean validarLogin(String log,String pass)throws UsuariInexistent{
      
        GestorUsuarios g = new GestorUsuarios();
        return g.validar(log,pass);
        
    }//END_METODE

    /**
     * Descripció: Es dona de baixa al usuari identificat per log i pass del sistema
     * @param1: log login del usuari que es vol donar de baixa
     * @param2: pass password del usuari que es vol donar de baixa
     * @throws: UsuariInexistent
     */
    public void bajaUser(String log, String pass)throws UsuariInexistent{

       GestorUsuarios g = new GestorUsuarios();
       g.eliminar(log,pass);

    }//END_METODE

    /**
     * Descripció: Es vol modificar el password del usuari amb login log i password pass
     * @param1: log login del usuari al que volem canviar la contrasenya
     * @param2: pass password de l'usuari al que volem canviar la contrasenya
     * @param3: pass_nuevo nou password
     * @throws: UsuariInexistent.
     */
    public void modificarPass(String log, String pass,String pass_nuevo)throws UsuariInexistent,CaracteresInvalidos{

        GestorUsuarios g = new GestorUsuarios();

        try{

            g.modificar(log,pass,pass_nuevo);

        }//END_TRY

        catch(Exception e){
            
        }//END_CATCH

    }//END_METODE
    
}//END_CLASS
