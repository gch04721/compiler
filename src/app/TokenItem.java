package app;

public static class TokenItem {
    public String token;
    public Integer line;

    public TokenItem(String token, Integer line){
        this.token = token;
        this.line = line;
    }

    public String getToken(){
        return token;
    }

    public Integer getLine(){
        return line;
    }
}