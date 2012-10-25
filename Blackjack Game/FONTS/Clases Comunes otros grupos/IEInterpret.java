package ie;

/**
 * @author Aleix Fernández Donis
 * Cluster y Grupo C8G3
 * Creado el 5-May-2010
 *
 * Objetivo de la clase: interpreta un ast bien tipado y decorado
 */


/*
 * Clase que se encarga de interpretar un código semánticamente correcto,
 * por lo tanto sintácticamente correcto.
 * La idea es tener una cola de reglas para las reglas persistentes y a cada
 * paso mirar si alguna regla de la cola menor que la regla actual (siendo este
 * menor usado por la comparación, el comparador entre IEAST, esto es, el que
 * esté antes en el código). Una vez decidida que regla la parte principal se
 * hace en la función evalue que dado un árbol correspondiente a una expresión
 * evalúa esta función.
 */





import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Iterator;
import java.util.ListIterator;
import dominio.TC;
import java.io.Serializable;

class IEInterpret implements Serializable{
  /**
   * program      árbol ha interpretar
   * pm           puntero de modulo
   * pt           puntero a término
   * persistent   cola de reglas persistentes
   * st           tabla de símbolos
   * objective    objetivo al hacer la resolución
   * log          log interno del interpret
   * abort        valor en caso de producirse un abort
   * execution    si false al interpretar se evaluarà global y luego pasará a true 
   */

  IEAST program;
  IEAST pm;
  IEAST pt;
  PriorityQueue<IEAST> persistent;
  IESymTab st;
  String objective;
  String log;
  IEValue abort;
  boolean execution;


  /**
   * Constructora
   */
  IEInterpret() {
    log = "";
    execution = false;
  }


  /**
   * getter
   */
  String getLog() {
    return log;
  }

  /**
   * "setter"
   */
  void resetLog() {
    log = "";
  }


  /**
   * reset
   */
  void reset() {
    execution = false;
  }


  /**
   * devuelve si se ha cumplido o no el objetivo
   */
  boolean objective() {
    return st.get(objective).init;
  }


  /**
   * LIST program : ok
   * | DDOT : : ok
   * | | ID constant : int
   * | | (knows)*
   * (...)
   * Entrada un programa correcto y con los tipos asignados
   *
   * Llama a executeDecKnow para constant y global
   * Inicia la cola persistent a vacío
   * Empieza a ejectura por el main
   */

  void resolution(IEAST a, IESymTab s, String o) throws IEError {
    log += "[resolution." + execution + "] " + o + " in " + a.verbose() + "\n";
    program = a;
    st = s;
    objective = o;
    persistent = new PriorityQueue<IEAST>(30, new IEAST());
    if (!execution) {
      execution = true;
      executeDecKnow(a.down().down().right());
    }
    reExecute(a.down().right());
  }


  /**
   * Ejecuta un bloque de declaraciones (sólo hechos...)
   */
  void executeDecKnow(IEAST a) throws IEError {
    log += "[executeDecKnow]" + a.verbose() + "\n";
    if (a == null) return;
    for (a = a.down(); a != null; a = a.right()) {
      executeTerm(a);
    }
  }


  /**
   * A es un DDOT
   * Ejecute un módulo cualquiera
   * Mira si la variable del módulo
   * si es diferente a 0 entonces ejecuta el módulo
   * sino se llama recursivamente para ejecutar el siguiente módulo
   * PARA EL MOD = 2 HAY QUE VARIAR ALGO, PONER LA VAR DEL BUCLE EN GLOBAL, Y TAL, ESTO ES EL POINTERPROGRAM
   * ES BASTANTE RUDIMENTARIO
   */
  void reExecute(IEAST a) throws IEError {
    log += "[reExecute]" + a.verbose() + "\n";
    pm = a;
    IEAST pmc = pm;
    while (pm != null && !objective()) {
      IEValue mod = st.get(pm.down().getText());
      if (mod.i != 0) {
        executeMod(pm.down().right());
      }
      if (pmc != pm) {
        pmc = pm;
      }
      else {
        pmc = pm = pm.right();
      }
    }
    while (persistent.size() != 0 && !objective()) {
      Iterator<IEAST> it = persistent.iterator();
      while (it.hasNext()) {
        IEAST t = it.next();
        if (evalue(t.down()).b) {
          executeKnows(t.down().right());
          persistent.remove(t);
          break;
        }
      }
      break;
    }
  }


