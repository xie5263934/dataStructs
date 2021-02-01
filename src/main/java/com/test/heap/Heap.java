package com.test.heap;

/**
 * 堆排序的实现,这里我们以实现一个大顶堆为例子
 * 一般默认堆的底层都是使用数组实现
 * 堆排序的实现，首先需要建堆，然后排序
 * 建堆的过程有两种方式，一种是自顶向下的方式，一种是自底向上的方式
 * 第一种
 * 依次从前往后处理数组中的数据，首先假定堆中只有一个数据，然后从数组的第二个数据开始假定往堆中插入数据，然后自底向上堆化,自底向上的过程就是比较当前的值和父节点的值大小，如果大于父节点就交换两个
 * 节点的值，然后把父节点设置成当前节点，重复上面的动作，直到当前节点为根节点为止，堆化完成之后，继续处理数组中下一个数据，然后重复前面的堆化过程，直到数组最后一个数据结束，一共需要处理N-1个数据
 * 第二种
 * 依次从后往前处理数组中的数据，但是对于叶子节点我们不需要处理，所以处理的起点是N/2倒数第一个非叶子节点，然后自顶向下堆化，自顶向下的过程就是比较当前节点和两个孩子节点的值大小，如果父节点最大，
 * 那就结束当前流程，否则就找到两个孩子节点中最大的那个，和父节点进行交换，然后将最大的那个孩子设置成当前节点，重复上面的动作，直到叶子节点为止。堆化完成之后，继续处理数组中倒数往前的下一个数据，
 * 然后重复前面的堆化过程，直到数组倒数最后一个数据结束，一共需要处理N/2个数据。
 * 建堆的过程建议使用第二种自顶向下的过程，性能好一些
 * 排序的过程
 * 首先我们要确认一点，堆顶的数据是所有数据中最大的数据，然后每次将堆顶的数据和数组当前逻辑最后一位进行交换，逻辑最后一位变成了新的堆定，然后进行自顶向下的堆化，堆的大小减一，所以数组的逻辑最后一位
 * 也往前移动一位，重复上上面的动作，直到堆中只剩一个数据为止，这样数组中的数据就是有序的了，因为我们每次都是取得堆定中得数据，也就是每次都是取得剩余得数据中最大得。
 *
 * @Auth:jinrun.xie
 * @Date:2021/2/1
 **/
public class Heap {
    /**
     * 用来存储数据的底层数组
     */
    private int arr[];
    /**
     * 数组的容量，因为我们为了处理方便，直接从下标1开始存储数据，对应第一个数据
     */
    private int n;
    /**
     * 用来标识当前当前数组中已经存入的数据
     */
    private int count;

    public Heap(int capacity) {
        this.n = capacity + 1;
        arr = new int[n];
        this.count = 0;
    }

    public void addItem(int number) {
        this.count++;
        arr[count] = number;
    }

    /**
     * 自底向上的修复堆
     * 当当前节点的父节点存在，并且当前节点大于父节点的值，交换当前节点和父节点的值，并且将父节点设置成当前节点，然后重复上面的操作，直到其中一个条件不满足退出循环
     *
     * @param arr
     * @param i
     */
    private void fromLowToHigh(int arr[], int i) {
        while (i / 2 >= 1 && arr[i] > arr[i / 2]) {
            swap(arr, i / 2, i);
            i = i / 2;
        }
    }

    /**
     * 自顶向下修复堆
     * 如果当前节点的孩子节点存在，并且至少有一个孩子节点大于当前节点，那么就找到两个孩子节点中最大的那个，然后把当前节点的值和最大值的那个孩子节点交换，将当前节点设置成值最大的
     * 那个孩子节点，然后重复上面的操作，直到其中一个条件不满足退出循环。
     *
     * @param arr
     * @param i
     */
    private void fromHighToLow(int arr[], int i, int n) {
        while (true) {
            /**
             * maxPos就是一个临时变量，用来记录当前节点，和两个孩子节点中最大的那个数的下标，如果maxPos最后不等于i，那么就把当前节点和maxPos指向的孩子节点进行交换，否则就退出循环
             */
            int maxPos = i;
            if (i * 2 <= n && arr[i] < arr[i * 2]) {
                maxPos = i * 2;
            }
            if (i * 2 + 1 <= n && arr[maxPos] < arr[i * 2 + 1]) {
                maxPos = i * 2 + 1;
            }
            if (maxPos == i) {
                break;
            }
            swap(arr, i, maxPos);
            i = maxPos;
        }
    }

    /**
     * 自顶向下堆化操作,
     * 从N/2非叶子节点从后往前开始处理，处理N/2个数据
     */
    public void heapFiyHigh() {
        for (int i = count / 2; i >= 1; i--) {
            fromHighToLow(arr, i, count);
        }
    }

    /**
     * 自底向上堆化操作
     * 从第二个元素开始从前往后处理，处理N-1个数据
     */
    public void heapFiyLow() {
        for (int i = 2; i <= count; i++) {
            fromLowToHigh(arr, i);
        }
    }

    private void swap(int arr[], int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 排序的过程，首先交换堆顶和数组逻辑上最后一个元素，堆大小减一，逻辑上数组最后一个元素往前挪一位，然后自顶向下修复堆，然后重复重复上面过程，又交换新的堆的对顶和数组逻辑上最后一个元素，
     * 修复堆，直到堆中只剩下最后一个元素停止，这样操作之后，整个数组中的数据就是有序的。
     */
    public void sort() {
        for (int i = count; i >= 1; i--) {
            swap(arr, 1, i);
            fromHighToLow(arr, 1, i - 1);
        }
    }

    public void print() {
        for (int i = 1; i <= count; i++) {
            System.out.println(arr[i]);
        }
        System.out.println(";");
    }


}
