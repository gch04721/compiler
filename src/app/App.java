package app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import Lexical.*;
import Syntax.*;

public class App {
    public static void main(String[] args) {
        String fileName = "/Test/test.c";
        String line;
        FileReader reader = null;
        ArrayList<TokenItem> tokens = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        final String tokenErrorMsg1 = "ERROR (TOKEN NOT ACCEPT)";
        final String tokenErrorMsg2 = "ERROR (COMMENT)";

        System.out.println("Input C file with path!  ex) C:\\Users\\JuHyeon\\Desktop\\test");
        fileName = in.next();

        // about lexical analyzer
        try {       //try catch for file open
            String file ;
            file = fileName + ".txt";
            reader = new FileReader(file);
            BufferedReader bufReader = new BufferedReader(reader);
            int count = 1;
            while ((line = bufReader.readLine()) != null) {         // make token to use customized split method line by line
                StrToToken.Tokenize(line, tokens, count);
                count++;
            }
            bufReader.close();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // for make .out file, uncomment line #43
        StrToToken.PrintLexicalAnalysis(fileName, tokens);  // make filename.out & analyze tokens to use dfa

        // // Syntax analyzer start
        // ArrayList<Token> resultTokens;
        // resultTokens = StrToToken.AnalyzeOnArrayList(tokens);
        
        // // start check tokens have any error message]
        // // if token has error messages, syntax analyzer do not need to executed
        // if(!resultTokens.contains(tokenErrorMsg1) && !resultTokens.contains(tokenErrorMsg2)){
        //     // if no, start syntac analyze
        //     ArrayList<String> terminals;

        //     // change given token sequence to terminal sequence,
        //     ReadTokenTable tokenReader = new ReadTokenTable(tokens, resultTokens);
        //     terminals = tokenReader.getTerminalSequence();
        //     System.out.println(terminals);

        //     // start syntax analyze
        //     SyntaxAnalyze syntaxAnalyzer =  new SyntaxAnalyze(terminals);
        //     System.out.println(syntaxAnalyzer.analyze());
        // }
        // else{
        //     System.out.println("lexical errors, check .out file");
        // }
    }
}