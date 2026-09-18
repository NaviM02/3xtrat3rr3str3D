grammar Z;

program : classDeclaration EOF;

classDeclaration : PUBLIC CLASS ID LBRACE classMember* RBRACE;

classMember
    : fieldDeclaration          #FieldMember
    | constructorDeclaration    #ConstructorMember
    | methodDeclaration         #MethodMember
    ;

fieldDeclaration : PUBLIC? type variableDeclaratorList SEMI;

variableDeclaratorList : variableDeclarator (COMMA variableDeclarator)*;

variableDeclarator : ID initializer?;

constructorDeclaration : PUBLIC ID LPAREN parameterList? RPAREN block;

methodDeclaration
    : PUBLIC? type ID LPAREN parameterList? RPAREN block #TypedMethodDeclaration
    | PUBLIC? VOID ID LPAREN parameterList? RPAREN block #VoidMethodDeclaration
    ;

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
    : ASSIGN expression       #ExpressionInitializerValue
    | ASSIGN arrayInitializer #ArrayInitializerValue
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
    : conditionalExpression                                         #ToConditionalExpression
    | postfixExpression assignmentOperator assignmentExpression     #AssignmentExpressionValue
    ;

assignmentOperator
    : ASSIGN
    | PLUS_ASSIGN
    | MINUS_ASSIGN
    | MULT_ASSIGN
    ;

conditionalExpression
    : logicalOrExpression                                       #LogicalOrConditionalExpression
    | logicalOrExpression QUESTION expression COLON expression  #TernaryExpression
    ;

logicalOrExpression
    : logicalOrExpression OR logicalAndExpression  #OrExpr
    | logicalAndExpression                         #ToLogicalAndExpr
    ;

logicalAndExpression
    : logicalAndExpression AND equalityExpression  #AndExpr
    | equalityExpression                           #ToEqualityExpr
    ;

equalityExpression
    : equalityExpression EQUAL comparisonExpression       #EqualExpr
    | equalityExpression NOT_EQUAL comparisonExpression   #NotEqualExpr
    | comparisonExpression                                #ToComparisonExpr
    ;

comparisonExpression
    : comparisonExpression LESS additiveExpression             #LessExpr
    | comparisonExpression GREATER additiveExpression          #GreaterExpr
    | comparisonExpression LESS_EQUAL additiveExpression       #LessEqualExpr
    | comparisonExpression GREATER_EQUAL additiveExpression    #GreaterEqualExpr
    | additiveExpression                                       #ToAdditiveExpr
    ;

additiveExpression
    : additiveExpression PLUS multiplicativeExpression     #AdditionExpr
    | additiveExpression MINUS multiplicativeExpression    #SubtractionExpr
    | multiplicativeExpression                             #ToMultiplicativeExpr
    ;

multiplicativeExpression
    : multiplicativeExpression MULT unaryExpression        #MultiplicationExpr
    | multiplicativeExpression DIV unaryExpression         #DivisionExpr
    | multiplicativeExpression MOD unaryExpression         #ModuloExpr
    | unaryExpression                                      #ToUnaryExpr
    ;

unaryExpression
    : NOT unaryExpression       #NotExpr
    | MINUS unaryExpression     #NegateExpr
    | PLUS unaryExpression      #PositiveExpr
    | postfixExpression         #ToPostfixExpr
    ;

postfixExpression
    : primaryExpression postfixOperation*
    ;

postfixOperation
    : LBRACK expression RBRACK                    #ArrayAccessOp
    | DOT ID                                      #MemberAccessOp
    | LPAREN argumentList? RPAREN                 #FunctionCallOp
    | INCREMENT                                   #PostIncrementOp
    | DECREMENT                                   #PostDecrementOp
    ;

primaryExpression
    : literal                                   #LiteralExpr
    | ID                                        #VariableExpr
    | NEW ID LPAREN argumentList? RPAREN        #ObjCreationExpr
    | arrayCreation                             #ArrayCreationExpr
    | NULL                                      #NullExpr
    | READLN LPAREN RPAREN                        #ReadExpr
    | LPAREN expression RPAREN                  #ParenthesizedExpr
    ;

arrayCreation
    : NEW primitiveType arrayCreationDimensions
    | NEW ID arrayCreationDimensions
    ;

arrayCreationDimensions
    : (LBRACK expression RBRACK)+
    ;

literal
    : INTEGER_LITERAL                             #IntegerLiteralExpr
    | FLOAT_LITERAL                               #FloatLiteralExpr
    | CHAR_LITERAL                                #CharLiteralExpr
    | STRING_LITERAL                              #StringLiteralExpr
    | TRUE                                        #TrueLiteralExpr
    | FALSE                                       #FalseLiteralExpr
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