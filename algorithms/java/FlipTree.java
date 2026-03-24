import java.util.Deque;
import java.util.ArrayDeque;

public class FlipTree {

  static void flipTree(Node root) {
    Deque<Node> queue = new ArrayDeque<>();
    queue.add(root);
    while (queue.size() > 0) {
      // Get the next node from the queue and swap it's children
      Node cur = queue.remove();
      Node temp = cur.left;
      cur.left = cur.right;
      cur.right = temp;

      // Add any children to the queue.
      if (cur.left != null) {
        queue.add(cur.left);
      }
      if (cur.right != null) {
        queue.add(cur.right);
      }
    }
  }
  
  static void printSpaces(int level) {
      for (int i = 0; i < level; i++) {
        System.out.printf(" ");
      }
  }

  static void printTree(Node root) {
    Deque<Node> queue = new ArrayDeque<>();
    queue.add(root);
    int curLevel = 0;

    while (queue.size() > 0) {
      Node cur = queue.remove();
      if (cur.level > curLevel) {
        System.out.println();
        curLevel = cur.level;
      }
      printSpaces(curLevel);
      System.out.printf("%s", cur.val);
      printSpaces(curLevel);

      if (cur.left != null) {
        cur.left.level = cur.level+1;
        queue.add(cur.left);
      }
      if (cur.right != null) {
        cur.right.level = cur.level+1;
        queue.add(cur.right);
      }
    }
  }

  public static void main(String[] args) {
    
Node a = new Node("a");
Node b = new Node("b");
Node c = new Node("c");
Node d = new Node("d");
Node e = new Node("e");
Node f = new Node("f");
Node g = new Node("g");
Node h = new Node("h");

a.left = b;
a.right = c;
b.left = d;
b.right = e;
c.right = f;
e.left = g;
e.right = h;

    printTree(a);
    flipTree(a);
    System.out.println("====");
    printTree(a);
  }

  public static class Node {
    String val;
    Node left;
    Node right;
    int level;
    public Node(String val) {
      this.val = val;
    }
  }
}
