package miw.ast.expresiones;

import miw.ast.sentencias.Sentencia;
import miw.visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quidiello on 31/10/15.
 */
public class Funcion extends AbstractExpresion implements Sentencia {

    public Variable variable;
    public List<Expresion> argumentos;

    public Funcion(Integer linea, Integer columna, Variable variable, List<Expresion> argumentos) {
        super(linea, columna);
        this.variable = variable;
        this.argumentos = argumentos;
    }

    public Funcion(Integer linea, Integer columna, Variable variable) {
        this(linea, columna, variable, new ArrayList<Expresion>());
    }

    @Override
    public String toString() {
        String s = variable.nombre + "(";
        if(!argumentos.isEmpty()) {
            for (Expresion argumento: argumentos) {
                s += argumento + ", ";
            }
            s = s.substring(0, s.length()-2);
        }
        s += ")";
        return s;
    }

    @Override
    public Object accept(Visitor visitor, Object object) {
        return visitor.visit(this, object);
    }
}
