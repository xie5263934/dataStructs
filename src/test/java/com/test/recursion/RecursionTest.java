package com.test.recursion;

import org.junit.Test;

/**
 * @Auth:jinrun.xie
 * @Date:2020/9/14
 **/
public class RecursionTest {

    @Test
    public void testStairs(){
        StairsProblem stairsProblem = new StairsProblem();
        System.out.println(stairsProblem.stairNumbers(6));
    }
}
