package com.test.linklist.single;


/**
 * @Auth:jinrun.xie
 * @Date:2020/9/8 单向链表
 **/
public class SingleLinkList {
    /**
     * 链表的头结点
     */
    private Node header;

    public SingleLinkList(Node header) {
        this.header = header;
    }

    /**
     * 使用头插法，将新增的节点放在第一个节点上
     *
     * @param value
     */
    public void add(String value) {
        Node node = new Node();
        node.setValue(value);
        //如果头结点的下一个节点为空，那么说明这是第一次插入，直接放在头结点后面
        if (header.getNext() == null) {
            header.setNext(node);
            //如果头结点的下一个节点不为空，那么说明这不是第一次插入，那么当前新节点插入到第一个位置，头结点指向当前位置，当前插入节点指向头结点指向的节点
        } else {
            Node next = header.getNext();
            node.setNext(next);
            header.setNext(node);
        }
    }

    /**
     * 遍历链表查找执行数据
     *
     * @param value
     */
    public Node find(String value) {
        Node node = header.getNext();
        while (node != null) {
            if (value.equals(node.getValue())) {
                return node;
            }
            node = node.getNext();
        }
        return null;
    }

    /**
     * 删除链表中的某个数据
     * 首先根据value找到要删除的节点
     * 然后再找到当前节点的前一个节点
     * 最后再把前节点的指针指向当前节点的下一个节点，删除当前节点
     *
     * @param value
     */
    public Node delete(String value) {
        Node node = header.getNext();
        while (node != null) {
            if (value.equals(node.getValue())) {
                Node previous = header;
                while (previous != null) {
                    if (value.equals(previous.getNext().getValue())) {
                        previous.setNext(node.getNext());
                        return node;
                    }
                    previous = previous.getNext();
                }
            }
            node = node.getNext();
        }
        return null;
    }

    /**
     * 打印当前链表的所有数据
     */
    public void print() {
        Node node = header.getNext();
        while (node != null) {
            System.out.println(node.getValue());
            node = node.getNext();
        }

    }
}