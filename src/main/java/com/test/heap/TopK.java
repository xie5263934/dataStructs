package com.test.heap;

/**
 * top k的问题，假定有一大批的无序数据，假设有上亿，如何通过一次便利将其中前K大的数据查找出来
 * 首先我们使用前K个数据构建一个含有K个元素的小顶堆(堆顶的数据是所有元素中最小的一个数据)，
 * 然后我们从k+1个数开始，每次都和小顶堆的对顶元素进行比较，如果小于对顶，那么肯定也小于小顶堆中的任何一个元素，就直接跳过
 * 如果大于小顶堆，那么就把当前对顶元素替换成当前元素，这样小顶堆中始终保存了目前为止所有数据中最大的K个元素。遍历数据直到最后
 * 最后输出小顶堆的所有元素，就是这批数据中最大的K个数据。
 * 求解topk的思路，我们先假设前K个元素就是所有数据中最大的前K个元素，并且构建一个小顶堆，也就是这批最大的K个数中最小的那个数在堆顶，如果
 * 比较的元素比对顶还小，那么肯定不符合前K个元素，如果比堆顶元素大，那么久替换掉堆顶元素，当前元素是前K个元素中的一个，然后修复堆，将这前K个元素中
 * 最小的元素移动到堆定，然后重复上面的操作，最后堆中肯定就是前K个最大的元素
 *
 * @Auth:jinrun.xie
 * @Date:2021/2/1
 **/
public class TopK {
    private int arr[];
    private int count;
    private int size;

    public TopK(int capacity) {
        this.arr = new int[capacity + 1];
        this.count = 0;
        this.size = capacity;
    }

    public void add(int number) {
        if (count < size) {
            count++;
            arr[count] = number;
            heapFiyLow(arr, count);
        } else if (number > arr[1]) {
            arr[1] = number;
            highHeap(arr, 1, count);
        }
    }

    private void highHeap(int arr[], int i, int n) {
        while (true) {
            int maxPos = i;
            if (i * 2 <= n && arr[i] > arr[i * 2]) {
                maxPos = i * 2;
            }
            if (i * 2 + 1 <= n && arr[maxPos] > arr[i * 2 + 1]) {
                maxPos = i * 2 + 1;
            }
            if (maxPos == i) {
                break;
            }
            swap(arr, i, maxPos);
            i = maxPos;
        }
    }

    private void heapFiyLow(int arr[], int i) {
        while (i / 2 >= 1 && arr[i] < arr[i / 2]) {
            swap(arr, i, i / 2);
            i = i / 2;
        }
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public void print() {
        for (int i = 1; i <= size; i++) {
            System.out.println(arr[i]);
        }
    }

}
