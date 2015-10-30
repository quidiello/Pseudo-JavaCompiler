%{
import miw.lexico.Lexico;
import miw.ast.*;
import miw.ast.definiciones.*;
import miw.ast.expresiones.*;
import miw.ast.expresiones.literales.*;
import miw.ast.expresiones.operadores.*;
import miw.ast.sentencias.*;
import miw.ast.tipos.*;
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

programa:           declaraciones VOID MAIN '(' ')' '{' sentencias '}'  { this.ast = new Programa(lexico.getLinea(), lexico.getColumna(), (ArrayList<DefVariable>)$1, (ArrayList<Sentencia>)$7); System.err.println(this.ast.toString());}
                    ;

declaraciones:      declaraciones declaracion ';'   { List<Definicion> declaraciones = (ArrayList<Definicion>)$1;
                                                    declaraciones.addAll((ArrayList<Definicion>)$2);
                                                    $$ = declaraciones; }
                    | /* vacío */                   { $$ = new ArrayList<Definicion>(); }
                    ;

declaracion:        tipo identificadores            { List<Variable> variables = (ArrayList <Variable>)$2;
                                                    List<DefVariable> declaraciones = new ArrayList <DefVariable>();
                                                    for(Variable variable : variables) {
                                                        declaraciones.add(new DefVariable(lexico.getLinea(), lexico.getColumna(), variable.nombre, (Tipo)$1));
                                                    }
                                                    $$ = declaraciones; }
                    ;

tipo:               INT                             { $$ = TipoEntero.getInstance();  }
                    | DOUBLE                        { $$ = TipoDoble.getInstance();  }
                    | CHAR                          { $$ = TipoCaracter.getInstance();  }
                    | tipo '[' CTE_ENTERA ']'       { $$ = new TipoArray((Tipo)$1, (int)$3);  }
                    ;

identificadores:    IDENTIFICADOR                       { List<Variable> variables = new ArrayList<Variable>();
                                                        variables.add(new Variable(lexico.getLinea(), lexico.getColumna(), (String)$1));
                                                        $$ = variables; }
                    | identificadores ',' IDENTIFICADOR { List<Variable> variables = (ArrayList<Variable>)$1;
                                                        variables.add(new Variable(lexico.getLinea(), lexico.getColumna(), (String)$3));
                                                        $$ = variables; }
                    ;

sentencias:         sentencia                       { List<Sentencia> sentencias = new ArrayList<Sentencia>();
                                                    sentencias.add((Sentencia)$1);
                                                    $$ = sentencias; }
                    | sentencias sentencia          { List<Sentencia> sentencias = (ArrayList<Sentencia>)$1;
                                                    sentencias.add((Sentencia)$2);
                                                    $$ = sentencias; }
                    ;

sentencia:          read ';'                        { $$ = $1; }
                    | write ';'                     { $$ = $1; }
                    | asignacion ';'                { $$ = $1; }
                    ;

asignacion:         expresion '=' expresion         { $$ = new Asignacion(lexico.getLinea(), lexico.getColumna(), (Expresion)$1, (Expresion)$3); }
                    ;

read:               READ expresiones                { $$ = new Read(lexico.getLinea(), lexico.getColumna(), (ArrayList<Expresion>)$2); }
                    ;

write:              WRITE expresiones               { $$ = new Write(lexico.getLinea(), lexico.getColumna(), (ArrayList<Expresion>)$2); }
                    ;

expresiones:        expresion                       { List<Expresion> expresiones = new ArrayList<Expresion>();
                                                    expresiones.add((Expresion)$1);
                                                    $$ = expresiones; }
                    | expresiones ',' expresion     { List<Expresion> expresiones = (ArrayList<Expresion>) $1;
                                                    expresiones.add((Expresion)$3);
                                                    $$ = expresiones; }
                    ;

expresion:  expresion '+' expresion                 { $$ = new OperadorAritmetico(lexico.getLinea(), lexico.getColumna(), (Expresion)$1, (Expresion)$3, "+"); }
            | expresion '-' expresion               { $$ = new OperadorAritmetico(lexico.getLinea(), lexico.getColumna(), (Expresion)$1, (Expresion)$3, "-"); }
            | expresion '*' expresion               { $$ = new OperadorAritmetico(lexico.getLinea(), lexico.getColumna(), (Expresion)$1, (Expresion)$3, "*"); }
            | expresion '/' expresion               { $$ = new OperadorAritmetico(lexico.getLinea(), lexico.getColumna(), (Expresion)$1, (Expresion)$3, "/"); }
            | expresion '%' expresion               { $$ = new OperadorAritmetico(lexico.getLinea(), lexico.getColumna(), (Expresion)$1, (Expresion)$3, "%"); }
            | '-' expresion %PREC MENOS_UNARIO      { $$ = new OperadorUnarioNegativo(lexico.getLinea(), lexico.getColumna(), (Expresion)$2); }
            | expresion '[' expresion ']'           { $$ = new OperadorAccesoArray(lexico.getLinea(), lexico.getColumna(), (Expresion)$1, (Expresion)$3); }
            | '(' expresion ')'                     { $$ = $2;}
            | CTE_ENTERA                            { $$ = new LiteralEntero(lexico.getLinea(),lexico.getColumna(),(int)$1); }
            | CTE_DOBLE                             { $$ = new LiteralDoble(lexico.getLinea(),lexico.getColumna(),(double)$1); }
            | CTE_CARACTER                          { $$ = new LiteralCaracter(lexico.getLinea(),lexico.getColumna(),(char)$1); }
            | IDENTIFICADOR                         { $$ = new Variable(lexico.getLinea(),lexico.getColumna(),(String)$1); }
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