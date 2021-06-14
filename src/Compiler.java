
//@AUTHOR: GORKEM TOPRAK
//DATE: May 3, 2021 Monday

public class Compiler {

    private Parser parser;
    private ProgramText programText;
    private String label;

    public Compiler(){

        try {
            label = "P";
            programText = new ProgramText();
            Scanner scanner = new Scanner(programText);
            for(int i=0; i<programText.progText.length(); i++){
                parser = new Parser(scanner, label);
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
