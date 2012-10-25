package ie;

/**
 * @author Aleix Fernández Donis
 * Cluster y Grupo C8G3
 * Creado el 5-May-2010
 *
 * Objetivo de la clase: comprobación de tipo
 */


/*
 * IESemantic comprueba que los tipos de un árbol sean correctos
 * además inicia una IESymTab con los ids del programa
 */


import java.util.Map;
import java.util.TreeMap;
import java.util.Iterator;
import dominio.TC;
import java.io.Serializable;

  /**
   * A lo largo del documento usaremos la siguientes convenciones para representar las pres de los árboles
   * También en algunos sitios en que se gane claridad se usarán expresiones regulares sencillas
   * para caracterizar las precondiciones
   * TT TEXT : TYPE         formato de los nodos
   * (TT)                   un nodo TT
   * (TT)k                  k nodos TT
   * (TT)+                  almenos un nodo TT
   * (TT)*                  cualquier número de nodos TT (posiblemente 0)
   * Donde TT pueden ser elementos de la gramática definida en ieParser
   * (terminales y no terminales)
   */


class IESemantic implements Serializable {
  /**
   * st   tabla de símbolos
   * err  0 indica si ha habido algún error
   *      > 0 ha habido algún error
   * log  guarda las incidencias del análisis semántico
   */

  IESymTab st;
  int err;
  String log;


  /**
   * Creadora
   */
  IESemantic(IESymTab symtab) {
    err = 0;
    st = symtab;
    log = "";
  }


  /**
   * getter del log
   */
  String getLog() {
    return log;
  }


  /**
   * función que devuelve true si no se ha detectado ningún error durante el
   * análisis semántico del programa
   * Esto principalmente quiere decir que los tipos son correctos y no se usan
   * identificadores no definidos, etc
   *
   * pre: AST es un árbol reconocido por el analizados sintáctico (parser)
   */
  boolean programCheck(IEAST a) {
    /**
     * AST a:
     * LIST program : error
     * | DDOT : : error
     * | | (LIST)                          globals
     * | DDOT : : error
     * | | (LIST)                          main
     * | (LIST)
     */

    if (!a.down().down().getText().equals("global")) {
      errorInvalidMod(a.getLine(), "global", a.down().down().getText());
    }
    if (!a.down().right().down().getText().equals("main")) {
      errorInvalidMod(a.getLine(), "main", a.down().right().down().getText());
    }

    IEAST b = a.down();
    while(b != null) {
      createMod(b.down().getText(), b.getLine());
      b.tp = TC.ti;
      b = b.right();
    }
    typeCheck(a);

    return err == 0;
  }


