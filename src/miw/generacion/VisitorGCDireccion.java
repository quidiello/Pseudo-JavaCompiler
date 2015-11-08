package miw.generacion;

import miw.ast.definiciones.DefVariable;
import miw.ast.expresiones.Variable;
import miw.ast.expresiones.operadores.OperadorAccesoArray;
import miw.ast.tipos.Tipo;
import miw.ast.tipos.TipoEntero;

/**
 * Created by quidiello on 8/11/15.
 */
public class VisitorGCDireccion extends AbstractVisitorGC {

    private GeneradorCodigo generadorCodigo;
    private VisitorGCValor visitorGCValor;

    public VisitorGCDireccion(GeneradorCodigo generadorCodigo) {
        this.generadorCodigo = generadorCodigo;
    }

    public void setVisitorGCValor(VisitorGCValor visitorGCValor) {
        this.visitorGCValor = visitorGCValor;
    }

    @Override
    public Object visit(Variable variable, Object object) {
        if (variable.definicion instanceof DefVariable) {
            DefVariable defVariable = (DefVariable) variable.definicion;
            if (defVariable.getAmbito() == 0) {
                this.generadorCodigo.pusha(defVariable.getOffset());
            }
            else {
                this.generadorCodigo.pushbp();
                this.generadorCodigo.push(defVariable.getOffset());
                this.generadorCodigo.add(TipoEntero.getInstance(defVariable.getLinea(), defVariable.getColumna()));
            }
        }

        return null;
    }

    @Override
    public Object visit(OperadorAccesoArray operadorAccesoArray, Object object) {
        Tipo left = operadorAccesoArray.leftValue.getTipo();
        Tipo right = operadorAccesoArray.rightValue.getTipo();

        operadorAccesoArray.leftValue.accept(this, object);
        operadorAccesoArray.rightValue.accept(visitorGCValor, object);
        this.generadorCodigo.cast(right, TipoEntero.getInstance(operadorAccesoArray.getLinea(), operadorAccesoArray.getColumna()));

        this.generadorCodigo.push(right.getTamano());
        this.generadorCodigo.mul(left);
        this.generadorCodigo.add(left);

        return null;
    }

}