  /**
   * a es un LIST mod_block
   * va ejecutando los términos teniendo en cuenta los persistentes
   */
  void executeMod(IEAST a) throws IEError {
    log += "[executeMod]" + a.verbose() + "\n";
    if (a == null) return;
    pt = a.down();
    while (pt != null && !objective()) {
      Iterator<IEAST> it = persistent.iterator();
      IEValue b = new IEValue(TC.tb);
      b.b = false;
      IEAST t = null;
      while (it.hasNext() && !b.b) {
        t = it.next();
        if (pt.getLine() < t.getLine()) {
          break;
        }
        b.b = evalue(t.down()).b;
        log += "[executeMod] persistent ? " + b.b + " " + t.verbose() + "\n";
      }
      if (b.b) {
        log += "[executeMod] persistent\n";
        executeKnows(t.down().right());
        persistent.remove(t);
      }
      else {
        log += "[executeMod] no persistent\n";
        executeTerm(pt);
        if(pt != null) {
          pt = pt.right();
        }
      }
    }
  }


  /**
   * ejecuta un término
   */
  void executeTerm(IEAST a) throws IEError {
    log += "[executeTerm]" + a.verbose() + "\n";
    if (a.tt.equals("PRINT")) {
      //si a.down() = null no se imprimirà nada...
      log += ast2s(a.down()) + "\n";
    }
    else if (a.tt.equals("ABORT")) {
      log += ast2s(a.down()) + "\n";
      error(a.getLine(), "USER ABORT: " + ast2s(a.down()));
    }
    else if (a.tt.equals("ASIG")) {
      /**
       * los copy ya aseguran init = 1
       * simplemente hay dos casos, tienes un ID
       * o tienes un referenciable, esto es un (INDEX int)* ID
       *
       * NO ME MOLAN NADA ESTOS COPYS porque son inecesarios pero puntero a puntero...?
       * opción a:  hacer un vo = st.get(...); vo.copy(v);  // e.copy(v)
       * opción b:  hacer un st.insertId(, v);              // insert
       * Opción c mucho más eficiente (nos ahorra una copia...)
       */
      IEValue v = evalue(a.down().right());
      if (a.down().tt.equals("ID")) {
        IEValue vo = st.get(a.down().getText());
        v.tp = vo.tp;
        if (vo.mod) {
          v.mod = true;
          log += "[executeTerm] (mod) " + a.down().getText() + " <- " + v.i + "\n";
          if (v.i != 1 && v.i != 0) {
            v.i = 1;
            pm = searchMod(a.down().getText());
            pt = null;
          }
        }
        st.createId(a.down().getText(), v);
      }
      else {
        valueRef(a.down(), v);
      }
    }
    else if (a.tt.equals("NORMAL")) {
      //las reglas, simplemente evaluamos, si son ciertas executeknows...
      IEValue b = evalue(a.down());
      if (b.b) {
        executeKnows(a.down().right());
      }
    }
    else if (a.tt.equals("PERSISTENT")) {
      IEValue b = evalue(a.down());
      if (b.b) {
        executeKnows(a.down().right());
      }
      else {
        persistent.add(a);
        log += "[executeTerm] add persistent (" + (persistent.peek() == null) + ")\n";
      }
    }
    else if (a.tt.equals("MACRO")) {
      IEValue v = st.get(a.down().getText());
      v.macro = a.down().right();
      v.init = true;
    }
    /*
     * con :: no hacemos nada...
     * podríamos podarlo en semantic...
     */
  }


