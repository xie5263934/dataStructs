package com.test.sheldon;

import org.junit.Test;


public class QuickSortTest {


    @Test
    public void testQuickSort() {
        int[] arr = {9, 2, 1, 14, 0, 18, 20, 100, 32};
        QuickSort quickSort = new QuickSort();
        quickSort.sort(arr);
        print(arr);
    }

    @Test
    public void testInsertionSort() {
        int[] arr = {9, 2, 1, 14, 0, 18, 20, 100, 32};
        InsertionSort sort = new InsertionSort();
        sort.sort(arr);
        print(arr);
    }

    private void print(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    @Test
    public void testBubbleSort() {
        int[] arr = {9, 2, 1, 14, 0, 18, 20, 100, 32};
        BubbleSort sort = new BubbleSort();
        sort.sort(arr);
        print(arr);
    }

    @Test
    public void testSelectionSort() {
        int[] arr = {9, 2, 1, 14, 0, 18, 20, 100, 32};
        BubbleSort sort = new BubbleSort();
        sort.sort(arr);
        print(arr);
    }

    @Test
    public void testMergeSort() {
        int[] arr = {9, 2, 1, 14, 0, 18, 20, 100, 32};
        MergeSort sort = new MergeSort();
        sort.sort(arr);
        print(arr);
    }

    @Test
    public void testHeapSort() {
        int[] arr = {9, 2, 1, 14, 0, 18, 20, 100, 32};
        HeapSort sort = new HeapSort();
        sort.sort(arr);
        print(arr);
    }
}
