package com.further.run.algorithm.recursion;

/**
 * Created by Zion
 * 2019/3/27.
 * 有面值1,5,10，20,50,100的人民币，求问10000有多少种组成方法？
 * https://www.zhihu.com/question/315108379
 */
public class CoinSum {
    private static final int MAX = 100;
    public static void sum() {
        int[] coinNums = {1, 5, 10, 20, 50};
        int coinNumSum = 0;
        for (int i : coinNums){
            coinNumSum += i;
        }
        int coinSum = 0;
        coinSum += coinNums.length;
        for (int i = 0; i < coinNums.length; i++) {
            for (int j = i + 1; j < coinNums.length; j++) {
                coinSum += (MAX / (coinNums[i] + coinNums[j]) + 1);
            }
        }
//        for (int i = 0; i < coinNums.length; i++) {
//            for (int j = i + 1; j < coinNums.length; j++) {
//                for (int k = j + 1; k < coinNums.length; k++) {
//                    coinSum += (MAX / (coinNums[i] + coinNums[j] + coinNums[k]) + 1);
//                }
//            }
//        }
        for (int i = 0; i < coinNums.length; i++) {
            for (int j = i + 1; j < coinNums.length; j++) {
                coinSum += (MAX / (coinNumSum - coinNums[i] - coinNums[j]) + 1);
            }
        }

        for (int i = 0; i < coinNums.length; i++) {
                coinSum += (MAX / (coinNumSum - coinNums[i] ) + 1);
        }
        coinSum += (MAX / coinNumSum + 1);

        System.out.print("coinSum : " + coinSum + "\n");

    }
}
