import java.util.TreeSet;
import java.util.Queue;
import java.util.List;
import java.util.ArrayList;
import java.util.ArrayDeque;

public class BinarySearchTreeBFS {

    public static List<Node> bfs(Node rootNode) {
        Queue<Node> queue = new ArrayDeque<>();
        List<Node> retVal = new ArrayList<>();

        // Visit the root node and add it to the result
        rootNode.visited = true;
        retVal.add(rootNode);
        queue.add(rootNode);

        while (queue.size() > 0) {
            Node curNode = queue.peek();
            Node child = null;
            while ((child = curNode.getLeast()) != null) {
                child.visited = true;
                retVal.add(child);
                queue.add(child);
            }
            queue.remove();
        }

        return retVal;
    }

    public static void main(String[] args) {
        /*
            Build the following tree
                  1
                / | \
               2  3  4
             / |      \
            5  6        7
        */
    
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);

        n1.add(n2, n3, n4);
        n2.add(n5, n6);
        n4.add(n7);

        List<Node> result = bfs(n1);
        System.out.println("Result = " + result);
    }

    public static class Node implements Comparable<Node> {
        TreeSet<Node> nodes;
        int value;
        boolean visited;

        public void add(Node... nodes) {
            for (Node node : nodes) {
                this.nodes.add(node);
            }
        }

        public Node getLeast() {
            // Return the least valued node that has not yet been visited
            for (Node node : nodes) {
                if (!node.visited) {
                    return node;
                }
            }
            return null;
        }

        public Node(int value) {
            this.value = value;
            nodes = new TreeSet<>();
        }

        @Override
        public int compareTo(Node other) {
            return this.value - other.value;
        }

        @Override
        public int hashCode() {
            return value;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true; 
            }
            if (other == null || other.getClass() != this.getClass()) {
                return false;
            }
            Node otherNode = (Node) other;
            if (this.value == otherNode.value) {
                return true;
            }
            return false;
        }

        @Override
        public String toString() {
            return "" + value;
        }
    }
}
