package Syntax;

import java.util.ArrayList;
import java.util.HashMap;
import app.TokenItem;

/**
 * ReadTokenTable
 */
public class ReadTokenTable {
    public ArrayList<String> terminals = new ArrayList<String>();
    private ArrayList<TokenItem> tokens;
    private ArrayList<TokenItem> resultTokens;
    private HashMap<String, String> terminalTable = new HashMap<String, String>();

    public ReadTokenTable(ArrayList<TokenItem> tokens, ArrayList<TokenItem> resultTokens){
        this.tokens = tokens;
        this.resultTokens = resultTokens;

        terminalTable.put("MAINPROG", "mainprog");
        terminalTable.put("FUNCTION", "function");
        terminalTable.put("PROCEDURE", "procedure");
        terminalTable.put("BEGIN", "begin");
        terminalTable.put("END", "end");
        terminalTable.put("IF", "if");
        terminalTable.put("THEN", "then");
        terminalTable.put("ELSE", "else");
        terminalTable.put("ELIF", "elif");
        terminalTable.put("NOP", "nop");
        terminalTable.put("WHILE", "while");
        terminalTable.put("RETURN", "return");
        terminalTable.put("FOR", "for");
        terminalTable.put("INT", "int");
        terminalTable.put("INTEGER", "num_int");
        terminalTable.put("FLOAT", "float");
        terminalTable.put("FLOAT_NUMBER", "num_float");
        terminalTable.put("PRINT", "print");
        terminalTable.put("IN", "in");
        terminalTable.put("ID", "id");
        terminalTable.put("PLUS", "plus");
        terminalTable.put("MINUS", "minus");
        terminalTable.put("TIMES", "mult");
        terminalTable.put("DIVIDE", "div");
        terminalTable.put("LESS THEN", "relop");
        terminalTable.put("LESS EQUAL", "relop");
        terminalTable.put("GREATER THEN", "relop");
        terminalTable.put("GREATER EQUAL", "relop");
        terminalTable.put("EQUAL", "relop");
        terminalTable.put("NOT EQUAL", "relop");
        terminalTable.put("NOT", "!");
        terminalTable.put("LPAREN", "lparen");
        terminalTable.put("RPAREN", "rparen");
        terminalTable.put("LBRACKET", "lbracket");
        terminalTable.put("RBRACKET", "rbracket");
        terminalTable.put("COMMA", "comma");
        terminalTable.put("SEMICOLON", "semicolon");
        terminalTable.put("COLON", "colon");
        terminalTable.put("ASSIGN", "assign");
    }

    private void removeWhiteSpace(){
        for(int i=0; i<this.tokens.size(); i++){
            if(this.tokens.get(i).getToken().contains(" ")){
                this.tokens.remove(i);
            }
        }
        for(int i=0; i<this.resultTokens.size(); i++){
            if(this.resultTokens.get(i).getToken().contains("WHITESPACE")){
                this.resultTokens.remove(i);
            }
        }
    }

    private void tokenToTerminal(){
        // from terminalTable, we change all tokens that given by lexical analyzer
        for(int i=0; i<this.resultTokens.size(); i++){
            String terminal = this.terminalTable.get(this.resultTokens.get(i).getToken());
            this.resultTokens.get(i).setToken(terminal);
        }
    }

    private void divideArithmetic(){
        // Leixcal analyze give us an arithmetic sign ambiguously
        // so we need to read original value and check it is add or sub
        // and it is div or mult
        // for(int i=0; i<this.resultTokens.size(); i++){
        //     if(this.resultTokens.get(i).equals("arithmetic")){
        //         if(this.tokens.get(i).equals("+") || this.tokens.get(i).equals("-")){
        //             this.resultTokens.get(i).token
        //         }
        //         else if(this.tokens.get(i).equals("*") || this.tokens.get(i).equals("/")){
        //             this.resultTokens.set(i, "multdiv");
        //         }
        //     }
        // }
    }

    public ArrayList<TokenItem> getTerminalSequence(){
        // remove all white space in token list
        this.removeWhiteSpace();

        // all given tokens to terminal
        this.tokenToTerminal();

        // ARITHMETIC to addsub and multdiv
        //this.divideArithmetic();

        // debug
        // System.out.println(this.tokens);
        // System.out.println(this.resultTokens);

        return this.resultTokens;
    }
}