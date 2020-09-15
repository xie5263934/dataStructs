package com.test.sort;

/**
 * @Auth 45208
 * @Date 9/15/2020
 * 快速排序
 * 快排也是使用分而治之的思想
 * 首先我们在要排序的数组中从开始下标到结束下标的任意位置找一个元素作为分界元素，将比这个元素小的放到数组的左边，将比这个元素大的放到这个数组的右边，最后将这个分界放到数组的中间位置
 * 然后我们对于分界左边的数组进行同样操作进行排序，对分界右边的数组也进行排序，然后重复上面的动作，直到要排序的数组只有一个元素的时候，我们认为这个数组是有序的，那么对于整体而言也是有序的了
 * 最关键的地方是我们如何选择分界元素和如何对数组分区，也就是将小于分界放到数组左边，大于数组分区放到数组右边
 * 首先我们可以选择要排序数组的最后一个元素作为分界
 * 然后我们可以使用像之前选择排序一样的思想，我们使用一个下标，用来将数组分割成两部份，左边部分是小于分界的已经处理部分，我们要做的就是从剩余的部分中依次去处理未处理的部分，如果未处理部分的数据小于
 * 分界，我们就放到已处理部分的末尾，但是这个地方涉及到数组的数据移动，我们可以使用一个偷懒的方法，就是对已处理部分末尾的数据和当前要处理的那个数据进行交换
 **/
public class QuickSort {

    public void sort(int[] arr, int n) {
        if (n <= 1) {
            return;
        }
        quickSort(arr, 0, n - 1);
    }

    public void quickSort(int[] arr, int s, int e) {
        if (s >= e) {
            return;
        }
        int q = partition(arr, s, e);
        quickSort(arr, s, q - 1);
        quickSort(arr, q + 1, e);
    }

    public int partition(int[] arr, int s, int e) {
        int i = s;
        int j = s;
        int pivot = arr[e];
        for (; j <= e - 1; j++) {
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
}
