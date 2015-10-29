package miw.ast.expresiones.operadores;

import miw.ast.AbstractNodoAST;
import miw.ast.expresiones.Expresion;

/**
 * Created by ast on 26/10/15.
 */
public class OperadorUnarioNegativo extends AbstractNodoAST implements Expresion {

    public Expresion expresion;

    public OperadorUnarioNegativo(Integer line, Integer column, Expresion expresion) {
        super(line, column);
        this.expresion = expresion;
    }

    @Override
    public String toString() {
        return "-" + expresion;
    }

}
