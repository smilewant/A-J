package com.further.run.algorithm;

import com.further.run.algorithm.sort.BuckSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Hukuan
 * 2019/3/8.
 * 找出数组里的重复/不重复数字
 * 找出第k大的数，以及次数
 */
public class FindNumber {
    public static void find(int[] arrs) {
        int[] kth = {-1, 2, 0};
//        findKthLargest(kth, 2);
        System.out.print("u want find num " + findKthLargest(kth, 2));
        int[] out = new int[arrs.length];
        int[] out2 = new int[arrs.length];//不重复数字
        int max = 0;
        for (int i : arrs) {
            max = max < i ? i : max;
        }
        int[] other = new int[max + 1];//要求空间大
        for (int i : arrs) {
            other[i]++;
        }
        int m = 3;
        for (int i = other.length - 1; i >= 0; i--) {
            if (other[i] > 0) {
                m--;
            }
            if (m == 0) {
                System.out.print("u want find num " + i + " repeat time " + other[i] + "\n");
            }
        }
        GenerateData.displayArray(other);
        int j = 0;
        int k = 0;
        for (int i = 0; i < other.length; i++) {
            if (other[i] == 1) {
                out[j] = i;
                j++;
            }
            if (other[i] > 1) {
                out2[k] = i;
                k++;
            }
        }
        GenerateData.displayArray(out);
        GenerateData.displayArray(out2);
        BuckSort.sort(arrs);
        GenerateData.displayArray(arrs);
    }

    public static int findKthLargest(int[] nums, int k) {//包含重复数
        int max = 0;
        int min = 0;
        for (int i : nums) {
            max = max < i ? i : max;
            min = min < i ? min : i;
        }
        int[] other = new int[max + 1];//要求空间大
        int[] other2;
        if (min < 0) {
            other2 = new int[Math.abs(min) + 1];
        } else {
            other2 = new int[max + 1];
        }
//        int[] other2 = new int[max + 1];//要求空间大
        for (int i : nums) {
            if (i >= 0) {
                other[i]++;
            } else {
                other2[Math.abs(i)]++;
            }
        }
        for (int i = other.length - 1; i >= 0; i--) {
            if (other[i] > 0) {
                k= k - other[i];
            }
            if (k <= 0) {
                return i;
            }
        }
        for (int i = 1; i < other2.length; i++) {
            if (other2[i] > 0) {
                k= k - other2[i];
            }
            if (k <= 0) {
                return -i;
            }
        }
        if (k > 0) {
            return min;
        }
        return 0;
    }

    public static List<Integer> arr2List(int[] arrs) {
        List<Integer> list = new ArrayList<>();
        for (int i : arrs) {
            list.add(i);
        }
        return list;
    }

    public static int findMax(List<Integer> arrs) {
        int max = 0;
        for (int i = 0; i < arrs.size(); i++) {
            max = max < arrs.get(i) ? arrs.get(i) : max;
        }
        return max;
    }
}
