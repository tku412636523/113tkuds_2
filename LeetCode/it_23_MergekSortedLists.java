import java.util.PriorityQueue;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        // 最小堆，根據節點值排序
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);

        // 初始化：把每條鏈結串列的頭節點放入堆
        for (ListNode node : lists) {
            if (node != null) pq.offer(node);
        }

        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;

        while (!pq.isEmpty()) {
            // 取出最小節點
            ListNode node = pq.poll();
            curr.next = node;
            curr = curr.next;

            // 若還有下一個節點，放進堆
            if (node.next != null) {
                pq.offer(node.next);
            }
        }

        return dummy.next;
    }
}

/*
解題思路：
1. 使用最小堆（PriorityQueue）存放每條鏈結串列的頭節點。
2. 每次取出堆中最小的節點，接到新串列後面。
3. 如果該節點有下一個，將它放入堆中。
4. 時間 O(N log k)，空間 O(k)。
*/
