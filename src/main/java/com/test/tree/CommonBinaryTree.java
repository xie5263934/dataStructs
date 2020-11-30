package com.test.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 普通二分查找，处理值存在重复的情况
 * 在这种情况下，如果要插入的值等于节点中的某个值了，这种时候我们就假定要插入的值大于节点的值，将其插入到当前节点的右子树中
 * 所以在查找和删除的过程中都要处理重复的情况
 */
public class CommonBinaryTree {
    /**
     * 哨兵节点，也是根节点
     */
    private Node tree;

    public void add(Node node) {
        if (tree == null) {
            tree = node;
            return;
        }
        Node p = tree;
        Node pp = null;
        while (p != null) {
            pp = p;
            if (node.getData() >= p.getData()) {
                p = p.getRight();
            } else {
                p = p.getLeft();
            }
        }
        if (node.getData() >= pp.getData()) {
            pp.setRight(node);
        } else {
            pp.setLeft(node);
        }
    }

    public List<Node> search(int value) {
        List<Node> nodeList = new ArrayList<>();
        if (tree != null) {
            Node p = tree;
            while (p != null) {
                if (p.getData() == value) {
                    nodeList.add(p);
                }
                if (value >= p.getData()) {
                    p = p.getRight();
                } else {
                    p = p.getLeft();
                }
            }
        }
        return nodeList;
    }

    public void middle(Node node) {
        if (node == null) {
            return;
        }
        middle(node.getLeft());
        System.out.println(node.getData());
        middle(node.getRight());
    }

    public Node getTree() {
        return tree;
    }

    public void setTree(Node tree) {
        this.tree = tree;
    }
}
