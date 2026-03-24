import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;

public class UndirectedConnectedComponents {

    public static boolean bftraversal(Map<Integer, List<Integer>> graph, Set<Integer> visited, int startNode) {
        if (visited.contains(startNode)) {
            return false;
        }
        // Else, traverse the tree, and in the process we will mark all of
        // nodes in this component of the graph as visited.
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(startNode);
        while (queue.size() > 0) {
            int currNode = queue.remove();
            visited.add(currNode);
            for (int neighbor : graph.get(currNode)) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                }
            }
        }

        return true;
    }

    public static int countComponents(Map<Integer, List<Integer>> graph) {
        Set<Integer> visited = new HashSet<>();
        int retVal = 0;

        // Iterate over each of the nodes in the nodes Set and traverse the
        // graph, each time, increment the number of times that we execute a
        // traversal which will tell us now many components there are in the
        // graph.
        for (int node : graph.keySet()) {
            if (bftraversal(graph, visited, node)) {
                retVal++;
            }
        }

        return retVal;
    };

    public static void runSearch(String graphName, Map<Integer, List<Integer>> graph) {
        System.out.printf("graph=%s, %s has [%d] components%n", graphName, graph, countComponents(graph));
    }

    public static void main(String[] args) {
        Map<Integer, List<Integer>>  graph1 = new HashMap<>();
        graph1.put(0, new ArrayList<Integer>(Arrays.asList(new Integer[]{8, 1, 5})));
        graph1.put(1, new ArrayList<Integer>(Arrays.asList(new Integer[]{0})));
        graph1.put(5, new ArrayList<Integer>(Arrays.asList(new Integer[]{0, 8})));
        graph1.put(8, new ArrayList<Integer>(Arrays.asList(new Integer[]{0, 5})));
        graph1.put(2, new ArrayList<Integer>(Arrays.asList(new Integer[]{3, 4})));
        graph1.put(3, new ArrayList<Integer>(Arrays.asList(new Integer[]{2, 4})));
        graph1.put(4, new ArrayList<Integer>(Arrays.asList(new Integer[]{3, 2})));

        runSearch("Graph1", graph1);
    }
}
