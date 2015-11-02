package miw.ast.expresiones.literales;

import miw.ast.expresiones.AbstractExpresion;

/**
 * Created by quidiello on 28/10/15.
 */
public class LiteralCaracter extends AbstractExpresion {

    public Character valor;

    public LiteralCaracter(Integer line, Integer column, Character valor) {
        super(line, column);
        this.valor = valor;
    }

    @Override
    public String toString() {
        return String.valueOf(valor);
    }
}
