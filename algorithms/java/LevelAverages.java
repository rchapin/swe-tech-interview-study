import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Queue;
import java.util.ArrayDeque;

public class LevelAverages {

  public static double[] levelAverages(Node root) {
    // For each node that we find, we will create another Node object and
    // in it we will set its left and right and level.  The root node will
    // start with level 0 and each successive set of children will get their
    // level set to parentNode.level + 1.
    Queue<Node> queue = new ArrayDeque<>();

    // We will store the values of each of the elements in a given level in
    // a Map keyed by the level "id".
    Map<Integer, List<Integer>> levels = new HashMap<>();

    Node startNode = new Node(root.val);
    startNode.left = root.left;
    startNode.right = root.right;
    startNode.level = 0;
    queue.add(startNode);

    while (queue.size() != 0) {
      // Remove a node from the queue and then check to see if it has any
      // children.  If it does, for each child create a new Node, add the
      // val and then set the level to be that of the parent + 1.
      Node cur = queue.remove();

      // Add the value of this node to the correct list in the levels Map
      List<Integer> vals = levels.get(cur.level);
      if (vals == null) {
        vals = new ArrayList<>();
        levels.put(cur.level, vals);
      }
      vals.add(cur.val);

      List<Node> children = new ArrayList<>();
      if (cur.left != null) {
        children.add(cur.left);
      }
      if (cur.right != null) {
        children.add(cur.right);
      }
      for (Node child : children) {
        Node n = new Node(child.val);
        n.left = child.left;
        n.right = child.right;
        n.level = cur.level + 1;
        queue.add(n);
      }
    }

    System.out.println(levels);

    // Now calculate the averages for each of the keys in the levels map
    double[] retVal = new double[levels.size()];
    for (Map.Entry<Integer, List<Integer>> entry : levels.entrySet()) {
      int level = entry.getKey();
      double total = 0D;
      for (Integer i : entry.getValue()) {
        total += (double)i; 
      }
      double avg = total/entry.getValue().size();
      retVal[level] = avg;
    }

    return retVal;
  }

  public static String arrToString(double[] arr) {
    StringBuilder buf = new StringBuilder();
    buf.append("[");
    for (double d : arr) {
      buf.append(Double.toString(d));
      buf.append(", ");
    }
    buf.setLength(buf.length() - 2);
    buf.append("]");
    return buf.toString();
  }

  public static void main(String[] args) {

    Node a = new Node(3);
    Node b = new Node(11);
    Node c = new Node(4);
    Node d = new Node(4);
    Node e = new Node(-2);
    Node f = new Node(1);
    a.left = b;
    a.right = c;
    b.left = d;
    b.right = e;
    c.right = f;

    // a b c d e f g
    // g h i j k l m

    Node g = new Node(5);
    Node h = new Node(11);
    Node i = new Node(54);
    Node j = new Node(20);
    Node k = new Node(15);
    Node l = new Node(1);
    Node m = new Node(3);
    g.left = h;
    g.right = i;
    h.left = j;
    h.right = k;
    k.left = l;
    k.right = m;

    List<Node> roots = new ArrayList<>();
    roots.add(a);
    roots.add(g);

    for (Node root : roots) {
      System.out.printf("Averages for root=%s %s%n", root, arrToString(levelAverages(root)));
    }
  }

  public static class Node {
    int val;
    Node left;
    Node right;
    int level;

    public Node(int val) {
      this.val = val;
    }

    @Override
    public String toString() {
      return "val=" + Integer.toString(val) + ", level=" + level;
    }
  }
}
