package com.test.stack;

/**
 * @Auth:jinrun.xie
 * @Date:2020/9/11 实现一个栈, 底层用数组实现一个固定大小的顺序栈
 **/
public class MyStack {
    /**
     * 栈的底层数组
     */
    private String[] items;
    /**
     * 栈的容量
     */
    private int n;
    /**
     * 当前栈里面的元素个数
     */
    private int count = 0;

    public MyStack(int n) {
        this.items = new String[n];
        this.n = n;
    }

    /**
     * 入栈操作，如果没有充足空间了，就报失败
     *
     * @param value
     * @return
     */
    public boolean push(String value) {
        if (count == n) {
            return false;
        }
        items[count] = value;
        count++;
        return true;
    }

    public String pop() {
        if (count == 0) {
            return null;
        }
        count--;
        return items[count];
    }
}
