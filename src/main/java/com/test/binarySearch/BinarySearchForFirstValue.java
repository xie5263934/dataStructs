package com.test.binarySearch;

/**
 * 当前有序数组中存在重复的数字，使用二分查找法，查找第一个等于指定值的数字
 * 思考，对于这种有重复值的情况，当我们使用简单的二分查找法就不好使了，因为有可能我们返回的就不是第一个等于指定值的数字，那么在这种情况下，
 * 假如当前我们找到某个数等于指定的值，我们就需要判断当前这个数是否是指定值的第一个数了，如果当前这个数的下标是0或者它的前一个数不等于指定的值，那么它就是第一个等于指定值的数
 * 否则我们应该继续在左区间查找，重复上面的步骤直到区间为0
 *
 * @Auth:jinrun.xie
 * @Date:2020/10/28
 **/
public class BinarySearchForFirstValue {

    public int binarySearch(int arr[], int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] < value) {
                low = mid + 1;
            } else if (arr[mid] > value) {
                high = mid - 1;
            } else {
                if (mid == 0 || arr[mid - 1] != value) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

}
