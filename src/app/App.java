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
        
        ArrayList<Pair<String, String>> lexResult;
        boolean checkError = lex.analyze();
        lexResult = lex.getResult();
        if(checkError){
            // Error Msg 출력
            for(int i=0; i<lexResult.size(); i++){
                System.out.println(i + "번째 줄 " + lexResult.get(i) + "에서 오타");
            }
        }
        else{
            // Syntax Analyzer 시작
        }
    }
}