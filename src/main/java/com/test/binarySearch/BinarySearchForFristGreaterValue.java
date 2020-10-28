package com.test.binarySearch;

/**
 * 查找第一个大于等于指定值的元素
 * 这里我们还是使用二分法进行查找，当查找到的某个数比指定值小的时候，就在右区间继续查找
 * 如果查找到的某个数比指定值大，但是它的前一个数比指定值小，那么这个数就是我们要查找的数
 * 否则就继续在左区间继续查找
 * 重复上面的过程直到区间为0
 * @Auth:jinrun.xie
 * @Date:2020/10/28
 **/
public class BinarySearchForFristGreaterValue {

    public int binarySearch(int arr[] , int n, int value){
        int low = 0;
        int high = n-1;
        while(low<=high){
            int mid = low+((high-low)>>1);
            if(arr[mid]<value){
                low=mid+1;
            }
            else{
                if(mid==0||arr[mid-1]<value){
                    return mid;
                }else{
                    high=mid-1;
                }
            }
        }
        return -1;
    }
}
