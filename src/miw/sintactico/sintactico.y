%{
import miw.lexico.Lexico;
import miw.ast.*;
import miw.ast.tipos.TipoError;
import java.util.*;

%}
      
%token CTE_ENTERA CTE_CARACTER CTE_DOBLE IDENTIFICADOR
%token INT DOUBLE CHAR VOID
%token IF ELSE WHILE FOR
%token WRITE READ RETURN VOID MAIN
%token IGUAL DISTINTO MAYOR_IGUAL MENOR_IGUAL OR AND

%left '='
%left AND OR '!'
%left IGUAL MAYOR_IGUAL MENOR_IGUAL DISTINTO
%left '+' '-'
%left '*' '/' '%'
%right MENOS_UNARIO
%nonassoc '[' ']'
%nonassoc '(' ')'

%%

programa:           declaraciones VOID MAIN '(' ')' '{' sentencias '}'
                    ;

declaraciones:      declaraciones declaracion ';'
                    | /* vacío */
                    ;

declaracion:        tipo identificadores
                    ;

tipo:               INT
                    | DOUBLE
                    | CHAR
                    | tipo '[' CTE_ENTERA ']'
                    ;

identificadores:    IDENTIFICADOR
                    | identificadores ',' IDENTIFICADOR
                    ;

sentencias:         sentencia
                    | sentencias sentencia
                    ;

sentencia:          read ';'
                    | write ';'
                    | asignacion ';'
                    ;

asignacion:         expresion '=' expresion
                    ;

read:               READ expresiones
                    ;

write:              WRITE expresiones
                    ;

expresiones:        expresion
                    | expresiones ',' expresion
                    ;

expresion:  expresion '+' expresion
            | expresion '-' expresion
            | expresion '*' expresion
            | expresion '/' expresion
            | expresion '%' expresion
            | '-' expresion %PREC MENOS_UNARIO
            | expresion '[' expresion ']'
            | '(' expresion ')'
            | CTE_ENTERA
            | CTE_DOBLE
            | CTE_CARACTER
            | IDENTIFICADOR
            ;


%%
/**
* Referencia al analizador léxico
*/
private Lexico lexico;
/**
* Referencia al ast
*/
public NodoAST ast;

// * Llamada al analizador léxico
private int yylex () {
    int token=0;
    try { 
	token=lexico.yylex(); 
    } catch(Throwable e) {
		new TipoError(lexico.getLinea(), lexico.getColumna(),
		"Error Léxico en línea " + lexico.getLinea()+
        		" y columna "+lexico.getColumna()+":\n\t"+e);
    }
    return token;
}

// * Manejo de Errores Sintácticos
public void yyerror (String error) {
	new TipoError(lexico.getLinea(), lexico.getColumna(),
    		"Error Sintáctico en línea " + lexico.getLinea()+
            		" y columna "+lexico.getColumna()+":\n\t"+error);
}

// * Constructor del Sintáctico
public Parser(Lexico lexico) {
	this.lexico = lexico;
	lexico.setParser(this);
}

// * El yyparse original no es público
public int parse() {
	return yyparse();
}

// * El yylval no es un atributo público
public void setYylval(Object yylval) {
	this.yylval=yylval;
}

// * El yylval no es un atributo público
public Object getYylval() {
	return this.yylval;
}