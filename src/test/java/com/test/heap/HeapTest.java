package com.test.heap;

import org.junit.Test;

import java.util.Random;

/**
 * @Auth:jinrun.xie
 * @Date:2021/2/1
 **/
public class HeapTest {

    @Test
    public void test(){
        Heap heap = new Heap(10);
        Random random = new Random();
        for(int i =0; i< 10; i++){
            heap.addItem(random.nextInt(30));
        }
        heap.print();
       // heap.heapFiyLow();
        heap.heapFiyHigh();
        heap.sort();
        heap.print();
    }
}
