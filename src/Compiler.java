import java.io.BufferedReader;
import java.io.FileReader;

public class Compiler {

    private Parser parser;
    private ProgramText programText;

    public Compiler(){
        try {
            FileReader fileReader = new FileReader("program1.txt");
            programText = new ProgramText(new BufferedReader(fileReader));
            Scanner scanner = new Scanner(programText);
            parser = new Parser(scanner);
            parser.parse();
        }
        catch (Exception e) {
            System.out.println("Error occur..!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Compiler();
    }
}
