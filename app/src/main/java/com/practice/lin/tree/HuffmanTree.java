package com.practice.lin.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * create on 2018/12/16 19:55
 * Email:sunlin0737@outlook.com
 *
 * @author lin
 */
public class HuffmanTree {

    TreeNode root;

    public TreeNode createHuffmanTree(ArrayList<TreeNode> list) {

        if (list == null) {
            throw new NullPointerException();
        }

        while (list.size() > 1) {
            Collections.sort(list);
            TreeNode left = list.get(list.size() - 1);
            TreeNode right = list.get(list.size() - 2);

            TreeNode create_node = new TreeNode("create node", left, right, null, left.weight + right.weight);
            left.parent = create_node;
            right.parent = create_node;

            list.remove(left);
            list.remove(right);
            list.add(create_node);
        }

        root = list.get(0);
        return root;
    }


    public String getCode(TreeNode node) {
        TreeNode nodeTemp = node;
        Stack<String> stack = new Stack();

        while (nodeTemp != null && nodeTemp.parent != null) {
            if (nodeTemp.parent.leftChild == nodeTemp) {
                stack.push("0");
            } else if (nodeTemp.parent.rightChild == nodeTemp) {
                stack.push("1");
            }
            nodeTemp = nodeTemp.parent;
        }

        String code = "";
        while (!stack.isEmpty()) {
            String pop = stack.pop();
            code = code + pop;
        }
        return  code;

    }

}
