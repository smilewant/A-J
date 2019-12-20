package com.further.leetcode;

import java.util.ArrayList;
import java.util.List;

import okhttp3.internal.http.RetryAndFollowUpInterceptor;

/**
 * Created by Zion
 * 2019/12/19.
 * <p>
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * <p>
 * 说明：
 * <p>
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,2,1]
 * 输出: 1
 * 示例 2:
 * <p>
 * 输入: [4,1,2,1,2]
 * 输出: 4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/single-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution93 {
    List<String> ipAddress = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        if (s.length() < 4 || s.length() > 12) return ipAddress;
       get(s, "");
        return ipAddress;
    }


    public void get(String s, String temp) {
        if (isLegalIp(temp)) {
            if (s.length() == 0) {
                ipAddress.add(temp.substring(0, temp.length() -1));
            }
            return;
        }
        if (s.length() > 0) {
            for (int i = 0; i < s.length() && i < 3; i++) {
                if (isLegalSubIp(s.substring(0, i + 1))) {

                    String temp1 = temp.concat(s.substring(0, i + 1)).concat(".");
                    if (temp1.split("\\.").length * 3 + s.substring(i+1).length() > 12) continue;
                    get(s.substring(i+1), temp1);
                }
            }
        }
    }


    public boolean isLegalIp(String ip) {
        return ip.split("\\.").length >= 4 ;
    }

    public boolean isLegalSubIp(String sub) {
        if (sub.startsWith("0") && sub.length() > 1) return false;
        return Integer.parseInt(sub) <= 255;
    }
}
