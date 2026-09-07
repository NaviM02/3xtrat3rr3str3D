lexer grammar YLexer;

tokens { INDENT, DEDENT }

@header {
import org.antlr.v4.runtime.Token;
}

STRUCTURES : '%estructuras';
FUNCTIONS  : '%funciones';
STRUCTURE : 'estructura';
DEFINE    : 'definir';
IF        : 'si';
THEN      : 'entonces';
ELSE      : 'sino';
OTHERWISE : 'contrario';
SWITCH    : 'elegir';
CASE      : 'caso';
ALWAYS    : 'siempre';
FOR       : 'para';
WHILE     : 'mientras';
DO        : 'hacer';
BREAK     : 'romper';
CONTINUE  : 'continuar';
RETURN    : 'retornar';
PRINT     : 'imprimir';
READ      : 'leer';

INTEGER   : 'entero';
FLOAT     : 'flotante';
CHARACTER : 'caracter';
BOOLEAN   : 'bool';
STRING    : 'cadena';
TRUE      : 'verdadero';
FALSE     : 'falso';

INCREMENT     : '++';
DECREMENT     : '--';
PLUS          : '+';
MINUS         : '-';
MULT          : '*';
DIV           : '/';
MOD           : '%';
AND           : '&&';
OR            : '||';
NOT           : '!';
EQUAL         : '==';
NOT_EQUAL     : '!=';
LESS_EQUAL    : '<=';
GREATER_EQUAL : '>=';
LESS          : '<';
GREATER       : '>';
PLUS_ASSIGN   : '+=';
MINUS_ASSIGN  : '-=';
MULT_ASSIGN   : '*=';
ASSIGN        : '=';
ARROW         : '->';

LPAREN : '(';
RPAREN : ')';
LBRACK : '[';
RBRACK : ']';
LBRACE : '{';
RBRACE : '}';
COMMA  : ',';
COLON  : ':';
SEMI   : ';';
DOT    : '.';

FLOAT_LITERAL : [0-9]+ '.' [0-9]+ ;
INTEGER_LITERAL : [0-9]+ ;
CHAR_LITERAL : '\'' . '\'' ;
STRING_LITERAL : '"' (~["\r\n])* '"' ;
ID : [a-zA-Z_][a-zA-Z0-9_]* ;

LINE_COMMENT : '//' ~[\r\n]* -> channel(HIDDEN) ;
BLOCK_COMMENT : '/*' .*? '*/' -> channel(HIDDEN) ;

NEWLINE : '\r'? '\n' ;
WS : [ \t]+ ;