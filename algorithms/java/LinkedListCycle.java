import java.util.Set;
import java.util.HashSet;

public class LinkedListCycle<T> {

    public static <T> boolean linkedListCycle(Node<T> head) {
        Set<Node<T>> visited = new HashSet<>(); 
        Node<T> curr = head;

        while (curr != null) {
            if (visited.contains(curr)) {
                // We have already visited this node and this means that there
                // is a cycle in this list.
                return true;
            }
            visited.add(curr);
            curr = curr.next;
        }

        return false;
    }
    

    public static void main(String[] args) {
        Node<Character> a = new Node<>('a');
        Node<Character> b = new Node<>('b');
        Node<Character> c = new Node<>('c');
        Node<Character> d = new Node<>('d');

        a.next = b;
        b.next = c;
        c.next = d;
        d.next = b;
        System.out.println(linkedListCycle(a));

        Node<Character> q = new Node<>('q');
        Node<Character> r = new Node<>('r');
        Node<Character> s = new Node<>('s');
        Node<Character> t = new Node<>('t');
        Node<Character> u = new Node<>('u');
        q.next = r;
        r.next = s;
        s.next = t;
        t.next = u;
        u.next = q;
        System.out.println(linkedListCycle(q));

        a.next = b;
        b.next = c;
        c.next = d;
        d.next = null;
        System.out.println(linkedListCycle(a));
         
    }

    public static class Node<T> {
        T val;
        Node<T> next;

        public Node(T val) {
            this.val = val;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o instanceof Node<?>) {
                Node<?> otherNode = (Node<?>)o;
                if (otherNode.val.equals(val)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public int hashCode() {
            return val.hashCode();
        }
    }
}
