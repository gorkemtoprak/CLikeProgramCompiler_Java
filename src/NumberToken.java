import java.io.IOException;

public class NumberToken extends Token{

    NumberToken(ProgramText source) throws IOException {
        super(source);
        StringBuffer buffer = new StringBuffer();
        buffer.append(source.curChar()); // we have the first character that Scanner has read the rest of digits.
        //return the number token
//        getNumber(buffer);
        extract();
    }

    public void extract()  throws IOException {
        StringBuffer buffer = new StringBuffer();
        buffer.append(source.curChar());
        getNumber(buffer);
        text = buffer.toString();
    }

    private int getNumber(StringBuffer digits) {

        tokenType = TokenType.NUMBER;
        int currentNum = 0;
        int prevNumber = -1;
        int index = 0;

        if (digits == null) {
            return 0;
        }

        while ((index < digits.length()) && (currentNum >= prevNumber)) {
            prevNumber = currentNum;
            currentNum = Character.getNumericValue(digits.charAt(index++));
        }

        if (currentNum >= prevNumber) {
            return currentNum;
        }
        else
            return 0;
    }
}