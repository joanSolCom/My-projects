package ie;
import java.io.Serializable;
/**
 * @author Aleix Fernández Donis
 * Cluster y Grupo C8G3
 * Creado el 5-May-2010
 *
 * Objetivo de la clase: clase para los tipos del lenguaje
 */


/*
 * Clase para representar los diferentes tipos que pueden tener los hechos
 */




public class IEType implements Serializable{
  String type;
  IEType right, ret, tuple;

  /**
   * type puede contener los siguientes valores
   *    error   tipo para representación interna
   *            significa que o bien no se ha hecho una comprobación de tipos
   *            o ha habido algún error al intentar hacer la comprobación
   *    idapp   idapp es un identificado de primitiva
   *            (función primitiva del motor implementada en java)
   *    idmacro idmacro es un identificador de macro
   *            (función definida en el mismo archivo de reglas)
   *    string
   *    bool
   *    real
   *    tuple   es del tipo tuple
   *            ret apunta a una lista de IEType encadenadas por tuple
   *            el primer elemento de la lista nos da el IEType del primer
   *            elemento de la tupla
   *            (las tuplas por restricción sintáctica tienen almenos dos elementos)
   *            (diferentes elementos de una tupla posiblemente tienen distintos tipos)
   *    list    es del tipo lista
   *            en ret está el IEType de los elementos
   *            (todos los elementos de una misma lista tienen el mismo tipo)
   *    (*)+    tipos especiales que unifica con todos los tipos (*-unificables)
   *            necesarios para que haya polimorfismo
   *            map :: (* -> **) -> [*] -> [**]
   *            Hay que tener en cuenta que * y ** pueden unificarse a tipos diferente
   *            pero es posible unificarlos a un mismo tipo
   *            Es posible que temporalmente, en el proceso de análisis semántico puedan
   *            existir tipos *f, *p (para diferenciar *-unificables de distinos
   *            parámetros en el proceso de unificación), pero una vez acabado el proceso
   *            no queda ningún *X
   *    ok      tipo para representación interna
   *            significa que un árbol sin tipo todos sus hijos están bien tipificados
   *            (por ejemplo, si en la raiz del árbol, "program", tenemos el tipo ok
   *            significa que todos los tipos de sus hijos, todo el programa, tiene
   *            tipos correctos
   *
   *
   *    Funciones (idmacro e idapp)
   *              en right tienen el tipo de su parámetro
   *              en ret tienen el tipo que devuelven
   *            como utilizamos curryficación la función "resto a b = a % b"
   *            tendría tipo resto :: int -> (int -> int)
   *            esto es, tiene un único parámetro int, y devuelve una función
   *            con un único parámetro int que devuelve un int.
   */


  /**
   * Constructora por defecto, tipo = error
   */
  public IEType() {
    type = "error";
    right = ret = tuple = null;
  }

  /**
   * Constructora apartir del nombre de un tipo
   * Utilizada para tipos básicos
   */
  public IEType(String s) {
    type = s;
    right = ret = tuple = null;
  }

  /**
   * Constructora apartir de un nombre y dos tipos
   * Utlizada para funcinoes (idmacro e idapp)
   */
  public IEType(String s, IEType ri, IEType re) {
    type = s;
    tuple = null;
    right = ri;
    ret = re;
  }

  /**
   * Constructora que apartir de un tipo x nos devuelve el tipo [x]
   * Utlizada para crear tipos de listas
   */
  public IEType(IEType t) {
    type = "list";
    right = tuple = null;
    ret = t;
  }

  /**
   * Constructora que apartir de un entero n nos devuelve el tipo *^n
   * Utilizada para renombrar tipos *-unificables en los parámetros de funciones
   */
  public IEType(int n) {
    String s = "";
    for (int i = 0; i < n; ++i) {
      s += "*";
    }
    type = s;
    right = ret = tuple = null;
  }

  /**
   * Constructora para crear tuplas
   */
  public IEType(String s, IEType e) {
    type = s; //type == "tuple"
    ret = e;
    right = tuple = null;
  }

  /**
   * Constructora para crear encadenados...
   */
  public IEType(IEType t, IEType e) {
    type = t.type;
    right = t.right;
    ret = t.ret;
    tuple = e;
  }

  /**
   * Función que nos devuelve un IEType
   * Para copiar (recursivamente) un objeto
   */
  public IEType copy() {
    IEType t = new IEType(type);
    if (ret != null) {
      t.ret = ret.copy();
    }
    if (right != null) {
      t.right = right.copy();
    }
    if (tuple != null) {
      t.tuple = tuple.copy();
    }
    return t;
  }

  /**
   * Función para copiar recursivamente
   */
  public void copy(IEType t) {
    type = t.type;
    if (t.ret != null) {
      ret = t.ret.copy();
    }
    else {
      ret = null;
    }
    if (t.right != null) {
      right = t.right.copy();
    }
    else {
      right = null;
    }
    if (t.tuple != null) {
      tuple = t.tuple.copy();
    }
    else {
      tuple = null;
    }
  }

