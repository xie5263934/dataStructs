package com.test.binarySearch;

/**
 * 查找最后一个小于等于指定值的数据
 * 首先使用二分查找找到某个数，然后当前这个数和指定的值进行比较，如果这个数小于或者等于指定的值，并且这个数是最后一个元素或者它的下一个元素大于指定值，就是我们
 * 要查找的数，否则就继续在右区间查找
 * 如果找到的这个数比指定的数还要大，那么就在左区间找
 *
 * @Auth:jinrun.xie
 * @Date:2020/10/28
 **/
public class BinarySearchForLastLittleValue {

    public int binarySearch(int arr[], int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] <= value) {
                if (mid == n - 1 || arr[mid + 1] > value) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}
