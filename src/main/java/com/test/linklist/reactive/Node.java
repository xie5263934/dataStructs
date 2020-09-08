package com.test.linklist.reactive;

/**
 * 双向链表节点
 * 包括前驱指针
 * 包括后驱指针
 * 包括数据
 */
public class Node {
    /**
     * 数据域
     */
    private Integer value;
    /**
     * 前驱指针
     */
    private Node previous;
    /**
     * 后驱指针
     */
    private Node next;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node(Integer value) {
        this.value = value;
    }
}
