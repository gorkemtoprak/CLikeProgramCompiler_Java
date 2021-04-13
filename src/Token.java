import java.io.IOException;

//This class denotes a token in language

//@AUTHOR: GORKEM TOPRAK
//DATE: April 11, 2021 Sunday

 public class Token {

    public TokenType tokenType;
    public String text;
    public ProgramText source;

    Token (ProgramText source) throws IOException {
        this.source = source;
        extract();
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public String getText(){
        return text;
    }

    public void extract() throws IOException {
        text = Character.toString(source.curChar());
        source.nextChar();
    }
    public char currentChar() throws IOException {
        return source.curChar();
    }

    public char nextChar() {
        return source.nextChar();
    }
}
