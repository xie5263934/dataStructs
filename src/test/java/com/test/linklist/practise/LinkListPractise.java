package com.test.linklist.practise;

import com.test.linklist.lru.Node;
import org.junit.Test;

import java.util.Random;

/**
 * @Auth:jinrun.xie
 * @Date:2020/9/10
 **/
public class LinkListPractise {

    @Test
    public void testReverse() {
        Node header = new Node();
        LinkListreverse linkListreverse = new LinkListreverse(header);
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            linkListreverse.add(random.nextInt(100));
        }
        System.out.println("链表新增完成");
        linkListreverse.print();
        linkListreverse.reveser();
        System.out.println("链表反转完成");
        linkListreverse.print();
    }

    @Test
    public void testCircle() {
        Node header = new Node();
        LinkListCircleCheck linkListCircleCheck = new LinkListCircleCheck(header);
        Node node1 = new Node();
        Node node2 = new Node();
        Node node3 = new Node();
        Node node4 = new Node();
        Node node5 = new Node();
        Node node6 = new Node();
        header.setNext(node1);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);
        node5.setNext(node6);
        node6.setNext(node3);
        System.out.println(linkListCircleCheck.check());
    }

    @Test
    public void testCircleEnter() {
        Node header = new Node();
        LinkListCircleCheck linkListCircleCheck = new LinkListCircleCheck(header);
        Node node1 = new Node();
        node1.setValue(1);
        Node node2 = new Node();
        node2.setValue(2);
        Node node3 = new Node();
        node3.setValue(3);
        Node node4 = new Node();
        node4.setValue(4);
        Node node5 = new Node();
        node5.setValue(5);
        Node node6 = new Node();
        node6.setValue(6);
        header.setNext(node1);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);
        node5.setNext(node6);
        node6.setNext(node3);
        Node node = linkListCircleCheck.getCircleEnter();
        if (node != null) {
            System.out.println(node.getValue());
        }
    }

    @Test
    public void testLinkListMerge() {
        Node header1 = new Node();
        Node header2 = new Node();
        LinkListMerge linkListMerge = new LinkListMerge(header1, header2);
        Node node11 = new Node();
        header1.setNext(node11);
        node11.setValue(1);
        Node node12 = new Node();
        node11.setNext(node12);
        node12.setValue(4);
        Node node13 = new Node();
        node12.setNext(node13);
        node13.setValue(7);

        Node node21 = new Node();
        header2.setNext(node21);
        node21.setValue(2);
        Node node22 = new Node();
        node21.setNext(node22);
        node22.setValue(4);
        Node node23 = new Node();
        node22.setNext(node23);
        node23.setValue(8);

        System.out.println("链表1的内容");
        linkListMerge.print(linkListMerge.getHeader1());

        System.out.println("链表2的内容");
        linkListMerge.print(linkListMerge.getHeader2());

        Node node = linkListMerge.merge();
        System.out.println("合并后的内容");
        linkListMerge.print(node);
    }

    @Test
    public void testMiddleElement() {
        Node header = new Node();
        LInkListMiddleElement lInkListMiddleElement = new LInkListMiddleElement(header);
        for (int i = 1; i <= 8; i++) {
            lInkListMiddleElement.add(i);
        }
        Node node = lInkListMiddleElement.middleElement();
        if (node != null) {
            System.out.println(node.getValue());
        }
    }

    @Test
    public void testDeleteElement() {
        Node header = new Node();
        header.setValue(0);
        LinkListDeteleElement linkListDeteleElement = new LinkListDeteleElement(header);
        for (int i = 8; i > 0; i--) {
            linkListDeteleElement.add(i);
        }
        Node node = linkListDeteleElement.delete(3);
        if (node != null) {
            System.out.println(node.getValue());
        }
    }

}
