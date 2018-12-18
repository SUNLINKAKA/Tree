package com.practice.lin.tree;

import android.support.annotation.NonNull;

/**
 * create on 2018/12/9 16:03
 * Email:sunlin0737@outlook.com
 *
 * @author lin
 */
public class TreeNode<AnyType extends Comparable<? super AnyType>> implements Comparable<TreeNode<AnyType>> {

    /**
     * 节点所存数据
     */
    AnyType element;
    /**
     * 当前节点的左孩子
     */
    TreeNode<AnyType> leftChild;
    /**
     * 当前节点的右孩子
     */
    TreeNode<AnyType> rightChild;
    /**
     * 当前节点的父节点
     */
    TreeNode<AnyType> parent;


    /**
     * 权重（huffman树）
     */
    int weight;

    public TreeNode(AnyType element, TreeNode<AnyType> leftChild, TreeNode<AnyType> rightChild, TreeNode<AnyType> parent, int weight) {
        this.element = element;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.parent = parent;
        this.weight = weight;
    }

    public TreeNode(AnyType element, TreeNode<AnyType> leftChild, TreeNode<AnyType> rightChild, TreeNode<AnyType> parent) {
        this.element = element;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.parent = parent;
    }

    public TreeNode(AnyType element) {
        this(element, null, null, null);
    }

    public TreeNode(String element, int weight) {
        this.element = (AnyType) element;
        this.weight = weight;
    }


    @Override
    public int compareTo(@NonNull TreeNode<AnyType> o) {
        if (o.weight > this.weight) {
            return 1;
        } else if (o.weight < this.weight) {
            return -1;
        }
        return 0;
    }
}
