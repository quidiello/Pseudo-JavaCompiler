package miw.visitor;

import miw.ast.Programa;
import miw.ast.definiciones.DefFuncion;
import miw.ast.definiciones.DefVariable;
import miw.ast.expresiones.Funcion;
import miw.ast.expresiones.Variable;
import miw.ast.expresiones.literales.LiteralCaracter;
import miw.ast.expresiones.literales.LiteralDoble;
import miw.ast.expresiones.literales.LiteralEntero;
import miw.ast.expresiones.operadores.*;
import miw.ast.sentencias.*;
import miw.ast.tipos.*;

/**
 * Created by quidiello on 2/11/15.
 */
public interface Visitor {

    Object visit (Programa programa, Object object);

    // Literales
    Object visit (LiteralCaracter literalCaracter, Object object);
    Object visit (LiteralEntero literalEntero, Object object);
    Object visit (LiteralDoble literalDoble, Object object);

    // Expresiones
    Object visit (Variable variable, Object object);

    // Operadores
    Object visit (OperadorAccesoArray operadorAccesoArray, Object object);
    Object visit (OperadorAritmetico operadorAritmetico, Object object);
    Object visit (OperadorComparacion operadorComparacion, Object object);
    Object visit (OperadorLogico operadorLogico, Object object);
    Object visit (OperadorUnarioCast operadorUnarioCast, Object object);
    Object visit (OperadorUnarioNegacion operadorUnarioNegacion, Object object);
    Object visit (OperadorUnarioNegativo operadorUnarioNegativo, Object object);

    // Sentencias
    Object visit (Asignacion sentenciaAsignacion, Object object);
    Object visit (Funcion sentenciaFuncion, Object object);
    Object visit (Return sentenciaReturn, Object object);
    Object visit (If sentenciaIf, Object object);
    Object visit (While sentenciaWhile, Object object);
    Object visit (Read sentenciaRead, Object object);
    Object visit (Write sentenciaWrite, Object object);

    // Definiciones
    Object visit (DefVariable defVariable, Object object);
    Object visit (DefFuncion defFuncion, Object object);

    // Tipos
    Object visit (TipoArray tipoArray, Object object);
    Object visit (TipoCaracter tipoCaracter, Object object);
    Object visit (TipoDoble tipoDoble, Object object);
    Object visit (TipoEntero tipoEntero, Object object);
    Object visit (TipoFuncion tipoFuncion, Object object);
    Object visit (TipoVoid tipoVoid, Object object);
    Object visit (TipoError tipoError, Object object);

}
