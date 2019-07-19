package com.further.leetcode;

/**
 * Created by Zion
 * 2019/7/4.
 */
public class Solution6 {

    public static String convert(String s, int numRows) {
        if (numRows == 1) return s;
        int i = 0;
        StringBuilder[] rows = new StringBuilder[numRows];
        boolean goingDown = false;
        for (int m = 0; m < s.length(); m++) {
            if (rows[i] == null) {
                rows[i] = new StringBuilder();
            }
            rows[i].append(s.charAt(m));

            if (i == 0 || i == numRows - 1) goingDown = !goingDown;
            i += goingDown ? 1 : -1;

        }
        StringBuilder st = new StringBuilder();
        for (StringBuilder sb : rows) {
            st.append(sb);
        }
        return st.toString();
    }

}
