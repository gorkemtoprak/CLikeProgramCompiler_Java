import java.io.IOException;

//@AUTHOR: GORKEM TOPRAK
//DATE: May 3, 2021 Monday

public class IdentifierToken extends Token{
    IdentifierToken(ProgramText source){
        super(source);
    }

    public void extract(){
        StringBuilder stringBuilder = new StringBuilder();
        char curChar = currentChar();

        while(Character.isLetter(curChar)) {
            stringBuilder.append(curChar);
            curChar = nextChar();
        }

        text = stringBuilder.toString();
        if (text.contains("while")){
            tokenType = TokenType.WHILE;
        }
        else if(text.contains("if")){
            tokenType = TokenType.IF;
        }
        else if(text.contains("else if")){
            tokenType = TokenType.ELSE_IF;
        }
        else if(text.contains("else")){
            tokenType = TokenType.ELSE;
        }
        else if(text.contains("in")){
            tokenType = TokenType.IN;
        }
        else if(text.contains("out")){
            tokenType = TokenType.OUT;
        }
        else if(text.contains("begin")){
            tokenType = TokenType.BEGIN;
        }
        else if(text.contains("end_while")){
            tokenType = TokenType.END_WHILE;
        }
        else if(text.contains("then")){
            tokenType = TokenType.THEN;
        }
        else if(text.contains("end_if")){
            tokenType = TokenType.END_IF;
        }
        else {
            tokenType = TokenType.IDENTIFIER;
        }
    }
}
