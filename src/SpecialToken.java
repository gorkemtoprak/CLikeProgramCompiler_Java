import java.io.IOException;

public class SpecialToken extends Token{
    SpecialToken(ProgramText source, String text, TokenType specialtype) throws IOException{
        super(source);
        this.text = text;
        this.tokenType = specialtype;
    }

    public void extract() throws IOException {
        char currentChar = currentChar();
        text = Character.toString(currentChar);
        switch (currentChar) {
            case';':{
                currentChar = nextChar();
                if (currentChar == '=') {
                    text += currentChar;
                    nextChar();
                }
                else if (currentChar == ';') {
                    tokenType = TokenType.SEMI_COLON;
                    text += currentChar;
                    nextChar();
                }
            }
            case ')': {
                currentChar = nextChar();
                if (currentChar == '=') {
                    text += currentChar;
                    nextChar();
                }
                else if (currentChar == ')') {
                    tokenType = TokenType.RIGHT_PAR;
                    text += currentChar;
                    nextChar();
                }
            }
            case '(': {
                currentChar = nextChar();
                if (currentChar == '=') {
                    text += currentChar;
                    nextChar();
                }
                else if (currentChar == '(') {
                    tokenType = TokenType.LEFT_PAR;
                    text += currentChar;
                    nextChar();
                }
                break;
            }
            case '}':  {
                currentChar = nextChar();
                if (currentChar == '=') {
                    text += currentChar;
                    nextChar();
                }
                else if (currentChar == '+') {
                    tokenType = TokenType.RIGHT_CURLY;
                    text += currentChar;
                    nextChar();
                }
                break;
            }
            case '{': {
                currentChar = nextChar();
                if (currentChar == '=') {
                    text += currentChar;
                    nextChar();
                }
                else if (currentChar == '{') {
                    tokenType = TokenType.LEFT_CURLY;
                    text += currentChar;
                    nextChar();
                }
                break;
            }
            case '=': {
                currentChar = nextChar();
                if (currentChar == '=') {
                    text += currentChar;
                    nextChar();
                }
                else if (currentChar == '=') {
                    tokenType = TokenType.EQUAL;
                    text += currentChar;
                    nextChar();
                }
                break;
            }
            case '+': {
                currentChar = nextChar();
                if (currentChar == '=') {
                    text += currentChar;
                    nextChar();
                }
                else if (currentChar == '+') {
                    tokenType = TokenType.PLUS;
                    text += currentChar;
                    nextChar();
                }
                break;
            }
            case '<': {
                currentChar = nextChar();
                if (currentChar == '=') {
                    text += currentChar;
                    nextChar();
                }
                else if (currentChar == '<') {
                    tokenType = TokenType.LESS_THAN;
                    text += currentChar;
                    nextChar();
                    break;
                }
                break;
            }
            default: {
                nextChar();
                System.out.println("Error..!");
            }
        }
    }

}
