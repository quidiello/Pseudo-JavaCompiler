package miw.visitor;

import miw.ast.Programa;
import miw.ast.definiciones.DefFuncion;
import miw.ast.definiciones.DefVariable;
import miw.ast.definiciones.Definicion;
import miw.ast.expresiones.Expresion;
import miw.ast.expresiones.Funcion;
import miw.ast.expresiones.Variable;
import miw.ast.expresiones.literales.LiteralCaracter;
import miw.ast.expresiones.literales.LiteralDoble;
import miw.ast.expresiones.literales.LiteralEntero;
import miw.ast.expresiones.operadores.*;
import miw.ast.sentencias.*;
import miw.ast.tipos.*;

/**
 * Created by quidiello on 2/11/15.
 */
public abstract class AbstractVisitor implements Visitor {
    @Override
    public Object visit(Programa programa, Object object) {


        for (Definicion definicion : programa.definiciones) {
            definicion.accept(this, object);
        }

        return null;
    }

    @Override
    public Object visit(LiteralCaracter literalCaracter, Object object) {
        return null;
    }

    @Override
    public Object visit(LiteralEntero literalEntero, Object object) {
        return null;
    }

    @Override
    public Object visit(LiteralDoble literalDoble, Object object) {
        return null;
    }

    @Override
    public Object visit(Variable variable, Object object) {
        return null;
    }

    @Override
    public Object visit(OperadorAccesoArray operadorAccesoArray, Object object) {

        operadorAccesoArray.leftValue.accept(this, object);
        operadorAccesoArray.rightValue.accept(this, object);

        return null;
    }

    @Override
    public Object visit(OperadorAritmetico operadorAritmetico, Object object) {

        operadorAritmetico.leftValue.accept(this, object);
        operadorAritmetico.rightValue.accept(this, object);

        return null;
    }

    @Override
    public Object visit(OperadorComparacion operadorComparacion, Object object) {

        operadorComparacion.leftValue.accept(this, object);
        operadorComparacion.rightValue.accept(this, object);

        return null;
    }

    @Override
    public Object visit(OperadorLogico operadorLogico, Object object) {

        operadorLogico.leftValue.accept(this, object);
        operadorLogico.rightValue.accept(this, object);

        return null;
    }

    @Override
    public Object visit(OperadorUnarioCast operadorUnarioCast, Object object) {

        operadorUnarioCast.tipo.accept(this, object);
        operadorUnarioCast.expresion.accept(this, object);

        return null;
    }

    @Override
    public Object visit(OperadorUnarioNegacion operadorUnarioNegacion, Object object) {

        operadorUnarioNegacion.expresion.accept(this, object);

        return null;
    }

    @Override
    public Object visit(OperadorUnarioNegativo operadorUnarioNegativo, Object object) {

        operadorUnarioNegativo.expresion.accept(this, object);

        return null;
    }

    @Override
    public Object visit(Asignacion sentenciaAsignacion, Object object) {

        sentenciaAsignacion.leftValue.accept(this, object);
        sentenciaAsignacion.rightValue.accept(this, object);

        return null;
    }

    @Override
    public Object visit(Funcion sentenciaFuncion, Object object) {

        for (Expresion expresion : sentenciaFuncion.argumentos) {
            expresion.accept(this, object);
        }
        sentenciaFuncion.variable.accept(this, object);

        return null;
    }

    @Override
    public Object visit(Return sentenciaReturn, Object object) {

        sentenciaReturn.expresion.accept(this, object);

        return null;
    }

    @Override
    public Object visit(If sentenciaIf, Object object) {

        sentenciaIf.condicion.accept(this, object);
        for (Sentencia sentencia : sentenciaIf.sentenciasIf) {
            sentencia.accept(this, object);
        }
        if (sentenciaIf.sentenciasElse != null) {
            for (Sentencia sentencia: sentenciaIf.sentenciasElse) {
                sentencia.accept(this, object);
            }
        }

        return null;
    }

    @Override
    public Object visit(While sentenciaWhile, Object object) {

        sentenciaWhile.condicion.accept(this, object);
        for (Sentencia sentencia : sentenciaWhile.sentencias) {
            sentencia.accept(this, object);
        }

        return null;
    }

    @Override
    public Object visit(Read sentenciaRead, Object object) {

        for (Expresion expresion : sentenciaRead.expresiones) {
            expresion.accept(this, object);
        }

        return null;
    }

    @Override
    public Object visit(Write sentenciaWrite, Object object) {

        for (Expresion expresion : sentenciaWrite.expresiones) {
            expresion.accept(this, object);
        }

        return null;
    }

    @Override
    public Object visit(DefVariable defVariable, Object object) {
        return null;
    }

    @Override
    public Object visit(DefFuncion defFuncion, Object object) {

        for (DefVariable defVariable : defFuncion.variables) {
            defVariable.accept(this, object);
        }

        for (Sentencia sentencia : defFuncion.sentencias) {
            sentencia.accept(this, object);
        }

        return null;
    }

    @Override
    public Object visit(TipoArray tipoArray, Object object) {

        tipoArray.tipo.accept(this, object);

        return null;
    }

    @Override
    public Object visit(TipoCaracter tipoCaracter, Object object) {
        return null;
    }

    @Override
    public Object visit(TipoDoble tipoDoble, Object object) {
        return null;
    }

    @Override
    public Object visit(TipoEntero tipoEntero, Object object) {
        return null;
    }

    @Override
    public Object visit(TipoFuncion tipoFuncion, Object object) {

        for (DefVariable defVariable : tipoFuncion.parametros) {
            defVariable.accept(this, object);
        }
        tipoFuncion.tipoRetorno.accept(this, object);

        return null;
    }

    @Override
    public Object visit(TipoVoid tipoVoid, Object object) {
        return null;
    }

    @Override
    public Object visit(TipoError tipoError, Object object) {
        return null;
    }
}
