import java.util.*;

public class M03_TopKConvenience {
    static class Item {
        String name;
        int qty;
        int order; 

        Item(String name, int qty, int order) {
            this.name = name;
            this.qty = qty;
            this.order = order;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int K = sc.nextInt();

        PriorityQueue<Item> pq = new PriorityQueue<>((a, b) -> {
            if (a.qty != b.qty) return a.qty - b.qty; 
            return b.order - a.order; 
        });

        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int qty = sc.nextInt();
            Item item = new Item(name, qty, i);

            if (pq.size() < K) {
                pq.offer(item);
            } else {
                Item top = pq.peek();
                if (item.qty > top.qty || 
                   (item.qty == top.qty && item.order < top.order)) {
                    pq.poll();
                    pq.offer(item);
                }
            }
        }
        sc.close();

        List<Item> result = new ArrayList<>(pq);
        result.sort((a, b) -> {
            if (b.qty != a.qty) return b.qty - a.qty;
            return a.order - b.order; 
        });

        for (Item it : result) {
            System.out.println(it.name + " " + it.qty);
        }
    }
}

/*
 * Time Complexity: O(n log K)
 * 說明：
 * 1) 共 n 筆商品，每筆可能觸發一次 heap 操作 (insert 或 replace)，成本 O(log K)。
 * 2) 因為 K 遠小於 n，總時間 = O(n log K)。
 * 3) 輸出前再排序 K 筆元素，成本 O(K log K)，相對 n log K 可忽略。
 */
