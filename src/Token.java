import java.io.IOException;

//This class denotes a token in language
 public class Token {

    protected TokenType tokenType;
    protected String text;
    protected ProgramText source;
    protected Object value;

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
//        nextChar();
    }
    public char currentChar() throws IOException {
        return source.curChar();
    }

    public char nextChar() {
        return source.nextChar();
    }
}
