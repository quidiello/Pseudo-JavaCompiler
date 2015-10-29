#!/bin/bash
cd $(dirname $0)
java -cp jflex/JFlex.jar JFlex.Main -d ../src/miw/lexico ../src/miw/lexico/lexico.flex
./byaccj/yacc.macosx -J -v -Jpackage=miw.sintactico -Jsemantic=Object ../src/miw/sintactico/sintactico.y
mv Parser.java ../src/miw/sintactico
mv y.output ../src/miw/sintactico