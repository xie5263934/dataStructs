package com.test.sheldon;

public class SelectionSort {

    public void sort(int arr []){
        if(arr.length <= 1){
            return;
        }
        for(int i = 0; i<arr.length; i++){
            int min = arr[i];
            for(int j = i+1; j<arr.length; j++){
                if(min > arr[j]){
                    min = arr[j];
                }
            }
            arr[i]=min;
        }
    }
}
