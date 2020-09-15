package com.test.sort;

/**
 * @Auth:jinrun.xie
 * @Date:2020/9/15 冒泡排序，核心思想就是相邻两个元素进行比较交换，将最大或者最小的元素移动到数组末尾
 * 平均时间复杂度O(n²),因为我们在处理过程中，如果两个元素相等就不进行位置交换，所以彼此位置关系是保持不变的，因此是个稳定的排序算法
 **/
public class BubbleSort {

    public void sort(int arr[], int n) {
        if (n <= 1) {
            return;
        }
        for (int i = 0; i < n; i++) {
            boolean flag = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    flag = true;
                }
            }
            if (!flag) {
                return;
            }
        }
    }
}
