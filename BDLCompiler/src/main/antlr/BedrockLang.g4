grammar BedrockLang;

program: (importStat SEMICOLON?)* (declareVarStat SEMICOLON | defineCmdStat | whenStat)*;
stat: ifElseStat | whileStat | declareVarStat SEMICOLON | returnStat SEMICOLON | breakStat SEMICOLON | continueStat SEMICOLON;
expr: command #commandExpr
    | (INT | DEC | BOOL | NULL | STRING) #literalExpr
    | varid #varExpr
    | LB expr RB #bracketExpr
    | PLUS expr #positiveExpr
    | MINUS expr #negativeExpr
    | expr PLUS expr #plusExpr
    | expr MINUS expr #minusExpr
    | expr MULTIPLY expr #multiplyExpr
    | expr DIVIDE expr #divideExpr
    | expr EQ expr #equalExpr
    | expr NEQ expr #notEqualExpr
    | expr GTR expr #greaterExpr
    | expr GTREQ expr #greaterEqualExpr
    | expr LWR expr #lowerExpr
    | expr LWREQ expr #lowerEqualExpr
    | expr REMAIN expr #remainExpr
    | varid (LA expr RA)* SET expr #setVarExpr
    | ID (DOT ID | DOT varid)* DOT varid #chainStaticFieldExpr
    | varid (DOT ID | DOT varid)* DOT varid #chainVirtualFieldExpr
    | LEN expr #getLengthExpr
    | expr LA expr RA #getArrayElementExpr
    ;

importStat: IMPORT (ID COMMA?)+ FROM id #importSingleStatic
    | IMPORT MULTIPLY FROM id #importAllStatic
    | IMPORT id #importClass
    ;
whenStat: WHEN ID block;
ifElseStat: IF expr block (ELIF expr block)* (ELSE block)?;
whileStat: WHILE (LA ID RA)? expr block;
defineCmdStat: DEF defineSignature block;
defineSignature: ID (COLON typeid)? (defineSignatureWordSingle | defineSignatureVariable)*;
defineSignatureWordSingle: ID;
defineSignatureVariable: varid COLON typeid;
declareVarStat: VAR varid COLON typeid (SET expr)? #hasTypeVarDeclare
    | VAR varid SET expr #inferTypeVarDeclare
    ;
returnStat: RETURN expr;
breakStat: BREAK ID?;
continueStat: CONTINUE ID?;

command: commandId (ID | expr)*;
block: START (expr SEMICOLON | stat)* END;

typeid: ID (LA RA)*;
id: ID (DOT ID | DOT varid)*;
varid: DOLLAR id;
commandId: ID (DOT ID | DOT varid)* DOT ID #invokeCommand
    | ID #callCommand
    | varid (DOT ID | DOT varid)* DOT ID #virtualCommand
    ;

WS: [ \n\r\t] -> channel(HIDDEN);
COMMENT: '//'~[\n]* -> channel(HIDDEN);
MUTICOMMENT: '/*'.*?'*/' -> channel(HIDDEN);

COMMA: ',';
SEMICOLON: ';'+;
COLON: ':';
LB: '(';
RB: ')';
LA: '[';
RA: ']';
START: '{';
END: '}';
DOT: '.';
EQ: '==';
NEQ: '!=';
SET: '=';
PLUS: '+';
MINUS: '-';
MULTIPLY: '*';
DIVIDE: '/';
REMAIN: '%';
NOT: '!';
AND: '&';
OR: '|';
GTREQ: '>=';
GTR: '>';
LWREQ: '<=';
LWR: '<';
DOLLAR: '$';
LEN: '#';

DEF: 'def';
VAR: 'var';
WHEN: 'when';
RETURN: 'return';
IMPORT: 'import';
FROM: 'from';
AS: 'as';
IF: 'if';
ELSE: 'else';
ELIF: 'elif';
WHILE: 'while';
BREAK: 'break';
CONTINUE: 'continue';

fragment STRCHAR: ~["] | '\\"';
fragment INTEGER: [0-9]+;
fragment ESCAPECHAR: '\\'. ;
STRING: '"' (ESCAPECHAR | ~('"' | '\\'))* '"' | '\'' (ESCAPECHAR | ~('\'' | '\\'))* '\'';
INT: INTEGER;
DEC: INTEGER'.'INTEGER;
BOOL: 'true' | 'false';
NULL: 'null';

fragment IDStart: ~[0-9 @[\]\-+=()*&^%$!~`?<>,.:;"'\\|！#【】{}：。“”‘’/？《》，、·￥…（）；\r\n\t];
fragment IDPart: ~[ @[\]\-+=()*&^%$!~`?<>,.:;"'\\|！#【】{}：。“”‘’/？《》，、·￥…（）；\r\n\t];
ID: IDStart IDPart*;