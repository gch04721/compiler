package Lexical;

import util.Pair;
import util.FileSplit;

import java.util.ArrayList;


/**
 * LexicalAnalyzer
 * Input을 TOKEN으로 구분
 * 결과물 :
 * input string : TOKEN name 형식으로 .lex 파일 출력
 * Line by Line으로 파일을 읽어들이고, 
 * Lexical Error가 존재할 경우 Line number를 Error msg와 함께 출력
 * 
 * Main에서 사용되는 class
 */
public class LexicalAnalyzer {
    /** 
     * Syntax Analyzer, Main에서 사용할 변수
     * word : TOKEN형태로 저장.
     * 파일의 모든 word가 저장되어야 한다.
     */
    private ArrayList<Pair<String, String>> resultList;
    /**
     * error가 난 word를 line number와 함께 저장
     */
    private ArrayList<Pair<Integer, String>> errorList; 
    /**
     * 파일을 읽은 다음 각 줄을 저장한 ArrayList
     */
    private ArrayList<String> splittedStringList; 

    /**
     * @param input : 파싱할 파일의 이름 설정
     */
    public LexicalAnalyzer(String input){
        this.resultList = new ArrayList<>();
        this.errorList = new ArrayList<>();
        this.splittedStringList = new ArrayList<>();
        
        FileSplit fSplitter = new FileSplit(input);
        this.splittedStringList = fSplitter.getResult();

        // 각 라인별 파싱 시작
        for(int i=0; i<this.splittedStringList.size(); i++){
            // string을 word별로 split
            StrSplit sSplitter = new StrSplit(this.splittedStringList.get(i));
            ArrayList<String> wordList = sSplitter.getResult();

            // DFA(Regex)를 통해 word에 맞는 TOKEN을 탐색
            for(int j = 0; j<wordList.size(); j++){
                Word2Token tokenizer = new Word2Token(wordList.get(j));
                if(tokenizer.error == false){
                    // 올바른 lex
                    String token = tokenizer.getResult();
                    this.resultList.add(new Pair<String, String>(wordList.get(i), token));
                } 
                else if(tokenizer.error == true){
                    // 잘못된 lex
                    this.errorList.add(new Pair<Integer, String>(i, wordList.get(i)));
                }
            }
        }
    }

    // @TODO
    public boolean analyze(){
        boolean status = false; // error 여부 검사, true: 에러 발생

        return status;
    }

    public ArrayList<Pair<String, String>> getResult(){
        return this.resultList;
    }
}