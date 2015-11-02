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
public final static short MENOS_QUE_ELSE=279;
public final static short MENOS_UNARIO=280;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    1,    3,    3,    4,    5,    5,    5,    5,
    7,    6,    6,    8,    8,    9,    9,    9,    9,    9,
    9,    9,   15,   15,   17,   17,   18,   18,   20,   20,
   21,   21,   12,   10,   11,   13,   14,   16,   16,   23,
   23,   24,   25,   25,   26,   26,   26,   26,   26,   26,
   26,   26,    2,    2,   22,   22,   19,   19,   19,   19,
   19,   19,   19,   19,   19,   19,   19,   19,   19,   19,
   19,   19,   19,   19,   19,   19,
};
final static short yylen[] = {                            2,
    1,    1,    2,    3,    2,    2,    1,    1,    1,    4,
    1,    1,    3,    1,    2,    2,    2,    2,    2,    1,
    1,    2,    1,    1,    5,    6,    5,    6,    2,    2,
    2,    3,    3,    2,    2,    2,    5,    3,    4,    3,
    1,    2,    1,    1,    8,    7,    9,    8,    8,    7,
    9,    8,    1,    2,    1,    3,    3,    3,    3,    3,
    3,    3,    3,    2,    3,    3,    3,    3,    2,    4,
    3,    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
    7,    8,    9,    0,    0,    1,    0,    0,    0,    0,
   53,   11,   44,   43,    0,    0,   54,    0,    0,    5,
    0,    0,    0,    0,    0,    4,    0,    0,    0,    0,
    0,    0,   41,   10,   13,    0,    0,    0,   42,    0,
    0,    0,    0,   72,   74,   73,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   14,    0,
    0,    0,    0,   20,   21,    0,   23,   24,    0,    0,
   40,    0,    0,    0,    0,    0,   76,    0,    0,    0,
    0,    0,    0,    0,    0,   12,    0,   46,   15,   16,
   17,   18,   19,   22,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   50,    0,    0,    0,    0,    0,   71,   45,   38,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   48,   49,    0,   52,    0,    0,
    0,   39,   70,   47,   51,    0,    0,    0,   37,   31,
    0,    0,   26,   28,   32,   29,   30,
};
final static short yydgoto[] = {                          5,
    6,    7,    8,    9,   56,   22,   57,   58,   59,   60,
   61,   62,   63,   64,   65,   77,   67,   68,   69,  153,
  148,   79,   32,   33,   24,   11,
};
final static short yysindex[] = {                       -68,
    0,    0,    0, -222,    0,    0,  -68,  -68,  -57,  -88,
    0,    0,    0,    0,  -19,  -88,    0,  -68,  -36,    0,
 -230,  -15,    0,   13,  -40,    0,  -20, -199,  -30,  -60,
  -79,   39,    0,    0,    0,  -22,   41,  128,    0,  -10,
 -174,  128,   -6,    0,    0,    0,   68,   79,  422,  422,
  422,  422,  422,  422,  128,  -79,   95,  143,    0,   83,
   86,   88,   96,    0,    0,   97,    0,    0,  -17,  128,
    0,  128,  159,  128,  422,  422,    0,   94,  113,  113,
   94,  107,   71,   55,  179,    0,  118,    0,    0,    0,
    0,    0,    0,    0,  422,  422,  422,  422,  422,  422,
  422,  422,  422,  422,  422,  422,  422,  128,  248,  280,
    0,  128,  387,   62,   69,  422,    0,    0,    0,   99,
  160,  160,  160,  160,  107,  107,   94,   91,   91,   71,
   71,   71,   78,  402,    0,    0,  418,    0,  433,   42,
   94,    0,    0,    0,    0,  448, -102, -102,    0,    0,
  464,  433,    0,    0,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,  167,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  170,    0,    0,
    0,  115,   -8,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  -37,    0,    0,    0,
    0,    0,    0,    0,    0,   87,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   -7,  116,  120,
  121,  221,  -28,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  535,  541,  549,  557,  527,  570,  127,  516,  522,   -2,
   25,   34,    0,    0,    0,    0,    0,    0,    0,    0,
    5,    0,    0,    0,    0,    0,  481,  496,    0,    0,
    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  169,   52,  158,  837,    0,  455,  787,  514,    0,
    0,    0,    0,    0,    0,  798,    0,    0,  864,   43,
  -92,  -32,  161,  146,  185,   40,
};
final static int YYTABLESIZE=980;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         75,
   30,   20,   21,   75,   75,   75,   75,   75,   69,   75,
   36,   21,   69,   69,   69,   69,   69,   80,   69,  106,
   25,   75,   26,   75,  104,  102,   27,  103,   28,  105,
   69,   43,   69,   55,   59,   12,   55,   12,   59,   59,
   59,   59,   59,  101,   59,   56,   17,  149,   56,   13,
   12,   55,   29,   75,  120,   75,   59,   17,   59,  157,
   12,   60,   38,   56,   69,   60,   60,   60,   60,   60,
   61,   60,   34,  107,   61,   61,   61,   61,   61,   40,
   61,   43,   41,   60,   41,   60,    1,    2,    3,   55,
   59,  106,   61,   72,   61,  117,  104,  102,  106,  103,
   42,  105,  139,  104,  102,  106,  103,   75,  105,  140,
  104,  102,   70,  103,  106,  105,   74,   60,   76,  104,
  102,  108,  103,   76,  105,  112,   61,  106,   76,   76,
  106,   76,  104,   76,   87,  104,  102,  105,  103,  142,
  105,   90,  116,  106,   91,  107,   92,   76,  104,  102,
   52,  103,  107,  105,   93,   94,  116,   54,  119,  107,
   52,  107,   53,  152,  146,   19,    2,   54,  107,    3,
  143,   12,   53,    6,   35,   52,   18,   76,   34,   36,
   12,  107,   54,   13,  107,   33,   71,   53,   15,   37,
  154,   52,    1,    2,    3,    4,  106,  107,   54,    0,
    0,  104,  102,   53,  103,    0,  105,    0,    0,    0,
    0,   52,   19,    0,    0,    0,    0,    0,   54,    0,
    1,    2,    3,   53,    0,    0,    0,    0,    0,   19,
    1,    2,    3,    0,    0,   75,   75,   75,   75,   75,
   75,    0,    0,    0,   69,   69,   69,   69,   69,   69,
  107,    0,    0,    0,    0,   95,   96,   97,   98,   99,
  100,   64,    0,    0,   64,   19,    0,   88,    0,   19,
   59,   59,   59,   59,   59,   59,    0,    0,    0,   64,
   52,   64,    0,  111,    0,    0,    0,   54,    0,    0,
    0,    0,   53,    0,    0,    0,    0,   60,   60,   60,
   60,   60,   60,  118,    0,    0,   61,   61,   61,   61,
   61,   61,   52,   64,    0,    0,    0,    0,    0,   54,
    0,    0,    0,    0,   53,    0,    0,   95,   96,   97,
   98,   99,  100,    0,   95,   96,   97,   98,   99,  100,
    0,   95,   96,   97,   98,   99,  100,    0,    0,    0,
   95,   96,   97,   98,   99,  100,    0,    0,    0,   76,
   76,   76,   76,   76,   76,    0,   95,   96,   97,   98,
   99,  100,  135,    0,   44,   45,   46,   12,    0,   95,
   96,   97,   98,    0,   44,   45,   46,   12,    1,    2,
    3,    0,   47,    0,   48,    0,   49,   50,   51,   44,
   45,   46,   12,    0,  136,    0,    0,   47,    0,   48,
    0,   49,   50,   51,    0,   44,   45,   46,   12,   52,
    0,    0,    0,   47,    0,   48,   54,   49,   50,   51,
    0,   53,    0,    0,   52,   44,   45,   46,   12,    0,
    0,   54,    0,   47,    0,   48,   53,   49,   50,   51,
   52,    0,    0,    0,   52,    0,    0,   54,   14,    0,
    0,   54,   53,    0,   23,   52,   53,    0,    0,    0,
   14,    0,   54,    0,    0,    0,    0,   53,    0,    0,
   52,    0,   35,    0,    0,   39,    0,   54,    0,    0,
    0,    0,   53,    0,    0,    0,   52,   64,   64,    0,
    0,    0,    0,   54,   44,   45,   46,   12,   53,    0,
   86,  138,   47,   25,   48,    0,   49,   50,   51,    0,
   25,    0,    0,    0,    0,   25,  144,    0,   27,    0,
    0,    0,    0,    0,    0,   27,   44,   45,   46,   12,
   27,    0,  145,    0,   47,    0,   48,    0,   49,   50,
   51,    0,    0,    0,    0,  146,   57,    0,   57,   57,
   57,    0,   58,    0,   58,   58,   58,   63,    0,    0,
   63,   89,  150,    0,   57,   65,   57,    0,   65,    0,
   58,   66,   58,    0,   66,   63,   89,   63,  155,   68,
    0,    0,   68,   65,    0,   65,    0,   67,   89,   66,
   67,   66,    0,    0,    0,   25,    0,   68,   57,   68,
   62,    0,    0,   62,   58,   67,    0,   67,    0,   63,
   27,    0,   89,   89,    0,    0,   89,   65,   62,    0,
   62,    0,    0,   66,    0,    0,    0,    0,    0,    0,
    0,   68,    0,   44,   45,   46,   12,   89,    0,   67,
   89,   47,  147,   48,    0,   49,   50,   51,   44,   45,
   46,   12,   62,    0,   89,  156,   47,    0,   48,    0,
   49,   50,   51,    0,   44,   45,   46,   12,   44,   45,
   46,   12,   47,    0,   48,    0,   49,   50,   51,   44,
   45,   46,   12,    0,    0,    0,    0,   47,    0,   48,
    0,   49,   50,   51,   44,   45,   46,   12,    0,    0,
    0,    0,   47,    0,   48,    0,   49,   50,   51,    0,
   44,   45,   46,   12,    0,    0,    0,    0,   47,    0,
   48,    0,   49,   50,   51,    0,    0,   25,   25,   25,
   25,    0,    0,    0,    0,   25,    0,   25,    0,   25,
   25,   25,   27,   27,   27,   27,    0,    0,    0,    0,
   27,    0,   27,    0,   27,   27,   27,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   57,   57,
   57,   57,   57,   57,   58,   58,   58,   58,   58,   58,
    0,    0,    0,   63,   63,    0,    0,   65,   65,   65,
   65,   65,   65,   66,   66,   66,   66,   66,   66,    0,
    0,   68,   68,   68,   68,   68,   68,    0,   73,   67,
   67,   67,   67,   67,   67,   66,   10,    0,    0,   66,
    0,   85,    0,   16,   10,    0,   62,   62,    0,    0,
    0,    0,   66,    0,   16,   66,  109,    0,  110,    0,
  113,   31,    0,    0,    0,   31,    0,   66,    0,   66,
   66,   66,    0,    0,    0,    0,    0,   31,    0,    0,
    0,    0,   66,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  134,    0,    0,    0,  137,    0,
    0,    0,    0,    0,    0,   66,   66,   66,    0,   66,
   66,    0,   78,   78,   81,   82,   83,   84,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   66,  151,    0,   66,    0,   66,    0,  114,  115,
    0,    0,    0,   66,    0,    0,    0,    0,   66,   66,
   78,    0,    0,    0,    0,    0,    0,    0,  121,  122,
  123,  124,  125,  126,  127,  128,  129,  130,  131,  132,
  133,    0,    0,    0,    0,    0,    0,    0,    0,  141,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
   41,   59,   91,   41,   42,   43,   44,   45,   37,   47,
   41,   91,   41,   42,   43,   44,   45,   50,   47,   37,
   40,   59,   59,   61,   42,   43,  257,   45,   44,   47,
   59,   40,   61,   41,   37,   44,   44,  260,   41,   42,
   43,   44,   45,   61,   47,   41,    7,  140,   44,  272,
   59,   59,   40,   91,   87,   93,   59,   18,   61,  152,
  260,   37,  123,   59,   93,   41,   42,   43,   44,   45,
   37,   47,   93,   91,   41,   42,   43,   44,   45,   41,
   47,   41,   44,   59,   44,   61,  261,  262,  263,   38,
   93,   37,   59,   42,   61,   41,   42,   43,   37,   45,
  123,   47,   41,   42,   43,   37,   45,   40,   47,   41,
   42,   43,  123,   45,   37,   47,  123,   93,   40,   42,
   43,   70,   45,   37,   47,   74,   93,   37,   42,   43,
   37,   45,   42,   47,   40,   42,   43,   47,   45,   41,
   47,   59,   44,   37,   59,   91,   59,   61,   42,   43,
   33,   45,   91,   47,   59,   59,   44,   40,   41,   91,
   33,   91,   45,  266,  123,    8,    0,   40,   91,    0,
   93,  260,   45,   59,   59,   33,    8,   91,   59,   59,
  260,   91,   40,  272,   91,   59,   41,   45,    4,   29,
  148,   33,  261,  262,  263,  264,   37,   91,   40,   -1,
   -1,   42,   43,   45,   45,   -1,   47,   -1,   -1,   -1,
   -1,   33,   55,   -1,   -1,   -1,   -1,   -1,   40,   -1,
  261,  262,  263,   45,   -1,   -1,   -1,   -1,   -1,   72,
  261,  262,  263,   -1,   -1,  273,  274,  275,  276,  277,
  278,   -1,   -1,   -1,  273,  274,  275,  276,  277,  278,
   91,   -1,   -1,   -1,   -1,  273,  274,  275,  276,  277,
  278,   41,   -1,   -1,   44,  108,   -1,  125,   -1,  112,
  273,  274,  275,  276,  277,  278,   -1,   -1,   -1,   59,
   33,   61,   -1,  125,   -1,   -1,   -1,   40,   -1,   -1,
   -1,   -1,   45,   -1,   -1,   -1,   -1,  273,  274,  275,
  276,  277,  278,  125,   -1,   -1,  273,  274,  275,  276,
  277,  278,   33,   93,   -1,   -1,   -1,   -1,   -1,   40,
   -1,   -1,   -1,   -1,   45,   -1,   -1,  273,  274,  275,
  276,  277,  278,   -1,  273,  274,  275,  276,  277,  278,
   -1,  273,  274,  275,  276,  277,  278,   -1,   -1,   -1,
  273,  274,  275,  276,  277,  278,   -1,   -1,   -1,  273,
  274,  275,  276,  277,  278,   -1,  273,  274,  275,  276,
  277,  278,  125,   -1,  257,  258,  259,  260,   -1,  273,
  274,  275,  276,   -1,  257,  258,  259,  260,  261,  262,
  263,   -1,  265,   -1,  267,   -1,  269,  270,  271,  257,
  258,  259,  260,   -1,  125,   -1,   -1,  265,   -1,  267,
   -1,  269,  270,  271,   -1,  257,  258,  259,  260,   33,
   -1,   -1,   -1,  265,   -1,  267,   40,  269,  270,  271,
   -1,   45,   -1,   -1,   33,  257,  258,  259,  260,   -1,
   -1,   40,   -1,  265,   -1,  267,   45,  269,  270,  271,
   33,   -1,   -1,   -1,   33,   -1,   -1,   40,    4,   -1,
   -1,   40,   45,   -1,   10,   33,   45,   -1,   -1,   -1,
   16,   -1,   40,   -1,   -1,   -1,   -1,   45,   -1,   -1,
   33,   -1,   28,   -1,   -1,   31,   -1,   40,   -1,   -1,
   -1,   -1,   45,   -1,   -1,   -1,   33,  277,  278,   -1,
   -1,   -1,   -1,   40,  257,  258,  259,  260,   45,   -1,
   56,  125,  265,   33,  267,   -1,  269,  270,  271,   -1,
   40,   -1,   -1,   -1,   -1,   45,  125,   -1,   33,   -1,
   -1,   -1,   -1,   -1,   -1,   40,  257,  258,  259,  260,
   45,   -1,  125,   -1,  265,   -1,  267,   -1,  269,  270,
  271,   -1,   -1,   -1,   -1,  123,   41,   -1,   43,   44,
   45,   -1,   41,   -1,   43,   44,   45,   41,   -1,   -1,
   44,   58,  125,   -1,   59,   41,   61,   -1,   44,   -1,
   59,   41,   61,   -1,   44,   59,   73,   61,  125,   41,
   -1,   -1,   44,   59,   -1,   61,   -1,   41,   85,   59,
   44,   61,   -1,   -1,   -1,  125,   -1,   59,   93,   61,
   41,   -1,   -1,   44,   93,   59,   -1,   61,   -1,   93,
  125,   -1,  109,  110,   -1,   -1,  113,   93,   59,   -1,
   61,   -1,   -1,   93,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   93,   -1,  257,  258,  259,  260,  134,   -1,   93,
  137,  265,  139,  267,   -1,  269,  270,  271,  257,  258,
  259,  260,   93,   -1,  151,  152,  265,   -1,  267,   -1,
  269,  270,  271,   -1,  257,  258,  259,  260,  257,  258,
  259,  260,  265,   -1,  267,   -1,  269,  270,  271,  257,
  258,  259,  260,   -1,   -1,   -1,   -1,  265,   -1,  267,
   -1,  269,  270,  271,  257,  258,  259,  260,   -1,   -1,
   -1,   -1,  265,   -1,  267,   -1,  269,  270,  271,   -1,
  257,  258,  259,  260,   -1,   -1,   -1,   -1,  265,   -1,
  267,   -1,  269,  270,  271,   -1,   -1,  257,  258,  259,
  260,   -1,   -1,   -1,   -1,  265,   -1,  267,   -1,  269,
  270,  271,  257,  258,  259,  260,   -1,   -1,   -1,   -1,
  265,   -1,  267,   -1,  269,  270,  271,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  273,  274,
  275,  276,  277,  278,  273,  274,  275,  276,  277,  278,
   -1,   -1,   -1,  277,  278,   -1,   -1,  273,  274,  275,
  276,  277,  278,  273,  274,  275,  276,  277,  278,   -1,
   -1,  273,  274,  275,  276,  277,  278,   -1,   42,  273,
  274,  275,  276,  277,  278,   38,    0,   -1,   -1,   42,
   -1,   55,   -1,    7,    8,   -1,  277,  278,   -1,   -1,
   -1,   -1,   55,   -1,   18,   58,   70,   -1,   72,   -1,
   74,   25,   -1,   -1,   -1,   29,   -1,   70,   -1,   72,
   73,   74,   -1,   -1,   -1,   -1,   -1,   41,   -1,   -1,
   -1,   -1,   85,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  108,   -1,   -1,   -1,  112,   -1,
   -1,   -1,   -1,   -1,   -1,  108,  109,  110,   -1,  112,
  113,   -1,   49,   50,   51,   52,   53,   54,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  134,  146,   -1,  137,   -1,  139,   -1,   75,   76,
   -1,   -1,   -1,  146,   -1,   -1,   -1,   -1,  151,  152,
   87,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   95,   96,
   97,   98,   99,  100,  101,  102,  103,  104,  105,  106,
  107,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  116,
};
}
final static short YYFINAL=5;
final static short YYMAXTOKEN=280;
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
"MENOR_IGUAL","OR","AND","MENOS_QUE_ELSE","MENOS_UNARIO",
};
final static String yyrule[] = {
"$accept : programa",
"programa : definiciones",
"definiciones : declaracionesFuncion",
"definiciones : declaraciones declaracionesFuncion",
"declaraciones : declaraciones declaracion ';'",
"declaraciones : declaracion ';'",
"declaracion : tipo identificadores",
"tipo : INT",
"tipo : DOUBLE",
"tipo : CHAR",
"tipo : tipo '[' CTE_ENTERA ']'",
"identificador : IDENTIFICADOR",
"identificadores : identificador",
"identificadores : identificadores ',' identificador",
"sentencias : sentencia",
"sentencias : sentencias sentencia",
"sentencia : read ';'",
"sentencia : write ';'",
"sentencia : asignacion ';'",
"sentencia : return ';'",
"sentencia : while",
"sentencia : if",
"sentencia : funcion ';'",
"if : if_sentencia",
"if : if_sentencias",
"if_sentencia : IF '(' expresion ')' sentencia",
"if_sentencia : IF '(' expresion ')' sentencia else",
"if_sentencias : IF '(' expresion ')' bloque",
"if_sentencias : IF '(' expresion ')' bloque else",
"else : ELSE sentencia",
"else : ELSE bloque",
"bloque : '{' '}'",
"bloque : '{' sentencias '}'",
"asignacion : expresion '=' expresion",
"read : READ expresiones",
"write : WRITE expresiones",
"return : RETURN expresion",
"while : WHILE '(' expresion ')' bloque",
"funcion : identificador '(' ')'",
"funcion : identificador '(' expresiones ')'",
"parametros : parametros ',' parametro",
"parametros : parametro",
"parametro : tipo identificador",
"nombreFuncion : identificador",
"nombreFuncion : MAIN",
"declaracionFuncion : VOID nombreFuncion '(' ')' '{' declaraciones sentencias '}'",
"declaracionFuncion : VOID nombreFuncion '(' ')' '{' sentencias '}'",
"declaracionFuncion : VOID nombreFuncion '(' parametros ')' '{' declaraciones sentencias '}'",
"declaracionFuncion : VOID nombreFuncion '(' parametros ')' '{' sentencias '}'",
"declaracionFuncion : tipo nombreFuncion '(' ')' '{' declaraciones sentencias '}'",
"declaracionFuncion : tipo nombreFuncion '(' ')' '{' sentencias '}'",
"declaracionFuncion : tipo nombreFuncion '(' parametros ')' '{' declaraciones sentencias '}'",
"declaracionFuncion : tipo nombreFuncion '(' parametros ')' '{' sentencias '}'",
"declaracionesFuncion : declaracionFuncion",
"declaracionesFuncion : declaracionesFuncion declaracionFuncion",
"expresiones : expresion",
"expresiones : expresiones ',' expresion",
"expresion : expresion '+' expresion",
"expresion : expresion '-' expresion",
"expresion : expresion '*' expresion",
"expresion : expresion '/' expresion",
"expresion : expresion '%' expresion",
"expresion : expresion AND expresion",
"expresion : expresion OR expresion",
"expresion : '!' expresion",
"expresion : expresion IGUAL expresion",
"expresion : expresion DISTINTO expresion",
"expresion : expresion MENOR_IGUAL expresion",
"expresion : expresion MAYOR_IGUAL expresion",
"expresion : '-' expresion",
"expresion : expresion '[' expresion ']'",
"expresion : '(' expresion ')'",
"expresion : CTE_ENTERA",
"expresion : CTE_DOBLE",
"expresion : CTE_CARACTER",
"expresion : identificador",
"expresion : funcion",
};

