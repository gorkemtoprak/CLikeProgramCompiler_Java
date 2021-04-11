
//This class responsible for scanning token to the parser
//Scanner will ask the Source for characters and one sequence of characters from a token and it will return
//immediately. Scanner needs to know some if rules (for example, what constitutes a number, or and identifier)

import java.util.HashMap;

public class Scanner {
    private Token token;
    private ProgramText source;
//    private HashMap<TokenType, String> tokenTypeStringHashMap;
    Scanner(ProgramText source) {
        this.source = source;
    }

    Token nextToken() throws Exception {

        char ch = source.nextChar();

        while (Character.isWhitespace(ch)){
            ch = source.nextChar();
        }
        if (Character.isDigit(ch)){
            //number token
            token = new NumberToken(source);
        }
        else if(Character.isLetter(ch)){
            //identifier token
            token = new IdentifierToken(source);
        }
//        else if(Character.isAlphabetic(ch)){
//            tokenTypeStringHashMap.put(TokenType.EQUAL, "=");
//            tokenTypeStringHashMap.put(TokenType.WHILE, "\\b(while)\\b.*");
//            token = new SpecialToken(source);
//        }
        else if(!Character.isWhitespace(ch)){
            for(TokenType type : TokenType.values()){
                if(String.valueOf(ch).equals(type.getText())){
                    token = new SpecialToken(source,String.valueOf(ch),type);
                    return token;
                }


            }
        }
        else{
            return null;
        }
        return token;
    }
}