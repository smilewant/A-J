package com.further.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zion
 * 2019/12/25.
 */
public class Solution13 {
    public int  romanToInt(String s) {

        Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);

        int length = s.length() -1;
        int total = 0;
        while (length > 0) {
            if (length > 1 && romanMap.get(s.charAt(length)) < romanMap.get(s.charAt(length + 1))) {
                total -= romanMap.get(s.charAt(length));

            } else {
                total += romanMap.get(s.charAt(length ));
            }
            length--;
        }

        return total;
    }


}
