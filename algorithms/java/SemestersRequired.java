import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class SemestersRequired {

    public static Map<Integer, List<Integer>> buildAdjList(int[][] prereqs) {
        // Build a map that's keys are the classes and the values an array of
        // prereqs for each class.
        Map<Integer, List<Integer>> retVal = new HashMap<>();
        int prereqNum = 0;
        int classNum = 0;
        for (int[] prereq : prereqs) {
            prereqNum = prereq[0];
            classNum = prereq[1];
            List<Integer> classPrereqs = retVal.get(classNum);
            if (classPrereqs == null) {
                classPrereqs = new ArrayList<>();
                retVal.put(classNum, classPrereqs);
            }
            classPrereqs.add(prereqNum);
        }
        System.out.println(retVal);
        return retVal;
    }

    public static int traverse(int classNum, Map<Integer, List<Integer>> adjList, Map<Integer, Integer> visited) {
        if (visited.containsKey(classNum)) {
            return visited.get(classNum);
        }
        // Our base case is when there is NO key for the classNum in question,
        // we simply return 1 because that is the only class that needs to be
        // taken.
        if (!adjList.containsKey(classNum)) {
            visited.put(classNum, 1);
            return 1;
        }

        int retVal = 0;
        int curResult = 0;
        for (int prereq : adjList.get(classNum)) {
            // The number of classes is the result of the traversal + 1 to include
            // the current class that we are processing
            curResult = 1 + traverse(prereq, adjList, visited); 
            // We take the maximum amount for each of the sub trees that we
            // traverse.
            retVal = Math.max(retVal, curResult);
        }
        
        // Memoize the number of prereqs that we need to take
        visited.put(classNum, retVal);
        return retVal;
    }

    public static int semestersRequired(int numCourses, int[][] prereqs) {
        Map<Integer, List<Integer>> adjList = buildAdjList(prereqs);
        if (adjList.size() == 0) {
            return 1;
        }
        // Now that we have our adjacency list, we need to do a DFS iterating
        // over all of the keys in the class, essentially, working backwards to
        // find the longest path in the graph.  In this case it is the number of
        // nodes in the path that equates the the number of semesters that are
        // required.
        //
        // The visited Map will keep track of the memoized number of classes that
        // need to be taken before we can take the class that is the key.
        Map<Integer, Integer> visited = new HashMap<>();
        int mostClasses = 0;
        int currClasses = 0;
        for (int classNum : adjList.keySet()) {
            currClasses = traverse(classNum, adjList, visited);
            mostClasses = Math.max(currClasses, mostClasses);
        }

        return mostClasses;
    }

    public static void main(String[] args) {
        int numCoursesA = 7;
        int[][] prereqsA = new int[][]{
            {4, 3},
            {3, 2},
            {2, 1},
            {1, 0},
            {5, 2},
            {5, 6},
        };
        System.out.println(semestersRequired(numCoursesA, prereqsA));

        int numCoursesB = 12;
        int[][] prereqsB = new int[0][];
        System.out.println(semestersRequired(numCoursesB, prereqsB));

        int[][] prereqsC = new int[][]{
          {3, 4},
          {3, 0},
          {3, 1},
          {3, 2},
          {3, 5},
        };
        System.out.println(semestersRequired(1, prereqsC));
    }
}
