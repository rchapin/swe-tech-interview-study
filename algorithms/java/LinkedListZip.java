
public class LinkedListZip {

    public static <T> void traverseList(Node<T> node) {
        Node<T> next = null;
        while (node != null) {
            System.out.printf("%s ", node);
            node = node.next;
        }
        System.out.println();
    }

    public static <T> Node<T> zipLists(Node<T> list1Head, Node<T> list2Head) {

        Node<T> cur1 = list1Head;
        Node<T> cur2 = list2Head;
        Node<T> tail = null;
        Node<T> prevTail = null;
        int counter = 1;

        while (true) {
            if (counter % 2 == 0) {
                // Read from list 2
                tail = cur2;
                if (cur2 != null) {
                    cur2 = cur2.next;
                }
            } else {
                // Read from list 1
                tail = cur1;
                if (cur1 != null) {
                    cur1 = cur1.next;
                }
            }

            if (prevTail != null && tail != null) {
                prevTail.next = tail;
            }
            if (prevTail == null && tail == null) {
                break;
            }
            prevTail = tail;
            counter++;
        }

        return list1Head;
    }

    public static void main(String[] args) {
        // List "1"
        Node<Character> f = new Node<>('f', null); 
        Node<Character> e = new Node<>('e', f); 
        Node<Character> d = new Node<>('d', e); 
        Node<Character> c = new Node<>('c', d); 
        Node<Character> b = new Node<>('b', c); 
        Node<Character> a = new Node<>('a', b ); 

        // List "2"
        Node<Character> n = new Node<>('n', null ); 
        Node<Character> m = new Node<>('m', n ); 

        Node<Character> head = zipLists(n, a);
        traverseList(head);
    }

    public static class Node<T>{
        T val;
        Node<T> next;

        public Node(T val) {
            this(val, null);
        }

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


