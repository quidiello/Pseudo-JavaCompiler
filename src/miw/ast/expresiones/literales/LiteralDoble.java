package miw.ast.expresiones.literales;

import miw.ast.expresiones.AbstractExpresion;
import miw.visitor.Visitor;

/**
 * Created by ast on 26/10/15.
 */
public class LiteralDoble extends AbstractExpresion {

    public Double valor;

    public LiteralDoble(Integer line, Integer column, Double valor) {
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
