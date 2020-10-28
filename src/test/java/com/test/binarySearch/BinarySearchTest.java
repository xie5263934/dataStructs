package com.test.binarySearch;

import org.junit.Test;

import java.util.Random;

public class BinarySearchTest {

    @Test
    public void test() {
        int arr[] = new int[20];
        arr[0] = 3;
        Random random = new Random();
        for (int i = 1; i < arr.length; i++) {
            arr[i] = arr[i - 1] + random.nextInt(20);
        }
        BinarySearch binarySearch = new BinarySearch();
        for (int i = 0; i < arr.length; i++) {

        }
        System.out.println(binarySearch.search(arr, arr.length, 154));
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }
    }

    @Test
    public void testSquare() {
        double a = 0.04;
        int precious = 6;
        BinarySearchForSquare binarySearchForSquare = new BinarySearchForSquare();
        System.out.println(binarySearchForSquare.getSquare(a, precious));
    }

    @Test
    public void testFirstValue() {
        int arr[] = new int[]{1, 3, 4, 5, 6, 8, 8, 8, 11, 18};
        BinarySearchForFirstValue binarySearch = new BinarySearchForFirstValue();
        System.out.println(binarySearch.binarySearch(arr, arr.length, 5));
    }

    @Test
    public void testLastValue() {
        int arr[] = new int[]{1, 3, 4, 5, 6, 8, 8, 8, 8, 18};
        BinarySearchForLastValue binarySearch = new BinarySearchForLastValue();
        System.out.println(binarySearch.binarySearch(arr, arr.length, 8));
    }

    @Test
    public void testFirstGreaterValue() {
        int arr[] = new int[]{1, 3, 4, 5, 6, 8, 8, 14, 17, 18};
        BinarySearchForFristGreaterValue binarySearch = new BinarySearchForFristGreaterValue();
        System.out.println(binarySearch.binarySearch(arr, arr.length, 13));
    }

    @Test
    public void testLastLittleValue() {
        int arr[] = new int[]{1, 3, 4, 5, 6, 8, 8, 14, 17, 18};
        BinarySearchForLastLittleValue binarySearch = new BinarySearchForLastLittleValue();
        System.out.println(binarySearch.binarySearch(arr, arr.length, 7));
    }

    @Test
    public void testRecycleValue(){
        int arr[] = new int[]{4,5,6,1,2,3};
        BinarySearchForRecycleValue binarySearch = new BinarySearchForRecycleValue();
        System.out.println(binarySearch.binarySearch(arr,arr.length,3));
    }
}
