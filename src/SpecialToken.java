import java.io.IOException;

//@AUTHOR: GORKEM TOPRAK
//DATE: April 11, 2021 Sunday

public class SpecialToken extends Token{
    SpecialToken(ProgramText source, String text, TokenType tokenType) throws IOException{
        super(source);
        this.text = text;
        this.tokenType = tokenType;
    }
}
