package Lexical;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import app.TokenItem;

public class StrToToken {

    private static boolean isNumber(char c){     //define state number  a.k.a) digits
        if(c>=48 && c<=57){     // ascii code for 0~9
            return true;
        }
        return false;
    }

    private static boolean isLiteral(char c){    //define state Literal a.k.a ) alphabet
        if((c>=65 && c<=90) || (c>=97 && c<=122)){  // a~z && A~Z
            return true;
        }
        return false;
    }

    private static boolean checkID(String scan){     // dfa for ID
        if(!isLiteral(scan.charAt(0))){ // must start with literal
            return false;
        }
        int i = 1;
        while (i<scan.length()){
            if(isNumber(scan.charAt(i)) || isLiteral(scan.charAt(i))){ // keep final state if use digit or Listeral
                i++;
            }
            else {
                return false;           // else false
            }
        }
        return true;        // if finish with final state, return true
    }

    private static boolean checkSignedInteger(String scan){  //dfa for Signedinteger
        if(scan.charAt(0)=='0' && scan.length()!=1){     // startwith 0 and not end  is falase
            return false;
        }
        if(scan.charAt(0)=='0' && scan.length()==1){    // startwith 0 and end is true
            return true;
        }
        if(scan.charAt(0)=='-'){        // if start with -
            if(scan.charAt(1)=='0'){    // next state must not 0
                return false;
            }
            for(int i = 1; i < scan.length(); i++){
                if(!isNumber(scan.charAt(i))){  // if digit, keep final state, and not digit is false
                    return false;
                }
            }
            return true;                // keep final state, return true
        }
        else{
            for(int i = 0; i < scan.length(); i++){     // if not start with -
                if(!isNumber(scan.charAt(i))){  // if digit, keep final state, and note digit is false
                    return false;
                }
            }
            return true;            // keep final state, and then return ture
        }
    }

    private static boolean checkWhitespaces(String scan){       // dfa for white space
        if(scan.charAt(0)=='\t' || scan.charAt(0)=='\n' || scan.charAt(0)==' ' ){   // if \t or \n or ' ' are define whitespace
            return true;
        }
        return false;  // else false
    }

    public static void Tokenize(String str, ArrayList<TokenItem> tokens, int line) {     // str to token
        int i = 0;
        int length = str.length();
    
        while (i < str.length()) {
            String token = new String();
            while (i < str.length() && (isLiteral(str.charAt(i)) || isNumber(str.charAt(i)))) {     // if not literal or  not number -> tokenize
                token = token + str.charAt(i);
                i++;
            }
            if(token.length() > 0)
                tokens.add(new TokenItem(token, line));

            String notLiteralToken = new String();
            if (i < str.length()){
                if(str.charAt(i) == '>' || str.charAt(i) == '<' || str.charAt(i) == '=' || str.charAt(i) == '!') {     // if comparision check 2 char
                    notLiteralToken = notLiteralToken + str.charAt(i);
                    if (str.charAt(i + 1) == '=') {
                        i++;
                        notLiteralToken = notLiteralToken + str.charAt(i);
                    }
                    tokens.add(new TokenItem(notLiteralToken, line));
                    i++;
                } else {        // else add tokens
                    tokens.add(new TokenItem(Character.toString(str.charAt(i)), line));
                    i++;
                }
            }
        }

        for (int j = 0; j < tokens.size(); j++) {       // if blank token, out
            if (tokens.get(j).getToken().equals(" ")) {
                tokens.remove(j);
                j--;
            }
        }
        NegativeInteger(tokens);
        FloatToken(tokens);
    }

    private static void FloatToken(ArrayList<TokenItem> tokens) {      //check float type
        int flag = 0;
        int lineNum = 0;
        String token = new String();
        for(int j = 0; j < tokens.size(); j++){
            if(flag == 0 && checkSignedInteger(tokens.get(j).getToken())) {
                flag++;
                token = token + tokens.get(j).getToken();
                lineNum = tokens.get(j).getLine();
                tokens.remove(j);

                if(j != 0) {
                    j--;
                }
            } else if (flag == 1 && tokens.get(j).getToken().equals(".")) {
                flag++;
                token = token + tokens.get(j).getToken();
                tokens.remove(j);
                
                if(j != 0) {
                    j--;
                }
            } else if(flag == 2 && checkSignedInteger(tokens.get(j).getToken())) {
                token = token + tokens.get(j).getToken();
                tokens.set(j, new TokenItem(token, tokens.get(j).getLine()));
                flag = flag - 2;
                token = new String();
            } else if(flag == 1) {      // just number
                tokens.add(j, new TokenItem(token, tokens.get(j).getLine()));
                flag--;
            } else if(flag == 2) {      // number.
                tokens.add(j, new TokenItem(token, tokens.get(j).getLine()));
                flag = flag - 2;
            }
        }
        if(flag == 1 || flag == 2) {
            tokens.add(new TokenItem(token, lineNum));
        }
    }

    private static void NegativeInteger(ArrayList<TokenItem> tokens) {     // check - , whether SingedInteger or Specification
        String token = new String();
        for (int j = 0; j < tokens.size(); j++) {
            if (tokens.get(j).getToken().equals("-") && !((checkSignedInteger(tokens.get(j - 1).getToken()) || checkID(tokens.get(j - 1).getToken())) || checkWhitespaces(tokens.get(j - 1).getToken()) && (checkSignedInteger(tokens.get(j - 2).getToken()) || checkID(tokens.get(j - 2).getToken())))) {
                token = token + tokens.get(j).getToken();
                token = token + tokens.get(j + 1).getToken();
                tokens.set(j, new TokenItem(token, tokens.get(j).getLine()));
                tokens.remove(j + 1);
            }
        }
    }

    public static void PrintLexicalAnalysis(String fileName, ArrayList<TokenItem> tokens) {    // store outputs to <filename>.out
        try {
            fileName = fileName + ".out";
            File file = new File(fileName);
            FileWriter writer = new FileWriter(file);
            BufferedWriter bufWriter = new BufferedWriter(writer);

            for (int j = 0; j < tokens.size(); j++) {
                bufWriter.write("<" + Lexer.Analyze(tokens.get(j).getToken()) + " , " + tokens.get(j).getToken() + ">");
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
            String token = Lexer.Analyze(tokens.get(i));
            result.add(token);
        }
        return result;
    }
}