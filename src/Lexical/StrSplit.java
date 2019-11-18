package Lexical;

import java.util.ArrayList;

/**
 * 파일의 한 line을 word별로 구분 String형 input이 존재할 경우 해당 input은 묶어서 처리해야함 결과물 :
 * ArrayList<String>, 각 element는 한 단어 또는 한 문장
 */
public class StrSplit {

    private ArrayList<String> splittedLine;
    public StrSplit(String input){
        this.splittedLine = new ArrayList<>();

        // 입력받은 스트링을 word 또는 " "별로 구분하여 splittedLine에 저장
    }

    public ArrayList<String> getResult(){
        return this.splittedLine;
    }
}