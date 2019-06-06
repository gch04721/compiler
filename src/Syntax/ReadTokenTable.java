package Syntax;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * ReadTokenTable
 */
public class ReadTokenTable {
    public ArrayList<String> terminals = new ArrayList<String>();
    private ArrayList<String> tokens;
    private ArrayList<String> resultTokens;
    private HashMap<String, String> terminalTable = new HashMap<String, String>();

    public ReadTokenTable(ArrayList<String> tokens, ArrayList<String> resultTokens){
        this.tokens = tokens;
        this.resultTokens = resultTokens;

        terminalTable.put("INT", "vtype");
        terminalTable.put("CHAR", "vtype");
        terminalTable.put("IF", "if");
        terminalTable.put("ELSE", "else");
        terminalTable.put("WHILE", "while");
        terminalTable.put("ARITHMETIC", "arithmetic");
        terminalTable.put("ASSIGN", "assign");
        terminalTable.put("COMPARISON", "comp");
        terminalTable.put("TERMINATE", "semi");
        terminalTable.put("SINGED_INTEGER", "num");
        terminalTable.put("LITERAL_STRING", "literal");
        terminalTable.put("ID", "id");
        terminalTable.put("LPAREN", "lparen");
        terminalTable.put("RPAREN", "rparen");
        terminalTable.put("LBAREN", "lbrace");
        terminalTable.put("RBAREN", "rbrace");
        terminalTable.put("COMMA", "comma");
        terminalTable.put("RETURN", "return");
    }

    private void removeWhiteSpace(){
        for(int i=0; i<this.tokens.size(); i++){
            if(this.tokens.get(i).contains(" ")){
                this.tokens.remove(i);
            }
        }
        for(int i=0; i<this.resultTokens.size(); i++){
            if(this.resultTokens.get(i).contains("WHITESPACE")){
                this.resultTokens.remove(i);
            }
        }
    }

    private void tokenToTerminal(){
        for(int i=0; i<this.resultTokens.size(); i++){
            String terminal = this.terminalTable.get(this.resultTokens.get(i));
            this.resultTokens.set(i, terminal);
        }
    }

    private void divideArithmetic(){
        for(int i=0; i<this.resultTokens.size(); i++){
            if(this.resultTokens.get(i).equals("arithmetic")){
                if(this.tokens.get(i).equals("+") || this.tokens.get(i).equals("-")){
                    this.resultTokens.set(i, "addsub");
                }
                else if(this.tokens.get(i).equals("*") || this.tokens.get(i).equals("/")){
                    this.resultTokens.set(i, "multdiv");
                }
            }
        }
    }

    public ArrayList<String> getTerminalSequence(){
        // remove all white space in token list
        this.removeWhiteSpace();

        // int, char to vtype
        this.tokenToTerminal();

        // ARITHMETIC to addsub and multdiv
        this.divideArithmetic();

        // debug
        // System.out.println(this.tokens);
        // System.out.println(this.resultTokens);

        return this.resultTokens;
    }
}