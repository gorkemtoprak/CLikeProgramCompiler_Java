import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

// The purpose of this class is to abstract away from where to program is coming.
// This class provides a single character to the Scanner class when asked for.
// It reads the program (from a file or as a String) line by line from top to bottom.

public class ProgramText {
//    private BufferedReader reader;
//    private String progText;
//    private int posInLine;
//    public static char EOF = 0;
//
//    ProgramText(BufferedReader reader){
//        this.reader = reader;
//        try {
//            progText = readWholeProgram();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        posInLine = -1;
//    }
//
//    private String readWholeProgram() throws IOException {
//        return new String(Files.readAllBytes(Paths.get("program1.txt")));
//    }
//
//    char curChar(){
//        if (posInLine == -1){
//            posInLine++;
//        }
//        return progText.charAt(posInLine);
//    }
//
//    char nextChar(){
//        //what if we are reading first time
//        //what if we are at the end of the line
//        //0|1|2|3
//        if (posInLine == -1 || posInLine == progText.length()){
//            readLine();
//            if (progText == null){
//                return  EOF;
//            }
//            else{
//                posInLine++;
//                return progText.charAt(posInLine);
//            }
//        }
//        return progText.charAt(++posInLine);
//    }
//
//    void readLine(){
//        try {
//            progText = reader.readLine();
//            posInLine = -1;
//        } catch (IOException e) {
//            e.printStackTrace ();
//        }
//    }

    private String progText;
    public BufferedReader bufferedReader;
    private int curPos;
    public static char EOF = 0;
    public static final char EOL = '\n';

    ProgramText(BufferedReader bufferedReader){
        curPos = -1;

        try {
            progText = readWholeProgram();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private String readWholeProgram() throws IOException {
        return new String(Files.readAllBytes(Paths.get("program1.txt")));
    }

    char curChar() throws IOException {
        if (curPos == -1) {
            readLine();
            return nextChar();
        }
        else if (progText == null) {
            return EOF;
        }
        else if ((curPos == -1) || (curPos == progText.length())) {
            return EOL;
        }
        else if (curPos > progText.length()) {
            readLine();
            return nextChar();
        }
        else {
            return progText.charAt(curPos);
        }
    }

    char nextChar() {
        curPos++;
        if(curPos == progText.length())
            return EOF;

        return progText.charAt(curPos);
    }

    public char peekChar()
            throws Exception
    {
        curChar();
        if (progText == null) {
            return EOF;
        }

        int nextPos = curPos + 1;
        return nextPos < progText.length() ? progText.charAt(nextPos) : EOL;
    }

    private void readLine() throws IOException {
        progText = bufferedReader.readLine();
        curPos = -1;
        if (progText != null) {
            ++curPos;
        }
    }

}
