//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";



package miw.sintactico;



//#line 1 "../src/miw/sintactico/sintactico.y"

import miw.lexico.Lexico;
import miw.ast.*;
import miw.ast.definiciones.*;
import miw.ast.expresiones.*;
import miw.ast.expresiones.literales.*;
import miw.ast.expresiones.operadores.*;
import miw.ast.sentencias.*;
import miw.ast.tipos.*;
import java.util.*;

//#line 29 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//## **user defined:Object
String   yytext;//user variable to return contextual strings
Object yyval; //used to return semantic vals from action routines
Object yylval;//the 'lval' (result) I got from yylex()
Object valstk[] = new Object[YYSTACKSIZE];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
final void val_init()
{
  yyval=new Object();
  yylval=new Object();
  valptr=-1;
}
final void val_push(Object val)
{
  try {
    valptr++;
    valstk[valptr]=val;
  }
  catch (ArrayIndexOutOfBoundsException e) {
    int oldsize = valstk.length;
    int newsize = oldsize*2;
    Object[] newstack = new Object[newsize];
    System.arraycopy(valstk,0,newstack,0,oldsize);
    valstk = newstack;
    valstk[valptr]=val;
  }
}
final Object val_pop()
{
  return valstk[valptr--];
}
final void val_drop(int cnt)
{
  valptr -= cnt;
}
final Object val_peek(int relative)
{
  return valstk[valptr-relative];
}
final Object dup_yyval(Object val)
{
  return val;
}
//#### end semantic value section ####
public final static short CTE_ENTERA=257;
public final static short CTE_CARACTER=258;
public final static short CTE_DOBLE=259;
public final static short IDENTIFICADOR=260;
public final static short INT=261;
public final static short DOUBLE=262;
public final static short CHAR=263;
public final static short VOID=264;
public final static short IF=265;
public final static short ELSE=266;
public final static short WHILE=267;
public final static short FOR=268;
public final static short WRITE=269;
public final static short READ=270;
public final static short RETURN=271;
public final static short MAIN=272;
public final static short IGUAL=273;
public final static short DISTINTO=274;
public final static short MAYOR_IGUAL=275;
public final static short MENOR_IGUAL=276;
public final static short OR=277;
public final static short AND=278;
public final static short MENOS_UNARIO=279;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    3,    4,    4,    4,    4,    5,    5,
    2,    2,    6,    6,    6,    9,    7,    8,   11,   11,
   10,   10,   10,   10,   10,   10,   10,   10,   10,   10,
   10,   10,
};
final static short yylen[] = {                            2,
    8,    3,    0,    2,    1,    1,    1,    4,    1,    3,
    1,    2,    2,    2,    2,    3,    2,    2,    1,    3,
    3,    3,    3,    3,    3,    2,    4,    3,    1,    1,
    1,    1,
};
final static short yydefred[] = {                         3,
    0,    0,    5,    6,    7,    0,    0,    0,    0,    2,
    9,    0,    0,    0,    0,    0,    0,    8,   10,    0,
   29,   31,   30,   32,    0,    0,    0,    0,    0,   11,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    1,
   12,   13,   14,   15,    0,    0,    0,    0,    0,    0,
    0,    0,   28,    0,    0,    0,    0,    0,    0,    0,
    0,   27,
};
final static short yydgoto[] = {                          1,
    2,   29,    7,    8,   13,   30,   31,   32,   33,   34,
   36,
};
final static short yysindex[] = {                         0,
    0, -226,    0,    0,    0, -270,  -56,  -90,  -29,    0,
    0, -245,  -16,  -11,  -62, -227,  -84,    0,    0,  -36,
    0,    0,    0,    0,  -32,  -32,  -32,  -32,  -40,    0,
  -17,   -8,   -7,   46,   66,   16,   16,  -30,   57,    0,
    0,    0,    0,    0,  -32,  -32,  -32,  -32,  -32,  -32,
  -32,  -32,    0,   66,   21,   21,  -30,  -30,  -30,   35,
   66,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    6,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  -38,    8,   10,  -27,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   11,   73,   79,  -18,    3,   12,    0,
  -37,    0,
};
final static short yygindex[] = {                         0,
    0,    0,    0,    0,    0,   45,    0,    0,    0,  128,
   50,
};
final static int YYTABLESIZE=234;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         28,
   12,    9,   10,   28,   27,   19,   20,   28,   27,   26,
   14,   15,   27,   26,   26,   26,   26,   26,   23,   26,
   19,   20,   23,   23,   23,   23,   23,   16,   23,   17,
   18,   26,   19,   26,    3,    4,    5,    6,   20,   24,
   23,   42,   23,   24,   24,   24,   24,   24,   25,   24,
   43,   44,   25,   25,   25,   25,   25,   50,   25,   52,
   51,   24,   48,   24,    4,   26,   18,   49,   17,   16,
   25,   50,   25,   41,   23,   37,   48,   46,    0,   47,
    0,   49,   50,    0,   40,    0,    0,   48,   46,    0,
   47,    0,   49,   50,    0,   24,    0,   53,   48,   46,
    0,   47,   50,   49,   25,    0,   45,   48,   46,    0,
   47,   51,   49,   21,    0,   21,   21,   21,    0,   22,
    0,   22,   22,   22,    0,   51,    0,   62,    0,    0,
    0,   21,    0,   21,    0,    0,   51,   22,    0,   22,
    0,    0,    0,    0,    0,    0,    0,   51,    0,    0,
    0,    0,   35,   35,   38,   39,   51,    0,    0,    0,
    0,    0,    0,    0,    0,   21,    0,    0,    0,   11,
    0,   22,   54,   55,   56,   57,   58,   59,   60,   61,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   21,   22,   23,   24,
   21,   22,   23,   24,   21,   22,   23,   24,   25,   26,
    0,    0,   25,   26,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
   91,  272,   59,   40,   45,   44,   44,   40,   45,   37,
   40,  257,   45,   41,   42,   43,   44,   45,   37,   47,
   59,   59,   41,   42,   43,   44,   45,   44,   47,   41,
   93,   59,  260,   61,  261,  262,  263,  264,  123,   37,
   59,   59,   61,   41,   42,   43,   44,   45,   37,   47,
   59,   59,   41,   42,   43,   44,   45,   37,   47,   44,
   91,   59,   42,   61,   59,   93,   59,   47,   59,   59,
   59,   37,   61,   29,   93,   26,   42,   43,   -1,   45,
   -1,   47,   37,   -1,  125,   -1,   -1,   42,   43,   -1,
   45,   -1,   47,   37,   -1,   93,   -1,   41,   42,   43,
   -1,   45,   37,   47,   93,   -1,   61,   42,   43,   -1,
   45,   91,   47,   41,   -1,   43,   44,   45,   -1,   41,
   -1,   43,   44,   45,   -1,   91,   -1,   93,   -1,   -1,
   -1,   59,   -1,   61,   -1,   -1,   91,   59,   -1,   61,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   91,   -1,   -1,
   -1,   -1,   25,   26,   27,   28,   91,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   93,   -1,   -1,   -1,  260,
   -1,   93,   45,   46,   47,   48,   49,   50,   51,   52,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,  260,
  257,  258,  259,  260,  257,  258,  259,  260,  269,  270,
   -1,   -1,  269,  270,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=279;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
