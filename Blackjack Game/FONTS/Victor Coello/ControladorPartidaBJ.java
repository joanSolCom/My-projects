
package dominio;
import java.io.*;


/**
 * @author Victor Coello
 * Cluster i Grup 8.2
 * Creat el 18-abr-2010,21:32
 *
 * Objectiu de la classe: Controlar la partida
 * Classes Utilitzades: CtrlPartida,GestorUsuarios
 */

public class ControladorPartidaBJ extends CtrlPartida{

        /** Definicio dels atributs **/

        PartidaBJ p;

        /** Definicio dels metodes **/

        /**
         * Descripcio: Creadora
         */       
        public ControladorPartidaBJ(){

            
        }//END_METHOD

       /**
        * Descripcio: Guarda l'objecte partida donat a disc amb el nom donat.
        * @param1 nombre: nom que s'assignara a la partida guardada.
        * @param2 p: objecte de la partida a guardar.
        * @return un booleano que dice si se ha guardado bien
        */
        public boolean GuardarPartida(Partida p,String nombre){

            GestorDiscoPartida g = new GestorDiscoPartida();
            try{
                g.guardarPartida(p,nombre);
            }
            catch(DiscoException de){

                de.printStackTrace();
                return false;
            }
            
            return true;

        }//END_METHOD

        public String cargaEstrategia(String ipo1){

            InputStream file = (InputStream)((this.getClass().getClassLoader().getResourceAsStream("Estrategias/" + ipo1 + ".txt")));
            String estr = "";
            try{
                  byte[] b231 = new byte[file.available()];
                  file.read(b231);
                  file.close();
                  estr = new String(b231);

              }//END_TRY
              catch(Exception e){
                  e.printStackTrace();
              }//END_CATCH

            return estr;
            
        }//END_METODE

       /**
        * Descripcio: Carrega l'objecte partida amb el nom donat.
        * @param1 nombre: nom de la partida a carregar
        */
        public void CargarPartida(String nombre){

            GestorDiscoPartida g = new GestorDiscoPartida();
            try{
                this.p = (PartidaBJ)g.CargarPartida(nombre);
            }
            catch(DiscoException de){
                de.printStackTrace();
            }

        }//END_METHOD


       /**
        * Descripcio: Crea los usuarios de las maquinas, en caso
        * de no haberse creado ya, asi se guardan sus estadisticas
        *
        */
        public void maquines_default(){

            ControladorUsuario c = new ControladorUsuario();
            Usuario u0 = new Maquina();
            Usuario u1 = new Maquina();
            Usuario u2 = new Maquina();


            File s = new File("UsuarisBlackjack");

            if(!s.exists()){

                s.mkdir();

            }//END_IF
            
            String p2 = s.getAbsolutePath();

            File f = new File(p2,"Facil");
            File f1 = new File(p2,"Medio");
            File f2 = new File(p2,"Dificil");

            if(!f.exists() && !f1.exists() && !f2.exists()){

                try{
                    c.altaUser("Facil", null, "Facil");
                    c.altaUser("Medio", null, "Medio");
                    c.altaUser("Dificil", null, "Dificil");
                }//END_TRY
                catch(Exception ex){
                    ex.printStackTrace();
                }//END_CATCH

            }//END_IF


        }//END_METODE



       /**
        * Descripcio: actualiza las estadisticas
        */
        public void ActualizarEstadisticas() {

            actEst();


        }//END_METHOD


