package miw.ast;

import miw.ast.definiciones.DefVariable;
import miw.ast.sentencias.Sentencia;

import java.util.List;

/**
 * Created by quidiello on 29/10/15.
 */
public class Programa extends AbstractNodoAST {

    public List<DefVariable> defVariables;
    public List<Sentencia> sentencias;

    public Programa(Integer linea, Integer columna, List<DefVariable> defVariables, List<Sentencia> sentencias) {
        super(linea, columna);
        this.defVariables = defVariables;
        this.sentencias = sentencias;
    }

    public String toString() {
        String s = "";
        for (DefVariable defVariable : defVariables) {
            s += "\n" + defVariable;
        }
        s += "void Main() {";

        for (Sentencia sentencia : sentencias) {
            s += "\n\t" + sentencia;
        }
        return s + "\n}";
    }
}
