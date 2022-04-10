package com.tuodfh.algorithm.binarytree;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tdj
 * 2022/4/10 0010
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历，
 * inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 */
public class RebuildTreeByPreInOrder {

    /**
     * 重建树结构
     * preorder = [3,9,20,15,7]
     * inorder = [9,3,15,20,7]
     * @param preorder 先序树
     * @param inorder 中序树
     * @return 树根节点
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }
        Map<Integer, Integer> inMap = new HashMap<>(8);
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length -1, inMap);
    }

    public TreeNode buildTree(int[] preorder, int l1, int r1, int[] inorder, int l2, int r2, Map<Integer, Integer> inMap) {
        if (l1 > r1) {
            return null;
        }
        TreeNode head = new TreeNode(preorder[l1]);
        if (l1 == r1) {
            return head;
        }
        int index = inMap.get(preorder[l1]);
        head.left = buildTree(preorder, l1 + 1, index - l2 + l1, inorder, l2, index - 1, inMap);
        head.right = buildTree(preorder, index - l2 + l1 + 1, r1, inorder, index + 1, r2, inMap);
        return head;
    }

}
