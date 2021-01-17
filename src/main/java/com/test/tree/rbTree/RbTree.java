package com.test.tree.rbTree;

import java.util.Comparator;

/**
 * 红黑树实现
 *
 * @Auth 45208
 * @Date 1/17/2021
 **/
public class RbTree<T extends Comparable<T>> {
    /**
     * 根节点
     */
    private RbTreeNode<T> root;

    /**
     * 哨兵节点
     */
    private RbTreeNode<T> nil;

    /**
     * 左旋操作，是以当前节点以及当前节点右孩子的连接为轴，向左方向的旋转
     *
     * @param tree
     * @param node
     */
    public void leftRotation(RbTree<T> tree, RbTreeNode<T> node) {
        RbTreeNode<T> right = node.getRight();
        node.setRight(right.getLeft());
        if (right.getLeft() != tree.nil) {
            right.getLeft().setParent(node);
        }
        right.setLeft(node);
        right.setParent(node.getParent());
        if (node.getParent() != tree.nil) {
            if (node == node.getParent().getLeft()) {
                node.getParent().setLeft(right);
            } else {
                node.getParent().setRight(right);
            }
        } else {
            tree.setRoot(right);
        }
        node.setParent(right);
    }

    /**
     * 右旋操作，是以当前节点以及当前节点左孩子的连接为轴，向右方向的旋转
     *
     * @param tree
     * @param node
     */
    public void rightRotaion(RbTree<T> tree, RbTreeNode<T> node) {
        RbTreeNode<T> left = node.getLeft();
        node.setLeft(left.getRight());
        if (left.getRight() != tree.nil) {
            left.getRight().setParent(node);
        }
        left.setRight(node);
        left.setParent(node.getParent());
        if (node.getParent() != tree.getNil()) {
            if (node == node.getParent().getLeft()) {
                node.getParent().setLeft(left);
            } else {
                node.getParent().setRight(left);
            }
        } else {
            tree.setRoot(left);
        }
        node.setParent(left);
    }

    public void insert(RbTree<T> tree, RbTreeNode<T> node){
        RbTreeNode<T> parent = tree.nil;
        RbTreeNode<T> location = tree.root;
        while(location != tree.nil){
            parent = location;
            if(node.getKey().compareTo(location.getKey()) < 0){
                location = location.getLeft();
            }else{
                location = location.getRight();
            }
        }
        node.setParent(parent);
        if(parent == tree.nil){
            tree.root = node;
        }
        node.setLeft(tree.nil);
        node.setRight(tree.nil);
        node.setColor(0);
    }

    public void insertFix(RbTree<T> tree , RbTreeNode<T> node){
        /**
         * 因为当前节点是红色的，并且如果父节点也是红色的，那么就需要进行处理
         */
        while(node.getParent().getColor() == 0){
            //如果当前节点的父节点是爷爷节点的左孩子
            if(node.getParent() == node.getParent().getParent().getLeft()){
                RbTreeNode uncle = node.getParent().getParent().getRight();
                //如果叔叔的节点也是红色的，那么就将父节点和叔叔节点染成黑色，将爷爷节点染成红色，然后将当前
                //要处理的节点转译成爷爷节点，因为爷爷节点是红色的，可能会违反(2根节点是黑色，4或者一个节点的左右孩子节点都是黑色两条性质中的一条)，需要继续执行
                if(uncle.getColor() == 0){
                    node.getParent().setColor(1);
                    uncle.setColor(1);
                    node.getParent().getParent().setColor(0);
                    node = node.getParent().getParent();
                }
                else if(node)
            }
        }
    }

    public RbTreeNode<T> getRoot() {
        return root;
    }

    public void setRoot(RbTreeNode<T> root) {
        this.root = root;
    }

    public RbTreeNode<T> getNil() {
        return nil;
    }

    public void setNil(RbTreeNode<T> nil) {
        this.nil = nil;
    }
}
