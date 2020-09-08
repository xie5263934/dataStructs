package com.test.linklist.reactive;


import org.junit.Test;

import java.util.Random;

/**
 * @Auth 45208
 * @Date 9/8/2020
 **/
public class ReactiveLinkListTest {

    @Test
    public void test() {
        Random random = new Random();
        Node header = new Node(null);
        ReactiveLinkList reactiveLinkList = new ReactiveLinkList(header);
        for (int i = 0; i < 10; i++) {
            reactiveLinkList.add(random.nextInt(20));
        }
        System.out.println("链表新增结束，链表中的元素");
        reactiveLinkList.print();
        for (int i = 0; i < 5; i++) {
            Node node = reactiveLinkList.find(random.nextInt(20));
            if (node != null) {
                System.out.println("find:" + node.getValue());
            }
        }
        for (int i = 0; i < 5; i++) {
            Node node = reactiveLinkList.delete(random.nextInt(20));
            if (node != null) {
                System.out.println("delete:" + node.getValue());
            }
        }
        System.out.println("链表删除结束，链表中的元素");
        reactiveLinkList.print();
    }
}
