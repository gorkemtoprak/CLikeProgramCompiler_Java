import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

//@AUTHOR: GORKEM TOPRAK
//DATE: June 7, 2021 Monday

public class Node {

    public Node parent; //The parent to start the parse tree.
    public String label; //Label for storage of the children's label
    public List<Node> children; //This array will keep children.
    public HashMap<String, Integer> map = new HashMap<String, Integer>();

//    Node(Node parent, String label){
//        this.parent = parent;
//        this.label = label;
//        this.children = new ArrayList<>();
//    }

    public Node(String label){
        this.label = label;
        this.children = new ArrayList<>();
    }


    public Node addChild(Node node) {
        children.add(node);
        this.parent = node;
//        this.label = label;
        return node;
    }

    public Node getParent(){
        return parent;
    }

    public String getLabel(){
        return label;
    }

    public List<Node> getChildren() {
        System.out.println(children.get(0).label);
        return children;
    }

    // This will take the parse tree (its root node) and start interpreting it.
    // The evaluator could be implemented using a depth-first traversal (DFT) of the parse tree.
    // Evaluator will take the (root of your) parse tree created by your parser. It will do a DFT of your parse tree
    void eval(){
        map = new HashMap<String, Integer>();
        for (int i=0; i<children.size(); i++){
            children.get(i).eval();
        }
    }
}
