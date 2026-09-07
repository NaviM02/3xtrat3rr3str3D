grammar PigLatin;

// global
program
    : globalVariablesSection? functionSection? mainSection FINIS_PROGRAM ';' EOF
    ;

globalVariablesSection
    : VARIABLES_SECTION declaration*
    ;

functionSection
    : FUNCTIONS_SECTION functionDeclaration*
    ;

mainSection
    : MAIN_SECTION statement*
    ;

// structures and functions
structDeclaration
    : STRUCTURA ID '{' (structField ';')+ '}' FINIS ';'                  #StructWithSemicolon
    | STRUCTURA ID '{' structField (',' structField)* ','? '}' FINIS ';' #StructWithComma
    ;

structField
    : ESTO ID ':' type      #StructVariableField
    | SERIES ID ':' type    #StructArrayField
    ;

functionDeclaration
    : procedureDeclaration
    | functionWithReturn
    ;

procedureDeclaration
    : ACTIO ID '(' parameterList? ')' functionBody FINIS ';'
    ;

functionWithReturn
    : RATIO type ID '(' parameterList? ')' functionBody FINIS ';'
    ;

parameterList
    : parameters+=parameter (',' parameters+=parameter)*
    ;

parameter
    : ESTO ID ':' type
    ;

functionBody
    : '{' localVariableSection? statement* '}'
    ;

// declarations
declaration
    : variableDeclaration
    | arrayDeclaration
    | structDeclaration
    ;

localVariableSection
    : VARIABILES '[' declaration* ']'
    ;

variableDeclaration
    : ESTO ID ':' type ';'                           #NormalVarDeclaration
    | ESTO ID ':' type expression ';'                #NormalVarDeclaration
    | ESTO ID ':' type structInitializer ';'?        #NormalVarDeclaration
    ;

initializer
    : expression          #ExprInit
    | structInitializer   #StructInit
    ;

structInitializer
    : '{' fields+=structFieldInitializer (',' fields+=structFieldInitializer)* '}'
    ;

structFieldInitializer
    : ID ':' initializer
    ;

arrayConstructor
    : type '[' expression ']'
    ;

arrayDeclaration
    : SERIES ID '[' expression ']' (':' type)? arrayInitializer? ';'
    ;

arrayInitializer
    : '{' values+=expression (',' values+=expression)* '}'
    ;

type
    : NUMERUS
    | DECIMALIS
    | TEXTUM
    | BOOL
    | LITTERA
    | ID
    ;

// statements
statement
    : assignment         #AssignmentStmt
    | incrementStatement  #IncrementStmt
    | functionCallStatement #FunctionCallStatementStmt
    | ifStatement        #IfStmt
    | whileStatement     #WhileStmt
    | doWhileStatement   #DoWhileStmt
    | forStatement       #ForStmt
    | returnStatement    #ReturnStmt
    | breakStatement     #BreakStmt
    | continueStatement  #ContinueStmt
    | printStatement     #PrintStmt
    | readStatement      #ReadStmt
    ;

assignment
    : postfixExpression '=' expression ';'
    | postfixExpression '=' structInitializer ';'?
    ;

incrementStatement
    : incrementableExpression PLUSPLUS  ';'
    | incrementableExpression MINUSMINUS ';'
    ;

incrementableExpression
    : ID                                     #IncrementVariable
    | postfixExpression '[' expression ']'   #IncrementArrayAccess
    | postfixExpression '.' ID               #IncrementMemberAccess
    ;

functionCallStatement
    : callableExpression functionArguments ';'
    ;

callableExpression
    : ID                                     #CallVariable
    | postfixExpression '[' expression ']'   #CallArrayAccess
    | postfixExpression '.' ID               #CallMemberAccess
    ;

ifStatement
    : SI '(' expression ')' block elseIfStatement* elseStatement? FINIS ';'
    ;

elseIfStatement
    : ALITER '(' expression ')' block
    ;

elseStatement
    : ALITER block
    ;

block
    : '{' statement* '}'
    ;

whileStatement
    : DUM '(' expression ')' block FINIS ';'
    ;

doWhileStatement
    : FACERE block DUM '(' expression ')' ';'
    ;

