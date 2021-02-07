package com.test.stringmatch;

/**
 * 暴力匹配算法
 * 算法的核心思想就是从主串的第0个元素开始，依次取长度为模式串的字串，和模式串比较是否一样，如果一样就找到，如果不一样就从下一个元素开始，直到主串中开始元素到剩余元素的的字串长度
 * 小于模式串为止
 *
 * @Auth:jinrun.xie
 * @Date:2021/2/7
 **/
public class BruteForce {
    private char[] main;

    public BruteForce(char[] main) {
        this.main = main;
    }

    public int indexOf(char[] pattern) {
        int index = -1;
        if (pattern.length > main.length) {
            return index;
        }
        for (int i = 0; i < main.length- pattern.length + 1; i++) {
            boolean match = true;
            int start = i;
            for (int j = 0; j < pattern.length && start < main.length; j++) {
                if (main[start] != pattern[j]) {
                    match = false;
                    break;
                }
                start++;
            }
            if (match) {
                return i;
            }
        }
        return index;
    }


}
