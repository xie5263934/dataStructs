package com.test.linklist.lru;

import org.junit.Test;


/**
 * @Auth 45208
 * @Date 9/8/2020
 **/
public class LruTest {

    @Test
    public void test() {
        Node header = new Node();
        LRUAlgorithm lru = new LRUAlgorithm(header, 10);
        for (int i = 0; i < 10; i++) {
            lru.lru(i);
        }
        lru.print();
        lru.lru(20);
        lru.print();
        lru.lru(30);
        lru.print();
        lru.lru(8);
        lru.print();
    }
}
