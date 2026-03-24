import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Deque;
import java.util.ArrayDeque;

public class LowestCommonAncestor {

    public static char getLowestCommonAncestor(Map<Character, List<Character>> visited, char v1, char v2) {
        char retVal = '\0';
        int idx = 0;

        List<Character> v1Parents = visited.get(v1);
        List<Character> v2Parents = visited.get(v2);

        while (true) {
            // Ensure we don't step off the edge of either list
            if (idx > v1Parents.size() - 1 || idx > v2Parents.size() - 1) {
                break;
            }

            if (v1Parents.get(idx) == v2Parents.get(idx)) {
                retVal = v1Parents.get(idx);
            } else {
                break;
            }
            idx++;
        }

        return retVal;
    }
    
    public static char lowestCommonAncestor(Node root, char v1, char v2) {
        // Define and populate a Set in which we will keep the values of the
        // two nodes that we are searching for to them compare ancestors.
        Set<Character> search = new HashSet<>();
        search.add(v1);
        search.add(v2);

        // We will execute a BFS over the tree, using a queue to keep track of
        // our progress through the tree.
        Deque<Node> queue = new ArrayDeque<>();

        // A Map in which we will keep all of the nodes that we have visited
        // along with an ordered List of their parents.
        Map<Character, List<Character>> visited = new HashMap<>();

        // Start the traversal by adding our root node to our queue.  We will always
        // add a new Node instance so that we can re-run against the same test data
        Node newRoot = new Node(root.val);
        newRoot.left = root.left;
        newRoot.right = root.right;
        queue.add(newRoot);

        while (search.size() > 0 && queue.size() > 0) {
            // Remove the next node from the queue, then add the value of the
            // current node to it's list of parents.  We will use this list of
            // parents to pre-populate the parents for each of this node's
            // children.
            Node currNode = queue.remove();
            currNode.parents.add(currNode.val);
            visited.put(currNode.val, currNode.parents);

            // Check to see if this current node is one that we are looking for.
            if (search.contains(currNode.val)) {
                search.remove(currNode.val);
            }

            // For each of the child nodes, we will add the list of parents for
            // the current node and then push them onto the queue.
            Node left = currNode.left;
            Node right = currNode.right;
            if (left != null) {
                Node l = new Node(left.val);
                l.left = left.left;
                l.right = left.right;
                l.addParents(currNode.parents);
                queue.add(l);
            }
            if (right != null) {
                Node r = new Node(right.val);
                r.left = right.left;
                r.right = right.right;
                r.addParents(currNode.parents);
                queue.add(r);
            }
        }

        return getLowestCommonAncestor(visited, v1, v2);
    }

    public static void main(String[] args) {

        Node a = new Node('a');
        Node b = new Node('b');
        Node c = new Node('c');
        Node d = new Node('d');
        Node e = new Node('e');
        Node f = new Node('f');
        Node g = new Node('g');
        Node h = new Node('h');
        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        c.right = f;
        e.left = g;
        e.right = h;

        System.out.println(lowestCommonAncestor(a, 'd', 'h')); // b
        System.out.println(lowestCommonAncestor(a, 'd', 'g')); // b
        System.out.println(lowestCommonAncestor(a, 'g', 'c')); // a
        System.out.println(lowestCommonAncestor(a, 'b', 'g')); // b
        System.out.println(lowestCommonAncestor(a, 'f', 'c')); // c

        Node l = new Node('l');
        Node m = new Node('m');
        Node n = new Node('n');
        Node o = new Node('o');
        Node p = new Node('p');
        Node q = new Node('q');
        Node r = new Node('r');
        Node s = new Node('s');
        Node t = new Node('t');
        l.left = m;
        l.right = n;
        n.left = o;
        n.right = p;
        o.left = q;
        o.right = r;
        p.left = s;
        p.right = t;

        System.out.println(lowestCommonAncestor(l, 'r', 'p')); // n
        System.out.println(lowestCommonAncestor(l, 'm', 'o')); // l
        System.out.println(lowestCommonAncestor(l, 't', 'q')); // n
        System.out.println(lowestCommonAncestor(l, 's', 'p')); // p
    }

    public static class Node {
        char val;
        Node left;
        Node right;
        List<Character> parents;

        public Node(char val) {
            this.val = val;
            this.parents = new ArrayList<>();
        }

        public void addParents(List<Character> parents) {
            for (Character p : parents) {
                this.parents.add(p);
            }
        }
    }
}
