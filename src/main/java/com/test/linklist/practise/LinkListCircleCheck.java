package com.test.linklist.practise;

import com.test.linklist.lru.Node;

/**
 * @Auth:jinrun.xie
 * @Date:2020/9/10 链表中环的检测
 * 检测思路
 * 通过快慢两个指针
 * 慢指针每次只前进一步
 * 快指针每次前进两步
 * 如果链中存在环，那么快指针进入环中快速前进，在一定的圈数之后一定会追上慢指针，两个指针在环中谋个节点上相遇，证明有环的存在
 **/

/**
 * 链表环入口的查找
 * 查找思路
 * 首先通过快慢指针的方法判断环是否存在，并且当快慢指针在环中相遇的时候，慢指针在环中的位置距离环的入口和首节点到环的位置是一样的。
 * 所以我们增加两个指针p1，p2，p1指向第一个节点，p2指向慢指针当前的节点，每次两个指针都往前移动一步，当p1,p2相遇的时候，就正好在环的入口节点处
 */
public class LinkListCircleCheck {

    private Node header;

    public LinkListCircleCheck(Node header) {
        this.header = header;
    }

    public boolean check() {
        Node slow = header;
        Node fast = header;
        while (slow != null && fast != null) {
            slow = slow.getNext();
            fast = fast.getNext();
            if (fast != null) {
                fast = fast.getNext();
            }
            if (slow.equals(fast)) {
                return true;
            }
        }
        return false;
    }

    public Node getCircleEnter() {
        Node slow = header.getNext();
        Node fast = header.getNext();
        Node ptr1 = null;
        Node ptr2 = null;
        while (slow != null && fast != null) {
            if (ptr1 != null && ptr1.equals(ptr2)) {
                return ptr1;
            }
            slow = slow.getNext();
            fast = fast.getNext();
            if (fast != null) {
                fast = fast.getNext();
            }
            if (slow.equals(fast)) {
                ptr1 = header.getNext();
                ptr2 = slow;
            }
            if (ptr1 != null) {
                ptr1 = ptr1.getNext();
                ptr2 = ptr2.getNext();
            }
        }
        return null;
    }

    public Node getHeader() {
        return header;
    }

    public void setHeader(Node header) {
        this.header = header;
    }
}
