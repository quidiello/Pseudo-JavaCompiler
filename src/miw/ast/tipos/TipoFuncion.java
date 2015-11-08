package miw.ast.tipos;

import miw.ast.AbstractNodoAST;
import miw.ast.definiciones.DefVariable;
import miw.ast.expresiones.Expresion;
import miw.visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quidiello on 29/10/15.
 */
public class TipoFuncion extends AbstractTipo {

    public List<DefVariable> parametros;
    public Tipo tipoRetorno;
    public Integer offset;

    public TipoFuncion(Integer linea, Integer columna, Tipo tipoRetorno, List<DefVariable> parametros) {
        super(linea, columna);
        this.parametros = parametros;
        this.tipoRetorno = tipoRetorno;
        this.offset = 0;
    }

    public TipoFuncion(Integer linea, Integer columna, Tipo tipoRetorno) {
        this(linea, columna, tipoRetorno, new ArrayList<DefVariable>());
    }

    @Override
    public Object accept(Visitor visitor, Object object) {
        return visitor.visit(this, object);
    }

    @Override
    public String getNombre() {
        return "función";
    }

    @Override
    public Tipo parentesis (List<Expresion> expresiones) {
        if(parametros.size() != expresiones.size()) {
            return null;
        }
        for (int i = 0; i < parametros.size(); i++) {
            if(! expresiones.get(i).getTipo().esPromocionable(parametros.get(i).getTipo())) {
                return null;
            }
        }
        return this.tipoRetorno;
    }

}
