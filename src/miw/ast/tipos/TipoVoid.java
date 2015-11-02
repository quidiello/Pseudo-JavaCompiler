package miw.ast.tipos;

import miw.ast.AbstractNodoAST;

/**
 * Created by quidiello on 29/10/15.
 */
public class TipoVoid extends AbstractNodoAST implements Tipo {

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

}