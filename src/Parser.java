//This class knows about the grammar and verifies that the sequence of tokens returned by
//the Scanner follows the rules of the grammar

//@AUTHOR: GORKEM TOPRAK
//DATE: June 7, 2021 Monday

import java.util.ArrayList;
import java.util.HashMap;

public class Parser extends Node {
    private Scanner scanner;
    private Token token;
    private boolean check = true;
    public Tree tree;
    public String label;
    public Node node = new Node("P");
    public Node currentNode;
    public String leftChild;
    public ArrayList<String> listControl = new ArrayList<String>(); //If the parentheses are closed it will do the current node parent.
    private java.util.Scanner sc = new java.util.Scanner(System.in);

    Parser(Scanner scanner, String label){
        super(label);
        this.scanner = scanner;
        this.label = label;
    }

    //this parse method will be used only for the first assignment
    void parse() throws Exception {
        //While we do not reach the end of file we will keep asking the scanner for the next token
        while (!((token = nextToken()) instanceof EOFToken)){
            if(token == null){
                System.out.println("Token is returning null..");
                System.exit(0);
            }
            else{
                if (token != null) {
                    currentNode = node;
                    if(listControl.size() > 0){
                        for(int i=0; i<listControl.size(); i++){
                            for(Node n : currentNode.children){
                                if(n.label.equals(listControl.get(i))){
                                    currentNode = n.children.get(1);
                                }
                            }
                        }
                    }
                    S();
                }

            }
            eval();
            DFT dft = new DFT();
            System.out.println("Pre-Order of my program's parse tree: ");
            System.out.println("-----------------------------------------------------");
            System.out.println(dft.preorder(node));
            System.out.println("-----------------------------------------------------");
            System.out.println("Post-Order of my program's parse tree: ");
            System.out.println("-----------------------------------------------------");
            System.out.println(dft.postorder(node));
        }

    }

    public Token nextToken(){
        return scanner.nextToken();
    }

    //Before I call any function I assume that the token which is the first token for this non terminal function
    //is already in the currentToken
    void S() throws Exception {
        if(token.getTokenType().equals(TokenType.END_OF_FILE)){
            System.out.println("End of the file.. ");
            return;
        }
        else {
            S1();
//            checkProgram();
        }
    }

    // This is check programs if it's true then print the true message if not
    // it will be show error message or end of file error.. [IN THIS PHASE 3 WE DON'T NEED THIS..]
//    boolean checkProgram() throws Exception {
//        S1();
//        if(token.getTokenType().equals(TokenType.END_OF_FILE)){
//            System.out.println("End of the file.. ");
//            return false;
//        }
//        else {
//            System.out.println("Program is synthetically true!");
//        }
//        return true;
//    }

    //This is adding children to the tree
    void addChildToTree(Node node, String label){
        node.addChild(new Node(label));
    }

