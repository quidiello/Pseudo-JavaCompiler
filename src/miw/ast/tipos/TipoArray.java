package miw.ast.tipos;

import com.sun.corba.se.spi.ior.ObjectKey;
import miw.ast.AbstractNodoAST;
import miw.ast.expresiones.Expresion;
import miw.visitor.Visitor;

import java.util.List;

/**
 * Created by quidiello on 29/10/15.
 */
public class TipoArray extends AbstractTipo {

    public Tipo tipo;
    public Integer size;

    public TipoArray(Integer linea, Integer columna, Tipo tipo, Integer size) {
        super(linea, columna);
        this.tipo = tipo;
        this.size = size;
    }

    @Override
    public String toString() {
        return tipo.toString() + "[" + size + "]";
    }

    @Override
    public Object accept(Visitor visitor, Object object) {
        return visitor.visit(this, object);
    }

    @Override
    public String getNombre() {
        return "array";
    }

    @Override
    public Tipo corchetes (Tipo tipo) {
        if (tipo instanceof TipoError) {
            return tipo;
        }
        if (tipo.esPromocionable(TipoEntero.getInstance(this.getLinea(), this.getColumna()))) {
            return this.tipo;
        }
        return null;
    }

    @Override
    public Integer getTamano() {
        return size * tipo.getTamano();
    }

}
