package com.further.leetcode;

import android.support.v4.content.ContextCompat;

/**
 * Created by Zion
 * 2019/7/31.
 */
public class Solution322 {
    public int coinChange(int[] coins, int amount) {
        int sum = 0;
        // 5  4  3  --- 12


        return sum;
    }

    int getMax(int[] coins) {
        int max = 0;
        for (int j : coins) {
            max = j > max ? j : max;
        }
        return max;
    }
}
