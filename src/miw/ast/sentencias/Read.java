package miw.ast.sentencias;

import miw.ast.AbstractNodoAST;
import miw.ast.expresiones.Expresion;
import miw.visitor.Visitor;

import java.util.List;

/**
 * Created by ast on 26/10/15.
 */
public class Read extends AbstractNodoAST implements Sentencia {

    public List<Expresion> expresiones;

    public Read(Integer line, Integer column, List<Expresion> expresiones) {
        super(line, column);
        this.expresiones = expresiones;
    }

    @Override
    public String toString() {
        String s = "read ";
        for (Expresion expresion : expresiones) {
            s += expresion + ", ";
        }
        return s.substring(0, s.length()-2);
    }

    @Override
    public Object accept(Visitor visitor, Object object) {
        return visitor.visit(this, object);
    }

}
