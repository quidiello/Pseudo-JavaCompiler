package miw.ast.tipos;

/**
 * Created by quidiello on 29/10/15.
 */
public class TipoDoble implements Tipo {

    private static TipoDoble INSTANCE = new TipoDoble();

    private TipoDoble() {}

    public static TipoDoble getInstance() {
        return INSTANCE;
    }

    @Override
    public String toString() {
        return "double";
    }

}
