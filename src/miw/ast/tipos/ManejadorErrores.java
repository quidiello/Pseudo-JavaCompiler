package miw.ast.tipos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quidiello on 27/10/15.
 */
public class ManejadorErrores {

    public List<TipoError> errores;

    private static ManejadorErrores INSTANCE = new ManejadorErrores();

    private ManejadorErrores() {
        this.errores = new ArrayList<TipoError>();
    }

    public static ManejadorErrores getInstance() {
        return INSTANCE;
    }

    public boolean addError(TipoError error) {
        return this.errores.add(error);
    }

    public boolean existeErrores() {
        return !this.errores.isEmpty();
    }

    public  List<TipoError> getErrores() {
        return errores;
    }
}
