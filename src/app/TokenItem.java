package app;

public class TokenItem {
    private String token;
    private String string;
    private Integer line;
    public boolean isError = false;
    public String errmsg;

    public TokenItem(String token, String string, Integer line){
        this.token = token;
        this.string = string;
        this.line = line;
    }

    public String getToken(){
        return token;
    }

    public void setToken(String new_token){
        this.token = new_token;
    }

    public String getString(){
        return this.string;
    }
    
    public void setString(String new_string){
        this.string = new_string;
    }

    public Integer getLine(){
        return line;
    }
}