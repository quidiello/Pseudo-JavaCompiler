package miw.ast.expresiones;

import miw.ast.definiciones.DefVariable;
import miw.ast.definiciones.Definicion;
import miw.ast.tipos.Tipo;
import miw.visitor.Visitor;


/**
 * Created by ast on 26/10/15.
 */
public class Variable extends AbstractExpresion {

    public String nombre;
    public Definicion definicion;

    public Variable(Integer line, Integer column, String nombre) {
        super(line, column);
        this.nombre = nombre;

        setLef(true);
    }

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public Object accept(Visitor visitor, Object object) {
        return visitor.visit(this, object);
    }

}
