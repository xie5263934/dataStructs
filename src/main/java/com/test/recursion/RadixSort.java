package com.test.recursion;

/**
 * @Auth 45208
 * @Date 9/21/2020
 * 基数排序，对于可以要排序的数据，如果我们可以对数据进行按位分，那么对于两个数据，我们就可以按位进行比较，如果高位相同，我们再进行低位的比较，如果低位不相同那么就能确定两个数的大小
 * 在排序的时候，我们先从最后一位开始排序，直到最高位，并且在排序的过程中使用稳定的排序算法，这样高位排序的过程中，低位的有序性不会受到影响，这样最终排序出来的结果就是有序的
 **/
public class RadixSort {

    public void sort(String[] arr, int n) {
        if (n <= 1) {
            return;
        }

        for (int i = 2; i >= 0; i--) {
            innerSort(arr, i, n);
        }
    }

    public void innerSort(String[] arr, int i, int n) {
        int c[] = new int[26];
        for (int j = 0; j < 26; j++) {
            c[j] = 0;
        }
        for (int j = 0; j < n; j++) {
            String str = arr[j];
            int index = getIndex(str.charAt(i));
            c[index]++;
        }
        for (int j = 1; j < 26; j++) {
            c[j] = c[j] + c[j - 1];
        }
        String r[] = new String[n];
        for (int j = n - 1; j >= 0; j--) {
            String str = arr[j];
            int index = getIndex(str.charAt(i));
            int lastIndex = --c[index];
            r[lastIndex] = arr[j];
        }
        for (int j = 0; j < n; j++) {
            arr[j] = r[j];
        }
    }

    private int getIndex(char c) {
        char letter[] = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        int i = 0;
        for (; i < 26; i++) {
            if (c == letter[i]) {
                break;
            }
        }
        return i;
    }
}
