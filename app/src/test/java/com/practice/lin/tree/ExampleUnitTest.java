package com.practice.lin.tree;

import android.util.Log;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    int[] array2 = {4, 5};//测试删除根节点只有右孩子
    int[] array3 = {4, 2};//测试删除根节点只有左孩子
    int[] array4 = {11, 9, 12};//测试删除叶子
    int[] array5 = {11};//测试删除只有根
    /**
     * 删除5，15，28，39，可以测试删除非根节点只有左/右孩子的情况
     */
    int[] array7 = {50, 25, 75, 10, 35, 5, 15, 28, 39, 6, 14, 27, 42};

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int i : array7) {
            tree.insert(i);
            //tree.put(i);
        }
        Traverse.midOrderTraverse(tree.root);
        System.out.println();
        tree.delNode(50);
        Traverse.midOrderTraverse(tree.root);

    }

    @Test
    public void testHuffman() {

        ArrayList<TreeNode> list = new ArrayList<>();

        TreeNode treeNode0 = new TreeNode("good50", 50);
        TreeNode treeNode1 = new TreeNode("good10", 10);
        TreeNode treeNode2 = new TreeNode("good20", 20);
        TreeNode treeNode3 = new TreeNode("good110", 110);
        TreeNode treeNode4 = new TreeNode("good200", 200);
        TreeNode treeNode5 = new TreeNode("good10_2", 10);
        TreeNode treeNode6 = new TreeNode("good50_2", 50);


        list.add(treeNode0);
        list.add(treeNode1);
        list.add(treeNode2);
        list.add(treeNode3);
        list.add(treeNode4);
        list.add(treeNode5);
        list.add(treeNode6);

        HuffmanTree tree = new HuffmanTree();
        tree.createHuffmanTree(list);

        Traverse.showHuffman(tree.root);


        System.out.println(tree.getCode(treeNode2));
    }


}