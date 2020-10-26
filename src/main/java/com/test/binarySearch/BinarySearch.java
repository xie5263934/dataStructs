package com.test.binarySearch;

/**
 * 二分查找，时间复杂度是O(logn)，能够非常快速的在一个有序数组中查找一个数
 * 有两个前置条件
 * 1:底层的数据结构必须是数组，因为要支持下标的随机访问
 * 2:数据必须是有序的，因此适合数据变化比较少的场景，如果是动态数据，数据随时都在变化，就不太适合
 * 有三个需要注意的地方
 * 1:结束的条件是left<=right，这样才不会漏掉最后一个数据，因为当left=right的时候，整个序列长度是1，还是有内容的，还不能结束
 * 2:当左右区间的指针移动的时候，left=mid+1,right=mid-1，否则会出现left=mid=right的情况，
 * 导致mid一直等于left=right=(left+right)/2，从而出现死循环
 * 3:mid=(left+right)/2可能会出现left+right超出数据能表示的范围，从而溢出，计算错误。建议的方式是left+(right-left)/2
 */
public class BinarySearch {
    public int search(int arr [], int n,int value){
        //return commonBinarySearch(arr,0,n-1,value);
        return circleBinarySearch(arr,0,n-1,value);
    }

    /**
     * 普通的通过循环方式实现的二分查找
     * @param arr
     * @param left
     * @param right
     * @param value
     */
    private int commonBinarySearch(int arr[],int left, int right,int value){
        while(left<=right){
            int mid = left+(right-left)/2;
            if(arr[mid]==value){
                return mid;
            }else if(value>arr[mid]){
                left = mid+1;
            }else{
                right=mid-1;
            }
        }
        return -1;
    }

    private int circleBinarySearch(int arr[], int left, int right, int value){
        if(left>right){
            return -1;
        }
        int n;
        int mid = left+(right-left)/2;
        if(arr[mid]==value){
            return mid;
        }else if(arr[mid]<value){
            n = circleBinarySearch(arr,mid+1,right,value);
        }else{
            n =circleBinarySearch(arr,left,mid-1,value);
        }
        return n;
    }
}
