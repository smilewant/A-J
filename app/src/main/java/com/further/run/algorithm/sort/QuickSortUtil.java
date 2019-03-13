package com.further.run.algorithm.sort;

import android.util.Log;

/**
 * Created by Hukuan
 * 2018/4/26.
 * 快速排序
 */
public class QuickSortUtil {
    public static void sort(int[] arr, int low, int high) {
        int i = low;
        int j = high;
        int key = arr[i];
        while (i < j) {
            for (; j > i; j--) {
                if (arr[j] < key) {
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                    i++;
                    break;
                }
            }
            Log.d("TAG_1 ", "TAG_1 " + displayArray(arr));
            for (; i < j; i++) {
                if (key < arr[i]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    j--;
                    break;
                }
            }
            Log.d("TAG_2",  "TAG_2 " + displayArray(arr));
        }
        Log.d("TAG_2",  "low " + low + " i " + i + " high " + high);
        if (i > low) sort(arr, low, i - 1);
        if (i < high) sort(arr, i + 1, high);
    }

    private static String displayArray(int[] arrays) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int a : arrays){
            stringBuilder.append(a).append(",");
        }
        return stringBuilder.toString();

    }

//    public static void sort(int arr[], int low, int high) {
//        int l = low;
//        int h = high;
//        int povit = arr[low];
//
//        while (l < h) {
//            while (l < h && arr[h] >= povit)
//                h--;
//            if (l < h) {
//                int temp = arr[h];
//                arr[h] = arr[l];
//                arr[l] = temp;
//                l++;
//            }
//
//            while (l < h && arr[l] <= povit)
//                l++;
//
//            if (l < h) {
//                int temp = arr[h];
//                arr[h] = arr[l];
//                arr[l] = temp;
//                h--;
//            }
//        }
//        System.out.print("l=" + (l + 1) + "h=" + (h + 1) + "povit=" + povit + "\n");
////        if (l > low) sort(arr, low, l - 1);
////        if (h < high) sort(arr, l + 1, high);
//    }
}
