package miw.ast.tipos;

import miw.ast.AbstractNodoAST;

/**
 * Created by quidiello on 27/10/15.
 */
public class TipoError extends AbstractNodoAST implements Tipo{

    public String mensaje;

    public TipoError(Integer linea, Integer columna, String mensaje) {
        super(linea, columna);
        this.mensaje = mensaje;

        ManejadorErrores manejadorErrores = ManejadorErrores.getInstance();
        manejadorErrores.addError(this);
    }

    @Override
    public String toString() {
        return "(línea: " + getLinea() + ", columna: " + getColumna() +") " + mensaje;
    }
}