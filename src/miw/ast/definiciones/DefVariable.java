package miw.ast.definiciones;

import miw.ast.AbstractNodoAST;
import miw.ast.tipos.Tipo;

/**
 * Created by quidiello on 29/10/15.
 */
public class DefVariable extends AbstractNodoAST implements Definicion {

    public String nombre;
    public Tipo tipo;

    public DefVariable(Integer linea, Integer columna, String nombre, Tipo tipo) {
        super(linea, columna);
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public String toString() {
        return tipo.toString() + " " + nombre;
    }
}