//#line 209 "../src/miw/sintactico/sintactico.y"

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
//#line 571 "Parser.java"
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
//#line 33 "../src/miw/sintactico/sintactico.y"
{ ast = new Programa(lexico.getLinea(), lexico.getColumna(), (List<Definicion>)val_peek(0)); System.err.println(ast); }
break;
case 2:
//#line 36 "../src/miw/sintactico/sintactico.y"
{ yyval = val_peek(0); }
break;
case 3:
//#line 37 "../src/miw/sintactico/sintactico.y"
{ List<Definicion> lista = (List<Definicion>)val_peek(1);
                                                                lista.addAll((List<Definicion>)val_peek(0)); }
break;
case 4:
//#line 41 "../src/miw/sintactico/sintactico.y"
{ List<Definicion> declaraciones = (ArrayList<Definicion>)val_peek(2);
                                                    declaraciones.addAll((ArrayList<Definicion>)val_peek(1));
                                                    yyval = declaraciones; }
break;
case 5:
//#line 44 "../src/miw/sintactico/sintactico.y"
{ yyval = val_peek(1); }
break;
case 6:
//#line 47 "../src/miw/sintactico/sintactico.y"
{ List<Variable> variables = (ArrayList <Variable>)val_peek(0);
                                                    List<DefVariable> declaraciones = new ArrayList <DefVariable>();
                                                    for(Variable variable : variables) {
                                                        declaraciones.add(new DefVariable(lexico.getLinea(), lexico.getColumna(), variable.nombre, (Tipo)val_peek(1)));
                                                    }
                                                    yyval = declaraciones; }
