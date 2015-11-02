package miw.ast.expresiones;

import miw.ast.AbstractNodoAST;

/**
 * Created by quidiello on 31/10/15.
 */
public class AbstractExpresion extends AbstractNodoAST implements  Expresion {

    private boolean left;

    public AbstractExpresion(Integer linea, Integer columna) {
        super(linea, columna);
        left = false;
    }

    @Override
    public boolean isLeft() {
        return left;
    }

    @Override
    public void setLef(boolean left) {
        this.left = left;
    }
}
