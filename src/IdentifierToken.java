import java.io.IOException;

public class IdentifierToken extends Token{
    IdentifierToken(ProgramText source) throws IOException {
        super(source);
    }

    public void extract()  throws IOException {
        StringBuilder textBuffer = new StringBuilder();
        char currentChar = currentChar();

        while (Character.isLetter(currentChar)) {
            textBuffer.append(currentChar);
            currentChar = nextChar();
        }
        text = textBuffer.toString();
        tokenType = TokenType.IDENTIFIER;
    }
}
