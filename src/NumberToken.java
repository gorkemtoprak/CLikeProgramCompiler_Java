import java.io.IOException;

//@AUTHOR: GORKEM TOPRAK
//DATE: April 11, 2021 Sunday

public class NumberToken extends Token{

    NumberToken(ProgramText source) throws IOException {
        super(source);
        StringBuffer buffer = new StringBuffer();
        buffer.append(source.curChar()); // we have the first character that Scanner has read the rest of digits.
        //return the number token
        extract();
    }

    public void extract()  throws IOException {
        StringBuffer buffer = new StringBuffer();
        buffer.append(source.curChar());
        text = buffer.toString();
        tokenType = TokenType.NUMBER;
    }

}