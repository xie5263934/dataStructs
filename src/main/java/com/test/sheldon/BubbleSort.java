package com.test.sheldon;

public class BubbleSort {
    public void sort(int arr[]){
        if(arr.length <= 1){
            return;
        }
        for(int i = arr.length -1; i>=0; i--){
            for(int j = 0; j<i; j++){
                if(arr[j] > arr[j+1]){
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1]= tmp;
                }
            }
        }
    }
}
