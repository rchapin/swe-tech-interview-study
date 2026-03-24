import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Deque;
import java.util.ArrayDeque;

public class GraphDFTraversal {

    public static void dfsItr(Map<String, String[]> graph, String startNode) {
        // Instantiate a stack for us to use to keep track of our progress in
        // our graph.
        Deque<String> stack = new ArrayDeque<>();

        // Also instantiate a Set that we will use to keep track of which nodes
        // we have visited.
        Set<String> visited = new HashSet<>();

        // Push the start node onto the stack and start iterating there
        stack.push(startNode);

        while (stack.size() > 0) {
            // Pop an item off the stack and look to see if we have already
            // visited this node.
            String node = stack.pop();
            if (!visited.contains(node)) {
                System.out.printf("%s ", node);
                visited.add(node);
            }
            // Iterate over all of the current node's children, and for those
            // that have NOT yet been visited, add them to the stack.
            for (String neighborNode : graph.get(node)) {
                if (!visited.contains(neighborNode)) {
                    stack.push(neighborNode);
                }
            }
        }

        System.out.println();
    }

    public static void dfsRecursive(Map<String, String[]> graph, String node, Set<String> visited) {
        // Since we will be traversing the graph recursively, we will use the
        // call stack as the stack.
        if (!visited.contains(node)) {
            System.out.printf("%s ", node);
            visited.add(node);
        }
        for (String neighborNode: graph.get(node)) {
            dfsRecursive(graph, neighborNode, visited);
        }
    }

    public static void main(String[] args) {
        // Build an adjacency matric for a a directed graph.  A graph element
        // with elements in its adjacency array indicates that there is an
        // edge FROM the current node to the node in the array

        // A simple graph with only one incoming edge for any given node.
        Map<String, String[]> g1 = new HashMap<>();
        g1.put("a", new String[]{"b", "c"});
        g1.put("b", new String[]{"d"});
        g1.put("c", new String[]{"e"});
        g1.put("d", new String[]{"f"});
        g1.put("e", new String[0]);
        g1.put("f", new String[0]);

        // A graph where we have some nodes that have multiple incoming edges.
        Map<String, String[]> g2 = new HashMap<>();
        g2.put("a", new String[]{"b", "c"});
        g2.put("b", new String[]{"d"});
        g2.put("c", new String[]{"d", "e"});
        g2.put("d", new String[]{"e"});
        g2.put("e", new String[]{"f"});
        g2.put("g", new String[]{"d"});
        g2.put("f", new String[0]);

        System.out.println("Iterative traversals:");
        dfsItr(g1, "a");
        dfsItr(g2, "a");
        System.out.println("Recursive traversals:");
        dfsRecursive(g1, "a", new HashSet<String>());
        System.out.println();
        dfsRecursive(g2, "a", new HashSet<String>());
        System.out.println();
    }
}
