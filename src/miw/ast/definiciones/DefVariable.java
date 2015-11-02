package miw.ast.definiciones;

import miw.ast.AbstractNodoAST;
import miw.ast.tipos.Tipo;

/**
 * Created by quidiello on 29/10/15.
 */
public class DefVariable extends AbstractDefinicion {

    public Integer offset;
    public Integer ambito;

    public DefVariable(Integer linea, Integer columna, String nombre, Tipo tipo) {
        super(linea, columna, nombre, tipo);
    }

    public String toString() {
        return getTipo().toString() + " " + getNombre();
    }
}
