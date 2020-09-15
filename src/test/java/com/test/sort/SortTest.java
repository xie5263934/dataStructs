package com.test.sort;

import org.junit.Test;

import java.util.Random;

/**
 * @Auth:jinrun.xie
 * @Date:2020/9/15
 **/
public class SortTest {

    @Test
    public void testBubble() {
        Random random = new Random();
        int[] arr = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = random.nextInt(100);
            System.out.println(arr[i]);
        }
        BubbleSort sort = new BubbleSort();
        sort.sort(arr, arr.length);
        for (int i = 0; i < 10; i++) {
            System.out.println(arr[i]);
        }
    }

    @Test
    public void testInsertion() {
        Random random = new Random();
        int[] arr = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = random.nextInt(100);
            System.out.println(arr[i]);
        }
        InsertSort sort = new InsertSort();
        sort.sort(arr, arr.length);
        for (int i = 0; i < 10; i++) {
            System.out.println(arr[i]);
        }
    }


    @Test
    public void testSelection() {
        Random random = new Random();
        int[] arr = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = random.nextInt(100);
            System.out.println(arr[i]);
        }
        SelectionSort sort = new SelectionSort();
        sort.sort(arr, arr.length);
        for (int i = 0; i < 10; i++) {
            System.out.println(arr[i]);
        }
    }

    @Test
    public void testMergeSort() {
        Random random = new Random();
        int[] arr = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = random.nextInt(100);
            System.out.println(arr[i]);
        }
        MergeSort sort = new MergeSort();
        sort.sort(arr, arr.length);
        for (int i = 0; i < 10; i++) {
            System.out.println(arr[i]);
        }
    }

    @Test
    public void testQuickSort() {
        Random random = new Random();
        int[] arr = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = random.nextInt(100);
            System.out.println(arr[i]);
        }
        QuickSort sort = new QuickSort();
        sort.sort(arr, arr.length);
        for (int i = 0; i < 10; i++) {
            System.out.println(arr[i]);
        }
    }
}
