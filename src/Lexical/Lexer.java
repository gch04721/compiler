
package Lexical;
import Lexical.Token.Tokens;;

import java.util.Map;
import java.util.HashMap;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer{

    private Map<Tokens, String> LexerMap = new HashMap<Tokens, String>();




    private void createMap(){


        // literals
        LexerMap.put(Tokens.ID, "^[a-zA-Z]{1}[0-9a-zA-Z_]{0,31}$");


        // operators
        LexerMap.put(Tokens.PLUS, ".+");
        LexerMap.put(Tokens.MINUS, ".-");
        LexerMap.put(Tokens.TIMES, ".*");
        LexerMap.put(Tokens.DIVIDE, "./");
        LexerMap.put(Tokens.LT, ".<");
        LexerMap.put(Tokens.LE, ".<=");
        LexerMap.put(Tokens.GT, ".>");
        LexerMap.put(Tokens.GE, ".>=");
        LexerMap.put(Tokens.EQ, ".==");
        LexerMap.put(Tokens.NE, ".!=");
        LexerMap.put(Tokens.MINUS, ".!");

        // delimiters
        LexerMap.put(Tokens.LPAREN, ".(");
        LexerMap.put(Tokens.RPAREN, ".)");
        LexerMap.put(Tokens.LBRACKET, ".[");
        LexerMap.put(Tokens.RBRACKET, ".]");
        LexerMap.put(Tokens.COMMA, ".,");
        LexerMap.put(Tokens.PERIOD, "..");
        LexerMap.put(Tokens.SEMICOLON, ".;");
        LexerMap.put(Tokens.COLON, ".:");
        LexerMap.put(Tokens.EQUAL, ".=");

    }


   



}

