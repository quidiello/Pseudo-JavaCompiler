package miw.ast.expresiones.literales;

import miw.ast.expresiones.AbstractExpresion;
import miw.ast.tipos.TipoCaracter;
import miw.ast.tipos.TipoEntero;
import miw.visitor.Visitor;

/**
 * Created by quidiello on 28/10/15.
 */
public class LiteralCaracter extends AbstractExpresion {

    public Character valor;

    public LiteralCaracter(Integer linea, Integer columna, Character valor) {
        super(linea, columna);
        this.valor = valor;
        this.setTipo(TipoCaracter.getInstance(linea, columna));
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
