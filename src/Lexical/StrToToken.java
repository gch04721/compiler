package Lexical;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class StrToToken {
    public static void Tokenize(String str, ArrayList<String> tokens) {     // str to token
        int i = 0;
        while (i < str.length()) {
            String token = new String();

            while ((LexicalSpecifications.isLiteral(str.charAt(i)) || LexicalSpecifications.isNumber(str.charAt(i)))) {     // if not literal or  not number -> tokenize
                token = token + str.charAt(i);
                i++;
            }
            tokens.add(token);

            String notLiteralToken = new String();
            if (str.charAt(i) == '>' || str.charAt(i) == '<' || str.charAt(i) == '=' || str.charAt(i) == '!') {     // if comparision check 2 char
                notLiteralToken = notLiteralToken + str.charAt(i);
                if (str.charAt(i + 1) == '=') {
                    i++;
                    notLiteralToken = notLiteralToken + str.charAt(i);
                }
                tokens.add(notLiteralToken);
                i++;
            } else {        // else add tokens
                tokens.add(Character.toString(str.charAt(i)));
                i++;
            }
        }
        for (int j = 0; j < tokens.size(); j++) {       // if blank token, out
            if (tokens.get(j).equals("")) {
                tokens.remove(j);
                j--;
            }
        }
        StringToken(tokens);
        NegativeInteger(tokens);
    }

    private static void StringToken(ArrayList<String> tokens) {     // check if has " make string
        int flag = 0;
        String token = new String();
        for (int j = 0; j < tokens.size(); j++) {
            if (tokens.get(j).equals("\"") && flag == 0) {
                flag++;
                token = token + tokens.get(j);
                tokens.remove(j);
                j--;
            } else if (tokens.get(j).equals("\"") && flag == 1) {
                token = token + tokens.get(j);
                tokens.set(j, token);
                flag--;
                token = new String();
            } else if (flag == 1) {
                token = token + tokens.get(j);
                tokens.remove(j);
                j--;
            }
        }
        if (flag == 1) {
            tokens.add(token);
        }
    }

    private static void NegativeInteger(ArrayList<String> tokens) {     // check - , whether SingedInteger or Specification
        String token = new String();
        for (int j = 0; j < tokens.size(); j++) {
            if (tokens.get(j).equals("-") && !((LexicalSpecifications.checkSignedInteger(tokens.get(j - 1)) || LexicalSpecifications.checkID(tokens.get(j-1)))||LexicalSpecifications.checkWhitespaces(tokens.get(j-1))&&(LexicalSpecifications.checkSignedInteger(tokens.get(j - 2)) || LexicalSpecifications.checkID(tokens.get(j-2))))) {
                token = token + tokens.get(j);
                token = token + tokens.get(j + 1);
                tokens.set(j, token);
                tokens.remove(j + 1);
            }
        }
    }

    public static void PrintLexicalAnalysis(String fileName, ArrayList<String> tokens) {    // store outputs to <filename>.out
        try {
            fileName = fileName + ".out";
            File file = new File(fileName);
            FileWriter writer = new FileWriter(file);
            BufferedWriter bufWriter = new BufferedWriter(writer);

            for (int j = 0; j < tokens.size(); j++) {
                bufWriter.write("<" + LexicalSpecifications.Analyze(tokens.get(j)) + " , " + tokens.get(j) + ">");
                bufWriter.newLine();
            }

            bufWriter.close();
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("file open error");
            e.printStackTrace();
        }
    }

    public static ArrayList<String> AnalyzeOnArrayList(ArrayList<String> tokens){
        ArrayList<String> result = new ArrayList<String>();
        for(int i=0; i<tokens.size(); i++){
            String token = LexicalSpecifications.Analyze(tokens.get(i));
            result.add(token);
        }
        return result;
    }
}