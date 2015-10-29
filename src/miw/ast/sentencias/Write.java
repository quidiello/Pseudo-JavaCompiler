package miw.ast.sentencias;

import miw.ast.AbstractNodoAST;
import miw.ast.expresiones.Expresion;

import java.util.List;

/**
 * Created by ast on 26/10/15.
 */
public class Write extends AbstractNodoAST implements Sentencia {

    public List<Expresion> expresiones;

    public Write(Integer line, Integer column, List<Expresion> expresiones) {
        super(line, column);
        this.expresiones = expresiones;
    }

    @Override
    public String toString() {
        String s = "write ";
        for (Expresion expresion : expresiones) {
            s += expresion + ", ";
        }
        return s.substring(0, s.length()-2);
    }
}
