package miw.ast.expresiones.operadores;

import miw.ast.expresiones.Expresion;
import miw.ast.tipos.Tipo;
import miw.visitor.Visitor;

/**
 * Created by ast on 26/10/15.
 */
public class OperadorUnarioCast extends OperadorUnario {

    public Tipo tipo;

    public OperadorUnarioCast(Integer line, Integer column, Expresion expresion, Tipo tipo) {
        super(line, column, expresion);
        this.tipo = tipo;
        setTipo(tipo);
    }

    @Override
    public String toString() {
        return "(" + tipo.getNombre() + ") " + expresion;
    }

    @Override
    public Object accept(Visitor visitor, Object object) {
        return visitor.visit(this, object);
    }

}
