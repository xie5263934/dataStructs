package com.test.sheldon;

public class QuickSort {
    public void sort(int arr[]) {
        sort(arr, 0, arr.length - 1);
    }

    private void sort(int arr[], int s, int e) {
        if (s >= e) {
            return;
        }
        int q = partition(arr, s, e);
        sort(arr, s, q - 1);
        sort(arr, q + 1, e);
    }

    private int partition(int arr[], int s, int e) {
        int i = s;
        int j = s;
        int povit = arr[e];
        for (; j < e; j++) {
            if (arr[j] < povit) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
            }
        }
        arr[e] = arr[i];
        arr[i] = povit;
        return i;
    }
}
