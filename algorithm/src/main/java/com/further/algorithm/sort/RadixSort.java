package com.further.algorithm.sort;

/**
 * Created by Hukuan
 * 基数排序
 * MSD LSD wrong
 */
public class RadixSort {

    public static void sort(int[] arrays) {
        int temp = 0;
        for (int i = 0; i < 10; i++) {
            temp = arrays[i] > temp ? arrays[i] : temp;
        }
        int n = 1;
        while ((temp /= 10) > 1) {
            n++;
        }

        int digit = 1;
        int k = 0;
        int[][] number = new int[10][arrays.length];
        int[] order = new int[10];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < arrays.length; j++) {
                int locate = arrays[j] / digit % 10;
                number[locate][order[locate]] = arrays[j];
                order[locate]++;
            }
            for (int coordinate = 0; coordinate < 10; coordinate++) {
                if (order[coordinate] > 0) {
                    for (int j = 0; j < order[coordinate]; j++) {
                        arrays[k] = number[coordinate][j];
                        k++;
                    }
                    order[coordinate] = 0;
                }
            }
            digit *= 10;
            k = 0;
        }
    }
}
