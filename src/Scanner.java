
//This class responsible for scanning token to the parser

public class Scanner {
    private Token token;
    private ProgramText source;

    Scanner(ProgramText source) {
        this.source = source;
    }
    //Scanner will ask the Source for characters and one sequence of characters from a token and it will return
    //immediately. Scanner needs to know some if rules (for example, what constitutes a number, or and identifier)

    protected Token extractToken() throws Exception {

        skipWhiteSpace();

        Token token;
        char currentChar = currentChar();
        if (currentChar == ProgramText.EOF) {
            token = new EOFToken(source);
        }
        else if (Character.isLetter(currentChar)) {
            token = new IdentifierToken(source);
        }
        else if (Character.isDigit(currentChar)) {
            token = new NumberToken(source);
        }
        else if (Character.isAlphabetic(currentChar)) {
            token = new SpecialToken(source);
        }
        else
            return token = null;


        return token;
    }

    Token nextToken() throws Exception {
//        extractToken();
        char ch = source.nextChar();

        if (Character.isDigit(ch)){
            //number token
            token = new NumberToken(source);
        }
        else if(Character.isLetter(ch)){
            //identifier token
            token = new IdentifierToken(source);
        }
        else if(Character.isAlphabetic(ch)){
            //special symbol token
            token = new SpecialToken(source);
        }
        else{
            return null;
        }
        return token;
    }

    public char currentChar()
            throws Exception
    {
        return source.curChar();
    }

    public char nextChar() throws Exception {
        return source.nextChar();
    }

    private void skipWhiteSpace() throws Exception{
        char currentChar = currentChar();

        while (Character.isWhitespace(currentChar) || (currentChar == '/')){

            // Start of a comment?
            if(currentChar == '/') {
                currentChar = nextChar();

                //Is single line comment?
                if(currentChar == '/') {
                    do {
                        currentChar = nextChar();
                    } while ((currentChar != ProgramText.EOL) && (currentChar != ProgramText.EOF));
                }

                //Is multiline comment?
                else if(currentChar == '*') {
                    boolean end = false;
                    do {
                        currentChar = nextChar();
                        if((currentChar == '*') && (peekChar() == '/')) { //reached the end?
                            end = true;
                            break;
                        }
                    } while (currentChar != ProgramText.EOF);
                    if(end) {
                        nextChar(); // consumes '*'
                        currentChar = nextChar(); // consumes '/'
                    }
                }
            }

            // Not a comment.
            else {
                currentChar = nextChar(); //consume whitespace character.
            }
        }
    }

    public char peekChar() throws Exception{
        return source.peekChar();
    }
}
