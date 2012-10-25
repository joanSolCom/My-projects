package ie;

/**
 * @author Aleix Fernández Donis
 * Cluster y Grupo C8G3
 * Creado el 5-May-2010
 *
 * Objetivo de la clase: Definir las primitivas
 */


/*
 * Clase que implementa las primitivas del lenguaje
 * Todas las primitivas tienen un nombre y un IEValue con el campo idapp != null
 * Por herencia se puede modificar function añadiendo nuevas primitivas segun
 * las necesidades del usuario de IEngine
 */





import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.ArrayList;
import java.lang.Math;
import java.util.Random;
import java.util.Date;
import dominio.TC;
import java.io.Serializable;

class IEFunction implements Serializable{
  /*
   * function lista de funciones, se puede expandir mediante herencia
   * in     para evaluar una función muchas veces nos hace falta evaluar un ast
   *        porque aceptamos funciones de orden superior, como por ejemplo:
   *        map m [1,2,3] siendo m una macro
   * rand   para primitivas que devuelven elementos aleatorios
   */

  static List<IEPair<String, IEValue>> function;
  static IEInterpret in;
  static Random rand;


  /**
   * Inicializa IEFunc
   */
  IEFunction(IEInterpret interpret) {
    in = interpret;

    Date date = new Date();
    rand = new Random(date.getTime());

    function = new ArrayList<IEPair<String, IEValue>>(45);

    String name;
    IEValue value;


    /**
     * funciones de conversión y manejo de enteros y reales
     */

    /**
     * int          ::  real -> int
     *              ::  hace un casting de real a entero
     */
    name = "int";
    value = new IEValue(new IEType("idapp", TC.tr, TC.ti));
    value.app = new IEAbstractFunction() {
      public IEValue app(int n, IEValue[] args) {
        IEValue v = new IEValue(TC.ti);
        v.i = castr2i(args[0].r);
        return v;
      }
    };
    function.add(new IEPair(name, value));

    /**
     * real         ::  int -> real
     *              ::  hace un casting de entero a real
     */
    name = "real";
    value = new IEValue(new IEType("idapp", TC.ti, TC.tr));
    value.app = new IEAbstractFunction() {
      public IEValue app(int n, IEValue[] args) {
        IEValue v = new IEValue(TC.tr);
        v.r = casti2r(args[0].i);
        return v;
      }
    };
    function.add(new IEPair(name, value));

    /**
     * floor        ::  real -> int
     *              ::  redondea un real a int por defecto
     */
    name = "floor";
    value = new IEValue(new IEType("idapp", TC.tr, TC.ti));
    value.app = new IEAbstractFunction() {
      public IEValue app(int n, IEValue[] args) {
        IEValue v = new IEValue(TC.ti);
        v.i = floor(args[0].r);
        return v;
      }
    };
    function.add(new IEPair(name, value));

    /**
     * ceil         ::  real -> int
     *              ::  redondea un real a int por exceso
     */
    name = "ceil";
    value = new IEValue(new IEType("idapp", TC.tr, TC.ti));
    value.app = new IEAbstractFunction() {
      public IEValue app(int n, IEValue[] args) {
        IEValue v = new IEValue(TC.ti);
        v.i = ceil(args[0].r);
        return v;
      }
    };
    function.add(new IEPair(name, value));

    /**
     * frac         ::  real -> real
     *              ::  devuelve la parte fraccionaro de un real
     */
    name = "frac";
    value = new IEValue(new IEType("idapp", TC.tr, TC.tr));
    value.app = new IEAbstractFunction() {
      public IEValue app(int n, IEValue[] args) {
        IEValue v = new IEValue(TC.tr);
        v.r = frac(args[0].r);
        return v;
      }
    };
    function.add(new IEPair(name, value));


    /**
     * misceláneo de funciones 
     */

    /**
     * constie      :: int
     *              :: función constante (sirve como muestra de ejemplo)
     */
    name = "constie";
    value = new IEValue(new IEType("idapp", null, TC.ti));
    value.app = new IEAbstractFunction() {
      public IEValue app(int n, IEValue[] args) {
        IEValue v = new IEValue(TC.ti);
        v.i = constie();
        return v;
      }
    };
    function.add(new IEPair(name, value));


    /**
     * funciones random
     */

    /**
     * rand         ::  real
     *              ::  devuelve un valor aleatorio entre 0.0 y 1.0
     */
    name = "random";
    value = new IEValue(new IEType("idapp", null, TC.tr));
    value.app = new IEAbstractFunction() {
      public IEValue app(int n, IEValue[] args) {
        IEValue v = new IEValue(TC.tr);
        v.r = random();
        return v;
      }
    };
    function.add(new IEPair(name, value));

    /**
     * intrandom      ::  int
     *              ::  genera un entero aleatoriamente
     */
    name = "intrandom";
    value = new IEValue(new IEType("idapp", null, TC.ti));
    value.app = new IEAbstractFunction() {
      public IEValue app(int n, IEValue[] args) {
        IEValue v = new IEValue(TC.ti);
        v.i = intrandom();
        return v;
      }
    };
    function.add(new IEPair(name, value));

    /**
     * boolrandom     ::  bool
     *              ::  genera un booleano aleatoriamente
     */
    name = "boolrandom";
    value = new IEValue(new IEType("idapp", null, TC.tb));
    value.app = new IEAbstractFunction() {
      public IEValue app(int n, IEValue[] args) {
        IEValue v = new IEValue(TC.tb);
        v.b = boolrandom();
        return v;
      }
    };
    function.add(new IEPair(name, value));


    /**
     * funciones booleanas sobre listas
     */

    /**
     * and          ::  [bool] -> bool
     */
    name = "and";
    value = new IEValue(new IEType("idapp", TC.tlb, TC.tb));
    value.app = new IEAbstractFunction() {
      public IEValue app(int n, IEValue[] args) {
        IEValue v = new IEValue(TC.tb);
        v.b = and(args[0].l);
        return v;
      }
    };
    function.add(new IEPair(name, value));

    /**
     * or           ::  [bool] -> bool
     */
    name = "or";
    value = new IEValue(new IEType("idapp", TC.tlb, TC.tb));
    value.app = new IEAbstractFunction() {
      public IEValue app(int n, IEValue[] args) {
        IEValue v = new IEValue(TC.tb);
        v.b = or(args[0].l);
        return v;
      }
    };
    function.add(new IEPair(name, value));


    /**
     * funciones polimórficas
     */

    /**
     * id           ::  * -> *
     *              :: función identidad
     */
    name = "id";
    value = new IEValue(new IEType("idapp", TC.tu1, TC.tu1));
    value.app = new IEAbstractFunction() {
      public IEValue app(int n, IEValue[] args) {
        return id(args[0]);
      }
    };
    function.add(new IEPair(name, value));

    /**
     * const        ::  * -> ** -> *
     *              :: crea una función constante que acepta un parámetro
     *             de tipo ** y devuelve un *
     *             Ejemplo: (const 3) es (* -> int) y siempre devuelve 3
     */
    name = "const";
    value = new IEValue(new IEType("idapp", TC.tu1, new IEType("idapp", TC.tu2, TC.tu1)));
    value.app = new IEAbstractFunction() {
      public IEValue app(int n, IEValue[] args) {
        return id(args[0]);
      }
    };
    function.add(new IEPair(name, value));

    /**
     * converse     ::  (* -> ** -> ***) -> ** -> * -> ***
     *              :: converse es un combinador para invertir el ordre de aplicación
     *             de los argumentos por una funció de dos arguments
     *             És útil para curryficar
     */
    name = "converse";
    value = new IEValue(new IEType("idapp", new IEType("idapp", TC.tu1, new IEType("idapp", TC.tu2, TC.tu3)), new IEType("idapp", TC.tu2, new IEType("idapp", TC.tu1, TC.tu3))));
    value.app = new IEAbstractFunction() {
      public IEValue app(int n, IEValue[] args) throws IEError {
        return converse(n, args[0], args[1], args[2]);
      }
    };
    function.add(new IEPair(name, value));

    /**
     * until      ::  (* -> bool) -> (* -> *) -> * -> *
     *            :: mientras el primer predicado sea cierto va aplicando la función
     *           al resultado, y volviendo a aplicar...
     */
    name = "until";
    value = new IEValue(new IEType("idapp", new IEType("idapp", TC.tu1, TC.tb), new IEType("idapp", new IEType("idapp", TC.tu1, TC.tu1), new IEType("idapp", TC.tu1, TC.tu1))));
    value.app = new IEAbstractFunction() {
      public IEValue app(int n, IEValue[] args) throws IEError {
        return until(n, args[0], args[1], args[2]);
      }
    };
    function.add(new IEPair(name, value));

    /**
     * ifelse     ::  bool -> * -> *
     */
    name = "ifelse";
    value = new IEValue(new IEType("idapp", TC.tb, new IEType("idapp", TC.tu1, new IEType("idapp", TC.tu1, TC.tu1))));
    value.app = new IEAbstractFunction() {
      public IEValue app(int n, IEValue[] args) {
        return ifelse(args[0], args[1], args[2]);
      }
    };
    function.add(new IEPair(name, value));

    /**
     * funciones de listas
     */

    /**
     * hd         ::  [*] -> *
     *            ::  devuelve el elemento de la cabeza de una lista
     */
    name = "hd";
    value = new IEValue(new IEType("idapp", TC.tlu1, TC.tu1));
    value.app = new IEAbstractFunction() {
      public IEValue app(int n, IEValue[] args) throws IEError {
        return hd(n, args[0].l);
      }
    };
    function.add(new IEPair(name, value));

    /**
     * tl         ::  [*] -> [*]
     *            ::  devuelve la cola de una lista
     */
    name = "tl";
    value = new IEValue(new IEType("idapp", TC.tlu1, TC.tlu1));
    value.app = new IEAbstractFunction() {
      public IEValue app(int n, IEValue[] args) throws IEError {
        IEValue v = new IEValue();
        v.l = tl(n, args[0].l);
        return v;
      }
    };
    function.add(new IEPair(name, value));

    /**
     * init       ::  [*] -> [*]
     *            ::  devuelve la lista menos el último elemento de la cola
     */
    name = "init";
    value = new IEValue(new IEType("idapp", TC.tlu1, TC.tlu1));
    value.app = new IEAbstractFunction() {
      public IEValue app(int n, IEValue[] args) throws IEError {
        IEValue v = new IEValue();
        v.l = init(n, args[0].l);
        return v;
      }
    };
    function.add(new IEPair(name, value));

    /**
     * last       ::  [*] -> *
     *            ::  devuelve el último elemento de la lista
     */
    name = "last";
    value = new IEValue(new IEType("idapp", TC.tlu1, TC.tu1));
    value.app = new IEAbstractFunction() {
      public IEValue app(int n, IEValue[] args) throws IEError {
        return last(n, args[0].l);
      }
    };
    function.add(new IEPair(name, value));

    /**
     * prefix       ::  * -> [*] -> [*]
     *              ::  añade un elemento al principio de una lista
     */
    name = "prefix";
    value = new IEValue(new IEType("idapp", TC.tu1, new IEType("idapp", TC.tlu1, TC.tlu1)));
    value.app = new IEAbstractFunction() {
      public IEValue app(int n, IEValue[] args) {
        IEValue v = new IEValue();
        v.l = prefix(args[0], args[1].l);
        return v;
      }
    };
    function.add(new IEPair(name, value));

    /**
     * postfix      ::  * -> [*] -> [*]
     *              ::  añade un elemento al final de la lista
     */
    name = "postfix";
    value = new IEValue(new IEType("idapp", TC.tu1, new IEType("idapp", TC.tlu1, TC.tlu1)));
    value.app = new IEAbstractFunction() {
      public IEValue app(int n, IEValue[] args) {
        IEValue v = new IEValue();
        v.l = postfix(args[0], args[1].l);
        return v;
      }
    };
    function.add(new IEPair(name, value));

    /**
     * take         ::  int -> [*] -> [*]
     *              ::  coge los primeros n elementos de una lista
     *              como miranda {...-2,-1,0} = []
     *              > size = todo...
     */
    name = "take";
    value = new IEValue(new IEType("idapp", TC.ti, new IEType("idapp", TC.tlu1, TC.tlu1)));
    value.app = new IEAbstractFunction() {
      public IEValue app(int n, IEValue[] args) {
        IEValue v = new IEValue();
        v.l = take(args[0].i, args[1].l);
        return v;
      }
    };
    function.add(new IEPair(name, value));

    /**
     * drop         :: int -> [*] -> [*]
     *              :: elimina los primeros n elementos de una lista
     */
    name = "drop";
    value = new IEValue(new IEType("idapp", TC.ti, new IEType("idapp", TC.tlu1, TC.tlu1)));
    value.app = new IEAbstractFunction() {
      public IEValue app(int n, IEValue[] args) {
        IEValue v = new IEValue();
        v.l = drop(args[0].i, args[1].l);
        return v;
      }
    };
    function.add(new IEPair(name, value));

    /**
     * reverse      ::  [*] -> [*]
     *              ::  devuelve la lista en orden inverso
     */
    name = "reverse";
    value = new IEValue(new IEType("idapp", TC.tlu1, TC.tlu1));
    value.app = new IEAbstractFunction() {
      public IEValue app(int n, IEValue[] args) {
        IEValue v = new IEValue();
        v.l = reverse(args[0].l);
        return v;
      }
    };
    function.add(new IEPair(name, value));

    /**
     * member       ::  *-> [*] -> bool
     *              ::  devuelve true si i sólo si el elemento está en la lista
     */
    name = "member";
    value = new IEValue(new IEType("idapp", TC.tu1, new IEType("idapp", TC.tlu1, TC.tb)));
    value.app = new IEAbstractFunction() {
      public IEValue app(int n, IEValue[] args) {
        IEValue v = new IEValue(TC.tb);
        v.b = member(args[0], args[1].l);
        return v;
      }
    };
    function.add(new IEPair(name, value));

    /**
     * limit        ::  [*] -> *
     *              ::  devuelve el primer elemento que se repita
     *              si no hay el último
     */
    name = "limit";
    value = new IEValue(new IEType("idapp", TC.tlu1, TC.tu1));
    value.app = new IEAbstractFunction() {
      public IEValue app(int n, IEValue[] args) throws IEError {
        return limit(n, args[0].l);
      }
    };
    function.add(new IEPair(name, value));

    /**
     * concat       :: [[*]] -> [*]
     *              :: devuelve la concatenación de las listas de una lista
     */
    name = "concat";
    value = new IEValue(new IEType("idapp", new IEType(TC.tlu1), TC.tlu1));
    value.app = new IEAbstractFunction() {
      public IEValue app(int n, IEValue[] args) {
        IEValue v = new IEValue();
        v.l = concat(args[0].l);
        return v;
      }
    };
    function.add(new IEPair(name, value));

    /**
     * mkset        ::  [*] -> [*]
     *              ::  elimina los elementos repetidos (deja el primero que encuentre)
     */
    name = "mkset";
    value = new IEValue(new IEType("idapp", TC.tlu1, TC.tlu1));
    value.app = new IEAbstractFunction() {
      public IEValue app(int n, IEValue[] args) {
        IEValue v = new IEValue();
        v.l = mkset(args[0].l);
        return v;
      }
    };
    function.add(new IEPair(name, value));

    /**
     * repeat       ::  int -> * -> [*]
     *              ::  devuelve una lista donde se repiten el elemento n veces
     */
    name = "repeat";
    value = new IEValue(new IEType("idapp", TC.ti, new IEType("idapp", TC.tu1, TC.tlu1)));
    value.app = new IEAbstractFunction() {
      public IEValue app(int n, IEValue[] args) {
        IEValue v = new IEValue();
        v.l = repeat(args[0].i, args[1]);
        return v;
      }
    };
    function.add(new IEPair(name, value));

    /**
     * subpre       ::  [*] -> [[*]]
     *              ::  devuelve prefijos..
     */
    name = "subpre";
    value = new IEValue(new IEType("idapp", TC.tlu1, new IEType(TC.tlu1)));
    value.app = new IEAbstractFunction() {
      public IEValue app(int n, IEValue[] args) {
        IEValue v = new IEValue();
        v.l = subpre(args[0]);
        return v;
      }
    };
    function.add(new IEPair(name, value));


    /**
     * funciones de orden superior sobre listas
     */

    /**
     * iterate      ::  (* -> *) -> * -> int -> [*]
     *              ::  itera n veces al valor la función, y devuelve la lista
     *              con las succesivas iteraciones
     */
    name = "iterate";
    value = new IEValue(new IEType("idapp", new IEType("idapp", TC.tu1, TC.tu1), new IEType("idapp", TC.tu1, new IEType("idapp", TC.ti, TC.tlu1))));
    value.app = new IEAbstractFunction() {
      public IEValue app(int n, IEValue[] args) throws IEError {
        IEValue v = new IEValue();
        v.l = iterate(n, args[0], args[1], args[2].i);
        return v;
      }
    };
    function.add(new IEPair(name, value));
   
    /**
     * takewhile    :: (* -> bool) -> [*] -> [*]
     *              :: devuelve el prefijo de la lista maximal tal que todos
     *                 los elementos cumplen el predicado
     */
    name = "takewhile";
    value = new IEValue(new IEType("idapp", new IEType("idapp", TC.tu1, TC.tb), new IEType("idapp", TC.tlu1, TC.tlu1)));
    value.app = new IEAbstractFunction() {
      public IEValue app(int n, IEValue[] args) throws IEError {
        IEValue v = new IEValue();
        v.l = takewhile(n, args[0], args[1].l);
        return v;
      }
    };
    function.add(new IEPair(name, value));

    /**
     * dropwhile    :: (* -> bool) -> [*] -> [*]
     *              :: elimina el prefijo maximal tal que todos los elementos
     *                 cumplen el predicado
     */
    name = "dropwhile";
    value = new IEValue(new IEType("idapp", new IEType("idapp", TC.tu1, TC.tb), new IEType("idapp", TC.tlu1, TC.tlu1)));
    value.app = new IEAbstractFunction() {
      public IEValue app(int n, IEValue[] args) throws IEError {
        IEValue v = new IEValue();
        v.l = dropwhile(n, args[0], args[1].l);
        return v;
      }
    };
    function.add(new IEPair(name, value));

    /**
     * filter       :: (* -> bool) -> [*] -> [*]
     *              :: filtra los elementos que no cumplen el predicado
     */
    name = "filter";
    value = new IEValue(new IEType("idapp", new IEType("idapp", TC.tu1, TC.tb), new IEType("idapp", TC.tlu1, TC.tlu1)));
    value.app = new IEAbstractFunction() {
      public IEValue app(int n, IEValue[] args) throws IEError {
        IEValue v = new IEValue();
        v.l = filter(n, args[0], args[1].l);
        return v;
      }
    };
    function.add(new IEPair(name, value));

    /**
     * foldl        :: (* -> ** -> *) -> * -> [**] -> *
     */
    name = "foldl";
    value = new IEValue(new IEType("idapp", new IEType("idapp", TC.tu1, new IEType("idapp", TC.tu2, TC.tu1)), new IEType("idapp", TC.tu1, new IEType("idapp", TC.tlu2, TC.tu1))));
    value.app = new IEAbstractFunction() {
      public IEValue app(int n, IEValue[] args) throws IEError {
        return foldl(n, args[0], args[1], args[2].l);
      }
    };
    function.add(new IEPair(name, value));

    /**
     * foldl1       :: (* -> * -> *) -> [*] -> *
     */
    name = "foldl1";
    value = new IEValue(new IEType("idapp", new IEType("idapp", TC.tu1, new IEType("idapp", TC.tu2, TC.tu1)), new IEType("idapp", TC.tlu2, TC.tu1)));
    value.app = new IEAbstractFunction() {
      public IEValue app(int n, IEValue[] args) throws IEError {
        return foldl1(n, args[0], args[1].l);
      }
    };
    function.add(new IEPair(name, value));

    /**
     * foldr        :: (* -> ** -> *) -> * -> [**] -> *
     */
    name = "foldr";
    value = new IEValue(new IEType("idapp", new IEType("idapp", TC.tu1, new IEType("idapp", TC.tu2, TC.tu1)), new IEType("idapp", TC.tu1, new IEType("idapp", TC.tlu2, TC.tu1))));
    value.app = new IEAbstractFunction() {
      public IEValue app(int n, IEValue[] args) throws IEError {
        return foldr(n, args[0], args[1], args[2].l);
      }
    };
    function.add(new IEPair(name, value));

    /**
     * foldr1       :: (* -> * -> *) -> [*] -> *
     */
    name = "foldr1";
    value = new IEValue(new IEType("idapp", new IEType("idapp", TC.tu1, new IEType("idapp", TC.tu2, TC.tu1)), new IEType("idapp", TC.tlu2, TC.tu1)));
    value.app = new IEAbstractFunction() {
      public IEValue app(int n, IEValue[] args) throws IEError {
        return foldr1(n, args[0], args[1].l);
      }
    };
    function.add(new IEPair(name, value));

    /**
     * map          ::  ( * -> ** ) -> [*] -> [**]
     *              ::  aplica a una lista de elementos *, una función *->** y
     *                  devuelve la lista resultante de aplicar la función a
     *                  cada elemento
     */
    name = "map";
    value = new IEValue(new IEType("idapp", new IEType("idapp", TC.tu1, TC.tu2), new IEType("idapp", TC.tlu1, TC.tlu2)));
    value.app = new IEAbstractFunction() {
      public IEValue app(int n, IEValue[] args) throws IEError {
        IEValue v = new IEValue();
        v.l = map(n, args[0], args[1].l);
        return v;
      }
    };
    function.add(new IEPair(name, value));

    /**
     * map2         ::  ( * -> ** -> *** ) -> [*] -> [**] -> [***]
     *              ::  aplica a una lista de elementos *, una función *->** y
     *                  devuelve la lista resultante de aplicar la función a
     *                  cada elemento
     */
    name = "map2";
    value = new IEValue(new IEType("idapp", new IEType("idapp", TC.tu1, new IEType("idapp", TC.tu2, TC.tu3)), new IEType("idapp", TC.tlu1, new IEType("idapp", TC.tlu2, TC.tlu3))));
    value.app = new IEAbstractFunction() {
      public IEValue app(int n, IEValue[] args) throws IEError {
        IEValue v = new IEValue();
        v.l = map2(n, args[0], args[1].l, args[2].l);
        return v;
      }
    };
    function.add(new IEPair(name, value));

    /**
     * scan         :: (* -> ** -> *) -> * -> [**] -> [*]
     */
    name = "scan";
    value = new IEValue(new IEType("idapp", new IEType("idapp", TC.tu1, new IEType("idapp", TC.tu2, TC.tu1)), new IEType("idapp", TC.tu1, new IEType("idapp", TC.tlu2, TC.tlu1))));
    value.app = new IEAbstractFunction() {
      public IEValue app(int n, IEValue[] args) throws IEError {
        IEValue v = new IEValue();
        v.l = scan(n, args[0], args[1], args[2].l);
        return v;
      }
    };
    function.add(new IEPair(name, value));
  }


