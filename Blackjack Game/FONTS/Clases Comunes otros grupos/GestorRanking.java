package dominio;


/**
*
* @author Vincenzo Sellitti
* Cluster y Grupo: C8G1
* Creado el 30 de abril de 2010, 09:30
*
* Objetivo de la clase: Gestiona las operaciones de guardar y salvar el ranking en el disco
*
* Clases utilizadas: Ranking.
*
*/
import java.io.*;

public class GestorRanking {

    /**
      * Constructor vacio
      * @param -
      * @return  -
      * @throws -
      */
    public GestorRanking(){

    }//END_CONSTRUCTOR

    /**
      * Guarda en un fichero el ranking pasado como parametro;
      * @param - o:Vector a guardar;
      * @return  -
      * @throws - FileNotFoundException,IOException
      */

    public void guardarRanking(Ranking[] o) throws FileNotFoundException,IOException{
        try{
            
            String s = System.getProperty("user.dir"); //s contiene el directorio actual

            File f2 = new File(s,"ranking.dat");
            
            FileOutputStream fos = new FileOutputStream(f2);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            
            for(int i=0; i<10; i++) {
            out.writeObject( (Ranking) o[i]);
            }

            //out.writeObject(o);
            out.flush();
            out.close();
           
        }//END_TRY

        catch(FileNotFoundException f){
            f.printStackTrace();
        }//END_CATCH

        catch(IOException e){
            e.printStackTrace();
        }//END_CATCH

  }//END_METODE


  /**
      * Carga desde un fichero el ranking y lo devuelve como paramentro de vuelta;
      * @param -
      * @return  - clasificacion : El ranking cargado
      * @throws - FileNotFoundException,IOException,ClassNotFoundException,RankingNotFoundException
      */

  public Ranking[] cargarRanking() throws ClassNotFoundException,FileNotFoundException,IOException, RankingNotFoundException{

     
      Ranking [] clasificacion = new Ranking[10];
      for(int i=0;i<10;i++){clasificacion[i]=new Ranking();} //Inicializo el vector que contendrà el Ranking
          try{

               String s =System.getProperty("user.dir"); //s contiene el directorio actual
               File f1 = new File(s,"ranking.dat");
               if(!f1.exists()){                  
                   throw new RankingNotFoundException(); //si no esiste ningun ranking genera un exception
               }
               FileInputStream fis = new FileInputStream(f1);
               ObjectInputStream in = new ObjectInputStream(fis);
                        

               for(int i=0; i<10; i++) {
                   clasificacion[i]=( (Ranking) in.readObject() );
               }

               in.close();
        }//END_TRY

        catch(IOException e){
                  e.printStackTrace();
        }//END_CATCH

        catch(ClassNotFoundException f){
            f.printStackTrace();
        }//END_CATCH

        
        return clasificacion;//Devuelve el ranking
       

    }//END_METODE


}//END_CLASS

