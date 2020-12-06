package Week_01;

/**
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 */

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
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode swap = head;;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                swap.next = l2;
                l2 = l2.next;
            } else if (l2 == null) {
                swap.next = l1;
                l1 = l1.next;
            } else if (l1.val > l2.val) {
                swap.next = l2;
                l2 = l2.next;
            } else {
                swap.next = l1;
                l1 = l1.next;
            }
            swap = swap.next;
        }
        return head.next;
    }
}