package com.test.stringmatch;

import org.junit.Test;

/**
 * @Auth:jinrun.xie
 * @Date:2021/2/7
 **/
public class StringMatchTest {

    @Test
    public void testBF() {
        char[] main = new char[10];
        main[0] = 'a';
        main[1] = 'c';
        main[2] = 'd';
        main[3] = 'y';
        main[4] = 'e';
        main[5] = 'x';
        main[6] = 'm';
        main[7] = 'a';
        main[8] = 'b';
        main[9] = 'c';
        char[] pattern = new char[3];
        pattern[0] = 'x';
        pattern[1] = 'm';
        pattern[2] = 'a';
        BruteForce bruteForce = new BruteForce(main);
        System.out.println(bruteForce.indexOf(pattern));
    }


    @Test
    public void testRK() {
        char[] main = new char[10];
        main[0] = 'a';
        main[1] = 'c';
        main[2] = 'd';
        main[3] = 'y';
        main[4] = 'e';
        main[5] = 'x';
        main[6] = 'm';
        main[7] = 'a';
        main[8] = 'b';
        main[9] = 'c';
        char[] pattern = new char[3];
        pattern[0] = 'm';
        pattern[1] = 'a';
        pattern[2] = 'b';
        RKMatch rkMatch = new RKMatch(main);
        System.out.println(rkMatch.indexOf(pattern));
    }


    @Test
    public void testBM() {
        char[] main = new char[10];
        main[0] = 'a';
        main[1] = 'c';
        main[2] = 'd';
        main[3] = 'y';
        main[4] = 'e';
        main[5] = 'x';
        main[6] = 'm';
        main[7] = 'a';
        main[8] = 'b';
        main[9] = 'c';
        char[] pattern = new char[3];
        pattern[0] = 'm';
        pattern[1] = 'a';
        pattern[2] = 'b';
        BMMatch bmMatch = new BMMatch();
        System.out.println(bmMatch.indexOf(main,pattern));
    }
}
