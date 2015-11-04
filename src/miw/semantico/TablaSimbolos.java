package miw.semantico;

import miw.ast.definiciones.Definicion;

import java.util.*;

/**
 * Created by quidiello on 3/11/15.
 */
public class TablaSimbolos {

    private List<Map<String, Definicion>> tabla;
    private Map<String, Definicion> actual;

    public TablaSimbolos() {
        tabla = new ArrayList<Map<String, Definicion>>();
        set();
    }

    public boolean insertar(Definicion definicion) {

        if (! actual.containsKey(definicion.getNombre())) {
            actual.put(definicion.getNombre(), definicion);
            return true;
        }

        return false;

    }

    public Definicion getDefinicion(String nombre) {

        ListIterator iterator = tabla.listIterator(tabla.size());
        Definicion definicion = null;

        while (iterator.hasPrevious() && definicion == null) {
            definicion = ((Map<String,Definicion>)iterator.previous()).get(nombre);
        }

        return definicion;
    }

    public void set() {
        actual = new HashMap<String,Definicion>();
        tabla.add(actual);
    }

    public void reset() {
        int prev = tabla.size() - 2;
        if (prev >= 0) {
            actual = tabla.get(prev);
            actual.remove(prev + 1);
        }
    }

}
