package Lexical;

public class Token {
    public enum Tokens 
    {
        //reserved words 
        MAINPROG, FUNCTION, PROCEDURE, BEGIN, END, IF, THEN, ELSE, ELIF, 
        NOP, WHILE, RETURN, FOR, INT, FLOAT, PRINT, EPSILON,

        // literals
        ID,

        // operators
        PLUS, MINUS, TIMES, DIVIDE, OR, AND, NOT, XOR, LSHIFT, RSHIFT, LT, LE, GT, GE, EQ, NE, LNOT,

        // delimiters
        LPAREN, RPAREN, LBRACKET, RBRACKET, COMMA, PERIOD, SEMICOLON, COLON, EQUAL,
        
        //comments
        COMMENTS;
    }

}