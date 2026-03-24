import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;

public class LargestUndirectedComponent {

    public static int dftraversal(Map<Integer, List<Integer>> graph, Set<Integer> visited, int node) {
        int retVal = 0;
        if (visited.contains(node)) {
            return retVal;
        }
        // Mark our node as visited and add it to the count of nodes that we
        // have seen thus far.
        visited.add(node);
        retVal++;

        for (int neighbornode : graph.get(node)) {
            retVal += dftraversal(graph, visited, neighbornode); 
        }

        return retVal;
    }

    public static int largestComponent(Map<Integer, List<Integer>> graph) {
        Set<Integer> visited = new HashSet<>();
        int retVal = Integer.MIN_VALUE;

        // Iterate over each of the nodes in the nodes Set and traverse the
        // graph, each time, we will get back the number of nodes in the
        // component of the graph that we visited.  We then check to see if
        // it is greater than any other components that we visited and if so
        // update our value.
        for (int node : graph.keySet()) {
            int currNodeCount = dftraversal(graph, visited, node);
            retVal = currNodeCount > retVal ? currNodeCount : retVal;
        }

        return retVal;
    };

    public static void runSearch(String graphName, Map<Integer, List<Integer>> graph) {
        System.out.printf("graph=%s's largest component %s is %d%n", graphName, graph, largestComponent(graph));
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
