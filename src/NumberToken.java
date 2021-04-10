import java.io.IOException;

public class NumberToken extends Token{

    NumberToken(ProgramText source) throws IOException {
        super(source);
        StringBuffer buffer = new StringBuffer();
        buffer.append(source.curChar()); // we have the first character that Scanner has read the rest of digits.
        //return the number token
        extractNumber(buffer);
        text = buffer.toString();
    }

    protected void extract()  throws IOException {
        StringBuffer buffer = new StringBuffer();
        buffer.append(source.curChar());
        extractNumber(buffer);
        text = buffer.toString();
    }


    protected int extractNumber(StringBuffer digits) {

        if (digits == null) {
            return 0;
        }
        int curVal = 0;
        int prevValue = -1;
        int index = 0;

        while ((index < digits.length()) && (curVal >= prevValue)) {
            prevValue = curVal;
//            curVal = 10 * curVal +  Character.getNumericValue(digits.charAt(index++));
            curVal = Character.getNumericValue(digits.charAt(index++));
        }

        if (curVal >= prevValue) {
            return curVal;
        }
        else
            return 0;
    }
}