forStatement
    : PER '(' variableDeclaration expression ';' expression ')' block
    ;

returnStatement
    : REDDERE expression? ';'
    ;

continueStatement
    : PERGE ';'
    ;

breakStatement
    : INTERRUMPE ';'
    ;

readStatement
    : postfixExpression? READ
    ;

printStatement
    : PRINT expressions+=expression (PRINT expressions+=expression)* ';'
    ;

// expressions
expression
    : logicalOrExpression
    ;

logicalOrExpression
    : logicalOrExpression OR logicalAndExpression      #OrExpr
    | logicalAndExpression                             #ToLogicalAndExpr
    ;

logicalAndExpression
    : logicalAndExpression AND equalityExpression      #AndExpr
    | equalityExpression                               #ToEqualityExpr
    ;

equalityExpression
    : equalityExpression EQUAL comparisonExpression        #EqualExpr
    | equalityExpression NOT_EQUAL comparisonExpression    #NotEqualExpr
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
    | unaryExpression                                      #ToUnaryExpr
    ;

unaryExpression
    : NON unaryExpression          #NotExpr
    | MINUS unaryExpression        #NegateExpr
    | postfixExpression            #ToPostfixExpr
    ;

postfixExpression
    : postfixExpression '[' expression ']'        #ArrayAccessExpr
    | postfixExpression '.' ID                    #MemberAccessExpr
    | postfixExpression functionArguments         #FunctionCallExpr
    | postfixExpression PLUSPLUS                  #PostIncrementExpr
    | postfixExpression MINUSMINUS                #PostDecrementExpr
    | primaryExpression                           #ToPrimaryExpr
    ;

functionArguments
    : '(' argumentList? ')'
    ;

argumentList
    : arguments+=expression (',' arguments+=expression)*
    ;

primaryExpression
    : NUMBER                 #NumberLiteralExpr
    | DECIMAL                #DecimalLiteralExpr
    | STRING                 #StringLiteralExpr
    | CHAR                   #CharLiteralExpr
    | VERUM                  #TrueLiteralExpr
    | FALSUS                 #FalseLiteralExpr
    | ID                     #VariableExpr
    | '(' expression ')'     #ParenthesizedExpr
    ;

// Lexer
// key words for sections
VARIABLES_SECTION : 'VARIABILES>';
FUNCTIONS_SECTION : 'MUNERA>';
MAIN_SECTION      : 'MAIOR>';
FINIS_PROGRAM     : 'FINIS';

// key words
STRUCTURA  : 'structura';
FINIS      : 'finis';
ESTO       : 'esto';
SERIES     : 'series';
ACTIO      : 'actio';
RATIO      : 'ratio';
SI         : 'si';
ALITER     : 'aliter';
DUM        : 'dum';
FACERE     : 'facere';
PER        : 'per';
REDDERE    : 'reddere';
PERGE      : 'perge';
INTERRUMPE : 'interrumpe';
VARIABILES : 'VARIABILES';

// key word for types
NUMERUS   : 'numerus';
DECIMALIS : 'decimalis';
TEXTUM    : 'textum';
LITTERA   : 'littera';
BOOL      : 'bool';
VERUM     : 'verum';
FALSUS    : 'falsus';

// operators
PLUS        : '+';
MINUS       : '-';
MULT        : '*';
DIV         : '/';
PLUSPLUS    : '++';
MINUSMINUS  : '--';
AND         : '&&';
OR          : '||';
NON         : 'non';
EQUAL       : '==';
NOT_EQUAL   : '!=';
LESS        : '<';
GREATER     : '>';
LESS_EQUAL  : '<=';
GREATER_EQUAL : '>=';
READ        : '<<';
PRINT       : '>>';

// literals
NUMBER  : [0-9]+ ;
DECIMAL : [0-9]+ '.' [0-9]+ ;
STRING  : '"' (~["\r\n])* '"' ;
CHAR    : '\'' . '\'' ;
ID      : [a-zA-Z_][a-zA-Z0-9_]* ;

// comments
LINE_COMMENT  : '//' ~[\r\n]* -> skip ;
BLOCK_COMMENT : '##' .*? '##' -> skip ;
WS            : [ \t\r\n]+ -> skip ;
