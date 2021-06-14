import java.io.IOException;

//@AUTHOR: GORKEM TOPRAK
//DATE: May 3, 2021 Monday

public class SpecialToken extends Token{
    SpecialToken(ProgramText source, String text, TokenType tokenType){
        super(source);
        this.text = text;
        this.tokenType = tokenType;
    }
}
