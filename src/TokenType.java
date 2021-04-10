
public enum TokenType {
    LEFT_CURLY("{"),
    RIGHT_CURLY("}"),
    LEFT_PAR("("),
    RIGHT_PAR(")"),
    EQUAL("="),
    PLUS("+"),
    SEMI_COLON(";"),
    LESS_THAN("<"),
    WHILE,
    IDENTIFIER,
    NUMBER,
    SPACE;

    private String text;

    TokenType(String text) {
        this.text = text;
    }
    TokenType(){
        this.text = this.toString();
    }
}