package Syntax;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * InitializeVariables
 */
public class InitializeVariables {
    public InitializeVariables(){

    }
    public ArrayList<String> initTerminals(){
        ArrayList<String> terminals = new ArrayList<String>();
        terminals.add("vtype");
        terminals.add("num");
        terminals.add("literal");
        terminals.add("id");
        terminals.add("if");
        terminals.add("else");
        terminals.add("while");
        terminals.add("return");
        terminals.add("addsub");
        terminals.add("multdiv");
        terminals.add("assign");
        terminals.add("comp");
        terminals.add("semi");
        terminals.add("comma");
        terminals.add("lparen");
        terminals.add("rparen");
        terminals.add("lbrace");
        terminals.add("rbrace");
        terminals.add("epsilon");
        return terminals;
    }
    public ArrayList<String> initNonTerminals(){
        ArrayList<String> non_terminals = new ArrayList<String>();
        non_terminals.add("CODE");
        non_terminals.add("DECL");
        non_terminals.add("ARG");
        non_terminals.add("MOREARGS");
        non_terminals.add("BLOCK");
        non_terminals.add("STMT");
        non_terminals.add("RHS");
        non_terminals.add("RHSORFCALL");
        non_terminals.add("EXPR");
        non_terminals.add("EXPR2");
        non_terminals.add("TERM");
        non_terminals.add("TERM2");
        non_terminals.add("FACTOR");
        non_terminals.add("COND");
        non_terminals.add("RETURN");
        non_terminals.add("$");
        return non_terminals;
    }
    public HashMap<Pair<String, String>, ArrayList<String>> initParsingTable(){
        HashMap<Pair<String, String>, ArrayList<String>> parsingTable = new HashMap<Pair<String, String>, ArrayList<String>>();
        ArrayList<String> tmpList = new ArrayList<>();
        // add Epsilons
        parsingTable.put(Pair.make_pair("CODE", "$"), tmpList);

        parsingTable.put(Pair.make_pair("ARG", "rparen"), tmpList);

        parsingTable.put(Pair.make_pair("MOREARGS", "rparen"), tmpList);

        parsingTable.put(Pair.make_pair("BLOCK", "return"), tmpList);
        parsingTable.put(Pair.make_pair("BLOCK", "rbrace"), tmpList);
        
        parsingTable.put(Pair.make_pair("TERM2", "semi"), tmpList);
        parsingTable.put(Pair.make_pair("TERM2", "addsub"), tmpList);
        parsingTable.put(Pair.make_pair("TERM2", "rparen"), tmpList);

        parsingTable.put(Pair.make_pair("EXPR2", "rparen"), tmpList);
        parsingTable.put(Pair.make_pair("EXPR2", "semi"), tmpList);
       
        ArrayList<String> tmpList2 = new ArrayList<>();
        tmpList2.add("CODE");
        tmpList2.add("DECL");
        tmpList2.add("id");
        tmpList2.add("vtype");
        parsingTable.put(Pair.make_pair("CODE", "vtype"), tmpList2);
        
        ArrayList<String> tmpList3 = new ArrayList<>();
        tmpList3.add("semi");
        parsingTable.put(Pair.make_pair("DECL", "semi"), tmpList3);
        
        ArrayList<String> tmpList4 = new ArrayList<>();
        tmpList4.add("rbrace");
        tmpList4.add("RETURN");
        tmpList4.add("BLOCK");
        tmpList4.add("lbrace");
        tmpList4.add("rparen");
        tmpList4.add("ARG");
        tmpList4.add("lparen");
        parsingTable.put(Pair.make_pair("DECL", "lparen"), tmpList4);
        
        ArrayList<String> tmpList5 = new ArrayList<>();
        tmpList5.add("MOREARGS");
        tmpList5.add("id");
        tmpList5.add("vtype");
        parsingTable.put(Pair.make_pair("ARG", "vtype"), tmpList5);

        ArrayList<String> tmpList6 = new ArrayList<>();
        tmpList6.add("MOREARGS");
        tmpList6.add("id");
        tmpList6.add("vtype");
        tmpList6.add("comma");
        parsingTable.put(Pair.make_pair("ARG", "comma"), tmpList6);
        
        ArrayList<String> tmpList7 = new ArrayList<>();
        tmpList7.add("BLOCK");
        tmpList7.add("STMT");
        parsingTable.put(Pair.make_pair("BLOCK", "vtype"), tmpList7);
        
        parsingTable.put(Pair.make_pair("BLOCK", "id"), tmpList7);
        
        parsingTable.put(Pair.make_pair("BLOCK", "if"), tmpList7);
        
        parsingTable.put(Pair.make_pair("BLOCK", "while"), tmpList7);
        
        ArrayList<String> tmpList8 = new ArrayList<>();
        tmpList8.add("semi");
        tmpList8.add("id");
        tmpList8.add("vtype");
        parsingTable.put(Pair.make_pair("STMT", "vtype"), tmpList8);
        
        ArrayList<String> tmpList9 = new ArrayList<>();
        tmpList9.add("semi");
        tmpList9.add("RHSORFCALL");
        tmpList9.add("id");
        parsingTable.put(Pair.make_pair("STMT", "id"), tmpList9);
        

        ArrayList<String> tmpList10 = new ArrayList<>();
        tmpList10.add("rbrace");
        tmpList10.add("BLOCK");
        tmpList10.add("lbrace");
        tmpList10.add("else");
        tmpList10.add("rbrace");
        tmpList10.add("BLOCK");
        tmpList10.add("lbrace");
        tmpList10.add("rparen");
        tmpList10.add("COND");
        tmpList10.add("lparen");
        tmpList10.add("if");
        parsingTable.put(Pair.make_pair("STMT", "if"), tmpList10);
        
        ArrayList<String> tmpList11 = new ArrayList<>();
        tmpList11.add("rbrace");
        tmpList11.add("BLOCK");
        tmpList11.add("lbrace");
        tmpList11.add("rparen");
        tmpList11.add("COND");
        tmpList11.add("lparen");
        tmpList11.add("while");
        parsingTable.put(Pair.make_pair("STMT", "while"), tmpList);
        
        ArrayList<String> tmpList12 = new ArrayList<>();
        tmpList12.add("EXPR");
        parsingTable.put(Pair.make_pair("RHS", "num"), tmpList12);

        parsingTable.put(Pair.make_pair("RHS", "id"), tmpList12);
        
        parsingTable.put(Pair.make_pair("RHS", "lparen"), tmpList12);

        ArrayList<String> tmpList13 = new ArrayList<>();
        tmpList13.add("literal");
        parsingTable.put(Pair.make_pair("RHS", "literal"), tmpList13);

        ArrayList<String> tmpList14 = new ArrayList<>();
        tmpList14.add("RHS");
        tmpList14.add("assign");
        parsingTable.put(Pair.make_pair("RHSORFCALL", "assign"), tmpList14);
        

        ArrayList<String> tmpList15 = new ArrayList<>();
        tmpList15.add("EXPR2");
        tmpList15.add("TERM");
        parsingTable.put(Pair.make_pair("EXPR", "num"), tmpList15);
        
        parsingTable.put(Pair.make_pair("EXPR", "id"), tmpList15);
        
        parsingTable.put(Pair.make_pair("EXPR", "lparen"), tmpList15);
        

        ArrayList<String> tmpList16 = new ArrayList<>();
        tmpList16.add("EXPR");
        tmpList16.add("addsub");
        parsingTable.put(Pair.make_pair("EXPR2", "addsub"), tmpList16);
        
        ArrayList<String> tmpList17 = new ArrayList<>();
        tmpList17.add("TERM2");
        tmpList17.add("FACTOR");
        parsingTable.put(Pair.make_pair("TERM", "num"), tmpList17);

        parsingTable.put(Pair.make_pair("TERM", "id"), tmpList17);
        
        parsingTable.put(Pair.make_pair("TERM", "lparen"), tmpList17);
        
        ArrayList<String> tmpList18 = new ArrayList<>();
        tmpList18.add("TERM");
        tmpList18.add("multdiv");
        parsingTable.put(Pair.make_pair("TERM2", "multdiv"), tmpList18);
        
        ArrayList<String> tmpList19 = new ArrayList<>();
        tmpList19.add("num");
        parsingTable.put(Pair.make_pair("FACTOR", "num"), tmpList19);
        
        ArrayList<String> tmpList20 = new ArrayList<>();
        tmpList20.add("id");
        parsingTable.put(Pair.make_pair("FACTOR", "id"), tmpList20);
        
        ArrayList<String> tmpList21 = new ArrayList<>();
        tmpList21.add("rparen");
        tmpList21.add("EXPR");
        tmpList21.add("lparen");
        parsingTable.put(Pair.make_pair("FACTOR", "lparen"), tmpList21);
        
        ArrayList<String> tmpList22 = new ArrayList<>();
        tmpList22.add("FACTOR");
        tmpList22.add("comp");
        tmpList22.add("FACTOR");
        parsingTable.put(Pair.make_pair("COND", "num"), tmpList22);
        
        parsingTable.put(Pair.make_pair("COND", "id"), tmpList22);
        
        parsingTable.put(Pair.make_pair("COND", "lparen"), tmpList22);
        
        ArrayList<String> tmpList23 = new ArrayList<>();
        tmpList23.add("semi");
        tmpList23.add("FACTOR");
        tmpList23.add("return");
        parsingTable.put(Pair.make_pair("RETURN", "return"), tmpList23);
        

        return parsingTable;
    }
}