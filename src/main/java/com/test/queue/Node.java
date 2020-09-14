package com.test.queue;

/**
 * @Auth:jinrun.xie
 * @Date:2020/9/14
 **/
public class Node {
    /**
     * 指向下个节点的指针
     */
    private Node next;

    /**
     * 数据域
     */
    private String value;

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