  /**
   * Para un tipo *-unificable nos devuelve la longitud del campo type
   * Para un tipo no *-unificable nos devuelve 0
   */
  int undef() {
    if (type.charAt(0) == '*') {
      return type.length();
    }
    return 0;
  }

  /**
   * Tiene algún tipo *-unificable
   * (las funciones aunque sean polimórficas no las consideramos *-unificables)
   */
  boolean someUndef() {
    if (func()) {
      return false;
    }
    if (undef() > 0) {
      return true;
    }
    if (type.equals("tuple")) {
      IEType t = ret;
      while (t != null) {
        if (t.someUndef()) {
          return true;
        }
        t = t.tuple;
      }
    }
    else if (ret != null && ret.someUndef()) {
      return true;
    }
    return false;
  }

  /**
   * Devuelve true si y sólo si el tipo es una función
   */
  boolean func() {
    return type == "idapp" || type == "idmacro" || type == "idfunc";
  }

  /**
   * Devuelve true si y sólo si el tipo es compuesto
   */
  boolean comp() {
    return type == "list" || type == "tuple";
  }


  /**
   * Comprueba si dos tipos son iguales
   * uno puede estar o no dentro de una tupla y eso no afecta en nada,
   * por tanto el valor del campo tuple puede ser distinto
   *
   * Queremos saber si dos tipos son iguales, por lo tanto no tiene sentido
   * que el int del primer elemento de {1, true} tenga tipo diferente a al int 2
   */
  public boolean equals(IEType t) {
    if (t == null) {
      return false;
    }

    if (type.equals("error") && !t.type.equals("error") ||
        !type.equals("error") && t.type.equals("error")) {
      return false;
    }
    else if (type.charAt(0) == '*' || t.type.charAt(0) == '*') {
      return true;
    }

    if (t.func() && func()) {
    }
    else if (!type.equals(t.type)) {
      return false;
    }

    if (type.equals("list")) {
      if(!ret.equalsAll(t.ret)) return false;
    }
    else if (type.equals("tuple")) {
      if(!ret.equalsAll(t.ret)) return false;
    }
    else if (func()) {
        if(!right.equalsAll(t.right) || !ret.equalsAll(t.ret)) return false;
    }

    return true;
  }

  /**
   * Comprueba si dos tiops son iguales y si están en una tupla, si están en la misma tupla
   * Función auxiliar utilizada por equals
   */
  public boolean equalsAll(IEType t) {
    if (type.equals("error") && !t.type.equals("error") ||
        !type.equals("error") && t.type.equals("error")) {
      return false;
    }
    else if (type.charAt(0) == '*' || t.type.charAt(0) == '*') {
      return true;
    }

    if (t.func() && func()) {
    }
    else if (!type.equals(t.type)) {
      return false;
    }

    if (type.equals("list")) {
      if (!ret.equalsAll(t.ret)) return false;
    }
    else if (type.equals("tuple")) {
      if (!ret.equalsAll(t.ret)) return false;
    }
    else if (func()) {
      if (!right.equalsAll(t.right) || !ret.equalsAll(t.ret)) return false;
    }

    if (tuple != null) {
      return t.tuple != null && tuple.equals(t.tuple);
    }

    return t.tuple == null;
  }


  /**
   * Comprueba que dos tipos son exactamente iguales
   * Sólo se diferencia de equals() porque tiene en cuenta que si hay parámetors
   * de una función han de ser el mismo
   * Esto srive porque siempre obligamos a una función a tener el tipo más definido posible
   */
  public boolean exactlyEquals(IEType t) {
    if(undef() > 0 && t.undef() > 0) {
      if (type.charAt(type.length()-1) == '_' || t.type.charAt(t.type.length()-1) == '_') {
        return type.equals(t.type);
      }
      else {
        return true;
      }
    }
    else if (undef() > 0) {
      return false;
    }
    else if (t.undef() > 0) {
      return false;
    }
    if (t.func() && func()) {
    }
    else if (!type.equals(t.type)) {
      return false;
    }

    if (type.equals("list")) {
      if (!ret.exactlyEquals(t.ret)) {
        return false;
      }
    }
    else if (type.equals("tuple")) {
      IEType tmp = ret;
      t = t.ret;
      while (tmp != null && t != null) {
        if (!tmp.exactlyEquals(t)) {
          return false;
        }
        tmp = tmp.tuple;
        t = t.tuple;
      }
      if (t != null || tmp != null) {
        return false;
      }
    }
    else if (func()) {
      if (!right.exactlyEquals(t.right) || !ret.exactlyEquals(t.ret)) {
        return false;
      }
    }
    return true;
  }
 

  /**
   * Devolvemos una string representando el tipo
   */
  public String getType() {
    String s = type;
    if (type == "list") s += "[" + ret.getType() + "]";
    else if (type == "tuple") {
      s += "<";
      for (IEType t = ret; t != null; t = t.tuple) {
        s += t.getType();
        if (t.tuple != null) s += ",";
      }
      s += ">";
    }
    else if (func()) {
      //idapp obligatorio que tenga ret, pero puede no tener right (función sin parametros)
      s = "( [" + type + "] " + (right != null ? right.getType() : "" ) + ") -> " + ret.getType();
    }
    return s;
  }
}