    public Node S1() throws Exception {
        leftChild = token.getText();
        while (check){
           if(token.getTokenType().equals(TokenType.IF)){
               addChildToTree(currentNode, token.getText());
               for(Node n : currentNode.children){
                   if(n.label.equals(TokenType.IF.getText())){
                       currentNode = n;
                   }
               }
                token = scanner.nextToken();
                if(token.getTokenType().equals(TokenType.LEFT_PAR)){
                    token = scanner.nextToken();
                    Exp();
                    addChildToTree(currentNode, Exp().toString());
                }
                else {
                    System.out.println("[ERROR CODE 3] Left Parenthesis Error: '(' is missing.");
//                    throw new Exception("[ERROR CODE 3] Left Parenthesis Error: '(' is missing." + token.getText());
                    System.exit(0);
                }
            }
           else if(token.getTokenType().equals(TokenType.WHILE)){
               addChildToTree(currentNode, token.getText());
               for(Node n : currentNode.children){
                   if(n.label.equals(TokenType.WHILE.getText())){
                       currentNode = n;
                   }
               }
               token = scanner.nextToken();
               if(token.getTokenType().equals(TokenType.LEFT_PAR)){
                   token = scanner.nextToken();
//                    System.out.println(token.getText());
                   if (token.tokenType.equals(TokenType.IDENTIFIER)){
                       leftChild = token.getText();
                       token = scanner.nextToken();
                       addChildToTree(currentNode, token.getText());
                       for(Node n : currentNode.children){
                           if(n.label.equals(token.getText())){
                               currentNode = n;
                           }
                       }
                       addChildToTree(currentNode, leftChild);
                       Boolean();
                       for(Node n : currentNode.children){
                           if(n.label.equals(TokenType.LESS_THAN.getText()) || n.label.equals(TokenType.GREATER_THAN.getText())
                           ||n.label.equals(TokenType.GREATER_THAN_EQUAL.getText()) || n.label.equals(TokenType.LESS_THAN_EQUAL.getText())){
                               currentNode = n;
                           }
                       }
                       if (token.tokenType.equals(TokenType.NUMBER) || token.tokenType.equals(TokenType.IDENTIFIER)){
                           label = token.getText();
                           addChildToTree(currentNode, label);
                           token = scanner.nextToken();
                           if (token.tokenType.equals(TokenType.RIGHT_PAR)){
                               token = scanner.nextToken();
                               if (token.tokenType.equals(TokenType.LEFT_CURLY)){
//                                            System.out.println(children.get(0).label);
                                   addChildToTree(currentNode,"body");
                                   token = scanner.nextToken();
                                   if(token.getTokenType().equals(TokenType.OUT)){
                                        addChildToTree(currentNode, "out");
//                                        new Out(this, "output");
                                       token = scanner.nextToken();
                                       if (token.tokenType.equals(TokenType.IDENTIFIER)){
                                           Ident(token.getText());
//                                           label = token.getText();
//                                           addChildToTree(currentNode, label);
                                           token = scanner.nextToken();
                                           if (token.tokenType.equals(TokenType.SEMI_COLON)){
                                               token = scanner.nextToken();
                                               if (token.getTokenType().equals(TokenType.RIGHT_CURLY)) {
                                                    token = scanner.nextToken();
//                                                    System.out.println( token.getText());
//                                                   System.exit(0);
                                               }
                                               else {
                                                   System.out.println("[ERROR CODE 6] Right Curly Parenthesis Error: '}' is missing.");
//                                                    throw new Exception("[ERROR CODE 6] Right Curly Parenthesis Error: '}' is missing. " + token.getText());
//                                                    System.exit(0);
                                               }
                                           }
                                           else {
                                               System.out.println("[ERROR CODE 1] Semicolon Error: ';' is missing.");
                                               System.exit(0);
                                           }
                                       }
                                       else {
                                           System.out.println("[ERROR CODE 1] Identifier is missing.");
                                           System.exit(0);
                                       }
                                   }
                               }
                               else {
                                   System.out.println("[ERROR CODE 5] Left Curly Error: '{' is missing.");
                               }
                           }
                           else {
                               System.out.println("[ERROR CODE 4] Right Parenthesis Error: ')' is missing.");
//                        throw new Exception("[ERROR CODE 4] Right Parenthesis Error: ')' is missing. " + token.getText());
                           }
                       }
                       else {
                           System.out.println("[ERROR CODE 8] Error there are no Number please use..");
//                    throw new Exception("[ERROR CODE 8] Error there are no Number please use. " + token.getText());
                       }
                   }
                   else {
                       System.out.println("[ERROR CODE 9] Error there are no Identifier please use..");
//                throw new Exception("[ERROR CODE 9] Error there are no Identifier please use. " + token.getText());
                   }
               }
               else {
                   System.out.println("[ERROR CODE 3] Left Parenthesis Error: '(' is missing.");
//                    throw new Exception("[ERROR CODE 3] Left Parenthesis Error: '(' is missing." + token.getText());
                   System.exit(0);
               }
           }
            else if(token.getTokenType().equals(TokenType.IDENTIFIER)){
                leftChild = token.getText();
                token = scanner.nextToken();
                if(token.getTokenType().equals(TokenType.EQUAL)){
                    addChildToTree(currentNode, token.getText());
                    for(Node n : currentNode.children){
                        if(n.label.equals(TokenType.EQUAL.getText())){
                            currentNode = n;
                        }
                    }
                    addChildToTree(currentNode, leftChild);
                    token = scanner.nextToken();
    //                    Exp();
                    if (token.getTokenType().equals(TokenType.NUMBER) || token.getTokenType().equals(TokenType.IDENTIFIER)){
                        leftChild = token.getText();
                        addChildToTree(currentNode, leftChild);
                        token = scanner.nextToken();
                         if (token.getTokenType().equals(TokenType.PLUS) || token.getTokenType().equals(TokenType.MINUS)
                                 || token.getTokenType().equals(TokenType.MULT) || token.getTokenType().equals(TokenType.DIVIDE))
                         {
                             addChildToTree(currentNode, token.getText());
                             for(Node n : currentNode.children){
                                 if(n.label.equals(TokenType.PLUS.getText()) || n.label.equals(TokenType.MINUS.getText())){
                                     currentNode = n;
                                 }
                             }
                             token = scanner.nextToken();
                             if (token.getTokenType().equals(TokenType.NUMBER) || token.getTokenType().equals(TokenType.IDENTIFIER)) {
                                 label = token.getText();
                                 addChildToTree(currentNode, label);
                                 token = scanner.nextToken();
                                 if (token.getTokenType().equals(TokenType.SEMI_COLON)) {
//                                         token = scanner.nextToken();
                                 }
                                 else {
                                     System.out.println("[ERROR CODE 1] Semicolon Error: ';' is missing.");
//                                     throw new Exception("[ERROR CODE 1] Semicolon Error: ';' is missing. " + token.getText());
//                                     System.exit(0);
                                 }
                             }
                             else {
                                 System.out.println("[ERROR CODE 2] Error there is no Number or Identifier please use..");
//                                 throw new Exception("[ERROR CODE 2] Error there is no Number or Identifier please use.. " + token.getText());
                             }
                         }
                         if (token.getTokenType().equals(TokenType.SEMI_COLON)) {
    //                             token = scanner.nextToken();
                         }
                         else {
                             System.out.println("[ERROR CODE 1] Semicolon Error: ';' is missing.");
//                             throw new Exception("[ERROR CODE 1] Semicolon Error: ';' is missing. " + token.getText());
                             System.exit(0);
                         }
                    }
                    else {
                        System.out.println("[ERROR CODE 2] Error there is no Number or Identifier please use.");
//                        throw new Exception("[ERROR CODE 2] Error there is no Number or Identifier please use. " + token.getText());
                        System.exit(0);
                    }
                }
                else {
                    System.out.println("[ERROR CODE 7] Equal Sign Error: '=' is missing.");
//                    throw new Exception("[ERROR CODE 7] Equal Sign Error: '=' is missing. " + token.getText());
                    System.exit(0);
                }
            }
            else if(token.getTokenType().equals(TokenType.IN)){
                In();
                System.exit(0);
           }
           else if(token.getTokenType().equals(TokenType.OUT)){
               Out();
               System.exit(0);
           }
            break;
        }
        return node;
    }