  /**
   * Funcion que ejecuta una lista de knows...
   */
  void executeKnows(IEAST a) throws IEError {
    log += "[executeKnows]" + a.verbose() + "\n";
    if(a == null) return;
    for (a = a.down(); a != null; a = a.right()) {
      executeTerm(a);
    }
  }


  /**
   * evalue recibe un árbol de una expresión y devuelve su valor
   */
  IEValue evalue(IEAST a) throws IEError {
    log += "[evalue]" + a.verbose() + "\n";
    IEValue v;
    if (a.tt.equals("OR")) {
      v = evalueOR(a);
    }
    else if (a.tt.equals("AND")) {
      v = evalueAND(a);
    }
    else if (a.tt.equals("NOT")) {
      v = evalueNOT(a);
    }
    else if (a.tt.equals("REST")) {
      v = evalueREST(a);
    }
    else if (a.tt.equals("MINUS") && a.down().right() == null) {
      v = evalueMINUSu(a);
    }
    else if (a.tt.equals("EQUAL")) {
      v = evalueEQUAL(a);
    }
    else if (a.tt.equals("NOTEQUAL")) {
      v = evalueNOTEQUAL(a);
    }
    else if (a.tt.equals("LESSEQUAL")) {
      v = evalueLESSEQUAL(a);
    }
    else if (a.tt.equals("GREATEQUAL")) {
      v = evalueGREATEQUAL(a);
    }
    else if (a.tt.equals("LESS")) {
      v = evalueLESS(a);
    }
    else if (a.tt.equals("GREAT")) {
      v = evalueGREAT(a);
    }
    else if (a.tt.equals("PLUS")) {
      v = evaluePLUS(a);
    }
    else if (a.tt.equals("MINUS") && a.down().right() != null) {
      v = evalueMINUSb(a);
    }
    else if (a.tt.equals("MUL")) {
      v = evalueMUL(a);
    }
    else if (a.tt.equals("DIV")) {
      v = evalueDIV(a);
    }
    else if (a.tt.equals("INDEX")) {
      v = evalueINDEX(a);
    }
    else if (a.tt.equals("PLUSPLUS")) {
      v = evaluePLUSPLUS(a);
    }
    else if (a.tt.equals("MINUSMINUS")) {
      v = evalueMINUSMINUS(a);
    }
    else if (a.tt.equals("SHARP")) {
      v = evalueSHARP(a);
    }
    else if (a.tt.equals("OSB")) {
      v = evalueOSB(a);
    }
    else if (a.tt.equals("OCURLY")) {
      v = evalueOCURLY(a);
    }
    else if (a.tt.equals("ID")) {
      v = evalueID(a);
    }
    else if (a.tt.equals("INTCONST")) {
      v = evalueINTCONST(a);
    }
    else if (a.tt.equals("REALCONST")) {
      v = evalueREALCONST(a);
    }
    else {
      v = new IEValue(TC.te);
      /* nota : consideramos todos los casos
       *        con lo cual código muerto
       *        se ha de cambiar el último else if por un else...
       */
    }
    return v;
  }


  /**
   * las evalueFunc sirven para que las primitivas puedan
   * evaluar expresiones
   * Por ejemplo un map real [1,2,3], la función map hace una llama a evalueFunc(v1, v2)
   * con v1 el valor de real, y v2 el valor de un elemento
   * Si bien el ejemplo ese era sencillo, cuando tengamos macros puede ser algo complicado:
   * l1 = [1,2,3]
   * l2 = [0.2, 3.1, 4.5, 6.9, 14.5, 9.0, 1]
   * cond r = (int r) % 2 == 0
   * myZip a b c = (a+1, b*2)
   * a = map2 myZip l1 (filter cond l2)
   */
  IEValue evalueFunc(int n, IEValue func, IEValue arg) throws IEError {
    log += "[evalueFunc.1] " + func + " " + arg + "\n";
    if (func.tp.type.equals("idapp")) {
      IEValue[] args = new IEValue[1];
      args[0] = arg;
      return func.app.app(n, args);
    }
    else {
      st.createId("_0", arg);
      return evalue(func.macro);
    }
  }

