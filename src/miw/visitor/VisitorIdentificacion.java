package miw.visitor;

import miw.ast.definiciones.DefFuncion;
import miw.ast.definiciones.DefVariable;
import miw.ast.definiciones.Definicion;
import miw.ast.expresiones.Variable;
import miw.ast.sentencias.Sentencia;
import miw.ast.tipos.TipoError;
import miw.ast.tipos.TipoFuncion;
import miw.semantico.TablaSimbolos;

/**
 * Created by quidiello on 3/11/15.
 */
public class VisitorIdentificacion extends AbstractVisitor {

    private TablaSimbolos tablaSimbolos;

    public VisitorIdentificacion() {
        tablaSimbolos = new TablaSimbolos();
    }

    @Override
    public Object visit(DefFuncion defFuncion, Object object) {
        if (!tablaSimbolos.insertar(defFuncion)) {
            new TipoError(defFuncion.getLinea(), defFuncion.getColumna(), "Función '" + defFuncion.getNombre() + "' ya está definida.");
        }

        tablaSimbolos.set();
        TipoFuncion tipoFuncion = (TipoFuncion) defFuncion.getTipo();
        for (Definicion definicion : tipoFuncion.parametros) {
            definicion.accept(this, object);
        }
        for (Definicion definicion : defFuncion.variables) {
            definicion.accept(this, object);
        }

        for (Sentencia sentencia : defFuncion.sentencias) {
            sentencia.accept(this, object);
        }
        tablaSimbolos.reset();

        return null;
    }

    @Override
    public Object visit(DefVariable defVariable, Object object) {
        if (!tablaSimbolos.insertar(defVariable)) {
            new TipoError(defVariable.getLinea(), defVariable.getColumna(), "Variable '" + defVariable.getNombre() +
                    "' ya está definida en este ámbito.");
        }

        return super.visit(defVariable, object);
    }

    @Override
    public Object visit(Variable variable, Object object) {
        Definicion definicion = tablaSimbolos.getDefinicion(variable.nombre);
        if (definicion != null) {
            variable.definicion = definicion;
            variable.setTipo(variable.definicion.getTipo());
        }
        else {
            new TipoError(variable.getLinea(), variable.getColumna(), "No se pudo resolver el identificador '" + variable.nombre + "'.");
        }

        return super.visit(variable, object);
    }

}
