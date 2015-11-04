package miw.semantico;

import miw.ast.expresiones.Expresion;
import miw.ast.sentencias.Asignacion;
import miw.ast.sentencias.Read;
import miw.ast.tipos.TipoError;
import miw.visitor.AbstractVisitor;

/**
 * Created by quidiello on 3/11/15.
 */
public class VisitorSemantico extends AbstractVisitor {

    @Override
    public Object visit(Asignacion asignacion, Object object) {
        super.visit(asignacion, object);

        if (!asignacion.leftValue.isLeft()) {
            new TipoError(asignacion.leftValue.getLinea(), asignacion.leftValue.getColumna(), "Expresión (" + asignacion.leftValue + ") no asignable al lado izquierdo.");
        }

        return null;
    }

    @Override
    public Object visit(Read sentenciaRead, Object object) {
        super.visit(sentenciaRead, object);

        for (Expresion expresion : sentenciaRead.expresiones) {
            if (!expresion.isLeft()) {
                new TipoError(expresion.getLinea(), expresion.getColumna(), "Expresión(" + expresion + ") no asignable al lado izquierdo.");
            }
        }

        return null;
    }

}
