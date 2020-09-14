package com.test.queue;

/**
 * @Auth:jinrun.xie
 * @Date:2020/9/14 数组实现的简单队列
 **/
public class ArrayQueue {
    /**
     * 队列头指针
     */
    private int header;

    /**
     * 队列尾指针
     */
    private int tail;

    /**
     * 数组大小
     */
    private int n;
    /**
     * 队列底层实现
     */
    private String[] items;

    public ArrayQueue(int n) {
        this.n = n;
        items = new String[n];
        this.header = 0;
        this.tail = 0;
    }

    public boolean enqueue(String value) {
        //如果尾指针指向了数组的末尾
        if (tail == n) {
            //并且这个时候头指针指向了数组的头，那么数组就真的满了，入队列失败
            if (header == 0) {
                return false;
            }
            //从当前头指针开始到尾指针之间的所有元素全部挪到数组开头
            for (int i = header; i < tail; i++) {
                items[i - header] = items[i];
            }
            //重置两个指针
            tail -= header;
            header = 0;
        }
        items[tail] = value;
        tail++;
        return true;
    }

    public String dequeue() {
        String result = null;
        //当前队列是空的
        if (header == tail) {
            return null;
        }
        result = items[header];
        header++;
        return result;
    }
}
