package com.test.linklist.practise;

import com.test.linklist.lru.Node;

/**
 * @Auth:jinrun.xie
 * @Date:2020/9/10 链表合并
 **/
public class LinkListMerge {

    private Node header1;

    private Node header2;

    public LinkListMerge(Node header1, Node header2) {
        this.header1 = header1;
        this.header2 = header2;
    }

    public void print(Node header) {
        Node node = header.getNext();
        while (node != null) {
            System.out.println(node.getValue());
            node = node.getNext();
        }
    }

    public Node getHeader1() {
        return header1;
    }

    public void setHeader1(Node header1) {
        this.header1 = header1;
    }

    public Node getHeader2() {
        return header2;
    }

    public void setHeader2(Node header2) {
        this.header2 = header2;
    }

    /**
     * 创建一个新的链表，将原来两个链表的节点移入到新链表中
     * 合并的时候将存储的值比较小的元素加入到新链表的末尾，并且指针向后移动到下一个节点
     * 最后检查一下哪个链表的元素还没有走完，加入到新链表的末尾
     */
    public Node merge() {
        Node header = new Node();
        Node node = header;
        Node node1 = header1.getNext();
        Node node2 = header2.getNext();
        Node next1 = null;
        Node next2 = null;
        while (node1 != null && node2 != null) {
            next1 = node1.getNext();
            next2 = node2.getNext();
            if (node1.getValue() < node2.getValue()) {
                node.setNext(node1);
                node = node1;
                node1 = next1;
            } else {
                node.setNext(node2);
                node = node2;
                node2 = next2;
            }
        }
        if (node1 != null) {
            node.setNext(node1);
        } else {
            node.setNext(node2);
        }
        return header;
    }
}
