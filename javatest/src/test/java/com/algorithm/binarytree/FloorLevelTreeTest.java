package com.algorithm.binarytree;

import org.junit.Test;

import java.util.List;

/**
 * Date:2021/12/7,11:55
 * author:jy
 */
public class FloorLevelTreeTest {
    @Test
    public void floorLevelTreeTest(){
        FloorLevelTree tree = new FloorLevelTree();
        FloorLevelTree.TreeNode treeNode = new FloorLevelTree.TreeNode(10);
        treeNode.left = new FloorLevelTree.TreeNode(5);
        treeNode.right = new FloorLevelTree.TreeNode(18);

        List<FloorLevelTree.TreeNode> list =  tree.floorLevelTree(treeNode);
        for (FloorLevelTree.TreeNode node: list ) {
            System.out.println(node.data);
        }
    }
}
