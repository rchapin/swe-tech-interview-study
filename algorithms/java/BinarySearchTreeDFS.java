
import java.util.Deque;
import java.util.List;
import java.util.ArrayList;
import java.util.ArrayDeque;

public class BinarySearchTreeDFS {

    public static List<Node> preOrder(Node rootNode) {
        System.out.println("-- Preorder Traversal --");
        List<Node> retVal = new ArrayList<>();
        Deque<Node> stack = new ArrayDeque<>();

        // Add the node provided to the stack and start the traversal.
        stack.push(rootNode);
        retVal.add(rootNode);
        rootNode.visited = true;

        while (stack.size() > 0) {
            // Get a reference to the topmost item in the stack
            Node currNode = stack.peek();
            // What is the least valued, unvisited child of this node?
            Node leastChild = currNode.getLeast();
            if (leastChild != null) {
                // Mark it as visited and add it to the stack
                System.out.println("leastChild of node " + currNode + "=" + leastChild);
                retVal.add(leastChild);
                stack.push(leastChild);
                leastChild.visited = true;
                continue;
            } else {
                // Pop the current node off the stack
                System.out.println("node " + currNode + " does not have any unvisited children");
                stack.pop();
            }
        }
        return retVal;
    }

    public static List<Node> inOrder(Node rootNode) {
        System.out.println("-- Inorder Traversal --");
        List<Node> retVal = new ArrayList<>();
        Deque<Node> stack = new ArrayDeque<>();

        // Add the node provided to the stack and start the traversal.
        // Do not yet visit the node
        stack.push(rootNode);

        while (stack.size() > 0) {
            // Get a reference to the topmost item in the stack
            Node curNode = stack.peek();
            if (curNode.left != null && !curNode.left.visited) {
                // Add that node to the stack
                System.out.println("pushing curNode.left = " + curNode.left);
                stack.push(curNode.left);
                continue;
            }
            // If we do not have a left node, visit the current node
            retVal.add(curNode);
            curNode.visited = true;
            // Pop the current node off the stack
            stack.pop();
            if (curNode.right != null && !curNode.right.visited) {
                System.out.println("pushing curNode.right = " + curNode.right);
                stack.push(curNode.right);
            }
        }

        return retVal;
    }

    public static List<Node> postOrder(Node rootNode) {
        System.out.println("-- Postorder Traversal --");
        List<Node> retVal = new ArrayList<>();
        Deque<Node> stack = new ArrayDeque<>();

        // Add the root node to the stack but do not visit it yet
        stack.push(rootNode);

        while (stack.size() > 0) {
            // Get a reference to the topmost item in the stack
            Node curNode = stack.peek();
            System.out.println("Examining curNode=" + curNode);
            if (curNode.left != null && !curNode.left.visited) {
                System.out.println("Found left node=" + curNode.left);
                stack.push(curNode.left);
                continue;
            }
            if (curNode.right != null && !curNode.right.visited) {
                System.out.println("Found right node=" + curNode.left);
                stack.push(curNode.right);
                continue;
            }
            // Since we have already processed both the left and right trees
            // of the current node, visit it and then pop it off the stack
            System.out.println("Visiting curNode=" + curNode);
            retVal.add(curNode);
            curNode.visited = true;
            stack.pop();
        }

        return retVal;
    }

    public static void resetNodes(List<Node> nodes) {
        for (Node node : nodes) {
            node.visited = false;
        }
    }

    public static void main(String[] args) {
        /*
            Build the following tree
    
                5
              /   \
             3     6
           /   \    \
          2     4    7
        */
    
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        List<Node> nodes = new ArrayList<>();
        nodes.add(n2);
        nodes.add(n3);
        nodes.add(n4);
        nodes.add(n5);
        nodes.add(n6);
        nodes.add(n7);
        n3.left = n2;
        n3.right = n4;
        n6.right = n7;
        n5.left = n3;
        n5.right = n6;

        List<Node> preOrder = BinarySearchTreeDFS.preOrder(n5);
        resetNodes(nodes);
        List<Node> inOrder = BinarySearchTreeDFS.inOrder(n5);
        resetNodes(nodes);
        List<Node> postOrder = BinarySearchTreeDFS.postOrder(n5);
        resetNodes(nodes);

        System.out.println("preOrder = " + preOrder);
        System.out.println("inOrder = " + inOrder);
        System.out.println("postOrder = " + postOrder);
    }

    public static class Node implements Comparable<Node> {
        Node left;
        Node right;
        int value;
        boolean visited;

        public Node getLeast() {
            Node l = null;
            Node r = null;
            if (left != null && !left.visited) {
                l = left;
            }
            if (right != null && !right.visited) {
                r = right;
            }
            if (l != null && r != null) {
                return l.compareTo(r) < 0 ? l : r;
            }
            return l != null ? l : r;
        }

        public Node(int value) {
            this.value = value;
        }

        @Override
        public int compareTo(Node other) {
            return this.value - other.value;
        }

        @Override
        public String toString() {
            return "" + value;
        }
    }
}
