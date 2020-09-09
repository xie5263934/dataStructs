package com.test.linklist.sameletter;

/**
 * @Auth:jinrun.xie
 * @Date:2020/9/9 链表回文检测
 * 使用快慢指针一共需要三个指针
 * 慢指针每次移动一个
 * 快指针每次移动两个
 * 头前节点指针用来指向当前慢指针指向的节点的前一个节点，因为我们需要将链表进行反转，所以需要知道当前慢指针指向的前一个节点是谁，这样把当前节点的后驱设置成头前节点指针指向的节点
 * 每次设置一个临时指针指向当前节点，然后慢指针移动到下一个节点，然后将当前节点的后驱设置成头前指针指向的节点，然后然后让头前节点指向当前节点
 **/
public class LetterLinkList {
    /**
     * 链表头
     */
    private Node header;

    /**
     * 元素个数
     */
    private int count = 0;

    public LetterLinkList(Node header) {
        this.header = header;
    }

    /**
     * 头插法
     *
     * @param value
     */
    public void add(String value) {
        Node node = new Node();
        node.setValue(value);
        node.setNext(header.getNext());
        header.setNext(node);
        count++;
    }

    /**
     * 是否回文检测
     * 快慢指针检查法，设置三个额外的指针
     * p指针，指向慢指针当前节点的前一个节点
     * s指针，慢指针每次向前一步
     * q指针，快指针每次向前两步
     */
    public boolean check() {
        boolean flag = true;
        Node p = header;
        Node s = header.getNext();
        Node q = header.getNext();
        if (count >= 2) {
            header.setNext(null);
            while (q != null) {
                Node t = s;
                //慢指针向前一步
                s = s.getNext();
                //p指针向前两步
                q = q.getNext();
                if (q != null) {
                    q = q.getNext();
                }
                t.setNext(p);
                p = t;
            }
            //如果链表是奇数个元素,那么慢指针当前指向的前一个和后一个元素开始比较
            if (count % 2 != 0) {
                p = p.getNext();
                while (p != null && s != null) {
                    if (!p.getValue().equals(s.getValue())) {
                        flag = false;
                    }
                    p = p.getNext();
                    s = s.getNext();
                }
                //如果链表是偶数个元素，那么慢指针和慢指针的前一个节点指针开始比较元素
            } else {
                while (p != null && s != null) {
                    if (!p.getValue().equals(s.getValue())) {
                        flag = false;
                    }
                    p = p.getNext();
                    s = s.getNext();
                }
            }
        } else {
            flag = false;
        }
        return flag;
    }

    public void print() {
        Node node = header.getNext();
        while (node != null) {
            System.out.println(node.getValue());
            node = node.getNext();
        }
    }
}
