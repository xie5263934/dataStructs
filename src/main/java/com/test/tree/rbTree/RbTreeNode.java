package com.test.tree.rbTree;

import java.util.Comparator;

/**
 * @Auth 45208
 * @Date 1/17/2021
 **/
public class RbTreeNode<T extends Comparable<T>> {
    /**
     * 0红色，1黑色
     */
    private Integer color;

    /**
     * 关键字，也就是数据区
     */
    private T key;

    /**
     * 指向左孩子的指针
     */
    private RbTreeNode<T> left;

    /**
     * 指向右孩子的指针
     */
    private RbTreeNode<T> right;

    /**
     * 指向父节点的指针
     */
    private RbTreeNode<T> parent;

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public RbTreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(RbTreeNode<T> left) {
        this.left = left;
    }

    public RbTreeNode<T> getRight() {
        return right;
    }

    public void setRight(RbTreeNode<T> right) {
        this.right = right;
    }

    public RbTreeNode<T> getParent() {
        return parent;
    }

    public void setParent(RbTreeNode<T> parent) {
        this.parent = parent;
    }
}
