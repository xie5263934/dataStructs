package com.test.tree;

public class Node {
    private Integer data;
    private Node left;
    private Node right;
    /**
     * 在使用stack来实现非递归遍历的时候，需要用这个字段来判断当前节点的右孩子是否已经处理过
     */
    private boolean flag;

    public Node(Integer data) {
        this.data = data;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void setFlag(boolean flag){
        this.flag = flag;
    }

    public boolean getFlag(){
        return this.flag;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                '}';
    }
}
