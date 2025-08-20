/*
 * Time Complexity: O(n)
 * 說明：
 * 1. 遍歷每個節點一次，檢查 BST 與 AVL 高度差 → O(n)
 * 2. BST 檢查透過遞迴上下界傳遞，每個節點訪問一次
 * 3. AVL 檢查透過後序計算高度，每個節點訪問一次
 */

import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

public class M09_AVLValidate {

    public static TreeNode buildTree(List<Integer> vals) {
        if (vals.isEmpty() || vals.get(0) == -1) return null;
        TreeNode root = new TreeNode(vals.get(0));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while (i < vals.size()) {
            TreeNode curr = queue.poll();
            if (curr == null) continue;

            if (vals.get(i) != -1) {
                curr.left = new TreeNode(vals.get(i));
                queue.add(curr.left);
            }
            i++;
            if (i >= vals.size()) break;

            if (vals.get(i) != -1) {
                curr.right = new TreeNode(vals.get(i));
                queue.add(curr.right);
            }
            i++;
        }
        return root;
    }

    public static boolean isBST(TreeNode node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return isBST(node.left, min, node.val) && isBST(node.right, node.val, max);
    }

    public static int checkAVL(TreeNode node) {
        if (node == null) return 0;
        int lh = checkAVL(node.left);
        if (lh == -1) return -1;
        int rh = checkAVL(node.right);
        if (rh == -1) return -1;

        if (Math.abs(lh - rh) > 1) return -1;  
        return Math.max(lh, rh) + 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        List<Integer> vals = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            vals.add(sc.nextInt());
        }

        TreeNode root = buildTree(vals);

        if (!isBST(root, Long.MIN_VALUE, Long.MAX_VALUE)) {
            System.out.println("Invalid BST");
            return;
        }

        if (checkAVL(root) == -1) {
            System.out.println("Invalid AVL");
            return;
        }

        System.out.println("Valid");
    }
}
