parser grammar YParser;

options {
    tokenVocab = YLexer;
}

// PROGRAMA
program : NEWLINE* structuresSection? functionsSection NEWLINE* EOF;

// SECCIONES
structuresSection : STRUCTURES NEWLINE structureDeclaration+;

functionsSection : FUNCTIONS NEWLINE functionDeclaration+;

// ESTRUCTURAS
structureDeclaration : STRUCTURE ID COLON NEWLINE INDENT structureField+ DEDENT;

structureField : type ID arrayDimensions? NEWLINE;

arrayDimensions : (LBRACK INTEGER_LITERAL RBRACK)+;

// FUNCIONES
functionDeclaration : DEFINE ID LPAREN parameterList? RPAREN returnType? COLON NEWLINE INDENT statement* DEDENT;

returnType : ARROW type;

parameterList : parameter (COMMA parameter)*;

parameter : arrayParameter
    | structureParameter
    | type ID
    ;

arrayParameter : LBRACK RBRACK type ID;

structureParameter : LBRACE RBRACE ID ID;

// SENTENCIAS
statement
    : variableDeclaration
    | structureDeclaration
    | assignmentStatement
    | incrementStatement
    | ifStatement
    | switchStatement
    | forStatement
    | whileStatement
    | doWhileStatement
    | breakStatement
    | continueStatement
    | returnStatement
    | printStatement
    | readStatement
    | expressionStatement
    ;

// DECLARACIONES
variableDeclaration
    : type ID initializer? NEWLINE
    | type ID arrayDeclaration initializer? NEWLINE
    | ID ID initializer? NEWLINE
    ;

arrayDeclaration : (LBRACK expression RBRACK)+;

initializer
    : ASSIGN expression
    | ASSIGN arrayInitializer
    | ASSIGN structureInitializer
    ;

arrayInitializer : LBRACE expressionList? RBRACE;

structureInitializer : LBRACE expressionList? RBRACE;

expressionList : expression (COMMA expression)*;

// ASIGNACIONES
assignmentStatement : assignableExpression assignmentOperator expression NEWLINE;

assignmentOperator
    : ASSIGN
    | PLUS_ASSIGN
    | MINUS_ASSIGN
    | MULT_ASSIGN
    ;

// INCREMENTO / DECREMENTO
incrementStatement : assignableExpression (INCREMENT | DECREMENT) NEWLINE;

assignableExpression : postfixExpression;

// IF
ifStatement : IF LPAREN expression RPAREN THEN NEWLINE INDENT statement* DEDENT elseIfClause* elseClause?;

elseIfClause: ELSE IF LPAREN expression RPAREN THEN NEWLINE INDENT statement* DEDENT;

elseClause: ELSE NEWLINE INDENT statement* DEDENT;

// SWITCH
switchStatement : SWITCH LPAREN expression RPAREN COLON NEWLINE INDENT switchCase* defaultCase? DEDENT;

switchCase : CASE expression COLON NEWLINE INDENT statement* breakStatement DEDENT;

defaultCase : ALWAYS COLON NEWLINE INDENT statement* breakStatement DEDENT;

// FOR
forStatement : FOR LPAREN forInitializer? SEMI expression? SEMI forUpdate? RPAREN COLON NEWLINE INDENT statement* DEDENT;

forInitializer
    : variableDeclarationWithoutNewline
    | expression
    ;

variableDeclarationWithoutNewline
    : type ID initializer?
    | type ID arrayDeclaration initializer?
    | ID ID initializer?
    ;

forUpdate : expression;

// WHILE
whileStatement : WHILE LPAREN expression RPAREN DO COLON NEWLINE INDENT statement* DEDENT;

// DO-WHILE
doWhileStatement : DO COLON NEWLINE INDENT statement* DEDENT WHILE LPAREN expression RPAREN NEWLINE;

// CONTROL DE FLUJO
breakStatement : BREAK NEWLINE;

continueStatement : CONTINUE NEWLINE;

returnStatement : RETURN expression? NEWLINE;

// FUNCIONES ESPECIALES
printStatement : PRINT LPAREN expressionList? RPAREN NEWLINE;

readStatement : READ LPAREN RPAREN NEWLINE;

expressionStatement : expression NEWLINE;

// EXPRESIONES
expression
    : logicalOrExpression
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
    : equalityExpression EQUAL comparisonExpression
    | equalityExpression NOT_EQUAL comparisonExpression
    | comparisonExpression
    ;

comparisonExpression
    : comparisonExpression LESS additiveExpression
    | comparisonExpression GREATER additiveExpression
    | comparisonExpression LESS_EQUAL additiveExpression
    | comparisonExpression GREATER_EQUAL additiveExpression
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
    | postfixExpression
    ;

postfixExpression
    : primaryExpression postfixOperation*
    ;

postfixOperation
    : LBRACK expression RBRACK
    | DOT ID
    | LPAREN argumentList? RPAREN
    | INCREMENT
    | DECREMENT
    ;

primaryExpression
    : INTEGER_LITERAL
    | FLOAT_LITERAL
    | CHAR_LITERAL
    | STRING_LITERAL
    | TRUE
    | FALSE
    | ID
    | LPAREN expression RPAREN
    ;

argumentList
    : expression (COMMA expression)*
    ;

// TIPOS
type
    : INTEGER
    | FLOAT
    | CHARACTER
    | BOOLEAN
    | STRING
    | ID
    ;