package miw.ast.expresiones.literales;

import miw.ast.expresiones.AbstractExpresion;
import miw.visitor.Visitor;

/**
 * Created by ast on 26/10/15.
 */
public class LiteralEntero extends AbstractExpresion {

    public Integer valor;

    public LiteralEntero(Integer line, Integer column, Integer valor) {
        super(line, column);
        this.valor = valor;
    }

    @Override
    public String toString() {
        return String.valueOf(valor);
    }

    @Override
    public void accept(Visitor visitor, Object object) {
        visitor.visit(this, object);
    }

}