  /**
   * función que comprueba los tipos del árbol a
   * Hay que tener en cuenta que el analizador semántico se llama si y sólo si
   * el analizador sintáctivo ha tenido éxito
   * Además el analizador semántico intenta buscar todos los errores, por eso
   * cuando encuentra un error de tipos, pone en el nodo correspondiente el
   * tipo error, pero luego lo trata como si fuera correcto, para poder
   * encotnrar todos los errores de tipo, que es normalmente la estrategia
   * a seguir de todos los compiladores
   */
  private void typeCheck(IEAST a) {

    if (a == null) {
      /**
       * Si el árbol és nulo no hay nada que comprobar
       */
      return;
    }
    

    if (a.tt.equals("DDOT")) {
      /**
       * AST a:
       * DDOT : :
       * | ID id : modulo
       * | (LIST)
       *
       * modulo:
       *  creamos la variable módulo con createMod
       *  miramos que tanto el tipo de la variable módulo sea correcto (a.down())
       *  miramos que el tipo de los términos del módulo sean correctos (a.down().right())
       * si no hay errores en el identificador de módulo propagamos el tipo que tenga LIST
       */
      typeCheck(a.down().right());
      a.tp = a.down().right().tp;
    }
    else if (a.tt.equals("LIST")) {
      /**
       * AST a:
       * LIST id : error
       * | (TERM)*
       *
       * Ponemos b = true (no ha habido ningún error en ningun término de la lista)
       * Para todos los términos de la lista hacemos una comprobación, si error ponemos b a falso
       * Una vez acabados todos los términos si b sigue a cierto ponemos a ok
       * (por defecto y por pre tenemos tipo de LIST como error, por eso no hacemos nada cuando b es falso)
       */
      boolean b = true;
      for (IEAST t = a.down(); t != null; t = t.right()) {
        typeCheck(t);
        if (t.tp.equals(TC.te)) b = false;
      }
      if (b) {
        a.tp = TC.ok;
      }
    }
    else if (a.tt.equals("DDDOT")) {
      /**
       * declaración de var
       * AST a:
       * DDDOT :: : error
       *  | ID
       *  | type
       * miramos que down sea un id correcto: esto es que sea un id y no esté ya definidio
       * miramos que en type no haya nada indefinido como a :: * (no permitimos tipos no funciones polimórifcas)
       * por tanto algo como a :: * -> * sí es correcto.
       */
      if (!a.down().tt.equals("ID") || a.down().down() != null) {
        errorNotUndefinedId(a.getLine(), a.getText());
      }
      else if (st.find(a.down().getText())) {
        errorYetExistsId(a.getLine(), a.getText());
      }
      else {
        a.down().tp = decType(a.down().right());
        if (a.down().tp.someUndef()) {
          errorIncorrectType(a.getLine(), a.down().tp.getType());
        }
        createId(a.down().getText(), a.down().tp);
        a.tp = a.down().tp;
      }
    }
    else if (a.tt.equals("ID")) {
      /** 
       * Casos:
       * si no definidia: error
       * si definidia:
       *  si id no es func es trivial
       *  si id es func:
       *   si id es func constante la tratamos directamente typeándola con su retorno
       *   sino hacemos un matching con los parámetros
       *
       * !tratamos las funciones constantes diferentes porque tal como las hemos definido
       *  sin un parámetros hemos de subir el return al AST, en cambio las funcs con
       *  un parámetro hemos de subir gastando un argumento, no se pueden tratar igual
       *  y mejor discernir aquí, no tanto por eficiencia que es imperceptible sino
       *  por claridad y facilidad
       *
       */

      if (!st.find(a.getText())) {
        errorNonDefinedId(a.getLine(), a.getText());
      }
      else {
        a.tp = (st.get(a.getText())).tp;
        if (a.tp.func()) {
          if (a.tp.right == null) {
            //caso constante...
            if (a.down() != null) {
              errorNumParam(a.getLine(), a.getText());
            }
            a.tp = a.tp.ret;
          }
          else {
            match(a);
          }
        }
      }
    }
    else if (a.tt.equals("ASIG")) {
      /**
       * ASIG : error
       * | (expr)
       * | (expr)
       */

      typeCheck(a.down());
      typeCheck(a.down().right());

      if (!ref(a.down())) {
        errorNoReferenceable(a.getLine(), a.down().getText());
      }
      if (a.down().right().tp.func()) {
        errorPartialFunc(a.getLine(), a.down().right().tp.getType());
      }
      if (!a.down().tp.equals(a.down().right().tp) && !a.down().tp.equals(TC.te) && !a.down().right().tp.equals(TC.te)) {
        errorIncorrectAssigType(a.getLine(), a.down().getText(), a.down().tp.getType(), a.down().right().tp.getType());
      }
      a.tp = a.down().tp;
    }
    else if (a.tt.equals("MACRO")) {
      /**
       * mirar que: a.down().down() a.d.d.r,etc sean ids
       * y que el id del nombre del a macro esté definido
       */
      if (!a.down().tt.equals("ID")) {
        errorNotId(a.getLine(), a.down().getText());
      }
      else if (!st.find(a.down().getText())){
        errorNonDefinedId(a.getLine(), a.getText());
      }
      else {
        a.down().tp = st.get(a.down().getText()).tp;
        if (a.down().tp.right == null) {
          errorNoMacro(a.getLine(), a.down().getText());
        }
        else {
          macro(a);
        }
      }
    }
    else if (a.tt.equals("NORMAL") || a.tt.equals("PERSISTENT")) {
      /**
       * AST a:
       * (NORMAL|PERSISTENT) (:-|#-) : error
       * | LIST : error
       *
       * Evaluamos la expresión de la izquierda
       * Si no es booleana ni ya viene con error devolvemos un error
       * Le damos el tipo bool
       * (para seguir, y no parar sólo por un error sino mostrárlos todos)
       * Hacemos un typeCheck de la lista de hechos
       */
      typeCheck(a.down());
      if (!a.down().tp.equals(TC.tb) && !a.down().tp.equals(TC.te)) {
        errorNotBoolInRule(a.getLine(), a.down().tp.getType());
      }
      a.tp = TC.tb;
      typeCheck(a.down().right());
    }
    else if (a.tt.equals("PRINT") || a.tt.equals("ABORT")) {
      /**
       * Puede haber o no expresión
       */
      typeCheck(a.down());
      if (a.down() != null) {
          a.tp = a.down().tp;
      }
      else {
        a.tp = TC.ok;
      }
    }
    else if (a.tt.equals("INTCONST")) {
      a.tp = TC.ti;
    }
    else if (a.tt.equals("REALCONST")) {
      a.tp = TC.tr;
    }
    else if (a.tt.equals("STRING")) {
      a.tp = TC.ts;
    }
    else if (a.tt.equals("OR") || a.tt.equals("AND")) {
      typeCheck(a.down());
      typeCheck(a.down().right());
      if (!a.down().tp.equals(TC.tb) && !a.down().tp.equals(TC.te)) {
        errorIncompatibleOperator(a.getLine(), a.getText(), a.down().tp.getType());
      }
      if (!a.down().right().tp.equals(TC.tb) && !a.down().right().tp.equals(TC.te)) {
        errorIncompatibleOperator(a.getLine(), a.getText(), a.down().right().tp.getType());
      }
      a.tp = TC.tb;
    }
    else if (a.tt.equals("NOT")) {
      typeCheck(a.down());
      if (!a.down().tp.equals(TC.tb) && !a.down().tp.equals(TC.te)) {
        errorIncompatibleOperator(a.getLine(), a.getText(), a.down().tp.getType());
      }
      a.tp = TC.tb;
    }
    else if (a.tt.equals("EQUAL") || a.tt.equals("NOTEQUAL") || a.tt.equals("LESSEQUAL") ||
              a.tt.equals("GREATEQUAL") || a.tt.equals("LESS") || a.tt.equals("GREAT")) {
      typeCheck(a.down());
      typeCheck(a.down().right());
      if (!a.down().tp.equals(a.down().right().tp) && !comparable(a.down().tp) &&
          !a.down().tp.equals(TC.te) && !a.down().right().tp.equals(TC.te)) {
        errorIncompatibleComp(a.getLine(),  a.down().tp.getType(), a.down().right().tp.getType());
      }
      a.tp = TC.tb;
    }
    else if (a.tt.equals("PLUS") || (a.tt.equals("MINUS") && a.down().right() != null) ||
              a.tt.equals("MUL") || a.tt.equals("DIV")) {
      typeCheck(a.down());
      typeCheck(a.down().right());
      boolean b = true;
      if ((!a.down().tp.equals(TC.ti) && !a.down().tp.equals(TC.tr)) && !a.down().tp.equals(TC.te)) {
        b = false;
        errorIncompatibleOperator(a.getLine(), a.getText(), a.down().tp.getType());
      }
      if ((!a.down().right().tp.equals(TC.ti) && !a.down().right().tp.equals(TC.tr)) && !a.down().right().tp.equals(TC.te)) {
        b = false;
        errorIncompatibleOperator(a.getLine(), a.getText(), a.down().right().tp.getType());
      }
      if (a.down().tp.equals(a.down().right().tp)) {
        a.tp = a.down().tp;
      }
      else if (b && !a.down().tp.equals(TC.te) && !a.down().right().tp.equals(TC.te)) {
        errorIncompatibleOperator(a.getLine(), a.getText(), a.down().tp.getType(), a.down().right().tp.getType());
      }
    }
    else if (a.tt.equals("REST")) {
      typeCheck(a.down());
      typeCheck(a.down().right());
      if (!a.down().tp.equals(TC.ti) && !a.down().tp.equals(TC.te)) {
        errorIncompatibleOperator(a.getLine(), a.getText(), a.down().tp.getType());
      }
      if (!a.down().right().tp.equals(TC.ti) && !a.down().right().tp.equals(TC.te)) {
        errorIncompatibleOperator(a.getLine(), a.getText(), a.down().right().tp.getType());
      }
      a.tp = TC.ti;
    }
    else if (a.tt.equals("MINUS") && a.down().right() == null) {
      typeCheck(a.down());
      if ((!a.down().tp.equals(TC.ti) && !a.down().tp.equals(TC.tr)) && !a.down().tp.equals(TC.te)) {
        errorIncompatibleOperator(a.getLine(), a.getText(), a.down().right().tp.getType());
      }
      a.tp = a.down().tp;
    }
    else if (a.tt.equals("PLUSPLUS") || a.tt.equals("MINUSMINUS")) {
      typeCheck(a.down());
      typeCheck(a.down().right());
      if (!a.down().tp.type.equals("list") && !a.down().tp.equals(TC.te)) {
        errorIncompatibleOperator(a.getLine(), a.getText(), a.down().tp.getType());
      }
      if (!a.down().tp.type.equals("list") && !a.down().tp.equals(TC.te)) {
        errorIncompatibleOperator(a.getLine(), a.getText(), a.down().right().tp.getType());
      }
      if (!a.down().tp.equals(a.down().right().tp) && !a.down().tp.equals(TC.te) && !a.down().right().tp.equals(TC.te)) {
        errorIncompatibleOperator(a.getLine(),  a.getText(), a.down().tp.getType(), a.down().right().tp.getType());
      }
      a.tp = a.down().tp;
    }
    else if (a.tt.equals("SHARP")) {
      typeCheck(a.down());
      if (!a.down().tp.type.equals("list") && !a.down().tp.equals(TC.te)) {
        errorIncompatibleOperator(a.getLine(), a.getText(), a.down().right().tp.getType());
      }
      a.tp = TC.ti;
    }
    else if (a.tt.equals("INDEX")) {
      /**
       * INDEX  : error
       * | (expr) id : error
       * | (expr) id : error
       * Primero hacemos un typecheck de down i down.right
       * Si down.right no es un entero ni error sacamos un error
       * damos a la raiz el tipo de la list
       */
      typeCheck(a.down());
      typeCheck(a.down().right());
      if (!a.down().right().tp.equals(TC.ti) && !a.down().right().tp.equals(TC.te)) {
        errorIncompatibleOperator(a.getLine(), a.getText(), a.down().right().tp.getType());
      }

      if (a.down().tp.type.equals("list")) {
        a.tp = a.down().tp.ret;
      }
      else if (a.down().tp.type.equals("tuple")) {
        if (!a.down().right().tt.equals("INTCONST")) {
          errorNotIntCosnt(a.getLine(), a.down().right().getText());
        }
        else {
          IEType b = a.down().tp.ret;
          int n = Integer.parseInt(a.down().right().getText());
          for (int i = 0; b != null && i < n; ++i, b = b.tuple);
          if (b == null) {
            errorNotExistsTupleElement(a.getLine(), a.down().getText(), n);
          }
          else {
            a.tp = b;
          }
        }
      }
      else if (!a.down().tp.equals(TC.te)){
        errorIncompatibleOperator(a.getLine(), a.getText(), a.down().tp.getType());
      }
    }
    else if (a.tt.equals("OSB")) {
      /**
       * AST a
       * OSB [ : error
       * | (expr)*
       *
       * Primero de todo miramos si down es null, entonces es la list vacía
       * Evidentemente no podemos inferir ningún tipo de la list con lo cual
       * le damos el tipo [*] para que sea *-unificable
       * Si existe down, hacemos el check del down, le damos ese tipo al AST a
       * y vamos recorriendo todos los hijos del AST a mirando que sean del
       * tipo del AST(o sea, del primer elemento, o sea que todos tengan el
       * mismo tipo), si alguno no lo es damos error
       */

      if (a.down() == null) {
        a.tp = TC.tlu;
      }
      else {
        typeCheck(a.down());
        a.tp = new IEType(a.down().tp);
        for (IEAST p = a.down().right(); p != null; p = p.right()) {
          typeCheck(p);
          if (!p.tp.equals(TC.te) && !p.tp.equals(a.tp.ret)) {
            errorIncompatibleOperator(a.getLine(), a.getText(), a.tp.ret.getType(), p.tp.getType());
          }
          a.tp.ret = maxDefined(a.tp.ret, p.tp);
        }
      }
    }
    else if (a.tt.equals("OCURLY")) {
      /**
       * AST a
       * OCURLY { : error
       * | (expr)
       * | (expr)
       * | (expr)*
       *
       * Las tuplas almenos tienen dos elementos, como vemos en la pre del AST a
       * Simplemente recorreremos los hijos construyendo el tipo de A
       */

      a.tp = new IEType("tuple");
      IEType t = a.tp;
      IEAST p = a.down();
      typeCheck(p);
      t.ret = p.tp.copy();
      for (t = t.ret, p = p.right(); p != null; p = p.right(), t = t.tuple) {
        typeCheck(p);
        t.tuple = p.tp.copy();
      }
    }
    else {
      /**
       * Por pre del análisis léxico nunca llegará aquí
       */
      errorInvalidToken(a.getLine(), a.getTt());
    }
  }

