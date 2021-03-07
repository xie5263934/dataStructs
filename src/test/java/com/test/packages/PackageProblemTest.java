package com.test.packages;

import org.junit.Test;

/**
 * @Auth 45208
 * @Date 3/7/2021
 **/
public class PackageProblemTest {

    @Test
    public void testPackageProblem1(){
        PackageProblem packageProblem = new PackageProblem();
        packageProblem.f(0,0);
        System.out.println(packageProblem.getMaxW());
    }
}
