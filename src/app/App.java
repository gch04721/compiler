package app;

import java.util.ArrayList;
import java.util.Scanner;

import util.Pair;
import Lexical.LexicalAnalyzer;

public class App {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Input C file with path!  ex) C:\\Users\\gch04\\Desktop\\test");
        String fileName = in.next();
        in.close();

        //System.out.println(fileName);
        LexicalAnalyzer lex = new LexicalAnalyzer(fileName);
        
        ArrayList<Pair<String, String>> lexList;
        lexList = lex.getResult();
    }
}