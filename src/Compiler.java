
//@AUTHOR: GORKEM TOPRAK
//DATE: April 11, 2021 Sunday

public class Compiler {

    private Parser parser;
    private ProgramText programText;

    public Compiler(){
        try {
            programText = new ProgramText();
            Scanner scanner = new Scanner(programText);
            for(int i=0; i<programText.progText.length(); i++){
                parser = new Parser(scanner);
                parser.parse();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Compiler();
    }
}
