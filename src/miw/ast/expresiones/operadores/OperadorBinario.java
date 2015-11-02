package miw.ast.expresiones.operadores;

import miw.ast.AbstractNodoAST;
import miw.ast.expresiones.AbstractExpresion;
import miw.ast.expresiones.Expresion;

/**
 * Created by quidiello on 28/10/15.
 */
public class OperadorBinario extends AbstractExpresion {

    public Expresion leftValue;
    public Expresion rightValue;
    public String operador;

    public OperadorBinario(Integer linea, Integer columna, Expresion leftValue, Expresion rightValue, String operador) {
        super(linea, columna);
        this.leftValue = leftValue;
        this.rightValue = rightValue;
        this.operador = operador;
    }
}