"','","'-'",null,"'/'",null,null,null,null,null,null,null,null,null,null,null,
"';'",null,"'='",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,"CTE_ENTERA","CTE_CARACTER",
"CTE_DOBLE","IDENTIFICADOR","INT","DOUBLE","CHAR","VOID","IF","ELSE","WHILE",
"FOR","WRITE","READ","RETURN","MAIN","IGUAL","DISTINTO","MAYOR_IGUAL",
"MENOR_IGUAL","OR","AND","MENOS_UNARIO",
};
final static String yyrule[] = {
"$accept : programa",
"programa : declaraciones VOID MAIN '(' ')' '{' sentencias '}'",
"declaraciones : declaraciones declaracion ';'",
"declaraciones :",
"declaracion : tipo identificadores",
"tipo : INT",
"tipo : DOUBLE",
"tipo : CHAR",
"tipo : tipo '[' CTE_ENTERA ']'",
"identificadores : IDENTIFICADOR",
"identificadores : identificadores ',' IDENTIFICADOR",
"sentencias : sentencia",
"sentencias : sentencias sentencia",
"sentencia : read ';'",
"sentencia : write ';'",
"sentencia : asignacion ';'",
"asignacion : expresion '=' expresion",
"read : READ expresiones",
"write : WRITE expresiones",
"expresiones : expresion",
"expresiones : expresiones ',' expresion",
"expresion : expresion '+' expresion",
"expresion : expresion '-' expresion",
"expresion : expresion '*' expresion",
"expresion : expresion '/' expresion",
"expresion : expresion '%' expresion",
"expresion : '-' expresion",
"expresion : expresion '[' expresion ']'",
"expresion : '(' expresion ')'",
"expresion : CTE_ENTERA",
"expresion : CTE_DOBLE",
"expresion : CTE_CARACTER",
"expresion : IDENTIFICADOR",
};

