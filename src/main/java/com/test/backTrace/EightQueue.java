package com.test.backTrace;

import java.util.Arrays;

/**
 * 8皇后问题，使用经典的回溯思想，其实回溯就是暴力枚举，将所有可能的情况全部遍历一遍。
 * 对于这种回溯，其实基本的框架就是对当前这一层的处理，每一种情况都往下递归处理一次，直到处理完所有的情况，所以代码的框架，其实就是
 * search(k){
 * k==结束条件 return
 * for(){
 * if(true){
 * search(k+1)
 * }
 * }
 * }
 *
 * @Auth 45208
 * @Date 3/6/2021
 **/
public class EightQueue {
    private int[] places = new int[8];
    private int t = 0;

    public void search(int k) {
        if (k == 8) {
            print();
            return;
        }
        for (int i = 0; i <= 7; i++) {
            if (isOk(k, i)) {
                places[k] = i;
                search(k+1);
            }
        }
    }

    private boolean isOk(int row, int column) {
        int leftUp = column - 1;
        int rightUp = column + 1;
        for (int i = row - 1; i >= 0; i--) {
            if (places[i] == column) {
                return false;
            }
            if (leftUp >= 0 && places[i] == leftUp) {
                return false;
            }
            if (rightUp <= 7 && places[i] == rightUp) {
                return false;
            }
            leftUp--;
            rightUp++;
        }
        return true;
    }

    private void print() {
        System.out.println("第" + (t++) + "种解法");
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                if (places[i] == j) {
                    System.out.print("Q");
                } else {
                    System.out.print("*");
                }
            }
            System.out.println();
        }
    }

}
