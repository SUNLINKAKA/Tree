package com.practice.lin.tree;

import java.util.NoSuchElementException;

/**
 * create on 2018/12/9 16:34
 * Email:sunlin0737@outlook.com
 *
 * @author lin
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {

    public TreeNode<AnyType> root;


    /**
     * 找最小的节点
     *
     * @param tree 树节点
     * @return
     */
    private TreeNode<AnyType> findMin(TreeNode<AnyType> tree) {
        if (tree == null) {
            throw new NullPointerException("findMin tree null");
        }
        while (tree.leftChild != null) {
            tree = tree.leftChild;
        }
        return tree;
    }


    /**
     * 查找节点
     *
     * @return
     */
    public TreeNode<AnyType> find(AnyType data) {
        if (root == null) {
            throw new NullPointerException("root null");
        }
        TreeNode<AnyType> node = this.root;
        while (node != null) {
            if (node.element.compareTo(data) > 0) {
                node = node.leftChild;
            } else if (node.element.compareTo(data) < 0) {
                node = node.rightChild;
            } else {
                return node;
            }
        }
        return null;
    }


    //////////////////////递归调用////////////////////////////////////////////////////


    public void insert(AnyType data) {
        root = insert(data, root);
    }


    public void remove(AnyType data) {
        root = remove(data, root);
    }

    /**
     * 删除节点，递归实现
     * @param data
     * @param tree
     * @return
     */
    private TreeNode<AnyType> remove(AnyType data, TreeNode<AnyType> tree) {

        if (tree == null) {
            throw new NoSuchElementException("树是空的！");
        }

        int compareResult = data.compareTo(tree.element);

        if (compareResult > 0) {
            tree.rightChild = remove(data, tree.rightChild);
        } else if (compareResult < 0) {
            tree.leftChild = remove(data, tree.leftChild);
        } else {
            if (tree.rightChild != null && tree.leftChild != null) {
                TreeNode<AnyType> min = findMin(tree.rightChild);
                tree.element = min.element;
                tree.rightChild = remove(tree.element, tree.rightChild);
            } else {
                if (tree.rightChild != null) {
                    tree.rightChild.parent = tree.parent;
                    tree = tree.rightChild;
                } else if (tree.leftChild != null) {
                    tree.leftChild.parent = tree.parent;
                    tree = tree.leftChild;
                } else {
                    tree = null;
                }
            }
        }

        return tree;
    }


    /**
     * 递归添加节点
     *
     * @param data 要插入的数据
     * @param node 插入的起点节点
     * @return
     */
    private TreeNode<AnyType> insert(AnyType data, TreeNode<AnyType> node) {
        if (node == null) {
            TreeNode<AnyType> anyTypeBinaryNode = new TreeNode<>(data);
            return anyTypeBinaryNode;
        }

        int compareResult = data.compareTo(node.element);

        if (compareResult < 0) {
            TreeNode<AnyType> insert = insert(data, node.leftChild);
            node.leftChild = insert;
            insert.parent = node;
        } else if (compareResult > 0) {
            TreeNode<AnyType> insert = insert(data, node.rightChild);
            node.rightChild = insert;
            insert.parent = node;
        }
        return node;
    }


    ///////////////////////////////////////////////////非递归/////////////////////////////////////////////////////////////////////

    /**
     * 非递归循环插入节点，并绑定parent节点
     *
     * @param data
     * @return
     */
    public TreeNode<AnyType> put(AnyType data) {
        if (root == null) {
            TreeNode<AnyType> node = new TreeNode(data);
            root = node;
            return root;
        }

        TreeNode<AnyType> parent = null;
        TreeNode<AnyType> node = root;
        while (null != node) {
            //记录父节点
            parent = node;

            if (data.compareTo(node.element) > 0) {
                node = node.rightChild;
            } else if (data.compareTo(node.element) < 0) {
                node = node.leftChild;
            } else {
                return node;
            }
        }

        //生成节点并放入
        TreeNode<AnyType> newNode = new TreeNode<>(data);
        if (data.compareTo(parent.element) > 0) {
            parent.rightChild = newNode;
        } else if (data.compareTo(parent.element) < 0) {
            parent.leftChild = newNode;
        }
        newNode.parent = parent;
        return newNode;
    }


    public void delNode(AnyType data) {

        TreeNode<AnyType> node = find(data);
        if (node == null) {
            throw new NoSuchElementException();
        }

        del(data, node);

    }

    /**
     * 删除节点，非递归调用
     * @param data
     * @param node
     */
    private void del(AnyType data, TreeNode<AnyType> node) {
        if (node.rightChild != null && node.leftChild != null) {
            //左右孩子都不为空的情况

            TreeNode<AnyType> min = findMin(node.rightChild);
            if (node.parent == null) {
                //根节点
                if (min.rightChild == null) {
                    delRaLnull(min);
                    node.element = min.element;
                } else {
                    delLnull(min);
                    node.element = min.element;
                }
            } else {
                //非根节点
                if (min.rightChild == null) {
                    delRaLnull(min);
                    node.element = min.element;
                } else {
                    delLnull(min);
                    node.element = min.element;
                }
            }
        } else if (node.rightChild == null && node.leftChild == null) {
            //左右孩子都为空
            delRaLnull(node);
        } else if (node.rightChild == null) {
            //右孩子空，左孩子不空
            delRnull(node);
        } else if (node.leftChild == null) {
            //左孩子空，右孩子不空
            delLnull(node);
        }
    }

    /**
     * 删除节点右孩子是NULL
     *
     * @param node
     */
    private void delRnull(TreeNode<AnyType> node) {
        if (node.parent == null) {
            //根节点
            node.leftChild.parent = null;
            root = node.leftChild;
            node.leftChild = null;
        } else {
            //非根节点
            if (node.element.compareTo(node.parent.element) > 0) {
                node.parent.rightChild = node.leftChild;
                node.leftChild.parent = node.parent;
            } else {
                node.parent.leftChild = node.leftChild;
                node.leftChild.parent = node.parent;
            }
            node.parent = null;
            node.leftChild = null;
        }
    }

    /**
     * 删除节点左孩子是NULL
     *
     * @param node
     */
    private void delLnull(TreeNode<AnyType> node) {
        if (node.parent == null) {
            //根节点
            node.rightChild.parent = null;
            root = node.rightChild;
            node.rightChild = null;
        } else {
            //非根节点
            if (node.element.compareTo(node.parent.element) > 0) {
                node.parent.rightChild = node.rightChild;
                node.rightChild.parent = node.parent;
            } else {
                node.parent.leftChild = node.rightChild;
                node.rightChild.parent = node.parent;
            }
            node.parent = null;
            node.rightChild = null;

        }
    }

    /**
     * 删除节点左右孩子都为null
     *
     * @param node
     */
    private void delRaLnull(TreeNode<AnyType> node) {
        if (node.parent == null) {
            //根节点
            node = null;
            root = null;
        } else {
            //非根节点
            if (node.element.compareTo(node.parent.element) > 0) {
                node.parent.rightChild = null;
            } else {
                node.parent.leftChild = null;
            }
            node.parent = null;
        }
    }

}
