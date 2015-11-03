package miw.ast.definiciones;

import miw.ast.sentencias.Sentencia;
import miw.ast.tipos.Tipo;
import miw.ast.tipos.TipoFuncion;
import miw.visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quidiello on 29/10/15.
 */
public class DefFuncion extends AbstractDefinicion implements Sentencia {

    public List<Sentencia> sentencias;
    public List<DefVariable> variables;

    public DefFuncion(Integer linea, Integer columna, String nombre, Tipo tipo, List<Sentencia> sentencias, List<DefVariable> variables) {
        super(linea, columna, nombre, tipo);
        this.sentencias = sentencias;
        this.variables = variables;
    }

    public DefFuncion(Integer linea, Integer columna, String nombre, Tipo tipo, List<Sentencia> sentencias) {
        this(linea, columna, nombre, tipo, sentencias, new ArrayList<DefVariable>());
    }

    public String toString() {
        TipoFuncion tipoFuncion = (TipoFuncion) getTipo();
        String s = tipoFuncion.tipoRetorno + " " + getNombre() + "(";
        if(! tipoFuncion.parametros.isEmpty()) {
            for (DefVariable parametro : tipoFuncion.parametros) {
                s += parametro + ", ";
            }
            s = s.substring(0, s.length()-2);
        }
        s += ") {";
        for (DefVariable variable : variables) {
            s += "\n" + variable;
        }
        for (Sentencia sentencia : sentencias) {
            s += "\n" + sentencia;
        }
        s += "}";

        return s;
    }

    @Override
    public void accept(Visitor visitor, Object object) {
        visitor.visit(this, object);
    }

}
