package com.test.stringmatch;

import java.util.Arrays;

/**
 * @Auth:jinrun.xie
 * @Date:2021/2/7
 **/
public class RKMatch {
    private char[] character = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private char[] main;

    public RKMatch(char[] main) {
        this.main = main;
    }

    public int indexOf(char[] pattern) {
        int index = -1;
        if (pattern.length > main.length) {
            return index;
        }
        int patternHash = hash(pattern);
        for (int i = 0; i < main.length - pattern.length + 1; i++) {
            boolean match = false;
            int start = i;
            char[] sub = Arrays.copyOfRange(main, i, i + pattern.length);
            int hash = hash(sub);
            if (patternHash == hash) {
                match = true;
                for (int j = 0; j < pattern.length; j++) {
                    if (main[start] != pattern[j]) {
                        match = false;
                        break;
                    }
                    start++;
                }
            }
            if (match) {
                return i;
            }
        }
        return index;
    }

    private int hash(char[] sequence) {
        int hash = 0;
        for (int i = 0; i < sequence.length; i++) {
            hash += character[getIndex(sequence[i])];
        }
        return hash;
    }

    private int getIndex(char s) {
        int index = -1;
        for (int i = 0; i < character.length; i++) {
            if (character[i] == s) {
                return i;
            }
        }
        return index;
    }
}