  IEValue evalueFunc(int n, IEValue func, IEValue arg0, IEValue arg1) throws IEError {
    log += "[evalueFunc.2] " + func + " " + arg0 + " " + arg1 + "\n";
    if (func.tp.type.equals("idapp")) {
      IEValue[] args = new IEValue[2];
      args[0] = arg0;
      args[1] = arg1;
      return func.app.app(n, args);
    }
    else {
      st.createId("_0", arg0);
      st.createId("_1", arg1);
      return evalue(func.macro);
    }
  }

  IEValue evalueFunc(int n, IEValue func) throws IEError {
    log += "[evalueFunc.0] " + func + "\n";
    if (func.tp.type.equals("idapp")) {
      IEValue[] args = new IEValue[0];
      return func.app.app(n, args);
    }
    else {
      return evalue(func.macro);
    }
  }

  IEValue evalueFunc(int n, IEValue func, IEValue[] args) throws IEError {
    log += "[evalueFunc.1] " + "\n";
    if (func.tp.type.equals("idapp")) {
      return func.app.app(n, args);
    }
    else {
      for (int i = 0; i < args.length; ++i) {
        st.createId("_"+i, args[i]);
      }
      return evalue(func.macro);
    }
  }


  /**
   * valueRef nos devuelve el IEValue
   * del elemento referido en una expresión (INDEX INT)* ID
   */
  IEValue valueRef(IEAST a) throws IEError {
    log += "[valueRef.aux] " + a.verbose() + "\n";
    if (a.tt.equals("ID")) {
      IEValue l = st.get(a.getText());
      if (!l.init) {
        errorNotInit(a.getLine(), a.getText());
      }
      return l;
    }
    IEValue l = valueRef(a.down());
    int n = (int) evalue(a.down().right()).i;
    if (n >= l.l.size()) {
      errorOutOfRange(a.getLine(), a.getText(), n);
    }
    return l.l.get(n);
  }

  /**
   * valueRef nos pone el valor v en el lugar indicado
   * en una expresión (INDEX INT)+ ID
   */
  void valueRef(IEAST a, IEValue v) throws IEError {
    log += "[valueRef] " + a.verbose() + "\n";
    IEValue l = valueRef(a.down());
    int n = (int) evalue(a.down().right()).i;
    if (n >= l.l.size()) {
      errorOutOfRange(a.getLine(), a.getText(), n);
    }
    l.l.set(n, v);
  }


  /**
   * devuelve el AST del módulo m apuntando a DDOT
   * evidentemtne a.down siempre el getText y taL
   * pre: m es correcto
   * post: en todo caso devuelve null
   */
  IEAST searchMod(String m) {
    for (IEAST a = program.down(); a != null; a = a.right()) {
      if (a.down().getText().equals(m)) {
        return a;
      }
    }
    return null;
    //va buscando por program el string m, devuelve el adecuado para meterlo ya en pm
  }


  /**
   * devuelve el valor de texto del árbol...
   */
  String ast2s(IEAST a) throws IEError {
    if (a == null) {
      return "";
    }
    else if (a.tt.equals("STRING")) {
      return a.getText();
    }
    else {
      return evalue(a).toString();
    }
  }
  
  /**
   * funciones para interpretar operadores del lenguaje
   */
  IEValue evalueOR(IEAST a) throws IEError {
    IEValue v = evalue(a.down());
    if (!v.b) {
      v = evalue(a.down().right());
    }
    return v;
  }

