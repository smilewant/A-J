package com.further.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zion
 * 2019/12/11.
 *
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 *
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution46 {
    List<List<Integer>> totalI = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        List<Integer> originNum = new ArrayList<>();
        for (int i = 0; i <  nums.length; i++) {
            originNum.add(nums[i]);
        }
        addL(new ArrayList<Integer>(), originNum);
        return totalI;
    }

    void addL(List<Integer> head, List<Integer> last){
        if (last.size() <= 1) {
            totalI.add(head);
            totalI.add(last);
            return;
        }
        for (int i = 0; i < last.size(); i++) {

            List<Integer> temp = new ArrayList<>();
            temp.addAll(head);
            temp.add(last.get(i));
            addL(temp, right(last, i));
        }
    }

     List<Integer>  right(List<Integer> last, int i) {
        List<Integer> temp = new ArrayList<>();
        temp.addAll(last);
        temp.remove(i);
        return temp;
    }
}