       /**
        * Descripcio: actualiza las estadisticas
        */
        private void actEst(){

            GestorUsuariosC g = new GestorUsuariosC();
            int contador = 1;
            int punt = 0;
            Humano h1 = new Humano();

            while(contador<p.num_jugadores){
                                
                if(p.jugadores[contador].usuario!=null){

                    Humano h2 = (Humano)p.jugadores[contador].usuario;

                    try{

                        h1 = (Humano)g.carregarUsuari(h2.getLogin(),h2.getPassword());
                     
                    }//END_TRY

                    catch(UsuariInexistent e){

                    }//END_CATCH

                    punt = h1.getEst().getPuntuacionMedia();
                    h2.getEst().setPuntuacionMedia((p.jugadores[contador].getChips() - 1000) + punt);

                    try{
                        g.actualizarJugador(h2,h2.getLogin());                   


                    }//END_TRY

                    catch(UsuariInexistent e){

                    }//END_CATCH

                    
                    }

                else if(p.jugadores[contador].iebj != null){

                    Maquina m1 = new Maquina();


                    try{

                        m1 = (Maquina)g.carregarMaquina(p.jugadores[contador].iebj.nomEstrategia);

                    }//END_TRY

                    catch(UsuariInexistent e){

                    }//END_CATCH

                    punt = m1.getEst().getPuntuacionMedia();
                    m1.getEst().setPuntuacionMedia((p.jugadores[contador].getChips() - 1000) + punt);

                    try{
                        g.actualizarJugador(m1,m1.getLogin());

                    }//END_TRY

                    catch(UsuariInexistent e){

                    }//END_CATCH

                }
                contador++;
            }//END_WHILE

        }//END_METHOD

       /**
        * Descripcio: Actualiza las estadisticas y destruye la partida
        */
        public void AbandonarPartida(){

            ActualizarEstadisticas();
            p=null;

        }//END_METHOD

       /**
        * Descripcio: Inicializa una partida
        * @param1 numjug: numero de jugadores
        * @param2 numbarajas: numero de barajas
        * @param3 comodin: indica si se usaran comodines o no
        */
        public void InicializarPartida(int numjug, int numbarajas, boolean comodin){

                p = new PartidaBJ(numjug,numbarajas,comodin);
		int i=1;
                p.jugadores = new JugadorBJ[numjug];

		while(i<numjug){

                        JugadorBJ j = new JugadorBJ();
			//try{
                            p.AddJugador2(j, i);

                      //  }
                       /* catch(PartidaException e){

                        }*/

			i++;

		}//END_WHILE

              
        }//END_METHOD


    /**
      * Descripcio: carga un usuario
      * @param1 log: login del usuario
      * @param2 pass: password del usuario
      * @throws UsuariInexistent: No existe el usuario
      * @return L'objecte usuari
      */
    public Humano carregar_usuari(String log, String pass)throws UsuariInexistent{

        GestorUsuarios g = new GestorUsuarios();
        Humano h = new Humano();
        
        try{

            h = (Humano)g.carregarUsuari(log, pass);

        }//END_TRY

        catch(Exception e){

        }//END_CATCH

        return h;

    }//END_METODE

     /**
      * Descripcio: carga un usuario
      * @param1 log: login del usuario
      * @param2 pass: password del usuario
      * @throws UsuariInexistent: No existe el usuario
      * @return L'objecte usuari
      */
    public Maquina carregarMaquina(String log)throws UsuariInexistent{

        GestorUsuariosC g = new GestorUsuariosC();
        Maquina m = new Maquina();

        try{

            m = (Maquina)g.carregarMaquina(log);

        }//END_TRY

        catch(Exception e){

        }//END_CATCH

        return m;

    }//END_METODE

   /**
    * Descripcio: da las cartas iniciales a jugadores y croupier
    */
    public void cartes_inicials(){

           p.croupier.afegir_carta(p.baraja.sacar_carta());
           int contador = 1;


           while(contador<p.getNumJugadores()){

               //if(p.jugadores[contador].getChips()!=0){

                    p.jugadores[contador].hitting(0, p.baraja.sacar_carta());
                    p.jugadores[contador].hitting(0, p.baraja.sacar_carta());

             //  }//END_IF
               
            contador++;

           }//END_WHILE

    }//END_METHOD

