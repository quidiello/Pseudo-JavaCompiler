package miw.ast.sentencias;

import miw.ast.AbstractNodoAST;
import miw.ast.expresiones.Expresion;
import miw.visitor.Visitor;

/**
 * Created by ast on 26/10/15.
 */
public class Return extends AbstractNodoAST implements Sentencia {

    public Expresion expresion;

    public Return(Integer linea, Integer columna, Expresion expresion) {
        super(linea, columna);
        this.expresion = expresion;
    }

    @Override
    public String toString(){
        return  "return " + expresion;
    }

    @Override
    public Object accept(Visitor visitor, Object object) {
        return visitor.visit(this, object);
    }

}
