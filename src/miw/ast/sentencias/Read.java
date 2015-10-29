package miw.ast.sentencias;

import miw.ast.AbstractNodoAST;
import miw.ast.expresiones.Expresion;

import java.util.List;

/**
 * Created by ast on 26/10/15.
 */
public class Read extends AbstractNodoAST implements Sentencia {

    public List<Expresion> expresions;

    public Read(Integer line, Integer column, List<Expresion> expresions) {
        super(line, column);
        this.expresions = expresions;
    }

    @Override
    public String toString() {
        String s = "read ";
        for (Expresion expresion : expresions) {
            s += expresion + ", ";
        }
        return s.substring(0, s.length()-2);
    }
}
