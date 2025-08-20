
import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) { this.val = val; }
}

public class M08_BSTRangedSum {

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

    public static int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) return 0;

        if (root.val < L) return rangeSumBST(root.right, L, R);
        if (root.val > R) return rangeSumBST(root.left, L, R);

        return root.val 
               + rangeSumBST(root.left, L, R) 
               + rangeSumBST(root.right, L, R);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        List<Integer> vals = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            vals.add(sc.nextInt());
        }

        int L = sc.nextInt();
        int R = sc.nextInt();

        TreeNode root = buildTree(vals);
        int sum = rangeSumBST(root, L, R);

        System.out.println("Sum: " + sum);
    }
}
