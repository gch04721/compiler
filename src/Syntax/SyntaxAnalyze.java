package Syntax;

import java.util.ArrayList;
import java.util.Stack;

import app.TokenItem;

import java.util.HashMap;


/**
 * SyntaxAnalyze
 */


public class SyntaxAnalyze {
    final String startSymbol = "PROGRAM";
    final String endSymbol = "$";

    private Stack<String> analyzeStack = new Stack<String>();
    private ArrayList<TokenItem> terminalSequence;

    private ArrayList<String> terminals = new ArrayList<String>();
    private ArrayList<String> non_terminals = new ArrayList<String>();

    private HashMap<Pair<String, String>, ArrayList<String>> parsingTable = new HashMap<Pair<String, String>, ArrayList<String>>();

    public SyntaxAnalyze(ArrayList<TokenItem> terminalSequence){
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
        String result = "Success";
        int index = -1;
        final int maxIndex = this.terminalSequence.size();
        boolean indexNext = true;
        TokenItem nextTerminal= new TokenItem("", "", -1);
        boolean typeInput = true;
        while(!this.analyzeStack.isEmpty()){
            if(indexNext){
                index = index + 1;
                if(index == maxIndex){
                    nextTerminal = new TokenItem("$", "", -1);
                    indexNext = false;
                }
                else{
                    nextTerminal = this.terminalSequence.get(index);
                    indexNext = false;
                }
            }
            // for debug
            //System.out.println(this.analyzeStack);
            String popedString = this.analyzeStack.pop();
            //System.out.println(popedString + "\t"+ nextTerminal.getToken() + ", " +nextTerminal.getString());

            if(popedString == "$"){
                result = "SUCCESS";
                break;
            }

            int chk = this.isTerminals(popedString);
            if(chk == -1){
                // poped string is non-terminal, push new strings
                Pair<String, String> tmpPair = new Pair<String, String>(popedString, nextTerminal.getToken()); 

                // check current nonTerminal with next terminal pair is exist in parsing table
                if(!parsingTable.containsKey(tmpPair)){
                    // that pair isn't exist, given terminal sequence is wrong.                  
                    result = "Line #:" + nextTerminal.getLine() + " Token: " + nextTerminal.getString() +", ERROR OCCURRED, WRONG GRAMMAR";
                    break;
                }

                // get predicted next terminals & non-terminals sequence
                ArrayList<String> tmpList = this.parsingTable.get(tmpPair);
                for(String str : tmpList){
                    this.analyzeStack.push(str);
                }
            }
            else if(chk == 1){
                // popedString is terminal, check same or not
                // if same, move index
                // else terminate program and show error message
                if(popedString != nextTerminal.getToken()){
                    result = "Line #:" + nextTerminal.getLine() + " Token: " + nextTerminal.getString() +", ERROR OCCURRED, WRONG GRAMMAR";
                    break;
                }   
                else{
                    indexNext = true;
                }
            }
            else{
                // wrong input
                // input string is neither terminals and non-terminals 
                result = "Line #:" + nextTerminal.getLine() + " Token: " + nextTerminal.getString() +", ERROR OCCURRED, WRONG GRAMMAR";
                break;
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