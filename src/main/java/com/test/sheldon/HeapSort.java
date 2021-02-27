package com.test.sheldon;

public class HeapSort {

    public void sort(int arr[]) {
        if (arr.length <= 1) {
            return;
        }
        innerSort(arr);
    }

    private void innerSort(int arr[]) {
        int n = arr.length-1;
        for (int i = n / 2; i >= 0; i--) {
            heap(arr,i,n);
        }
        for(int i=n;i>=1; i--){
            swap(arr,i,0);
            heap(arr,0,i-1);
        }
    }

    private void heap(int[] arr, int i, int n) {
        while (true) {
            int maxPos = i;
            if (i * 2+1 <= n && arr[i] < arr[i * 2+1]) {
                maxPos = i * 2+1;
            }
            if (i * 2 + 2 <= n && arr[maxPos] < arr[i * 2 + 2]) {
                maxPos = i * 2 + 2;
            }
            if (maxPos == i) {
                break;
            }
            swap(arr, i, maxPos);
            i = maxPos;
        }
    }

    private void swap(int[] tmp, int i, int j) {
        int value = tmp[i];
        tmp[i] = tmp[j];
        tmp[j] = value;
    }

}
