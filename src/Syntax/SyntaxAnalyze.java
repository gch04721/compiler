package Syntax;

import java.util.ArrayList;
import java.util.Stack;
import java.util.HashMap;


/**
 * SyntaxAnalyze
 */


public class SyntaxAnalyze {
    final String startSymbol = "CODE";
    final String endSymbol = "$";

    private Stack<String> analyzeStack = new Stack<String>();
    private ArrayList<String> terminalSequence;

    private ArrayList<String> terminals = new ArrayList<String>();
    private ArrayList<String> non_terminals = new ArrayList<String>();

    private HashMap<Pair<String, String>, ArrayList<String>> parsingTable = new HashMap<Pair<String, String>, ArrayList<String>>();

    public SyntaxAnalyze(ArrayList<String> terminalSequence){
        analyzeStack.push(endSymbol);
        analyzeStack.push(startSymbol);
        this.terminalSequence = terminalSequence;

        InitializeVariables init = new InitializeVariables();

        // set terminals & non_terminals
        this.terminals = init.initTerminals();
        this.non_terminals = init.initNonTerminals();
        this.parsingTable = init.initParsingTable();
    }

    public String analyze(){
        String result = "";
        int index = -1;
        final int maxIndex = this.terminalSequence.size();
        boolean indexNext = true;
        String nextTerminal= "";
        while(!this.analyzeStack.isEmpty()){
            if(indexNext){
                index = index + 1;
                if(index == maxIndex){
                    nextTerminal = "$";
                    indexNext = false;
                }
                else{
                    nextTerminal = this.terminalSequence.get(index);
                    indexNext = false;
                }
            }

            String popedString = this.analyzeStack.pop();

            // for debug
            System.out.println(popedString + "    "+ nextTerminal);
            System.out.println(this.analyzeStack);
            System.out.println();

            if(popedString == "$"){
                result = "SUCCESS";
                break;
            }
            
            int chk = this.isTerminals(popedString);
            if(chk == -1){
                // popedString is non-terminal, push new strings
                Pair<String, String> tmpPair = new Pair<String, String>(popedString, nextTerminal); 
                if(!parsingTable.containsKey(tmpPair)){
                    result = "ERROR OCCURED";
                    break;
                }
                ArrayList<String> tmpList = this.parsingTable.get(tmpPair);
                for(String str : tmpList){
                    this.analyzeStack.push(str);
                }
            }
            else if(chk == 1){
                // popedString is terminal, check same or not
                if(popedString != nextTerminal){
                    result = "ERROR OCCURED";
                    break;
                }   
                else{
                    indexNext = true;
                }
            }
            else{
                // wrong value
            }
        }
        return result;
    }

    private int isTerminals(String str){

        if(terminals.contains(str))
            return 1;
        else if(this.non_terminals.contains(str))
            return -1;
        else
            return 0;
    }
}