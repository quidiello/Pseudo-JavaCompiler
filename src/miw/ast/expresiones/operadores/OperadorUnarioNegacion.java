package miw.ast.expresiones.operadores;

import miw.ast.expresiones.Expresion;
import miw.visitor.Visitor;

/**
 * Created by ast on 26/10/15.
 */
public class OperadorUnarioNegacion extends OperadorUnario {

    public OperadorUnarioNegacion(Integer line, Integer column, Expresion expresion) {
        super(line, column, expresion);
    }

    @Override
    public String toString() {
        return "!" + expresion;
    }

    @Override
    public Object accept(Visitor visitor, Object object) {
        return visitor.visit(this, object);
    }

}
