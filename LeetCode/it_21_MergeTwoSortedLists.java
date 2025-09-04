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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                curr.next = list1;   
                list1 = list1.next; 
            } else {
                curr.next = list2;   
                list2 = list2.next; 
            }
            curr = curr.next; 
        }

        if (list1 != null) {
            curr.next = list1;
        } else {
            curr.next = list2;
        }

        return dummy.next; 
    }
}

/*
解題思路：
1. 使用 dummy 節點，避免特別處理頭節點。
2. 迴圈比較 list1 和 list2 的值，小的先接到新串列。
3. 最後將未結束的串列整段接上。
4. 時間 O(m+n)，空間 O(1)。
*/
