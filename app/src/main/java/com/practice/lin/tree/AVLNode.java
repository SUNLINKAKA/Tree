package com.practice.lin.tree;

/**
 * create on 2018/12/16 22:01
 * Email:sunlin0737@outlook.com
 *
 * @author lin
 */
public class AVLNode<AnyType extends Comparable<? super AnyType>> {

    int height;

    AnyType element;

    AVLNode<AnyType> parent;

    AVLNode<AnyType> right;

    AVLNode<AnyType> left;


    public AVLNode(AnyType element, AVLNode<AnyType> parent, AVLNode<AnyType> right, AVLNode<AnyType> left) {
        this.element = element;
        this.parent = parent;
        this.right = right;
        this.left = left;
        this.height = 0;
    }

    public AVLNode(AnyType element) {
        this(element, null, null, null);
    }
}
