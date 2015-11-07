package miw.ast.expresiones.literales;

import miw.ast.expresiones.AbstractExpresion;
import miw.ast.tipos.TipoDoble;
import miw.visitor.Visitor;

/**
 * Created by ast on 26/10/15.
 */
public class LiteralDoble extends AbstractExpresion {

    public Double valor;

    public LiteralDoble(Integer linea, Integer columna, Double valor) {
        super(linea, columna);
        this.valor = valor;
        this.setTipo(TipoDoble.getInstance(linea, columna));
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
