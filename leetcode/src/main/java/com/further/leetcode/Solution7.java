package com.further.leetcode;

/**
 * Created by Zion
 * 2019/7/19.
 */
public class Solution7 {

    public static int reverse(int x) {

        return x < 0 ? -r1(Math.abs(x)) : r1(x);
    }

    public static int r1(int x) {
        int y = 0;
        while (x > 0) {
            y += x % 10;

            x = x / 10;
            if (x > 0) y *= 10;
        }
        return y;
    }

}
