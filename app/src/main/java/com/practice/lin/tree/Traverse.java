package com.practice.lin.tree;

import java.util.LinkedList;

/**
 * create on 2018/12/9 17:16
 * Email:sunlin0737@outlook.com
 *
 * @author lin
 */
public class Traverse<AnyType extends Comparable<? super AnyType>> {

    public static <AnyType extends Comparable<? super AnyType>> void midOrderTraverse(TreeNode<AnyType> root) {
        if (root == null) {
            return;
        }
        midOrderTraverse(root.leftChild);
        System.out.print(root.element + "  ");
        midOrderTraverse(root.rightChild);
    }


    public static <AnyType extends Comparable<? super AnyType>> void showHuffman(TreeNode<AnyType> root) {
        LinkedList<TreeNode> list = new LinkedList<>();
        list.offer(root);
        while (!list.isEmpty()) {
            TreeNode node = list.pop();
            System.out.println(node.element);

            if (node.leftChild != null) {
                list.offer(node.leftChild);
            }

            if (node.rightChild != null) {
                list.offer(node.rightChild);
            }
        }
    }


    public static <AnyType extends Comparable<? super AnyType>> void showTree(AVLNode<AnyType> root) {
        LinkedList<AVLNode> list = new LinkedList<>();
        list.offer(root);
        while (!list.isEmpty()) {
            AVLNode node = list.pop();
            System.out.print(node.element + "   ");

            if (node.left != null) {
                list.offer(node.left);
            }

            if (node.right != null) {
                list.offer(node.right);
            }
        }
    }
}
