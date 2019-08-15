package com.further.algorithm.sort;

/**
 * Created by Hukuan
 * 2018/4/26.
 * 插入排序
 */
public class InsertionSort {

    public static void sort(int[] arrays) {

        for (int i = 0; i < arrays.length; i++) {
            int temp = arrays[i];
            for (int j = i - 1; j > -1; j--) {
                if (arrays[j] > temp) {
                    arrays[j + 1] = arrays[j];
                    arrays[j] = temp;

                }
            }
        }
    }
}
