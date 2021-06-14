import java.io.IOException;

//This class denotes a token in language

//@AUTHOR: GORKEM TOPRAK
//DATE: May 3, 2021 Monday

 public class Token {

    public TokenType tokenType;
    public String text;
    public ProgramText source;

    Token (ProgramText source){
        this.source = source;
        extract();
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public String getText(){
        return text;
    }

    public void extract(){
        text = Character.toString(source.curChar());
        source.nextChar();
    }
    public char currentChar(){
        return source.curChar();
    }

    public char nextChar() {
        return source.nextChar();
    }
}
