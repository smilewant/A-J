package com.further.leetcode;

/**
 * Created by Zion
 * 2019/7/3.
 */
public class Solution235 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        int m = p.val;
        int n = q.val;
        if (m < root.val && n < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (m > root.val && n > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return root;
        }
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
