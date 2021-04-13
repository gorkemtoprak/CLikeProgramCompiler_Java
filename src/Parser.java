
//This class knows about the grammar and verifies that the sequence of tokens returned by
//the Scanner follows the rules of the grammar

//P -> S+        //+ here means one or more (it is a shortcut)
//S -> while(E){S+}
//S -> if(E){S+}
//E -> E + T

//@AUTHOR: GORKEM TOPRAK
//DATE: April 11, 2021 Sunday

public class Parser {
    private Scanner scanner;
    private Token token;

    Parser(Scanner scanner){
        this.scanner = scanner;
    }

    //this parse method will be used only for the first assignment
    void parse() throws Exception {
        //While we do not reach the end of file we will keep asking the scanner for the next token
        while (!((token = nextToken()) instanceof EOFToken)){
            if(token == null){
//                System.out.println("token is returning null..");
            }
            else{
                System.out.printf("Token Text: %s  - Token Type: %s \n", token.getText(), token.getTokenType());
            }
        }
    }

    public Token nextToken() throws Exception {
        return scanner.nextToken();
    }

    /*
     * the actual parse method will be like the following:
     * void parse(){
     * 		Token token = scanner.nextToken();
     * 		P(token);
     * }
     *
     */
    //For every non-terminal symbol in the grammar write a method.
    void P(Token token) {//S(token);}

    }
    void S(Token token) {//if(token.text.equals("while") ...
        //if(token.text.equals("if")...
        //else there is a syntax error here
    }

    void T() {}
    //...
}
