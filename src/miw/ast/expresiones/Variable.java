package miw.ast.expresiones;

import miw.ast.AbstractNodoAST;

/**
 * Created by ast on 26/10/15.
 */
public class Variable extends AbstractExpresion {

    public String nombre;

    public Variable(Integer line, Integer column, String nombre) {
        super(line, column);
        this.nombre = nombre;

        setLef(true);
    }

    @Override
    public String toString() {
        return nombre;
    }
}
