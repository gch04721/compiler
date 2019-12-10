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
    public static boolean isExist(ArrayList<ID> tmpList, String id){
        for(int i=0; i<tmpList.size(); i++){
            if(tmpList.get(i).id.equals(id)){
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        String fileName = "/Test/test.c";
        String line;
        FileReader reader = null;
        ArrayList<TokenItem> tokens = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        final String tokenErrorMsg1 = "ERROR (TOKEN NOT ACCEPT)";
        //final String tokenErrorMsg2 = "ERROR (COMMENT)";

        System.out.println("Input C file with path!  ex) C:\\Users\\JeongWoo\\Desktop\\test");
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
        ArrayList<TokenItem> resultTokens;
        resultTokens = StrToToken.AnalyzeOnArrayList(tokens);
        
        // error check
        ArrayList<ID> id_all = new ArrayList<>();
        int scope_level = 0;
        boolean isError = false;
        for(int i=0; i< resultTokens.size(); i++){
            //System.out.println(resultTokens.get(i).getLine() + ": " + resultTokens.get(i).getToken() +  ", string : " + resultTokens.get(i).getString());
            if(resultTokens.get(i).getToken().equals("MAINPROG")){
                String attrib = resultTokens.get(i).getToken();
                i = i+1;
                if(i >= resultTokens.size()){break;}
                String id = resultTokens.get(i).getString();

                if(isExist(id_all, id)){
                    // wrong id input
                    isError = true;
                    resultTokens.get(i).isError = true;
                    resultTokens.get(i).errmsg = "id: "+ resultTokens.get(i).getString() + "is already exist";
                    break;
                }

                ID _id = new ID(id, attrib);
                _id.scopeLevel = -1;
                id_all.add(_id);
            }
            if( resultTokens.get(i).getToken().equals("FUNCTION")){
                String attrib = resultTokens.get(i).getToken();
                i = i+1;
                if(i >= resultTokens.size()){break;}
                String id = resultTokens.get(i).getString();

                if(isExist(id_all, id)){
                    // wrong id input
                    isError = true;
                    resultTokens.get(i).isError = true;
                    resultTokens.get(i).errmsg = "id: "+ resultTokens.get(i).getString() + "is already exist";
                    break;
                }

                ID _id = new ID(id, attrib);
                _id.scopeLevel = -1;
                id_all.add(_id);

                i = i + 1;
                if(i >= resultTokens.size()){break;}

                if(resultTokens.get(i).getToken().equals("LPAREN")){
                    int _count = 0;
                    ArrayList<String> paramID = new ArrayList<>();
                    while(!resultTokens.get(i).getToken().equals("RPAREN")){
                        i = i + 1;
                        if(i >= resultTokens.size()){break;}
                        if(resultTokens.get(i).getToken().equals("ID")){
                            _count++;
                            paramID.add(resultTokens.get(i).getString());
                        }
                        if(resultTokens.get(i).getToken().equals("INT") || resultTokens.get(i).getToken().equals("FLOAT")){
                            for(int j=0; j<_count; j++){
                                _id.paramList.add(resultTokens.get(i).getToken());

                                if(isExist(id_all, paramID.get(j))){
                                    // wrong id input
                                    isError = true;
                                    resultTokens.get(i).isError = true;
                                    resultTokens.get(i).errmsg = "id: "+ resultTokens.get(i).getString() + "is already exist";
                                    break;
                                }

                                ID __id = new ID(paramID.get(j), resultTokens.get(i).getToken());

                                __id.scopeLevel = scope_level+ 1;
                                __id.parent = _id;
                                id_all.add(__id);
                            }
                            paramID.clear();
                            _id.paramNum = _id.paramNum + _count;
                            _count = 0;
                        }
                    }
                    i = i+1;
                }
                if(i >= resultTokens.size()){break;}
                if(resultTokens.get(i).getToken().equals("COLON")){
                    i = i+1;
                    if(i >= resultTokens.size()){break;}
                    _id.returnValue = resultTokens.get(i).getToken();
                    continue;
                }

            }
            if( resultTokens.get(i).getToken().equals("PROCEDURE")){
                String attrib = resultTokens.get(i).getToken();
                i = i+1;
                if(i >= resultTokens.size()){break;}
                String id = resultTokens.get(i).getString();

                if(isExist(id_all, id)){
                    // wrong id input
                    isError = true;
                    resultTokens.get(i).isError = true;
                    resultTokens.get(i).errmsg = "id: "+ resultTokens.get(i).getString() + "is already exist";
                    break;
                }

                ID _id = new ID(id, attrib);
                _id.scopeLevel = -1;
                id_all.add(_id);
                i = i +1;
                if(i >= resultTokens.size()){break;}
                    if(resultTokens.get(i).getToken().equals("LPAREN")){
                        int _count =0;
                        ArrayList<String> paramID = new ArrayList<>();
                        while(!resultTokens.get(i).getToken().equals("RPAREN")){
                            i = i +1;
                            if(i >= resultTokens.size()){break;}
                            if(resultTokens.get(i).getToken().equals("ID")){
                                _count++;
                                paramID.add(resultTokens.get(i).getString());
                            }
                            if(resultTokens.get(i).getToken().equals("INT") || resultTokens.get(i).getToken().equals("FLOAT")){
                                for(int j=0; j<_count; j++){
                                    _id.paramList.add(resultTokens.get(i).getToken());

                                    if(isExist(id_all, paramID.get(j))){
                                        // wrong id input
                                        isError = true;
                                        resultTokens.get(i).isError = true;
                                        resultTokens.get(i).errmsg = "id: "+ resultTokens.get(i).getString() + "is already exist";
                                        break;

                                    }

                                    ID __id = new ID(paramID.get(j), resultTokens.get(i).getToken());
                                    __id.scopeLevel = scope_level+ 1;
                                    __id.parent = _id;
                                    id_all.add(__id);
                                }
                                paramID.clear();
                                _id.paramNum = _id.paramNum +  _count;
                                _count = 0;
                            }
                        }
                    }
                    while(!resultTokens.get(i).getToken().equals("INT") && !resultTokens.get(i).getToken().equals("FLOAT")){
                        i = i+1;
                        if(i >= resultTokens.size()){break;}
                    }
                    attrib = resultTokens.get(i).getToken();
                    i = i+1;
                    if(resultTokens.get(i).getToken().equals("LBRACKET")){
                        while(!resultTokens.get(i).getToken().equals("ID")){
                            i = i +1;
                            if(i >= resultTokens.size()){break;}
                        }
                    }
                    if(resultTokens.get(i).getToken().equals("ID")){
                        if(isExist(id_all, resultTokens.get(i).getString())){
                            // wrong id input
                            isError = true;
                            resultTokens.get(i).isError = true;
                            resultTokens.get(i).errmsg = "id: "+ resultTokens.get(i).getString() + "is already exist";
                            break;
                        }
                        ID __id = new ID(resultTokens.get(i).getString(), attrib);
                        __id.scopeLevel = scope_level + 1;
                        id_all.add(__id);
                    }
                    continue;    
            }
            if(resultTokens.get(i).getToken().equals("INT") || resultTokens.get(i).getToken().equals("FLOAT")){
                // 변수 생성
                String attrib = resultTokens.get(i).getToken();
                i = i+1;
                if(resultTokens.get(i).getToken().equals("LBRACKET")){
                    while(!resultTokens.get(i).getToken().equals("ID")){
                        i = i +1;
                        if(i >= resultTokens.size()){break;}
                    }
                }
                if(resultTokens.get(i).getToken().equals("ID")){
                    if(isExist(id_all, resultTokens.get(i).getString())){
                        // wrong id input
                        isError = true;
                        resultTokens.get(i).isError = true;
                        resultTokens.get(i).errmsg = "id: "+ resultTokens.get(i).getString() + "is already exist";
                        break;
                    }
                    ID _id = new ID(resultTokens.get(i).getString(), attrib);
                    _id.scopeLevel = scope_level;
                    id_all.add(_id);
                }
                i =  i+1;
                if(i >= resultTokens.size()){break;}
                while(resultTokens.get(i).getToken().equals("COMMA")){
                    i =  i+1;
                    if(i >= resultTokens.size()){break;}
                    if(resultTokens.get(i).getToken().equals("ID")){
                        if(isExist(id_all, resultTokens.get(i).getString())){
                            // wrong id input
                            isError = true;
                            resultTokens.get(i).isError = true;
                            resultTokens.get(i).errmsg = "id: "+ resultTokens.get(i).getString() + "is already exist";
                            break;
                        }
                        ID _id = new ID(resultTokens.get(i).getString(), attrib);
                        _id.scopeLevel = scope_level;
                        id_all.add(_id);
                    }
                }
                
                continue;
            }

            if(resultTokens.get(i).getToken().equals("BEGIN")){
                //scope in
                scope_level++;
                continue;
            }

            if(resultTokens.get(i).getToken().equals("END")){
                // scope out
                for(int j=0; j<id_all.size(); j++){
                    if(id_all.get(j).scopeLevel == scope_level){
                        id_all.remove(j);
                        j--;
                    }
                }
                scope_level--;
                continue;
            }

            if(resultTokens.get(i).getToken().equals("FOR")){

            }

            //
            if(resultTokens.get(i).getToken().equals("ID")){
                /**
                 * 1. scope에 존재하는지 -> scope error, spell error, undefined function
                 */
                boolean isChecked = false;
                for(int j=0; j<id_all.size(); j++){
                    if(resultTokens.get(i).getToken().equals("END")){i = i -1; break;}
                    if(id_all.get(j).id.equals(resultTokens.get(i).getString())){
                        isChecked = true;
                        i = i + 1;
                        if(i >= resultTokens.size()){break;}
                        
                        String tmpToken = resultTokens.get(i).getToken();

                        if(tmpToken.equals("LBRACKET")){
                            i = i +1 ;
                            if(resultTokens.get(i).getToken().equals("INTEGER")){}
                            else{
                                // type error
                                isError = true;
                                resultTokens.get(i).isError = true;
                                resultTokens.get(i).errmsg = "Array index number must be integer";
                            }
                            i = i+2;
                        }
                        tmpToken = resultTokens.get(i).getToken();

                        if(tmpToken.equals("LPAREN")){
                            // parameter 검사
                            ArrayList<String> tmpIdList = new ArrayList<>();
                            while(!resultTokens.get(i).getToken().equals("RPAREN")){
                                i = i +1;
                                if(i >= resultTokens.size()){break;}
                                if(resultTokens.get(i).getToken().equals("ID") || resultTokens.get(i).getToken().equals("INTEGER")
                                || resultTokens.get(i).getToken().equals("FLOAT_NUMBER")){
                                    if(resultTokens.get(i).getToken().equals("INTEGER")) {tmpIdList.add("INT");}
                                    else if(resultTokens.get(i).getToken().equals("FLOAT_NUMBER")) {tmpIdList.add("FLOAT");}
                                    else {
                                        for(int k=0; k<id_all.size();k++){
                                            if(id_all.get(k).id.equals(resultTokens.get(i).getString())){
                                                tmpIdList.add(id_all.get(k).attrib);
                                            }
                                        }
                                        //tmpIdList.add(resultTokens.get(i).getString());
                                    }
                                }
                            }
                            if(tmpIdList.size() != id_all.get(j).paramNum){
                                // param number error
                                isError = true;
                                resultTokens.get(i).isError = true;
                                resultTokens.get(i).errmsg = "number of parameter is wrong";
                                
                            }
                            else{
                                for(int k=0;k<tmpIdList.size(); k++){
                                    if(tmpIdList.get(k).equals(id_all.get(j).paramList.get(k))){
                                        continue;
                                    }
                                    else{
                                        // param type error
                                        isError = true;
                                        resultTokens.get(i).isError = true;
                                        resultTokens.get(i).errmsg = "type error";

                                    }
                                }
                            }
                            
                        }
                        else if(tmpToken.equals("ASSIGN") || tmpToken.equals("LESS THEN") || tmpToken.equals("LESS EQUAL") || tmpToken.equals("GREATER THEN")
                        || tmpToken.equals("GREATER EQUAL") || tmpToken.equals("EQUAL") || tmpToken.equals("NOT EQUAL") || tmpToken.equals("PLUS") 
                        || tmpToken.equals("MINUS") || tmpToken.equals("DIVIDE") || tmpToken.equals("TIMES")){
                            // type checking
                            // ID, INTEGER, FLOAT_NUMBER, endwith SEMICOLON
                            while(!(resultTokens.get(i).getToken().equals("SEMICOLON") || resultTokens.get(i).getToken().equals("END"))){
                                i = i +1;
                                
                                if(i >= resultTokens.size()){break;}
                                if(resultTokens.get(i).getToken().equals("END") || resultTokens.get(i).getToken().equals("ELIF") || resultTokens.get(i).getToken().equals("ELSE")){
                                    if(j == id_all.size()-1)
                                        j = j-1;
                                    break;
                                }
                                tmpToken = resultTokens.get(i).getToken();
                                
                                if(tmpToken.equals("INTEGER")){
                                    if(id_all.get(j).attrib.equals("INT")){
                                        continue;
                                    }
                                    else{
                                        // type error
                                        isError = true;
                                        resultTokens.get(i).isError = true;
                                        resultTokens.get(i).errmsg = "type error";
                                                
                                    }
                                }
                                else if(tmpToken.equals("FLOAT_NUMBER")){
                                    if(id_all.get(j).attrib.equals("FLOAT")){
                                        continue;
                                    }
                                    else{
                                        // type error
                                        isError = true;
                                        resultTokens.get(i).isError = true;
                                        resultTokens.get(i).errmsg = "type error";
                                    }
                                }
                                else if(tmpToken.equals("ID")){
                                    for(int k =0; k<id_all.size(); k++){
                                        if(id_all.get(k).id.equals(resultTokens.get(i).getString())){
                                            if(id_all.get(k).attrib.equals("FUNCTION")){
                                                if(id_all.get(k).returnValue.equals(id_all.get(j).attrib)){
                                                    while(!resultTokens.get(i).getToken().equals("RPAREN")){
                                                        i = i +1;
                                                    }
                                                    continue;
                                                }
                                                else{
                                                    // type error
                                                    isError = true;
                                                    resultTokens.get(i).isError = true;
                                                    resultTokens.get(i).errmsg = "type error";
                                                }
                                                
                                            }   
                                            else{
                                                if(id_all.get(k).attrib.equals(id_all.get(j).attrib)){
                                                    continue;
                                                }
                                                
                                                else{
                                                    // type error
                                                    isError = true;
                                                    resultTokens.get(i).isError = true;
                                                    resultTokens.get(i).errmsg = "type error";
                                                }
                                            }                             
                                            
                                        }
                                    }
                                }
                            }

                        }
                        
                    }
                }
                if(!isChecked){
                    // spell or undefined or scope error
                    isError = true;
                    resultTokens.get(i).isError = true;
                    resultTokens.get(i).errmsg = "cannot find object";
                }
                else{
                    continue;
                }

            }

            if(resultTokens.get(i).getToken().equals("RETURN")){
                i = i + 1;
                if(i >= resultTokens.size()){break;}
                // find function

                ID function = new ID("no", "no");
                for(int j=id_all.size() -1; j >= 0; j--){
                    if(id_all.get(j).attrib.equals("FUNCTION")){
                        function = id_all.get(j);
                    }
                }
                if(function.attrib.equals("no")){
                    isError = true;
                    resultTokens.get(i).isError= true;
                    resultTokens.get(i).errmsg = "Cannot find object";
                }
                else{
                    for(int j=0; j<id_all.size(); j++){
                        if(id_all.get(j).id.equals(resultTokens.get(i).getString())){
                            ID tmpId = id_all.get(j);
                            if(function.returnValue.equals(tmpId.attrib)){
                                break;
                            }
                            else{
                                // error
                                isError = true;
                                resultTokens.get(i).isError = true;
                                resultTokens.get(i).errmsg = "type error";
                            }
                        }
                    }
                }
            }
        }

        // start check tokens have any error message
        // if token has error messages, syntax analyzer do not need to executed
        if(!resultTokens.contains(tokenErrorMsg1) && !isError){
            // if no, start syntac analyze
            ArrayList<TokenItem> terminals;

            // change given token sequence to terminal sequence,
            ReadTokenTable tokenReader = new ReadTokenTable(tokens, resultTokens);
            terminals = tokenReader.getTerminalSequence();

            // start syntax analyze
            SyntaxAnalyze syntaxAnalyzer =  new SyntaxAnalyze(terminals);
            System.out.println(syntaxAnalyzer.analyze());
            
        }
        else{
            System.out.println("error occurred");

            for(int i=0; i<resultTokens.size(); i++){
                if(resultTokens.get(i).isError){
                    System.out.println("Line #: " + resultTokens.get(i).getLine() + " Token: " + resultTokens.get(i).getString() + " Error msg : " + resultTokens.get(i).errmsg);
                }
            }
        }
    }
}