  /**
   * getter de function
   */
  List<IEPair<String, IEValue>> getFunction() {
    return function;
  }


  /**
   * primitivas
   */

  /**
   * funciones de conversión y manejo de enteros y reales
   */
  static int castr2i(double r) {
    return (int) r;
  }

  static double casti2r(int i) {
    return (double) i;
  }

  static int floor(double r) {
    return (int) Math.floor(r);
  }

  static int ceil(double r) {
    return (int) Math.ceil(r);
  }

  static double frac(double r) {
    return r - (double)((int) r);
  }


  /**
   * misceláneo de funciones 
   */

  static int constie() {
    return 12;
  }


  /**
   * funciones random
   */

  static double random() {
    return rand.nextDouble();
  }

  static int intrandom() {
    return rand.nextInt();
  }

  static boolean boolrandom() {
    return rand.nextBoolean();
  }


  /**
   * funciones booleanas sobre listas
   */

  static boolean and(List<IEValue> list) {
    Iterator<IEValue> it = list.iterator();
    while (it.hasNext()) {
      if (it.next().b) {
        return false;
      }
    }
    return true;
  }

  static boolean or(List<IEValue> list) {
    Iterator<IEValue> it = list.iterator();
    while (it.hasNext()) {
      if (it.next().b) {
        return true;
      }
    }
    return false;
  }