break;
case 7:
//#line 55 "../src/miw/sintactico/sintactico.y"
{ yyval = TipoEntero.getInstance(lexico.getLinea(), lexico.getColumna());  }
break;
case 8:
//#line 56 "../src/miw/sintactico/sintactico.y"
{ yyval = TipoDoble.getInstance(lexico.getLinea(), lexico.getColumna());  }
break;
case 9:
//#line 57 "../src/miw/sintactico/sintactico.y"
{ yyval = TipoCaracter.getInstance(lexico.getLinea(), lexico.getColumna());  }
break;
case 10:
//#line 58 "../src/miw/sintactico/sintactico.y"
{ yyval = new TipoArray(lexico.getLinea(), lexico.getColumna(), (Tipo)val_peek(3), (int)val_peek(1));  }
break;
case 11:
//#line 61 "../src/miw/sintactico/sintactico.y"
{ yyval = new Variable(lexico.getLinea(), lexico.getColumna(), (String)val_peek(0)); }
break;
case 12:
//#line 64 "../src/miw/sintactico/sintactico.y"
{ List<Variable> variables = new ArrayList<Variable>();
                                                        variables.add((Variable)val_peek(0));
                                                        yyval = variables; }
break;
case 13:
//#line 67 "../src/miw/sintactico/sintactico.y"
{ List<Variable> variables = (ArrayList<Variable>)val_peek(2);
                                                        variables.add((Variable)val_peek(0));
                                                        yyval = variables; }
