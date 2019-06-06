package Lexical;

public class LexicalSpecifications {
    String scan;

    public static boolean isNumber(char c){     //define state number  a.k.a) digits
        if(c>=48 && c<=57){     // ascii code for 0~9
            return true;
        }
        return false;
    }

    public static boolean isLiteral(char c){    //define state Literal a.k.a ) alphabet
        if((c>=65 && c<=90) || (c>=97 && c<=122)){  // a~z && A~Z
            return true;
        }
        return false;
    }

    public static boolean checkINT(String scan) {   //dfa for INT
        if(scan.length()!=3){
            return false;
        }

        if(scan.charAt(0)=='I' && scan.charAt(1)=='N' && scan.charAt(2)=='T'){      // I -> N -> T -> finish is true
            return true;
        }

        if(scan.charAt(0)=='i' && scan.charAt(1)=='n' && scan.charAt(2)=='t'){      // i -> n -> t -> finish is true
            return true;
        }
        return false;           //else false
    }

    public static boolean checkCHAR(String scan){   //dfa for CHAR
        if(scan.length()!=4){
            return false;
        }

        if(scan.charAt(0)=='C' && scan.charAt(1)=='H' && scan.charAt(2)=='A' && scan.charAt(3)=='R'){       // C -> H -> A -> R -> finish is true
            return true;
        }

        if(scan.charAt(0)=='c' && scan.charAt(1)=='h' && scan.charAt(2)=='a' && scan.charAt(3)=='r'){       // c -> h -> a -> r -> finish is true
            return true;
        }
        return false;          //else false
    }

