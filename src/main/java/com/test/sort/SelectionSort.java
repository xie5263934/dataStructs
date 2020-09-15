package com.test.sort;

/**
 * @Auth:jinrun.xie
 * @Date:2020/9/15 选择排序
 * 我们将整个要排序的数据分成已排序部分和未排序部分，首先我们默认整个数据都是未排序的，然后每次从未排序部分选出最大或者最小的数据，然后放到已排序部分的末尾
 * 平均时间复杂度O(n²),是不稳定的排序算法，因为某个数据虽然位于另外一个相同数据的前面，但是因为正好位于已排序数据的末尾，
 * 但是与未排序数据中心最大或者最小数据进行位置交换的时候，放到了另外一个相同数据的后面了
 **/
public class SelectionSort {

    public void sort(int arr[], int n) {
        if (n <= 1) {
            return;
        }
        for (int i = 0; i < n; i++) {
            int value = arr[i];
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (value > arr[j]) {
                    value = arr[j];
                    min = j;
                }
            }
            int tmp = arr[i];
            arr[i] = arr[min];
            arr[min] = tmp;
        }
    }
}