break;
case 14:
//#line 72 "../src/miw/sintactico/sintactico.y"
{ List<Sentencia> sentencias = new ArrayList<Sentencia>();
                                                    sentencias.add((Sentencia)val_peek(0));
                                                    yyval = sentencias; }
break;
case 15:
//#line 75 "../src/miw/sintactico/sintactico.y"
{ List<Sentencia> sentencias = (ArrayList<Sentencia>)val_peek(1);
                                                    sentencias.add((Sentencia)val_peek(0));
                                                    yyval = sentencias; }
break;
case 16:
//#line 80 "../src/miw/sintactico/sintactico.y"
{ yyval = val_peek(1); }
break;
case 17:
//#line 81 "../src/miw/sintactico/sintactico.y"
{ yyval = val_peek(1); }
break;
case 18:
//#line 82 "../src/miw/sintactico/sintactico.y"
{ yyval = val_peek(1); }
break;
case 19:
//#line 83 "../src/miw/sintactico/sintactico.y"
{ yyval = val_peek(1); }
break;
case 20:
//#line 84 "../src/miw/sintactico/sintactico.y"
{ yyval = val_peek(0); }
break;
case 21:
//#line 85 "../src/miw/sintactico/sintactico.y"
{ yyval = val_peek(0); }
break;
case 23:
//#line 89 "../src/miw/sintactico/sintactico.y"
{ yyval = val_peek(0); }
break;
case 24:
//#line 90 "../src/miw/sintactico/sintactico.y"
{ yyval = val_peek(0); }
break;
case 25:
//#line 93 "../src/miw/sintactico/sintactico.y"
{ List<Sentencia> sentenciasIf = new ArrayList<Sentencia>();
                                                                            sentenciasIf.add((Sentencia)val_peek(0));
                                                                            yyval = new If(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), sentenciasIf); }
