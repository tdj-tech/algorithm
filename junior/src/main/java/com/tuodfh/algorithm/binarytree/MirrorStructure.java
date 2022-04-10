package com.tuodfh.algorithm.binarytree;

/**
 * @author tdj
 * 2022/4/10 0010
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 */
public class MirrorStructure {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isMirror(root.left, root.right);
    }

    public boolean isMirror(TreeNode left, TreeNode right) {
        if (left == null ^ right == null) {
            return false;
        }
        if (left == null) {
            return true;
        }
        return left.val == right.val && isMirror(left.left, right.right) && isMirror(left.right, right.left);
    }

}

