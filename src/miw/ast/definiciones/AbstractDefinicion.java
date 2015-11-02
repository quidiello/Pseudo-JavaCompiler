package miw.ast.definiciones;

import miw.ast.AbstractNodoAST;
import miw.ast.tipos.Tipo;

/**
 * Created by quidiello on 31/10/15.
 */
public class AbstractDefinicion extends AbstractNodoAST implements Definicion {

    private String nombre;
    private Tipo tipo;

    public AbstractDefinicion(Integer linea, Integer columna, String nombre, Tipo tipo) {
        super(linea, columna);
        this.nombre = nombre;
        this.tipo = tipo;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public Tipo getTipo() {
        return tipo;
    }
}
