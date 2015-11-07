package miw.ast.tipos;

import miw.ast.AbstractNodoAST;
import miw.visitor.Visitor;

/**
 * Created by quidiello on 29/10/15.
 */
public class TipoVoid extends AbstractTipo {

    private static TipoVoid INSTANCE;

    private TipoVoid(Integer linea, Integer columna) {
        super(linea, columna);
    }

    public static TipoVoid getInstance(Integer linea, Integer columna) {
        if(INSTANCE == null) {
            INSTANCE = new TipoVoid(linea, columna);
        }
        return INSTANCE;
    }

    @Override
    public String toString() {
        return "void";
    }

    @Override
    public Object accept(Visitor visitor, Object object) {
        return visitor.visit(this, object);
    }

    @Override
    public String getNombre() {
        return "void";
    }

    @Override
    public boolean esBasico() {
        return true;
    }

}
