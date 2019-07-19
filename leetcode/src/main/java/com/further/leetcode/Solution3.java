package com.further.leetcode;

/**
 * Created by Zion
 * 2019/6/26.
 */
public class Solution3 {
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        int[] index = new int[128]; // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return ans;

//        int length = 0;
//        LinkedBlockingQueue queue = new LinkedBlockingQueue();
//        for (int i = 0; i < s.length(); i++) {
//            if (queue.contains(s.charAt(i))) {
//
//                while(!queue.poll().equals(s.charAt(i))) {
//
//                }
//
//            }
//            queue.offer(s.charAt(i));
//            length = queue.size() > length ? queue.size() : length;
//        }
//        HashMap<Character, Integer> map = new HashMap<>();
//        for (int i = 0; i < s.length(); i++) {
//            if (map.containsKey(s.charAt(i))){
//                int index  = map.get(s.charAt(i));
//                Iterator<Map.Entry<Character, Integer>> iterator = map.entrySet().iterator();
//                while(iterator.hasNext()) {
//                    Map.Entry<Character, Integer> entry =  iterator.next();
//                    if (entry.getValue() <= index) iterator.remove();
//
//                }
//
//            }
//            map.put(s.charAt(i), i);
//            length = map.size() > length? map.size() :length;
//
//        }
//
//        List<Character> list = new ArrayList<>();
//        for (int i = 0; i < s.length(); i++) {
//            if (list.contains(s.charAt(i))) {
//                while(!list.get(0).equals(s.charAt(i))){
//                    list.remove(0);
//                }
//                list.remove(0);
//            }
//           list.add(s.charAt(i));
//            length = list.size() > length? list.size() :length;
//        }
//
//        return length;
    }


}
