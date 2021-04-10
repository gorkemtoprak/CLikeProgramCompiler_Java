import java.io.IOException;

public class IdentifierToken extends  Token{
    IdentifierToken(ProgramText source) throws IOException {
        super(source);
    }

    protected void extract()  throws IOException {
        StringBuilder textBuffer = new StringBuilder();
        char currentChar = currentChar();

        while (Character.isLetterOrDigit(currentChar)) {
            textBuffer.append(currentChar);
            currentChar = nextChar();
        }
        text = textBuffer.toString();
        tokenType = TokenType.IDENTIFIER;

    }
}
