package miw.generacion;

import miw.ast.definiciones.DefFuncion;
import miw.ast.definiciones.DefVariable;
import miw.ast.expresiones.Variable;
import miw.ast.tipos.Tipo;
import miw.ast.tipos.TipoFuncion;
import miw.visitor.AbstractVisitor;

import java.util.ListIterator;

/**
 * Created by quidiello on 6/11/15.
 */
public class VisitorOffset extends AbstractVisitor {

    private Integer offset;
    private Integer offsetLocal;

    public VisitorOffset() {
        offset = 0;
        offsetLocal = 0;
    }

    @Override
    public Object visit(DefVariable defVariable, Object object) {
        super.visit(defVariable, object);

        if (defVariable.getAmbito() == 0) {
            defVariable.setOffset(offset);
            offset += defVariable.getTipo().getTamano();
        }
        else  {
            offsetLocal -= defVariable.getTipo().getTamano();
            defVariable.setOffset(offsetLocal);
        }

        return offsetLocal;
    }

    @Override
    public Object visit(DefFuncion defFuncion, Object object) {

        defFuncion.getTipo().accept(this, object);

        offsetLocal = 0;
        for (DefVariable defVariable : defFuncion.variables) {
            defVariable.accept(this, object);
        }

        defFuncion.setOffsetVariablesLocales(offsetLocal);

        return null;
    }

    public Object visit(TipoFuncion tipoFuncion, Object object) {

        int offsetParametros = 4;

        ListIterator iterator = tipoFuncion.parametros.listIterator(tipoFuncion.parametros.size());
        DefVariable defVariable;

        while (iterator.hasPrevious()) {
            defVariable = (DefVariable) iterator.previous();
            defVariable.setOffset(offsetParametros);
            offsetParametros += defVariable.getTipo().getTamano();
        }

        return null;
    }

}
