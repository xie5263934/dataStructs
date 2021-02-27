package com.test.sheldon;

public class InsertionSort {

    public void sort(int arr[]){
        if(arr.length <=1){
            return;
        }
       for(int i = 1; i<arr.length; i++){
           int tmp = arr[i];
           int j = i-1;
           for(;j>=0;j--){
               if(tmp < arr[j]){
                   arr[j+1]=arr[j];
               }else{
                   break;
               }
           }
           arr[j+1]=tmp;
       }
    }
}