    //This is our Expression method
    public Node Exp() throws Exception {
        while (check){
            if (token.tokenType.equals(TokenType.IDENTIFIER)){
                leftChild = token.getText();
                token = scanner.nextToken();
                addChildToTree(currentNode, token.getText());
                for(Node n : currentNode.children){
                    if(n.label.equals(TokenType.LESS_THAN.getText()) || n.label.equals(TokenType.GREATER_THAN.getText())
                            ||n.label.equals(TokenType.GREATER_THAN_EQUAL.getText()) || n.label.equals(TokenType.LESS_THAN_EQUAL.getText())){
                        currentNode = n;
                    }
                }
                Boolean();
                addChildToTree(node, leftChild);
                if (token.tokenType.equals(TokenType.NUMBER) || token.tokenType.equals(TokenType.IDENTIFIER)){
                    label = token.getText();
                    addChildToTree(currentNode, label);
                    token = scanner.nextToken();
                    if (token.tokenType.equals(TokenType.RIGHT_PAR)){
                        token = scanner.nextToken();
                        if (token.tokenType.equals(TokenType.LEFT_CURLY)){
                            token = scanner.nextToken();
                            Ident();
                            addChildToTree(currentNode, "body");
                            if (token.getTokenType().equals(TokenType.RIGHT_CURLY)) {
                                token = scanner.nextToken();
//                                System.out.println( token.getText());
//                                IfChecker();
                            }
                            else {
                                System.out.println("[ERROR CODE 6] Right Curly Parenthesis Error: '}' is missing.");
//                                throw new Exception("[ERROR CODE 6] Right Curly Parenthesis Error: '}' is missing. " + token.getText());
                                System.exit(0);
                            }
                        }
                        else {
                            System.out.println("[ERROR CODE 5] Left Curly Error: '{' is missing.");
                        }
                        // It is not necessary i guess. I selected the curly parenthesis..
                        if ( token.getTokenType().equals(TokenType.BEGIN)){
                            token = scanner.nextToken();
                            Ident();
//                            System.out.println( token.getText());
//                            Ident();
                            if (token.getTokenType().equals(TokenType.END_WHILE)) {
//                                System.out.println( token.getText());
                                token = scanner.nextToken();

                            }
                            else {
                                System.out.println("[ERROR CODE 6] Right Curly Parenthesis Error: '}' is missing.");
//                                throw new Exception("[ERROR CODE 6] Right Curly Parenthesis Error: '}' is missing. " + token.getText());
                                System.exit(0);
                            }
                        }
                        if ( token.getTokenType().equals(TokenType.THEN)){
                            token = scanner.nextToken();
                            Ident();
//                            System.out.println( token.getText());
//                            Ident();
                            if (token.getTokenType().equals(TokenType.END_IF)) {
//                                System.out.println( token.getText());
                                token = scanner.nextToken();

                            }
                            else {
                                System.out.println("[ERROR CODE 6] Right Curly Parenthesis Error: '}' is missing.");
//                                throw new Exception("[ERROR CODE 6] Right Curly Parenthesis Error: '}' is missing. " + token.getText());
                                System.exit(0);
                            }
                        }
                    }
                    else {
                        System.out.println("[ERROR CODE 4] Right Parenthesis Error: ')' is missing.");
//                        throw new Exception("[ERROR CODE 4] Right Parenthesis Error: ')' is missing. " + token.getText());
                    }
                }
                else {
                    System.out.println("[ERROR CODE 8] Error there are no Number please use..");
//                    throw new Exception("[ERROR CODE 8] Error there are no Number please use. " + token.getText());
                }
                node = this.Assignment();
            }
            else {
                System.out.println("[ERROR CODE 9] Error there are no Identifier please use..");
//                throw new Exception("[ERROR CODE 9] Error there are no Identifier please use. " + token.getText());
            }
            System.exit(0);
            break;
        }
        return node;
    }

