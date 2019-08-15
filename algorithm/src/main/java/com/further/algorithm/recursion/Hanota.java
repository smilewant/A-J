package com.further.algorithm.recursion;

import java.util.Stack;

/**
 * Created by Zion
 * 汉诺塔问题
 * 2019/3/26.
 */
public class Hanota {
    public static void hanota(int n, Stack begin){
        Stack middle = new Stack();
        Stack end = new Stack();
        han(n, begin, middle, end);
        System.out.print("stack " + end );
    }

    public static void han(int n, Stack begin, Stack middle, Stack end) {
        if (n == 1) {
            end.push(begin.pop());
            return;
        }
        han(n-1, begin, end, middle);
        move(begin, end);
        han(n-1, middle, begin, end);
    }

    private static void move(Stack begin, Stack end) {
        end.push(begin.pop());
    }
}
