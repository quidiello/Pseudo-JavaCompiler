package miw.ast.tipos;

/**
 * Created by quidiello on 29/10/15.
 */
public class TipoCaracter implements Tipo {

    private static TipoCaracter INSTANCE = new TipoCaracter();

    private TipoCaracter() {}

    public static TipoCaracter getInstance() {
        return INSTANCE;
    }

}