break;
case 26:
//#line 96 "../src/miw/sintactico/sintactico.y"
{ List<Sentencia> sentenciasIf = new ArrayList<Sentencia>();
                                                                            sentenciasIf.add((Sentencia)val_peek(1));
                                                                            yyval = new If(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(3), sentenciasIf, (ArrayList<Sentencia>)val_peek(0)); }
break;
case 27:
//#line 101 "../src/miw/sintactico/sintactico.y"
{ yyval = new If(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), (ArrayList<Sentencia>)val_peek(0)); }
break;
case 28:
//#line 102 "../src/miw/sintactico/sintactico.y"
{ yyval = new If(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(3), (ArrayList<Sentencia>)val_peek(1), (ArrayList<Sentencia>)val_peek(0)); }
break;
case 29:
//#line 105 "../src/miw/sintactico/sintactico.y"
{ List<Sentencia> sentencias = new ArrayList<Sentencia>();
                                                        sentencias.add((Sentencia)val_peek(0));
                                                        yyval = sentencias; }
break;
case 30:
//#line 108 "../src/miw/sintactico/sintactico.y"
{ yyval = val_peek(0); }
break;
case 31:
//#line 111 "../src/miw/sintactico/sintactico.y"
{ yyval = new ArrayList<Sentencia>(); }
break;
case 32:
//#line 112 "../src/miw/sintactico/sintactico.y"
{ yyval = val_peek(1); }
break;
case 33:
//#line 115 "../src/miw/sintactico/sintactico.y"
{ yyval = new Asignacion(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), (Expresion)val_peek(0)); }
break;
case 34:
//#line 118 "../src/miw/sintactico/sintactico.y"
{ yyval = new Read(lexico.getLinea(), lexico.getColumna(), (ArrayList<Expresion>)val_peek(0)); }
break;
case 35:
//#line 121 "../src/miw/sintactico/sintactico.y"
{ yyval = new Write(lexico.getLinea(), lexico.getColumna(), (ArrayList<Expresion>)val_peek(0)); }
break;
case 36:
//#line 124 "../src/miw/sintactico/sintactico.y"
{ yyval = new Return(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(0)); }
break;
case 37:
//#line 127 "../src/miw/sintactico/sintactico.y"
{ yyval = new While(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), (ArrayList<Sentencia>)val_peek(0)); }
break;
case 38:
//#line 129 "../src/miw/sintactico/sintactico.y"
{ yyval = new Funcion(lexico.getLinea(), lexico.getColumna(), (Variable)val_peek(2)); }
break;
case 39:
//#line 130 "../src/miw/sintactico/sintactico.y"
{ yyval = new Funcion(lexico.getLinea(), lexico.getColumna(), (Variable)val_peek(3), (List<Expresion>)val_peek(1)); }
break;
case 40:
//#line 133 "../src/miw/sintactico/sintactico.y"
{ List<DefVariable> parametros = (List<DefVariable>) val_peek(2);
                                                            parametros.add((DefVariable)val_peek(0));
                                                            yyval = parametros; }