  IEValue evalueAND(IEAST a) throws IEError {
    IEValue v = evalue(a.down());
    if (v.b) {
      v = evalue(a.down().right());
    }
    return v;
  }

  IEValue evalueNOT(IEAST a) throws IEError {
    IEValue v = evalue(a.down());
    v.b = !v.b;
    return v;
  }

  IEValue evalueREST(IEAST a) throws IEError {
    IEValue v = evalue(a.down());
    IEValue t = evalue(a.down().right());
    if (t.i == 0) {
      errorDivision0(a.getLine(), a.getText());
    }
    else {
      v.i = ((int)v.i) % ((int)t.i);
    }
    return v;
  }

  IEValue evalueMINUSu(IEAST a) throws IEError {
    IEValue v = evalue(a.down());
    v.r = -v.r;
    v.i = -v.i;
    return v;
  }

  IEValue evalueEQUAL(IEAST a) throws IEError {
    IEValue v = new IEValue(TC.tb);
    IEValue v0 = evalue(a.down());
    IEValue v1 = evalue(a.down().right());
    v.b = equal(v0, v1);
    return v;
  }

  IEValue evalueNOTEQUAL(IEAST a) throws IEError {
    IEValue v = new IEValue(TC.tb);
    IEValue v0 = evalue(a.down());
    IEValue v1 = evalue(a.down().right());
    v.b = !equal(v0, v1);
    return v;
  }

  IEValue evalueLESSEQUAL(IEAST a) throws IEError {
    IEValue v = new IEValue(TC.tb);
    IEValue v0 = evalue(a.down());
    IEValue v1 = evalue(a.down().right());
    v.b = !great(v0, v1);
    return v;
  }

  IEValue evalueGREATEQUAL(IEAST a) throws IEError {
    IEValue v = new IEValue(TC.tb);
    IEValue v0 = evalue(a.down());
    IEValue v1 = evalue(a.down().right());
    v.b = !less(v0, v1);
    return v;
  }

  IEValue evalueLESS(IEAST a) throws IEError {
    IEValue v = new IEValue(TC.tb);
    IEValue v0 = evalue(a.down());
    IEValue v1 = evalue(a.down().right());
    v.b = less(v0, v1);
    return v;
  }

  IEValue evalueGREAT(IEAST a) throws IEError {
    IEValue v = new IEValue(TC.tb);
    IEValue v0 = evalue(a.down());
    IEValue v1 = evalue(a.down().right());
    v.b = great(v0, v1);
    return v;
  }

  IEValue evaluePLUS(IEAST a) throws IEError {
    IEValue v = evalue(a.down());
    IEValue t = evalue(a.down().right());
    v.r += t.r;
    v.i += t.i;
    return v;
  }

  IEValue evalueMINUSb(IEAST a) throws IEError {
    IEValue v = evalue(a.down());
    IEValue t = evalue(a.down().right());
    v.r -= t.r;
    v.i -= t.i;
    return v;
  }

  IEValue evalueMUL(IEAST a) throws IEError {
      IEValue v = evalue(a.down());
      IEValue t = evalue(a.down().right());
      v.r *= t.r;
      v.i *= t.i;
      return v;
  }

  IEValue evalueDIV(IEAST a) throws IEError {
    IEValue v = evalue(a.down());
    IEValue t = evalue(a.down().right());
    if (v.tp.equals(TC.ti)) {
      if(t.i == 0) {
        errorDivision0(a.getLine(), a.getText());
      }
      else {
        v.i /= t.i;
      }
    }
    else {
      if(t.r == 0) {
        errorDivision0(a.getLine(), a.getText());
      }
      else {
        v.r /= t.r;
      }
    }
    return v;
  }

  IEValue evalueINDEX(IEAST a) throws IEError {
    int n = evalue(a.down().right()).i;
    List<IEValue> l = evalue(a.down()).l;
    if (n >= l.size()) {
      errorOutOfRange(a.getLine(), a.getText(), n);
    }
    return l.get(n);
  }

