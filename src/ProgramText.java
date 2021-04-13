import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

// The purpose of this class is to abstract away from where to program is coming.
// This class provides a single character to the Scanner class when asked for.
// It reads the program (from a file or as a String) line by line from top to bottom.

//@AUTHOR: GORKEM TOPRAK
//DATE: April 11, 2021 Sunday

public class ProgramText {

    public String progText;
    public BufferedReader bufferedReader;
    private int curPos;
    public static char EOF = 0;

    ProgramText(){
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

    private void readLine() throws IOException {
        progText = bufferedReader.readLine();
        curPos = -1;
        if (progText != null) {
            ++curPos;
        }
        //   try {
//            progText = bufferedReader.readLine();
//            curPos = -1;
//            if (progText != null) {
//                ++curPos;
//            }
//        } catch (IOException e) {
//            e.printStackTrace ();
//        }
    }

}

