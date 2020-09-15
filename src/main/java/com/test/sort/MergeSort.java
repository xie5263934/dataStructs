package com.test.sort;

/**
 * @Auth:jinrun.xie
 * @Date:2020/9/15 归并排序
 * 归并排序的思路是我们对要排序的数据，从中间将要排序的数据分成两个部分，如果两个部分已经是有序的了，那么我们只需要将两部分合并起来，那么整个就是有序的，所以我们第一步就是
 * 不停的从中间对数据进行分节，直到每个小部分只有一个元素为止，对于一个元素的情况，我们认为就是有序的,这个时候也就是递归的终止条件就是开始下标大于等于结束下标了，然后开始归并的过程
 * 将左段数组和右段数组合并在一起，这个时候要注意的是右段数组的开始下标是中间+1的位置，这样不断的合并，最后整个数据就是有序的了
 **/
public class MergeSort {

    public void sort(int[] arr, int n) {
        if (n <= 1) {
            return;
        }
        mergeSort(arr, 0, n - 1);
    }

    public void mergeSort(int[] arr, int s, int e) {
        //递归的终止条件，当分解的开始下标开始大于等于结束下标的时候，就终止了
        if (s >= e) {
            return;
        }
        //将当前要排序的数据从中间分开，分成两段排序
        int q = (s + e) / 2;
        //对左段开始排序
        mergeSort(arr, s, q);
        //对有段开始排序
        mergeSort(arr, q + 1, e);
        //然后对已经有序的数据进行合并
        merge(arr, s, q, e);
    }

    public void merge(int[] arr, int s, int q, int e) {
        //从左段数组的第一个元素开始
        int i = s;
        //从右段数组的第一个元素开始，这里要特别注意一下，q表示中间，所以右段数组的第一个元素是q+1
        int j = q+1;
        int k = 0;
        int[] tmp = new int[arr.length];
        while (i <= q && j <= e) {
            if (arr[i] < arr[j]) {
                tmp[k++] = arr[i++];
            } else {
                tmp[k++] = arr[j++];
            }
        }
        if (i <= q) {
            while (i <= q) {
                tmp[k++] = arr[i++];
            }
        } else {
            while (j <= e) {
                tmp[k++] = arr[j++];
            }
        }
        for (k = 0; k <= e-s; k++) {
            arr[s + k] = tmp[k];
        }
    }
}
