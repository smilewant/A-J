package com.further.run.algorithm.sort;

/**
 * Created by Zion
 * 2019/2/21.
 * 堆排序
 */
public class HeapSort {
    private int[] arr;

    public HeapSort(int[] arr) {
        this.arr = arr;
    }

    public void sort() {
        int len = arr.length - 1;
        int beginIndex = (len - 1) >> 1;
        for (int i = beginIndex; i >= 0; i--) {
            maxHeapify(i, len);
        }
        System.out.print("==================================");
        int k = 2;
        for (int i = len; i > 0; i--) {

            swap(0, i);
            k--;
            if (k <= 0) {
                System.out.print("==================================" + arr[i] + "\n");
            }
            maxHeapify(0, i - 1);
        }
    }

    private void maxHeapify(int index, int len) {
        System.out.print("arr : " + displayArray(arr) + "\n");
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
            swap(cMax, index);
            maxHeapify(cMax, len);
        }
    }

    private String displayArray(int[] arrays) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int a : arrays) {
            stringBuilder.append(a).append(",");
        }
        return stringBuilder.toString();

    }

    private void swap(int cMax, int index) {
        int temp = arr[cMax];
        arr[cMax] = arr[index];
        arr[index] = temp;
    }
}
