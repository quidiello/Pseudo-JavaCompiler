package miw.ast;

import miw.ast.expresiones.Expresion;
import miw.ast.sentencias.Sentencia;

import java.util.List;

/**
 * Created by ast on 26/10/15.
 */
public class Function extends AbstractNodoAST {

    public String nombre;
    public String tipoRetorno;
    public List<Expresion> parametros;
    public List<Sentencia> sentencias;


    public Function(Integer linea, Integer columna, String nombre, String tipoRetorno, List<Sentencia> sentencias, List<Expresion> parametros) {
        super(linea, columna);
        this.nombre = nombre;
        this.tipoRetorno = tipoRetorno;
        this.parametros = parametros;
        this.sentencias = sentencias;
    }

    public Function(Integer linea, Integer columna, String nombre, String tipoRetorno, List<Sentencia> sentencias) {
        super(linea, columna);
        this.nombre = nombre;
        this.tipoRetorno = tipoRetorno;
        this.parametros = null;
        this.sentencias = sentencias;
    }

    public String toString() {
        String s = tipoRetorno + " " + nombre + "(";
        if (parametros != null) {
            for (Expresion parametro : parametros) {
                s += parametro + ", ";
            }
            s = s.substring(0, s.length()-2);
        }
        s += ") {";

        for (Sentencia sentencia : sentencias) {
            s += "\n\t" + sentencia;
        }
        return s + "\n}";
    }
}
