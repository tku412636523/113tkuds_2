import java.util.*;

public class AVLLeaderboardSystem {

    static class Node {
        int score;
        String player;
        Node left, right;
        int height;
        int size; 

        Node(String player, int score) {
            this.player = player;
            this.score = score;
            this.height = 1;
            this.size = 1;
        }
    }

    private Node root;
    private Map<String, Integer> playerScores = new HashMap<>(); 

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    private int size(Node node) {
        return node == null ? 0 : node.size;
    }

    private void update(Node node) {
        if (node != null) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
            node.size = 1 + size(node.left) + size(node.right);
        }
    }

    private int getBalance(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        update(y);
        update(x);
        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        update(x);
        update(y);
        return y;
    }

    private Node rebalance(Node node) {
        int balance = getBalance(node);

        if (balance > 1) {
            if (getBalance(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }

        if (balance < -1) {
            if (getBalance(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }

        return node;
    }

    private Node insert(Node node, String player, int score) {
        if (node == null) return new Node(player, score);

        if (score > node.score || (score == node.score && player.compareTo(node.player) < 0)) {
            node.left = insert(node.left, player, score);
        } else {
            node.right = insert(node.right, player, score);
        }

        update(node);
        return rebalance(node);
    }

    private Node delete(Node node, String player, int score) {
        if (node == null) return null;

        if (score > node.score || (score == node.score && player.compareTo(node.player) < 0)) {
            node.left = delete(node.left, player, score);
        } else if (score < node.score || (score == node.score && player.compareTo(node.player) > 0)) {
            node.right = delete(node.right, player, score);
        } else {
            if (node.left == null || node.right == null) {
                node = (node.left != null) ? node.left : node.right;
            } else {
                Node minNode = node.right;
                while (minNode.left != null) minNode = minNode.left;
                node.player = minNode.player;
                node.score = minNode.score;
                node.right = delete(node.right, minNode.player, minNode.score);
            }
        }

        if (node != null) {
            update(node);
            node = rebalance(node);
        }
        return node;
    }

    public void addOrUpdatePlayer(String player, int score) {
        if (playerScores.containsKey(player)) {
            int oldScore = playerScores.get(player);
            root = delete(root, player, oldScore);
        }
        root = insert(root, player, score);
        playerScores.put(player, score);
    }

    public int getRank(String player) {
        if (!playerScores.containsKey(player)) return -1;
        int score = playerScores.get(player);
        return getRankRec(root, player, score) + 1; 
    }

    private int getRankRec(Node node, String player, int score) {
        if (node == null) return 0;

        if (score > node.score || (score == node.score && player.compareTo(node.player) < 0)) {
            return getRankRec(node.left, player, score);
        } else if (score < node.score || (score == node.score && player.compareTo(node.player) > 0)) {
            return size(node.left) + 1 + getRankRec(node.right, player, score);
        } else {
            return size(node.left); 
        }
    }

    public List<String> topK(int k) {
        List<String> result = new ArrayList<>();
        collectTopK(root, k, result);
        return result;
    }

    private void collectTopK(Node node, int k, List<String> result) {
        if (node == null || result.size() >= k) return;

        collectTopK(node.left, k, result); 
        if (result.size() < k) {
            result.add(node.player + "(" + node.score + ")");
        }
        collectTopK(node.right, k, result);
    }

    public static void main(String[] args) {
        AVLLeaderboardSystem leaderboard = new AVLLeaderboardSystem();

        leaderboard.addOrUpdatePlayer("Alice", 100);
        leaderboard.addOrUpdatePlayer("Bob", 80);
        leaderboard.addOrUpdatePlayer("Charlie", 120);
        leaderboard.addOrUpdatePlayer("David", 90);

        System.out.println("前 3 名: " + leaderboard.topK(3));
        System.out.println("Alice 排名: " + leaderboard.getRank("Alice"));
        System.out.println("Charlie 排名: " + leaderboard.getRank("Charlie"));

        leaderboard.addOrUpdatePlayer("Alice", 130); 
        System.out.println("更新後前 3 名: " + leaderboard.topK(3));
        System.out.println("Alice 排名: " + leaderboard.getRank("Alice"));
    }
}
