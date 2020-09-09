package com.test.linklist.sameletter;

/**
 * @Auth:jinrun.xie
 * @Date:2020/9/9
 **/
public class Node {
    /**
     * 值域
     */
    private String value;

    /**
     * 后驱指针
     */
    private Node next;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