    //This is our Identifier method
    public Node Ident() throws Exception {
//        Expr_Tail();
        if(token.getTokenType().equals(TokenType.IDENTIFIER)){
            token = scanner.nextToken();
            if(token.getTokenType().equals(TokenType.EQUAL)){
                token = scanner.nextToken();
                if (token.getTokenType().equals(TokenType.NUMBER) || token.getTokenType().equals(TokenType.IDENTIFIER)){
                    token = scanner.nextToken();
                    if (token.getTokenType().equals(TokenType.PLUS) || token.getTokenType().equals(TokenType.MINUS)
                            || token.getTokenType().equals(TokenType.MULT) || token.getTokenType().equals(TokenType.DIVIDE))
                    {
                        token = scanner.nextToken();
                        if (token.getTokenType().equals(TokenType.NUMBER) || token.getTokenType().equals(TokenType.IDENTIFIER)) {
                            token = scanner.nextToken();
                            if (token.getTokenType().equals(TokenType.SEMI_COLON)) {
                                token = scanner.nextToken();
                            }
                            else {
                                System.out.println("[ERROR CODE 1] Semicolon Error: ';' is missing.");
//                                throw new Exception("[ERROR CODE 1] Semicolon Error: ';' is missing. " + token.getText());
                                System.exit(0);
                            }
                        }
                        else {
                            System.out.println("[ERROR CODE 2] Error there is no Number or Identifier please use..");
//                            throw new Exception("[ERROR CODE 2] Error there is no Number or Identifier please use. " + token.getText());
                        }
                    }
                    else {
                        System.out.println("[ERROR CODE 13] Error there is no expression or term please use..");
//                        throw new Exception("[ERROR CODE 13] Error there is no expression or term please use. " + token.getText());
                    }
                    if (token.getTokenType().equals(TokenType.SEMI_COLON)) {
                        token = scanner.nextToken();
                    }
                }
                else if (token.getTokenType().equals(TokenType.NUMBER) || token.getTokenType().equals(TokenType.IDENTIFIER)) {
                    token = scanner.nextToken();
                    if (token.getTokenType().equals(TokenType.SEMI_COLON)) {
                        token = scanner.nextToken();
                    }
                    else {
                        System.out.println("[ERROR CODE 1] Semicolon Error: ';' is missing.");
//                        throw new Exception("[ERROR CODE 1] Semicolon Error: ';' is missing. " + token.getText());
                        System.exit(0);
                    }
                }
                else {
                    System.out.println("[ERROR CODE 2] Error there is no Number or Identifier please use..");
//                    throw new Exception("[ERROR CODE 2] Error there is no Number or Identifier please use. " + token.getText());
                    System.exit(0);
                }
            }
            else {
                System.out.println("[ERROR CODE 7] Equal Sign Error: '=' is missing.");
//                throw new Exception("[ERROR CODE 7] Equal Sign Error: '=' is missing. " + token.getText());
                System.exit(0);
            }
        }
        else if(token.getTokenType().equals(TokenType.IN)){
            In();
        }
        else if(token.getTokenType().equals(TokenType.OUT)){
            Out();
        }
        return node;
    }

