package com.further.run.concurrent;

//import com.further.leetcode.Solution19;
//import com.further.leetcode.Solution24;
//import com.further.leetcode.Solution25;
//import com.further.leetcode.Solution6;
//import com.further.leetcode.Solution7;
import com.further.algorithm.recursion.CoinSum;
import com.further.leetcode.Solution784;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Hukuan
 * 2018/5/9.
 */
class Test {
    static Thread t1;
    static Thread t2;

    enum Event{
        A("detail");
        String detail;
        Event(String detail) {
            this.detail = detail;
        }
    }

    public static String code(String str) throws NoSuchAlgorithmException {
        MessageDigest alga;
        String myinfo = str;
        alga = MessageDigest.getInstance("MD5");
        alga.update(myinfo.getBytes());
        byte[] digesta = alga.digest();
        String hs = "";
        String stmp = "";
        for (int n = 0; n < digesta.length; n++) {
            stmp = (java.lang.Integer.toHexString(digesta[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }
        return hs.toUpperCase();
    }

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Solution784 solution25 = new Solution784();
        solution25.letterCasePermutation("a1b2");

        CoinSum.sum();
        Event.A.name();
//        int[] arrd = {0, 2, 1, 1, 4, 1, 1, 2, 3, 4};
//        Stack begin = new Stack();
//        begin.push("a");
//        begin.push("b");
//        begin.push("c");
//        begin.push("d");
//        begin.push("e");
//        Hanota.hanota(begin.size(), begin);
////        new HeapSort(arrd).sort();
//        Solution26 solution = new Solution26();
        System.out.print(URLEncoder.encode("Redmi Note 7+".replaceAll("\\s*", ""), "UTF-8"));
        System.out.print("Event.A.name() : " + Event.A.detail + "\n");

        System.out.print("so " + code(URLEncoder.encode("Redmi Note 7+".replaceAll("\\s*", ""), "UTF-8")) + "\n");
//        int[] a = GenerateData.generateEventR(20);
//        GenerateData.displayArray(a);
//        FindNumber.find(a);
//
//        System.out.println(String.format("%.2f", 5.00000001d));
//        System.out.println("领\u0020\u0020\u0020\u0020券");
//        System.out.println("领领领券");
//        int[] nums = new int[] {-1, 0, 1, 2, -1, -4};
//        ThreeSum threeSum = new ThreeSum();
//        threeSum.threeSum(nums);
//        showFiles(new File("D:"), "");
//        List<String> imageList = new ArrayList<>();
//        imageList.add("1");
//        imageList.add("2");
//        int j = imageList.size();
//        while (imageList.size() > 1 && imageList.size() < 7) {
//            //如果图片的数量很少，执行instantiateItem的时候没有destroy会报错，viewpager的缓存一般是1，正常只要大于3就可以了，这里保险起见设为7
//            imageList.addAll(imageList);
//            j += j;
//        }
//        for (int i = 0; i < imageList.size(); i++) {
//            System.out.println(imageList.get(i) + "\n");
//        }
//        System.out.println(imageList);
//        VolatileTest.futureOperate2();
//        StringBuilder stringBuilder = new StringBuilder();
//        for (int i = 0 ; i <  10; i++) {
//            stringBuilder.append(i + ",");
//        }
//        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
//        stringBuilder.append("\n");
//        for (int i = 0 ; i <  10; i++) {
//            stringBuilder.append(i + ",");
//        }
//        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
//        stringBuilder.reverse();
//        System.out.print("getI " + stringBuilder.toString() + " - ");
//        testSequenceThread();
//        Long l = 12l;
//        boolean a = l.equals(12);
//        System.out.print("a :  " + Boolean.toString(a) + "\n");
//
//        Runnable runnable1 = new Runnable() {
//            @Override
//            public void run() {
//                int i = 0;
//                System.out.print("getI " + i + " - ");
//            }
//        };
//        runnable1.run();
//        new Thread(runnable1).start();
//        gee g = new gee() {
//            @Override
//            void run() {
//                int i = 1;
//                System.out.print("getI " + i + " - ");
//            }
//        };
//        g.run();

//        Other other = new Other();
//        String hello = "Hello", lo = "lo";
//        System.out.print((hello == "Hello") + " ");
//        System.out.print((other.hello == hello) + " ");
//        System.out.print((com.further.run.anno.Other.hello == hello) + " ");
//        System.out.print((hello == ("Hel"+"lo")) + " ");
//        System.out.print((hello == ("Hel"+lo)) + " ");
//        System.out.println(hello == ("Hel"+lo).intern());


        Integer A = 1;

        Integer B = 1;

        Integer C = new Integer(1);

        Integer D = 129;

        Integer E = 129;

        System.out.print(A == B);
        System.out.print(A == C);
        System.out.print(D);

        /*
        一副从1到n的牌，每次从牌堆顶取一张放桌子上，再取一张放牌堆底，直到手机没牌，最后桌子上的牌是从1到n有序，设计程序，输入n，输出牌堆的顺序数组
         */
        int n = 13;

        LinkedList<Integer> list = new LinkedList<Integer>();

        LinkedList<Integer> sslist = new LinkedList<Integer>();

        for (int i = 1; i <= n; i++) {

            list.add(i);

        }

        boolean flag = true;

        while (list.size() > 0) {

            if (flag) {

                sslist.add(list.get(0));

                list.remove(0);

                flag = false;

            } else {

                list.add(list.get(0));

                list.remove(0);

                flag = true;

            }

        }

        Map<Object, Object> map = new HashMap<Object, Object>();

        int num = 1;

        System.out.print(sslist.toString() + " \n  ");
        for (Integer integer : sslist) {

            map.put(integer, num++);

        }

        Collection<Object> values = map.values();

        for (Object object : values) {

            System.out.print(object + ", ");

        }

    }

    private static void showFiles(File file, String blank) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                System.out.println(blank + f.getAbsolutePath());
                showFiles(f, blank + " ");
            }
        } else {
            System.out.println(blank + file.getAbsolutePath());
        }
    }


    abstract static class gee {
        abstract void run();
    }

    private static int getI() {
        int i;
        try {
            i = 1 / 0;
        } catch (Exception e) {
            i = 2;
        } finally {
//            i = 3;
        }
        System.out.print("getI " + i + " - ");
        return i;

    }

    /*
        测试线程执行顺序 start
        哪些方法可以限定线程的执行顺序：join？synchronized?
     */
    private static void testSequenceThread() {
        t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                method(Thread.currentThread());
            }
        }, "t1");
        t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    t1.join();
                } catch (Exception e) {

                }
                method(Thread.currentThread());
            }
        }, "t2");
        t1.start();
        t2.start();
    }

    private static void method(Thread thread) {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(thread.getName() + "持有锁 " + t1.isAlive() + "  " + t2.isAlive());

        }

    }
     /*
        测试线程执行顺序 end
     */


    //input: {A=[a1, a2, a3, a4, a5, a6, a7, a8], B=[b1, b2, b3]}
