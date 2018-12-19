package com.practice.lin.tree;

/**
 * create on 2018/12/16 21:59
 * Email:sunlin0737@outlook.com
 *
 * @author lin
 */
public class AVLTree<AnyType extends Comparable<? super AnyType>> {

    /**
     * 平衡临界，大于该值表示失去平衡
     */
    private static final int ALLOWED_IMBALANCE = 1;
    private AVLNode<AnyType> root;

    public AVLNode<AnyType> getRoot(){
        return  root;
    }

    public void insert(AnyType element){
        root = insert(element,root);
    }


    /**
     * 单旋转，左左偏
     *
     *              k2                       k1
     *            /  \                     /   \
     *          k1    A    ------>>>>     B    k2
     *         /  \                            / \
     *        B    C                          C   A
     * 其中 k2、k1 表示节点
     * ABC表示树  其中 AC深度一致，B深度多1
     * @param k2
     * @return
     */
    private AVLNode<AnyType> rotateWithLeftChild(AVLNode k2) {

        if (k2 == null) {
            throw new NullPointerException();
        }

        //把C变成k2的左子树
        AVLNode k1 = k2.left;
        k2.left = k1.right;

        if (k1.right != null) {
            k1.right.parent = k2;
        }

        //
        k1.parent = k2.parent;
        if (k2.parent == null) {
            root = k1;
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


    /**
     * 单旋转 右右偏
     *
     *          k2                          k1
     *         / \                         / \
     *       A    k1  ---------------->  k2   C
     *           / \                    / \
     *          B   C                  A   B
     * 其中 k2、k1 表示节点
     * ABC表示树  其中 AB深度一致，C深度多1
     * @param k2
     * @return
     */
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
           root = k1;
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

    private AVLNode<AnyType> insert(AnyType element, AVLNode<AnyType> tree) {
        if (tree == null) {
            return new AVLNode<>(element);
        }

        int compareResult = element.compareTo(tree.element);
        if (compareResult < 0) {
            AVLNode<AnyType> insert = insert(element, tree.left);
            insert.parent = tree;
            tree.left =insert;
        } else if (compareResult > 0) {
            AVLNode<AnyType> insert = insert(element, tree.right);
            insert.parent = tree;
            tree.right = insert;
        } else {

        }
        return balance(tree);

    }

    /**
     * 验证平衡
     *
     * @param tree
     * @return
     */
    private AVLNode<AnyType> balance(AVLNode<AnyType> tree) {

        if (tree == null) {
            return null;
        }

        if (height(tree.left) - height(tree.right) > ALLOWED_IMBALANCE) {
            if (height(tree.left.left) > height(tree.left.right)) {
                tree = rotateWithLeftChild(tree);
            } else {
                tree = doubleWithLeftChild(tree);
            }
        } else if (height(tree.right) - height(tree.left) > ALLOWED_IMBALANCE) {
            if (height(tree.right.right) > height(tree.right.left)) {
                tree = rotateWithRightChild(tree);
            } else {
                tree = doubleWithRightChild(tree);
            }
        }
        tree.height = Math.max(height(tree.left), height(tree.right)) + 1;

        return tree;
    }

    /**
     * 处理 双旋转，左-右偏
     *        k3                        k3                        k2
     *       / \                       / \                       / \
     *      k1  D    以k1为节点      k2   D     k3为节点       k1   k3
     *     / \      ------------>   / \        -------->      / \   / \
     *    A   k2        左旋      k1  C         右旋        A   B  C   D
     *       / \                 / \
     *      B   C               A  B
     * 其中 k3、k2、k1 表示节点
     * A、B、C、D 表示树，且 BC可以不同时存在，ABCD高度一致
     * @param k3
     * @return
     */
    private AVLNode<AnyType> doubleWithLeftChild(AVLNode<AnyType> k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }


    /**
     * 处理 双旋转，左-右偏
     *        k3                                 k3                             k2
     *       / \                                / \                            / \
     *      A  k1      以k1为节点              A  k2       k3为节点          k3   k1
     *        / \   ---------------->            / \      ---------->      / \   / \
     *      k2   D      右旋                   B   k1       左旋         A   B  C   D
     *    / \                                     / \
     *   B   C                                   C   D
     * 其中 k3、k2、k1 表示节点
     * A、B、C、D 表示树  ，BC可以不同时存在，ABCD深度一致
     * @param k3
     * @return
     */
    private AVLNode<AnyType> doubleWithRightChild(AVLNode<AnyType> k3) {
        k3.right = rotateWithLeftChild(k3.right);
        return rotateWithRightChild(k3);
    }

    private int height(AVLNode node) {
        return node == null ? -1 : node.height;
    }

}