    //This is our Expression Tail method '+', '-' (Addition - Subtraction) for arithmetic expression
    public Node Expr_Tail() throws Exception {
        T();
        while (check){
            if (token.tokenType.equals(TokenType.PLUS) || token.tokenType.equals(TokenType.MINUS)) {
                token = scanner.nextToken();
                if (token.tokenType.equals((TokenType.IDENTIFIER)) || token.tokenType.equals((TokenType.NUMBER))){
                    token = scanner.nextToken();
                    if (token.tokenType.equals((TokenType.SEMI_COLON))){
                        token = scanner.nextToken();
                    }
                    else{
                        System.out.println("[ERROR CODE 1] Error! There is no ';' please use..");
//                        throw new Exception("[ERROR CODE 1] Semicolon Error: ';' is missing. " + token.getText());
                    }
                }
                else{
                    System.out.println("[ERROR CODE 2] Error there are no Identifier or Number please use..");
//                    throw new Exception("[ERROR CODE 2] Error there are no Identifier or Number please use. " + token.getText());
                }
            }
            else{
                System.out.println("[ERROR CODE 10] Error there are no '+' or '-' please use..");
//                throw new Exception("[ERROR CODE 10] Error there are no '+' or '-' please use. " + token.getText());
            }
            break;
        }
        return node;
    }

    //This is our Term method (Multiplication - Division)
    public Node T() throws Exception {
        Factor();
        while (check){
            if (token.tokenType.equals((TokenType.IDENTIFIER))){
                token = scanner.nextToken();
                if (token.tokenType.equals((TokenType.EQUAL))){
                    token = scanner.nextToken();
                    if (token.tokenType.equals((TokenType.IDENTIFIER)) || token.tokenType.equals((TokenType.NUMBER))){
                        token = scanner.nextToken();
                        Term_Tail();
                    }
                    else {
                        System.out.println("[ERROR CODE 2] Error there are no Identifier or Number please use..");
//                        throw new Exception("[ERROR CODE 2] Error there are no Identifier or Number please use. " + token.getText());
                    }
                }
                else {
                    System.out.println("[ERROR CODE 7] Error there is no '=' please use..");
//                    throw new Exception("[ERROR CODE 7] Error there is no '=' please use. " + token.getText());
                    System.exit(0);
                }
            }
            else {
                System.out.println("[ERROR CODE 9] Error please use Identifier..");
//                throw new Exception("[ERROR CODE 9] Error please use Identifier. " + token.getText());
                System.exit(0);
            }
            System.exit(0);
            break;
        }
        return node;
    }

