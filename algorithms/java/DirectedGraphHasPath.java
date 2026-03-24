import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;
import java.util.ArrayDeque;

public class DirectedGraphHasPath {

    public static boolean hasPathBFS(Map<String, String[]> graph, String src, String dest) {
        // Instantiate a queue for us to use to keep track of our progress
        // while traversing our graph.
        Queue<String> queue = new ArrayDeque<>();

        // Also instantiate a Set that we will use to keep track of which nodes
        // we have visited.
        Set<String> visited = new HashSet<>();

        // Add the start node to the queue and start iterating there
        queue.add(src);

        while (queue.size() > 0) {
            // Pop an item off the queue and look to see if we have already
            // visited this node.
            String node = queue.remove();
            if (!visited.contains(node)) {
                visited.add(node);
            }
            // Iterate over all of the current node's children and check to
            // see if it is our target node.
            for (String neighborNode : graph.get(node)) {
                if (neighborNode.equals(dest)) {
                    return true;
                }
                if (!visited.contains(neighborNode)) {
                    queue.add(neighborNode);
                }
            }
        }
        return false;
    }

    /**
    * Execute a recursive, DFS for our destination node.
    */ 
    public static boolean hasPathDFS(Map<String, String[]> graph, String src, String dest) {
        if (src.equals(dest)) {
            return true;
        }
        for (String neighbor : graph.get(src)) {
            if (hasPathDFS(graph, neighbor, dest)) {
                return true;
            }
        }
        return false;
    }

    public static void runBFSearch(String graphName, Map<String, String[]> graph, String src, String dest) {
        System.out.printf("BFS: graph=%s, src=%s, dest=%s, hasPathBFS=%b%n", graphName, src, dest, hasPathBFS(graph, src, dest));
    }

    public static void runDFSearch(String graphName, Map<String, String[]> graph, String src, String dest) {
        System.out.printf("DFS: graph=%s, src=%s, dest=%s, hasPathDFS=%b%n", graphName, src, dest, hasPathDFS(graph, src, dest));
    }

    public static void main(String[] args) {
        // Build an adjacency matric for a a directed graph.  A graph element
        // with elements in its adjacency array indicates that there is an
        // edge FROM the current node to the node in the array

        // A simple graph with only one incoming edge for any given node.
        Map<String, String[]> g1 = new HashMap<>();
        g1.put("a", new String[]{"c", "b"});
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

        runBFSearch("g1", g1, "a", "f");
        runBFSearch("g2", g2, "a", "f");
        runBFSearch("g2", g2, "a", "g");
        runDFSearch("g1", g1, "a", "f");
        runDFSearch("g2", g2, "a", "f");
        runDFSearch("g2", g2, "a", "g");

    }
}
