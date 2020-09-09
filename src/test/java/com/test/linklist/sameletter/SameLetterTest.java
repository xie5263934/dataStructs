package com.test.linklist.sameletter;

import org.junit.Test;

/**
 * @Auth:jinrun.xie
 * @Date:2020/9/9
 **/
public class SameLetterTest {

    @Test
    public void test() {
        Node header = new Node();
        LetterLinkList letterLinkList = new LetterLinkList(header);
        letterLinkList.add("A");
        letterLinkList.add("B");
        letterLinkList.add("C");
        letterLinkList.add("D");
        letterLinkList.add("E");
        letterLinkList.add("F");
        letterLinkList.add("G");
        letterLinkList.add("H");
        letterLinkList.add("I");
        letterLinkList.add("I");
        letterLinkList.add("H");
        letterLinkList.add("G");
        letterLinkList.add("F");
        letterLinkList.add("E");
        letterLinkList.add("D");
        letterLinkList.add("C");
        letterLinkList.add("B");
        letterLinkList.add("A");
        letterLinkList.add("X");
        letterLinkList.add("X");
        letterLinkList.print();
        System.out.println(letterLinkList.check());
    }
}
