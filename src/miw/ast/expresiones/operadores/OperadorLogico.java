package miw.ast.expresiones.operadores;

import miw.ast.expresiones.Expresion;

/**
 * Created by ast on 26/10/15.
 */
public class OperadorLogico extends OperadorBinario {

    public OperadorLogico(Integer linea, Integer columna, Expresion leftValue, Expresion rightValue, String operador) {
        super(linea, columna, leftValue, rightValue, operador);
    }

    @Override
    public String toString() {
        return "(" + leftValue + " " + operador + " " + rightValue + ")";
    }
}
