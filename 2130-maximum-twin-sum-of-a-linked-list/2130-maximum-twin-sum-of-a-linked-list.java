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
    public int pairSum(ListNode head) {
        List<Integer> l = new ArrayList<>();
        ListNode cur = head;
        while(cur != null) {
            l.add(cur.val);
            cur = cur.next;
        }

        int n = l.size();
        int max = Integer.MIN_VALUE;
        for(int i=0; i<n/2; i++) {
            max = Math.max(max, l.get(i) + l.get(n-1-i));
        }

        return max;
    }
}