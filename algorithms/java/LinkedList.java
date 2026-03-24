
public class LinkedList {

    public static <T> void traverseList(Node<T> node) {
        Node<T> next = null;
        while (node != null) {
            System.out.println(node);
            node = node.next;
        }
    }

    public static <T> void recurse(Node<T> node) {
        if (node == null) {
            return;
        }
        System.out.println(node);
        recurse(node.next);
    }

    public static void main(String[] args) {
        Node<Character> d = new Node<>('d', null); 
        Node<Character> c = new Node<>('c', d); 
        Node<Character> b = new Node<>('b', c); 
        Node<Character> a = new Node<>('a', b ); 
        traverseList(a);
        System.out.println();
        recurse(a);
    }

    public static class Node<T>{
        T val;
        Node<T> next;

        public Node(T val, Node<T> next) {
            this.val = val;
            if (next != null) {
                this.next = next;
            }
        }

        @Override
        public String toString() {
            return val.toString();
        }
    }
}


