package ie;

/**
 * @author Aleix Fernández Donis
 * Cluster y Grupo C8G3
 * Creado el 5-May-2010
 *
 * Objetivo de la clase: utilidad básica para gestiones pares
 */


import java.io.Serializable;


public class IEPair<A, B> implements Serializable{

  private A first;
  private B second;

  public IEPair(A first, B second) {
    this.first = first;
    this.second = second;
  }
  

  public A getFirst() {
    return first;
  }

  public void setFirst(A first) {
    this.first = first;
  }

  public B getSecond() {
    return second;
  }

  public void setSecond(B second) {
    this.second = second;
  }
}
