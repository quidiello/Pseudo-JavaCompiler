// ************  Código a incluir ********************

package miw.lexico;
import miw.sintactico.Parser;
import miw.ast.tipos.TipoError;

%%
// ************  Opciones ********************
// % debug // * Opción para depurar
%byaccj
%class Lexico
%public
%unicode
%line
%column

%{
// ************  Atributos y métodos ********************
// * El analizador sintáctico
private Parser parser;
public void setParser(Parser parser) {
	this.parser=parser;
}

// * Para acceder al número de línea (yyline es package)
public int getLinea() { 
	// * Flex empieza en cero
	return yyline+1;
}

// * Para acceder al número de columna (yycolumn es package)
public int getColumna() { 
	// * Flex empieza en cero
	return yycolumn+1;
}

%}

// ************  Patrones (macros) ********************
ConstanteEntera = [0-9]*
Decimal = ([0-9]*"."[0-9]+)|([0-9]+"."[0-9]*)
Exponente = ((([0-9]*"."[0-9]+)|([0-9]+"."[0-9]*))|([0-9]+))[eE]-?[0-9]+
ConstanteDoble = {Decimal} | {Exponente}
Identificador = [a-zA-ZáéíóúÁÉÍÓÚñÑ]+[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9]*
ComentarioLinea = "//".*\n
ComentarioMultiple = "/*"~"*/"
FinLinea = \r\n | \n\r | \r | \n
Basura = {ComentarioLinea} | {ComentarioMultiple} | {FinLinea} | [ \t\f]

%%
// ************  Acciones ********************
// * Eliminar basura
{Basura} {}

// * Palabras reservadas
"int"				{ parser.setYylval(yytext());
					  return Parser.INT; }
"double"			{ parser.setYylval(yytext());
					  return Parser.DOUBLE; }
"char"				{ parser.setYylval(yytext());
					  return Parser.CHAR; }
"void"				{ parser.setYylval(yytext());
					  return Parser.VOID; }

"if"				{ parser.setYylval(yytext());
					  return Parser.IF; }
"while"				{ parser.setYylval(yytext());
					  return Parser.WHILE; }
"for"				{ parser.setYylval(yytext());
					  return Parser.FOR; }
"read"				{ parser.setYylval(yytext());
					  return Parser.READ; }
"write"				{ parser.setYylval(yytext());
					  return Parser.WRITE; }
"else"				{ parser.setYylval(yytext());
					  return Parser.ELSE; }
"return"			{ parser.setYylval(yytext());
					  return Parser.RETURN; }
"void"			{ parser.setYylval(yytext());
					  return Parser.VOID; }
"main"			{ parser.setYylval(yytext());
					  return Parser.MAIN; }

"=="				{ parser.setYylval(yytext());
					  return Parser.IGUAL; }
"!="				{ parser.setYylval(yytext());
					  return Parser.DISTINTO; }
"<="				{ parser.setYylval(yytext());
					  return Parser.MENOR_IGUAL; }
">="				{ parser.setYylval(yytext());
					  return Parser.MAYOR_IGUAL; }
"||"				{ parser.setYylval(yytext());
					  return Parser.OR; }
"&&"				{ parser.setYylval(yytext());
					  return Parser.AND; }

"=" |
"+" |
"-" |
"*" |
"/" |
"%" |
"!" |
"[" |
"]" |
"(" |
")" |
"{" |
"}" |
"<" |
">" |
"," |
";"					{ parser.setYylval(yytext());
					  return (int) yycharat(0); }


// * Constante Entera
{ConstanteEntera}	{ parser.setYylval(new Integer(yytext()));
         			  return Parser.CTE_ENTERA; }
// * Constante Doble
{ConstanteDoble}	{ parser.setYylval(new Double(yytext()));
         			  return Parser.CTE_DOBLE; }
// * Constante Carácter
'.'					{ parser.setYylval(new Character(yycharat(1)));
					  return Parser.CTE_CARACTER; }
'\\t'				{ parser.setYylval('\t');
					  return Parser.CTE_CARACTER; }
'\\n'				{ parser.setYylval('\n');
					  return Parser.CTE_CARACTER; }
'\\[0-9]+'				{ parser.setYylval((char)Integer.parseInt(yytext().replace("\\","").replace("\'","")));
					  return Parser.CTE_CARACTER; }
// * Variable
{Identificador}	{ parser.setYylval(yytext());
         			  return Parser.IDENTIFICADOR; }

// * Cualquier otro carácter
.					{ parser.setYylval(new TipoError(this.getLinea(), this.getColumna(), "Carácter \'"+ yycharat(0)+"\' desconocido.")); return 6;}