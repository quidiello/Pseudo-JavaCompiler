package miw.ast.expresiones.operadores;

import miw.ast.expresiones.Expresion;
import miw.ast.expresiones.ExpresionBinaria;

/**
 * Created by ast on 26/10/15.
 */
public class OperadorAritmetico extends ExpresionBinaria {

    public String operador;

    public OperadorAritmetico(Integer linea, Integer columna, Expresion leftValue, Expresion rightValue, String operador) {
        super(linea, columna, leftValue, rightValue);
        this.operador = operador;
    }

    @Override
    public String toString() {
        return "(" + leftValue + " " + operador + " " + rightValue + ")";
    }
}
