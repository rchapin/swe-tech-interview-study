
public class MaxTreeToLeafPathSum {

    public static int maxPathSum(Node n) {
        if (n.left == null && n.right == null) {
            return n.val;
        }
        int left = Integer.MIN_VALUE;
        int right = Integer.MIN_VALUE;
        if (n.left != null) {
            left = maxPathSum(n.left); 
        }
        if (n.right != null) {
            right = maxPathSum(n.right); 
        }
        return Math.max(left, right) + n.val;
    }

    public static void main(String[] args) {
        Node a = new Node(3);
        Node b = new Node(11);
        Node c = new Node(4);
        Node d = new Node(4);
        Node e = new Node(-2);
        Node f = new Node(1);
        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        c.right = f;

        //       3
        //    /    \
        //   11     4
        //  / \      \
        // 4   -2     1

        System.out.println(maxPathSum(a)); // -> 18


        Node aa = new Node(5);
        Node ab = new Node(11);
        Node ac = new Node(54);
        Node ad = new Node(20);
        Node ae = new Node(15);
        Node af = new Node(1);
        Node ag = new Node(3);
        aa.left = ab;
        aa.right = ac;
        ab.left = ad;
        ab.right = ae;
        ae.left = af;
        ae.right = ag;
        
        //        5
        //     /    \
        //    11    54
        //  /   \
        // 20   15
        //      / \
        //     1  3
        
        System.out.println(maxPathSum(aa)); // -> 59
    }

    public static class Node {
        final int val;
        Node left;
        Node right;
        public Node(int val) {
            this.val = val;
        }
    }
}
