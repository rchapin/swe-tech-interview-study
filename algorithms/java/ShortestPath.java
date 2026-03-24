import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;

public class ShortestPath {

    public static Map<String, List<String>> buildAdjList(String graphName, String[][] edges) {
        Map<String, List<String>> retVal = new HashMap<>();
        for (String[] edge : edges) {
            // Ensure that we have an element in the map for the nodes
            // described in this undircted edge
            for (String node : edge) {
                if (!retVal.containsKey(node)) {
                    // We have not yet recorded any edges for this node
                    List<String> l = new ArrayList<>();
                    retVal.put(node, l);
                }
            }
            retVal.get(edge[0]).add(edge[1]);
            retVal.get(edge[1]).add(edge[0]);
        }
        System.out.printf("graph=%s, %s%n", graphName, retVal);
        return retVal;
    }

    public static int shortestPath(Map<String, List<String>> graph, String src, String dest) {
        // Execute a BFS on this graph.
        // We need a queue and a set to keep track of where were are in the
        // search and whether or not we have already visited a given node,
        // respectfully.
        Deque<Node> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        // Add the start node to the queue and begin traversing through the tree.
        queue.add(new Node(src, 0));

        while (queue.size() > 0) {
            // Pop an item off the queue and mark it as visited
            Node currNode = queue.remove();
            visited.add(currNode.id);
            if (currNode.id.equals(dest)) {
                // Return the distance defined in this node
                return currNode.distance;
            }
            // If we have not found our destination yet, create and add a node
            // for each neighbor that has not yet been visited.
            for (String neighbor : graph.get(currNode.id)) {
                if (!visited.contains(neighbor)) {
                    // We increment the currNode distance to indicate that
                    // with this new neighbor we are one more hop away from
                    // our src node.
                    queue.add(new Node(neighbor, currNode.distance + 1));
                }
            }
        }

        return 0;
    }

    public static void runSearch(String graphName, Map<String, List<String>> graph, String src, String dest) {
        System.out.printf(
            "Shortest distance between src=%s, dest=%s, for graph=%s is %d%n",
            src,
            dest,
            graph.toString(),
            shortestPath(graph, src, dest));
    }

    public static void main(String[] args) {
        String[][] edges1 = {
            {"w", "x"},
            {"x", "y"},
            {"z", "y"},
            {"z", "v"},
            {"w", "v"}
        };
        String[][] edges2 = {
            {"a", "b"},
            {"a", "i"},
            {"i", "d"},
            {"b", "c"},
            {"d", "c"},
            {"d", "g"},
            {"d", "e"},
            {"e", "f"},
            {"f", "g"},
            {"g", "h"}
        };
        String[][] edges3 = {
            {"a", "c"},
            {"a", "b"},
            {"c", "b"},
            {"c", "d"},
            {"b", "d"},
            {"e", "d"},
            {"g", "f"},
        };
        Map<String, List<String>> graph1 = buildAdjList("Graph1", edges1);
        runSearch("Graph1", graph1, "w", "z");
        Map<String, List<String>> graph2 = buildAdjList("Graph2", edges2);
        runSearch("Graph2", graph2, "a", "h");
        Map<String, List<String>> graph3 = buildAdjList("Graph3", edges3);
        runSearch("Graph3", graph3, "a", "h");
    }

    public static class Node {
        final String id;
        int distance;

        public Node(String id, int distance) {
            this.id = id;
            this.distance = distance;
        }
    }
}