  /**
   * funciones polimórficas
   */

  static IEValue id(IEValue v) {
    return v;
  }

  static IEValue converse(int n, IEValue func, IEValue a, IEValue b) throws IEError {
    return in.evalueFunc(n, func, b, a);
  }

  static IEValue until(int n, IEValue cond, IEValue func, IEValue v) throws IEError {
    while (!in.evalueFunc(n, cond, v).b) {
      v = in.evalueFunc(n, func, v);
    }
    return v;
  }

  static IEValue ifelse(IEValue cond, IEValue a, IEValue b) {
    return cond.b ? a : b;
  }


  /**
   * funciones sobre listas
   */

  static IEValue hd(int n, List<IEValue> list) throws IEError {
    if (list.size() == 0) {
      in.errorOutOfRange(n, "hd", 0);
    }
    return list.get(0);
  }

  static List<IEValue> tl(int n, List<IEValue> list) throws IEError {
    if (list.size() == 0) {
      in.errorOutOfRange(n, "hd", 0);
    }
    return list.subList(1, list.size());
  }

  static List<IEValue> init(int n, List<IEValue> list) throws IEError {
    if (list.size() == 0) {
      in.errorOutOfRange(n, "hd", 0);
    }
    return list.subList(0, list.size()-1);
  }

  static IEValue last(int n, List<IEValue> list) throws IEError {
    if (list.size() == 0) {
      in.errorOutOfRange(n, "hd", 0);
    }
    return list.get(list.size()-1);
  }

