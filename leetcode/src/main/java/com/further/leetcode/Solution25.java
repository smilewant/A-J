package com.further.leetcode;

/**
 * Created by Zion
 * 2019/8/7.
 */
public class Solution25 {
    public  void swap(){
        ListNode h = new ListNode(1);
        ListNode temp = h;
        int n = 5;
        while(n > 0){
            temp.next = new ListNode(5 - n + 2);
            n--;
            temp = temp.next;
        }
        reverseKGroup(h, 3);
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode node = new ListNode(0);
        node.next = head;
        ListNode n = node;
        while(n != null && n.next != null) {
            n = partReverse(n, k);
        }
        return node.next;
    }

    public ListNode partReverse(ListNode listNode, int k){
       ListNode node = listNode;
        ListNode pre = new ListNode(0);
        ListNode next = node.next;
        while(node !=null) {
           node.next = pre;
           pre = node;
           node = next;
           if (node != null)next = node.next;

        }
//        pre.next = node;
        listNode.next = pre;

        return node;
    }
}