//#line 107 "../src/miw/sintactico/sintactico.y"

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
//#line 341 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 31 "../src/miw/sintactico/sintactico.y"
{ this.ast = new Programa(lexico.getLinea(), lexico.getColumna(), (ArrayList<DefVariable>)val_peek(7), (ArrayList<Sentencia>)val_peek(1)); System.err.println(this.ast.toString());}
break;
case 2:
//#line 34 "../src/miw/sintactico/sintactico.y"
{ List<Definicion> declaraciones = (ArrayList<Definicion>)val_peek(2);
                                                    declaraciones.addAll((ArrayList<Definicion>)val_peek(1));
                                                    yyval = declaraciones; }
break;
case 3:
//#line 37 "../src/miw/sintactico/sintactico.y"
{ yyval = new ArrayList<Definicion>(); }
break;
case 4:
//#line 40 "../src/miw/sintactico/sintactico.y"
{ List<Variable> variables = (ArrayList <Variable>)val_peek(0);
                                                    List<DefVariable> declaraciones = new ArrayList <DefVariable>();
                                                    for(Variable variable : variables) {
                                                        declaraciones.add(new DefVariable(lexico.getLinea(), lexico.getColumna(), variable.nombre, (Tipo)val_peek(1)));
                                                    }
                                                    yyval = declaraciones; }
break;
case 5:
//#line 48 "../src/miw/sintactico/sintactico.y"
{ yyval = TipoEntero.getInstance(lexico.getLinea(), lexico.getColumna());  }
break;
case 6:
//#line 49 "../src/miw/sintactico/sintactico.y"
{ yyval = TipoDoble.getInstance(lexico.getLinea(), lexico.getColumna());  }
break;
case 7:
//#line 50 "../src/miw/sintactico/sintactico.y"
{ yyval = TipoCaracter.getInstance(lexico.getLinea(), lexico.getColumna());  }
break;
case 8:
//#line 51 "../src/miw/sintactico/sintactico.y"
{ yyval = new TipoArray(lexico.getLinea(), lexico.getColumna(), (Tipo)val_peek(3), (int)val_peek(1));  }
break;
case 9:
//#line 54 "../src/miw/sintactico/sintactico.y"
{ List<Variable> variables = new ArrayList<Variable>();
                                                        variables.add(new Variable(lexico.getLinea(), lexico.getColumna(), (String)val_peek(0)));
                                                        yyval = variables; }
break;
case 10:
//#line 57 "../src/miw/sintactico/sintactico.y"
{ List<Variable> variables = (ArrayList<Variable>)val_peek(2);
                                                        variables.add(new Variable(lexico.getLinea(), lexico.getColumna(), (String)val_peek(0)));
                                                        yyval = variables; }
break;
case 11:
//#line 62 "../src/miw/sintactico/sintactico.y"
{ List<Sentencia> sentencias = new ArrayList<Sentencia>();
                                                    sentencias.add((Sentencia)val_peek(0));
                                                    yyval = sentencias; }