   /**
    * Descripcio: calcula las opciones que tiene un jugador segun la mano que tiene
    * @return un Vector donde por cada mano del jugador actual (puede tener varias
    * debido a los splits), hay una mascara de bits que indica que opciones tiene disponibles
    * de esta manera, los bits tienen este significado X X X X X X El de menos peso significa
    * que tiene la opcion seguro activada. El segundo de menos peso significa que puede doblar
    * el tercero de menos peso indica si se puede hacer split. Los otros dos, estaran activos
    * siempre que no nos hayamos pasado de 21 (todos estaran a cero), i indican si se puede plantarse
    * o pedir carta
    */
    public int calcular_opciones(int manoactual){

        int i = p.getJugadorActual();
        Mano m = new Mano(0);
        Integer valor = 0;
        Carta c2;

            valor = 24;
            m = (Mano)p.jugadores[i].manos.elementAt(manoactual);
            Carta c1 = (Carta)m.cartas.get(0);


            if((m.getPuntuacion()>21)){
                valor = 0;
            }//END_IF
            else if(m.getPuntuacion() == 21){

                valor = 8;
            }

            else{

                if(((m.getPuntuacion() == 9) || (m.getPuntuacion() == 10) || (m.getPuntuacion() == 11)) && m.cartas.size() == 2){

                    valor +=2;

                }//END_IF

                if(m.cartas.size() == 2){

                    c2 = (Carta)m.cartas.get(1);
                    if(c1.getPuntuacion() == c2.getPuntuacion()){

                        valor +=4;

                        }//END_IF
                }


                if(p.croupier.getSeguro()){

                        valor++;

                }//END_IF

            }//END_ELSE


        return valor;

    }//END_METHOD


   /**
    * Descripcio: el croupier juega su turno
    */
    public void jugar_croupier(){

        while(p.croupier.getPuntuacion()<17){

            p.croupier.afegir_carta(p.baraja.sacar_carta());

        }//END_WHILE
        
    }//END_METHOD

   /**
    * Descripcio: Una vez acabada la mano, se paga a los jugadores
    * y se actualizan tanto estadisticas como achievements, ademas
    * de resetear sus manos al final.
    */
    public void finalizar_manos(){

        int i = 1;
        int p_croupier = p.croupier.getPuntuacion();
        int c = 0;
        int mans = 1;
        while(i<p.getNumJugadores()){
            
            
            mans = p.jugadores[i].manos.size();
            

            while(c<mans){

                Mano m = (Mano)p.jugadores[i].manos.get(c);
                if(p.jugadores[i].indagame){
                pagar(m,i,p_croupier,mans);
                }
                
                c++;

            }//END_WHILE
            c=0;

            i++;

        }//END_WHILE

        int j = 1;

        while(j<p.getNumJugadores()){

            p.jugadores[j].reset();
            j++;

        }//END_WHILE


    }//END_METHOD

