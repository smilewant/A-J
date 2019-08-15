package com.further.algorithm;

import java.util.Random;

/**
 * Created by Zion
 * 2019/3/8.
 */
public class GenerateData {
    //生成随机不重复的数据
    public static int[] generateEvent(int size) {
        int[] arrays = new int[size];
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        int[] x = new int[100];
        for (int i = 0; i < 99; i++) {
            x[i] = i;
        }
        for (int i = 0; i < size; i++) {
            int ran = random.nextInt(100 - i);
            int temp = x[ran];
            x[ran] = x[99 - i];
            x[99 - i] = temp;
            arrays[i] = x[99 - i];
        }
        return arrays;
    }
    //生成随机数据（会重复）
    public static int[] generateEventR(int size) {
        int[] arrays = new int[size];
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());

        for (int i = 0; i < size; i++) {
            arrays[i] = random.nextInt(20);

        }
        return arrays;
    }

    public static String displayArray(int[] arrays) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int a : arrays){
            stringBuilder.append(a).append(",");
        }
        System.out.print("arrs " + stringBuilder.toString() + "\n");
        return stringBuilder.toString();

    }
}
