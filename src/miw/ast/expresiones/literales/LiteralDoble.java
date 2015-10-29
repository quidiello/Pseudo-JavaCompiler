package miw.ast.expresiones.literales;

import miw.ast.AbstractNodoAST;
import miw.ast.expresiones.Expresion;

/**
 * Created by ast on 26/10/15.
 */
public class LiteralDoble extends AbstractNodoAST implements Expresion {

    public Double valor;

    public LiteralDoble(Integer line, Integer column, Double valor) {
        super(line, column);
        this.valor = valor;
    }

    @Override
    public String toString() {
        return String.valueOf(valor);
    }
}
