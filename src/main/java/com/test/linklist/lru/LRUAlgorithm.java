package com.test.linklist.lru;


/**
 * @Auth 45208
 * @Date 9/8/2020
 * LRU least recently used 最近最少使用缓存淘汰算法
 * 思路
 * 1:实现一个单向链表，尾节点就是最近最少访问的那个数据
 * 2:当访问一个新的数据的时候，遍历整个链表
 * 2-1:如果当前数据不存在，那么使用头插法将当前节点加入第一个节点，并且检查当前链表元素个数是否已经超过阈值，如果超过，将尾节点删除
 * 2-2:如果当前数据存在，那么将当前数据从当前位置删除，并且使用头插法重新插入头节点
 **/
public class LRUAlgorithm {

    /**
     * 链表头
     */
    private Node header;

    /**
     * 缓存的阈值
     */
    private int count = 0;

    private int limit;

    public LRUAlgorithm(Node header, int limit) {
        this.header = header;
        this.limit = limit;
    }

    public void lru(Integer value) {
        Node node = header.getNext();
        Node target = null;
        while (node != null) {
            if (value.equals(node.getValue())) {
                target = node;
                break;
            }
            node = node.getNext();
        }
        if (target != null) {
            Node previous = header;
            while (previous != null) {
                if (target.equals(previous.getNext())) {
                    previous.setNext(target.getNext());
                }
                previous = previous.getNext();
            }
            target.setNext(header.getNext());
            header.setNext(target);
        } else {
            Node current = new Node();
            current.setValue(value);
            current.setNext(header.getNext());
            header.setNext(current);
            count++;
            check();
        }
    }

    public void print() {
        Node node = header.getNext();
        while (node != null) {
            System.out.print(node.getValue() + ",");
            node = node.getNext();
        }
        System.out.print("\n");
    }

    /**
     * 如果当前链表元素个数已经超过阈值，那么删除尾元素
     */
    public void check() {
        if (count > limit) {
            Node node = header.getNext();
            while (node != null) {
                if (node.getNext() == null) {
                    Node previous = header;
                    while (previous != null) {
                        if (node.equals(previous.getNext())) {
                            previous.setNext(null);
                            System.out.println("淘汰了值=" + node.getValue() + "的节点");
                            count--;
                        }
                        previous = previous.getNext();
                    }
                }
                node = node.getNext();
            }
        }
    }

}
