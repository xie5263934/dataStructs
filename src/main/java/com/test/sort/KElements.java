package com.test.sort;

/**
 * @Auth:jinrun.xie
 * @Date:2020/9/16 求解无序数组第K大元素
 * 1:我们可以使用任何排序算法，将所有的数据进行从大到小排序，然后遍历排序后的数组取a[k-1]
 * 2:可以使用选择排序法，我们每次都从待排序的数组中取最大的数据，然后依次放入数组前面，取K次，然后找到取数组a[k-1]
 * 3:冒泡排序，每次都将最大的泡冒到数组的前面，这样K次之后，取数组的a[k-1]
 * 以上方法最快也要k*O(n)的时间复杂度
 * 使用分治和分区的思想
 * 我们分区之后，将无序数组分成了三个部分a[s,p-1],a[p],a[p+1,e]它们的关系是，a[s,p-1]里面的所有元素都是大于等于a[p]的，而a[p+1,s]的所有元素都是小于等于a[p]的
 * 如果K==p+1,那么a[p]就是我们要找的元素，如果k<p+1,那么递归处理左边部分，如果k>p+1那么递归处理右边部分
 * 其中关键点是这里我们相当于使用了快排的倒序排序，将大的数据放在左边，将小的数据放在右边
 **/
public class KElements {

    public Integer find(int[] arr, int n, int k) {
        if (k > n) {
            return null;
        }
        return innerFind(arr, 0, n - 1, k);
    }

    public Integer innerFind(int[] arr, int s, int e, int k) {
        if (s >= e) {
            return null;
        }
        int p = partition(arr, s, e);
        if (p + 1 == k) {
            return arr[p];
        } else if (k < p + 1) {
            return innerFind(arr, s, p - 1, k);
        } else {
            return innerFind(arr, p + 1, e, k);
        }
    }

    public int partition(int[] arr, int s, int e) {
        int i = s;
        int j = s;
        int pivot = arr[e];
        for (; j < e; j++) {
            if (arr[j] > pivot) {
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
            }
        }
        arr[e] = arr[i];
        arr[i] = pivot;
        return i;
    }
}
