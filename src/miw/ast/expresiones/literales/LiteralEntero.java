package miw.ast.expresiones.literales;

import miw.ast.expresiones.AbstractExpresion;
import miw.ast.tipos.TipoEntero;
import miw.visitor.Visitor;

/**
 * Created by ast on 26/10/15.
 */
public class LiteralEntero extends AbstractExpresion {

    public Integer valor;

    public LiteralEntero(Integer linea, Integer columna, Integer valor) {
        super(linea, columna);
        this.valor = valor;
        this.setTipo(TipoEntero.getInstance(linea, columna));
    }

    @Override
    public String toString() {
        return String.valueOf(valor);
    }

    @Override
    public Object accept(Visitor visitor, Object object) {
        return visitor.visit(this, object);
    }

}