   /**
    * Descripcio: paga al jugador segun el resultado de su mano
    * y actualiza estadisticas y achievements cuando sea oportuno.
    * @param m: La mano a analizar
    * @param i: El jugador actual
    * @param p_croupier: la puntuacion del croupier
    * @param mans: numero de manos de ese jugador.
    */
    public void pagar(Mano m,int i,int p_croupier,int mans){

        if(p.jugadores[i].usuario!=null){

            if(p.jugadores[i].getChips() == 0){

                p.jugadores[i].usuario.getEst().getEstC().getAchievements().setAllIn();

            }//END_IF
           
        }
        if(p.jugadores[i].usuario!=null){
            if(mans>2){

            p.jugadores[i].usuario.getEst().getEstC().getAchievements().setDobleSplit();

        }//END_IF
        }
        //SEGURO
        if((p.jugadores[i].seguro)&&(p_croupier == 21) ){
            
            p.jugadores[i].end(m.getIdMano(),m.getApuesta()/2);

        }//END_IF

        else if((m.getPuntuacion()==21)&&(p_croupier!=21)){

            //BLACKJACK
            if((mans==1)&&(m.cartas.size() == 2)){

                p.jugadores[i].end(m.getIdMano(), (5 * m.getApuesta())/2);

                    if(p.jugadores[i].usuario!=null){


                        p.jugadores[i].usuario.getEst().getEstC().setNumBJs(p.jugadores[i].usuario.getEst().getEstC().getNumBJs() + 1);

                        p.jugadores[i].usuario.getEst().getEstC().getAchievements().setPrimerBJ();


                    if(p.jugadores[i].usuario.getEst().getEstC().getNumBJs() == 15){

                        p.jugadores[i].usuario.getEst().getEstC().getAchievements().setQuinzeBJs();

                    }//END_IF
                }
            }//END_IF

            //NO ES BLACKJACK, HA FET SPLIT O SUMA 21 AMB MES DE 2 CARTES
            else{

                p.jugadores[i].end(m.getIdMano(), 2 * m.getApuesta());

            }//END_ELSE
        if(p.jugadores[i].usuario!=null){
            p.jugadores[i].usuario.getEst().setPartidasGanadas(p.jugadores[i].usuario.getEst().getPartidasGanadas() + 1);
            p.jugadores[i].usuario.getEst().getEstC().getAchievements().setPrimeraMaGuanyada();

        }//END_ELSEIF
        }

        //EMPATE
        else if(m.getPuntuacion() == p_croupier && m.getPuntuacion()<22 ){

            p.jugadores[i].end(m.getIdMano(), m.getApuesta());

            if(p.jugadores[i].usuario!=null){
                p.jugadores[i].usuario.getEst().getEstC().setNumEmpats(p.jugadores[i].usuario.getEst().getEstC().getNumEmpats() + 1);


            if(m.getPuntuacion() == 21){

                p.jugadores[i].usuario.getEst().getEstC().getAchievements().setEmpatBJ();

            }//END_IF
            }
        }//END_ELSEIF

        //GANAS o EL CROUPIER SE PASA
        else if(((m.getPuntuacion()>p_croupier)&&(m.getPuntuacion()<21)) || ((p_croupier>21)&&(m.getPuntuacion()<21)) ){

            //if(p.jugadores[i].indagame){
            p.jugadores[i].end(m.getIdMano(), 2 * m.getApuesta());//}
           // else{
                //p.jugadores[i].end(m.getIdMano(),0);
            //}
            if(p.jugadores[i].usuario!=null){
                p.jugadores[i].usuario.getEst().setPartidasGanadas(p.jugadores[i].usuario.getEst().getPartidasGanadas() + 1);

            p.jugadores[i].usuario.getEst().getEstC().getAchievements().setPrimeraMaGuanyada();

            if(m.getPuntuacion()<14){

                p.jugadores[i].usuario.getEst().getEstC().getAchievements().setGuanyarMenysCatorze();

            }//END_IF
            }
        }//END_ELSEIF

        //PIERDES
        else{

           if(p.jugadores[i].usuario!=null){
               p.jugadores[i].usuario.getEst().setPartidasPerdidas(p.jugadores[i].usuario.getEst().getPartidasPerdidas() + 1);


            if(m.getPuntuacion()==20){

                p.jugadores[i].usuario.getEst().getEstC().getAchievements().setPerdreAmbVint();

            }//END_IF
           }
        }//END_ELSE

        if(p.jugadores[i].usuario!=null){
            p.jugadores[i].usuario.getEst().setPartidasJugadas(p.jugadores[i].usuario.getEst().getPartidasJugadas() + 1);

        
        if(p.jugadores[i].getChips()>p.jugadores[i].usuario.getEst().getEstC().getMaxNumFitxes()){

            p.jugadores[i].usuario.getEst().getEstC().setMaxNumFitxes(p.jugadores[i].getChips());

        }//END_IF

        if(p.jugadores[i].usuario.getEst().getPartidasGanadas() == 15){

            p.jugadores[i].usuario.getEst().getEstC().getAchievements().setQuinzeMansGuanyades();

        }//END_IF

        comprobar_last_achievement(i);
        }
        if(p.jugadores[i].getChips()<p.apostaMinima){

            p.jugadores[i].indagame = false;
        }

    }//END_METHOD


