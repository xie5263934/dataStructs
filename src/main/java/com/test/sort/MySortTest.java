package com.test.sort;

/**
 * @Auth:jinrun.xie
 * @Date:2020/9/16 对快排和归并再进行一次手写和记忆
 * 1:相同点，都是使用了分而治之的思想，将整个数组的排序分成两部分来处理，如果两个小部分处理好了，那么整个大的部分也就处理好了
 * 2:不同点
 * 2-1:归并是自下而上的处理，也就是我们刚开始只是不停的分解问题，知道不能分解为止，然后再处理问题，具体表现在代码上就是我们刚开始是不停的进行递归，直到终止条件，然后再归并
 * 2-2:快排是自上而下的处理，也就是我们刚开始要选择分界，将小于分界的数据放到左边，大于分界的放到右边，然后再以分界为边界，找到中间分界点，然后再进行递归，具体表现在代码上就是我们刚开始要
 * 分区找到分界点，然后再进行递归，直到终止条件
 **/
public class MySortTest {

    public void quickSort(int[] arr, int n) {
        quickSortInner(arr, 0, n - 1);
    }

    public void quickSortInner(int arr[], int s, int e) {
        if (s >= e) {
            return;
        }
        //首先分区
        int q = partition(arr, s, e);
        //所以在处理的时候，分界点元素不处理，只处理分界元素的左边区域和分界元素的右边区域
        //递归处理分区左边，最后一个元素是q-1
        quickSortInner(arr, s, q - 1);
        //递归处理右边分区，第一个元素是q+1
        quickSortInner(arr, q + 1, e);
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

    public void mergeSort(int[] arr, int n) {
        mergeSortInner(arr, 0, n - 1);
    }

    public void mergeSortInner(int[] arr, int s, int e) {
        if (s >= e) {
            return;
        }
        int q = (s + e) / 2;
        //递归处理左边以q为结束
        mergeSortInner(arr, s, q);
        //递归处理右边，第一个元素是q+1
        mergeSortInner(arr, q + 1, e);
        merge(arr, s, q, e);
    }

    public void merge(int[] arr, int s, int q, int e) {
        //合并两个数组的时候，左边数组第一个元素是s
        int i = s;
        //右边数组第一个元素是q+1
        int j = q + 1;
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
        for (k = 0; k <= e - s; k++) {
            arr[s + k] = tmp[k];
        }
    }
}
