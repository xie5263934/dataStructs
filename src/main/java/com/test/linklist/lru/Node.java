package com.test.linklist.lru;

/**
 * @Auth 45208
 * @Date 9/8/2020
 **/
public class Node {
    /**
     * 数据域
     */
    private Integer value;
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

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
