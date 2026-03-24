
import java.util.Map;
import java.util.HashMap;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

public class CanColor {

  static boolean canColor(Map<String, Node> graph) {
    boolean retVal = true;
    Set<String> visited = new HashSet<>();
    Deque<Node> queue = new ArrayDeque<>();
    Node startNode = graph.entrySet().iterator().next().getValue();
    startNode.color = Color.RED;
    queue.add(startNode);

    // while (visited.size() < graph.size() && queue.size() > 0) {
    while (queue.size() > 0) {
      Node cur = queue.remove();
      visited.add(cur.val);
      Color color = cur.color;

      // Check all of the neighbors of this node
      for (Node neighbor : cur.neighbors) {
        // Is the color already set for this node?
        if (neighbor.color != null) {
          // If the color is not the opposite of our current color, we can
          // conclude that we are not able to set all adjacent nodes to
          // alternating colors and we can return false;
          if (cur.color == neighbor.color) {
            return false;
          }
        } else {
          // Set the color of this neighbor node to the opposite of our
          // current color.
          neighbor.color = cur.color == Color.RED ? Color.BLUE : Color.RED;
        }
        if (!visited.contains(neighbor.val)) {
          queue.add(neighbor);
        }
      }
    }

    return retVal;
  }


  public static void main(String[] args) {

    List<Map<String, List<String>>> testData = new ArrayList<>();

    Map<String, List<String>> t1 = new HashMap<>();
    t1.put("a", Arrays.asList("b", "c", "d"));
    t1.put("b", Arrays.asList("a"));
    t1.put("c", Arrays.asList("a"));
    t1.put("d", Arrays.asList("a"));
    testData.add(t1);

    Map<String, List<String>> t2 = new HashMap<>();
    t2.put("a", Arrays.asList("b", "c", "d"));
    t2.put("b", Arrays.asList("a"));
    t2.put("c", Arrays.asList("a", "d"));
    t2.put("d", Arrays.asList("a", "c"));
    testData.add(t2);

    Map<String, List<String>> t3 = new HashMap<>();
    t3.put("h", Arrays.asList("i", "k"));
    t3.put("i", Arrays.asList("h", "j"));
    t3.put("j", Arrays.asList("i", "k"));
    t3.put("k", Arrays.asList("h", "j"));
    t3.put("q", Arrays.asList("r", "s"));
    t3.put("r", Arrays.asList("q", "s"));
    t3.put("s", Arrays.asList("r", "q"));
    testData.add(t3);

    Map<String, List<String>> t4 = new HashMap<>();
    t4.put("z", Arrays.asList());
    testData.add(t4);

    for (Map<String, List<String>> t : testData) {
      Map<String, Node> graph = new HashMap<>();
      // Iterate over list to create all of the nodes
      for (Map.Entry<String, List<String>> entry : t.entrySet()) {
        Node n = new Node(entry.getKey());
        graph.put(entry.getKey(), n);
      }
      for (Map.Entry<String, List<String>> entry : t.entrySet()) {
        Node n = graph.get(entry.getKey());
        for (String neighbor : entry.getValue()) {
          // Put the node of that value into the neighborlist
          n.neighbors.add(graph.get(neighbor));
        }
      }

      System.out.printf("%ngraph=%s%n%b%n", graph, canColor(graph));
    }
  }

  public static class Node {
    String val;
    List<Node> neighbors;
    Color color;
    public Node(String val) {
      this.val = val;
      neighbors = new ArrayList<>();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      for (Node n : neighbors) {
        sb.append(" " + n.val);
      }
      return String.format("[val=%s, neighbors=%s, color=%s]", val, sb.toString(), color);
      
    }
  }

  public enum Color {
    BLUE,
    RED;
  }
}
