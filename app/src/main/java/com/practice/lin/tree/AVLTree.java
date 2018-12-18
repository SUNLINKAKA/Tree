package com.practice.lin.tree;

/**
 * create on 2018/12/16 21:59
 * Email:sunlin0737@outlook.com
 *
 * @author lin
 */
public class AVLTree<AnyType> {

    private AVLNode<AnyType> root;

    /**
     * 单旋转，左偏
     *
     * @param k2
     * @return
     */
    private AVLNode<AnyType> rotateWithLeftChild(AVLNode k2) {

        if (k2 == null) {
            throw new NullPointerException();
        }

        AVLNode k1 = k2.left;
        k2.left = k1.right;

        if (k1.right != null) {
            k1.right.parent = k2;
        }

        k1.parent = k2.parent;
        if (k2.parent == null) {
            k1 = root;
        } else {
            if (k2.parent.left == k2) {
                k2.parent.left = k1;
            } else if (k2.parent.right == k2) {
                k2.parent.right = k1;
            }
        }

        k2.parent = k1;
        k1.right = k2;


        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;

        return k1;
    }


    private AVLNode<AnyType> rotateWithRightChild(AVLNode<AnyType> k2) {

        if (k2 == null) {
            throw new NullPointerException();
        }
        AVLNode<AnyType> k1 = k2.right;

        k2.right = k1.left;
        if (k1.left != null) {
            k1.left.parent = k2;
        }

        k1.parent = k2.parent;
        if (k2.parent == null) {
            k1 = root;
        } else {
            if (k2.parent.left == k2) {
                k2.parent.left = k1;
            } else if (k2.parent.right == k2) {
                k2.parent.right = k1;
            }
        }

        k1.left = k2;
        k2.parent = k1;

        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.right), k2.height) + 1;
        return k1;

    }

    private int height(AVLNode node) {
        return node == null ? -1 : node.height;
    }
}
