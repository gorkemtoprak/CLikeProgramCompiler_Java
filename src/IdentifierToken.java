import java.io.IOException;

//@AUTHOR: GORKEM TOPRAK
//DATE: April 11, 2021 Sunday

public class IdentifierToken extends Token{
    IdentifierToken(ProgramText source) throws IOException {
        super(source);
    }

    public void extract()  throws IOException {
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
        else {
            tokenType = TokenType.IDENTIFIER;
        }
    }
}
