package miw.ast.sentencias;

import miw.ast.AbstractNodoAST;
import miw.ast.expresiones.Expresion;

/**
 * Created by ast on 26/10/15.
 */
public class Asignacion extends AbstractNodoAST implements Sentencia {

    public Expresion leftValue;
    public Expresion rightValue;

    public Asignacion(Integer linea, Integer columna, Expresion leftValue, Expresion rightValue) {
        super(linea, columna);
        this.leftValue = leftValue;
        this.rightValue = rightValue;
    }

    @Override
    public String toString(){
        return leftValue + " = " + rightValue;
    }
}