  /**
   * Devuelve verdadero si a es referenciable
   * Los tipos referenciables se definen recursivamente
   *  Un ID es refenciable
   *  Si R es referenciable entonces INDEX int R es referenciable
   *  (siempre y cuando R sea una lista, o una tupla i el int es un inconst)
   * Esta última condición no la comprueba esta función
   *
   * pre: A es un árbol generado por <expr>
   */
  boolean ref(IEAST a) {
    if (a.tt.equals("ID")) {
      return true;
    }
    else if (a.tt.equals("INDEX")) {
      return ref(a.down());
    }
    return false;
  }

  /**
   * función que nos devuelve si dos elementos son comparables
   * de momento sólo tipos básicos son comparables, he añadido compuestos
   * para funciones el problema es indecible...
   */
  private boolean comparable(IEType t) {
    if (t.equals(TC.tr) || t.equals(TC.tb)) {
      return true;
    }
    else if (t.type.equals("list")) {
      return comparable(t.ret);
    }
    else if (t.type.equals("tuple")) {
      for(IEType i = t.ret; i != null; i = i.tuple) {
        if (!comparable(i)) {
          return false;
        }
      }
      return true;
    }
    return false;
  }


  /**
   * creamos id como módulo
   * Casuística:
   *  · si id no está definido: añadimos el mod
   *  · sino, i el id está definidio:
   *   · el id tiene tipo int:
   *    · si el id ya es un módulo: damos error
   *          (el usuario ha creado dos módulos con el mismo nombre)
   *    · sino promocionamos el id a módulo
   *          (esto pasa si por ejemplo definimos su valor en globals, etc)
   *   · sino damos error
   */
  private void createMod(String id, int n) {
    if (!st.find(id)) {
      IEValue v = new IEValue(TC.ti);
      v.i = 1;
      v.mod = true;
      st.createId(id, v);
    }
    else {
      errorRepeatMod(n, id);
    }
  }

