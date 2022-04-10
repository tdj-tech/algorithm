package com.tuodfh.algorithm.binarytree;

/**
 * @author tdj
 * 2022/4/10 0010
 * 给定一个二叉树，找出其最大深度。
 */
public class MaxDepth {

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

}
