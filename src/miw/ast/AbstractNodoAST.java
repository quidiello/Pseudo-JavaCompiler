package miw.ast;

/**
 * Created by ast on 26/10/15.
 */
public class AbstractNodoAST implements NodoAST {

    private Integer linea;
    private Integer columna;

    public AbstractNodoAST(Integer linea, Integer columna) {
        this.linea = linea;
        this.columna = columna;
    }

    public Integer getLinea() {
        return linea;
    }

    public Integer getColumna() {
        return columna;
    }
}
