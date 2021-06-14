//@AUTHOR: GORKEM TOPRAK
//DATE: May 3, 2021 Monday

public enum TokenType {
    LEFT_CURLY("{"), RIGHT_CURLY("}"), LEFT_PAR("("), RIGHT_PAR(")"),
    EQUAL("="), EQUAL2("=="), PLUS("+"), MINUS("-"),  DIVIDE("/"),  MULT("*"), SEMI_COLON(";"),
    LESS_THAN("<"), GREATER_THAN(">"), LESS_THAN_EQUAL("<="), GREATER_THAN_EQUAL(">="), NOT_EQUAL("!="),
    BEGIN("begin"), THEN("then"), END_WHILE("end_while"), END_IF("end_if"), QUESTION_MARK("?"), COLON(":"),
    REMAINDER("%"), EXPONENT("^"),

    WHILE,
    END_OF_FILE,
    ERROR,
    IF,
    ELSE_IF,
    ELSE,
    IN,
    OUT,
    IDENTIFIER,
    NUMBER;

    private String text;

    TokenType(String text) {
        this.text = text;
    }
    TokenType(){
        this.text = this.toString();
    }

    //This getter just for the get all of the text values..
    public String getText() {
        return text;
    }
}