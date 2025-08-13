import java.util.*;

public class MultiLevelCacheSystem {

    static class Node {
        int key;
        String value;
        int accessCount;
        long lastAccessTime;
        int level; 

        Node(int key, String value, int level) {
            this.key = key;
            this.value = value;
            this.level = level;
            this.accessCount = 0;
            this.lastAccessTime = System.nanoTime();
        }

        double priorityScore() {
            int cost = switch (level) {
                case 1 -> 1;
                case 2 -> 3;
                default -> 10;
            };
            return (double) accessCount / cost;
        }
    }

    static class CacheLevel {
        int capacity;
        int level;
        Map<Integer, Node> map = new HashMap<>();
        PriorityQueue<Node> heap; 

        CacheLevel(int capacity, int level) {
            this.capacity = capacity;
            this.level = level;
            this.heap = new PriorityQueue<>(
                    Comparator.comparingDouble(Node::priorityScore)
                              .thenComparingLong(n -> n.lastAccessTime)
            );
        }

        boolean isFull() {
            return map.size() >= capacity;
        }

        void add(Node node) {
            node.level = level;
            map.put(node.key, node);
            heap.offer(node);
        }

        void remove(Node node) {
            map.remove(node.key);
            heap.remove(node);
        }

        Node evictLowest() {
            Node lowest = heap.poll();
            if (lowest != null) map.remove(lowest.key);
            return lowest;
        }
    }

    CacheLevel L1 = new CacheLevel(2, 1);
    CacheLevel L2 = new CacheLevel(5, 2);
    CacheLevel L3 = new CacheLevel(10, 3);

    Map<Integer, Node> globalMap = new HashMap<>();

    public String get(int key) {
        Node node = globalMap.get(key);
        if (node == null) return null;

        node.accessCount++;
        node.lastAccessTime = System.nanoTime();
        rebalance(node);
        return node.value;
    }

    public void put(int key, String value) {
        if (globalMap.containsKey(key)) {
            Node node = globalMap.get(key);
            node.value = value;
            get(key); 
            return;
        }

        Node node = new Node(key, value, 3);
        globalMap.put(key, node);
        moveToBestLevel(node);
    }

    private void moveToBestLevel(Node node) {
        double score = node.priorityScore();
        int targetLevel;
        if (score > 1.5) targetLevel = 1;     
        else if (score > 0.3) targetLevel = 2; 
        else targetLevel = 3;                

        if (node.level == targetLevel) return;

        removeFromCurrentLevel(node);

        CacheLevel target = getLevel(targetLevel);
        if (target.isFull()) {
            Node evicted = target.evictLowest();
            if (evicted != null) {
                if (targetLevel < 3) {
                    moveToBestLevel(evicted); 
                } else {
                    globalMap.remove(evicted.key); 
                }
            }
        }
        target.add(node);
    }

    private void rebalance(Node node) {
        moveToBestLevel(node);
    }

    private void removeFromCurrentLevel(Node node) {
        getLevel(node.level).remove(node);
    }

    private CacheLevel getLevel(int level) {
        return switch (level) {
            case 1 -> L1;
            case 2 -> L2;
            default -> L3;
        };
    }

    public void printCache() {
        System.out.println("L1: " + L1.map.keySet());
        System.out.println("L2: " + L2.map.keySet());
        System.out.println("L3: " + L3.map.keySet());
        System.out.println("------");
    }

    public static void main(String[] args) {
        MultiLevelCacheSystem cache = new MultiLevelCacheSystem();

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        cache.printCache();

        cache.get(1);
        cache.get(1);
        cache.get(2);
        cache.printCache();

        cache.put(4, "D");
        cache.put(5, "E");
        cache.put(6, "F");
        cache.printCache();
    }
}
