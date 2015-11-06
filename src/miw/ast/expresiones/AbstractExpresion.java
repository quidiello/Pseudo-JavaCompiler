package miw.ast.expresiones;

import miw.ast.AbstractNodoAST;
import miw.ast.tipos.Tipo;

/**
 * Created by quidiello on 31/10/15.
 */
public abstract class AbstractExpresion extends AbstractNodoAST implements  Expresion {

    private boolean left;

    private Tipo tipo;

    public AbstractExpresion(Integer linea, Integer columna, Tipo tipo) {
        super(linea, columna);
        this.left = false;
        this.tipo = tipo;
    }

    public AbstractExpresion(Integer linea, Integer columna) {
        this(linea, columna, null);
    }

    @Override
    public boolean isLeft() {
        return left;
    }

    @Override
    public void setLef(boolean left) {
        this.left = left;
    }

    @Override
    public Tipo getTipo() {
        return tipo;
    }

    @Override
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

}
