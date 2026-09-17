grammar Z;

program : classDeclaration EOF;

classDeclaration : PUBLIC CLASS ID LBRACE classMember* RBRACE;

classMember
    : fieldDeclaration
    | constructorDeclaration
    | methodDeclaration
    ;

fieldDeclaration : PUBLIC? type variableDeclaratorList SEMI;

variableDeclaratorList : variableDeclarator (COMMA variableDeclarator)*;

variableDeclarator : ID initializer?;

constructorDeclaration : PUBLIC ID LPAREN parameterList? RPAREN block;

methodDeclaration
    : PUBLIC? type ID LPAREN parameterList? RPAREN block
    | PUBLIC? VOID ID LPAREN parameterList? RPAREN block;

parameterList : parameter (COMMA parameter)*;

parameter : type ID;

block : LBRACE statement* RBRACE;

statement
    : variableDeclarationStatement
    | expressionStatement
    | printStatement
    | printlnStatement
    | readlnStatement
    | ifStatement
    | switchStatement
    | forStatement
    | whileStatement
    | doWhileStatement
    | returnStatement
    | breakStatement
    | continueStatement
    ;

variableDeclarationStatement : type variableDeclaratorList SEMI;

initializer
    : ASSIGN expression
    | ASSIGN arrayInitializer
    ;

statementOrBlock
    : statement
    | block
    ;

ifStatement : IF LPAREN expression RPAREN statementOrBlock (ELSE IF LPAREN expression RPAREN statementOrBlock)* (ELSE statementOrBlock)?;

switchStatement : SWITCH LPAREN expression RPAREN LBRACE switchCase* defaultCase? RBRACE;

switchCase : CASE expression COLON statement*;

defaultCase : DEFAULT COLON statement*;

forStatement : FOR LPAREN forInitializer? SEMI expression? SEMI forUpdate? RPAREN block;

forInitializer
    : variableDeclarationStatementNoSemi
    | expressionList
    ;

variableDeclarationStatementNoSemi : type variableDeclaratorList;

forUpdate : expressionList;

expressionList : expression (COMMA expression)*;

whileStatement : WHILE LPAREN expression RPAREN block;

doWhileStatement : DO block WHILE LPAREN expression RPAREN SEMI;

returnStatement : RETURN expression? SEMI;

breakStatement : BREAK SEMI;

continueStatement : CONTINUE SEMI;

printStatement : PRINT LPAREN argumentList? RPAREN SEMI;

printlnStatement : PRINTLN LPAREN argumentList? RPAREN SEMI;

readlnStatement : READLN LPAREN RPAREN SEMI;

expressionStatement : expression SEMI;

expression : assignmentExpression;

assignmentExpression
    : conditionalExpression
    | postfixExpression assignmentOperator assignmentExpression
    ;

assignmentOperator
    : ASSIGN
    | PLUS_ASSIGN
    | MINUS_ASSIGN
    | MULT_ASSIGN
    ;

conditionalExpression
    : logicalOrExpression
    | logicalOrExpression QUESTION expression COLON expression
    ;

logicalOrExpression
    : logicalOrExpression OR logicalAndExpression
    | logicalAndExpression
    ;

logicalAndExpression
    : logicalAndExpression AND equalityExpression
    | equalityExpression
    ;

equalityExpression
    : equalityExpression EQUAL relationalExpression
    | equalityExpression NOT_EQUAL relationalExpression
    | relationalExpression
    ;

relationalExpression
    : relationalExpression LESS additiveExpression
    | relationalExpression GREATER additiveExpression
    | relationalExpression LESS_EQUAL additiveExpression
    | relationalExpression GREATER_EQUAL additiveExpression
    | additiveExpression
    ;

additiveExpression
    : additiveExpression PLUS multiplicativeExpression
    | additiveExpression MINUS multiplicativeExpression
    | multiplicativeExpression
    ;

multiplicativeExpression
    : multiplicativeExpression MULT unaryExpression
    | multiplicativeExpression DIV unaryExpression
    | multiplicativeExpression MOD unaryExpression
    | unaryExpression
    ;

