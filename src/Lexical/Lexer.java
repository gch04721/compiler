
package Lexical;
import Lexical.Token.Tokens;

import java.util.Map;
import java.util.HashMap;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer{

    public static Map<Tokens, String> LexerMap = new HashMap<Tokens, String>();


    private static void createMap(){
        
        //reserved words 
        LexerMap.put(Tokens.MAINPROG, "\\b(mainprog)");
        LexerMap.put(Tokens.FUNCTION, "\\b(function)");
        LexerMap.put(Tokens.PROCEDURE, "\\b(procedure)");
        LexerMap.put(Tokens.MAINPROG, "\\b(begin)");
        LexerMap.put(Tokens.MAINPROG, "\\b(end)");
        LexerMap.put(Tokens.IF, "\\b(if)");
        LexerMap.put(Tokens.THEN, "\\b(then)");
        LexerMap.put(Tokens.ELSE, "\\b(else)");
        LexerMap.put(Tokens.ELIF, "\\b(elif)");
        LexerMap.put(Tokens.NOP, "\\b(nop)");
        LexerMap.put(Tokens.WHILE, "\\b(while)");
        LexerMap.put(Tokens.RETURN, "\\b(return)");
        LexerMap.put(Tokens.FOR, "\\b(for)");
        LexerMap.put(Tokens.INT, "\\b(int)");
        LexerMap.put(Tokens.FLOAT, "\\b(float)");
        LexerMap.put(Tokens.PRINT, "\\b(print)");
        LexerMap.put(Tokens.IN, "\\b(in)");
        
        // literals
        LexerMap.put(Tokens.ID, "^[a-zA-Z]{1}[0-9a-zA-Z_]{0,31}$");

        // operators
        LexerMap.put(Tokens.PLUS, "\\+");
        LexerMap.put(Tokens.MINUS, "\\-");
        LexerMap.put(Tokens.TIMES, "\\*");
        LexerMap.put(Tokens.DIVIDE, "\\/");
        LexerMap.put(Tokens.LT, "\\<");
        LexerMap.put(Tokens.LE, "\\<=");
        LexerMap.put(Tokens.GT, "\\>");
        LexerMap.put(Tokens.GE, "\\>=");
        LexerMap.put(Tokens.EQ, "\\==");
        LexerMap.put(Tokens.NE, "\\!=");
        LexerMap.put(Tokens.MINUS, "\\!");

        // delimiters
        LexerMap.put(Tokens.LPAREN, "\\(");
        LexerMap.put(Tokens.RPAREN, "\\)");
        LexerMap.put(Tokens.LBRACKET, "\\[");
        LexerMap.put(Tokens.RBRACKET, "\\]");
        LexerMap.put(Tokens.COMMA, "\\,");
        LexerMap.put(Tokens.PERIOD, "\\.");
        LexerMap.put(Tokens.SEMICOLON, "\\;");
        LexerMap.put(Tokens.COLON, "\\:");
        LexerMap.put(Tokens.EQUAL, "\\=");

        //comments
        LexerMap.put(Tokens.COMMENTS, "^(/\\*.*\\*/)$");
        LexerMap.put(Tokens.LINECOMMENT, "^(\\/\\/.*)$");
        
    }

    public static boolean isNumber(char c){     //define state number  a.k.a) digits
        if(c>=48 && c<=57){     // ascii code for 0~9
            return true;
        }
        return false;
    }

    public static boolean isLiteral(char c){    //define state Literal a.k.a ) alphabet
        if((c>=65 && c<=90) || (c>=97 && c<=122)){  // a~z && A~Z
            return true;
        }
        return false;
    }

    // public String Analyze(String scan){      // distinguish token type
    //     if(scan.matches(LexerMap.get(Tokens.INT))){
    //         return "INT";
    //     }
    //     else if(checkCHAR(scan)){
    //         return "CHAR";
    //     }
    //     else if(checkIf(scan)){
    //         return "IF";
    //     }
    //     else if(checkElse(scan)){
    //         return "ELSE";
    //     }
    //     else if(checkWhile(scan)){
    //         return "WHILE";
    //     }
    //     else if(checkReturn(scan)){
    //         return "RETURN";
    //     }
    //     else if(checkArithmetic(scan)){
    //         return "ARITHMETIC";
    //     }
    //     else if(checkAssignment(scan)){
    //         return "ASSIGN";
    //     }
    //     else if(checkComparison(scan)){
    //         return "COMPARISON";
    //     }
    //     else if(checkTerminate(scan)){
    //         return "TERMINATE";
    //     }
    //     else if(checkLPAREN(scan)){
    //         return "LPAREN";
    //     }
    //     else if(checkLBAREN(scan)){
    //         return "LBAREN";
    //     }
    //     else if(checkRPAREN(scan)){
    //         return "RPAREN";
    //     }
    //     else if(checkRBAREN(scan)){
    //         return "RBAREN";
    //     }
    //     else if(checkComma(scan)){
    //         return "COMMA";
    //     }
    //     else if(checkWhitespaces(scan)){
    //         return "WHITESPACE";
    //     }
    //     else if(checkSignedInteger(scan)){
    //         return "SINGED_INTEGER";
    //     }
    //     else if(checkLiteralString(scan)){
    //         return "LITERAL_STRING";
    //     }
    //     else if(checkID(scan)){
    //         return "ID";
    //     }
    //     else if(checkComments(scan)){   //Comments are not allowed
    //         return "ERROR (COMMENT)";
    //     }
    //     else{
    //         return "ERROR (TOKEN NOT ACCEPT)"; // others are not allowed
    //     }
    // }

    public static void main(String[] args){
        createMap();
        boolean result = Pattern.matches(LexerMap.get(Tokens.LINECOMMENT), "// its comment");
        System.out.println(result);
    }
}


