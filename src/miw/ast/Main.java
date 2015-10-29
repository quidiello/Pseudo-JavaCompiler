package miw.ast;

import miw.ast.definiciones.DefVariable;
import miw.ast.expresiones.Expresion;
import miw.ast.expresiones.Variable;
import miw.ast.expresiones.literales.LiteralEntero;
import miw.ast.expresiones.operadores.OperadorUnarioNegativo;
import miw.ast.expresiones.operadores.OperadorAritmetico;
import miw.ast.sentencias.Asignacion;
import miw.ast.sentencias.Read;
import miw.ast.sentencias.Sentencia;
import miw.ast.sentencias.Write;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ast on 26/10/15.
 */
public class Main {

    public static void main(String[] args) {

        List<Sentencia> sentencias = new ArrayList<Sentencia>();
        List<Expresion> expresions;

        //read a,b
        expresions = new ArrayList<Expresion>();
        expresions.add(new Variable(2,10,"a"));
        expresions.add(new Variable(2,12,"b"));
        sentencias.add(new Read(2,5, expresions));

        //a = (-b+3)*c/2
        Expresion leftValue = new Variable(3,5,"a");
        Expresion rightValue;
        //-b+3
        Expresion sum = new OperadorAritmetico(3,12,
                new OperadorUnarioNegativo(3,11,new Variable(3,11,"b")),
                new LiteralEntero(3,13,3),"+");
        Expresion multiplication = new OperadorAritmetico(3,15,
                sum,
                new Variable(3,16,"c"),"*");
        rightValue = new OperadorAritmetico(3,17,
                multiplication,
                new LiteralEntero(3,18,2),"/");
        sentencias.add(new Asignacion(3,7,leftValue,rightValue));

        //write a,c*2
        multiplication = new OperadorAritmetico(4,14,
                new Variable(4,13,"c"),
                new LiteralEntero(4,15,2),"*");
        expresions = new ArrayList<Expresion>();
        expresions.add(new Variable(4,12,"a"));
        expresions.add(multiplication);
        sentencias.add(new Write(4,5, expresions));

        Programa main = new Programa(1,1,new ArrayList<DefVariable>(),sentencias);
        System.out.println(main);
    }
}
