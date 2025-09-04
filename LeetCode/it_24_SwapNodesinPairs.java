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
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode curr = dummy;

        while (curr.next != null && curr.next.next != null) {
            ListNode first = curr.next;
            ListNode second = curr.next.next;

            curr.next = second;
            first.next = second.next;
            second.next = first;

            curr = first;
        }

        return dummy.next;
    }
}

/*
解題思路：
1. 使用 dummy 節點處理頭節點交換的特殊情況。
2. 每次取出相鄰兩個節點，調整指標完成交換。
3. 時間複雜度 O(n)，空間複雜度 O(1)。
範例：
head = [1,2,3,4]
dummy -> 1 -> 2 -> 3 -> 4
交換 (1,2) → dummy -> 2 -> 1 -> 3 -> 4
交換 (3,4) → dummy -> 2 -> 1 -> 4 -> 3
結果 [2,1,4,3]
*/
