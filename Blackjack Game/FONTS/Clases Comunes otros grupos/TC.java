package dominio;

import ie.*;

/**
 * @author Aleix Fernández Donis
 * Cluster y Grupo C8G3
 * Creado el 5-May-2010
 *
 * Objetivo de la clase: Utilidad para iniciar tipos
 */


/**
 * Colección de tipos más usados
 * No sólo se usan por comodidad sino también por eficiencia y seguirdad
 */





public class TC {
  public static final IEType te = new IEType("error");
  public static final IEType ok = new IEType("ok");
  public static final IEType tp = new IEType("idapp");
  public static final IEType tm = new IEType("idmacro");
  public static final IEType ts = new IEType("string");
  public static final IEType tb = new IEType("bool");
  public static final IEType tlb = new IEType(TC.tb);
  public static final IEType ti = new IEType("int");
  public static final IEType tli = new IEType(TC.ti);
  public static final IEType tr = new IEType("real");
  public static final IEType tlr = new IEType(TC.tr);
  public static final IEType tu = new IEType("*");
  public static final IEType tu1 = tu;
  public static final IEType tu2 = new IEType("**");
  public static final IEType tu3 = new IEType("***");
  public static final IEType tlu = new IEType(TC.tu);
  public static final IEType tlu1 = new IEType(TC.tu);
  public static final IEType tlu2 = new IEType(TC.tu2);
  public static final IEType tlu3 = new IEType(TC.tu3);
}
