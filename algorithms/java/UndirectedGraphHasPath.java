import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;

public class UndirectedGraphHasPath {

    public static Map<String, List<String>> buildAdjList(String graphName, String[][] edges) {
        Map<String, List<String>> retVal = new HashMap<>();
        for (String[] edge : edges) {
            
            // Ensure that we have an element in the map for the nodes
            // described in this undircted edge
            for (String node : edge) {
                List<String> nodeEdges = retVal.get(node);
                if (nodeEdges == null) {
                    // We have not yet recorded any edges for this node
                    nodeEdges = new ArrayList<>();
                    retVal.put(node, nodeEdges);
                }
            }
            retVal.get(edge[0]).add(edge[1]);
            retVal.get(edge[1]).add(edge[0]);
        }
        System.out.printf("graph=%s, %s%n", graphName, retVal);
        return retVal;
    }

    public static boolean hasPathBFS(Map<String, List<String>> graph, String src, String dest) {
        // We need a queue and a set to keep track of where were are in the
        // search and whether or not we have already visited a given node,
        // respectfully.
        Deque<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        // Add our src node onto the queue to begin our search
        queue.add(src);

        while (queue.size() > 0) {
            String currNode = queue.remove();
            visited.add(currNode);
            // Iterate over all of the neighbors for this node
            for (String neighbor : graph.get(currNode)) {
                if (neighbor.equals(dest)) {
                    return true;
                }
                // Since this neighbor is not the destination node, we need
                // to add it to the queue to continue our search
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                }
            }
        }
        
        return false;
    }

    public static boolean hasPathDFS(Map<String, List<String>> graph, String src, String dest) {
        // We will need a stack and set to keep track of where we are in the
        // search and whether or not we have already visited a given node,
        // respectfully.
        Deque<String> stack = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        // Add our src node to the stack as the first item that we will visit.
        stack.push(src);

        while (stack.size() > 0) {
            String currNode = stack.pop();
            visited.add(currNode);
            for (String neighbor : graph.get(currNode)) {
                if (neighbor.equals(dest)) {
                    return true;
                }
                if (!visited.contains(neighbor)) {
                    stack.push(neighbor);
                }
            }
        }

        return false;
    }

    public static void runDFSearch(String graphName, Map<String, List<String>> graph, String src, String dest) {
        System.out.printf("DFS: graph=%s, src=%s, dest=%s, result=%b%n", graphName, src, dest, hasPathDFS(graph, src, dest));
    }

    public static void runBFSearch(String graphName, Map<String, List<String>> graph, String src, String dest) {
        System.out.printf("BFS: graph=%s, src=%s, dest=%s, result=%b%n", graphName, src, dest, hasPathBFS(graph, src, dest));
    }


    public static void main(String[] args) {
        String[][] edges1 = {
            {"i", "j"},
            {"k", "i"},
            // Create a cycle in the graph
            {"k", "j"},
            {"m", "k"},
            {"k", "l"},
            {"o", "n"}
        };
        Map<String, List<String>> graph1 = buildAdjList("Graph1", edges1);
        
        runBFSearch("Graph1", graph1, "i", "l");
        runDFSearch("Graph1", graph1, "i", "l");
    }
}