break;
case 41:
//#line 136 "../src/miw/sintactico/sintactico.y"
{ List<DefVariable> parametros = new ArrayList<DefVariable>();
                                                            parametros.add((DefVariable)val_peek(0));
                                                            yyval = parametros; }
break;
case 42:
//#line 141 "../src/miw/sintactico/sintactico.y"
{ yyval = new DefVariable(lexico.getLinea(), lexico.getColumna(), ((Variable)val_peek(0)).nombre, (Tipo)val_peek(1)); }
break;
case 43:
//#line 144 "../src/miw/sintactico/sintactico.y"
{ yyval = val_peek(0); }
break;
case 44:
//#line 145 "../src/miw/sintactico/sintactico.y"
{ yyval = new Variable(lexico.getLinea(),lexico.getColumna(),(String)val_peek(0)); }
break;
case 45:
//#line 148 "../src/miw/sintactico/sintactico.y"
{ Tipo tipo = TipoVoid.getInstance(lexico.getLinea(), lexico.getColumna());
                                                                                                    TipoFuncion tipoFuncion = new TipoFuncion(lexico.getLinea(), lexico.getColumna(), tipo);
                                                                                                    yyval = new DefFuncion(lexico.getLinea(), lexico.getColumna(), ((Variable)val_peek(6)).nombre, tipoFuncion, (ArrayList<Sentencia>)val_peek(1), (ArrayList<DefVariable>)val_peek(2)); }
