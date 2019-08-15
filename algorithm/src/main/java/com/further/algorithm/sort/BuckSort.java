package com.further.algorithm.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hukuan
 * 桶排序： 将数组分到有限数量的桶里。每个桶再个别排序
 * 2019/2/28.
 */
public class BuckSort {
    public static void sort(int[] arrs) {
        int max = arrs[0], min = arrs[0];
        for (int i : arrs) {
            max = max > i ? max : i;
            min = min < i ? min : i;
        }
        int buckNum = (max - min) / 10 + 1;
        List buckList = new ArrayList<List<Integer>>();
        for (int i = 1; i <= buckNum; i++) {
            buckList.add(new ArrayList<>());
        }
        for (int i = 0; i < arrs.length; i++) {
            int index = (arrs[i] - min) / 10;
            ((ArrayList<Integer>) buckList.get(index)).add(arrs[i]);
        }

        ArrayList<Integer> bucket = null;
        int index = 0;
        for (int i = 0; i < buckNum; i++) {
            bucket = (ArrayList<Integer>) buckList.get(i);
            insertSort(bucket);
            for (int k : bucket) {
                arrs[index++] = k;
            }
        }
    }

    private static void insertSort(ArrayList<Integer> bucket) {
        for (int i = 1; i < bucket.size(); i++) {
            int temp = bucket.get(i);
            int j = i - 1;
            for (; j >= 0 && bucket.get(j) > temp; j--) {
                bucket.set(j + 1, bucket.get(j));
            }
            bucket.set(j + 1, temp);
        }
    }
}
