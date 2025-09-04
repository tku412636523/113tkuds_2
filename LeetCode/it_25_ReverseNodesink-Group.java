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
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prevGroupEnd = dummy;

        while (true) {
            ListNode kth = prevGroupEnd;
            for (int i = 0; i < k && kth != null; i++) {
                kth = kth.next;
            }
            if (kth == null) break; 

            ListNode groupStart = prevGroupEnd.next;
            ListNode nextGroupStart = kth.next;

            ListNode prev = nextGroupStart;
            ListNode curr = groupStart;
            while (curr != nextGroupStart) {
                ListNode temp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = temp;
            }

            prevGroupEnd.next = kth;
            prevGroupEnd = groupStart; 
        }

        return dummy.next;
    }
}

/*
解題思路：
1. 使用 dummy 節點方便操作。
2. 每次檢查剩餘節點是否 ≥ k。
3. 反轉該組節點，連接前後串列。
4. 重複直到串列尾。
5. 時間 O(n)，空間 O(1)。
範例：
head = [1,2,3,4,5], k = 2
反轉 1,2 → 2->1->3->4->5
反轉 3,4 → 2->1->4->3->5
剩餘 5，不足 k → 保持不變
結果：[2,1,4,3,5]
*/
