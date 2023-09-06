/*
Given the head of a singly linked list and an integer k, split the linked list into k consecutive linked list parts.

The length of each part should be as equal as possible: no two parts should have a size differing by more than one. This may lead to some parts being null.

The parts should be in the order of occurrence in the input list, and parts occurring earlier should always have a size greater than or equal to parts 
occurring later.

Return an array of the k parts.

 

Example 1:
    Input: head = [1,2,3], k = 5
    Output: [[1],[2],[3],[],[]]
    Explanation:
    The first element output[0] has output[0].val = 1, output[0].next = null.
    The last element output[4] is null, but its string representation as a ListNode is [].

Example 2:
    Input: head = [1,2,3,4,5,6,7,8,9,10], k = 3
    Output: [[1,2,3,4],[5,6,7],[8,9,10]]
    Explanation:
    The input has been split into consecutive parts with size difference at most 1, and earlier parts are a larger size than the later parts.
 

Constraints:
    The number of nodes in the list is in the range [0, 1000].
    0 <= Node.val <= 1000
    1 <= k <= 50
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

public class Solution {
    int x = 0;
    int count(ListNode root) {
        ListNode temp = root;
        while (temp != null) {
            temp = temp.next;
            x++;
        }
        return x;
    }

    public ListNode[] splitListToParts(ListNode head, int k) {
        int n = count(head);
        int x = n / k, y = n % k;
        ListNode[] v = new ListNode[k];
        ListNode a = head, b = null;
        for (int i = 0; i < k; i++) {
            v[i] = head;
            for (int j = 0; j < x + (i < y ? 1 : 0); j++) {
                b = head;
                head = head.next;
            }
            if (b != null) {
                b.next = null;
            }
        }
        return v;
    }
}

class Solution {
    public ListNode[] splitListToParts(ListNode head, int k) {
        ListNode[] res = new ListNode[k];
        HashMap<Integer, Integer> map = new HashMap<>();
        
        ListNode iter = head;
        
        int i = 0;
        while(iter!=null){
            int idx = i%k;
            map.put(idx, map.getOrDefault(idx,0)+1);
            i++;
            iter = iter.next;
        }
        
        iter = head;
        
        for(i=0; i<map.size(); i++){
            int size = map.get(i);
            ListNode dummyHead = new ListNode(0);
            ListNode h = dummyHead;
            while(iter!=null && size-->0){
                h.next = new ListNode(iter.val);
                h = h.next;
                iter = iter.next;
            }
            h.next = null;
            res[i] = dummyHead.next;
        }
        
        return res;
        
    }
}
