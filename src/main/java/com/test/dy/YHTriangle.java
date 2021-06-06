package com.test.dy;

/**
 * @Auth 45208
 * @Date 6/6/2021
 * 杨辉三角，求最短路径，这个题使用动态规划来解决，并且使用动态规划表来设计，动态规划表其实就是一个二维的数组，我们通过对这个二位数组的层层递进，最终求得我们想要的结果。
 **/
public class YHTriangle {

    private int [][] triangle = new int[][]{
            {5,0,0,0,0},
            {7,8,0,0,0},
            {2,3,4,0,0},
            {4,9,6,1,0},
            {2,7,9,4,5}
    };

    public int minDistance(int[][] triangle, int n){
        int [][] state = new int[n][n];
        state[0][0]=triangle[0][0];
        for(int i = 1; i< n; i++){
            for(int j = 0; j<=i; j++){
                int tmp1 = j-1<0?0:j-1;
                int min1 = state[i-1][tmp1];
                int tmp2 = j-2<0?0:j-2;
                int min2 = state[i-1][tmp2];
                state[i][j] = Math.min(min1,min2)+triangle[i][j];
            }
        }
        int min = Integer.MAX_VALUE;
        for(int i = 0; i< n; i++){
            if(min>state[n-1][i]){
                min = state[n-1][i];
            }
        }
        return min;
    }

    public static void main(String [] args){
        YHTriangle triangle = new YHTriangle();
        System.out.println(triangle.minDistance(triangle.triangle,5));
    }
}
