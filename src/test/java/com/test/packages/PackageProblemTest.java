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

    @Test
    public void testPackageProblem2(){
        PackageProblem2 problem2 = new PackageProblem2();
        problem2.handle();
    }

    @Test
    public void testPackageProblem3(){
        PackageProblem3 problem3 = new PackageProblem3();
        problem3.handle();
    }
}
