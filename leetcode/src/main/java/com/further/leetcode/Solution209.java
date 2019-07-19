package com.further.leetcode;

/**
 * Created by Zion
 * 2019/7/2.
 */
public class Solution209 {
    public static int minSubArrayLen(int s, int[] nums) {

//        int result = Integer.MAX_VALUE, left = 0, sum = 0;
//        for (int i = 0; i < nums.length; i++) {
//            sum += nums[i];
//            while (sum >= s) {
//                result = Math.min(result, i - left + 1);
//                sum -= nums[left++];
//            }
//        }
//        return result == Integer.MAX_VALUE ? 0 : result;

        for (int i : nums) {
            if (i >= s) return 1;
        }
        int min = 0;
        int m = 0, n = 1;
        int sum = s;
        while (m < nums.length) {
            sum += nums[m];
            if (sum >= s) {

                if (min == 0) {
                    min = n;
                } else {
                    min = Math.min(min, n);
                }
                m = m - min + 2;
                n = 1;
                sum = 0;
                continue;
            }
            m++;
            n++;
        }
        return min;
    }
}
