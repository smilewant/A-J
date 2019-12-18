package com.further.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Zion
 * 2019/11/26.
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 *
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 示例 1:
 *
 * 输入: [3,3,5,0,0,3,1,4]
 * 输出: 6
 * 解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 *      随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
 * 示例 2:
 *
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。  
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。  
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例 3:
 *
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。
 *
 * [1,2,4,2,5,7,2,4,9,0]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution123 {
    public int maxProfit(int[] prices) {
        if (prices.length < 2) return 0;
        List<Integer> indexL = new ArrayList<>();
        int min = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] <= prices[i-1]) {
                min = prices[i];
            }
            if (prices[i] > min && min != 0) {
                indexL.add(i-1);
                indexL.add(i);
            }
        }
        return maxP(Arrays.copyOfRange(prices, indexL.get(0), indexL.get(indexL.size())));
    }
    public int maxP(int[] prices) {
        if (prices.length < 2) return 0;
        List<Integer> indexL = new ArrayList<>();
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < prices[i-1]) {
                indexL.add(i);
            }
        }
        int total = 0;
        if (indexL.size() == 0) {
            total = prices[prices.length -1] - prices[0];
        }
        if(indexL.size() == prices.length - 1) {
            return 0;
        }
        for (Integer i : indexL) {
            total = Math.max(total, maxSub(0,i,prices));
        }

        return total;
    }

    public int maxSub(int start, int end, int[] prices) {
        int total_1 =profit(new int[2], end- start, Arrays.copyOfRange(prices, start, end))[0];
        int total_2 = profit(new int[2], prices.length - end, Arrays.copyOfRange(prices, end, prices.length))[0];
        return total_1 + total_2;
    }


    int[] profit(int[] a, int lastLength, int[] prices) {
        if (lastLength < 2) return new int[]{0,0};
        if (lastLength == 2) {
            a[0] = prices[1] > prices[0] ? prices[1] - prices[0] : 0;
            a[1] = Math.min(prices[0], prices[1]);

            return a;
        }

        profit(a, lastLength - 1, prices);


        if (prices[lastLength - 1] < a[1]) {
            a[1] = prices[lastLength - 1];
        }
        if (a[0] < prices[lastLength - 1] - a[1]) {
            a[0] = prices[lastLength - 1] - a[1];
        }

        return a;
    }
}
