import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class LongestPath<T> {

    public static int longestPath(Map<Character, char[]> graph) {
        int retVal = Integer.MIN_VALUE;
        int currentLongest = 0;
        Map<Character, Integer> visited = new HashMap<>();
        for (char c : graph.keySet()) {
            currentLongest = traverse(c, visited, graph);
            retVal = Math.max(currentLongest, retVal);
        }

        return retVal;
    }

    public static int traverse(char node, Map<Character, Integer> visited, Map<Character, char[]> graph) {
        if (visited.containsKey(node)) {
            return visited.get(node);
        }
        int longestPath = 0;
        int currLongestPath = 0;
        for (char c : graph.get(node)) {
            currLongestPath = 1 + traverse(c, visited, graph);
            longestPath = Math.max(currLongestPath, longestPath);
        }
        visited.put(node, longestPath);
        return longestPath;
    }

    public static void main(String[] args) {
        Map<Character, char[]> graphA = new HashMap<>();
        graphA.put('a', new char[]{'c', 'b'});
        graphA.put('b', new char[]{'c'});
        graphA.put('c', new char[0]);
        System.out.println(longestPath(graphA));

        Map<Character, char[]> graphB = new HashMap<>();
        graphB.put('a', new char[]{'c', 'b'});
        graphB.put('b', new char[]{'c'});
        graphB.put('c', new char[]{});
        graphB.put('q', new char[]{'r'});
        graphB.put('r', new char[]{'s', 'u', 't'});
        graphB.put('s', new char[]{'t'});
        graphB.put('t', new char[]{'u'});
        graphB.put('u', new char[]{});
        System.out.println(longestPath(graphB));
    }
}
