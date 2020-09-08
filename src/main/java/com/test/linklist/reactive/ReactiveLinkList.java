package com.test.linklist.reactive;

/**
 * @Auth 45208
 * @Date 9/8/2020
 * 双向链表操作
 **/
public class ReactiveLinkList {
    /**
     * 头节点
     */
    private Node header;

    public ReactiveLinkList(Node header) {
        this.header = header;
    }

    /**
     * 新增节点插入内容
     * 1:采用头插法，如果是插入第一个节点，直接头节点的next指向新插入节点，新插入节点前驱指向头节点
     * 2:如果不是插入第一个节点，那么当前新插入节点后驱指向头节点的后驱，第一个节点的前驱指向当前节点，头节点的后驱指向当前节点，当前节点前驱指向头节点
     * 头插法好处是时间复杂度为O（1），不用遍历链表，直接在头节点上插入新的数据就行了
     *
     * @param value
     */
    public void add(Integer value) {
        Node node = header.getNext();
        if (node == null) {
            Node current = new Node(value);
            current.setPrevious(header);
            header.setNext(current);
        } else {
            Node current = new Node(value);
            current.setNext(node);
            current.setPrevious(header);
            node.setPrevious(current);
            header.setNext(current);
        }
    }

    /**
     * 查找指定的值
     *
     * @param value
     * @return
     */
    public Node find(Integer value) {
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
     * 删除指定值的节点
     * 1:遍历找到对应的节点
     * 2:找到当前节点的前驱
     * 3:将前驱节点后驱指针指向当前节点的后驱
     * 4:将后驱节点的前驱指针指向当前节点的前驱
     * 使用双向链表的好处是我们只需要维护一个额外的前驱指针，删除的时候就省略掉了查找要删除节点前驱的O（n)复杂度的时间消耗，大大提高了删除的效率
     *
     * @param value
     * @return
     */
    public Node delete(Integer value) {
        Node node = header.getNext();
        while (node != null) {
            if (value.equals(node.getValue())) {
                Node previous = node.getPrevious();
                Node next = node.getNext();
                previous.setNext(next);
                if (next != null) {
                    next.setPrevious(previous);
                }
                return node;
            }
            node = node.getNext();
        }
        return null;
    }

    public void print() {
        Node node = header.getNext();
        while (node != null) {
            System.out.println(node.getValue());
            node = node.getNext();
        }
    }
}
