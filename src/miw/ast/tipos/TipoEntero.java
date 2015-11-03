package miw.ast.tipos;

import miw.ast.AbstractNodoAST;
import miw.visitor.Visitor;

/**
 * Created by quidiello on 29/10/15.
 */
public class TipoEntero extends AbstractNodoAST implements Tipo {

    private static TipoEntero INSTANCE;

    public TipoEntero(Integer linea, Integer columna) {
        super(linea, columna);
    }


    public static TipoEntero getInstance(Integer linea, Integer columna) {
        if (INSTANCE == null) {
            INSTANCE = new TipoEntero(linea, columna);
        }
        return INSTANCE;
    }

    @Override
    public String toString() {
        return "int";
    }

    @Override
    public void accept(Visitor visitor, Object object) {
        visitor.visit(this, object);
    }

}
