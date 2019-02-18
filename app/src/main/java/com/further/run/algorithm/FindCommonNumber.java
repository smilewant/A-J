package com.further.run.algorithm;

import com.further.run.log.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Hukuan
 * 2018/5/24.
 * input：234
 * arrays[] (
 * 2 abc
 * 3 def
 * 4 ghi
 * 5 jkl
 * 6 mno
 * 7 pqr
 * 8 stu
 * 9 vwx
 * 10 yz
 */
public class FindCommonNumber {
    private static HashMap<String, String> num2letterMap = new HashMap<>();
    private static String[] letterStrs= {"efs", "dgl", "anu", "egq", "awzsg"};

    public static void find(){
        init();
        letter2num();
    }

    private static void letter2num(){
        HashMap<String, String> tempMap = new HashMap<>();
        for (String letter : letterStrs){
            StringBuilder sb = new StringBuilder();
            for (char c : letter.toCharArray()){
                sb.append(getLetterNum(c));
            }
            tempMap.put(sb.toString(), letter);
        }
        LogUtil.d("tempMap : " + tempMap.toString());

        String numSt = "234";
        List<String> finalList = new ArrayList<>();
        Iterator<Map.Entry<String, String>> t = tempMap.entrySet().iterator();
        while(t.hasNext()){
            Map.Entry<String, String> entry = t.next();
            if (entry.getKey().contains(numSt)){
                finalList.add(entry.getValue());
            }
        }
        LogUtil.d("finalList : " + finalList.toString());
    }

    private static String getLetterNum(char c) {
        for (Map.Entry<String, String> entry : num2letterMap.entrySet()) {
            for (char l : entry.getValue().toCharArray()) {
                if (l == c) {
                    return entry.getKey();
                }
            }
        }
        return "";
    }

    private static void init(){
        num2letterMap.put("1", "abc");
        num2letterMap.put("2", "def");
        num2letterMap.put("3", "ghi");
        num2letterMap.put("4", "jkl");
        num2letterMap.put("5", "mno");
        num2letterMap.put("6", "pqr");
        num2letterMap.put("7", "stu");
        num2letterMap.put("8", "vwx");
        num2letterMap.put("9", "yz");
    }
}
