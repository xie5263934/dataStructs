package com.test.queue;

/**
 * @Auth:jinrun.xie
 * @Date:2020/9/14 底层使用数组实现的循环队列
 * 主要的原理是利用头指针和尾指针对于数组大小进行取余操作，从而实现了头指针和尾指针在数组大小范围内的循环移动
 * 其中当头指针和尾指针重合的时候，队列就是空的
 * 当尾指针+1对数组大小取余等于头指针的时候链表就满了(这种实现方式带来的好处是不需要移动数据，但是带来的坏处是有一个数组元素空间的浪费)
 **/
public class ArrayCircleQueue {
    /**
     * 队列头指针
     */
    private int header = 0;
    /**
     * 队列尾指针
     */
    private int tail = 0;
    /**
     * 队列底层数组
     */
    private String[] items;

    private int n;

    public ArrayCircleQueue(int n) {
        this.n = n;
        items = new String[n];
    }

    public boolean enqueue(String value) {
        if ((tail + 1) % n == header) {
            return false;
        }
        items[tail] = value;
        //tail在n的范围之内循环增加，当tail+1等于n的时候又从0开始
        tail = (tail + 1) % n;
        return true;
    }

    public String dequeue() {
        if (tail == header) {
            return null;
        }
        String result = items[header];
        header = (header + 1) % n;
        return result;
    }
}
