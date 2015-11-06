package miw.semantico;

import miw.ast.definiciones.DefFuncion;
import miw.ast.definiciones.DefVariable;
import miw.ast.expresiones.Expresion;
import miw.ast.expresiones.Funcion;
import miw.ast.expresiones.operadores.*;
import miw.ast.sentencias.*;
import miw.ast.tipos.*;
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
        else {
            Tipo op1 = asignacion.leftValue.getTipo();
            Tipo op2 = asignacion.rightValue.getTipo();
            if (op1.asignacion(op2) == null) {
                new TipoError(asignacion.getLinea(), asignacion.getColumna(), "Tipos incompatibles para la asignación. Se espera un " + op1.getNombre() + " y ha llegado un " + op2);
            }
        }

        return null;
    }

    @Override
    public Object visit(Read sentenciaRead, Object object) {
        super.visit(sentenciaRead, object);

        for (Expresion expresion : sentenciaRead.expresiones) {
            if (!expresion.isLeft()) {
                new TipoError(expresion.getLinea(), expresion.getColumna(), "Expresión (" + expresion + ") no asignable al lado izquierdo.");
            }
        }

        return null;
    }

    @Override
    public Object visit(OperadorAccesoArray operadorAccesoArray, Object object) {
        super.visit(operadorAccesoArray, object);

        Tipo op1 = operadorAccesoArray.leftValue.getTipo();
        Tipo op2 = operadorAccesoArray.rightValue.getTipo();

        Tipo operacion = op1.corchetes(op2);
        if (operacion == null) {
            operacion = new TipoError(operadorAccesoArray.getLinea(), operadorAccesoArray.getColumna(), "Tipos incompatibles para el acceso a array. Se espera un int y ha llegado un " + op2.getNombre());
        }
        operadorAccesoArray.setTipo(operacion);

        return null;
    }

    @Override
    public Object visit(OperadorAritmetico operadorAritmetico, Object object) {
        super.visit(operadorAritmetico, object);

        Tipo op1 = operadorAritmetico.leftValue.getTipo();
        Tipo op2 = operadorAritmetico.rightValue.getTipo();

        Tipo operacion = op1.aritmetica(op2);
        if (operacion == null) {
            operacion = new TipoError(operadorAritmetico.getLinea(), operadorAritmetico.getColumna(), "Tipos incompatibles para el operador aritmético: " + op1.getNombre() + " y " + op2.getNombre());
        }
        operadorAritmetico.setTipo(operacion);

        return null;
    }

    @Override
    public Object visit(OperadorComparacion operadorComparacion, Object object) {
        super.visit(operadorComparacion, object);

        Tipo op1 = operadorComparacion.leftValue.getTipo();
        Tipo op2 = operadorComparacion.rightValue.getTipo();

        Tipo operacion = op1.comparacion(op2);
        if (operacion == null) {
            operacion = new TipoError(operadorComparacion.getLinea(), operadorComparacion.getColumna(), "Tipos incompatibles para el operador comparación: " + op1.getNombre() + " y " + op2.getNombre());
        }
        operadorComparacion.setTipo(operacion);

        return null;
    }

    @Override
    public Object visit(OperadorLogico operadorLogico, Object object) {

        super.visit(operadorLogico, object);

        Tipo op1 = operadorLogico.leftValue.getTipo();
        Tipo op2 = operadorLogico.rightValue.getTipo();

        Tipo operacion = op1.logica(op2);
        if (operacion == null) {
            operacion = new TipoError(operadorLogico.getLinea(), operadorLogico.getColumna(), "Tipos incompatibles para el operador lógico: " + op1.getNombre() + " y  " + op2.getNombre());
        }
        operadorLogico.setTipo(operacion);

        return null;
    }

    @Override
    public Object visit(OperadorUnarioCast operadorUnarioCast, Object object) {
        super.visit(operadorUnarioCast, object);

        Tipo op1 = operadorUnarioCast.getTipo();
        Tipo op2 = operadorUnarioCast.expresion.getTipo();

        Tipo operacion = op1.cast(op2);
        if (operacion == null) {
            operacion = new TipoError(operadorUnarioCast.getLinea(), operadorUnarioCast.getColumna(), "Tipo '"+ op2.getNombre() + "' incompatibles para el operador cast");
        }
        operadorUnarioCast.setTipo(operacion);

        return null;
    }

    @Override
    public Object visit(OperadorUnarioNegacion operadorUnarioNegacion, Object object) {
        super.visit(operadorUnarioNegacion, object);

        Tipo op1 = operadorUnarioNegacion.expresion.getTipo();

        Tipo operacion = op1.logica();
        if (operacion == null) {
            operacion = new TipoError(operadorUnarioNegacion.getLinea(), operadorUnarioNegacion.getColumna(), "Tipo '" + op1.getNombre() + "' incompatible para el operador negación");
        }
        operadorUnarioNegacion.setTipo(operacion);

        return null;
    }

    @Override
    public Object visit(OperadorUnarioNegativo operadorUnarioNegativo, Object object) {
        super.visit(operadorUnarioNegativo, object);

        Tipo op1 = operadorUnarioNegativo.expresion.getTipo();

        Tipo operacion = op1.aritmetica();
        if (operacion == null) {
            operacion = new TipoError(operadorUnarioNegativo.getLinea(), operadorUnarioNegativo.getColumna(), "Tipo '" + op1.getNombre() + "' incompatible para el operador negativo");
        }
        operadorUnarioNegativo.setTipo(operacion);

        return null;
    }

    @Override
    public Object visit(Return sentenciaReturn, Object object) {
        super.visit(sentenciaReturn, object);

        Tipo tipo = (Tipo) object;
        Tipo retorno = sentenciaReturn.expresion.getTipo();

        if(! retorno.esPromocionable(tipo)) {
            new TipoError(sentenciaReturn.getLinea(), sentenciaReturn.getColumna(), "Tipo incompatible de retorno. Se espera un " + tipo.getNombre() + " y ha llegado un " + retorno.getNombre());
        }

        return null;
    }

    @Override
    public Object visit(If sentenciaIf, Object object) {
        super.visit(sentenciaIf, object);

        Tipo tipo = sentenciaIf.condicion.getTipo();
        if (! tipo.esLogico()) {
            new TipoError(sentenciaIf.getLinea(), sentenciaIf.getColumna(), "Tipo '" + tipo.getNombre() + "' incompatible para una condición");
        }

        return null;
    }

    @Override
    public Object visit(While sentenciaWhile, Object object) {
        super.visit(sentenciaWhile, object);

        Tipo tipo = sentenciaWhile.condicion.getTipo();
        if (! tipo.esLogico()) {
            new TipoError(sentenciaWhile.getLinea(), sentenciaWhile.getColumna(), "Tipo '" + tipo.getNombre() + "' incompatible para una condición");
        }

        return null;
    }

    @Override
    public Object visit(Funcion sentenciaFuncion, Object object) {
        super.visit(sentenciaFuncion, object);

        Tipo tipo = sentenciaFuncion.variable.definicion.getTipo();
        Tipo retorno = tipo.parentesis(sentenciaFuncion.argumentos);

        if (retorno == null) {
            new TipoError(sentenciaFuncion.getLinea(), sentenciaFuncion.getColumna(), "Función inválidad");
        }
        sentenciaFuncion.setTipo(retorno);

        return null;
    }

    @Override
    public Object visit(DefFuncion defFuncion, Object object) {

        TipoFuncion tipoFuncion = (TipoFuncion) defFuncion.getTipo();

        for (DefVariable defVariable : tipoFuncion.parametros) {
            Tipo tipo = defVariable.getTipo();
            if (tipo.esBasico()) {
                defVariable.accept(this, object);
            }
            else {
                new TipoError(defFuncion.getLinea(), defFuncion.getColumna(), "Tipos incompatibles de parámetros, han de ser básicos");
            }
        }
        if (! tipoFuncion.tipoRetorno.esBasico()) {
            new TipoError(defFuncion.getLinea(), defFuncion.getColumna(), "Tipo incompatible de retorno. Se espera un tipo básico y ha llegado un " + tipoFuncion.tipoRetorno.getNombre());
        }

        super.visit(defFuncion, tipoFuncion.tipoRetorno);

        return null;
    }

}
