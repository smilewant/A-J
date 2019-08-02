package com.further.leetcode;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zion
 * 2019/3/13.
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 * Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
 */
public class Solution26 {
    public int removeDuplicates(int[] nums) {
        List<Integer> list = new ArrayList<>();
        int j = 0;
        for (int i : nums) {
            if (!list.contains(i)) {
                list.add(i);
            }
        }
        for (Integer m :list){
            nums[j++] = m;
        }
//        GenerateData.displayArray(nums);
        return list.size();
    }

    public int removeDuplicates_beta(int[] nums) {
        if (nums.length <= 2)
            return nums.length;

        int index = 2;
        for (int i = 2; i < nums.length; i++){
            if (nums[i] != nums[index - 2])
                nums[index++] = nums[i];

        }
//        GenerateData.displayArray(nums);
        return index;
    }
}
