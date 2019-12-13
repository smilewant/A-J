package com.further.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Zion
 * 2019/12/12.
 */
public class Solution40 {
    List<List<Integer>> totalI = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        comb(candidates, target, new ArrayList<Integer>(), 0);
        return totalI;
    }

    private void comb(int[] candidates, int target, List<Integer> tempList, int index){

        for (int i = index; i < candidates.length-1; i++) {
            if (target < candidates[i]){
                continue;
            }

            List<Integer> temp = new ArrayList<>();
            temp.addAll(tempList);
            temp.add(candidates[i]);
            if (candidates[i] == target) {
                if (!totalI.contains(temp))
                    totalI.add(temp);
                return;
            } else {
                target -= candidates[i];
                comb(candidates, target, temp, i+1);
                target += candidates[i];
            }

        }

    }
}
