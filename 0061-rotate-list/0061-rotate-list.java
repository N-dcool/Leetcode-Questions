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
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        // Step 1: find length and tail
        int n = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            n++;
        }

        // Step 2: reduce k
        k = k % n;
        if (k == 0) {
            return head;
        }

        // Step 3: make circular
        tail.next = head;

        // Step 4: find new tail
        int steps = n - k;
        ListNode newTail = head;
        for (int i = 1; i < steps; i++) {
            newTail = newTail.next;
        }

        // Step 5: break
        ListNode newHead = newTail.next;
        newTail.next = null;

        return newHead;
    }
}