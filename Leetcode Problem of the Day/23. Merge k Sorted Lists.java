/*
You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.

Merge all the linked-lists into one sorted linked-list and return it.

 

Example 1:
    Input: lists = [[1,4,5],[1,3,4],[2,6]]
    Output: [1,1,2,3,4,4,5,6]
    Explanation: The linked-lists are:
    [
      1->4->5,
      1->3->4,
      2->6
    ]
    merging them into one sorted list:
    1->1->2->3->4->4->5->6

Example 2:
    Input: lists = []
    Output: []
    Example 3:

    Input: lists = [[]]
    Output: []


Constraints:
    k == lists.length
    0 <= k <= 104
    0 <= lists[i].length <= 500
    -104 <= lists[i][j] <= 104
    lists[i] is sorted in ascending order.
    The sum of lists[i].length will not exceed 104.
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
    public ListNode mergeKLists(ListNode[] lists) {
        int n = lists.length;
        if(n==0)
            return null;
        if(n==1)
            return lists[0];
        
        ListNode prevSorted = merge2List(lists[0], lists[1]);
        for(int i=2; i<n; i++){
            prevSorted = merge2List(prevSorted, lists[i]);
        }
        
        return prevSorted;
        
    }
    public ListNode merge2List(ListNode n1, ListNode n2){
        ListNode head = new ListNode(-1);
        ListNode dummy = head;
        
        while(n1!=null && n2!=null){
            if(n1.val < n2.val){
                dummy.next = n1;
                n1 = n1.next;
            }else{
                dummy.next = n2;
                n2 = n2.next;
            }
            dummy = dummy.next;
        }
        if(n1!=null)
            dummy.next = n1;
        else
            dummy.next = n2;
        
        return head.next;
    }
}
