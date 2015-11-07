package miw.ast.tipos;

import miw.ast.AbstractNodoAST;
import miw.ast.expresiones.Expresion;
import miw.visitor.Visitor;

import java.util.List;

/**
 * Created by quidiello on 29/10/15.
 */
public class TipoDoble extends AbstractTipo {

    private static TipoDoble INSTANCE;

    private TipoDoble(Integer linea, Integer columna) {
        super(linea, columna);
    }

    public static TipoDoble getInstance(Integer linea, Integer columna) {
        if(INSTANCE == null) {
            INSTANCE = new TipoDoble(linea, columna);
        }
        return INSTANCE;
    }

    @Override
    public String toString() {
        return "double";
    }

    @Override
    public Object accept(Visitor visitor, Object object) {
        return visitor.visit(this, object);
    }

    @Override
    public String getNombre() {
        return "double";
    }

    @Override
    public String getSufijo() {
        return "f";
    }

    @Override
    public Integer getTamano() {
        return 4;
    }

    @Override
    public boolean esBasico() {
        return true;
    }

    @Override
    public boolean esPromocionable(Tipo tipo) {
        return tipo instanceof TipoDoble;
    }

    @Override
    public boolean esLogico() {
        return false;
    }

    @Override
    public Tipo aritmetica() {
        return this;
    }

    @Override
    public Tipo aritmetica(Tipo tipo) {
        if (tipo instanceof TipoError || tipo instanceof  TipoDoble) {
            return tipo;
        }
        if (tipo instanceof TipoCaracter || tipo instanceof TipoEntero) {
            return this;
        }
        return null;
    }

    @Override
    public Tipo asignacion(Tipo tipo) {
        if (tipo instanceof TipoError || tipo instanceof TipoDoble) {
            return tipo;
        }
        if (tipo instanceof TipoEntero || tipo instanceof TipoCaracter) {
            return this;
        }
        return null;
    }

    @Override
    public Tipo cast(Tipo tipo) {
        if (tipo instanceof TipoError) {
            return tipo;
        }
        if( tipo instanceof TipoCaracter || tipo instanceof TipoEntero || tipo instanceof TipoDoble) {
            return this;
        }
        return null;
    }

    @Override
    public Tipo comparacion (Tipo tipo) {
        if (tipo instanceof TipoError || tipo instanceof  TipoEntero) {
            return tipo;
        }
        if (tipo instanceof TipoCaracter || tipo instanceof TipoDoble) {
            return TipoEntero.getInstance(this.getLinea(), this.getColumna());
        }
        return null;
    }

    @Override
    public Tipo corchetes (Tipo tipo) {
        return null;
    }

    @Override
    public Tipo parentesis(List<Expresion> expresiones) {
        return null;
    }

}
