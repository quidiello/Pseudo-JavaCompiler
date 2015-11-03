package miw.ast;

import miw.ast.definiciones.DefFuncion;
import miw.ast.definiciones.DefVariable;
import miw.ast.definiciones.Definicion;
import miw.ast.sentencias.Sentencia;
import miw.visitor.Visitor;
import miw.visitor.acceptVisitor;

import java.util.List;

/**
 * Created by quidiello on 29/10/15.
 */
public class Programa extends AbstractNodoAST implements acceptVisitor {

    public List<Definicion> definiciones;

    public Programa(Integer linea, Integer columna, List<Definicion> definiciones) {
        super(linea, columna);
        this.definiciones = definiciones;
    }

    public String toString() {
        String s = "";
        for (Definicion definicion : definiciones) {
            s += definicion + "\n";
        }
        return s;
    }

    @Override
    public void accept(Visitor visitor, Object object) {
        visitor.visit(this, object);
    }
}
