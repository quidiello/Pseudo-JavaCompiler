package miw.ast.expresiones.literales;

import miw.ast.AbstractNodoAST;
import miw.ast.expresiones.Expresion;

/**
 * Created by ast on 26/10/15.
 */
public class LiteralEntero extends AbstractNodoAST implements Expresion {

    public Integer valor;

    public LiteralEntero(Integer line, Integer column, Integer valor) {
        super(line, column);
        this.valor = valor;
    }

    @Override
    public String toString() {
        return String.valueOf(valor);
    }
}
