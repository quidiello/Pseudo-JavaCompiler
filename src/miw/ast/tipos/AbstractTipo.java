package miw.ast.tipos;

import miw.ast.AbstractNodoAST;
import miw.ast.expresiones.Expresion;

import java.util.List;

/**
 * Created by quidiello on 4/11/15.
 */
public abstract class AbstractTipo extends AbstractNodoAST implements Tipo {

    public AbstractTipo(Integer linea, Integer columna) {
        super(linea, columna);
    }

    @Override
    public String getNombre() {
        return "nombre";
    }

    @Override
    public String getSufijo() {
        return "sufijo";
    }

    @Override
    public Integer getTamano() {
        return 0;
    }

    @Override
    public boolean esBasico() {
        return false;
    }

    @Override
    public boolean esPromocionable(Tipo tipo) {
        return false;
    }

    @Override
    public boolean esLogico() {
        return false;
    }

    @Override
    public Tipo aritmetica() {
        return null;
    }

    @Override
    public Tipo aritmetica(Tipo tipo) {
        return null;
    }

    @Override
    public Tipo logica() {
        return null;
    }

    @Override
    public Tipo logica(Tipo tipo) {
        return null;
    }

    @Override
    public Tipo asignacion(Tipo tipo) {
        return null;
    }

    @Override
    public Tipo cast(Tipo tipo) {
        return null;
    }

    @Override
    public Tipo comparacion (Tipo tipo) {
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
