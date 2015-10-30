package miw.ast.tipos;

/**
 * Created by quidiello on 29/10/15.
 */
public class TipoEntero implements Tipo {

    private static TipoEntero INSTANCE = new TipoEntero();

    private TipoEntero() {}

    public static TipoEntero getInstance() {
        return INSTANCE;
    }

    @Override
    public String toString() {
        return "int";
    }

}
