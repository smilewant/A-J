package com.further.leetcode;

/**
 * Created by Zion
 * 2019/6/25.
 */
public class Solution1 {
    public int[] twoSum(int[] nums, int target) {

        int temp[] = new int[2];

        for (int i = 0; i < nums.length; i++) {
            temp[0] = i;

            for (int j = nums.length - 1; j > i; j--){
                if (target - nums[i] == nums[j]) {
                    temp[1] = j;
                    return temp;
                }
            }
        }


        return null;
    }
}
