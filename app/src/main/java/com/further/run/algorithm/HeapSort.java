package com.further.run.algorithm;

/**
 * Created by Hukuan
 * 2019/2/21.
 * 堆排序
 */
public class HeapSort {
    private int[] arr;
    public HeapSort(int[] arr) {
        this.arr = arr;
    }

    public void sort(){
        int len = arr.length - 1;
        int beginIndex = (len - 1) >> 1;
        for (int i = beginIndex; i >= 0; i--) {
            maxHeapify(i, len);
        }
        for (int i = len; i > 0; i--) {
            swap(0, i);
            maxHeapify(0, i - 1);
        }
    }

    private void maxHeapify(int index, int len) {
        int li = (index << 1) + 1;
        int ri = li + 1;
        int cMax = li;
        if (li > len) {
            return;
        }
        if (ri <= len && arr[ri] > arr[li]){
            cMax = ri;
        }
        if (arr[cMax] > arr[index]) {
            swap(cMax, index);
            maxHeapify(cMax,  len);
        }
    }

    private void swap(int cMax, int index) {
        int temp = arr[cMax];
        arr[cMax] = arr[index];
        arr[index] = temp;
    }
}
