import java.io.IOException;

//This class denotes a token in language
 public class Token {

    protected TokenType tokenType;
    protected String text;
    protected ProgramText source;

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

    protected void extract() throws IOException {
        text = Character.toString(source.curChar());
        source.nextChar();
//        nextChar();
    }
    protected char currentChar() throws IOException {
        return source.curChar();
    }

    public char nextChar() {
        return source.nextChar();
    }
}
