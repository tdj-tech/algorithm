package com.tuodfh.algorithm.binarytree;

/**
 * @author tdj
 * 2022/4/10 0010
 * 二叉树结构
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode() {
    }

    TreeNode(int val) { this.val = val; }

    TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
        this.right = right;
    }

}
