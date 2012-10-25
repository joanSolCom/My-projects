package ie;

/**
 * @author Aleix Fernández Donis
 * Cluster y Grupo C8G3
 * Creado el 5-May-2010
 *
 * Objetivo de la clase: ensamblar el paquete ie
 */

/*
 * ensambla el paquete ie dando una interfaz suficiente y sencilla
 * se puede añadir nuevas primitivas, nuevo conocimiento, consultar
 * conocimiento, hacer resoluciones y pedir el log con diferentes
 * niveles de debug
 */





import java.io.StringReader;
import java.io.DataInputStream;
import java.util.List;
import dominio.TC;
import java.io.Serializable;

public class IEngine implements Serializable {
  private IEInterpret interpret;
  private IESymTab symtab;
  private IEAST program;
  private IEFunction function;
  private String log;


  /**
   * creadoras
   */
  public IEngine(String s) throws IEError, Exception {
    StringReader sr = new StringReader(s);
    IEngine(new IELexer(sr));
  }

  public IEngine(DataInputStream i) throws IEError, Exception {
    IEngine(new IELexer(i));
  }

  /**
   * constructora principal
   * nos parsea el programa y hace la comprobación de tipos
   * si se produce algún error da una excepción
   */
  private void IEngine(IELexer lexer) throws IEError, Exception {
    IEParser parser = new IEParser(lexer);
    parser.setASTFactory(new IEASTFactory());
    parser.setASTNodeClass("ie.IEAST");
    parser.program();
    program = (IEAST)parser.getAST();
    interpret = new IEInterpret();
    function = new IEFunction(interpret);
    addFunction(function.getFunction());
    symtab = new IESymTab(function);
    IESemantic semantic = new IESemantic(symtab);
    log = semantic.getLog();
    if (!semantic.programCheck(program)) {
      throw new IEError("semantic error: \n" + program + "\n" + semantic.getLog());
    }
    interpret.resolution(program, symtab, "main");
  }

  /**
   * operación ganxo para permitir que se añadan nuevas primitivas
   */
  protected void addFunction(List<IEPair<String, IEValue>> function) {
  }


  /**
   * getter de log
   */
  public String getLog() {
    return log;
  }

  /**
   * setter de log
   */
  public void resetLog() {
    log = "";
  }


  /**
   * resetea el estado del motor:
   *  se borra el log del interpret
   *  se borran los valores de los hechos que ya habían
   *  la próxima resolución leerá :globals
   */
  public void reset() throws Exception {
    symtab = new IESymTab(function);
    interpret.reset();
    symtab.reset();
    interpret.resolution(program, symtab, "main");
    log += "[IEngine] reset\n";
  }

  /**
   * añade conocimiento
   */
  public void setKnowledge(IEPair<String, IEValue>[] know) {
    log += "[IEngine] setKnowledge\n";
    log += symtab.setKnowledge(know);
  }

  /**
   * consulta conocimiento
   */
  public IEValue getKnowledge(String id) {
    return symtab.getKnowledge(id);
  }

  /**
   * empieza la resolución con objetivo = objective
   * si se produce error de ejecución lo guarda en el log
   * y devuelve un valor con tipo ERROR
   */
  public IEValue resolution(String objective) {
    log += "[IEngine.resolution] " + objective + "\n";
    IEValue v;
    if (symtab.find(objective)) {
      try {
        symtab.objective(objective);
        interpret.resolution(program, symtab, objective);
        v = symtab.get(objective);
        if (v.init == false) {
          v = new IEValue(TC.te);
        }
      }
      catch (Exception e) {
        log += "[IEngine.resolution] exception: " + e + "\n";
        e.printStackTrace();
        v = new IEValue(TC.te);
      }
    }
    else {
      log += "[IEngine.resolution] objective not exists(" + objective + ")\n";
      v = new IEValue(TC.te);
    }
    log += interpret.getLog();
    interpret.resetLog();
    return v;
  }


  /**
   * añade a log información según el nivel pedido
   * ya sea del AST decorado con tipos o la tabla de símbolos
   */
  public void debug(int level) {
    log += "[IEngine.debug] " + level + "\n";
    log += program;
    if (level >= 1) {
      if (level < 2) {
        log += symtab.write();
      }
      else {
        log += symtab.writeVerbose();
      }
    }
  }
}