  /**
   * crea un id en la tabla de símbolos
   * inicia con init = false
   */
  private void createId(String id, IEType t) {
    IEValue v = new IEValue(t);
    v.init = false;
    st.createId(id, v);
  }


  /**
   * A es una declaración de tipo
   * mira que sea correcta y devuelve el tipo declarado
   */
  IEType decType(IEAST a) {
    IEType t;
    if (a.tt.equals("APP")) {
      t = new IEType("idmacro", decType(a.down()), decType(a.down().right()));
    }
    else if (a.tt.equals("OSB")) {
      t = new IEType(decType(a.down()));
    }
    else if (a.tt.equals("OCURLY")) {
      t = new IEType("tuple"); 
      IEAST b = a.down();
      t.ret = decType(b).copy();
      IEType tt = t.ret;
      while (b.right() != null) {
        b = b.right();
        tt.tuple = decType(b).copy();
        tt = tt.tuple;
      }
    }
    else if (a.getText().equals("int")) {
      t = TC.ti;
    }
    else if (a.getText().equals("real")) {
      t = TC.tr;
    }
    else if (a.getText().equals("bool")) {
      t = TC.tb;
    }
    else if (a.tt.equals("MUL") || a.tt.equals("UNIF2")) {
      t = new IEType(a.getText()+"_");
    }
    else {
      errorIncorrectType(a.getLine(), a.getText());
      t = TC.te;
    }
    return t;
  }


