package com.test.linklist.practise;

import com.test.linklist.lru.Node;

/**
 * @Auth:jinrun.xie
 * @Date:2020/9/10 删除倒数第N个节点
 * 思路，在哨兵节点中存储当前链表一共有多少个节点，然后跟要删除的N做判断，如果N比节点元素大，就直接返回，否则就是删除顺数链表长度减去N那个节点
 **/
public class LinkListDeteleElement {
    private Node header;

    public LinkListDeteleElement(Node header) {
        this.header = header;
    }

    public void add(Integer value) {
        Node node = new Node();
        node.setValue(value);
        node.setNext(header.getNext());
        header.setNext(node);
        header.setValue(header.getValue() + 1);
    }

    public Node delete(Integer n) {
        Integer count = header.getValue();
        if (n > count || n < 1) {
            return null;
        }
        int step = 0;
        Node previouse = header;
        Node node = header.getNext();
        while (step < count - n) {
            step++;
            previouse = node;
            node = node.getNext();
        }
        previouse.setNext(node.getNext());
        return node;
    }
}
