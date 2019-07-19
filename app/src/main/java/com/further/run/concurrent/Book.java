package com.further.run.concurrent;


/**
 * Created by Zion
 * 2019/6/11.
 */
public class Book {
    public static void main(String[] args) {
//        startFuction();
    }

//    public static void startFuction() {
//        System.out.println("书的静态方法");
//    }
//
//    static Book book = new Book();
//    static {
//        System.out.println("书的静态代码块");
//    }
//    {
//        System.out.println("书的普通代码块");
//    }
//
//    Book(){
//        System.out.println("书的构造方法");
//        System.out.println("price = " + price +",amount = " + amount);
//    }


    int price = 110;
    static int amount = 112;

    TreeNode invertTree(TreeNode root){
        if(root == null) return root;
        if (root.left == null && root.right == null) return root;
        invertTree(root.left);
        invertTree(root.right);
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        return  root;
    }


}

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {
        val = x;
    }
}
