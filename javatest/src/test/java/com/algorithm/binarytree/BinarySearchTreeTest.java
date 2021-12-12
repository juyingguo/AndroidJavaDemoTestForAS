package com.algorithm.binarytree;

import org.junit.Test;

import java.util.List;

/**
 * Date:2021/12/7,14:11
 * author:jy
 */
public class BinarySearchTreeTest {
    @Test
    public void findTest(){
        BinarySearchTree tree = new BinarySearchTree();
        BinarySearchTree.Node rootTreeNode = new BinarySearchTree.Node(10);
        rootTreeNode.setLeft(new BinarySearchTree.Node(5));
        rootTreeNode.setRight(new BinarySearchTree.Node(19));

        tree.setTree(rootTreeNode);
        BinarySearchTree.Node node = tree.find(19);
        if (node != null)
            System.out.println(node.getData());
    }
    @Test
    public void insertTest(){
        BinarySearchTree tree = new BinarySearchTree();
        BinarySearchTree.Node rootTreeNode = new BinarySearchTree.Node(10);
        rootTreeNode.setLeft(new BinarySearchTree.Node(5));
        rootTreeNode.setRight(new BinarySearchTree.Node(19));

        tree.setTree(rootTreeNode);
        tree.insert(29);
        tree.insert(8);
        List<BinarySearchTree.Node> list =  tree.floorLevelTree(rootTreeNode);
        for (BinarySearchTree.Node node: list ) {
            System.out.println(node.getData());
        }
    }
}
