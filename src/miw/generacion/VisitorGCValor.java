package miw.generacion;

import miw.ast.expresiones.Expresion;
import miw.ast.expresiones.Funcion;
import miw.ast.expresiones.Variable;
import miw.ast.expresiones.literales.LiteralCaracter;
import miw.ast.expresiones.literales.LiteralDoble;
import miw.ast.expresiones.literales.LiteralEntero;
import miw.ast.expresiones.operadores.*;
import miw.ast.sentencias.*;
import miw.ast.tipos.Tipo;
import miw.ast.tipos.TipoFuncion;

/**
 * Created by quidiello on 8/11/15.
 */
public class VisitorGCValor extends AbstractVisitorGC {

    private GeneradorCodigo generadorCodigo;
    private VisitorGCDireccion visitorGCDireccion;
    private VisitorGCEjecutar visitorGCEjecutar;

    public VisitorGCValor(GeneradorCodigo generadorCodigo, VisitorGCEjecutar visitorGCEjecutar) {
        this.generadorCodigo = generadorCodigo;
        this.visitorGCEjecutar = visitorGCEjecutar;
    }

    public void setVisitorGCDireccion(VisitorGCDireccion visitorGCDireccion) {
        this.visitorGCDireccion = visitorGCDireccion;
    }

    @Override
    public Object visit(LiteralCaracter literalCaracter, Object object) {
        this.generadorCodigo.push(literalCaracter.valor);

        return null;
    }

    @Override
    public Object visit(LiteralEntero literalEntero, Object object) {
        this.generadorCodigo.push(literalEntero.valor);

        return null;
    }

    @Override
    public Object visit(LiteralDoble literalDoble, Object object) {
        this.generadorCodigo.push(literalDoble.valor);

        return null;
    }

    @Override
    public Object visit(Variable variable, Object object) {
        variable.accept(visitorGCDireccion, object);
        this.generadorCodigo.load(variable.getTipo());

        return null;
    }

    @Override
    public Object visit(OperadorAritmetico operadorAritmetico, Object object) {
        Tipo tipo = operadorAritmetico.getTipo();
        Tipo left = operadorAritmetico.leftValue.getTipo();
        Tipo right = operadorAritmetico.rightValue.getTipo();

        operadorAritmetico.leftValue.accept(this, object);
        this.generadorCodigo.cast(left, tipo);
        operadorAritmetico.rightValue.accept(this, object);
        this.generadorCodigo.cast(right, tipo);
        this.generadorCodigo.aritmetica(tipo, operadorAritmetico.operador);

        return null;
    }

    @Override
    public Object visit(OperadorComparacion operadorComparacion, Object object) {
        Tipo left = operadorComparacion.leftValue.getTipo();
        Tipo right = operadorComparacion.rightValue.getTipo();
        Tipo tipo = left.mayor(right);

        operadorComparacion.leftValue.accept(this, object);
        this.generadorCodigo.cast(left, tipo);
        operadorComparacion.rightValue.accept(this, object);
        this.generadorCodigo.cast(right, tipo);
        this.generadorCodigo.comparacion(tipo, operadorComparacion.operador);

        return null;
    }

    @Override
    public Object visit(OperadorLogico operadorLogico, Object object) {
        Tipo tipo = operadorLogico.getTipo();
        Tipo left = operadorLogico.leftValue.getTipo();
        Tipo right = operadorLogico.rightValue.getTipo();

        operadorLogico.leftValue.accept(this,object);
        this.generadorCodigo.cast(left, tipo);
        operadorLogico.rightValue.accept(this, object);
        this.generadorCodigo.cast(right, tipo);
        this.generadorCodigo.logica(operadorLogico.operador);

        return null;
    }

    @Override
    public Object visit(OperadorUnarioCast operadorUnarioCast, Object object) {
        operadorUnarioCast.expresion.accept(this, object);
        this.generadorCodigo.cast(operadorUnarioCast.expresion.getTipo(), operadorUnarioCast.getTipo());

        return null;
    }

    @Override
    public Object visit(OperadorUnarioNegacion operadorUnarioNegacion, Object object) {
        operadorUnarioNegacion.expresion.accept(this, object);
        this.generadorCodigo.not();

        return null;
    }

    @Override
    public Object visit(OperadorUnarioNegativo operadorUnarioNegativo, Object object) {
        Tipo tipo = operadorUnarioNegativo.getTipo();

        this.generadorCodigo.push(0);
        operadorUnarioNegativo.expresion.accept(this, object);
        this.generadorCodigo.cast(operadorUnarioNegativo.expresion.getTipo(), tipo);
        this.generadorCodigo.sub(tipo);

        return null;
    }

    @Override
    public Object visit(Asignacion sentenciaAsignacion, Object object) {
        sentenciaAsignacion.accept(visitorGCEjecutar, object);
        sentenciaAsignacion.leftValue.accept(visitorGCDireccion, object);
        this.generadorCodigo.load(sentenciaAsignacion.leftValue.getTipo());

        return null;
    }

    @Override
    public Object visit(Funcion sentenciaFuncion, Object object) {
        TipoFuncion tipoFuncion = (TipoFuncion) sentenciaFuncion.variable.definicion.getTipo();

        int i = 0;
        while (i < sentenciaFuncion.argumentos.size()) {
            Expresion expresion = sentenciaFuncion.argumentos.get(i);
            expresion.accept(this, object);
            this.generadorCodigo.cast(expresion.getTipo(), tipoFuncion.parametros.get(i).getTipo());
        }
        this.generadorCodigo.call(sentenciaFuncion.variable.nombre);

        return null;
    }

    @Override
    public Object visit(OperadorAccesoArray operadorAccesoArray, Object object) {
        operadorAccesoArray.accept(visitorGCDireccion, object);
        this.generadorCodigo.load(operadorAccesoArray.getTipo());

        return null;
    }

}