unaryExpression
    : NOT unaryExpression
    | MINUS unaryExpression
    | PLUS unaryExpression
    | postfixExpression
    ;

postfixExpression
    : primaryExpression postfixOperation*
    ;

postfixOperation
    : arrayAccess
    | memberAccess
    | functionCall
    | INCREMENT
    | DECREMENT
    ;

arrayAccess
    : LBRACK expression RBRACK
    ;

memberAccess
    : DOT ID
    ;

functionCall
    : LPAREN argumentList? RPAREN
    ;

primaryExpression
    : literal
    | ID
    | NEW ID LPAREN argumentList? RPAREN
    | arrayCreation
    | NULL
    | LPAREN expression RPAREN
    ;

arrayCreation
    : NEW primitiveType arrayCreationDimensions
    | NEW ID arrayCreationDimensions
    ;

arrayCreationDimensions
    : (LBRACK expression RBRACK)+
    ;

literal
    : INTEGER_LITERAL
    | FLOAT_LITERAL
    | CHAR_LITERAL
    | STRING_LITERAL
    | TRUE
    | FALSE
    ;

argumentList : expression (COMMA expression)*;

arrayInitializer : LBRACE arrayInitializerElementList? RBRACE;

arrayInitializerElementList
    : arrayInitializerElement (COMMA arrayInitializerElement)*
    ;

arrayInitializerElement
    : expression
    | arrayInitializer
    ;

arrayDimensions : (LBRACK RBRACK)+;

type
    : primitiveType arrayDimensions?
    | referenceType arrayDimensions?
    ;

primitiveType
    : INT
    | DOUBLE
    | CHAR
    | BOOLEAN
    ;

referenceType
    : STRING
    | ID
    ;

PUBLIC : 'public';
CLASS : 'class';
VOID   : 'void';
RETURN : 'return';
IF       : 'if';
ELSE     : 'else';
SWITCH   : 'switch';
CASE     : 'case';
DEFAULT  : 'default';
FOR      : 'for';
WHILE    : 'while';
DO       : 'do';
BREAK    : 'break';
CONTINUE : 'continue';

PRINT   : 'print';
PRINTLN : 'println';
READLN  : 'readln';

NEW : 'new';
NULL : 'null';
TRUE  : 'true';
FALSE : 'false';

INT     : 'int';
DOUBLE  : 'double';
CHAR    : 'char';
BOOLEAN : 'boolean';
STRING : 'String';

PLUS        : '+';
MINUS       : '-';
MULT        : '*';
DIV         : '/';
MOD         : '%';

INCREMENT   : '++';
DECREMENT   : '--';

AND         : '&&';
OR          : '||';
NOT         : '!';

EQUAL       : '==';
NOT_EQUAL   : '!=';

LESS        : '<';
GREATER     : '>';

LESS_EQUAL  : '<=';
GREATER_EQUAL : '>=';

ASSIGN      : '=';
PLUS_ASSIGN : '+=';
MINUS_ASSIGN: '-=';
MULT_ASSIGN : '*=';

QUESTION    : '?';

LPAREN : '(';
RPAREN : ')';

LBRACE : '{';
RBRACE : '}';

LBRACK : '[';
RBRACK : ']';

DOT     : '.';
COMMA   : ',';
COLON   : ':';
SEMI    : ';';

FLOAT_LITERAL
    : [0-9]+ '.' [0-9]+
    ;

INTEGER_LITERAL
    : [0-9]+
    ;

CHAR_LITERAL
    : '\'' (~['\\] | '\\' .) '\''
    ;

STRING_LITERAL
    : '"' (~["\\\r\n] | '\\' .)* '"'
    ;
ID
    : [a-zA-Z_][a-zA-Z0-9_]*
    ;

LINE_COMMENT
    : '//' ~[\r\n]* -> channel(HIDDEN)
    ;

BLOCK_COMMENT
    : '/*' .*? '*/' -> channel(HIDDEN)
    ;

WS
    : [ \t\r\n]+ -> skip
    ;