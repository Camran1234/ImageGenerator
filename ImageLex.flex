
%%

%class ImageLex
%cup
%unicode
%line
%column
%public

%{
    //Java code if there's something
%}

LineTerminator = \r|\n|\r\n
WhiteSpace     = {LineTerminator} | [ \t\f]
/* Accept Decimal Numbers */
Number = [0-9]+ [\.[0-9]+]?
UserId = [^ ,\{\}\;\:]+

%%

<YYINITIAL> {
    ","     {System.out.println("Token: COMA");
				return new Symbol(COMA, yyline + 1, yycolumn + 1, yytext());}
    "{"     {System.out.println("Token: OPEN_CURLY");
				return new Symbol(OPEN_CURLY, yyline + 1, yycolumn + 1, yytext());}
    "}"     {System.out.println("Token: CLOSE_CURLY");
				return new Symbol(CLOSE_CURLY, yyline + 1, yycolumn + 1, yytext());}
    ";"     {System.out.println("Token: COLON");
				return new Symbol(COLON, yyline + 1, yycolumn + 1, yytext());}
    ":"     {System.out.println("Token: SEMI_COLON");
				return new Symbol(SEMI_COLON, yyline + 1, yycolumn + 1, yytext());}
    {Number} {System.out.println("Token NUMBER: "+yytext());
				return new Symbol(NUMBER, yyline + 1, yycolumn + 1, yytext());}
    {UserId}     {System.out.println("Token USERID: "+yytext());
				return new Symbol(USERID, yyline + 1, yycolumn + 1, yytext());}
    {WhiteSpace} {/* Ignore */}
}

