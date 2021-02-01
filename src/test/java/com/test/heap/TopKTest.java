package com.test.heap;

import org.junit.Test;

import java.util.Random;

/**
 * @Auth:jinrun.xie
 * @Date:2021/2/1
 **/
public class TopKTest {

    @Test
    public void test() {
        TopK topK = new TopK(5);
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            int n = random.nextInt(100);
            System.out.println(n + ",");
            topK.add(n);
        }
        topK.print();
    }
}
