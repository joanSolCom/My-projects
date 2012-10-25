package ie;

/**
 * @author Aleix Fernández Donis
 * Cluster y Grupo C8G3
 * Creado el 5-May-2010
 *
 * Objetivo de la clase: clase para representar los valores
 */


/*
 * Clase que gestino los distintos valores que puede tener un conocimiento
 * o hecho de la base de hechos.
 * Los hechos pueden ser tanto:
 *  básicos     (o atómicos) enteros, reales i booleanos
 *  compuestos  listas o tuplas
 *  funciones   ya sean primitivas y definidas en java como definidas mediante
 *              el lenguaje del motor de reglas
 */





import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.Serializable;
import dominio.TC;
public class IEValue implements Serializable{
  /**
   * init   nos dice si ese conocimiento está inicializado (necesario
   *        para saber si un hecho que pertenece a la tabla de símbolos
   *        le ha sido asignado algún valor)
   * mod    nos dice si esa variable es de módulo o no
   *        necesario para ReActivar un módulo asignándole un 2
   * tp     tipo del valor
   * l      lista de IEValue usada para los tipos compuestos 
   * macro  guarda la raiz que representa la función
   * app    guarda el IEAF que implementa esa función primitiva
   * b      para valores booleanos nos da el valor
   * i      para valores enteros nos da el valor
   * r      para valores reales nos da el valor
   */

  public boolean init;
  public boolean mod;
  public IEType tp;
  public List<IEValue> l;
  public IEAST macro;
  public IEInterfaceFunction app;
  public boolean b;
  public int i;
  public double r;


  /**
   * constructora
   */
  public IEValue() {
    init = true;
    mod = false;
    tp = null;
    l = null;
    macro = null;
    app = null;
  }

  /**
   * Crea valor apartir de cadena y dos tipos
   * Utilizada para crear IEValues de funciones
   */
  public IEValue(String s, IEType tri, IEType tre) {
    init = true;
    mod = false;
    tp = new IEType(s, tri, tre);
    l = null;
    macro = null;
    app = null;
  }

  /**
   * Crea apartir de un tipo un valor del mismo tipo
   */
  public IEValue(IEType t) {
    init = true;
    mod = false;
    tp = t;
    l = null;
    macro = null;
    app = null;
  }

  /**
   * Crea apartir de un IEValue otro IEValue idéntico
   * pero copiado recursivamente
   */
  public IEValue(IEValue v) {
    copy(v);
  }
  
  /**
   * Función que hace una copia recursiva
   */
  void copy(IEValue v) {
    init = true;
    macro = v.macro;//no copy
    app = v.app;//no copy
    b = v.b;
    i = v.i;
    r = v.r;
    v.mod = false;
    tp = v.tp;//no copy
    if(v.l == null) {
      l = null;
    }
    else {
      l = new ArrayList<IEValue>(v.l.size());
      Iterator<IEValue> it = v.l.iterator();
      while(it.hasNext()) {
        IEValue e = new IEValue(it.next());
        l.add(e);
      }
    }
  }


  /**
   * Representación en String del valor
   */
  public String toString() {
    String s = "(: " + (tp == null ? "null" : tp.getType()) + ") ";
    if (mod) {
      s += "|mod| ";
    }
    return s + toStringRes();
  }

  /**
   * Función para mapear un IEValue a una cadena de carácteres
   */
  public String toStringRes() {
    String s = "";
    if (tp == null) {
      s = "NOTYPE";
    }
    else if (!init) {
      s += "(¬init)";
    }
    else if (tp.equals(TC.tb)) {
      s += b;
    }
    else if (tp.equals(TC.ti)) {
      s += i;
    }
    else if (tp.equals(TC.tr)) {
      s += r;
    }
    else if (tp.comp()) {
      if (l != null) {
        Iterator<IEValue> it = l.iterator();
        if (tp.type.equals("list")) s += "[";
        else s += "<";
        while (it.hasNext()) {
          s += it.next().toStringRes();
          if (it.hasNext()) s += ",";
        }
        if (tp.type.equals("list")) s += "]";
        else s += ">";
      }
      else {
        s += "NULL";
      }
    }
    //else if (tp.type.equals("idmacro")) {
    //}
    //devolver el árbol no ayuda mucho a la visibildad...
    return s;
  }
}
