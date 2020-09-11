package com.test.stack;

import org.junit.Test;

/**
 * @Auth:jinrun.xie
 * @Date:2020/9/11
 **/
public class StackTest {

    @Test
    public void testPattern(){
        ReguleCaculate caculate = new ReguleCaculate(20);
        System.out.println(caculate.eval("5*6"));
    }

    @Test
    public void testSymbols(){
        SymbolCheck symbolCheck = new SymbolCheck(20);
        System.out.println(symbolCheck.check("{({}})"));
    }
}