break;
case 46:
//#line 151 "../src/miw/sintactico/sintactico.y"
{ Tipo tipo = TipoVoid.getInstance(lexico.getLinea(), lexico.getColumna());
                                                                                                    TipoFuncion tipoFuncion = new TipoFuncion(lexico.getLinea(), lexico.getColumna(), tipo);
                                                                                                    yyval = new DefFuncion(lexico.getLinea(), lexico.getColumna(), ((Variable)val_peek(5)).nombre, tipoFuncion, (ArrayList<Sentencia>)val_peek(1)); }
break;
case 47:
//#line 154 "../src/miw/sintactico/sintactico.y"
{ Tipo tipo = TipoVoid.getInstance(lexico.getLinea(), lexico.getColumna());
                                                                                                    TipoFuncion tipoFuncion = new TipoFuncion(lexico.getLinea(), lexico.getColumna(), tipo, (List<DefVariable>) val_peek(5));
                                                                                                    yyval = new DefFuncion(lexico.getLinea(), lexico.getColumna(), ((Variable)val_peek(7)).nombre, tipoFuncion, (ArrayList<Sentencia>)val_peek(1), (ArrayList<DefVariable>)val_peek(2)); }
break;
case 48:
//#line 157 "../src/miw/sintactico/sintactico.y"
{ Tipo tipo = TipoVoid.getInstance(lexico.getLinea(), lexico.getColumna());
                                                                                                    TipoFuncion tipoFuncion = new TipoFuncion(lexico.getLinea(), lexico.getColumna(), tipo, (List<DefVariable>) val_peek(4));
                                                                                                    yyval = new DefFuncion(lexico.getLinea(), lexico.getColumna(), ((Variable)val_peek(6)).nombre, tipoFuncion, (ArrayList<Sentencia>)val_peek(1)); }
break;
case 49:
//#line 160 "../src/miw/sintactico/sintactico.y"
{ TipoFuncion tipo = new TipoFuncion(lexico.getLinea(), lexico.getColumna(), (Tipo)val_peek(7));
                                                                                                    yyval = new DefFuncion(lexico.getLinea(), lexico.getColumna(), ((Variable)val_peek(6)).nombre, tipo, (ArrayList<Sentencia>)val_peek(1), (ArrayList<DefVariable>)val_peek(2)); }
break;
case 50:
//#line 162 "../src/miw/sintactico/sintactico.y"
{ TipoFuncion tipo = new TipoFuncion(lexico.getLinea(), lexico.getColumna(), (Tipo)val_peek(6));
                                                                                                    yyval = new DefFuncion(lexico.getLinea(), lexico.getColumna(), ((Variable)val_peek(5)).nombre, tipo, (ArrayList<Sentencia>)val_peek(1)); }
break;
case 51:
//#line 164 "../src/miw/sintactico/sintactico.y"
{ TipoFuncion tipo = new TipoFuncion(lexico.getLinea(), lexico.getColumna(), (Tipo)val_peek(8), (List<DefVariable>) val_peek(5));
                                                                                                    yyval = new DefFuncion(lexico.getLinea(), lexico.getColumna(), ((Variable)val_peek(7)).nombre, tipo, (ArrayList<Sentencia>)val_peek(1), (ArrayList<DefVariable>)val_peek(2)); }