  IEValue evaluePLUSPLUS(IEAST a) throws IEError {
    IEValue v = evalue(a.down());
    v.l.addAll(evalue(a.down().right()).l);
    return v;
  }

  IEValue evalueMINUSMINUS(IEAST a) throws IEError {
    IEValue v = evalue(a.down());
    Iterator<IEValue> it = evalue(a.down().right()).l.iterator();
    while (it.hasNext()) {
      IEValue t = it.next();
      ListIterator<IEValue> s = v.l.listIterator();
      while (s.hasNext()) {
        if (equal(s.next(), t)) {
          s.remove();
          break;
        }
      }
    }
    return v;
  }

  IEValue evalueSHARP(IEAST a) throws IEError {
    IEValue v = new IEValue(TC.tr);
    v.i = (int) evalue(a.down()).l.size();
    return v;
  }

  IEValue evalueOSB(IEAST a) throws IEError {
    IEValue v = new IEValue(a.tp);
    v.l = new ArrayList<IEValue>();   
    for(IEAST b = a.down(); b != null; b = b.right()) {
      v.l.add(evalue(b));
    }
    return v;
  }

  IEValue evalueOCURLY(IEAST a) throws IEError {
    IEValue v = new IEValue(a.tp);
    v.l = new ArrayList<IEValue>();
    for(IEAST b = a.down(); b != null; b = b.right()) {
        v.l.add(evalue(b));
    }
    return v;
  }

  IEValue evalueID(IEAST a) throws IEError {
    IEValue v = st.get(a.getText());
    if (!v.init) {
      errorNotInit(a.getLine(), a.getText());
    }
    else if (v.tp.type == "idapp") {
      /**
       * DIFERENCIAR DE CONSTANTE?
       * cosas a diferenciar para un idapp
       *  se aplica
       *  se pasa 
       * esto se puede discernir mirando que a.type no sea una función
       * (no se permite ejecutar con falta de parámetros obviamente)
       */
      
      if (!a.tp.func()) {
        int i;
        IEAST b;
        for(b = a.down(), i = 0; b != null; b = b.right(), ++i);
        IEValue[] args = new IEValue[i];
        for(b = a.down(), i = 0; b != null; b = b.right(), ++i) {
          args[i] = evalue(b);
        }
        /**
         * llegados aquí:
         *  es una app
         *  los tipos son correctos
         *  está completamente instanciada
         */
        v = v.app.app(a.getLine(), args);
        v.tp = a.tp;
        /**
         * en general app devuelve el tipo más definido que se puede calcular de manera inmediata
         * esto es, un map devolverá [*], ya que para calcular * debería unificar
         * por eso, como el tipo ya lo guardamos en la raiz a y sabemos que los tipos son correctos
         * no nos preocupamos que app nos puede devolver un tipo no definido totalmente
         */
      }
    }
    else if (v.tp.type == "idmacro") {
      /**
       * si no es una función que se pase, cargamos los parámetros y evaluamos el árbol
       */
      if (!a.tp.func()) {
        IEAST t = a.down();
        for (int i = 0; t != null; ++i, t = t.right()) {
          v = evalue(t);
          st.createId("_"+i, v);
        }
        IEAST c = st.get(a.getText()).macro;
        v = evalue(st.get(a.getText()).macro);
      }
    }
    else {
      /**
       * es un id de variable, por tanto aquí sí hemos de copiar
       */
      v = new IEValue(v);
    }
    return v;
  }

  IEValue evalueINTCONST(IEAST a) {
    IEValue v = new IEValue(TC.ti);
    v.i = Integer.parseInt(a.getText());
    return v;
  }

  IEValue evalueREALCONST(IEAST a) {
    IEValue v = new IEValue(TC.tr);
    v.r = Double.parseDouble(a.getText());
    return v;
  }


