/*
You are given the head of a linked list, and an integer k.

Return the head of the linked list after swapping the values of the kth node from the beginning and the 
kth node from the end (the list is 1-indexed).

 

Example 1:
    Input: head = [1,2,3,4,5], k = 2
    Output: [1,4,3,2,5]

Example 2:
    Input: head = [7,9,6,6,7,8,3,0,9,5], k = 5
    Output: [7,9,6,6,8,7,3,0,9,5]
 

Constraints:
    The number of nodes in the list is n.
    1 <= k <= n <= 105
    0 <= Node.val <= 100
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
class Solution {
    public ListNode swapNodes(ListNode head, int k) {
        ListNode fast  = head;
        ListNode slow = head;

        while (k-1>0){
            fast = fast.next;
            k--;
        }
        ListNode node1 = fast;

        while(fast.next != null){
            slow = slow.next;
            fast = fast.next;
        }
        ListNode node2 = slow;

        int tmp = node1.val;
        node1.val = node2.val;
        node2.val = tmp;

        return head;
    }
}


/*
class Solution {
    public ListNode swapNodes(ListNode head, int k) {
        ListNode dummy = head;
        ListNode first = null;
        ListNode last = null;
        
        // finding length;
        ListNode dup = head;
        int n = 0;
        while(dup != null){
            n++;
            dup = dup.next;
        }
        
        // finding first and last:)
        int count = 1;
        while(dummy != null){
            if(count == k)
                first = dummy;
            if(count == n-k+1)
                last = dummy;
            dummy = dummy.next;
            count++;
        }
        
        // swaping value;
        int temp = first.val;
        first.val = last.val;
        last.val = temp;
        
        return head;
    }
}
*/
