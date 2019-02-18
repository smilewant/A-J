package com.further.run.labzone.tree;

import com.further.run.log.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Hukuan
 * 2018/5/30.
 * https://blog.csdn.net/bruce_6/article/details/38656413
 * https://blog.csdn.net/fengrunche/article/details/52305748
 */
public class TreeBinary<E> {
    private Node root = null;

    public static class Node<T> {
        T data;
        Node parent;
        Node left;
        Node right;

        public Node() {
        }

        public Node(T data) {
            this.data = data;
        }

        public Node(T data, Node parent) {
            this.data = data;
            this.parent = parent;
        }

        public String toString() {
            return "TreeParent$Node[data=" + data + ", parent=" + parent + "]";
        }
    }

    private final int DEFAULT_SIZE = 100;
    private int treeSize = 0;
    private List<Node> nodes;
    private int nodeNums;

    public TreeBinary(E data) {

        nodes = new ArrayList<>();
        Node node = new Node(data);
        nodes.add(node);

        if (root == null) {
            root = node;
            root.left = null;
            root.right = null;
            root.parent = null;
        }

    }

    public void addNode(E data) {
//        nodes.add(new Node(data));
        Node newNode = new Node(data);
        addNode(newNode, root);
    }

    private void addNode(Node newNode, Node curNode) {
        if (curNode == null) {
            curNode = newNode;
            curNode.left = null;
            curNode.right = null;
            curNode.parent = null;
        } else {
            if (curNode.left == null) {
                curNode.left = newNode;
                newNode.parent = curNode;
            } else {
                if (curNode.right == null) {
                    curNode.right = newNode;
                } else {
                    if (curNode.parent != null) {
                        if (curNode.parent.right != null) {
                            addNode(newNode, curNode.parent.right);
                        }
                    }
                    addNode(newNode, curNode.left);
                }
            }
        }
    }

    public void preOrderTraverse(Node node) {
        if (node != null) {
            LogUtil.d("node : " + node.data.toString());
            preOrderTraverse(node.left);
            preOrderTraverse(node.right);
        }
    }

    public void preOrder(Node node) {
        Stack stack = new Stack();
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                LogUtil.d("node : " + node.data.toString());
                node = node.left;
            }
            if (!stack.isEmpty()) {
                node = (Node)stack.pop();
                node = node.right;
            }
        }
    }

}
