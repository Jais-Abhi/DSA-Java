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
    public int[] nextLargerNodes(ListNode head) {

        ListNode prev = null;

        // Reverse linked list
        while(head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }

        Stack<Integer> st = new Stack<>();
        ArrayList<Integer> ans = new ArrayList<>();

        // Traverse reversed list
        while(prev != null) {

            while(!st.isEmpty() && st.peek() <= prev.val) {
                st.pop();
            }

            ans.add(st.isEmpty() ? 0 : st.peek());

            st.push(prev.val);
            prev = prev.next;
        }

        Collections.reverse(ans);

        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}