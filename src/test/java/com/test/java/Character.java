package com.test.java;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 对于多线程交替打印的问题，关键点有三个
 * 第一个就是要有一个全局的线程安全共享变量，来推进数据的往后移动
 * 第二点就是要有对应线程个数的condition，用来控制线程的阻塞和唤醒
 * 第三点就是在线程工作里面我们需要有个循环来判断是否当前情况是属于当前线程的，如果是就退出循环继续下面的情况，如果不是就进入循环继续阻塞，直到被其它线程唤醒再判断条件
 * @Auth:jinrun.xie
 * @Date:2021/3/12
 **/
public class Character {
    /**
     * 有一个线程共享变量来推进数据的往后移动，对于所有线程都是可见的，并且是线程安全的，使用volatile修饰
     */
    private volatile int number = 0;
    /**
     * 创建线程对应个数的condition，每个线程对应使用condition，这样能够精确地控制某个线程的阻塞和唤醒
     */
    private Lock lock = new ReentrantLock();
    Condition[] conditions = new Condition[]{
        lock.newCondition(),lock.newCondition(),lock.newCondition()
    };

    /**
     * 对应的业务方法，业务方法的入参就是当前线程要处理情况的标识，比如参数传入的是0，那么当前线程要处理的就是对应数据取余等于0的情况。最外层的循环用来控制要处理的次数
     * @param self
     */
    public void run(final int self) {
        while (number <= 17) { //业务次数的控制，当全局变量控制的业务次数超过了这个之后，就退出当前线程的运行，否则当前线程就继续运行
            try {
                lock.lock(); //首先要加锁，因为只有在加锁里面才能够使用condition条件
                /**
                 * 这里是最关键的代码，这里使用一个循环来不停判断当前线程要处理的条件是否满足，如果不满足就进入循环然后阻塞，直到被其它线程唤醒，然后再次进入判断条件
                 * 所以这里使用要给while循环，否则当当前线程被唤醒以后，并没有判断当前线程要处理的情况是否被满足了，就直接执行下面的代码了，这样就出错了。
                 */
                while (number % 3 != self) {
                    conditions[self].await();
                }
                /**
                 * 当业务代码走到这里，表示当前线程要处理的业务情况满足了，进行业务处理，然后将全局变量往后推，并且唤醒下一个线程来处理
                 */
                System.out.println(Thread.currentThread().getName() + ":" + number);
                number++;
                conditions[(self + 1) % 3].signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        Character character = new Character();
        for (int i = 0; i < 3; i++) {
            int j = i;
            new Thread(() -> character.run(j)).start();
        }
    }
}
