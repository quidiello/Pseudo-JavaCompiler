package miw.ast.expresiones.operadores;

import miw.ast.expresiones.Expresion;
import miw.ast.expresiones.ExpresionBinaria;

/**
 * Created by ast on 26/10/15.
 */
public class OperadorAccesoArray extends ExpresionBinaria {

    public String operador = "[]";

    public OperadorAccesoArray(Integer linea, Integer columna, Expresion leftValue, Expresion rightValue) {
        super(linea, columna, leftValue, rightValue);
    }

    @Override
    public String toString() {
        return "(" + leftValue + " " + operador + " " + rightValue + ")";
    }
}
