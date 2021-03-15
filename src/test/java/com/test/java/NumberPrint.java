package com.test.java;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auth:jinrun.xie
 * @Date:2021/3/12
 **/
public class NumberPrint {

    private volatile int number = 0;
    private Lock lock = new ReentrantLock();
    Condition zero = lock.newCondition();
    Condition one = lock.newCondition();

    public void runZero() {
        while (number < 20) {
            try {
                lock.lock();
                while (number % 2 != 0) {
                    zero.await();
                }
                System.out.println(number);
                number++;
                one.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                ;
            }
        }
    }


    public void runOne() {
        while (number < 20) {
            try {
                lock.lock();
                while (number % 2 != 1) {
                    one.await();
                }
                System.out.println(number);
                number++;
                zero.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                ;
            }
        }
    }

    public static void main(String[] args) {
        NumberPrint print = new NumberPrint();
        new Thread(() -> print.runZero()).start();
        new Thread(() -> print.runOne()).start();
    }
}