break;
case 12:
//#line 65 "../src/miw/sintactico/sintactico.y"
{ List<Sentencia> sentencias = (ArrayList<Sentencia>)val_peek(1);
                                                    sentencias.add((Sentencia)val_peek(0));
                                                    yyval = sentencias; }
break;
case 13:
//#line 70 "../src/miw/sintactico/sintactico.y"
{ yyval = val_peek(1); }
break;
case 14:
//#line 71 "../src/miw/sintactico/sintactico.y"
{ yyval = val_peek(1); }
break;
case 15:
//#line 72 "../src/miw/sintactico/sintactico.y"
{ yyval = val_peek(1); }
break;
case 16:
//#line 75 "../src/miw/sintactico/sintactico.y"
{ yyval = new Asignacion(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), (Expresion)val_peek(0)); }
break;
case 17:
//#line 78 "../src/miw/sintactico/sintactico.y"
{ yyval = new Read(lexico.getLinea(), lexico.getColumna(), (ArrayList<Expresion>)val_peek(0)); }
break;
case 18:
//#line 81 "../src/miw/sintactico/sintactico.y"
{ yyval = new Write(lexico.getLinea(), lexico.getColumna(), (ArrayList<Expresion>)val_peek(0)); }
break;
case 19:
//#line 84 "../src/miw/sintactico/sintactico.y"
{ List<Expresion> expresiones = new ArrayList<Expresion>();
                                                    expresiones.add((Expresion)val_peek(0));
                                                    yyval = expresiones; }
break;
case 20:
//#line 87 "../src/miw/sintactico/sintactico.y"
{ List<Expresion> expresiones = (ArrayList<Expresion>) val_peek(2);
                                                    expresiones.add((Expresion)val_peek(0));
                                                    yyval = expresiones; }
break;
case 21:
//#line 92 "../src/miw/sintactico/sintactico.y"
{ yyval = new OperadorAritmetico(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), (Expresion)val_peek(0), "+"); }
break;
case 22:
//#line 93 "../src/miw/sintactico/sintactico.y"
{ yyval = new OperadorAritmetico(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), (Expresion)val_peek(0), "-"); }
break;
case 23:
//#line 94 "../src/miw/sintactico/sintactico.y"
{ yyval = new OperadorAritmetico(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), (Expresion)val_peek(0), "*"); }
break;
case 24:
//#line 95 "../src/miw/sintactico/sintactico.y"
{ yyval = new OperadorAritmetico(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), (Expresion)val_peek(0), "/"); }
break;
case 25:
//#line 96 "../src/miw/sintactico/sintactico.y"
{ yyval = new OperadorAritmetico(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), (Expresion)val_peek(0), "%"); }
break;
case 26:
//#line 97 "../src/miw/sintactico/sintactico.y"
{ yyval = new OperadorUnarioNegativo(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(0)); }
break;
case 27:
//#line 98 "../src/miw/sintactico/sintactico.y"
{ yyval = new OperadorAccesoArray(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(3), (Expresion)val_peek(1)); }
break;
case 28:
//#line 99 "../src/miw/sintactico/sintactico.y"
{ yyval = val_peek(1);}
break;
case 29:
//#line 100 "../src/miw/sintactico/sintactico.y"
{ yyval = new LiteralEntero(lexico.getLinea(),lexico.getColumna(),(int)val_peek(0)); }
break;
case 30:
//#line 101 "../src/miw/sintactico/sintactico.y"
{ yyval = new LiteralDoble(lexico.getLinea(),lexico.getColumna(),(double)val_peek(0)); }
break;
case 31:
//#line 102 "../src/miw/sintactico/sintactico.y"
{ yyval = new LiteralCaracter(lexico.getLinea(),lexico.getColumna(),(char)val_peek(0)); }
break;
case 32:
//#line 103 "../src/miw/sintactico/sintactico.y"
{ yyval = new Variable(lexico.getLinea(),lexico.getColumna(),(String)val_peek(0)); }
break;
//#line 637 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
