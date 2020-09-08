package com.test.linklist.single;

import org.junit.Test;

import java.util.Random;

/**
 * @Auth:jinrun.xie
 * @Date:2020/9/8
 **/
public class LinkListTest {

    @Test
    public void testSingleLinkList() {
        Random random = new Random();
        Node header = new Node();
        SingleLinkList singleLinkList = new SingleLinkList(header);
        for (int i = 0; i < 10; i++) {
            singleLinkList.add(String.valueOf(random.nextInt(20)));
        }
        System.out.println("链表新增结束，链表中的元素");
        singleLinkList.print();
        for (int i = 0; i < 5; i++) {
            Node node = singleLinkList.find(String.valueOf(random.nextInt(20)));
            if (node != null) {
                System.out.println("find:" + node.getValue());
            }
        }
        for (int i = 0; i < 5; i++) {
            Node node = singleLinkList.delete(String.valueOf(random.nextInt(20)));
            if (node != null) {
                System.out.println("delete:" + node.getValue());
            }
        }
        System.out.println("链表删除结束，链表中的元素");
        singleLinkList.print();
    }
}
