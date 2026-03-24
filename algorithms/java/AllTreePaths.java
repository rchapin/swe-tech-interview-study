import java.util.Deque;
import java.util.LinkedList;

public class AllTreePaths<T> {

    public static <T> void appendSelf(T val, Deque<Deque<T>> agg, Deque<Deque<T>> l) {
        if (l == null) {
            return;
        }

        // For each sublist in l, append our value to the beginning of the list,
        // and then add it to the aggregate list of lists
        for (Deque<T> subList : l) {
            subList.addFirst(val);
            agg.add(subList);
        }
    } 

    public static <T> Deque<Deque<T>> allTreePaths(Node<T> n) {
        Deque<Deque<T>> retVal = new LinkedList<>();
        if (n.left == null && n.right == null) {
            Deque<T> l = new LinkedList<>();
            l.add(n.val);
            retVal.add(l);
            return retVal;    
        }

        Deque<Deque<T>> l = null;
        Deque<Deque<T>> r = null;
        if (n.left != null) {
            l = allTreePaths(n.left);
        }
        if (n.right != null) {
            r = allTreePaths(n.right);
        }

        // Add as the first element to each sub list returned the current
        // node's value and then aggregate all the sublists into the retVal
        appendSelf(n.val, retVal, l);
        appendSelf(n.val, retVal, r);
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
        System.out.println(allTreePaths(a));

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
        System.out.println(allTreePaths(aa));
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
