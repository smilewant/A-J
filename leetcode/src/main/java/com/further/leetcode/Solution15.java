package com.further.leetcode;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Zion
 * 2019/9/9.
 */
public class Solution15 {
    public List<List<Integer>> threeSum(int[] nums) {


        int[] lessNums = new int[nums.length];
        int[] moreNums = new int[nums.length];

        int j = 0, k = 0;
        for (int i : nums) {
            if (i != 0 ) {
                if (i < 0) {
                    lessNums[j++] = i;
                } else {
                    moreNums[k++] = i;
                }
            }
        }


        
        return  null;

    }
}