  /**
   * Hace match de una función con sus parámetros
   * AST a
   * ID id : error
   * (expr)+ : error
   * intenta unificar id con sus parámetros
   */
  void match(IEAST a) {
    a.tp = a.tp.copy();
    IEType t = a.tp;
    IEAST p = a.down();

    //se pasa como param...
    if (p == null) {
      return;
    }

    while (p != null && t != null) {
      p = p.right();
      t = t.ret;
    }
    if (p != null || t == null || t.right != null) {
      errorNumParam(a.getLine(), a.getText());
      return;
    }

    t = a.tp.right;
    p = a.down();
    Map<String, IEType> ut = new TreeMap<String, IEType>();
    for(int i = 1; p != null && t != null; ++i, t = a.tp.right, p = p.right()) {
      typeCheck(p);
      if (!p.tp.equals(t)) {
        errorIncompatibleParam(a.getLine(), a.getText(), i, t.getType(), p.tp.getType());
      }
      else {
        unificate(t, p.tp, ut, a.getLine(), a.getText(), i);
      }
      a.tp = a.tp.ret;
    }
    rewriteRes(a.tp, ut);

    //reescriptura de los tipos de param
    for(int i = 1; i < maxParam(a.down()) ; ++i) {
      String s = undef(i) + "_";
      if (ut.containsKey(s)) {
        IEType tt = ut.get(s);
        while (ut.containsKey(tt.type)) {
          tt = ut.get(tt.type);
        }
        if (tt.undef() == 0) {
          errorIncompatibleParam(a.getLine(), a.getText(), -1, tt.getType(), s);
        }
      }
    }
  }