//output: {A=[a3], A=[a4], A=[a2], A=[a8], A=[a7], A=[a6], A=[a5], A=[a1]}{B=[b1], B=[b2], B=[b3]}
    private static void list() {
        Map<String, List<String>> hashmap = new LinkedHashMap<>();
        List<String> A_1 = new LinkedList<String>() {
        };
        A_1.add("a1");
        A_1.add("a2");
        A_1.add("a3");
        A_1.add("a4");
        A_1.add("a5");
        A_1.add("a6");
        A_1.add("a7");
        A_1.add("a8");
        List<String> B_1 = new LinkedList<>();
        B_1.add("b1");
        B_1.add("b2");
        B_1.add("b3");
        hashmap.put("A", A_1);
        hashmap.put("B", B_1);
        System.out.print(hashmap.toString());
        for (String string : A_1) {
            System.out.println(string);
        }

        Set<Map.Entry<String, List<String>>> set = hashmap.entrySet();
        for (Map.Entry<String, List<String>> entry : set) {


            Map<String, List<String>> hashmap1 = new IdentityHashMap<>();

            for (String string : entry.getValue()) {
                List<String> tempList = new ArrayList<>();
                tempList.add(string);
                hashmap1.put(new String(entry.getKey()), tempList);
            }
            System.out.print(hashmap1.toString());

        }

    }

}

class Other {
    String hello = "Hello";
}
