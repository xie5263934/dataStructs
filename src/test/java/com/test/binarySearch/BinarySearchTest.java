package com.test.binarySearch;

import org.junit.Test;

import java.util.Random;

public class BinarySearchTest {

    @Test
    public void test(){
        int arr[] = new int[20];
        arr[0]=3;
        Random random = new Random();
        for(int i =1; i<arr.length; i++){
            arr[i]= arr[i-1]+random.nextInt(20);
        }
        BinarySearch binarySearch = new BinarySearch();
        for(int i =0; i<arr.length; i++){

        }
        System.out.println(binarySearch.search(arr,arr.length,154));
        for(int i =0; i<arr.length; i++){
            System.out.print(arr[i]+",");
        }
    }
}
