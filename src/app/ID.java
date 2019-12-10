package app;

import java.util.ArrayList;

/**
 * ID
 */
public class ID {
    public String id;
    public String attrib;
    public String returnValue;
    public ArrayList<String> paramList = new ArrayList<>();
    public int scopeLevel = 0;
    public int paramNum = 0;
    public ID parent = null;
    public ID(String id, String attrib){
        this.id = id;
        this.attrib = attrib;
    }
    
}