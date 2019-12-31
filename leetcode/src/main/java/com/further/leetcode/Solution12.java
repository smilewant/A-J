package com.further.leetcode;

/**
 * Created by Zion
 * 2019/12/25.
 */
public class Solution12 {
    public String intToRoman(int num) {
        int index = num / 5;
        int remain = num % 5;
        String[] romanChars = {"I", "V", "X", "L", "C", "D", "M"};
        int[] romanIndex = {0, 1, 2, 10, 20, 100, 200};
       int x = romanIndex.length - 1;
       int y = 0;
       String roman = "";
       while (index >= 0 && x > 0) {
           if (index >= romanIndex[x]){
               y++;
               index = index - romanIndex[x];
           } else {
               if (y == 4) {

                   if (roman.endsWith(romanChars[x+1])) {
                       roman = roman.substring(0, roman.length() - 1).concat(romanChars[x] + romanChars[x+2]);

                   } else {
                       roman = roman.concat(romanChars[x] + romanChars[x + 1]);
                   }
               } else {
                   roman = roman.concat(romanChars[x]);
               }

               y = 0;
               x--;

           }
       }
       if (remain == 4) {
           if (roman.endsWith("V")) {
               roman = roman.substring(0, roman.length() - 1).concat("IX");
           } else {
               roman = roman.concat("IV");
           }
       } else {
           while (remain > 0) {
               roman = roman.concat("I");
               remain--;
           }
       }
        return roman;

    }


}