  /**
   * devuelve el máximo parámetro definido en el árbol
   */
  int maxParam(IEAST b) {
    int m = 0;
    if (b == null) {
      return m;
    }

    if (b.tt.equals("ID")) {
      if (b.getText().charAt(0) == '_') {
        m = b.tp.getType().length() -1;
      }
    }
    m = Math.max(maxParam(b.down()), m);
    return Math.max(maxParam(b.right()), m);
  }
  
  /**
   * devuelve (*)^i con entrada i
   */
  String undef(int n) {
    String s = "";
    for(int i = 0; i < n; ++i) {
      s += "*";
    }
    return s;
  }


  //simplemente rewrite con lo que toca...
  void rewriteRes(IEType t, Map<String, IEType> ut) {
    if (t == null) {
      return;
    }
    if (t.undef() > 0) {
      while (ut.containsKey(t.type)) {
        t.copy(ut.get(t.type));
      }
    }
    else {
      rewriteRes(t.ret, ut);
      rewriteRes(t.tuple, ut);
      rewriteRes(t.right, ut);
    }
  }


  //el de la derecha no puede ser menos definido...
  //caso especial de []
  void unificate(IEType i, IEType d, Map<String, IEType> ut,int n, String s, int j) {
    while (ut.containsKey(i.type)) {
      i = ut.get(i.type);
    }
    while (ut.containsKey(d.type)) {
      d = ut.get(d.type);
    }
    if (i.undef() > 0 && i.type.equals(d.type)) {
      return;
    }

    if (i.undef() > 0) {
      ut.put(i.type, d);
    }
    else if (d.undef() > 0) {
      errorIncompatibleParam(n, s, j, i.getType(), d.getType());
    }
    else if (i.func()) {
      unificate(i.right, d.right, ut, n, s, j);
      unificate(i.ret, d.ret, ut, n, s, j);
    }
    else if (i.type.equals("list")) {
      //si d = [*] cortamos
      if (d.ret.type.charAt(0) != '*') {
        unificate(i.ret, d.ret, ut, n, s, j);
      }
    }
    else if (i.type.equals("tuple")) {
      unificate(i.ret, d.ret, ut, n, s, j);
    }
    else if (!i.equals(d)) {
      errorIncompatibleParam(n, s, j, i.getType(), d.getType());
    }
    if (i.right != null) {
      unificate(i.right, d.right, ut, n, s, j);
    }
  }


