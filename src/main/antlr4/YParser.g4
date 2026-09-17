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

parameter
    : arrayParameter                         #ArrayParameterParam
    | structureParameter                     #StructureParameterParam
    | type ID                                #NormalParameter
    ;

arrayParameter : LBRACK RBRACK type ID;

structureParameter : LBRACE RBRACE ID ID;

// SENTENCIAS
statement
    : variableDeclaration                    #VariableDeclarationStmt
    | structureDeclaration                   #StructureDeclarationStmt
    | assignmentStatement                    #AssignmentStmt
    | incrementStatement                     #IncrementStmt
    | ifStatement                            #IfStmt
    | switchStatement                       #SwitchStmt
    | forStatement                           #ForStmt
    | whileStatement                         #WhileStmt
    | doWhileStatement                       #DoWhileStmt
    | breakStatement                         #BreakStmt
    | continueStatement                     #ContinueStmt
    | returnStatement                        #ReturnStmt
    | printStatement                         #PrintStmt
    | readStatement                          #ReadStmt
    | expressionStatement                    #ExpressionStmt
    ;

// DECLARACIONES
variableDeclaration
    : variableDeclarationCore NEWLINE
    ;

variableDeclarationCore
    : type ID initializer?
    | type ID arrayDeclaration initializer?
    ;

arrayDeclaration : (LBRACK expression RBRACK)+;

initializer
    : ASSIGN expression                      #ExpressionInitializer
    | ASSIGN arrayInitializer                #ArrayInitializerValue
    | ASSIGN structureInitializer            #StructureInitializerValue
    ;

arrayInitializer : LBRACE expressionList? RBRACE;

structureInitializer : LBRACE expressionList? RBRACE;

expressionList : expression (COMMA expression)*;

// ASIGNACIONES
assignmentStatement : assignableExpression assignmentOperator expression NEWLINE;

assignmentOperator
    : ASSIGN                                  #AssignOperator
    | PLUS_ASSIGN                             #PlusAssignOperator
    | MINUS_ASSIGN                            #MinusAssignOperator
    | MULT_ASSIGN                             #MultiplyAssignOperator
    ;

// INCREMENTO / DECREMENTO
incrementStatement : assignableExpression (INCREMENT | DECREMENT) NEWLINE;

assignableExpression : postfixExpression;

// IF
ifStatement : IF LPAREN expression RPAREN THEN NEWLINE INDENT statement* DEDENT elseIfClause* elseClause?;

elseIfClause : ELSE LPAREN expression RPAREN THEN NEWLINE INDENT statement* DEDENT;

elseClause : OTHERWISE NEWLINE INDENT statement* DEDENT;

// SWITCH
switchStatement : SWITCH LPAREN expression RPAREN COLON NEWLINE INDENT switchCase* defaultCase? DEDENT;

switchCase : CASE expression COLON NEWLINE INDENT statement* breakStatement DEDENT;

defaultCase : ALWAYS COLON NEWLINE INDENT statement* breakStatement DEDENT;

// FOR
forStatement : FOR LPAREN forInitializer? SEMI expression? SEMI forUpdate? RPAREN COLON NEWLINE INDENT statement* DEDENT;

forInitializer
    : variableDeclarationCore
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
    : logicalOrExpression OR logicalAndExpression  #OrExpr
    | logicalAndExpression                         #ToLogicalAndExpr
    ;

logicalAndExpression
    : logicalAndExpression AND equalityExpression  #AndExpr
    | equalityExpression                           #ToEqualityExpr
    ;

equalityExpression
    : equalityExpression EQUAL comparisonExpression      #EqualExpr
    | equalityExpression NOT_EQUAL comparisonExpression  #NotEqualExpr
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
    : NOT unaryExpression          #NotExpr
    | MINUS unaryExpression        #NegateExpr
    | postfixExpression            #ToPostfixExpr
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
    : INTEGER_LITERAL                             #IntegerLiteralExpr
    | FLOAT_LITERAL                               #FloatLiteralExpr
    | CHAR_LITERAL                                #CharLiteralExpr
    | STRING_LITERAL                              #StringLiteralExpr
    | TRUE                                        #TrueLiteralExpr
    | FALSE                                       #FalseLiteralExpr
    | ID                                          #VariableExpr
    | READ LPAREN RPAREN                          #ReadExpr
    | LPAREN expression RPAREN                    #ParenthesizedExpr
    ;

argumentList
    : expression (COMMA expression)*
    ;

// TIPOS
type
    : INTEGER                                     #IntegerType
    | FLOAT                                       #FloatType
    | CHARACTER                                   #CharacterType
    | BOOLEAN                                     #BooleanType
    | STRING                                      #StringType
    | ID                                          #StructureType
    ;