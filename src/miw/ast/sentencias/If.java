package miw.ast.sentencias;

import miw.ast.AbstractNodoAST;
import miw.ast.expresiones.Expresion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ast on 26/10/15.
 */
public class If extends AbstractNodoAST implements Sentencia {

    public Expresion condicion;
    public List<Sentencia> sentenciasIf;
    public List<Sentencia> sentenciasElse;

    public If(Integer line, Integer column, Expresion condicion, List<Sentencia> sentenciasIf, List<Sentencia> sentenciasElse) {
        super(line, column);
        this.condicion = condicion;
        this.sentenciasIf = sentenciasIf;
        this.sentenciasElse = sentenciasElse;
    }

    public If(Integer line, Integer column, Expresion condicion, List<Sentencia> sentenciasIf) {
        this(line, column, condicion, sentenciasIf, new ArrayList<Sentencia>());
    }

    @Override
    public String toString() {
        String s = "if (" + condicion + ") {";
        for (Sentencia sentenciaIf : sentenciasIf) {
            s += "\n\t" + sentenciaIf + "; ";
        }
        if(!sentenciasElse.isEmpty()) {
            s += "\n} else {";
            for (Sentencia sentenciaElse : sentenciasElse) {
                s += "\n\t" + sentenciaElse + "; ";
            }
        }
        s += "\n}";
        return s;
    }
}
