import java.util.ArrayList;
import java.util.Stack;

//@AUTHOR: GORKEM TOPRAK
//DATE: June 7, 2021 Monday

// That method will be extended to the n-ary tree. The concept is simple: for each node, we must traverse all of
// its children (from left to right) before visiting the node itself.
// If I do not misunderstand this part, we will do it in the form of a pre-order.
// It will go to our tree in pre-order order and print all the children in that order. In addition, I made the post-order version
// at first, you can find it below. I also got this code from geeksforgeeks.org as a reference.

public class DFT {

    int currentRootIndex = 0;
    Stack<Pair> stack = new Stack<Pair>();
    ArrayList<String> postorderTraversal = new ArrayList<String>();

    // Iterative post-order traversal is performed with this function.
    public ArrayList<String> postorder(Node root) {
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(new Pair(root, currentRootIndex)); //Push the root and it's index into the stack
                currentRootIndex = 0;
                // If root doesn't have any children's that
                // we are already at the left most node, so we will mark root as null
                if (root.children.size() >= 1) {
                    root = root.children.get(0);
                }
                else {
                    root = null;
                }
                continue;
            }
            Pair temp = stack.pop(); // Pop the top of the stack and add it to our answer
            postorderTraversal.add(temp.node.label);

            // Repeatedly we will the pop all the elements from the stack till popped
            // element is last children of top of the stack.
            while (!stack.isEmpty() && temp.childrenIndex == stack.peek().node.children.size() - 1) {
                temp = stack.pop();
                postorderTraversal.add(temp.node.label);
            }
            // If stack is not empty, then simply assign the root to the next children of top of stack's node
            if (!stack.isEmpty()) {
                root = stack.peek().node.children.get(temp.childrenIndex + 1);
                currentRootIndex = temp.childrenIndex + 1;
            }
        }

        return postorderTraversal;
    }

    // Iterative pre-order traversal is performed with this function.
    public Stack<Node> preorder(Node root) {
        Stack<Node> nodes = new Stack<>();

        // push the current node onto the stack
        nodes.push(root);

        // Loop while the stack is not empty
        while (!nodes.isEmpty())
        {
            // Store the current node and pop
            // it from the stack
            Node node = nodes.pop();

            // Current node has been travarsed
            if (node != null) {
                System.out.print(node.label + " ");
                for(int i = node.children.size() - 1; i >= 0; i--) { // Store all the children of current node from right to left.
                    nodes.add(node.children.get(i));
                }
            }
        }
        return nodes;
    }
}

// Node and its index are pushed into the stack with the help of this class.
// I also got this code from geeksforgeeks.org as a reference.
class Pair {
    public Node node;
    public int childrenIndex;

    public Pair(Node _node, int _childrenIndex) {
        node = _node;
        childrenIndex = _childrenIndex;
    }
}

