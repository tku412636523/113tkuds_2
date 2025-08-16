import java.util.*;

public class PersistentAVLExercise {

    static class Node {
        final int key;
        final Node left, right;
        final int height, size;

        Node(int key, Node left, Node right) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.height = 1 + Math.max(height(left), height(right));
            this.size = 1 + size(left) + size(right);
        }

        private static int height(Node n) { return n == null ? 0 : n.height; }
        private static int size(Node n) { return n == null ? 0 : n.size; }
        private static int balance(Node n) { return n == null ? 0 : height(n.left) - height(n.right); }
    }

    private final List<Node> versions = new ArrayList<>();

    public PersistentAVLExercise() {
        versions.add(null); 
    }

    public void insert(int version, int key) {
        Node newRoot = insert(versions.get(version), key);
        versions.add(newRoot);
    }

    private Node insert(Node node, int key) {
        if (node == null) return new Node(key, null, null);

        if (key < node.key) {
            return rebalance(new Node(node.key, insert(node.left, key), node.right));
        } else if (key > node.key) {
            return rebalance(new Node(node.key, node.left, insert(node.right, key)));
        } else {
            return node; 
        }
    }

    private Node rebalance(Node node) {
        int balance = Node.balance(node);

        if (balance > 1) {
            if (Node.balance(node.left) < 0) {
                return rotateRight(new Node(node.key, rotateLeft(node.left), node.right));
            }
            return rotateRight(node);
        }
        if (balance < -1) {
            if (Node.balance(node.right) > 0) {
                return rotateLeft(new Node(node.key, node.left, rotateRight(node.right)));
            }
            return rotateLeft(node);
        }
        return node;
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node newRight = new Node(y.key, x.right, y.right);
        return new Node(x.key, x.left, newRight);
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node newLeft = new Node(x.key, x.left, y.left);
        return new Node(y.key, newLeft, y.right);
    }

    public boolean search(int version, int key) {
        Node root = versions.get(version);
        while (root != null) {
            if (key < root.key) root = root.left;
            else if (key > root.key) root = root.right;
            else return true;
        }
        return false;
    }

    public int getHeight(int version) {
        Node root = versions.get(version);
        return root == null ? 0 : root.height;
    }

    public List<Integer> inorder(int version) {
        List<Integer> result = new ArrayList<>();
        inorderRec(versions.get(version), result);
        return result;
    }

    private void inorderRec(Node node, List<Integer> result) {
        if (node == null) return;
        inorderRec(node.left, result);
        result.add(node.key);
        inorderRec(node.right, result);
    }

    public static void main(String[] args) {
        PersistentAVLExercise tree = new PersistentAVLExercise();

        tree.insert(0, 10); 
        tree.insert(1, 20); 
        tree.insert(2, 5);  
        System.out.println("版本 1: " + tree.inorder(1)); 
        System.out.println("版本 2: " + tree.inorder(2)); 
        System.out.println("版本 3: " + tree.inorder(3)); 

        System.out.println("版本 1 高度: " + tree.getHeight(1)); 
        System.out.println("版本 3 是否有 20: " + tree.search(3, 20)); 
        System.out.println("版本 1 是否有 20: " + tree.search(1, 20)); 
    }
}