    // '*' or '/' expression in here
    public Node Term_Tail() throws Exception {
        if (token.tokenType.equals(TokenType.MULT) || token.tokenType.equals(TokenType.DIVIDE)) {
            token = scanner.nextToken();
            if (token.tokenType.equals((TokenType.IDENTIFIER)) || token.tokenType.equals((TokenType.NUMBER))){
                token = scanner.nextToken();
                if (token.tokenType.equals((TokenType.SEMI_COLON))){
                    token = scanner.nextToken();
                    Factor();
                }
                else{
                    System.out.println("[ERROR CODE 1] Error! There is no ';' please use..");
//                    throw new Exception("[ERROR CODE 1] Error! There is no ';' please use. " + token.getText());
                }
            }
            else{
                System.out.println("[ERROR CODE 2] Error there are no Identifier or Number please use..");
//                throw new Exception("[ERROR CODE 2] Error there are no Identifier or Number please use. " + token.getText());
            }
        }
        else{
            System.out.println("[ERROR CODE 11] Error there are no '*' or '/' please use..");
//            throw new Exception("[ERROR CODE 11] Error there are no '*' or '/' please use. " + token.getText());
        }
        return node;
    }

    //Factor could be identifier, exp, integer..
    //Factor shows up inside arithmetic expression except for the operators.
    public Node Factor() throws Exception {
//        Exp();
        if(token.tokenType.equals(TokenType.NUMBER)) {
            new NumberToken(token.source);
            System.out.println("Number" + " " + token.getText());
            token = scanner.nextToken();
            if (token == null){
                System.out.println("Integer error..");
                System.exit(0);
            }
        }
        else if(token.tokenType.equals(TokenType.LEFT_PAR)) {
            Exp();
            System.out.println("Identifier" + " " + token.getText());
            token = scanner.nextToken();
            if (token.tokenType.equals(TokenType.RIGHT_PAR)) {
                token = scanner.nextToken();
            }
            else if (token == null) {
                System.out.println("Identifier error..");
                System.exit(0);
            }
        }
        else if(token.tokenType.equals(TokenType.IDENTIFIER)) {
            new IdentifierToken(token.source);
            System.out.println("Identifier" + " " + token.getText());
            token = scanner.nextToken();
             if (token == null){
                 System.out.println("Identifier error..");
                 System.exit(0);
             }
        }
        else {
            System.out.println("Error occur..");
        }
        return node;
    }

    public Node Boolean() throws Exception {
        if (token != null) {
    //            token = scanner.nextToken();
            if (token.tokenType.equals(TokenType.EQUAL) || token.tokenType.equals(TokenType.EQUAL2) ||
                    token.tokenType.equals(TokenType.LESS_THAN) || token.tokenType.equals(TokenType.GREATER_THAN) ||
                    token.tokenType.equals(TokenType.LESS_THAN_EQUAL) || token.tokenType.equals(TokenType.GREATER_THAN_EQUAL) ||
                    token.tokenType.equals(TokenType.NOT_EQUAL))
            {
                token = scanner.nextToken();
            }
            else {
                System.out.println("[ERROR CODE 12] Error boolean signs can not be empty..");
//                throw new Exception("[ERROR CODE 12] Error boolean signs can not be empty. " + token.getText());
                System.exit(0);
            }
        }
        return node;
    }

    // This is for in (input)
    // Actually, my aim was to compile the input I received from the user in txt according to
    // my program and give the output with out, but I could not do it.
    public Node In() throws Exception {
        // [THOSE ARE FROM PHASE 2 COMPILER]
//        token = scanner.nextToken();
//        if (token.getTokenType().equals(TokenType.NUMBER) || token.getTokenType().equals(TokenType.IDENTIFIER)){
//            token = scanner.nextToken();
//            if (token.getTokenType().equals(TokenType.SEMI_COLON)){
//                token = scanner.nextToken();
//            }
//            else {
//                System.out.println("[ERROR CODE 1] Semicolon Error: ';' is missing.");
////                throw new Exception("[ERROR CODE 1] Semicolon Error: ';' is missing. " + token.getText());
//                System.exit(0);
//            }
//        }
//        else {
//            System.out.println("[ERROR CODE 2] Error there is no Number or Identifier please use..");
////            throw new Exception("[ERROR CODE 2] Error there is no Number or Identifier please use. " + token.getText());
//            System.exit(0);
//        }
        // [THIS IS THE NEWEST VERSION OF THIS METHOD]
        System.out.println("-----------------------------------------------------");
        System.out.println("\nPlease enter a value: ");
        int val = sc.nextInt();
        map.put(token.getText(), val); //This map from the Node class.
        return node;
    }