  static List<IEValue> prefix(IEValue v, List<IEValue> list) {
    list.add(0, v);
    return list;
  }

  static List<IEValue> postfix(IEValue v, List<IEValue> list) {
    list.add(v);
    return list;
  }

  static List<IEValue> take(int j, List<IEValue> list) {
    if (j < 0) {
      j = 0;
    }
    else if (j > list.size()) {
      j = list.size();
    }
    return list.subList(0, j);
  }

  static List<IEValue> drop(int j, List<IEValue> list) {
    if (j < 0) {
      j = 0;
    }
    else if (j > list.size()) {
      j = list.size();
    }
    return list.subList(j, list.size());
  }

  static List<IEValue> reverse(List<IEValue> list) {
    Collections.reverse(list);
    return list;
  }

  static boolean member(IEValue v, List<IEValue> list) {
    return list.contains(v);
  }

  static IEValue limit(int n, List<IEValue> list) throws IEError {
    if(list.size() == 0) {
      in.errorOutOfRange(n, "limit", 0);
    }
    Iterator<IEValue> it = list.iterator();
    IEValue v = it.next();
    while (it.hasNext()) {
      IEValue t = it.next();
      if (t.equals(v)) {
        return v;
      }
      v = t;
    }
    return v;
  }

  static List<IEValue> concat(List<IEValue> list) {
    List<IEValue> r = new ArrayList<IEValue>();
    Iterator<IEValue> it = list.iterator();
    while (it.hasNext()) {
      r.addAll(it.next().l);
    }
    return r;
  }

