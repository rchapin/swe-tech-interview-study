import java.util.Deque;
import java.util.ArrayDeque;

public class HowHigh<T> {

    public static <T> int howHigh(Node<T> n) {
        if (n == null)  {
            return -1;
        }
        if (n.left == null && n.right == null) {
            return 0;
        }

        int left = 0;
        int right = 0;
        if (n.left != null) {
            left = howHigh(n.left);
        }
        if (n.right != null) {
            right = howHigh(n.right);
        }

        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {
        Node<Character> a = new Node<>('a');
        Node<Character> b = new Node<>('b');
        Node<Character> c = new Node<>('c');
        Node<Character> d = new Node<>('d');
        Node<Character> e = new Node<>('e');
        Node<Character> f = new Node<>('f');

        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        c.right = f;

        //      a
        //    /   \
        //   b     c
        //  / \     \
        // d   e     f
        System.out.println(howHigh(a)); // -> 2
    }

    public static class Node<T> {
        final T val;
        Node<T> left;
        Node<T> right;
        public Node(T val) {
            this.val = val;
        }
    }
}
