package com.algorithm.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Date:2021/12/7,11:34
 * author:jy
 * <p>
 *     https://blog.csdn.net/z_yemu/article/details/106678716
 * </p>
 */
class FloorLevelTree {
    /**
     * 二叉树同层遍历
     * @param root root TreeNode
     * @return List<TreeNode>
     */
    List<TreeNode> floorLevelTree(TreeNode root){
        List<TreeNode> result = new ArrayList<>();
        TreeNode node = root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()){
            node = queue.poll();
            result.add(node);
            if (node.left != null){
                queue.offer(node.left);
            }
            if (node.right != null){
                queue.offer(node.right);
            }
        }
        return result;
    }
    public static class TreeNode {
        public int data;
        TreeNode left;
        TreeNode right;

        TreeNode(int data) {
            this.data = data;
        }
    }
}
