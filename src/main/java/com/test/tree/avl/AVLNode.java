package com.test.tree.avl;

import java.util.Comparator;

/**
 * 创建一个节点，当前节点包含数据区，指向两个孩子节点的左孩子指针和右孩子指针，以及记录当前节点高度的字段
 *
 * @param <T>
 */
public class AVLNode<T extends Comparable<T>> {
    private T key;
    private AVLNode<T> left;
    private AVLNode<T> right;
    private int height;

    public AVLNode(T key, AVLNode left, AVLNode right) {
        this.key = key;
        this.left = left;
        this.right = right;
        this.height = 0;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public AVLNode getLeft() {
        return left;
    }

    public void setLeft(AVLNode left) {
        this.left = left;
    }

    public AVLNode getRight() {
        return right;
    }

    public void setRight(AVLNode right) {
        this.right = right;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
