import java.io.IOException;

//@AUTHOR: GORKEM TOPRAK
//DATE: May 3, 2021 Monday

public class NumberToken extends Token{

    NumberToken(ProgramText source){
        super(source);
        //return the number token
        extract();
    }

    public void extract() {
        StringBuilder stringBuilder = new StringBuilder();
        char curChar = currentChar();
        while(Character.isDigit(curChar)) {
            stringBuilder.append(curChar);
            curChar = nextChar();
            text = stringBuilder.toString();
        }
        tokenType = TokenType.NUMBER;
    }

}