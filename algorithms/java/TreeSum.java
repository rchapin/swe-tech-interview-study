
public class TreeSum {

    public static int treeSum(Node n) {
        if (n.left == null && n.right == null) {
            return n.val;
        }
        int retVal = n.val;
        if (n.left != null) {
            retVal += treeSum(n.left); 
        }
        if (n.right != null) {
            retVal += treeSum(n.right); 
        }
        return retVal;
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
        System.out.println(treeSum(a));

        Node aa = new Node(1);
        Node ab = new Node(6);
        Node ac = new Node(0);
        Node ad = new Node(3);
        Node ae = new Node(-6);
        Node af = new Node(2);
        Node ag = new Node(2);
        Node ah = new Node(2);
        aa.left = ab;
        aa.right = ac;
        ab.left = ad;
        ab.right = ae;
        ac.right = af;
        ae.left = ag;
        af.right = ah;
        System.out.println(treeSum(aa));
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
