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
}
