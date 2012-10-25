package ie;

/**
 * @author Aleix Fernández Donis
 * Cluster y Grupo C8G3
 * Creado el 5-May-2010
 *
 * Objetivo de la clase: gestión de la tabla de símbolos
 */


/*
 * Clase para la tabla de símbolos, necesaria tanto para el análisis semántico
 * como para la interpretación del programa.
 * Se implemetne con un HashMap que asocia identificadores de variables a IEValue
 */





import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import dominio.TC;
import java.io.Serializable;

public class IESymTab implements Serializable{
  /**
   * sv es la ED de tabla de símbolos en sí
   */
  Map<String, IEValue> sv;


  /**
   * creadora
   */
  IESymTab(IEFunction function) {
    sv = new HashMap<String, IEValue>();
    initGlobals();
    initFunction(function);
  }


  /**  
   * añade los identificadores propios del lenguaje
   * true i false únicos identificadores del lenguaje
   */
  private void initGlobals() {
    IEValue bct = new IEValue(TC.tb);
    bct.b = true;
    createId("true", bct);
    IEValue bcf = new IEValue(TC.tb);
    bcf.b = false;
    createId("false", bcf);
  }


  /**  
   * carga en la tabla de símbolos las funciones primitivas
   */
  private void initFunction(IEFunction f) {
    //con iteradores...
    Iterator<IEPair<String, IEValue>> it = f.function.iterator();
    while (it.hasNext()) {
      IEPair<String, IEValue> p = it.next();
      createId(p.getFirst(), p.getSecond());
    }
  }


  /**
   * operacion que devuelve true si y solo si ya existe la variable con
   * identificador id
   */
  boolean find(String id) {
    return sv.containsKey(id);
  }

  /**
   * operación que devuelve el IEValue de una variable a partir de su id
   */
  IEValue get(String id) {
    return sv.get(id);
  }

  /**
   * operación para asociar a un identificador id un IEValue v
   */
  void createId(String id, IEValue v) {
    sv.put(id, v);
  }


  /*
   * añade nuevo conocimiento a la base de hechos
   */
  String setKnowledge(IEPair<String, IEValue>[] know) {
    String s = "[setKnowledge]\n";
    for (int i = 0; i < know.length; ++i) {
      String id = know[i].getFirst();
      IEValue v = know[i].getSecond();
      if (find(id)) {
        if (v.tp.exactlyEquals(get(id).tp)) {
          createId(id, v);
          s += "[setKnowledge] create id " + id + " = " + v + "\n";
        }
        else {
          s += "[setKnowledge] invalid id type" + id + " (" + v.tp.getType() + ", " + get(id).tp.getType() + ")\n";
        }
      }
      else {
       s += "[setKnowledge] Undefined id: " + id + "\n";
      }
    }
    return s;
  }

  /*
   * devuelve un conocimiento de la base de hechos
   * si no se encuentra se devuelve un valor con tipo TC.te
   */
  IEValue getKnowledge(String id) {
    IEValue v = get(id);
    if (id == null) {
      v = new IEValue(TC.te);
    }
    return v;
  }

  /*
   * fija el objetivo
   */
  void objective(String objective) {
    IEValue v = get(objective);
    v.init = false;
  }

  /*
   * hace un reset de la tabla de símbolos, poniendo init = false
   */
  void reset() {
    Iterator<Map.Entry<String, IEValue>> it = sv.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry<String, IEValue> e = it.next();
      if (!e.getValue().tp.type.equals("idapp")) {
        e.getValue().init = false;
      }
    }
    initGlobals();
  }


  /**
   * operación para ayudar al debug, simplemente vuelca toda la st en una sting
   */
  String writeVerbose() {
    String s = "[symtab] writeVerbose\n";
    Iterator<Map.Entry<String, IEValue>> it = sv.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry<String, IEValue> e = it.next();
      s += e.getKey() + " -> " + e.getValue() + "\n";
    }
    return s;
  }

  /**
   * operación para ayudar al debug, simplemente vuelca toda la st en una sting
   * sólo pone los identificadores que no són ni anónimos (paso de parámetros)
   * ni son primitivas
   */
  String write() {
    String s = "[symtab] write\n";
    Iterator<Map.Entry<String, IEValue>> it = sv.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry<String, IEValue> e = it.next();
      if (!e.getValue().tp.type.equals("idapp") && e.getKey().charAt(0) != '_') {
        s += e.getKey() + " -> " + e.getValue() + "\n";
      }
    }
    return s;
  }
}
