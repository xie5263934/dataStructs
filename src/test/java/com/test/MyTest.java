package com.test;

import org.junit.Test;

/**
 * @Auth:jinrun.xie
 * @Date:2020/10/20
 **/
public class MyTest {

    @Test
    public void testMinRun(){
        int n = 996;
        int r = 0;
        while(n>=64){
            r |= n&1;
            n>>=1;
        }
        System.out.println(n+r);
    }
}
