package miw.ast.expresiones;

import miw.ast.NodoAST;
import miw.ast.tipos.Tipo;

/**
 * Created by ast on 26/10/15.
 */
public interface Expresion extends NodoAST {

    boolean isLeft();

    void setLef(boolean left);

    Tipo getTipo();

    void setTipo(Tipo tipo);

}
