package miw.ast.definiciones;

import miw.ast.NodoAST;
import miw.ast.tipos.Tipo;
import miw.visitor.acceptVisitor;

/**
 * Created by quidiello on 30/10/15.
 */
public interface Definicion extends NodoAST {

    String getNombre();

    Tipo getTipo();
}
