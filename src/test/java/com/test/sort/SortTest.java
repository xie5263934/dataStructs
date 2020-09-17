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

    @Test
    public void testSort1() {
        Random random = new Random();
        int[] arr = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = random.nextInt(100);
            System.out.println(arr[i]);
        }
        MySortTest sort = new MySortTest();
        sort.quickSort(arr, arr.length);
        for (int i = 0; i < 10; i++) {
            System.out.println(arr[i]);
        }
    }

    @Test
    public void testSort2() {
        Random random = new Random();
        int[] arr = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = random.nextInt(100);
            System.out.println(arr[i]);
        }
        MySortTest sort = new MySortTest();
        sort.mergeSort(arr, arr.length);
        for (int i = 0; i < 10; i++) {
            System.out.println(arr[i]);
        }
    }

    @Test
    public void testFindElements() {
        Random random = new Random();
        int[] arr = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = random.nextInt(100);
            System.out.println(arr[i]);
        }
        KElements sort = new KElements();
        Integer value = sort.find(arr, 10, 4);
        if (value != null) {
            System.out.println(value);
        }
    }

    @Test
    public void testBucketSort() {
        Random random = new Random();
        int[] arr = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = random.nextInt(100);
            System.out.println(arr[i]);
        }
        BucketSort sort = new BucketSort();
        sort.sort(arr);
        for (int i = 0; i < 10; i++) {
            System.out.println(arr[i]);
        }
    }

    @Test
    public void testCountSort() {
        Random random = new Random();
        int[] arr = new int[8];
        for (int i = 0; i < 8; i++) {
            arr[i] = random.nextInt(10);
            System.out.println(arr[i]);
        }
        CountSort sort = new CountSort();
        sort.sort(arr, arr.length);
        for (int i = 0; i < 8; i++) {
            System.out.println(arr[i]);
        }
    }
}
