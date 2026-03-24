import java.util.Deque;
import java.util.LinkedList;

public class TreePathFinder<T> {

    public static <T> Deque<T> pathFinder(Node<T> n, T target) {
        Deque<T> retVal = null;
        if (n.val.equals(target)) {
            // Return a list that includes our value as a single element
            retVal = new LinkedList<>();
            retVal.addFirst(n.val);
            return retVal;
        }

        if (n.left != null) {
            retVal = pathFinder(n.left, target);
            if (retVal != null) {
                retVal.addFirst(n.val);
                return retVal;
            }
        }

        if (n.right != null) {
            retVal = pathFinder(n.right, target);
            if (retVal != null) {
                retVal.addFirst(n.val);
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

        System.out.println(pathFinder(a, 'e')); // -> [ 'a', 'b', 'e' ]


        Node<Character> aa = new Node<>('a');
        Node<Character> ab = new Node<>('b');
        Node<Character> ac = new Node<>('c');
        Node<Character> ad = new Node<>('d');
        Node<Character> ae = new Node<>('e');
        Node<Character> af = new Node<>('f');
        Node<Character> ag = new Node<>('g');
        Node<Character> ah = new Node<>('h');

aa.left = ab;
aa.right = ac;
ab.left = ad;
ab.right = ae;
ac.right = af;
ae.left = ag;
af.right = ah;

//      a
//    /   \
//   b     c
//  / \     \
// d   e     f
//    /       \
//   g         h

        System.out.println(pathFinder(aa, 'c')); // -> [ 'a', 'b', 'e' ]
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
