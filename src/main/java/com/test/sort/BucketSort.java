package com.test.sort;

/**
 * @Auth 45208
 * @Date 9/16/2020
 * 桶排序，对数据要求很严格，核心思想是，将数据分散到N个自然有序的桶中，然后对每个桶里面的数据进行排序，最后整个数据就是有序的了
 * 使用场景是外部排序，比如mysql的外部排序等
 **/
public class BucketSort {

    public void sort(int[] arr) {
        int i1 = 0;
        int[] arr1 = new int[20];
        int i2 = 0;
        int[] arr2 = new int[20];
        int i3 = 0;
        int[] arr3 = new int[20];
        int i4 = 0;
        int[] arr4 = new int[20];
        int i5 = 0;
        int[] arr5 = new int[20];
        for (int i = 0; i < arr.length; i++) {
            if (0 <= arr[i] && arr[i] <= 20) {
                arr1[i1++] = arr[i];
            }
            if (21 <= arr[i] && arr[i] <= 40) {
                arr2[i2++] = arr[i];
            }
            if (41 <= arr[i] && arr[i] <= 60) {
                arr3[i3++] = arr[i];
            }
            if (61 <= arr[i] && arr[i] <= 80) {
                arr4[i4++] = arr[i];
            }
            if (81 <= arr[i] && arr[i] <= 100) {
                arr5[i5++] = arr[i];
            }
        }
        if (i1 > 0) {
            innerSort(arr1, 0, i1 - 1);
        }
        if (i2 > 0) {
            innerSort(arr2, 0, i2 - 1);
        }
        if (i3 > 0) {
            innerSort(arr3, 0, i3 - 1);
        }
        if (i4 > 0) {
            innerSort(arr4, 0, i4 - 1);
        }
        if (i5 > 0) {
            innerSort(arr5, 0, i5 - 1);
        }
        int i = 0;
        if (i1 > 0) {
            for (int j = 0; j < i1; j++) {
                arr[i++] = arr1[j];
            }
        }
        if (i2 > 0) {
            for (int j = 0; j < i2; j++) {
                arr[i++] = arr2[j];
            }
        }
        if (i3 > 0) {
            for (int j = 0; j < i3; j++) {
                arr[i++] = arr3[j];
            }
        }
        if (i4 > 0) {
            for (int j = 0; j < i4; j++) {
                arr[i++] = arr4[j];
            }
        }
        if (i5 > 0) {
            for (int j = 0; j < i5; j++) {
                arr[i++] = arr5[j];
            }
        }
    }

    public void innerSort(int arr[], int s, int e) {
        if (s >= e) {
            return;
        }
        int q = partition(arr, s, e);
        innerSort(arr, s, q - 1);
        innerSort(arr, q + 1, e);
    }

    public int partition(int arr[], int s, int e) {
        int i = s;
        int j = s;
        int pivot = arr[e];
        for (; j < e; j++) {
            if (arr[j] < pivot) {
                int tmp = arr[j];
                arr[j] = arr[i];
                arr[i] = tmp;
                i++;
            }
        }
        arr[e] = arr[i];
        arr[i] = pivot;
        return i;
    }
}
