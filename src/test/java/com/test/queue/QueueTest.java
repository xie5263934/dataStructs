package com.test.queue;

import org.junit.Test;

/**
 * @Auth:jinrun.xie
 * @Date:2020/9/14
 **/
public class QueueTest {

    @Test
    public void testArrayQueue() {
        ArrayQueue arrayQueue = new ArrayQueue(8);
        for (int i = 0; i < 8; i++) {
            Boolean result = arrayQueue.enqueue(String.valueOf(i));
            System.out.println(i + "入队列" + result);
        }
        for (int i = 0; i < 4; i++) {
            String result = arrayQueue.dequeue();
            System.out.println(result);
        }
        for (int i = 8; i < 12; i++) {
            Boolean result = arrayQueue.enqueue(String.valueOf(i));
            System.out.println(i + "入队列" + result);
        }
        for (int i = 0; i < 8; i++) {
            String result = arrayQueue.dequeue();
            System.out.println(result);
        }
    }

    @Test
    public void testLinkQueue() {
        LinkedQueue linkedQueue = new LinkedQueue();
        for (int i = 0; i < 10; i++) {
            linkedQueue.enqueue(String.valueOf(i));
        }
        Node node;
        while ((node = linkedQueue.dequeue()) != null) {
            System.out.println(node.getValue());
        }
    }

    @Test
    public void testCircleQueue() {
        ArrayCircleQueue arrayCircleQueue = new ArrayCircleQueue(10);
        for (int i = 0; i < 10; i++) {
            arrayCircleQueue.enqueue(String.valueOf(i));
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(arrayCircleQueue.dequeue());
        }
        for (int i = 10; i < 20; i++) {
            boolean flag = arrayCircleQueue.enqueue(String.valueOf(i));
            System.out.println(i + "入队列" + flag);
        }
    }
}
