package miw.ast.expresiones.operadores;

import miw.ast.expresiones.Expresion;

/**
 * Created by ast on 26/10/15.
 */
public class OperadorAccesoArray extends OperadorBinario {

    public OperadorAccesoArray(Integer linea, Integer columna, Expresion leftValue, Expresion rightValue) {
        super(linea, columna, leftValue, rightValue, "[]");

        setLef(true);
    }

    @Override
    public String toString() {
        return "(" + leftValue + "[" + rightValue + "])";
    }
}
