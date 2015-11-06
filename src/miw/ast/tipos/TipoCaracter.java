package miw.ast.tipos;

import miw.ast.AbstractNodoAST;
import miw.ast.expresiones.Expresion;
import miw.visitor.Visitor;

import java.util.List;

/**
 * Created by quidiello on 29/10/15.
 */
public class TipoCaracter extends AbstractTipo {

    private static TipoCaracter INSTANCE;

    private TipoCaracter(Integer linea, Integer columna) {
        super(linea, columna);
    }

    public static TipoCaracter getInstance(Integer linea, Integer columna) {
        if(INSTANCE == null) {
            INSTANCE = new TipoCaracter(linea, columna);
        }
        return INSTANCE;
    }

    @Override
    public String toString() {
        return "char";
    }

    @Override
    public void accept(Visitor visitor, Object object) {
        visitor.visit(this, object);
    }

    @Override
    public String getNombre() {
        return "char";
    }

    @Override
    public String getSufijo() {
        return "b";
    }

    @Override
    public Integer getTamano() {
        return 1;
    }

    @Override
    public boolean esBasico() {
        return true;
    }

    @Override
    public boolean esPromocionable(Tipo tipo) {
        return tipo instanceof TipoEntero || tipo instanceof TipoCaracter || tipo instanceof TipoDoble;
    }

    @Override
    public boolean esLogico() {
        return true;
    }

    @Override
    public Tipo aritmetica() {
        return TipoEntero.getInstance(this.getLinea(), this.getColumna());
    }

    @Override
    public Tipo aritmetica(Tipo tipo) {
        if (tipo instanceof TipoError || tipo instanceof  TipoEntero || tipo instanceof  TipoDoble) {
            return tipo;
        }
        if (tipo instanceof TipoCaracter) {
            return TipoEntero.getInstance(this.getLinea(), this.getColumna());
        }
        return null;
    }

    @Override
    public Tipo logica() {
        return TipoEntero.getInstance(this.getLinea(), this.getColumna());
    }

    @Override
    public Tipo logica(Tipo tipo) {
        if (tipo instanceof TipoError || tipo instanceof TipoEntero) {
            return tipo;
        }
        if (tipo instanceof TipoCaracter) {
            return TipoEntero.getInstance(this.getLinea(), this.getColumna());
        }
        return null;
    }

    @Override
    public Tipo asignacion(Tipo tipo) {
        if (tipo instanceof TipoError || tipo instanceof TipoCaracter) {
            return tipo;
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

}