  static List<IEValue> mkset(List<IEValue> list) {
    List<IEValue> r = new ArrayList<IEValue>();
    Iterator<IEValue> it = list.iterator();
    while (it.hasNext()) {
      IEValue v = it.next();
      Iterator<IEValue> s = r.iterator();
      boolean cerca = false;
      while (s.hasNext() && !cerca) {
        cerca = in.equal(s.next(), v);
      }
      if (!cerca) {
        r.add(v);
      }
    }
    return r;
  }

  static List<IEValue> repeat(int j, IEValue v) {
    List<IEValue> r = new ArrayList<IEValue>(j);
    for (int i = 0; i < j; ++i) {
      r.add(v);
    }
    return r;
  }

  static List<IEValue> subpre(IEValue vl) {
    List<IEValue> r = new ArrayList<IEValue>(vl.l.size()+1);
    for (int i = 0; i <= vl.l.size(); ++i) {
      IEValue v = new IEValue(vl.tp);
      v.l = vl.l.subList(0, i);
      r.add(v);
    }
    return r;
  }


  /**
   * funciones de orden superior sobre lsitas
   */

  static List<IEValue> iterate(int n, IEValue func, IEValue v, int j) throws IEError {
    List<IEValue> r = new ArrayList<IEValue>();
    for (int i = 0; i < j; ++i) {
      r.add(v);
      v = in.evalueFunc(n, func, v); 
    }
    return r;
  }