   /**
    * Descripcio: se elige la opcion split de la mano que se esta jugando
    * @param mano: identificador de la mano jugada
    */
    public void split(int mano){

        if(p.jugadores[p.getJugadorActual()].usuario!=null){
            p.jugadores[p.getJugadorActual()].usuario.getEst().getEstC().setNumSplits(p.jugadores[p.getJugadorActual()].usuario.getEst().getEstC().getNumSplits() + 1);
        }
        
        p.jugadores[p.getJugadorActual()].splitting(mano);

    }//END_METHOD


   /**
    * Descripcio: se elige la opcion doblar de la mano que se esta jugando
    * @param mano: identificador de la mano jugada
    */
    public void doblar(int mano){

        if(p.jugadores[p.getJugadorActual()].usuario!=null){
            p.jugadores[p.getJugadorActual()].usuario.getEst().getEstC().setNumApostesDoblades(p.jugadores[p.getJugadorActual()].usuario.getEst().getEstC().getNumApostesDoblades() + 1);
        }
        p.jugadores[p.getJugadorActual()].double_down(mano);
        hit(mano);
        //p.CalcularSiguienteJugador();

    }//END_METHOD


   /**
    * Descripcio: se elige la opcion seguro de la mano que se esta jugando
    * @param mano: identificador de la mano jugada
    */
    public void seguro(int mano){

        if(p.jugadores[p.getJugadorActual()].usuario!=null){
            p.jugadores[p.getJugadorActual()].usuario.getEst().getEstC().setNumSegurosEmprats(p.jugadores[p.getJugadorActual()].usuario.getEst().getEstC().getNumSegurosEmprats() + 1);
        }
        p.jugadores[p.getJugadorActual()].insurance(mano);

    }//END_METHOD


   /**
    * Descripcio: se elige la opcion hit de la mano que se esta jugando
    * @param mano: identificador de la mano jugada
    */
    public void hit(int mano){

        p.jugadores[p.getJugadorActual()].hitting(mano,(Carta)p.baraja.sacar_carta());

    }//END_METHOD


   /**
    * Descripcio: se elige la opcion stand
    */
    public void stand(){

        p.CalcularSiguienteJugador();

    }//END_METHOD


   /**
    * Descripcio: se hace la apuesta inicial
    * @param apuesta_ini: valor de la apuesta inicial
    */
    public void aposta_ini(int apuesta_ini){

        p.jugadores[p.getJugadorActual()].start(apuesta_ini);
        p.CalcularSiguienteJugador();

    }//END_METHOD


   /**
    * Descripcio: se comprueba si se tienen todos los achievements menos el ultimo
    * @param i: identificador del jugador
    */
    public void comprobar_last_achievement(int i){

        if(p.jugadores[i].usuario.getEst().getEstC().getAchievements().getAllIn() && p.jugadores[i].usuario.getEst().getEstC().getAchievements().getDobleSplit()
                && p.jugadores[i].usuario.getEst().getEstC().getAchievements().getEmpatBJ() && p.jugadores[i].usuario.getEst().getEstC().getAchievements().getGuanyarMenysCatorze()
                && p.jugadores[i].usuario.getEst().getEstC().getAchievements().getPerdreAmbVint() && p.jugadores[i].usuario.getEst().getEstC().getAchievements().getPrimerBJ()
                && p.jugadores[i].usuario.getEst().getEstC().getAchievements().getPrimeraMaGuanyada() && p.jugadores[i].usuario.getEst().getEstC().getAchievements().getQuinzeBJs()
                && p.jugadores[i].usuario.getEst().getEstC().getAchievements().getQuinzeMansGuanyades()){

            p.jugadores[i].usuario.getEst().getEstC().getAchievements().setTotsAchievements();
        }

    }//END_METHOD
                    
} //END_CLASS




