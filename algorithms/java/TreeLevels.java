import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Deque;
import java.util.ArrayDeque;

public class TreeLevels<T> {

    public static <T> Map<Integer, List<T>> treeLevels(Node<T> n) {
        Map<Integer, List<T>> retVal = new HashMap<>();
        // Create a queue and add the root node to it designating it as the
        // 0th level.
        Deque<QueueElem<T>> queue = new ArrayDeque<>();
        queue.add(new QueueElem<T>(n, 0));

        while (queue.size() > 0) {
            // Read the next item from the queue, visit it, by adding it to the
            // correct list in the Map, and then add any children to the queue
            // with the level as current + 1.
            QueueElem<T> currentElem = queue.remove();
            int level = currentElem.level;
            List<T> list = retVal.get(level);
            if (list == null) {
                // Create one, and add it to the Map
                list = new ArrayList<>();
                retVal.put(level, list);
            }
            // Add our currentElement's value to the List for this level.
            list.add(currentElem.node.val);

            // Add any children to the queue
            if (currentElem.node.left != null) {
                queue.add(new QueueElem<T>(currentElem.node.left, level + 1));
            }
            if (currentElem.node.right != null) {
                queue.add(new QueueElem<T>(currentElem.node.right, level + 1));
            }
        }

        return retVal;
    }

/*
    public static void printResult(Map<Integer, List<Node<T>>> result) {
         
    }
    */

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
        System.out.println(treeLevels(a));

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
        System.out.println(treeLevels(aa));
    }

    public static class QueueElem<T> {
        final Node<T> node;
        final int level;
        public QueueElem(Node<T> node, int level) {
            this.node = node;
            this.level = level;
        }
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
