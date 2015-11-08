package miw.generacion;

import miw.ast.tipos.Tipo;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Created by quidiello on 8/11/15.
 */
public class GeneradorCodigo {

    private PrintWriter printWriter;

    public GeneradorCodigo(String input, String output) throws FileNotFoundException {
        printWriter = new PrintWriter(output);
        printWriter.println("#source '" + input +"'");
        saltoLinea();
        printWriter.flush();
    }

    public void saltoLinea() {
        printWriter.println();
        printWriter.flush();
    }

    public void push(Character character) {
        printWriter.println("\tpushb " + (int) character);
        printWriter.flush();
    }

    public void push(Integer integer) {
        printWriter.println("\tpushi " + integer);
        printWriter.flush();
    }

    public void push(Double doble) {
        printWriter.println("\tpushf " + doble);
        printWriter.flush();
    }

    public void pusha(Integer direccion) {
        printWriter.println("\tpusha " + direccion);
        printWriter.flush();
    }

    public void pushbp() {
        printWriter.println("\tpush bp");
    }

    public void load(Tipo tipo) {
        printWriter.println("\tload" + tipo.getSufijo());
        printWriter.flush();
    }

    public void store(Tipo tipo) {
        printWriter.println("\tstore" + tipo.getSufijo());
        printWriter.flush();
    }

    public void add(Tipo tipo) {
        printWriter.println("\tadd" + tipo.getSufijo());
        printWriter.flush();
    }

    public void sub(Tipo tipo) {
        printWriter.println("\tsub" + tipo.getSufijo());
        printWriter.flush();
    }

    public void mul(Tipo tipo) {
        printWriter.println("\tmul" + tipo.getSufijo());
        printWriter.flush();
    }

    public void div(Tipo tipo) {
        printWriter.println("\tdiv" + tipo.getSufijo());
        printWriter.flush();
    }

    public void modi() {
        printWriter.println("\tmodi");
        printWriter.flush();
    }

    public void out(Tipo tipo) {
        printWriter.println("\tout" + tipo.getSufijo());
        printWriter.flush();
    }

    public void  in(Tipo tipo) {
        printWriter.println("\tin" + tipo.getSufijo());
        printWriter.flush();
    }

    public void enter(Integer integer) {
        printWriter.println("\tenter " + integer);
        printWriter.flush();
    }

    public void call(String id) {
        printWriter.println("\tcall " + id);
        printWriter.flush();
    }

    public void halt() {
        printWriter.println("halt");
        printWriter.flush();
    }

    public void cast(Tipo inicial, Tipo destino) {
        String sufijo = destino.getSufijo();
        switch (inicial.getSufijo()) {
            case "b":
                if (sufijo.equals("i")) {
                    printWriter.println("\tb2i");
                }
                else if (sufijo.equals("f")) {
                    printWriter.println("\tb2i");
                    printWriter.println("\tb2f");
                }
                break;
            case "i":
                if (sufijo.equals("b")) {
                    printWriter.println("\ti2b");
                }
                else if(sufijo.equals("f")) {
                    printWriter.println("\ti2f");
                }
                break;
            case "f":
                if (sufijo.equals("i")) {
                    printWriter.println("\tf2i");
                }
                else if (sufijo.equals("b")) {
                    printWriter.println("\tf2i");
                    printWriter.println("\ti2b");
                }
                break;
        }
    }

    public void gt(Tipo tipo) {
        printWriter.println("\tgt" + tipo.getSufijo());
        printWriter.flush();
    }

    public void lt(Tipo tipo) {
        printWriter.println("\tlt" + tipo.getSufijo());
        printWriter.flush();
    }

    public void ge(Tipo tipo) {
        printWriter.println("\tge" + tipo.getSufijo());
        printWriter.flush();
    }

    public void le(Tipo tipo) {
        printWriter.println("\tle" + tipo.getSufijo());
        printWriter.flush();
    }

    public void ne(Tipo tipo) {
        printWriter.println("\tne" + tipo.getSufijo());
        printWriter.flush();
    }

    public void eq(Tipo tipo) {
        printWriter.println("\teq" + tipo.getSufijo());
        printWriter.flush();
    }

    public void and() {
        printWriter.println("\tand");
        printWriter.flush();
    }

    public void or() {
        printWriter.println("\tor");
        printWriter.flush();
    }

    public void not() {
        printWriter.println("\tnot");
        printWriter.flush();
    }

    public void ret(Integer retorno, Integer locales, Integer parametros) {
        printWriter.println("\tret " + retorno + "," + locales + "," + parametros);
        printWriter.flush();
    }

    public void jmp(String id) {
        printWriter.println("\tjmp " + id);
        printWriter.flush();
    }

    public void jz(String id) {
        printWriter.println("\tjz " + id);
        printWriter.flush();
    }

    public void jnz(String id) {
        printWriter.println("\tjnz " + id);
        printWriter.flush();
    }

    public void etiqueta(String id) {
        printWriter.println(id + ":");
        printWriter.flush();
    }

    public void linea(Integer integer) {
        printWriter.println("#line " + integer);
        printWriter.flush();
    }

    public void comentario(String comentario) {
        printWriter.println("\t' " + comentario);
        printWriter.flush();
    }

    public void aritmetica(Tipo tipo, String operador) {
        switch (operador) {
            case "+":
                add(tipo);
                break;
            case "-":
                sub(tipo);
                break;
            case "*":
                mul(tipo);
                break;
            case "/":
                div(tipo);
                break;
            case "%":
                modi();
                break;
        }
    }

    public void logica(String operador) {
        switch (operador) {
            case "&&":
                and();
                break;
            case "||":
                or();
                break;
        }
    }

    public void comparacion(Tipo tipo, String operador) {
        switch (operador) {
            case "==":
                eq(tipo);
                break;
            case "!=":
                ne(tipo);
                break;
            case "<":
                lt(tipo);
                break;
            case ">":
                gt(tipo);
                break;
            case "<=":
                le(tipo);
                break;
            case ">=":
                ge(tipo);
                break;
        }
    }

    public void main() {
        printWriter.println("call main");
        halt();
        printWriter.flush();
    }

    public void pop(Tipo tipo) {
        printWriter.println("\tpop" + tipo.getSufijo());
        printWriter.flush();
    }

}