    // This is for out (output)
    public Node Out() throws Exception {
        // [THOSE ARE FROM PHASE 2 COMPILER]
//        token = scanner.nextToken();
//        if (token.getTokenType().equals(TokenType.NUMBER) || token.getTokenType().equals(TokenType.IDENTIFIER)){
//            token = scanner.nextToken();
//            if (token.getTokenType().equals(TokenType.SEMI_COLON)){
//                token = scanner.nextToken();
//            }
//            else {
//                System.out.println("[ERROR CODE 1] Semicolon Error: ';' is missing.");
////                throw new Exception("[ERROR CODE 1] Semicolon Error: ';' is missing. " + token.getText());
//                System.exit(0);
//            }
//        }
//        else {
//            System.out.println("[ERROR CODE 2] Error there is no Number or Identifier please use..");
////            throw new Exception("[ERROR CODE 2] Error there is no Number or Identifier please use. " + token.getText());
//            System.exit(0);
//        }
        // [THIS IS THE NEWEST VERSION OF THIS METHOD]
        System.out.println("-----------------------------------------------------");
        System.out.printf("Output of the given program is: ");
        String leftChild = currentNode.children.get(0).children.get(1).label;
        int left = Integer.parseInt(leftChild);
        map.put(token.getText(), left);
        String rightChild = currentNode.children.get(0).children.get(2).children.get(0).label;
        int right = Integer.parseInt(rightChild);
        map.put(token.getText(), right);
        if (currentNode.children.get(0).children.get(2).label.contains(TokenType.PLUS.getText())){
            System.out.print(right + left);
        }
        else if (currentNode.children.get(0).children.get(2).label.contains(TokenType.MINUS.getText())){
            System.out.print(left - right);
        }
        else if (currentNode.children.get(0).children.get(2).label.contains(TokenType.MULT.getText())){
            System.out.print(left * right);
        }
        else if (currentNode.children.get(0).children.get(2).label.contains(TokenType.DIVIDE.getText())){
            System.out.print(left / right);
        }
        else {
            return null;
        }
        return node;
    }

    public Node IfChecker() throws Exception {
        if(token.getTokenType().equals(TokenType.IF)){
            token = scanner.nextToken();
            if(token.getTokenType().equals(TokenType.LEFT_PAR)){
                token = scanner.nextToken();
                Exp();
            }
            else {
                System.out.println("[ERROR CODE 3] Left Parenthesis Error: '(' is missing.");
//                throw new Exception("[ERROR CODE 3] Left Parenthesis Error: '(' is missing " + token.getText());
                System.exit(0);
            }
        }
        else{
            System.out.println("[ERROR CODE 16] If statement is missing.");
//            throw new Exception("[ERROR CODE 16] If statement is missing. " + token.getText());
            System.exit(0);
        }
        return node;
    }

    //This is not necessary i guess.
    public Node Assignment() throws Exception {
        token = scanner.nextToken();
        if (token.getTokenType().equals(TokenType.EQUAL)){
            token = scanner.nextToken();
        }
        else {
            System.out.println("[ERROR CODE 7] Equal Sign Error: '=' is missing.");
//            throw new Exception("[ERROR CODE 7] Equal Sign Error: '=' is missing. " + token.getText());
            System.exit(0);
        }
        return node;
    }

    public Node Ident(String identifier){
        addChildToTree(node, identifier);
        return node;
    }

    public Node Integer(String num){
        addChildToTree(node, num);
        return node;
    }

    // ɛ (epsilon) means when no other production can be used. The semicolon does not much the terminal expr, so production
    // with body expr can not apply. In fact, the procedure returns without changing the semicolon or doing anything
    // else. Doing nothing corresponds to applying an epsilon.
    void Epsilon(){
        if (token.tokenType.equals(TokenType.SEMI_COLON)){
            token = scanner.nextToken();
        }
        else {
//            token.tokenType.equals(TokenType.END_OF_FILE);
            new EOFToken(token.source);
        }
    }
}