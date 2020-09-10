package com.test.linklist.practise;

import com.test.linklist.lru.Node;

/**
 * @Auth:jinrun.xie
 * @Date:2020/9/10 链表反转
 **/
public class LinkListreverse {
    /**
     * 哨兵
     */
    private Node header;

    public LinkListreverse(Node header) {
        this.header = header;
    }

    public void add(Integer value) {
        Node node = new Node();
        node.setValue(value);
        node.setNext(header.getNext());
        header.setNext(node);
    }

    public void print() {
        Node node = header.getNext();
        while (node != null) {
            System.out.println(node.getValue());
            node = node.getNext();
        }
    }

    /**
     * 链表反转思路
     * 使用三个指针
     * 1:指向当前节点的指针
     * 2;指向当前节点前驱的指针，反转的时候，将当前节点的后驱设置为当前节点前驱指针的节点
     * 3:当前节点后驱的指针，因为当反转了之后，如果不用后驱指针指向后驱节点，后面的节点就都丢失了
     * 每次处理的时候，先将当前节点的后驱设置为前驱指针指向的节点，然后把前驱指针指向当前节点，当前节点指向后驱指针指向的节点，这样一步一步往后挪，反转节点的指向
     * 当时要特殊处理第一个和最后一个节点，因为反转了之后，第一个节点就变成了尾结点，所以需要将第一个节点的后驱设置成空。尾结点变成了第一个节点，所以哨兵需要指向第一个节点
     */
    public void reveser() {
        Node previous = header;
        Node node = header.getNext();
        Node next = null;
        while (node != null) {
            next = node.getNext();
            node.setNext(previous);
            if (previous == header) {
                node.setNext(null);
            }
            previous = node;
            if (next == null) {
                header.setNext(node);
            }
            node = next;
        }
    }
}
