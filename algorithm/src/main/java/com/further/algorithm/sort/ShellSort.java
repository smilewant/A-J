package com.further.algorithm.sort;

/**
 * Created by Hukuan
 * 2018/4/26.
 * 希尔排序
 * 根据步长由长到短分组，进行排序，直到步长为1为止，属于插入排序的一种。
 */
public class ShellSort {

    public static void sort(int[] arrays) {
        int size = arrays.length;
        for (int gap = size / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < size; i++) {
                int temp = arrays[i];
                int j;
                for (j = i; j >= gap && arrays[j - gap] > temp; j -= gap) {
                    arrays[j] = arrays[j - gap];
                }
                arrays[j] = temp;
            }
        }
    }
}