break;
case 52:
//#line 166 "../src/miw/sintactico/sintactico.y"
{ TipoFuncion tipo = new TipoFuncion(lexico.getLinea(), lexico.getColumna(), (Tipo)val_peek(7), (List<DefVariable>) val_peek(4));
                                                                                                    yyval = new DefFuncion(lexico.getLinea(), lexico.getColumna(), ((Variable)val_peek(6)).nombre, tipo, (ArrayList<Sentencia>)val_peek(1)); }
break;
case 53:
//#line 170 "../src/miw/sintactico/sintactico.y"
{ List<DefFuncion> funciones = new ArrayList<DefFuncion>();
                                                                            funciones.add((DefFuncion)val_peek(0));
                                                                            yyval = funciones; }
break;
case 54:
//#line 173 "../src/miw/sintactico/sintactico.y"
{ List<DefFuncion> funciones = (ArrayList<DefFuncion>)val_peek(1);
                                                                            funciones.add((DefFuncion)val_peek(0));
                                                                            yyval = funciones; }
break;
case 55:
//#line 178 "../src/miw/sintactico/sintactico.y"
{ List<Expresion> expresiones = new ArrayList<Expresion>();
                                                    expresiones.add((Expresion)val_peek(0));
                                                    yyval = expresiones; }
break;
case 56:
//#line 181 "../src/miw/sintactico/sintactico.y"
{ List<Expresion> expresiones = (ArrayList<Expresion>) val_peek(2);
                                                    expresiones.add((Expresion)val_peek(0));
                                                    yyval = expresiones; }
break;
case 57:
//#line 186 "../src/miw/sintactico/sintactico.y"
{ yyval = new OperadorAritmetico(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), (Expresion)val_peek(0), "+"); }
break;
case 58:
//#line 187 "../src/miw/sintactico/sintactico.y"
{ yyval = new OperadorAritmetico(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), (Expresion)val_peek(0), "-"); }
break;
case 59:
//#line 188 "../src/miw/sintactico/sintactico.y"
{ yyval = new OperadorAritmetico(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), (Expresion)val_peek(0), "*"); }
break;
case 60:
//#line 189 "../src/miw/sintactico/sintactico.y"
{ yyval = new OperadorAritmetico(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), (Expresion)val_peek(0), "/"); }
break;
case 61:
//#line 190 "../src/miw/sintactico/sintactico.y"
{ yyval = new OperadorAritmetico(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), (Expresion)val_peek(0), "%"); }
break;
case 62:
//#line 191 "../src/miw/sintactico/sintactico.y"
{ yyval = new OperadorLogico(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), (Expresion)val_peek(0), "&&"); }
break;
case 63:
//#line 192 "../src/miw/sintactico/sintactico.y"
{ yyval = new OperadorLogico(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), (Expresion)val_peek(0), "||"); }
break;
case 64:
//#line 193 "../src/miw/sintactico/sintactico.y"
{ yyval = new OperadorUnarioNegacion(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(0)); }
break;
case 65:
//#line 194 "../src/miw/sintactico/sintactico.y"
{ yyval = new OperadorLogico(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), (Expresion)val_peek(0), "=="); }
break;
case 66:
//#line 195 "../src/miw/sintactico/sintactico.y"
{ yyval = new OperadorLogico(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), (Expresion)val_peek(0), "!="); }
break;
case 67:
//#line 196 "../src/miw/sintactico/sintactico.y"
{ yyval = new OperadorLogico(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), (Expresion)val_peek(0), "<="); }
break;
case 68:
//#line 197 "../src/miw/sintactico/sintactico.y"
{ yyval = new OperadorLogico(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), (Expresion)val_peek(0), ">="); }
break;
case 69:
//#line 198 "../src/miw/sintactico/sintactico.y"
{ yyval = new OperadorUnarioNegativo(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(0)); }
break;
case 70:
//#line 199 "../src/miw/sintactico/sintactico.y"
{ yyval = new OperadorAccesoArray(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(3), (Expresion)val_peek(1)); }
break;
case 71:
//#line 200 "../src/miw/sintactico/sintactico.y"
{ yyval = val_peek(1);}
break;
case 72:
//#line 201 "../src/miw/sintactico/sintactico.y"
{ yyval = new LiteralEntero(lexico.getLinea(),lexico.getColumna(),(int)val_peek(0)); }
break;
case 73:
//#line 202 "../src/miw/sintactico/sintactico.y"
{ yyval = new LiteralDoble(lexico.getLinea(),lexico.getColumna(),(double)val_peek(0)); }
break;
case 74:
//#line 203 "../src/miw/sintactico/sintactico.y"
{ yyval = new LiteralCaracter(lexico.getLinea(),lexico.getColumna(),(char)val_peek(0)); }
break;
case 75:
//#line 204 "../src/miw/sintactico/sintactico.y"
{ yyval = val_peek(0); }
break;
case 76:
//#line 205 "../src/miw/sintactico/sintactico.y"
{ yyval = val_peek(0); }
break;
//#line 1066 "Parser.java"
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
