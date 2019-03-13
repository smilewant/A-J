package com.further.run.algorithm;

/**
 * Created by Zion
 * 2019/3/8.
 * https://leetcode.com/problems/kth-largest-element-in-an-array/
 */
public class Solution {
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
}
