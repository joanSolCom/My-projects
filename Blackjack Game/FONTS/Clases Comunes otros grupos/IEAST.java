package ie;

/**
 * @author Aleix Fernández Donis
 * Cluster y Grupo C8G3
 * Creado el 5-May-2010
 *
 * Objetivo de la clase: Definir el Nodo de nuestro AST
 */


/*
 * Clase para los nodos del árbol AST (abstract syntax tree) que IE utiliza.
 * Bàsicamente se le añade un IEType para saber què tipo tiene la expresión
 * de la raiz del árbol (además de la línea del programa).
 * Además implemetna Comparator (siendo el criterio de comparación la línea
 * del código del fichero de reglas que creo ese nodo). Esto lo necesita el
 * IEInterprete para tener un criterio de ordenar las reglas persistentes
 * (y que este criterio sea el correcto)
 */





import antlr.Token;
import antlr.CommonAST;
import antlr.ASTFactory;
import antlr.collections.AST;

import java.util.Comparator;
import java.io.Serializable;


public class IEAST extends antlr.CommonAST implements Comparator,Serializable {
  /**
   * IEType tp: el IEType de la raiz
   * String tt: string con el tipo del token de la raiz (ID, PLUS, OCURLY, DOT...)
   * int line:  la línea de código que genero la raiz del AST
   */

  IEType tp;
  String tt;
  int line;


  /**
   * constructora
   */
  public IEAST() {
    super();
  }

  /**
   * constructora
   */
  public IEAST(Token ok) {
    super();
  }


  /**
   * inicializado apartir del int que identifica el token y el txt
   * nosotros mapeamos el tipo para guardar el tipo como string
   * el tipo que creamos por defecto es el tipo correspondiente a un error
   */
	public void initialize(int t, String txt) {
    super.initialize(t, txt);
    tt = IEParser.t2S(t);
    tp = new IEType();
    line = -1;
	}

  /**
   * inicializando apartir de otro árbol
   */
	public void initialize(AST t) {
    super.initialize(t);
    tt = IEParser.t2S(t.getType());
    tp = new IEType();
    line = t.getLine();
	}

  /**
   * inicializando a partir de un token
   */
	public void initialize(Token tok) {
    super.initialize(tok);
    tt = IEParser.t2S(tok.getType());
    tp = new IEType();
    line = tok.getLine();
	}


  /**
   * función para comprar dos AST, implementando Comparator
   */
  public int compare(Object a, Object b) {
    return ((IEAST)a).getLine() - ((IEAST)b).getLine();
  }


  /**
   * funciones para navejar por el ábrol
   * primer hijo, y siguiente hermano
   */
  IEAST down() {
    return (IEAST) super.getFirstChild();
  }
  IEAST right() {
    return (IEAST) super.getNextSibling();
  }


  /**
   * getter del atributo line
   */
  public int getLine() {
    return line;
  }

  /**
   * devolvemos tanto el texto del nodo como su tipo
   */
  String getTextIEType() {
    return tt + " " + getText() + " : " + tp.getType();
  }

  /**
   * devolvemos el string asociado al Tipo del token
   */
  String getTt() {
    return tt;
  }

  /**
   * devolvemos el string asociado al IEType
   */
  String getIEType() {
    return tp.getType();
  }

  /**
   * funciones para ayudar a depurar
   */
  String verbose() {
    return "[" + getLine() + "] " + tt + " " + getText() + " : " + (tp == null ? "null" : tp.getType());
  }

  String verboseHash() {
    return "[" + getLine() + "] " + tt + "  " + getText() + " : " + tp.getType() + " hash: " + this.hashCode() + " hash tt: " + tt.hashCode() + " hash tp: " + tp.hashCode();
  }


  /*
   * conversión de AST a String
   */
  public String toString() {
    return toString("");
  }

  public String toString(String o) {
    String s = o + verbose() + "\n";
    if (down() != null) {
      s += down().toString(o + "| ");
    }
    if (right() != null) {
      s += right().toString(o);
    }
    return s;
  }
}
