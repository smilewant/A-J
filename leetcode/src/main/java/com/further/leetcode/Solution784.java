package com.further.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zion
 * 2019/12/10.
 *
 * 给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。
 *
 * 示例:
 * 输入: S = "a1b2"
 * 输出: ["a1b2", "a1B2", "A1b2", "A1B2"]
 *
 * 输入: S = "3z4"
 * 输出: ["3z4", "3Z4"]
 *
 * 输入: S = "12345"
 * 输出: ["12345"]
 * 注意：
 *
 * S 的长度不超过12。
 * S 仅由数字和字母组成。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/letter-case-permutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution784 {
    List<String> totalS = new ArrayList<>();

    public List<String> letterCasePermutation(String S) {
        addS("", S);
        System.out.println(totalS.toString());
        return totalS;
    }

    public void addS(String head, String end){
        if (end.length() != 0 && head.length() > 0) {
            totalS.add(head.concat(end));
        }
        if (Character.isLetter(end.charAt(0))) {

            String s = Character.isUpperCase(end.charAt(0)) ? String.valueOf(Character.toLowerCase(end.charAt(0))) : String.valueOf(Character.toUpperCase(end.charAt(0)));
            addS(head.concat(s), end.substring(1));
            addS(head.concat(String.valueOf(end.charAt(0))), end.substring(1));
        }  else {
            addS(head.concat(String.valueOf(end.charAt(0))), end.substring(1));
        }

    }
}
