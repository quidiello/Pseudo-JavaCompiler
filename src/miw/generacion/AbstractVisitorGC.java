package miw.generacion;

import miw.ast.Programa;
import miw.ast.definiciones.DefFuncion;
import miw.ast.definiciones.DefVariable;
import miw.ast.expresiones.Funcion;
import miw.ast.expresiones.Variable;
import miw.ast.expresiones.literales.LiteralCaracter;
import miw.ast.expresiones.literales.LiteralDoble;
import miw.ast.expresiones.literales.LiteralEntero;
import miw.ast.expresiones.operadores.*;
import miw.ast.sentencias.*;
import miw.ast.tipos.*;
import miw.visitor.Visitor;

/**
 * Created by quidiello on 8/11/15.
 */
public class AbstractVisitorGC implements Visitor {
    @Override
    public Object visit(Programa programa, Object object) {
        throw new IllegalStateException("Visitor no implementado");
    }

    @Override
    public Object visit(LiteralCaracter literalCaracter, Object object) {
        throw new IllegalStateException("Visitor no implementado");
    }

    @Override
    public Object visit(LiteralEntero literalEntero, Object object) {
        throw new IllegalStateException("Visitor no implementado");
    }

    @Override
    public Object visit(LiteralDoble literalDoble, Object object) {
        throw new IllegalStateException("Visitor no implementado");
    }

    @Override
    public Object visit(Variable variable, Object object) {
        throw new IllegalStateException("Visitor no implementado");
    }

    @Override
    public Object visit(OperadorAccesoArray operadorAccesoArray, Object object) {
        throw new IllegalStateException("Visitor no implementado");
    }

    @Override
    public Object visit(OperadorAritmetico operadorAritmetico, Object object) {
        throw new IllegalStateException("Visitor no implementado");
    }

    @Override
    public Object visit(OperadorComparacion operadorComparacion, Object object) {
        throw new IllegalStateException("Visitor no implementado");
    }

    @Override
    public Object visit(OperadorLogico operadorLogico, Object object) {
        throw new IllegalStateException("Visitor no implementado");
    }

    @Override
    public Object visit(OperadorUnarioCast operadorUnarioCast, Object object) {
        throw new IllegalStateException("Visitor no implementado");
    }

    @Override
    public Object visit(OperadorUnarioNegacion operadorUnarioNegacion, Object object) {
        throw new IllegalStateException("Visitor no implementado");
    }

    @Override
    public Object visit(OperadorUnarioNegativo operadorUnarioNegativo, Object object) {
        throw new IllegalStateException("Visitor no implementado");
    }

    @Override
    public Object visit(Asignacion sentenciaAsignacion, Object object) {
        throw new IllegalStateException("Visitor no implementado");
    }

    @Override
    public Object visit(Funcion sentenciaFuncion, Object object) {
        throw new IllegalStateException("Visitor no implementado");
    }

    @Override
    public Object visit(Return sentenciaReturn, Object object) {
        throw new IllegalStateException("Visitor no implementado");
    }

    @Override
    public Object visit(If sentenciaIf, Object object) {
        throw new IllegalStateException("Visitor no implementado");
    }

    @Override
    public Object visit(While sentenciaWhile, Object object) {
        throw new IllegalStateException("Visitor no implementado");
    }

    @Override
    public Object visit(Read sentenciaRead, Object object) {
        throw new IllegalStateException("Visitor no implementado");
    }

    @Override
    public Object visit(Write sentenciaWrite, Object object) {
        throw new IllegalStateException("Visitor no implementado");
    }

    @Override
    public Object visit(DefVariable defVariable, Object object) {
        throw new IllegalStateException("Visitor no implementado");
    }

    @Override
    public Object visit(DefFuncion defFuncion, Object object) {
        throw new IllegalStateException("Visitor no implementado");
    }

    @Override
    public Object visit(TipoArray tipoArray, Object object) {
        throw new IllegalStateException("Visitor no implementado");
    }

    @Override
    public Object visit(TipoCaracter tipoCaracter, Object object) {
        throw new IllegalStateException("Visitor no implementado");
    }

    @Override
    public Object visit(TipoDoble tipoDoble, Object object) {
        throw new IllegalStateException("Visitor no implementado");
    }

    @Override
    public Object visit(TipoEntero tipoEntero, Object object) {
        throw new IllegalStateException("Visitor no implementado");
    }

    @Override
    public Object visit(TipoFuncion tipoFuncion, Object object) {
        throw new IllegalStateException("Visitor no implementado");
    }

    @Override
    public Object visit(TipoVoid tipoVoid, Object object) {
        throw new IllegalStateException("Visitor no implementado");
    }

    @Override
    public Object visit(TipoError tipoError, Object object) {
        throw new IllegalStateException("Visitor no implementado");
    }
}
