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
   19,   19,   19,   19,   19,   19,   19,   19,   19,
};
final static short yylen[] = {                            2,
    1,    1,    2,    3,    2,    2,    1,    1,    1,    4,
    1,    1,    3,    1,    2,    2,    2,    2,    2,    1,
    1,    2,    1,    1,    5,    6,    5,    6,    2,    2,
    2,    3,    3,    2,    2,    2,    5,    3,    4,    3,
    1,    2,    1,    1,    8,    7,    9,    8,    8,    7,
    9,    8,    1,    2,    1,    3,    3,    3,    3,    3,
    3,    3,    3,    2,    3,    3,    3,    3,    3,    3,
    2,    4,    4,    3,    1,    1,    1,    1,    1,
};
final static short yydefred[] = {                         0,
    7,    8,    9,    0,    0,    1,    0,    0,    0,    0,
   53,   11,   44,   43,    0,    0,   54,    0,    0,    5,
    0,    0,    0,    0,    0,    4,    0,    0,    0,    0,
    0,    0,   41,   10,   13,    0,    0,    0,   42,    0,
    0,    0,    0,   75,   77,   76,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   14,    0,
    0,    0,    0,   20,   21,    0,   23,   24,    0,    0,
   40,    0,    0,    0,    0,    0,   79,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   12,    0,   46,   15,
   16,   17,   18,   19,   22,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   50,    0,    0,    0,    0,    0,    0,
   74,   45,   38,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   48,   49,    0,   52,    0,    0,    0,   72,   39,   73,
   47,   51,    0,    0,    0,   37,   31,    0,    0,   26,
   28,   32,   29,   30,
};
final static short yydgoto[] = {                          5,
    6,    7,    8,    9,   56,   22,   57,   58,   59,   60,
   61,   62,   63,   64,   65,   77,   67,   68,   69,  160,
  155,   79,   32,   33,   24,   11,
};
final static short yysindex[] = {                        16,
    0,    0,    0, -242,    0,    0,   16,   16,  -56,  -71,
    0,    0,    0,    0,  -19,  -71,    0,   16,  -21,    0,
 -210,    9,    0,   33,  -30,    0,  -11, -172,   -5,  -34,
  -89,   11,    0,    0,    0,  -33,   39,  154,    0,  -22,
 -234,  154,  -17,    0,    0,    0,   74,   79,  529,  529,
  529,  529,  529,  593,  154,  -89,   80,  190,    0,   64,
   75,   82,   84,    0,    0,   92,    0,    0,   55,  154,
    0,  154,  219,  154,  529,  529,    0,  130,   77,   77,
  130,  160,   37,  -40,   62,  249,    0,  608,    0,    0,
    0,    0,    0,    0,    0,  529,  529,  529,  529,  529,
  529,  529,  529,  529,  529,  529,  529,  529,  529,  529,
  154,  301,  348,    0,  154,  387,   88,   95,  529,  529,
    0,    0,    0,   69,  276,  276,  276,  276,  160,  160,
  130,  276,  276,  311,  311,   37,   37,   37,  102,  406,
    0,    0,  423,    0,  442,   29,  130,    0,    0,    0,
    0,    0,  458, -112, -112,    0,    0,  477,  442,    0,
    0,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,  156,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  158,    0,    0,
    0,  110,    4,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  -37,    0,    0,    0,
    0,    0,    0,    0,    0,  123,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    5,  115,  119,
  121,  224,  -28,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  538,  568,  697,  732,  626,  788,
  122,  741,  776,  167,  532,   -2,   25,   34,    0,    0,
    0,    0,    0,    0,    0,    0,   67,    0,    0,    0,
    0,    0,    0,  495,  510,    0,    0,    0,    0,    0,
    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  153,  246,  104,  614,    0,  511,  815,  800,    0,
    0,    0,    0,    0,    0,  836,    0,    0,  971,    8,
  -85,  -38,  159,  141,  187,   19,
};
final static int YYTABLESIZE=1091;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         78,
  120,   21,   20,   78,   78,   78,   78,   78,   71,   78,
   30,   80,   71,   71,   71,   71,   71,   12,   71,   21,
   25,   78,   78,   78,   78,   17,    1,    2,    3,   13,
   71,   71,   71,   71,   59,   36,   17,   26,   59,   59,
   59,   59,   59,   43,   59,   55,   27,   12,   55,  124,
   21,   40,   28,   78,   41,   78,   59,   59,   59,   59,
  156,   60,   12,   55,   71,   60,   60,   60,   60,   60,
   61,   60,   29,  164,   61,   61,   61,   61,   61,   43,
   61,   34,   41,   60,   60,   60,   60,   12,   38,   42,
   59,  109,   61,   61,   61,   61,  107,  105,  109,  106,
   70,  108,  121,  107,  105,   74,  106,   56,  108,  149,
   56,   19,  119,   75,  103,  102,  104,   60,   76,   88,
  119,  103,   91,  104,  109,   56,   61,  110,  145,  107,
  105,  109,  106,   92,  108,  146,  107,  105,  109,  106,
   93,  108,   94,  107,  105,  110,  106,  103,  108,  104,
   95,  153,  110,  159,  103,    2,  104,    3,   19,   79,
   18,  103,  161,  104,   79,   79,  109,   79,    6,   79,
   12,  107,  105,   35,  106,   19,  108,   34,  110,   36,
   33,   71,   79,   79,   79,  110,   52,   37,   12,  103,
   15,  104,  110,   54,  150,    0,  109,    0,   53,    0,
   13,  107,  105,    0,  106,    0,  108,   57,    0,   57,
   57,   57,    0,   79,   19,    0,    0,    0,   19,  103,
  110,  104,   52,    0,    0,   57,   57,   57,   57,   54,
    1,    2,    3,    0,   53,   78,   78,   78,   78,   78,
   78,    0,    0,    0,   71,   71,   71,   71,   71,   71,
  110,   52,    0,    0,    0,    1,    2,    3,   54,   57,
    0,    0,    0,   53,   64,    0,    0,   64,    0,    0,
   59,   59,   59,   59,   59,   59,    1,    2,    3,    4,
    0,   52,   64,   55,   64,    0,    0,   72,   54,    0,
    0,    0,    0,   53,    0,    0,    0,   60,   60,   60,
   60,   60,   60,    0,    0,    0,   61,   61,   61,   61,
   61,   61,  109,    0,   89,  111,   64,  107,  105,  115,
  106,    0,  108,    0,    0,    0,    0,   96,   97,   98,
   99,  100,  101,   52,   96,   97,   98,   99,  100,  101,
   54,    0,    0,  114,    0,   53,    0,  109,    0,    0,
    0,    0,  107,    0,    0,    0,    0,  108,    0,    0,
   96,   97,   98,   99,  100,  101,  110,   96,   97,   98,
   99,  100,  101,  122,   96,   97,   98,   99,  100,  101,
   52,    0,    0,    0,    0,    0,    0,   54,    0,    0,
    0,    0,   53,    0,    0,   79,   79,   79,   79,   79,
   79,  110,   96,   97,   98,   99,  100,  101,    0,    0,
   44,   45,   46,   12,    1,    2,    3,    0,   47,   52,
   48,    0,   49,   50,   51,  141,   54,    0,    0,    0,
    0,   53,   96,   97,   98,   99,    0,    0,   52,   57,
   57,   57,   57,   57,   57,   54,   44,   45,   46,   12,
   53,    0,    0,    0,   47,   52,   48,    0,   49,   50,
   51,    0,   54,    0,    0,    0,    0,   53,    0,    0,
    0,    0,  142,    0,   52,   44,   45,   46,   12,    0,
    0,   54,    0,   47,    0,   48,   53,   49,   50,   51,
   52,    0,    0,    0,    0,    0,    0,   54,    0,    0,
   64,   64,   53,    0,    0,   44,   45,   46,   12,   52,
    0,  144,    0,   47,   14,   48,   54,   49,   50,   51,
   23,   53,    0,    0,    0,    0,   14,   25,    0,    0,
  151,    0,    0,    0,   25,    0,    0,    0,   35,   25,
    0,   39,   27,    0,    0,    0,    0,  152,    0,   27,
    0,    0,    0,    0,   27,    0,    0,   44,   45,   46,
   12,   52,    0,    0,  153,   47,   87,   48,   54,   49,
   50,   51,   58,   53,   58,   58,   58,    0,   65,    0,
    0,   65,  157,    0,    0,    0,    0,    0,    0,    0,
   58,   58,   58,   58,    0,    0,   65,   65,   65,   65,
    0,  162,    0,    0,   44,   45,   46,   12,   66,    0,
    0,   66,   47,   10,   48,    0,   49,   50,   51,   25,
   16,   10,    0,    0,   58,   52,   66,   66,   66,   66,
   65,   16,   54,    0,   27,    0,    0,   53,   31,    0,
   52,    0,   31,   44,   45,   46,   12,   54,  123,    0,
    0,   47,   53,   48,   31,   49,   50,   51,    0,    0,
   66,    0,   44,   45,   46,   12,   63,   84,    0,   63,
   47,    0,   48,    0,   49,   50,   51,    0,    0,   44,
   45,   46,   12,    0,   63,    0,   63,   47,    0,   48,
    0,   49,   50,   51,    0,    0,    0,    0,   44,   45,
   46,   12,    0,    0,    0,    0,   47,    0,   48,    0,
   49,   50,   51,    0,   44,   45,   46,   12,   63,    0,
    0,    0,   47,    0,   48,    0,   49,   50,   51,    0,
    0,    0,    0,   44,   45,   46,   12,   68,    0,    0,
   68,   47,    0,   48,    0,   49,   50,   51,    0,    0,
    0,   25,   25,   25,   25,   68,   68,   68,   68,   25,
    0,   25,    0,   25,   25,   25,   27,   27,   27,   27,
    0,    0,   67,    0,   27,   67,   27,    0,   27,   27,
   27,   69,    0,    0,   69,   44,   45,   46,   12,   68,
   67,   67,   67,   67,    0,    0,    0,    0,    0,   69,
   69,   69,   69,    0,   58,   58,   58,   58,   58,   58,
   65,   65,   65,   65,   65,   65,   70,    0,    0,   70,
    0,    0,    0,    0,   67,    0,    0,    0,   62,    0,
    0,   62,    0,   69,   70,   70,   70,   70,    0,    0,
   66,   66,   66,   66,   66,   66,   62,    0,   62,   44,
   45,   46,   12,    1,    2,    3,   73,   90,    0,    0,
    0,    0,    0,    0,   44,   45,   46,   12,   70,   86,
    0,    0,   90,   66,    0,    0,    0,   66,    0,    0,
   62,    0,    0,    0,  112,   90,  113,    0,  116,    0,
   66,    0,    0,   66,    0,    0,    0,    0,    0,    0,
    0,    0,   63,   63,    0,   66,    0,   66,   66,   66,
    0,   90,   90,    0,    0,   90,    0,    0,    0,    0,
    0,   66,    0,    0,    0,  140,    0,    0,    0,  143,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   90,
    0,    0,   90,    0,  154,    0,   66,   66,   66,    0,
   66,   66,    0,    0,    0,    0,    0,   90,  163,    0,
    0,    0,    0,    0,    0,    0,    0,  158,    0,   68,
   68,   68,   68,   68,   68,   66,    0,    0,   66,    0,
   66,    0,    0,    0,    0,    0,    0,    0,   66,    0,
    0,    0,    0,   66,   66,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   67,   67,   67,   67,   67,   67,
    0,    0,    0,   69,   69,   69,   69,   69,   69,   78,
   78,   81,   82,   83,   85,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  117,  118,    0,   70,   70,
   70,   70,   70,   70,    0,    0,    0,    0,   78,    0,
    0,    0,    0,    0,   62,   62,  125,  126,  127,  128,
  129,  130,  131,  132,  133,  134,  135,  136,  137,  138,
  139,    0,    0,    0,    0,    0,    0,    0,    0,  147,
  148,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
   41,   91,   59,   41,   42,   43,   44,   45,   37,   47,
   41,   50,   41,   42,   43,   44,   45,  260,   47,   91,
   40,   59,   60,   61,   62,    7,  261,  262,  263,  272,
   59,   60,   61,   62,   37,   41,   18,   59,   41,   42,
   43,   44,   45,   40,   47,   41,  257,   44,   44,   88,
   91,   41,   44,   91,   44,   93,   59,   60,   61,   62,
  146,   37,   59,   59,   93,   41,   42,   43,   44,   45,
   37,   47,   40,  159,   41,   42,   43,   44,   45,   41,
   47,   93,   44,   59,   60,   61,   62,  260,  123,  123,
   93,   37,   59,   60,   61,   62,   42,   43,   37,   45,
  123,   47,   41,   42,   43,  123,   45,   41,   47,   41,
   44,    8,   44,   40,   60,   61,   62,   93,   40,   40,
   44,   60,   59,   62,   37,   59,   93,   91,   41,   42,
   43,   37,   45,   59,   47,   41,   42,   43,   37,   45,
   59,   47,   59,   42,   43,   91,   45,   60,   47,   62,
   59,  123,   91,  266,   60,    0,   62,    0,   55,   37,
    8,   60,  155,   62,   42,   43,   37,   45,   59,   47,
  260,   42,   43,   59,   45,   72,   47,   59,   91,   59,
   59,   41,   60,   61,   62,   91,   33,   29,  260,   60,
    4,   62,   91,   40,   93,   -1,   37,   -1,   45,   -1,
  272,   42,   43,   -1,   45,   -1,   47,   41,   -1,   43,
   44,   45,   -1,   91,  111,   -1,   -1,   -1,  115,   60,
   91,   62,   33,   -1,   -1,   59,   60,   61,   62,   40,
  261,  262,  263,   -1,   45,  273,  274,  275,  276,  277,
  278,   -1,   -1,   -1,  273,  274,  275,  276,  277,  278,
   91,   33,   -1,   -1,   -1,  261,  262,  263,   40,   93,
   -1,   -1,   -1,   45,   41,   -1,   -1,   44,   -1,   -1,
  273,  274,  275,  276,  277,  278,  261,  262,  263,  264,
   -1,   33,   59,   38,   61,   -1,   -1,   42,   40,   -1,
   -1,   -1,   -1,   45,   -1,   -1,   -1,  273,  274,  275,
  276,  277,  278,   -1,   -1,   -1,  273,  274,  275,  276,
  277,  278,   37,   -1,  125,   70,   93,   42,   43,   74,
   45,   -1,   47,   -1,   -1,   -1,   -1,  273,  274,  275,
  276,  277,  278,   33,  273,  274,  275,  276,  277,  278,
   40,   -1,   -1,  125,   -1,   45,   -1,   37,   -1,   -1,
   -1,   -1,   42,   -1,   -1,   -1,   -1,   47,   -1,   -1,
  273,  274,  275,  276,  277,  278,   91,  273,  274,  275,
  276,  277,  278,  125,  273,  274,  275,  276,  277,  278,
   33,   -1,   -1,   -1,   -1,   -1,   -1,   40,   -1,   -1,
   -1,   -1,   45,   -1,   -1,  273,  274,  275,  276,  277,
  278,   91,  273,  274,  275,  276,  277,  278,   -1,   -1,
  257,  258,  259,  260,  261,  262,  263,   -1,  265,   33,
  267,   -1,  269,  270,  271,  125,   40,   -1,   -1,   -1,
   -1,   45,  273,  274,  275,  276,   -1,   -1,   33,  273,
  274,  275,  276,  277,  278,   40,  257,  258,  259,  260,
   45,   -1,   -1,   -1,  265,   33,  267,   -1,  269,  270,
  271,   -1,   40,   -1,   -1,   -1,   -1,   45,   -1,   -1,
   -1,   -1,  125,   -1,   33,  257,  258,  259,  260,   -1,
   -1,   40,   -1,  265,   -1,  267,   45,  269,  270,  271,
   33,   -1,   -1,   -1,   -1,   -1,   -1,   40,   -1,   -1,
  277,  278,   45,   -1,   -1,  257,  258,  259,  260,   33,
   -1,  125,   -1,  265,    4,  267,   40,  269,  270,  271,
   10,   45,   -1,   -1,   -1,   -1,   16,   33,   -1,   -1,
  125,   -1,   -1,   -1,   40,   -1,   -1,   -1,   28,   45,
   -1,   31,   33,   -1,   -1,   -1,   -1,  125,   -1,   40,
   -1,   -1,   -1,   -1,   45,   -1,   -1,  257,  258,  259,
  260,   33,   -1,   -1,  123,  265,   56,  267,   40,  269,
  270,  271,   41,   45,   43,   44,   45,   -1,   41,   -1,
   -1,   44,  125,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   59,   60,   61,   62,   -1,   -1,   59,   60,   61,   62,
   -1,  125,   -1,   -1,  257,  258,  259,  260,   41,   -1,
   -1,   44,  265,    0,  267,   -1,  269,  270,  271,  125,
    7,    8,   -1,   -1,   93,   33,   59,   60,   61,   62,
   93,   18,   40,   -1,  125,   -1,   -1,   45,   25,   -1,
   33,   -1,   29,  257,  258,  259,  260,   40,   41,   -1,
   -1,  265,   45,  267,   41,  269,  270,  271,   -1,   -1,
   93,   -1,  257,  258,  259,  260,   41,   54,   -1,   44,
  265,   -1,  267,   -1,  269,  270,  271,   -1,   -1,  257,
  258,  259,  260,   -1,   59,   -1,   61,  265,   -1,  267,
   -1,  269,  270,  271,   -1,   -1,   -1,   -1,  257,  258,
  259,  260,   -1,   -1,   -1,   -1,  265,   -1,  267,   -1,
  269,  270,  271,   -1,  257,  258,  259,  260,   93,   -1,
   -1,   -1,  265,   -1,  267,   -1,  269,  270,  271,   -1,
   -1,   -1,   -1,  257,  258,  259,  260,   41,   -1,   -1,
   44,  265,   -1,  267,   -1,  269,  270,  271,   -1,   -1,
   -1,  257,  258,  259,  260,   59,   60,   61,   62,  265,
   -1,  267,   -1,  269,  270,  271,  257,  258,  259,  260,
   -1,   -1,   41,   -1,  265,   44,  267,   -1,  269,  270,
  271,   41,   -1,   -1,   44,  257,  258,  259,  260,   93,
   59,   60,   61,   62,   -1,   -1,   -1,   -1,   -1,   59,
   60,   61,   62,   -1,  273,  274,  275,  276,  277,  278,
  273,  274,  275,  276,  277,  278,   41,   -1,   -1,   44,
   -1,   -1,   -1,   -1,   93,   -1,   -1,   -1,   41,   -1,
   -1,   44,   -1,   93,   59,   60,   61,   62,   -1,   -1,
  273,  274,  275,  276,  277,  278,   59,   -1,   61,  257,
  258,  259,  260,  261,  262,  263,   42,   58,   -1,   -1,
   -1,   -1,   -1,   -1,  257,  258,  259,  260,   93,   55,
   -1,   -1,   73,   38,   -1,   -1,   -1,   42,   -1,   -1,
   93,   -1,   -1,   -1,   70,   86,   72,   -1,   74,   -1,
   55,   -1,   -1,   58,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  277,  278,   -1,   70,   -1,   72,   73,   74,
   -1,  112,  113,   -1,   -1,  116,   -1,   -1,   -1,   -1,
   -1,   86,   -1,   -1,   -1,  111,   -1,   -1,   -1,  115,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  140,
   -1,   -1,  143,   -1,  145,   -1,  111,  112,  113,   -1,
  115,  116,   -1,   -1,   -1,   -1,   -1,  158,  159,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  153,   -1,  273,
  274,  275,  276,  277,  278,  140,   -1,   -1,  143,   -1,
  145,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  153,   -1,
   -1,   -1,   -1,  158,  159,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  273,  274,  275,  276,  277,  278,
   -1,   -1,   -1,  273,  274,  275,  276,  277,  278,   49,
   50,   51,   52,   53,   54,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   75,   76,   -1,  273,  274,
  275,  276,  277,  278,   -1,   -1,   -1,   -1,   88,   -1,
   -1,   -1,   -1,   -1,  277,  278,   96,   97,   98,   99,
  100,  101,  102,  103,  104,  105,  106,  107,  108,  109,
  110,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  119,
  120,
};
}
final static short YYFINAL=5;
final static short YYMAXTOKEN=280;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
"','","'-'",null,"'/'",null,null,null,null,null,null,null,null,null,null,null,
"';'","'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,
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
"expresion : expresion '<' expresion",
"expresion : expresion '>' expresion",
"expresion : '-' expresion",
"expresion : '(' tipo ')' expresion",
"expresion : expresion '[' expresion ']'",
"expresion : '(' expresion ')'",
"expresion : CTE_ENTERA",
"expresion : CTE_DOBLE",
"expresion : CTE_CARACTER",
"expresion : identificador",
"expresion : funcion",
};

