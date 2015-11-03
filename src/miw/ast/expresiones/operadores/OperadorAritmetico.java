package miw.ast.expresiones.operadores;

import miw.ast.expresiones.Expresion;
import miw.visitor.Visitor;

/**
 * Created by ast on 26/10/15.
 */
public class OperadorAritmetico extends OperadorBinario {

    public OperadorAritmetico(Integer linea, Integer columna, Expresion leftValue, Expresion rightValue, String operador) {
        super(linea, columna, leftValue, rightValue, operador);
    }

    @Override
    public String toString() {
        return "(" + leftValue + " " + operador + " " + rightValue + ")";
    }

    @Override
    public void accept(Visitor visitor, Object object) {
        visitor.visit(this, object);
    }
}
