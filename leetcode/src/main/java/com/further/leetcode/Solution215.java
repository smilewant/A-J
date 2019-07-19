package com.further.leetcode;

/**
 * Created by Zion
 * 2019/3/8.
 * https://leetcode.com/problems/kth-largest-element-in-an-array/
 */
public class Solution215 {
    public int findKthLargest(int[] arr, int k) {
        if(arr.length == 1) return arr[0];
        int len = arr.length - 1;
        int beginIndex = (len - 1) >> 1;
        for (int i = beginIndex; i >= 0; i--) {
            maxHeapify(arr,i, len);
        }

        for (int i = len; i >= 0; i--) {
            swap(arr, 0, i);
            k--;
            if (k <= 0) {
                return arr[i];
            }
            maxHeapify(arr,0, i - 1);
        }
        return 0;
    }

    private void maxHeapify(int[] arr, int index, int len) {

        int li = (index << 1) + 1;
        int ri = li + 1;
        int cMax = li;
        if (li > len) {
            return;
        }
        if (ri <= len && arr[ri] > arr[li]) {
            cMax = ri;
        }
        if (arr[cMax] > arr[index]) {
            swap(arr, cMax, index);
            maxHeapify(arr, cMax,  len);
        }
    }

    private void swap(int[] arr, int cMax, int index) {
        int temp = arr[cMax];
        arr[cMax] = arr[index];
        arr[index] = temp;
    }

    public static int findKthLargest_Beta(int[] nums, int k) {//包含重复数
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
}
