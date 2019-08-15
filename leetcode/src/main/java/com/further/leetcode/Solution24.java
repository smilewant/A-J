package com.further.leetcode;

/**
 * Created by Zion
 * 2019/8/6.
 */
public class Solution24 {
    public static  void swap(){
        ListNode h = new ListNode(1);
        h.next = new ListNode(2);
        h.next.next = new ListNode(3);
        h.next.next.next = new ListNode(4);
        swapPairs_2(h);
    }

    public static ListNode swapPairs(ListNode head) {

        if(head == null || head.next == null){
            return head;
        }
        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;
        return next;
    }

    public static ListNode swapPairs_2(ListNode head){
        ListNode n = new ListNode(0);
        n.next = head;
        ListNode nw = n;
        while(nw.next != null && nw.next.next != null) {
            ListNode pre = nw.next;
            ListNode next = pre.next;

            pre.next = next.next;
            next.next = pre;
            nw.next = next;
            nw = pre;

        }
        return n.next;

    }
}