//#line 212 "../src/miw/sintactico/sintactico.y"

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
//#line 601 "Parser.java"
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
{ yyval = new OperadorLogico(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), (Expresion)val_peek(0), "<"); }
break;
case 70:
//#line 199 "../src/miw/sintactico/sintactico.y"
{ yyval = new OperadorLogico(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(2), (Expresion)val_peek(0), ">"); }
break;
case 71:
//#line 200 "../src/miw/sintactico/sintactico.y"
{ yyval = new OperadorUnarioNegativo(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(0)); }
break;
case 72:
//#line 201 "../src/miw/sintactico/sintactico.y"
{ yyval = new OperadorUnarioCast(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(0), (Tipo)val_peek(2)); }
break;
case 73:
//#line 202 "../src/miw/sintactico/sintactico.y"
{ yyval = new OperadorAccesoArray(lexico.getLinea(), lexico.getColumna(), (Expresion)val_peek(3), (Expresion)val_peek(1)); }
break;
case 74:
//#line 203 "../src/miw/sintactico/sintactico.y"
{ yyval = val_peek(1);}
break;
case 75:
//#line 204 "../src/miw/sintactico/sintactico.y"
{ yyval = new LiteralEntero(lexico.getLinea(),lexico.getColumna(),(int)val_peek(0)); }
break;
case 76:
//#line 205 "../src/miw/sintactico/sintactico.y"
{ yyval = new LiteralDoble(lexico.getLinea(),lexico.getColumna(),(double)val_peek(0)); }
break;
case 77:
//#line 206 "../src/miw/sintactico/sintactico.y"
{ yyval = new LiteralCaracter(lexico.getLinea(),lexico.getColumna(),(char)val_peek(0)); }
break;
case 78:
//#line 207 "../src/miw/sintactico/sintactico.y"
{ yyval = val_peek(0); }
break;
case 79:
//#line 208 "../src/miw/sintactico/sintactico.y"
{ yyval = val_peek(0); }
break;
//#line 1108 "Parser.java"
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
