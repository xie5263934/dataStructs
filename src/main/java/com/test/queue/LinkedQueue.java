package com.test.queue;

/**
 * @Auth:jinrun.xie
 * @Date:2020/9/14
 **/
public class LinkedQueue {

    /**
     * 队列头节点指针
     */
    private Node header;

    /**
     * 队列尾结点指针
     */
    private Node tail;

    public LinkedQueue() {

    }

    public void enqueue(String value) {
        Node node = new Node();
        node.setValue(value);
        if (tail == null) {
            header = node;
            tail = node;
        } else {
            tail.setNext(node);
            tail = tail.getNext();
        }
    }

    public Node dequeue() {
        Node node;
        if (header == tail) {
            node = header;
            header = null;
            tail = null;
        } else {
            node = header;
            header = header.getNext();
            node.setNext(null);
        }
        return node;
    }
}
