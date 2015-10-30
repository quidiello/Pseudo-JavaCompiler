package miw.ast.tipos;

/**
 * Created by quidiello on 29/10/15.
 */
public class TipoArray implements Tipo {

    public Tipo tipo;
    public Integer size;

    public TipoArray(Tipo tipo, Integer size) {
        this.tipo = tipo;
        this.size = size;
    }

    @Override
    public String toString() {
        return tipo.toString() + "[" + size + "]";
    }

}
