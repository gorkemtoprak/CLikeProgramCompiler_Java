//@AUTHOR: GORKEM TOPRAK
//DATE: April 11, 2021 Sunday


public enum TokenType {
    LEFT_CURLY("{"), RIGHT_CURLY("}"), LEFT_PAR("("), RIGHT_PAR(")"),
    EQUAL("="), PLUS("+"),  SEMI_COLON(";"), LESS_THAN("<"),

    WHILE,

    IDENTIFIER, NUMBER;

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