  /**
   * se necesita para [[], [[]]].
   */
  IEType maxDefined(IEType a, IEType b) { 
    IEType t = new IEType();
    if (a.undef() > 0) {
      t.copy(b);
    }      
    else if (b.undef() > 0) {
      t.copy(a); 
    }      
    else if (a.type.equals("list")) {
      t.type = "list";
      t.ret = maxDefined(a.ret, b.ret);
    }      
    else if (a.type.equals("tuple")) {
      t.copy(a); 
      IEType tt = t.ret;
      for(a = a.ret, b = b.ret; a != null && b != null; a = a.tuple, b = b.tuple, tt = tt.tuple) {
        t = maxDefined(a, b);
      }
    }
    else if (a.func()) {
      t.copy(a);
      t.right = maxDefined(a.right, b.right);
      t.ret = maxDefined(a.ret, b.ret);
    }
    else {
      t.copy(a);
    }      
    return t;
  }


  /**
   * crea una macro
   */
  void macro(IEAST a) {
    IEType t = a.down().tp;
    IEAST b = a.down().down();
    Map<String, IEAST> pars = new TreeMap<String, IEAST>();
    for(int i = 0; b != null && t != null; ++i, t = t.ret, b = b.right()) {
      if (pars.containsKey(b.getText())) {
        errorNameParam(a.getLine(), a.down().getText());
      }
      b.tp = t.right;
      pars.put(b.getText(), b);
      b.setText("_" + i);
      createId(b.getText(), b.tp);
    }
    if(b != null || t == null || t.right != null) {
      errorNumParam(a.getLine(), a.getText());
      return;
    }
    simpleRewrite(a.down().right(), pars);

    typeCheck(a.down().right());

    if (!t.exactlyEquals(a.down().right().tp)) {
      errorTypeNotEquals(a.getLine(), a.down().getText(), t.getType(), a.down().right().tp.getType());
    }

    IEValue v = new IEValue(a.down().tp);
    st.createId(a.down().getText(), v);
    a.tp = a.down().tp;
  }

  void simpleRewrite(IEAST a, Map<String, IEAST> r) {
    if (a == null) {
      return;
    }
    if (r.containsKey(a.getText())) {
      IEAST b = r.get(a.getText());
      a.setText(b.getText());
      a.tp = b.tp;
    }
    simpleRewrite(a.down(), r);
    simpleRewrite(a.right(), r);
  }


