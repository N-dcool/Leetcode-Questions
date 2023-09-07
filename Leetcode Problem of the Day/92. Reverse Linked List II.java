/*
Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position 
left to position right, and return the reversed list.

 

Example 1:
    Input: head = [1,2,3,4,5], left = 2, right = 4
    Output: [1,4,3,2,5]

Example 2:
    Input: head = [5], left = 1, right = 1
    Output: [5]
 

Constraints:
    The number of nodes in the list is n.
    1 <= n <= 500
    -500 <= Node.val <= 500
    1 <= left <= right <= n
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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null) return null;
        if (head.next == null) return head;
        int index = 0;
        ListNode leftNode = null, leftBehind = null, rightNode = null;
        ListNode ans = new ListNode();
        ans.next = head;
        ListNode dummy = ans.next;

        while (dummy != null)
        {
            index++;
             if (index + 1 == left)
                leftBehind = dummy;
            if (index == left)
                leftNode = dummy;
            if (index == right)
                rightNode = dummy;
            dummy = dummy.next;
        }
        if (leftBehind != null)
            leftBehind.next = null;
       ListNode rightFront = (rightNode.next != null) ? rightNode.next : null;
       if (rightFront != null)
            rightNode.next = null;
       ListNode reverse = reverseList(leftNode);
       if (leftBehind != null)
            leftBehind.next = reverse;
       if (rightFront != null) 
            leftNode.next = rightFront;
        if (left == 1)
            return reverse;
       return ans.next;
    }

    public ListNode reverseList(ListNode head) {
        ListNode currentNext = null;
        ListNode previous = null;
        ListNode current = head;

        while (current != null)
        {
            currentNext = current.next;
            current.next = previous;
            previous = current;
            current = currentNext;
        }

        return previous;
    }
    
}

class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        int n = 0;
        ListNode iter = head;
        
        while(iter!=null){
            iter = iter.next;
            n++;
        }
        
        if(left==n)
            return head;
        
        int[] res = new int[n];
        iter = head;
        left--;
        right--;
        int end = right;
        
        for(int i=0; i<n; i++){
            if(i==left){
                if(left == end) left = -1;
                res[right] = iter.val;
                right--;
                left++;
            }
            else{
                res[i] = iter.val;
            }
            
            iter = iter.next;
        }
        
        ListNode ans = new ListNode(0);
        iter = ans;
        for(int a : res){
            iter.next = new ListNode(a);
            iter = iter.next;
        }
        
        
        return ans.next;
    }
}
