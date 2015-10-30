package miw.ast.tipos;

import miw.ast.AbstractNodoAST;

/**
 * Created by quidiello on 29/10/15.
 */
public class TipoCaracter extends AbstractNodoAST implements Tipo {

    private static TipoCaracter INSTANCE;

    private TipoCaracter(Integer linea, Integer columna) {
        super(linea, columna);
    }

    public static TipoCaracter getInstance(Integer linea, Integer columna) {
        if(INSTANCE == null) {
            INSTANCE = new TipoCaracter(linea, columna);
        }
        return INSTANCE;
    }

    @Override
    public String toString() {
        return "char";
    }

}
