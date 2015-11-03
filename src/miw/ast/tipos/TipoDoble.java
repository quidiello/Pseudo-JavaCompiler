package miw.ast.tipos;

import miw.ast.AbstractNodoAST;
import miw.visitor.Visitor;

/**
 * Created by quidiello on 29/10/15.
 */
public class TipoDoble extends AbstractNodoAST implements Tipo {

    private static TipoDoble INSTANCE;

    private TipoDoble(Integer linea, Integer columna) {
        super(linea, columna);
    }

    public static TipoDoble getInstance(Integer linea, Integer columna) {
        if(INSTANCE == null) {
            INSTANCE = new TipoDoble(linea, columna);
        }
        return INSTANCE;
    }

    @Override
    public String toString() {
        return "double";
    }

    @Override
    public void accept(Visitor visitor, Object object) {
        visitor.visit(this, object);
    }

}
