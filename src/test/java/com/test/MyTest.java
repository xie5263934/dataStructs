package com.test;

import com.test.linklist.LinkListSort;
import com.test.linklist.LinkListSort1;
import org.junit.Test;

/**
 * @Auth:jinrun.xie
 * @Date:2020/10/20
 **/
public class MyTest {

    @Test
    public void testMinRun() {
        int n = 996;
        int r = 0;
        while (n >= 64) {
            r |= n & 1;
            n >>= 1;
        }
        System.out.println(n + r);
    }

    @Test
    public void test() {
        LinkListSort.ListNode node1 = new LinkListSort.ListNode(9);
        LinkListSort.ListNode node2 = new LinkListSort.ListNode(12);
        node1.next = node2;
        LinkListSort.ListNode node3 = new LinkListSort.ListNode(7);
        node2.next = node3;
        LinkListSort.ListNode node4 = new LinkListSort.ListNode(13);
        node3.next = node4;
        LinkListSort.ListNode node5 = new LinkListSort.ListNode(-1);
        node4.next = node5;
        LinkListSort.ListNode node6 = new LinkListSort.ListNode(-9);
        node5.next = node6;
        LinkListSort.ListNode node7 = new LinkListSort.ListNode(20);
        node6.next = node7;
        LinkListSort linkListSort = new LinkListSort();
        LinkListSort.ListNode node = linkListSort.sort(node1);
        while (node != null) {
            System.out.println(node.value);
            node = node.next;
        }
    }

    @Test
    public void test1() {
        LinkListSort1.ListNode node1 = new LinkListSort1.ListNode(9);
        LinkListSort1.ListNode node2 = new LinkListSort1.ListNode(12);
        node1.next = node2;
        LinkListSort1.ListNode node3 = new LinkListSort1.ListNode(7);
        node2.next = node3;
        LinkListSort1.ListNode node4 = new LinkListSort1.ListNode(13);
        node3.next = node4;
        LinkListSort1.ListNode node5 = new LinkListSort1.ListNode(-1);
        node4.next = node5;
        LinkListSort1.ListNode node6 = new LinkListSort1.ListNode(-9);
        node5.next = node6;
        LinkListSort1.ListNode node7 = new LinkListSort1.ListNode(20);
        node6.next = node7;
        LinkListSort1 linkListSort = new LinkListSort1();
        LinkListSort1.ListNode node = linkListSort.sort(node1);
        while (node != null) {
            System.out.println(node.value);
            node = node.next;
        }
    }
}
