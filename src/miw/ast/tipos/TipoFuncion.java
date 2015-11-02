package miw.ast.tipos;

import miw.ast.AbstractNodoAST;
import miw.ast.definiciones.DefVariable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quidiello on 29/10/15.
 */
public class TipoFuncion extends AbstractNodoAST implements Tipo {

    public List<DefVariable> parametros;
    public Tipo tipoRetorno;

    public TipoFuncion(Integer linea, Integer columna, Tipo tipoRetorno, List<DefVariable> parametros) {
        super(linea, columna);
        this.parametros = parametros;
        this.tipoRetorno = tipoRetorno;
    }

    public TipoFuncion(Integer linea, Integer columna, Tipo tipoRetorno) {
        this(linea, columna, tipoRetorno, new ArrayList<DefVariable>());
    }

}
