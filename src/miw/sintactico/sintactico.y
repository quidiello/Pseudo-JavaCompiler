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

%right '='
%nonassoc MENOS_QUE_ELSE
%nonassoc ELSE
%left AND OR '!'
%left IGUAL MAYOR_IGUAL MENOR_IGUAL DISTINTO '<' '>'
%left '+' '-'
%left '*' '/' '%'
%right MENOS_UNARIO
%nonassoc '[' ']'
%nonassoc '(' ')'

%%

programa:           definiciones                    { ast = new Programa(lexico.getLinea(), lexico.getColumna(), (List<Definicion>)$1); }
                    ;

definiciones:       declaracionesFuncion                        { $$ = $1; }
                    | declaraciones declaracionesFuncion        { List<Definicion> lista = (List<Definicion>)$1;
                                                                lista.addAll((List<Definicion>)$2); }
                    ;

declaraciones:      declaraciones declaracion ';'   { List<Definicion> declaraciones = (ArrayList<Definicion>)$1;
                                                    declaraciones.addAll((ArrayList<Definicion>)$2);
                                                    $$ = declaraciones; }
                    | declaracion ';'               { $$ = $1; }
                    ;

declaracion:        tipo identificadores            { List<Variable> variables = (ArrayList <Variable>)$2;
                                                    List<DefVariable> declaraciones = new ArrayList <DefVariable>();
                                                    for(Variable variable : variables) {
                                                        declaraciones.add(new DefVariable(lexico.getLinea(), lexico.getColumna(), variable.nombre, (Tipo)$1));
                                                    }
                                                    $$ = declaraciones; }
                    ;

tipo:               INT                             { $$ = TipoEntero.getInstance(lexico.getLinea(), lexico.getColumna());  }
                    | DOUBLE                        { $$ = TipoDoble.getInstance(lexico.getLinea(), lexico.getColumna());  }
                    | CHAR                          { $$ = TipoCaracter.getInstance(lexico.getLinea(), lexico.getColumna());  }
                    | tipo '[' CTE_ENTERA ']'       { $$ = new TipoArray(lexico.getLinea(), lexico.getColumna(), (Tipo)$1, (int)$3);  }
                    ;

identificador:      IDENTIFICADOR                   { $$ = new Variable(lexico.getLinea(), lexico.getColumna(), (String)$1); }
                    ;

