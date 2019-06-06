package app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import Lexical.*;

public class App {
    public static void main(String[] args) {
        String fileName = args[0];
        String line;
        FileReader reader = null;
        ArrayList<String> tokens = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        System.out.println("Input C file with path!  ex) C:\\Users\\JuHyeon\\Desktop\\test");
        fileName = in.next();

        try {       //try catch for file open
            String file ;
            file = fileName + ".c";
            reader = new FileReader(file);
            BufferedReader bufReader = new BufferedReader(reader);
            while ((line = bufReader.readLine()) != null) {         // make token to use customized split method line by line
                StrToToken.Tokenize(line, tokens);
            }
            bufReader.close();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        StrToToken.PrintLexicalAnalysis(fileName, tokens);  // make filename.out & analyze tokens to use dfa
    }
}