  /**
   * auxiliares para evalues
   */
  boolean equal(IEValue a, IEValue b) {
    if (a.tp.equals(TC.ti)) {
      return a.i == b.i;
    }
    else if (a.tp.equals(TC.tr)) {
      return a.r == b.r;
    }
    else if (a.tp.equals(TC.tb)) {
      return a.b == b.b;
    }
    else if (a.tp.type.equals("list")){
      if (a.l.size() != b.l.size()) {
        return false;
      }
      Iterator<IEValue> ita = a.l.iterator();
      Iterator<IEValue> itb = b.l.iterator();
      while(ita.hasNext()) {
        if (!equal(ita.next(), itb.next())) {
          return false;
        }
      }
      return true;
    }
    else {
      //tuple porque son buenos tipos... y tienen mismo elementos...
      Iterator<IEValue> ita = a.l.iterator();
      Iterator<IEValue> itb = b.l.iterator();
      while(ita.hasNext()) {
        if (!equal(ita.next(), itb.next())) {
          return false;
        }
      }
      return true;
    }
  }

  boolean less(IEValue a, IEValue b) {
    if (a.tp.equals(TC.ti)) {
      return a.i < b.i;
    }
    else if (a.tp.equals(TC.tr)) {
      return a.r < b.r;
    }
    else if (a.tp.equals(TC.tb)) {
      return a.b == false && b.b == true;
    }
    else if (a.tp.type.equals("list")){
      Iterator<IEValue> ita = a.l.iterator();
      Iterator<IEValue> itb = b.l.iterator();
      while (ita.hasNext() && itb.hasNext()) {
        if (!less(ita.next(), itb.next())) {
          return false;
        }
      }
      if (ita.hasNext()) {
        return false;
      }
      return true;
    }
    else {
      Iterator<IEValue> ita = a.l.iterator();
      Iterator<IEValue> itb = b.l.iterator();
      while(ita.hasNext()) {
        if (!equal(ita.next(), itb.next())) {
          return false;
        }
      }
      return true;
    }
  }

  boolean great(IEValue a, IEValue b) {
    if (a.tp.equals(TC.ti)) {
      return a.i > b.i;
    }
    else if (a.tp.equals(TC.tr)) {
      return a.r > b.r;
    }
    else if (a.tp.equals(TC.tb)) {
      return a.b == true && b.b == false;
    }
    else if (a.tp.type.equals("list")){
      Iterator<IEValue> ita = a.l.iterator();
      Iterator<IEValue> itb = b.l.iterator();
      while (ita.hasNext() && itb.hasNext()) {
        if (!great(ita.next(), itb.next())) {
          return false;
        }
      }
      if (itb.hasNext()) {
        return false;
      }
      return true;
    }
    else {
      Iterator<IEValue> ita = a.l.iterator();
      Iterator<IEValue> itb = b.l.iterator();
      while(ita.hasNext()) {
        if (!great(ita.next(), itb.next())) {
          return false;
        }
      }
      return true;
    }
  }


  /**
   * funciones de error
   */
  private void error(int n, String s) throws IEError {
    log += "[interpret] Error in line " + n + ": " + s + "\n";
    throw new IEError("[interpret] Error in line " + n + ": " + s);
  }

  void errorOutOfRange(int n, String s, int i) throws IEError {
    error(n, "Out of range (" + s + ", " + i + ")");
  }

  void errorNotInit(int n, String s) throws IEError {
    error(n, "Not initialize (" + s + ")");
  }

  void errorDivision0(int n, String s) throws IEError {
    error(n, "Divison par zero (" + s + ")");
  }

}

/**
 * los knos de a :- know1, know2, know3, son atómicos, con lo cual si objetivo se cumple en know1
 * se ejecutarán tdoos los demás
 *
 * Aparte de objetivo y curry falta algo más?
 */
