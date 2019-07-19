package com.further.leetcode;

import java.util.Stack;

/**
 * Created by Zion
 * 2019/6/25.
 */
public class Solution2 {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode a = new ListNode(2);
        ListNode b = new ListNode(4);
        ListNode c = new ListNode(3);
        ListNode a1 = new ListNode(5);
        ListNode b1 = new ListNode(6);
        ListNode c1 = new ListNode(4);
        a.next = b;
        b.next = c;
        a1.next = b1;
        b1.next = c1;

        int carry = 0;
        ListNode node = new ListNode(0);
        ListNode nodeOut = node;
        while(l1 != null || l2 != null){
            int m = l1 != null? l1.val : 0;
            int n = l2 != null? l2.val : 0;

            int num = m + n + carry;
            carry = num / 10;

            ListNode node1 = new ListNode(num % 10);
            nodeOut.next = node1;
            nodeOut = nodeOut.next;

            if (l1 != null){
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if(carry > 0) nodeOut.next = new ListNode(1);
//        return node.next;


        String s1 = toS(a);
        String s2 = toS(a1);
        double sum = Double.parseDouble("1000000000000000000001") + Double.parseDouble(s2);
        int j = 10;
        ListNode sub = null;

        if (sum == 0) {
            return new ListNode(0);
        }
        Stack<Double> stack = new Stack<>();
        while (sum >= 1) {
          stack.push(sum %j);

            sum /= j;
        }
        while(!stack.empty()) {
            int m = stack.pop().intValue();
            if (sub == null) {
                sub = new ListNode(m);
            } else {
                ListNode n = new ListNode(m);
                n.next = sub;
                sub = n;
            }
        }
        toS(sub);
        return sub;
    }

    public static String toS(ListNode listNode){
        StringBuilder builder = new StringBuilder();
        builder.append(listNode.val);
        while(listNode.next != null){
            builder.insert(0,listNode.next.val);
            listNode = listNode.next;
        }
        System.out.print("build : " + builder.toString() + "\n");
        return builder.toString();
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}