package com.test.packages;

/**
 * @Auth 45208
 * @Date 3/7/2021
 **/
public class PackageProblem {
    private int maxW = Integer.MIN_VALUE; //放到背包中的物品的最大总重量
    private int [] weights = new int [] {2,2,4,6,3};//每个物品的重量
    private int n = 5;//物品的个数
    private int w = 9;//背包能够承受的最大重量

    public void f(int i, int cw){
        if(cw==w|| i==n){
            if(cw>maxW){
                maxW = cw;
            }
            return;
        }
        f(i+1,cw);
        if(cw+weights[i]<=w){
            f(i+1,cw+weights[i]);
        }
    }

    public  int getMaxW(){
        return this.maxW;
    }
}
