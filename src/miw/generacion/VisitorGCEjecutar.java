package miw.generacion;

import miw.ast.Programa;
import miw.ast.definiciones.DefFuncion;
import miw.ast.definiciones.DefVariable;
import miw.ast.definiciones.Definicion;
import miw.ast.expresiones.Expresion;
import miw.ast.expresiones.Funcion;
import miw.ast.sentencias.*;
import miw.ast.tipos.Tipo;
import miw.ast.tipos.TipoEntero;
import miw.ast.tipos.TipoFuncion;
import miw.ast.tipos.TipoVoid;

import java.io.FileNotFoundException;

/**
 * Created by quidiello on 8/11/15.
 */
public class VisitorGCEjecutar extends AbstractVisitorGC {

    private VisitorGCDireccion visitorGCDireccion;
    private VisitorGCValor visitorGCValor;
    private GeneradorCodigo generadorCodigo;
    private Integer etiquetas;

    public VisitorGCEjecutar(String entrada, String salida) throws FileNotFoundException {
        generadorCodigo = new GeneradorCodigo(entrada, salida);
        visitorGCDireccion = new VisitorGCDireccion(generadorCodigo);
        visitorGCValor = new VisitorGCValor(generadorCodigo, this);
        visitorGCDireccion.setVisitorGCValor(visitorGCValor);
        visitorGCValor.setVisitorGCDireccion(visitorGCDireccion);
        etiquetas = 0;
    }

    @Override
    public Object visit(Programa programa, Object object) {


        for (Definicion definicion : programa.definiciones) {
            if (definicion instanceof DefFuncion) {
                if (definicion.getNombre().equals("main")) {
                    this.generadorCodigo.main();
                    this.generadorCodigo.saltoLinea();
                }
            }
            definicion.accept(this, object);
        }

        return null;
    }

    @Override
    public Object visit(DefVariable defVariable, Object object) {
        if (defVariable.getAmbito() != 0) {
            this.generadorCodigo.comentario(defVariable.getTipo().getNombre() + " " + defVariable.getNombre() + " (offset " + defVariable.getOffset() + ")");
        }

        return null;
    }

    @Override
    public Object visit(DefFuncion defFuncion, Object object) {
        this.generadorCodigo.linea(defFuncion.getLinea());
        this.generadorCodigo.etiqueta(defFuncion.getNombre());
        this.generadorCodigo.enter(defFuncion.getOffsetVariablesLocales());

        TipoFuncion tipoFuncion = (TipoFuncion) defFuncion.getTipo();
        Integer parametros = 0;

        for (DefVariable defVariable : tipoFuncion.parametros) {
            parametros += defVariable.getTipo().getTamano();
            defVariable.accept(this, object);
        }

        for (DefVariable defVariable : defFuncion.variables) {
            defVariable.accept(this, object);
        }

        for (Sentencia sentencia : defFuncion.sentencias) {
            sentencia.accept(this, defFuncion);
        }

        if (tipoFuncion.tipoRetorno instanceof TipoVoid) {
            this.generadorCodigo.ret(0, defFuncion.getOffsetVariablesLocales(), parametros);
        }

        return null;
    }

    @Override
    public Object visit(Read sentenciaRead, Object object) {
        this.generadorCodigo.linea(sentenciaRead.getLinea());

        for (Expresion expresion : sentenciaRead.expresiones) {
            this.generadorCodigo.comentario("* Lectura");
            expresion.accept(visitorGCDireccion, object);
            this.generadorCodigo.in(expresion.getTipo());
            this.generadorCodigo.store(expresion.getTipo());
        }

        return null;
    }

    @Override
    public Object visit(Write sentenciaWrite, Object object) {
        this.generadorCodigo.linea(sentenciaWrite.getLinea());

        for (Expresion expresion : sentenciaWrite.expresiones) {
            this.generadorCodigo.comentario("* Escritura");
            expresion.accept(visitorGCValor, object);
            this.generadorCodigo.out(expresion.getTipo());
        }

        return null;
    }

    @Override
    public Object visit(Asignacion sentenciaAsignacion, Object object) {
        this.generadorCodigo.linea(sentenciaAsignacion.getLinea());

        sentenciaAsignacion.leftValue.accept(visitorGCDireccion, object);
        sentenciaAsignacion.rightValue.accept(visitorGCValor, object);

        Tipo left = sentenciaAsignacion.leftValue.getTipo();
        Tipo right = sentenciaAsignacion.rightValue.getTipo();
        this.generadorCodigo.cast(right, left);
        this.generadorCodigo.store(left);

        return null;
    }

    @Override
    public Object visit(Return sentenciaReturn, Object object) {
        DefFuncion defFuncion = (DefFuncion) object;
        sentenciaReturn.expresion.accept(visitorGCValor, object);
        this.generadorCodigo.ret(sentenciaReturn.expresion.getTipo().getTamano(), defFuncion.getOffsetVariablesLocales(), ((TipoFuncion) defFuncion.getTipo()).offset);

        return null;
    }

    @Override
    public Object visit(If sentenciaIf, Object object) {
        Integer etiqueta = this.etiquetas;
        this.etiquetas += 2;

        sentenciaIf.condicion.accept(visitorGCValor, this);
        this.generadorCodigo.cast(sentenciaIf.condicion.getTipo(), TipoEntero.getInstance(sentenciaIf.getLinea(), sentenciaIf.getColumna()));
        this.generadorCodigo.jz(etiqueta.toString());

        for (Sentencia sentencia : sentenciaIf.sentenciasIf) {
            sentencia.accept(this, object);
        }

        this.generadorCodigo.jmp(String.valueOf(etiqueta + 1));
        this.generadorCodigo.etiqueta(etiqueta.toString());
        etiqueta++;

        for (Sentencia sentencia : sentenciaIf.sentenciasIf) {
            sentencia.accept(this, object);
        }

        this.generadorCodigo.etiqueta(etiqueta.toString());

        return null;
    }

    @Override
    public Object visit(While sentenciaWhile, Object object) {
        Integer etiqueta = this.etiquetas;
        this.etiquetas += 2;

        this.generadorCodigo.etiqueta(etiqueta.toString());
        sentenciaWhile.condicion.accept(visitorGCValor, object);
        this.generadorCodigo.cast(sentenciaWhile.condicion.getTipo(), TipoEntero.getInstance(sentenciaWhile.getLinea(), sentenciaWhile.getColumna()));
        etiqueta++;
        this.generadorCodigo.jz(etiqueta.toString());

        for (Sentencia sentencia : sentenciaWhile.sentencias) {
            sentencia.accept(this, object);
        }
        this.generadorCodigo.jmp(String.valueOf(etiqueta - 1));
        this.generadorCodigo.etiqueta(etiqueta.toString());
        etiqueta++;

        return null;
    }

    @Override
    public Object visit(Funcion sentenciaFuncion, Object object) {
        sentenciaFuncion.accept(visitorGCValor, this);

        TipoFuncion tipoFuncion = (TipoFuncion) sentenciaFuncion.variable.definicion.getTipo();
        if (! (tipoFuncion.tipoRetorno instanceof TipoVoid) ) {
            this.generadorCodigo.pop(tipoFuncion.tipoRetorno);
        }

        return null;
    }

}