  /**
   * Errores
   */
  private void error(int n, String s) {
    err = 1;
    log += "[semantic] Error in line " + n + ": " + s + "\n";
  }

  private void errorInvalidToken(int n, String s) {
    error(n, "Invalid token (" + s + ")");
  }

  private void errorInvalidMod(int n, String m, String b) {
    error(n, "Invalid module. Must be " + m + " (" + b + ")");
  }

  private void errorNonDefinedId(int n, String s) {
    error(n, "Id not defined (" + s + ")");
  }
  
  private void errorNonDefinedIdPara(int n, String s) {
    error(n, "Id param not defined (" + s + ")");
  }
  
  private void errorRepeatMod(int n, String s) {
    error(n, "Repeat Id for module (" + s + ")");
  }

  private void errorIncorrectModType(int n, String s, String t) {
    error(n, "Incorrect type for module (" + s + " : " + t + ")");
  }

  private void errorIncorrectAssigType(int n, String id, String tpO, String tpD) {
    error(n, "Incorrect type asig (" + id + " : " + tpO + " <- " + tpD + ")");
  }

  private void errorNotBoolInRule(int n, String t) {
    error(n, "Not boolean expresion in rule-left (" + t + ")");
  }

  private void errorIncompatibleOperator(int n, String o, String t0, String t1) {
    error(n, "Incompatible type with operator " + o + " (" + t0 + ", " + t1 + ")");
  }
  
  private void errorIncompatibleOperator(int n, String o, String t) {
    error(n, "Incompatible type with operator " + o + " (" + t + ")");
  }
  
  private void errorIncompatibleComp(int n, String t0, String t1) {
    error(n, "Incompatible type comparation (" + t0 + ", " + t1 + ")");
  }

  private void errorNumParam(int n, String s) {
    error(n, "The number of parametres in the call do not match (" + s + ")");
  }

  private void errorIncompatibleParam(int n, String s, int p, String t0, String t1) {
    error(n, "Incompatible types in parameter " + p + " of " + s + " (" + t0 + ", " + t1 + ")");
  }

  private void errorInvalidMacro(int n, String s) {
    error(n, "Define a invalid param for macro (" + s + ")");
  }

  private void errorTypeNotUni(int n, String s, String t0, String t1) {
    error(n, "Not unificable in expresion " + s + " (" + t0 + ", " + t1 + ")");
  }

  private void errorTypeNotEquals(int n, String s, String t0, String t1) {
    error(n, "Type not equals in macro " + s + " (" + t0 + ", " + t1 + ")");
  }

  private void errorNoReferenceable(int n, String s) {
    error(n, "Left expresion in a rule must be referenceable (" + s + ")");
  }

  private void errorNoMacro(int n, String s) {
    error(n, s + " is not a valid macro id");
  }

  private void errorPartialFunc(int n, String s) {
    error(n, "Can't assign a partial match function (" + s + ")");
  }

  private void errorNotIntCosnt(int n, String s) {
    error(n, "Operator t @ i over t : tuple, i must bé a numeric constant (" + s + ")");
  }

  private void errorNotExistsTupleElement(int n, String s, int i) {
    error(n, "Element " + i + " not exists in tuple " + s);
  }
  
  private void errorNotUnificable(int n, String t0, String t1) {
    error(n, "Not unificable types (" + t0 + ", " + t1 + ")");
  }

  private void errorYetExistsId(int n, String s) {
    error(n, "Id yet defined (" + s + ")");
  }

  private void errorNotUndefinedId(int n, String s) {
    error(n, "Id " + s + " not undefined or not id");
  }

  private void errorIncorrectType(int n, String s) {
    error(n, "Incorrect type (" + s + ")");
  }

  private void errorNotId(int n, String s) {
    error(n, "Not Id (" + s + ")");
  }

  private void errorNameParam(int n, String s) {
    error(n, "Repeat name param (" + s +")");
  }
}
