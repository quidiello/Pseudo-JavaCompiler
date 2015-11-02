package miw.ast.sentencias;

import miw.ast.AbstractNodoAST;
import miw.ast.expresiones.Expresion;

import java.util.List;

/**
 * Created by ast on 26/10/15.
 */
public class While extends AbstractNodoAST implements Sentencia {

    public Expresion condicion;
    public List<Sentencia> sentencias;

    public While(Integer line, Integer column, Expresion condicion, List<Sentencia> sentencias) {
        super(line, column);
        this.condicion = condicion;
        this.sentencias = sentencias;
    }

    @Override
    public String toString() {
        String s = "while (" + condicion + ") {";
        for (Sentencia sentencia : sentencias) {
            s += "\n\t" + sentencia + "; ";
        }
        s += "\n}";
        return s;
    }
}
