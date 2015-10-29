package miw.ast.expresiones;

import miw.ast.AbstractNodoAST;

/**
 * Created by quidiello on 28/10/15.
 */
public class ExpresionBinaria extends AbstractNodoAST implements Expresion {

    public Expresion leftValue;
    public Expresion rightValue;

    public ExpresionBinaria(Integer linea, Integer columna, Expresion leftValue, Expresion rightValue) {
        super(linea, columna);
        this.leftValue = leftValue;
        this.rightValue = rightValue;
    }
}
