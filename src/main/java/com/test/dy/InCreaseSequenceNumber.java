package com.test.dy;

/**
 * @Auth 45208
 * @Date 6/10/2021
 * 我们有一个数字序列包含 n 个不同的数字，如何求出这个序列中的最长递增子序列长度？
 * 比如 2, 9, 3, 6, 5, 1, 7 这样一组数字序列，它的最长递增子序列就是 2, 3, 5, 7，所以最长递增子序列的长度是 4
 * 解题子最优解，当我考察当前某个数字的时候，我只需要在我之前的所有比我小的数字中选择递增序列最大的那个值，然后加1，就得到了到我这里最大的递增序列的值，这样考察到最后，就得到了我所期望的值
 **/
public class InCreaseSequenceNumber {

    public int getMaxSequecen(int[] arr) {
        /**
         * 判断数组的边界情况，当为空，或者没有元素或者一个元素的情况
         */
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return 1;
        }
        /**
         * 状态数组，用来存储考察到原数字数组的第N个元素的时候，对应的最常的递增子序列，原理就是在我之前位置里面所有比我小的数字里面递增序列最大的，然后加上1就是我的最大的递增序列。
         */
        int[] state = new int[arr.length];
        /**
         * 首先初始化第一个数字，默认就是1，我们认为就是包含一个数字的递增序列
         */
        state[0] = 1;
        /**
         * 从前往后依次递增考察每个数字
         */
        for (int i = 1; i < state.length; i++) {
            int max = 0;
            /**
             * 考察第i个数字的时候，需要遍历i之前的所有比他小的数字，在里面找到最大的递增子序列，然后在其基础上+1，就得到了当前数字的最大递增子序列
             */
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && max < state[j]) {
                    max = state[j];
                }
            }
            state[i] = max+1;
        }
        /**
         * 最后查找最大的子序列的值
         */
        int max = 0;
        for (int i = 0; i < state.length; i++) {
            if (max < state[i]) {
                max = state[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        InCreaseSequenceNumber number = new InCreaseSequenceNumber();
        int[] arr = new int[]{2, 9, 3, 6, 5, 1, 7};
        System.out.println(number.getMaxSequecen(arr));
    }
}
