package com.test.skiplist;

import org.junit.Test;

/**
 * @Auth 45208
 * @Date 11/1/2020
 **/
public class SkipListTest {

    @Test
    public void test(){
        MySkipList mySkipList = new MySkipList();
        mySkipList.insert(5);
        mySkipList.insert(9);
        mySkipList.insert(14);
        mySkipList.insert(20);
        mySkipList.insert(23);
        mySkipList.insert(24);
        mySkipList.insert(32);
        mySkipList.insert(41);
        mySkipList.insert(52);
        mySkipList.insert(36);
        mySkipList.insert(19);
        mySkipList.insert(21);
        mySkipList.insert(30);
        mySkipList.printAll_beautiful();
        System.out.println(mySkipList.find(24));
        mySkipList.delete(24);
        mySkipList.printAll_beautiful();
    }
}
