package miw.ast.tipos;

import miw.ast.AbstractNodoAST;
import miw.visitor.Visitor;

/**
 * Created by quidiello on 29/10/15.
 */
public class TipoArray extends AbstractNodoAST implements Tipo {

    public Tipo tipo;
    public Integer size;

    public TipoArray(Integer linea, Integer columna, Tipo tipo, Integer size) {
        super(linea, columna);
        this.tipo = tipo;
        this.size = size;
    }

    @Override
    public String toString() {
        return tipo.toString() + "[" + size + "]";
    }

    @Override
    public void accept(Visitor visitor, Object object) {
        visitor.visit(this, object);
    }

}
