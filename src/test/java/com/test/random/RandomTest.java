package com.test.random;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * @Auth:jinrun.xie
 * @Date:2020/9/28
 **/
public class RandomTest {

    @Test
    public void test1() {
        Random random = new Random();
        IntegerTest[] arr = new IntegerTest[200];
        for (int i = 0; i < 200; i++) {
            int cmp = random.nextInt(1000);
            IntegerTest integerTest = new IntegerTest();
            integerTest.setCmp(cmp);
            integerTest.setValue(String.valueOf(cmp));
            arr[i] = integerTest;
        }
        Arrays.sort(arr);
        for (int i = 0; i < 200; i++) {
            System.out.println(arr[i].getValue());
        }
    }
}
