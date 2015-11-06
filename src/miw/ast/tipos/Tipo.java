package miw.ast.tipos;

import miw.ast.NodoAST;
import miw.ast.expresiones.Expresion;

import java.util.List;

/**
 * Created by quidiello on 29/10/15.
 */
public interface Tipo extends NodoAST {

    String getNombre();

    String getSufijo();

    Integer getTamano();

    boolean esBasico();

    boolean esPromocionable(Tipo tipo);

    boolean esLogico();

    Tipo aritmetica();
    Tipo aritmetica(Tipo tipo);

    Tipo logica();
    Tipo logica(Tipo tipo);

    Tipo asignacion(Tipo tipo);

    Tipo cast(Tipo tipo);

    Tipo comparacion (Tipo tipo);

    Tipo corchetes (Tipo tipo);

    Tipo parentesis(List<Expresion> expresiones);

}