    public static boolean checkSignedInteger(String scan){  //dfa for Signedinteger
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

    public static boolean checkLiteralString(String scan){  // dfa for LiteralString
        if(scan.charAt(0)!='"'){  // must start with "
            return false;
        }
        int i = 1;
        while (i<scan.length()){
            if(scan.charAt(i)=='"'){   // end with " (final state)
                return true;
            }
            else if(isNumber(scan.charAt(i)) || isLiteral(scan.charAt(i)) || scan.charAt(i)==' '){ // between " and " -> must digit or literal or whitespcae
                i++;
            }
            else {      // if will not return false
                return false;
            }
        }
        return false;
    }

    public static boolean checkID(String scan){     // dfa for ID
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

    public static boolean checkIf(String scan){     //dfa for if
        if(scan.length()!=2){
            return false;
        }
        if(scan.charAt(0)=='i' && scan.charAt(1)=='f'){     // i - > f -> finish is true
            return true;
        }
        if(scan.charAt(0)=='I' && scan.charAt(1)=='F'){     // I -> F -> finish is true
            return true;
        }
        return false;               //else false
    }

    public static boolean checkElse(String scan){       // dfa for else
        if(scan.length()!=4){
            return false;
        }
        if(scan.charAt(0)=='e' && scan.charAt(1)=='l' && scan.charAt(2)=='s' && scan.charAt(3)=='e'){       // e -> l - > s -> e - > finish is true
            return true;
        }
        if(scan.charAt(0)=='E' && scan.charAt(1)=='L' && scan.charAt(2)=='S' && scan.charAt(3)=='E') {      // E -> L -> S -> E -> finish is true
            return true;
        }
        return false;       // else flase
    }

    public static boolean checkWhile(String scan){      // dfa for while
        if(scan.length()!=5){
            return false;
        }
        if(scan.charAt(0)=='w' && scan.charAt(1)=='h' && scan.charAt(2)=='i' && scan.charAt(3)=='l' && scan.charAt(4)=='e'){    // w->h->i->l->e -> finish is true
            return true;
        }
        if(scan.charAt(0)=='W' && scan.charAt(1)=='H' && scan.charAt(2)=='I' && scan.charAt(3)=='L' && scan.charAt(4)=='E'){    // W->H->I->L->E -> finish is true
            return true;
        }
        return false;           //else false
    }

    public static boolean checkReturn(String scan){     // dfa for return
        if(scan.length()!=6){
            return false;
        }
        if(scan.charAt(0)=='r' && scan.charAt(1)=='e' && scan.charAt(2)=='t' && scan.charAt(3)=='u' && scan.charAt(4)=='r' && scan.charAt(5)=='n'){     // r->e->t->u->r->n -> finish is true
            return true;
        }
        if(scan.charAt(0)=='R' && scan.charAt(1)=='E' && scan.charAt(2)=='T' && scan.charAt(3)=='U' && scan.charAt(4)=='R' && scan.charAt(5)=='N'){     // R->E->T->U->R->N -> finish is true
            return true;
        }
        return false;           //else false
    }

    public static boolean checkArithmetic(String scan){         // dfa for arithmetic
        if(scan.length()!=1){
            return false;
        }

        if((scan.charAt(0)=='+' || scan.charAt(0)=='-' || scan.charAt(0)=='*' || scan.charAt(0)=='/')){       // if start & end with + - * / is true
            return true;
        }
        return false;       // else false
    }

    public static boolean checkAssignment(String scan){     // dfa for Assignment
        if(scan.charAt(0)=='=' && scan.length()==1){    //if only one char and start with = is true
            return true;
        }
        return false;           //else false
    }

    public static boolean checkComparison(String scan){ // dfa for Comparisons
        if(scan.length()==1){
            if(scan.charAt(0) == '<' || scan.charAt(0)=='>'){   // <  or > and finish, it is true
                return true;
            }
        }
        else if(scan.length()==2){
            if((scan.charAt(0) == '<' || scan.charAt(0)=='>' || scan.charAt(0) == '!' || scan.charAt(0)=='=')&& scan.charAt(1)=='=' ){  // start at < or > or ! or = , and finish at = is true
                return true;
            }
        }
        return false;       // else false
    }

    public static boolean checkTerminate(String scan){      // dfa for Terminate
        if(scan.charAt(0)==';'){    // start at semicolon and end at semicolon is true
            return true;
        }

        return false;       // else false
    }

    public static boolean checkLPAREN(String scan){ // dfa for LPAREN
        if(scan.charAt(0)=='('){        // start at ( and end at ( is true
            return true;
        }
        return false;       // else false
    }

    public static boolean checkRPAREN(String scan){ // dfa for RPAREN
        if(scan.charAt(0)==')'){        // start at ) and end at ) is true
            return true;
        }
        return false;   // else false
    }

    public static boolean checkLBAREN(String scan){ // dfa for LBAREN
        if(scan.charAt(0)=='{'){        // start at { and end at { is true
            return true;
        }
        return false;  // else false
    }

    public static boolean checkRBAREN(String scan){ // dfa for RBAREN
        if(scan.charAt(0)=='}'){    // start at } and end at } is true
            return true;
        }
        return false;   //else false
    }

    public static boolean checkComma(String scan){      // dfa for Comma
        if(scan.charAt(0)==','){    // start at , and end at , is true
            return true;
        }
        return false;   //else false
    }

    public static boolean checkWhitespaces(String scan){       // dfa for white space
        if(scan.charAt(0)=='\t' || scan.charAt(0)=='\n' || scan.charAt(0)==' ' ){   // if \t or \n or ' ' are define whitespace
            return true;
        }
        return false;  // else false
    }

    public static boolean checkComments(String scan){       //dfa for check Comments
        if(scan.charAt(0)=='/' && scan.charAt(1)=='/'){     // if start at / and follow / is comment
            return true;
        }
        return false;   // else false
    }

    public static String Analyze(String scan){      // check what token is?
        if(checkINT(scan)){
            return "INT";
        }
        else if(checkCHAR(scan)){
            return "CHAR";
        }
        else if(checkIf(scan)){
            return "IF";
        }
        else if(checkElse(scan)){
            return "ELSE";
        }
        else if(checkWhile(scan)){
            return "WHILE";
        }
        else if(checkReturn(scan)){
            return "RETURN";
        }
        else if(checkArithmetic(scan)){
            return "ARITHMETIC";
        }
        else if(checkAssignment(scan)){
            return "ASSIGN";
        }
        else if(checkComparison(scan)){
            return "COMPARISON";
        }
        else if(checkTerminate(scan)){
            return "TERMINATE";
        }
        else if(checkLPAREN(scan)){
            return "LPAREN";
        }
        else if(checkLBAREN(scan)){
            return "LBAREN";
        }
        else if(checkRPAREN(scan)){
            return "RPAREN";
        }
        else if(checkRBAREN(scan)){
            return "RBAREN";
        }
        else if(checkComma(scan)){
            return "COMMA";
        }
        else if(checkWhitespaces(scan)){
            return "WHITESPACE";
        }
        else if(checkSignedInteger(scan)){
            return "SINGED_INTEGER";
        }
        else if(checkLiteralString(scan)){
            return "LITERAL_STRING";
        }
        else if(checkID(scan)){
            return "ID";
        }
        else if(checkComments(scan)){   //Comments are not allowed
            return "ERROR (COMMENT)";
        }
        else{
            return "ERROR (TOKEN NOT ACCEPT)"; // others are not allowed
        }
    }

}
