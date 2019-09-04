package com.further.algorithm;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Hukuan
 * 2019/1/3.
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        HashMap<List<Integer>, Integer> twoSum = new LinkedHashMap<>();
        List<List<Integer>> resultList = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                list.add(j);
                twoSum.put(list, Integer.valueOf(nums[i] + nums[j]));
            }
        }

        for (Map.Entry<List<Integer>, Integer> entry : twoSum.entrySet())
            for (int i = 0; i < nums.length; i++) {
                List<Integer> temp = entry.getKey();
                if (0 == entry.getValue() + nums[i] && !temp.contains(i)) {
                    List<Integer> list = new ArrayList<>();
                    if (i < temp.get(0)) {
                        list.add(nums[i]);
                        list.add(nums[temp.get(0)]);
                        list.add(nums[temp.get(1)]);
                    } else {
                        list.add(nums[temp.get(0)]);
                        if (i < temp.get(1)) {
                            list.add(nums[i]);
                            list.add(nums[temp.get(1)]);
                        } else {
                            list.add(nums[temp.get(1)]);
                            list.add(nums[i]);
                        }
                    }

                    if (!resultList.contains(list)) resultList.add(list);
                }
            }

        return resultList;
    }

}
