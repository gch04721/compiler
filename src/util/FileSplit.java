package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 입력된 파일을 line by line으로 분할 ArrayList로 반환 FileSplit
 */
public class FileSplit {
    private ArrayList<String> result;

    public FileSplit(String input){
        this.result = new ArrayList<>();

        String line;
        FileReader reader = null;
        
        try{
            reader = new FileReader(input);
            BufferedReader bufReader = new BufferedReader(reader);

            while((line = bufReader.readLine()) != null){
                //System.out.println(line);
                this.result.add(line);
            }

            bufReader.close();
            reader.close();
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public ArrayList<String> getResult(){
        return this.result;
    }
}