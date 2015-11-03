package miw.ast;

import miw.visitor.Visitor;

/**
 * Created by ast on 26/10/15.
 */
public interface NodoAST {

    Integer getLinea();

    Integer getColumna();

    void accept(Visitor visitor, Object object);

}
