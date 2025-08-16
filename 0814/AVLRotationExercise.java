public class AVLRotationExercise {

    static class Node {
        int key;
        Node left, right;
        int height;

        Node(int key) {
            this.key = key;
            this.height = 1;
        }
    }

    private static int height(Node node) {
        return node == null ? 0 : node.height;
    }

    private static void updateHeight(Node node) {
        if (node != null) {
            node.height = Math.max(height(node.left), height(node.right)) + 1;
        }
    }

    public static Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        return x; 
    }

    public static Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y; 
    }

    public static Node rotateLeftRight(Node z) {
        z.left = rotateLeft(z.left);
        return rotateRight(z);
    }

    public static Node rotateRightLeft(Node z) {
        z.right = rotateRight(z.right);
        return rotateLeft(z);
    }

    public static void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.key + " ");
            inorder(root.right);
        }
    }

    public static void printTree(Node root, String prefix) {
        if (root != null) {
            System.out.println(prefix + root.key + " (h=" + root.height + ")");
            printTree(root.left, prefix + "  L-");
            printTree(root.right, prefix + "  R-");
        }
    }

    public static void main(String[] args) {
        Node root1 = new Node(10);
        root1.right = new Node(20);
        root1.right.right = new Node(30);
        updateHeight(root1.right);
        updateHeight(root1);
        System.out.println("原始樹 (RR case):");
        printTree(root1, "");
        root1 = rotateLeft(root1);
        System.out.println("左旋後:");
        printTree(root1, "");
        System.out.println("----------------");

        Node root2 = new Node(30);
        root2.left = new Node(20);
        root2.left.left = new Node(10);
        updateHeight(root2.left);
        updateHeight(root2);
        System.out.println("原始樹 (LL case):");
        printTree(root2, "");
        root2 = rotateRight(root2);
        System.out.println("右旋後:");
        printTree(root2, "");
        System.out.println("----------------");

        Node root3 = new Node(30);
        root3.left = new Node(10);
        root3.left.right = new Node(20);
        updateHeight(root3.left);
        updateHeight(root3);
        System.out.println("原始樹 (LR case):");
        printTree(root3, "");
        root3 = rotateLeftRight(root3);
        System.out.println("左右旋後:");
        printTree(root3, "");
        System.out.println("----------------");

        Node root4 = new Node(10);
        root4.right = new Node(30);
        root4.right.left = new Node(20);
        updateHeight(root4.right);
        updateHeight(root4);
        System.out.println("原始樹 (RL case):");
        printTree(root4, "");
        root4 = rotateRightLeft(root4);
        System.out.println("右左旋後:");
        printTree(root4, "");
    }
}
