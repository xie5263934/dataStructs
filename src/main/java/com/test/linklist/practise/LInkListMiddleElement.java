package com.test.linklist.practise;

import com.test.linklist.lru.Node;

/**
 * @Auth:jinrun.xie
 * @Date:2020/9/10 查找链表中间节点，快慢指针，当快指针走向末尾的时候，慢指针正好处于链表中间节点
 **/
public class LInkListMiddleElement {
    private Node header;

    public LInkListMiddleElement(Node header) {
        this.header = header;
    }

    public void add(Integer value) {
        Node node = new Node();
        node.setValue(value);
        node.setNext(header.getNext());
        header.setNext(node);
    }

    public Node middleElement() {
        Node slow = header.getNext();
        Node fast = header.getNext();
        while (fast != null) {
            fast = fast.getNext();
            if (fast != null) {
                fast = fast.getNext();
            }
            slow = slow.getNext();
        }
        return slow;
    }
}