identificadores:    identificador                       { List<Variable> variables = new ArrayList<Variable>();
                                                        variables.add((Variable)$1);
                                                        $$ = variables; }
                    | identificadores ',' identificador { List<Variable> variables = (ArrayList<Variable>)$1;
                                                        variables.add((Variable)$3);
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
                    | return ';'                    { $$ = $1; }
                    | while                         { $$ = $1; }
                    | if                            { $$ = $1; }
                    | funcion ';'
                    ;

if:                 if_sentencia                    { $$ = $1; }
                    | if_sentencias                 { $$ = $1; }
                    ;

if_sentencia:       IF '(' expresion ')' sentencia %prec MENOS_QUE_ELSE     { List<Sentencia> sentenciasIf = new ArrayList<Sentencia>();
                                                                            sentenciasIf.add((Sentencia)$5);
                                                                            $$ = new If(lexico.getLinea(), lexico.getColumna(), (Expresion)$3, sentenciasIf); }
                    | IF '(' expresion ')' sentencia else                   { List<Sentencia> sentenciasIf = new ArrayList<Sentencia>();
                                                                            sentenciasIf.add((Sentencia)$5);
                                                                            $$ = new If(lexico.getLinea(), lexico.getColumna(), (Expresion)$3, sentenciasIf, (ArrayList<Sentencia>)$6); }
                    ;

if_sentencias:      IF '(' expresion ')' bloque %prec MENOS_QUE_ELSE        { $$ = new If(lexico.getLinea(), lexico.getColumna(), (Expresion)$3, (ArrayList<Sentencia>)$5); }
                    | IF '(' expresion ')' bloque else                      { $$ = new If(lexico.getLinea(), lexico.getColumna(), (Expresion)$3, (ArrayList<Sentencia>)$5, (ArrayList<Sentencia>)$6); }
                    ;

else:               ELSE sentencia                      { List<Sentencia> sentencias = new ArrayList<Sentencia>();
                                                        sentencias.add((Sentencia)$2);
                                                        $$ = sentencias; }
                    | ELSE bloque                       { $$ = $2; }
                    ;

bloque:             '{' '}'                         { $$ = new ArrayList<Sentencia>(); }
                    | '{' sentencias '}'            { $$ = $2; }
                    ;

asignacion:         expresion '=' expresion         { $$ = new Asignacion(lexico.getLinea(), lexico.getColumna(), (Expresion)$1, (Expresion)$3); }
                    ;

read:               READ expresiones                { $$ = new Read(lexico.getLinea(), lexico.getColumna(), (ArrayList<Expresion>)$2); }
                    ;

write:              WRITE expresiones               { $$ = new Write(lexico.getLinea(), lexico.getColumna(), (ArrayList<Expresion>)$2); }
                    ;

return:             RETURN expresion                { $$ = new Return(lexico.getLinea(), lexico.getColumna(), (Expresion)$2); }
                    ;

while:              WHILE '(' expresion ')' bloque  { $$ = new While(lexico.getLinea(), lexico.getColumna(), (Expresion)$3, (ArrayList<Sentencia>)$5); }

funcion:            identificador '(' ')'                   { $$ = new Funcion(lexico.getLinea(), lexico.getColumna(), (Variable)$1); }
                    | identificador '(' expresiones ')'     { $$ = new Funcion(lexico.getLinea(), lexico.getColumna(), (Variable)$1, (List<Expresion>)$3); }
                    ;

parametros:         parametros ',' parametro                { List<DefVariable> parametros = (List<DefVariable>) $1;
                                                            parametros.add((DefVariable)$3);
                                                            $$ = parametros; }
                    | parametro                             { List<DefVariable> parametros = new ArrayList<DefVariable>();
                                                            parametros.add((DefVariable)$1);
                                                            $$ = parametros; }
                    ;

parametro:          tipo identificador                      { $$ = new DefVariable(lexico.getLinea(), lexico.getColumna(), ((Variable)$2).nombre, (Tipo)$1); }
                    ;

nombreFuncion:      identificador                           { $$ = $1; }
                    | MAIN                                  { $$ = new Variable(lexico.getLinea(),lexico.getColumna(),(String)$1); }
                    ;

declaracionFuncion:     VOID nombreFuncion '(' ')' '{' declaraciones sentencias '}'                 { Tipo tipo = TipoVoid.getInstance(lexico.getLinea(), lexico.getColumna());
                                                                                                    TipoFuncion tipoFuncion = new TipoFuncion(lexico.getLinea(), lexico.getColumna(), tipo);
                                                                                                    $$ = new DefFuncion(lexico.getLinea(), lexico.getColumna(), ((Variable)$2).nombre, tipoFuncion, (ArrayList<Sentencia>)$7, (ArrayList<DefVariable>)$6); }
                        | VOID nombreFuncion '(' ')' '{' sentencias '}'                             { Tipo tipo = TipoVoid.getInstance(lexico.getLinea(), lexico.getColumna());
                                                                                                    TipoFuncion tipoFuncion = new TipoFuncion(lexico.getLinea(), lexico.getColumna(), tipo);
                                                                                                    $$ = new DefFuncion(lexico.getLinea(), lexico.getColumna(), ((Variable)$2).nombre, tipoFuncion, (ArrayList<Sentencia>)$6); }
                        | VOID nombreFuncion '(' parametros ')' '{' declaraciones sentencias '}'    { Tipo tipo = TipoVoid.getInstance(lexico.getLinea(), lexico.getColumna());
                                                                                                    TipoFuncion tipoFuncion = new TipoFuncion(lexico.getLinea(), lexico.getColumna(), tipo, (List<DefVariable>) $4);
                                                                                                    $$ = new DefFuncion(lexico.getLinea(), lexico.getColumna(), ((Variable)$2).nombre, tipoFuncion, (ArrayList<Sentencia>)$8, (ArrayList<DefVariable>)$7); }
                        | VOID nombreFuncion '(' parametros ')' '{' sentencias '}'                  { Tipo tipo = TipoVoid.getInstance(lexico.getLinea(), lexico.getColumna());
                                                                                                    TipoFuncion tipoFuncion = new TipoFuncion(lexico.getLinea(), lexico.getColumna(), tipo, (List<DefVariable>) $4);
                                                                                                    $$ = new DefFuncion(lexico.getLinea(), lexico.getColumna(), ((Variable)$2).nombre, tipoFuncion, (ArrayList<Sentencia>)$7); }
                        | tipo nombreFuncion '(' ')' '{' declaraciones sentencias '}'               { TipoFuncion tipo = new TipoFuncion(lexico.getLinea(), lexico.getColumna(), (Tipo)$1);
                                                                                                    $$ = new DefFuncion(lexico.getLinea(), lexico.getColumna(), ((Variable)$2).nombre, tipo, (ArrayList<Sentencia>)$7, (ArrayList<DefVariable>)$6); }
                        | tipo nombreFuncion '(' ')' '{' sentencias '}'                             { TipoFuncion tipo = new TipoFuncion(lexico.getLinea(), lexico.getColumna(), (Tipo)$1);
                                                                                                    $$ = new DefFuncion(lexico.getLinea(), lexico.getColumna(), ((Variable)$2).nombre, tipo, (ArrayList<Sentencia>)$6); }
                        | tipo nombreFuncion '(' parametros ')' '{' declaraciones sentencias '}'    { TipoFuncion tipo = new TipoFuncion(lexico.getLinea(), lexico.getColumna(), (Tipo)$1, (List<DefVariable>) $4);
                                                                                                    $$ = new DefFuncion(lexico.getLinea(), lexico.getColumna(), ((Variable)$2).nombre, tipo, (ArrayList<Sentencia>)$8, (ArrayList<DefVariable>)$7); }
                        | tipo nombreFuncion '(' parametros ')' '{' sentencias '}'                  { TipoFuncion tipo = new TipoFuncion(lexico.getLinea(), lexico.getColumna(), (Tipo)$1, (List<DefVariable>) $4);
                                                                                                    $$ = new DefFuncion(lexico.getLinea(), lexico.getColumna(), ((Variable)$2).nombre, tipo, (ArrayList<Sentencia>)$7); }
                        ;

declaracionesFuncion:   declaracionFuncion                                  { List<DefFuncion> funciones = new ArrayList<DefFuncion>();
                                                                            funciones.add((DefFuncion)$1);
                                                                            $$ = funciones; }
                        | declaracionesFuncion declaracionFuncion           { List<DefFuncion> funciones = (ArrayList<DefFuncion>)$1;
                                                                            funciones.add((DefFuncion)$2);
                                                                            $$ = funciones; }
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
            | expresion AND expresion               { $$ = new OperadorLogico(lexico.getLinea(), lexico.getColumna(), (Expresion)$1, (Expresion)$3, "&&"); }
            | expresion OR expresion                { $$ = new OperadorLogico(lexico.getLinea(), lexico.getColumna(), (Expresion)$1, (Expresion)$3, "||"); }
            | '!' expresion                         { $$ = new OperadorUnarioNegacion(lexico.getLinea(), lexico.getColumna(), (Expresion)$2); }
            | expresion IGUAL expresion             { $$ = new OperadorLogico(lexico.getLinea(), lexico.getColumna(), (Expresion)$1, (Expresion)$3, "=="); }
            | expresion DISTINTO expresion          { $$ = new OperadorLogico(lexico.getLinea(), lexico.getColumna(), (Expresion)$1, (Expresion)$3, "!="); }
            | expresion MENOR_IGUAL expresion       { $$ = new OperadorLogico(lexico.getLinea(), lexico.getColumna(), (Expresion)$1, (Expresion)$3, "<="); }
            | expresion MAYOR_IGUAL expresion       { $$ = new OperadorLogico(lexico.getLinea(), lexico.getColumna(), (Expresion)$1, (Expresion)$3, ">="); }
            | expresion '<' expresion               { $$ = new OperadorLogico(lexico.getLinea(), lexico.getColumna(), (Expresion)$1, (Expresion)$3, "<"); }
            | expresion '>' expresion               { $$ = new OperadorLogico(lexico.getLinea(), lexico.getColumna(), (Expresion)$1, (Expresion)$3, ">"); }
            | '-' expresion %PREC MENOS_UNARIO      { $$ = new OperadorUnarioNegativo(lexico.getLinea(), lexico.getColumna(), (Expresion)$2); }
            | '(' tipo ')' expresion                { $$ = new OperadorUnarioCast(lexico.getLinea(), lexico.getColumna(), (Expresion)$4, (Tipo)$2); }
            | expresion '[' expresion ']'           { $$ = new OperadorAccesoArray(lexico.getLinea(), lexico.getColumna(), (Expresion)$1, (Expresion)$3); }
            | '(' expresion ')'                     { $$ = $2;}
            | CTE_ENTERA                            { $$ = new LiteralEntero(lexico.getLinea(),lexico.getColumna(),(int)$1); }
            | CTE_DOBLE                             { $$ = new LiteralDoble(lexico.getLinea(),lexico.getColumna(),(double)$1); }
            | CTE_CARACTER                          { $$ = new LiteralCaracter(lexico.getLinea(),lexico.getColumna(),(char)$1); }
            | identificador                         { $$ = $1; }
            | funcion                               { $$ = $1; }
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