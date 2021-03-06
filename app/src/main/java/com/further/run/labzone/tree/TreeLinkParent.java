package com.further.run.labzone.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hukuan
 * 2018/5/30.
 */
public class TreeLinkParent<E> {
    public static class Node<T> {
        T data;
        Node parent;

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
    private Node<E>[] nodes;
    private int nodeNums;

    public TreeLinkParent(E data) {
        treeSize = DEFAULT_SIZE;
        nodes = new Node[treeSize];
        nodes[0] = new Node(data);
        nodeNums++;
    }

    public TreeLinkParent(E data, int treeSize) {
        this.treeSize = treeSize;
        nodes = new Node[treeSize];
        nodes[0] = new Node(data);
        nodeNums++;
    }

    public void addNode(E data, Node parent) {
        for (int i = 0; i < treeSize; i++) {
            if (nodes[i] == null) {
                nodes[i] = new Node(data, parent);
                nodeNums++;
                return;
            }
        }
        throw new RuntimeException("树已满");
    }

    public boolean isEmpty() {
        return nodes[0] == null;
    }

    public List<Node> children(Node parent) {
        List<Node> nodeList = new ArrayList<>();
        for (int i = 0; i < treeSize; i++) {
            if (nodes[i] != null && nodes[i].parent == parent) {
                nodeList.add(nodes[i]);
            }
        }
        return nodeList;
    }

    public int findDeep(){
        int deep = 0;
        for (int i = 0; i < treeSize; i++){

            if (nodes[i] != null){
                int m = 1;
                Node pPostion = nodes[i].parent;
                while(pPostion != null ){
                    pPostion = pPostion.parent;
                    m++;
                }
                if (deep < m) {
                    deep = m;
                }
            }
        }
        return deep;
    }


    private int pos(Node node) {
        for (int i = 0; i < treeSize; i++) {
            if (nodes[i] == node) {
                return i;
            }
        }
        return -1;
    }

}
