import java.io.IOException;

//@AUTHOR: GORKEM TOPRAK
//DATE: May 3, 2021 Monday

public class EOFToken extends Token{
    EOFToken(ProgramText source){
        super(source);
        extract();
    }

    public void extract(){
        tokenType = TokenType.END_OF_FILE;
    }
}
