package miw.ast.expresiones.operadores;

import miw.ast.AbstractNodoAST;
import miw.ast.expresiones.AbstractExpresion;
import miw.ast.expresiones.Expresion;

/**
 * Created by quidiello on 28/10/15.
 */
public abstract class OperadorUnario extends AbstractExpresion {

    public Expresion expresion;

    public OperadorUnario(Integer linea, Integer columna, Expresion expresion) {
        super(linea, columna);
        this.expresion = expresion;
    }
}
