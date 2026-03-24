import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.ArrayDeque;

public class LeafList<T> {

    public static <T> List<T> leafList(Node<T> n) {
        List<T> retVal = new ArrayList<>();
        // Create a stack and push the root node onto it
        Deque<Node<T>> stack = new ArrayDeque<>();
        stack.push(n);

        while (stack.size() > 0) {
            Node<T> current = stack.pop();
            // Check to see if this is a leaf node, if so, add its value to the
            // retVal.
            if (current.left == null && current.right == null) {
                retVal.add(current.val);
                continue;
            }
            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }

        return retVal;
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
        System.out.println(leafList(a));

        Node<Character> aa = new Node<>('a');
        Node<Character> ab = new Node<>('b');
        Node<Character> ac = new Node<>('c');
        Node<Character> ad = new Node<>('d');
        Node<Character> ae = new Node<>('e');
        Node<Character> af = new Node<>('f');
        Node<Character> ag = new Node<>('g');
        Node<Character> ah = new Node<>('h');
        Node<Character> ai = new Node<>('i');
        aa.left = ab;
        aa.right = ac;
        ab.left = ad;
        ab.right = ae;
        ac.right = af;
        ae.left = ag;
        ae.right = ah;
        af.left = ai;
        //         a
        //      /    \
        //     b      c
        //   /  \      \
        //  d    e      f
        //      / \    /   
        //     g  h   i 
        System.out.println(leafList(aa));
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