  static List<IEValue> takewhile(int n, IEValue func, List<IEValue> list) throws IEError {
    int i = 0;
    Iterator<IEValue> it = list.iterator();
    while (it.hasNext()) {
      IEValue e = it.next();
      if (!in.evalueFunc(n, func, e).b) {
        break;
      }
      ++i;
    }
    return list.subList(0, i);
  }

  static List<IEValue> dropwhile(int n, IEValue func, List<IEValue> list) throws IEError {
    int i = 0;
    Iterator<IEValue> it = list.iterator();
    while (it.hasNext()) {
      IEValue e = it.next();
      if (!in.evalueFunc(n, func, e).b) {
        break;
      }
      ++i;
    }
    return list.subList(i, list.size());
  }

  static List<IEValue> filter(int n, IEValue func, List<IEValue> list) throws IEError {
    List<IEValue> r = new ArrayList<IEValue>();
    Iterator<IEValue> it = list.iterator();
    while (it.hasNext()) {
      IEValue e = it.next();
      IEValue t = new IEValue(e);
      if (in.evalueFunc(n, func, t).b) {
        r.add(e);
      }
    }
    return r;
  }

  static IEValue foldl(int n, IEValue func, IEValue v, List<IEValue> list) throws IEError {
    Iterator<IEValue> it = list.iterator();
    while (it.hasNext()) {
      v = in.evalueFunc(n, func, v, it.next());
    }
    return v;
  }

