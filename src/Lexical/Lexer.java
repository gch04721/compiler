package Lexical;
import Lexical.Token.Tokens;

import java.util.Map;
import java.util.HashMap;
import java.util.regex.Pattern;

public class Lexer{

    public static Map<Tokens, String> LexerMap = new HashMap<Tokens, String>();

    private static void createMap(){
        
        //reserved words 
        LexerMap.put(Tokens.MAINPROG, "\\b(mainprog)");
        LexerMap.put(Tokens.FUNCTION, "\\b(function)");
        LexerMap.put(Tokens.PROCEDURE, "\\b(procedure)");
        LexerMap.put(Tokens.BEGIN, "\\b(begin)");
        LexerMap.put(Tokens.END, "\\b(end)");
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
        LexerMap.put(Tokens.LNOT, "\\!");

        // delimiters
        LexerMap.put(Tokens.LPAREN, "\\(");
        LexerMap.put(Tokens.RPAREN, "\\)");
        LexerMap.put(Tokens.LBRACKET, "\\[");
        LexerMap.put(Tokens.RBRACKET, "\\]");
        LexerMap.put(Tokens.COMMA, "\\,");
        LexerMap.put(Tokens.PERIOD, "\\.");
        LexerMap.put(Tokens.SEMICOLON, "\\;");
        LexerMap.put(Tokens.COLON, "\\:");
        LexerMap.put(Tokens.ASSIGN, "\\=");

        //comments
        LexerMap.put(Tokens.COMMENTS, "^(/\\*.*\\*/)$");
        LexerMap.put(Tokens.LINECOMMENT, "^(\\/\\/.*)$");
        
    }

    public static boolean isInteger(String scan){    
        return Pattern.matches("^[-+]?[0-9]*[0-9][0-9]*$", scan);
    }

    public static boolean isFloat(String scan){    
        return Pattern.matches("^[-+]?[0-9]*(\\.[0-9]+)$", scan);
    }

    public static String Analyze(String scan){   

        createMap();

        //reserved words
        if(Pattern.matches(LexerMap.get(Tokens.MAINPROG), scan)){
            return "MAINPROG";
        }
        else if(Pattern.matches(LexerMap.get(Tokens.FUNCTION), scan)){
            return "FUNCTION";
        }
        else if(Pattern.matches(LexerMap.get(Tokens.PROCEDURE), scan)){
            return "PROCEDURE";
        }
        else if(Pattern.matches(LexerMap.get(Tokens.BEGIN), scan)){
            return "BEGIN";
        }
        else if(Pattern.matches(LexerMap.get(Tokens.END), scan)){
            return "END";
        }
        else if(Pattern.matches(LexerMap.get(Tokens.IF), scan)){
            return "IF";
        }
        else if(Pattern.matches(LexerMap.get(Tokens.THEN), scan)){
            return "THEN";
        }
        else if(Pattern.matches(LexerMap.get(Tokens.ELSE), scan)){
            return "ELSE";
        }
        else if(Pattern.matches(LexerMap.get(Tokens.ELIF), scan)){
            return "ELIF";
        }
        else if(Pattern.matches(LexerMap.get(Tokens.NOP), scan)){
            return "NOP";
        }
        else if(Pattern.matches(LexerMap.get(Tokens.WHILE), scan)){
            return "WHILE";
        }
        else if(Pattern.matches(LexerMap.get(Tokens.RETURN), scan)){
            return "RETURN";
        }
        else if(Pattern.matches(LexerMap.get(Tokens.FOR), scan)){
            return "FOR";
        }
        else if(Pattern.matches(LexerMap.get(Tokens.INT), scan)){
            return "INT";
        }
        else if(Pattern.matches(LexerMap.get(Tokens.FLOAT), scan)){
            return "FLOAT";
        }
        else if(Pattern.matches(LexerMap.get(Tokens.PRINT), scan)){
            return "PRINT";
        }
        else if(Pattern.matches(LexerMap.get(Tokens.IN), scan)){
            return "IN";
        }

        //literals
        else if(Pattern.matches(LexerMap.get(Tokens.ID), scan)){
            return "ID";
        }

        //operators
        else if(Pattern.matches(LexerMap.get(Tokens.PLUS), scan)){
            return "PLUS";
        }
        else if(Pattern.matches(LexerMap.get(Tokens.MINUS), scan)){
            return "MINUS";
        }
        else if(Pattern.matches(LexerMap.get(Tokens.TIMES), scan)){
            return "TIMES";
        }
        else if(Pattern.matches(LexerMap.get(Tokens.DIVIDE), scan)){
            return "DIVIDE";
        }
        else if(Pattern.matches(LexerMap.get(Tokens.LT), scan)){
            return "LESS THEN";
        }
        else if(Pattern.matches(LexerMap.get(Tokens.LE), scan)){
            return "LESS EQUAL";
        }
        else if(Pattern.matches(LexerMap.get(Tokens.GT), scan)){
            return "GREATER THEN";
        }
        else if(Pattern.matches(LexerMap.get(Tokens.GE), scan)){
            return "GREATER EQUAL";
        }
        else if(Pattern.matches(LexerMap.get(Tokens.EQ), scan)){
            return "EQUAL";
        }
        else if(Pattern.matches(LexerMap.get(Tokens.NE), scan)){
            return "NOT EQUAL";
        }
        else if(Pattern.matches(LexerMap.get(Tokens.MINUS), scan)){
            return "MINUS";
        }

        //delimiters
        else if(Pattern.matches(LexerMap.get(Tokens.LPAREN), scan)){
            return "LPAREN";
        }
        else if(Pattern.matches(LexerMap.get(Tokens.RPAREN), scan)){
            return "RPAREN";
        }
        else if(Pattern.matches(LexerMap.get(Tokens.LBRACKET), scan)){
            return "LBRACKET";
        }
        else if(Pattern.matches(LexerMap.get(Tokens.RBRACKET), scan)){
            return "RBRACKET";
        }
        else if(Pattern.matches(LexerMap.get(Tokens.COMMA), scan)){
            return "COMMA";
        }
        else if(Pattern.matches(LexerMap.get(Tokens.PERIOD), scan)){
            return "PERIOD";
        }
        else if(Pattern.matches(LexerMap.get(Tokens.SEMICOLON), scan)){
            return "SEMICOLON";
        }
        else if(Pattern.matches(LexerMap.get(Tokens.COLON), scan)){
            return "COLON";
        }
        else if(Pattern.matches(LexerMap.get(Tokens.ASSIGN), scan)){
            return "ASSIGN";
        }

        //int or float
        else if(isInteger(scan)){
            return "INTEGER";
        }
        else if(isFloat(scan)){
            return "FLOAT_NUMBER";
        }
        
        //comments
        else if(Pattern.matches(LexerMap.get(Tokens.COMMENTS), scan)){
            return "COMMENT";
        }
        else if(Pattern.matches(LexerMap.get(Tokens.LINECOMMENT), scan)){
            return "LINECOMMENT";
        }
        else{
            return "ERROR (TOKEN NOT ACCEPT)"; // others are not allowed
        }
    }

    public static void main(String[] args){
        createMap();
        // boolean result = Pattern.matches(LexerMap.get(Tokens.LINECOMMENT), "// its comment");
        boolean result = Pattern.matches("^[-+]?[0-9]*(\\.[0-9]+)$", "-55013.65");
        System.out.println(result);
    }
}