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
        terminals.add("mainprog");
        terminals.add("function");
        terminals.add("procedure");
        terminals.add("id");
        terminals.add("if");
        terminals.add("else");
        terminals.add("while");
        terminals.add("return");
        terminals.add("plus");
        terminals.add("mult");
        terminals.add("assign");
        terminals.add("begin");
        terminals.add("semicolon");
        terminals.add("comma");
        terminals.add("lparen");
        terminals.add("rparen");
        terminals.add("lbrace");
        terminals.add("rbrace");
        terminals.add("lbracket");
        terminals.add("rbracket");
        terminals.add("epsilon");
        terminals.add("end");
        terminals.add("nop");
        terminals.add("elif");
        terminals.add("print");
        terminals.add("for");
        terminals.add("minus");
        terminals.add("div");
        terminals.add("!");
        terminals.add("in");
        terminals.add("num_int");
        terminals.add("num_float");
        terminals.add("int");
        terminals.add("colon");
        terminals.add("float");
        terminals.add("relop");
        return terminals;
    }
    public ArrayList<String> initNonTerminals(){
        ArrayList<String> non_terminals = new ArrayList<String>();
        non_terminals.add("PROGRAM");
        non_terminals.add("DECLS");
        non_terminals.add("SUB_DECLS");
        non_terminals.add("SUB_DECL");
        non_terminals.add("COMPOUND_STMT");
        non_terminals.add("TYPE");
        non_terminals.add("TYPE_ARR");
        non_terminals.add("ID_LIST");
        non_terminals.add("ID_LIST_S");
        non_terminals.add("STD_TYPE");
        non_terminals.add("SUB_HEAD");
        non_terminals.add("ARGS");
        non_terminals.add("PARAM_LIST");
        non_terminals.add("STMT_LIST");
        non_terminals.add("STMT_LIST_S");
        non_terminals.add("STMT");
        non_terminals.add("STMT_S");
        non_terminals.add("STMT_SS");
        non_terminals.add("VAR");
        non_terminals.add("VAR_S");
        non_terminals.add("EXP");
        non_terminals.add("EXP_S");
        non_terminals.add("PRINT_STMT");
        non_terminals.add("PRINT_STMT_S");
        non_terminals.add("PROC_STMT");
        non_terminals.add("PROC_STMT_S");
        non_terminals.add("IF_STMT");
        non_terminals.add("WHILE_STMT");
        non_terminals.add("FOR_STMT");
        non_terminals.add("ELIF_STMT");
        non_terminals.add("ACTUAL_PARAM_EXP");
        non_terminals.add("EXP_LIST");
        non_terminals.add("EXP_LIST_S");
        non_terminals.add("SIMPLE_EXP");
        non_terminals.add("SIMPLE_EXP_S");
        non_terminals.add("RELOP");
        non_terminals.add("TERM");
        non_terminals.add("TERM_S");
        non_terminals.add("ADDOP");
        non_terminals.add("MULTOP");
        non_terminals.add("FACTOR");
        non_terminals.add("FACTOR_S");
        non_terminals.add("SIGN");
        non_terminals.add("$");
        return non_terminals;
    }

    public HashMap<Pair<String, String>, ArrayList<String>> initParsingTable(){
        HashMap<Pair<String, String>, ArrayList<String>> parsingTable = new HashMap<Pair<String, String>, ArrayList<String>>();
        ArrayList<String> tmpList = new ArrayList<>();

        ArrayList<String> tmpList2 = new ArrayList<>();
        tmpList2.add("COMPOUND_STMT");
        tmpList2.add("SUB_DECLS");
        tmpList2.add("DECLS");
        tmpList2.add("semicolon");
        tmpList2.add("id");
        tmpList2.add("mainprog");
        parsingTable.put(Pair.make_pair("PROGRAM", "mainprog"), tmpList2);

        ArrayList<String> tmpList3 = new ArrayList<>();
        tmpList3.add("DECLS");
        tmpList3.add("semicolon");
        tmpList3.add("ID_LIST");
        tmpList3.add("TYPE");
        parsingTable.put(Pair.make_pair("DECLS", "int"), tmpList3);
        parsingTable.put(Pair.make_pair("DECLS", "float"), tmpList3);
            
        // add Epsilons move
        parsingTable.put(Pair.make_pair("DECLS", "begin"), tmpList);
        parsingTable.put(Pair.make_pair("DECLS", "semicolon"), tmpList);
        parsingTable.put(Pair.make_pair("DECLS", "function"), tmpList);
        parsingTable.put(Pair.make_pair("DECLS", "procedure"), tmpList);

        ArrayList<String> tmpList4 = new ArrayList<>();
        tmpList4.add("ID_LIST_S");
        tmpList4.add("id");
        parsingTable.put(Pair.make_pair("ID_LIST", "id"), tmpList4);

        ArrayList<String> tmpList5 = new ArrayList<>();
        tmpList5.add("ID_LIST_S");
        tmpList5.add("id");
        tmpList5.add("comma");
        parsingTable.put(Pair.make_pair("ID_LIST_S", "comma"), tmpList5);

        parsingTable.put(Pair.make_pair("ID_LIST_S", "colon"), tmpList);
        parsingTable.put(Pair.make_pair("ID_LIST_S", "semicolon"), tmpList);

        ArrayList<String> tmpList6 = new ArrayList<>();
        tmpList6.add("STD_TYPE");
        parsingTable.put(Pair.make_pair("TYPE", "int"), tmpList6);
        parsingTable.put(Pair.make_pair("TYPE", "float"), tmpList6);

        ArrayList<String> tmpList7 = new ArrayList<>();
        tmpList7.add("rbracket");
        tmpList7.add("num_int");
        tmpList7.add("lbracket");
        parsingTable.put(Pair.make_pair("TYPE_ARR", "lbracket"), tmpList7);

        parsingTable.put(Pair.make_pair("TYPE_ARR", "semicolon"), tmpList);
        parsingTable.put(Pair.make_pair("TYPE_ARR", "id"), tmpList);
        parsingTable.put(Pair.make_pair("TYPE_ARR", "int"), tmpList);
        parsingTable.put(Pair.make_pair("TYPE_ARR", "float"), tmpList);
        parsingTable.put(Pair.make_pair("TYPE_ARR", "rparen"), tmpList);

        ArrayList<String> tmpList8 = new ArrayList<>();
        tmpList8.add("TYPE_ARR");
        tmpList8.add("int");
        parsingTable.put(Pair.make_pair("STD_TYPE", "int"), tmpList8);

        ArrayList<String> tmpList9 = new ArrayList<>();
        tmpList9.add("TYPE_ARR");
        tmpList9.add("float");
        parsingTable.put(Pair.make_pair("STD_TYPE", "float"), tmpList9);

        ArrayList<String> tmpList10 = new ArrayList<>();
        tmpList10.add("SUB_DECLS");
        tmpList10.add("SUB_DECL");
        parsingTable.put(Pair.make_pair("SUB_DECLS", "function"), tmpList10);
        parsingTable.put(Pair.make_pair("SUB_DECLS", "procedure"), tmpList10);

        parsingTable.put(Pair.make_pair("SUB_DECLS", "begin"), tmpList);

        ArrayList<String> tmpList11 = new ArrayList<>();
        tmpList11.add("COMPOUND_STMT");
        tmpList11.add("DECLS");
        tmpList11.add("SUB_HEAD");
        parsingTable.put(Pair.make_pair("SUB_DECL", "function"), tmpList11);
        parsingTable.put(Pair.make_pair("SUB_DECL", "procedure"), tmpList11);

        ArrayList<String> tmpList12 = new ArrayList<>();
        tmpList12.add("semicolon");
        tmpList12.add("STD_TYPE");
        tmpList12.add("colon");
        tmpList12.add("ARGS");
        tmpList12.add("id");
        tmpList12.add("function");
        parsingTable.put(Pair.make_pair("SUB_HEAD", "function"), tmpList12);

        ArrayList<String> tmpList13 = new ArrayList<>();
        tmpList13.add("semicolon");
        tmpList13.add("ARGS");
        tmpList13.add("id");
        tmpList13.add("procedure");
        parsingTable.put(Pair.make_pair("SUB_HEAD", "procedure"), tmpList13);

        ArrayList<String> tmpList14 = new ArrayList<>();
        tmpList14.add("rparen");
        tmpList14.add("PARAM_LIST");
        tmpList14.add("lparen");
        parsingTable.put(Pair.make_pair("ARGS", "lparen"), tmpList14);

        parsingTable.put(Pair.make_pair("ARGS", "colon"), tmpList);
        parsingTable.put(Pair.make_pair("ARGS", "semicolon"), tmpList);

        ArrayList<String> tmpList15 = new ArrayList<>();
        tmpList15.add("PARAM_LIST");
        tmpList15.add("colon");
        tmpList15.add("ID_LIST");
        parsingTable.put(Pair.make_pair("PARAM_LIST", "id"), tmpList15);

        ArrayList<String> tmpList16 = new ArrayList<>();
        tmpList16.add("PARAM_LIST");
        tmpList16.add("TYPE");
        parsingTable.put(Pair.make_pair("PARAM_LIST", "int"), tmpList16);
        parsingTable.put(Pair.make_pair("PARAM_LIST", "float"), tmpList16);

        ArrayList<String> tmpList17 = new ArrayList<>();
        tmpList17.add("PARAM_LIST");
        tmpList17.add("semicolon");
        parsingTable.put(Pair.make_pair("PARAM_LIST", "semicolon"), tmpList17);

        parsingTable.put(Pair.make_pair("PARAM_LIST", "rparen"), tmpList);

        ArrayList<String> tmpList18 = new ArrayList<>();
        tmpList18.add("end");
        tmpList18.add("STMT_LIST");
        tmpList18.add("begin");
        parsingTable.put(Pair.make_pair("COMPOUND_STMT", "begin"), tmpList18);

        ArrayList<String> tmpList19 = new ArrayList<>();
        tmpList19.add("STMT_LIST_S");
        tmpList19.add("STMT");
        parsingTable.put(Pair.make_pair("STMT_LIST", "return"), tmpList19);
        parsingTable.put(Pair.make_pair("STMT_LIST", "id"), tmpList19);
        parsingTable.put(Pair.make_pair("STMT_LIST", "if"), tmpList19);
        parsingTable.put(Pair.make_pair("STMT_LIST", "for"), tmpList19);
        parsingTable.put(Pair.make_pair("STMT_LIST", "print"), tmpList19);
        parsingTable.put(Pair.make_pair("STMT_LIST", "while"), tmpList19);
        parsingTable.put(Pair.make_pair("STMT_LIST", "begin"), tmpList19);
        parsingTable.put(Pair.make_pair("STMT_LIST", "nop"), tmpList19);

        ArrayList<String> tmpList20 = new ArrayList<>();
        tmpList20.add("STMT_LIST_S");
        tmpList20.add("STMT");
        tmpList20.add("semicolon");
        parsingTable.put(Pair.make_pair("STMT_LIST_S", "semicolon"), tmpList20);

        parsingTable.put(Pair.make_pair("STMT_LIST_S", "end"), tmpList);

        ArrayList<String> tmpList21 = new ArrayList<>();
        tmpList21.add("STMT_S");
        tmpList21.add("id");
        parsingTable.put(Pair.make_pair("STMT", "id"), tmpList21);

        ArrayList<String> tmpList101 = new ArrayList<>();
        tmpList101.add("STMT_SS");
        parsingTable.put(Pair.make_pair("STMT_S", "assign"), tmpList101);

        ArrayList<String> tmpList102 = new ArrayList<>();
        tmpList102.add("PROC_STMT_S");
        parsingTable.put(Pair.make_pair("STMT_S", "lparen"), tmpList102);

        ArrayList<String> tmpList105 = new ArrayList<>();
        tmpList105.add("STMT_SS");
        tmpList105.add("VAR_S");
        parsingTable.put(Pair.make_pair("STMT_S", "lbracket"), tmpList105);
        parsingTable.put(Pair.make_pair("STMT_S", "assign"), tmpList105);

        ArrayList<String> tmpList107 = new ArrayList<>();
        tmpList107.add("EXP");
        tmpList107.add("assign");
        parsingTable.put(Pair.make_pair("STMT_SS", "assign"), tmpList107);

        parsingTable.put(Pair.make_pair("STMT_SS", "elif"), tmpList);
        parsingTable.put(Pair.make_pair("STMT_SS", "else"), tmpList);
        parsingTable.put(Pair.make_pair("STMT_SS", "semicolon"), tmpList);
        parsingTable.put(Pair.make_pair("STMT_SS", "end"), tmpList);

        ArrayList<String> tmpList22 = new ArrayList<>();
        tmpList22.add("PRINT_STMT");
        parsingTable.put(Pair.make_pair("STMT", "print"), tmpList22);

        ArrayList<String> tmpList24 = new ArrayList<>();
        tmpList24.add("COMPOUND_STMT");
        parsingTable.put(Pair.make_pair("STMT", "begin"), tmpList24);

        ArrayList<String> tmpList25 = new ArrayList<>();
        tmpList25.add("IF_STMT");
        parsingTable.put(Pair.make_pair("STMT", "if"), tmpList25);

        ArrayList<String> tmpList26 = new ArrayList<>();
        tmpList26.add("WHILE_STMT");
        parsingTable.put(Pair.make_pair("STMT", "while"), tmpList26);

        ArrayList<String> tmpList27 = new ArrayList<>();
        tmpList27.add("FOR_STMT");
        parsingTable.put(Pair.make_pair("STMT", "for"), tmpList27);

        ArrayList<String> tmpList28 = new ArrayList<>();
        tmpList28.add("EXP");
        tmpList28.add("return");
        parsingTable.put(Pair.make_pair("STMT", "return"), tmpList28);

        ArrayList<String> tmpList200 = new ArrayList<>();
        tmpList200.add("nop");
        parsingTable.put(Pair.make_pair("STMT", "nop"), tmpList200);

        ArrayList<String> tmpList29 = new ArrayList<>();
        tmpList29.add("ELIF_STMT");
        tmpList29.add("STMT");
        tmpList29.add("colon");
        tmpList29.add("EXP");
        tmpList29.add("if");
        parsingTable.put(Pair.make_pair("IF_STMT", "if"), tmpList29);

        ArrayList<String> tmpList30 = new ArrayList<>();
        tmpList30.add("ELIF_STMT");
        tmpList30.add("STMT");
        tmpList30.add("colon");
        tmpList30.add("EXP");
        tmpList30.add("elif");
        parsingTable.put(Pair.make_pair("ELIF_STMT", "elif"), tmpList30);

        ArrayList<String> tmpList31 = new ArrayList<>();
        tmpList31.add("STMT");
        tmpList31.add("colon");
        tmpList31.add("else");
        parsingTable.put(Pair.make_pair("ELIF_STMT", "else"), tmpList31);

        ArrayList<String> tmpList32 = new ArrayList<>();
        tmpList32.add("STMT");
        tmpList32.add("colon");
        tmpList32.add("EXP");
        tmpList32.add("while");
        parsingTable.put(Pair.make_pair("WHILE_STMT", "while"), tmpList32);

        ArrayList<String> tmpList33 = new ArrayList<>();
        tmpList33.add("STMT");
        tmpList33.add("colon");
        tmpList33.add("EXP");
        tmpList33.add("in");
        tmpList33.add("EXP");
        tmpList33.add("for");
        parsingTable.put(Pair.make_pair("FOR_STMT", "for"), tmpList33);

        ArrayList<String> tmpList34 = new ArrayList<>();
        tmpList34.add("PRINT_STMT_S");
        tmpList34.add("print");
        parsingTable.put(Pair.make_pair("PRINT_STMT", "print"), tmpList34);

        ArrayList<String> tmpList35 = new ArrayList<>();
        tmpList35.add("rparen");
        tmpList35.add("EXP");
        tmpList35.add("lparen");
        parsingTable.put(Pair.make_pair("PRINT_STMT_S", "lparen"), tmpList35);

        parsingTable.put(Pair.make_pair("PRINT_STMT_S", "else"), tmpList);
        parsingTable.put(Pair.make_pair("PRINT_STMT_S", "elif"), tmpList);
        parsingTable.put(Pair.make_pair("PRINT_STMT_S", "semicolon"), tmpList);
        parsingTable.put(Pair.make_pair("PRINT_STMT_S", "end"), tmpList);

        ArrayList<String> tmpList36 = new ArrayList<>();
        tmpList36.add("VAR_S");
        tmpList36.add("id");
        parsingTable.put(Pair.make_pair("VAR", "id"), tmpList36);

        ArrayList<String> tmpList37 = new ArrayList<>();
        tmpList37.add("rbracket");
        tmpList37.add("EXP");
        tmpList37.add("lbracket");
        parsingTable.put(Pair.make_pair("VAR_S", "lbracket"), tmpList37);

        parsingTable.put(Pair.make_pair("VAR_S", "int"), tmpList);
        parsingTable.put(Pair.make_pair("VAR_S", "float"), tmpList);
        parsingTable.put(Pair.make_pair("VAR_S", "num_int"), tmpList);
        parsingTable.put(Pair.make_pair("VAR_S", "num_float"), tmpList);
        parsingTable.put(Pair.make_pair("VAR_S", "id"), tmpList);
        parsingTable.put(Pair.make_pair("VAR_S", "!"), tmpList);
        parsingTable.put(Pair.make_pair("VAR_S", "plus"), tmpList);
        parsingTable.put(Pair.make_pair("VAR_S", "minus"), tmpList);
        parsingTable.put(Pair.make_pair("VAR_S", "assign"), tmpList);

        ArrayList<String> tmpList38 = new ArrayList<>();
        tmpList38.add("PROC_STMT_S");
        tmpList38.add("id");
        parsingTable.put(Pair.make_pair("PROC_STMT", "id"), tmpList38);

        ArrayList<String> tmpList100 = new ArrayList<>();
        tmpList100.add("rparen");
        tmpList100.add("ACTUAL_PARAM_EXP");
        tmpList100.add("lparen");
        parsingTable.put(Pair.make_pair("PROC_STMT_S", "lparen"), tmpList100);

        ArrayList<String> tmpList39 = new ArrayList<>();
        tmpList39.add("EXP_LIST");
        parsingTable.put(Pair.make_pair("ACTUAL_PARAM_EXP", "int"), tmpList39);
        parsingTable.put(Pair.make_pair("ACTUAL_PARAM_EXP", "float"), tmpList39);
        parsingTable.put(Pair.make_pair("ACTUAL_PARAM_EXP", "num_int"), tmpList39);
        parsingTable.put(Pair.make_pair("ACTUAL_PARAM_EXP", "num_float"), tmpList39);
        parsingTable.put(Pair.make_pair("ACTUAL_PARAM_EXP", "!"), tmpList39);
        parsingTable.put(Pair.make_pair("ACTUAL_PARAM_EXP", "id"), tmpList39);
        parsingTable.put(Pair.make_pair("ACTUAL_PARAM_EXP", "plus"), tmpList39);
        parsingTable.put(Pair.make_pair("ACTUAL_PARAM_EXP", "minus"), tmpList39); 

        parsingTable.put(Pair.make_pair("ACTUAL_PARAM_EXP", "rparen"), tmpList);

        ArrayList<String> tmpList40 = new ArrayList<>();
        tmpList40.add("EXP_LIST_S");
        tmpList40.add("EXP");
        parsingTable.put(Pair.make_pair("EXP_LIST", "int"), tmpList40);
        parsingTable.put(Pair.make_pair("EXP_LIST", "float"), tmpList40);
        parsingTable.put(Pair.make_pair("EXP_LIST", "num_int"), tmpList40);
        parsingTable.put(Pair.make_pair("EXP_LIST", "num_float"), tmpList40);
        parsingTable.put(Pair.make_pair("EXP_LIST", "!"), tmpList40);
        parsingTable.put(Pair.make_pair("EXP_LIST", "id"), tmpList40);
        parsingTable.put(Pair.make_pair("EXP_LIST", "plus"), tmpList40);
        parsingTable.put(Pair.make_pair("EXP_LIST", "minus"), tmpList40);

        ArrayList<String> tmpList41 = new ArrayList<>();
        tmpList41.add("EXP_LIST_S");
        tmpList41.add("EXP");
        tmpList41.add("comma");
        parsingTable.put(Pair.make_pair("EXP_LIST_S", "comma"), tmpList41);

        parsingTable.put(Pair.make_pair("EXP_LIST_S", "rparen"), tmpList);

        ArrayList<String> tmpList42 = new ArrayList<>();
        tmpList42.add("EXP_S");
        tmpList42.add("SIMPLE_EXP");
        parsingTable.put(Pair.make_pair("EXP", "int"), tmpList42);
        parsingTable.put(Pair.make_pair("EXP", "float"), tmpList42);
        parsingTable.put(Pair.make_pair("EXP", "num_int"), tmpList42);
        parsingTable.put(Pair.make_pair("EXP", "num_float"), tmpList42);
        parsingTable.put(Pair.make_pair("EXP", "!"), tmpList42);
        parsingTable.put(Pair.make_pair("EXP", "id"), tmpList42);
        parsingTable.put(Pair.make_pair("EXP", "plus"), tmpList42);
        parsingTable.put(Pair.make_pair("EXP", "minus"), tmpList42);

        ArrayList<String> tmpList43 = new ArrayList<>();
        tmpList43.add("EXP_S");
        tmpList43.add("SIMPLE_EXP");
        tmpList43.add("RELOP");
        parsingTable.put(Pair.make_pair("EXP_S", "relop"), tmpList43);
        parsingTable.put(Pair.make_pair("EXP_S", "in"), tmpList43);

        parsingTable.put(Pair.make_pair("EXP_S", "comma"), tmpList);
        parsingTable.put(Pair.make_pair("EXP_S", "rbracket"), tmpList);
        parsingTable.put(Pair.make_pair("EXP_S", "rparen"), tmpList);
        parsingTable.put(Pair.make_pair("EXP_S", "in"), tmpList);
        parsingTable.put(Pair.make_pair("EXP_S", "colon"), tmpList);
        parsingTable.put(Pair.make_pair("EXP_S", "nop"), tmpList);
        parsingTable.put(Pair.make_pair("EXP_S", "else"), tmpList);
        parsingTable.put(Pair.make_pair("EXP_S", "elif"), tmpList);
        parsingTable.put(Pair.make_pair("EXP_S", "semicolon"), tmpList);
        parsingTable.put(Pair.make_pair("EXP_S", "end"), tmpList);

        ArrayList<String> tmpList44 = new ArrayList<>();
        tmpList44.add("SIMPLE_EXP_S");
        tmpList44.add("TERM");
        parsingTable.put(Pair.make_pair("SIMPLE_EXP", "int"), tmpList44);
        parsingTable.put(Pair.make_pair("SIMPLE_EXP", "float"), tmpList44);
        parsingTable.put(Pair.make_pair("SIMPLE_EXP", "num_int"), tmpList44);
        parsingTable.put(Pair.make_pair("SIMPLE_EXP", "num_float"), tmpList44);
        parsingTable.put(Pair.make_pair("SIMPLE_EXP", "id"), tmpList44);
        parsingTable.put(Pair.make_pair("SIMPLE_EXP", "!"), tmpList44);
        parsingTable.put(Pair.make_pair("SIMPLE_EXP", "plus"), tmpList44);
        parsingTable.put(Pair.make_pair("SIMPLE_EXP", "minus"), tmpList44);

        ArrayList<String> tmpList45 = new ArrayList<>();
        tmpList45.add("SIMPLE_EXP_S");
        tmpList45.add("TERM");
        tmpList45.add("ADDOP");
        parsingTable.put(Pair.make_pair("SIMPLE_EXP_S", "plus"), tmpList45);
        parsingTable.put(Pair.make_pair("SIMPLE_EXP_S", "minus"), tmpList45);

        parsingTable.put(Pair.make_pair("SIMPLE_EXP_S", "relop"), tmpList);
        parsingTable.put(Pair.make_pair("SIMPLE_EXP_S", "in"), tmpList);
        parsingTable.put(Pair.make_pair("SIMPLE_EXP_S", "comma"), tmpList);
        parsingTable.put(Pair.make_pair("SIMPLE_EXP_S", "rbracket"), tmpList);
        parsingTable.put(Pair.make_pair("SIMPLE_EXP_S", "rparen"), tmpList);
        parsingTable.put(Pair.make_pair("SIMPLE_EXP_S", "colon"), tmpList);
        parsingTable.put(Pair.make_pair("SIMPLE_EXP_S", "nop"), tmpList);
        parsingTable.put(Pair.make_pair("SIMPLE_EXP_S", "else"), tmpList);
        parsingTable.put(Pair.make_pair("SIMPLE_EXP_S", "elif"), tmpList);
        parsingTable.put(Pair.make_pair("SIMPLE_EXP_S", "semicolon"), tmpList);
        parsingTable.put(Pair.make_pair("SIMPLE_EXP_S", "end"), tmpList);

        ArrayList<String> tmpList46 = new ArrayList<>();
        tmpList46.add("TERM_S");
        tmpList46.add("FACTOR");
        parsingTable.put(Pair.make_pair("TERM", "int"), tmpList46);
        parsingTable.put(Pair.make_pair("TERM", "float"), tmpList46);
        parsingTable.put(Pair.make_pair("TERM", "num_int"), tmpList46);
        parsingTable.put(Pair.make_pair("TERM", "num_float"), tmpList46);
        parsingTable.put(Pair.make_pair("TERM", "!"), tmpList46);
        parsingTable.put(Pair.make_pair("TERM", "id"), tmpList46);
        parsingTable.put(Pair.make_pair("TERM", "plus"), tmpList46);
        parsingTable.put(Pair.make_pair("TERM", "minus"), tmpList46);

        ArrayList<String> tmpList47 = new ArrayList<>();
        tmpList47.add("TERM_S");
        tmpList47.add("FACTOR");
        tmpList47.add("MULTOP");
        parsingTable.put(Pair.make_pair("TERM_S", "mult"), tmpList47);
        parsingTable.put(Pair.make_pair("TERM_S", "div"), tmpList47);

        parsingTable.put(Pair.make_pair("TERM_S", "plus"), tmpList);
        parsingTable.put(Pair.make_pair("TERM_S", "minus"), tmpList);
        parsingTable.put(Pair.make_pair("TERM_S", "relop"), tmpList);
        parsingTable.put(Pair.make_pair("TERM_S", "in"), tmpList);
        parsingTable.put(Pair.make_pair("TERM_S", "comma"), tmpList);
        parsingTable.put(Pair.make_pair("TERM_S", "rbracket"), tmpList);
        parsingTable.put(Pair.make_pair("TERM_S", "rparen"), tmpList);
        parsingTable.put(Pair.make_pair("TERM_S", "colon"), tmpList);
        parsingTable.put(Pair.make_pair("TERM_S", "nop"), tmpList);
        parsingTable.put(Pair.make_pair("TERM_S", "else"), tmpList);
        parsingTable.put(Pair.make_pair("TERM_S", "elif"), tmpList);
        parsingTable.put(Pair.make_pair("TERM_S", "semicolon"), tmpList);
        parsingTable.put(Pair.make_pair("TERM_S", "end"), tmpList);

        ArrayList<String> tmpList48 = new ArrayList<>();
        tmpList48.add("int");
        parsingTable.put(Pair.make_pair("FACTOR", "int"), tmpList48);

        ArrayList<String> tmpList148 = new ArrayList<>();
        tmpList148.add("num_int");
        parsingTable.put(Pair.make_pair("FACTOR", "num_int"), tmpList148);

        ArrayList<String> tmpList49 = new ArrayList<>();
        tmpList49.add("float");
        parsingTable.put(Pair.make_pair("FACTOR", "float"), tmpList49);

        ArrayList<String> tmpList149 = new ArrayList<>();
        tmpList149.add("num_float");
        parsingTable.put(Pair.make_pair("FACTOR", "num_float"), tmpList149);

        ArrayList<String> tmpList50 = new ArrayList<>();
        tmpList50.add("FACTOR_S");
        tmpList50.add("id");
        parsingTable.put(Pair.make_pair("FACTOR", "id"), tmpList50);

        ArrayList<String> tmpList51 = new ArrayList<>();
        tmpList51.add("FACTOR");
        tmpList51.add("!");
        parsingTable.put(Pair.make_pair("FACTOR", "!"), tmpList51);

        ArrayList<String> tmpList52 = new ArrayList<>();
        tmpList52.add("FACTOR");
        tmpList52.add("SIGN");
        parsingTable.put(Pair.make_pair("FACTOR", "plus"), tmpList52);
        parsingTable.put(Pair.make_pair("FACTOR", "minus"), tmpList52);

        ArrayList<String> tmpList53 = new ArrayList<>();
        tmpList53.add("FACTOR");
        tmpList53.add("VAR_S");
        parsingTable.put(Pair.make_pair("FACTOR_S", "lbracket"), tmpList53);
        parsingTable.put(Pair.make_pair("FACTOR_S", "int"), tmpList53);
        parsingTable.put(Pair.make_pair("FACTOR_S", "float"), tmpList53);
        parsingTable.put(Pair.make_pair("FACTOR_S", "num_int"), tmpList53);
        parsingTable.put(Pair.make_pair("FACTOR_S", "num_float"), tmpList53);
        parsingTable.put(Pair.make_pair("FACTOR_S", "id"), tmpList53);
        parsingTable.put(Pair.make_pair("FACTOR_S", "!"), tmpList53);
        parsingTable.put(Pair.make_pair("FACTOR_S", "plus"), tmpList53);
        parsingTable.put(Pair.make_pair("FACTOR_S", "minus"), tmpList53);

        ArrayList<String> tmpList54 = new ArrayList<>();
        tmpList54.add("PROC_STMT_S");
        parsingTable.put(Pair.make_pair("FACTOR_S", "lparen"), tmpList54);

        parsingTable.put(Pair.make_pair("FACTOR_S", "mult"), tmpList);
        parsingTable.put(Pair.make_pair("FACTOR_S", "div"), tmpList);
        parsingTable.put(Pair.make_pair("FACTOR_S", "plus"), tmpList);
        parsingTable.put(Pair.make_pair("FACTOR_S", "minus"), tmpList);
        parsingTable.put(Pair.make_pair("FACTOR_S", "relop"), tmpList);
        parsingTable.put(Pair.make_pair("FACTOR_S", "in"), tmpList);
        parsingTable.put(Pair.make_pair("FACTOR_S", "comma"), tmpList);
        parsingTable.put(Pair.make_pair("FACTOR_S", "rbracket"), tmpList);
        parsingTable.put(Pair.make_pair("FACTOR_S", "rparen"), tmpList);
        parsingTable.put(Pair.make_pair("FACTOR_S", "colon"), tmpList);
        parsingTable.put(Pair.make_pair("FACTOR_S", "else"), tmpList);
        parsingTable.put(Pair.make_pair("FACTOR_S", "elif"), tmpList);
        parsingTable.put(Pair.make_pair("FACTOR_S", "semicolon"), tmpList);
        parsingTable.put(Pair.make_pair("FACTOR_S", "end"), tmpList);

        ArrayList<String> tmpList55 = new ArrayList<>();
        tmpList55.add("relop");
        parsingTable.put(Pair.make_pair("RELOP", "relop"), tmpList55);

        ArrayList<String> tmpList56 = new ArrayList<>();
        tmpList56.add("in");
        parsingTable.put(Pair.make_pair("RELOP", "in"), tmpList56);

        ArrayList<String> tmpList57 = new ArrayList<>();
        tmpList57.add("plus");
        parsingTable.put(Pair.make_pair("ADDOP", "plus"), tmpList57);

        ArrayList<String> tmpList58 = new ArrayList<>();
        tmpList58.add("minus");
        parsingTable.put(Pair.make_pair("ADDOP", "minus"), tmpList58);

        ArrayList<String> tmpList59 = new ArrayList<>();
        tmpList59.add("mult");
        parsingTable.put(Pair.make_pair("MULTOP", "mult"), tmpList59);

        ArrayList<String> tmpList60 = new ArrayList<>();
        tmpList60.add("div");
        parsingTable.put(Pair.make_pair("MULTOP", "div"), tmpList60);

        ArrayList<String> tmpList61 = new ArrayList<>();
        tmpList61.add("plus");
        parsingTable.put(Pair.make_pair("SIGN", "plus"), tmpList61);

        ArrayList<String> tmpList62 = new ArrayList<>();
        tmpList62.add("minus");
        parsingTable.put(Pair.make_pair("SIGN", "minus"), tmpList62);

        return parsingTable;
    }
}