  static IEValue foldl1(int n, IEValue func, List<IEValue> list) throws IEError {
    if (list.size() == 0) {
      in.errorOutOfRange(n, "foldl1", 0);
    }
    Iterator<IEValue> it = list.iterator();
    IEValue v = it.next();
    while (it.hasNext()) {
      v = in.evalueFunc(n, func, v, it.next());
    }
    return v;
  }

  static IEValue foldr(int n, IEValue func, IEValue v, List<IEValue> list) throws IEError {
    ListIterator<IEValue> it = list.listIterator(list.size());
    while (it.hasPrevious()) {
      v = in.evalueFunc(n, func, it.previous(), v);
    }
    return v;
  }

  static IEValue foldr1(int n, IEValue func, List<IEValue> list) throws IEError {
    if (list.size() == 0) {
      in.errorOutOfRange(n, "foldr1", 0);
    }
    ListIterator<IEValue> it = list.listIterator(list.size());
    IEValue v = it.previous();
    while (it.hasPrevious()) {
      v = in.evalueFunc(n, func, it.previous(), v);
    }
    return v;
  }

  static List<IEValue> map(int n, IEValue func, List<IEValue> list) throws IEError {
    List<IEValue> r = new ArrayList<IEValue>(list.size());
    Iterator<IEValue> it = list.iterator();
    while (it.hasNext()) {
      r.add(in.evalueFunc(n, func, it.next()));
    }
    return r;
  }

  static List<IEValue> map2(int n, IEValue func, List<IEValue> list0, List<IEValue> list1) throws IEError {
    List<IEValue> r = new ArrayList<IEValue>(Math.min(list0.size(), list1.size()));
    Iterator<IEValue> it0 = list0.iterator();
    Iterator<IEValue> it1 = list1.iterator();
    while (it0.hasNext() && it1.hasNext()) {
      r.add(in.evalueFunc(n, func, it0.next(), it1.next()));
    }
    return r;
  }

  static List<IEValue> scan(int n, IEValue func, IEValue v, List<IEValue> list) throws IEError {
    List<IEValue> r = new ArrayList<IEValue>(list.size() + 1);
    Iterator<IEValue> it = list.iterator();
    r.add(v);
    while (it.hasNext()) {
      IEValue t = new IEValue(v);
      v = in.evalueFunc(n, func, t, it.next());
      r.add(v);
    }
    return r;
  }

}
