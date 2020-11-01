package com.test.binarySearch;

/**
 * 使用二分查找，在一个循环数组中查找等于指定值的元素
 * 首先我们假定当前数组是升序的，然后通过冒泡的方式两两比较，找到分界点，也就是上一段的开始和下一段的结束位置
 * 然后查找当前指定的值属于哪个分区
 * 在分区内使用二分查找查找指定的值
 *
 * @Auth:jinrun.xie
 * @Date:2020/10/28
 **/
public class BinarySearchForRecycleValue {

    public int binarySearch(int arr[], int n, int value) {
        int index = 0;
        for (int i = 0; i < n; i++) {
            if (i < n - 1) {
                if (arr[i] > arr[i + 1]) {
                    break;
                } else {
                    index++;
                }
            }
        }
        if (index == n) {
            index = 0;
        }
        int low = 0;
        int high = n - 1;
        if (arr[low] <= value && value <= arr[low + index]) {
            high = low + index;
        } else {
            low = low + index;
        }
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] == value) {
                return mid;
            } else if (arr[mid] < value) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}
