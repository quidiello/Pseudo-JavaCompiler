package miw.ast.tipos;

import miw.ast.AbstractNodoAST;
import miw.ast.expresiones.Expresion;
import miw.visitor.Visitor;

import java.util.List;

/**
 * Created by quidiello on 29/10/15.
 */
public class TipoEntero extends AbstractTipo {

    private static TipoEntero INSTANCE;

    public TipoEntero(Integer linea, Integer columna) {
        super(linea, columna);
    }


    public static TipoEntero getInstance(Integer linea, Integer columna) {
        if (INSTANCE == null) {
            INSTANCE = new TipoEntero(linea, columna);
        }
        return INSTANCE;
    }

    @Override
    public String toString() {
        return "int";
    }

    @Override
    public Object accept(Visitor visitor, Object object) {
        return visitor.visit(this, object);
    }

    @Override
    public String getNombre() {
        return "int";
    }

    @Override
    public String getSufijo() {
        return "i";
    }

    @Override
    public Integer getTamano() {
        return 2;
    }

    @Override
    public boolean esBasico() {
        return true;
    }

    @Override
    public boolean esPromocionable(Tipo tipo) {
        return tipo instanceof TipoEntero || tipo instanceof TipoDoble;
    }

    @Override
    public boolean esLogico() {
        return true;
    }

    @Override
    public Tipo aritmetica() {
        return this;
    }

    @Override
    public Tipo aritmetica(Tipo tipo) {
        if (tipo instanceof TipoError || tipo instanceof  TipoEntero || tipo instanceof  TipoDoble) {
            return tipo;
        }
        if (tipo instanceof TipoCaracter) {
            return this;
        }
        return null;
    }

    @Override
    public Tipo logica() {
        return this;
    }

    @Override
    public Tipo logica(Tipo tipo) {
        if (tipo instanceof TipoError || tipo instanceof TipoEntero) {
            return tipo;
        }
        if (tipo instanceof TipoCaracter) {
            return this;
        }
        return null;
    }

    @Override
    public Tipo asignacion(Tipo tipo) {
        if (tipo instanceof TipoError || tipo instanceof TipoEntero) {
            return tipo;
        }
        if (tipo instanceof TipoCaracter) {
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
            return this;
        }
        return null;
    }

}
