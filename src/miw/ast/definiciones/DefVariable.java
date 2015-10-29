package miw.ast.definiciones;

import miw.ast.tipos.Tipo;

/**
 * Created by quidiello on 29/10/15.
 */
public class DefVariable {

    public String nombre;
    public Tipo tipo;

    public DefVariable(String nombre, Tipo tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }
}
