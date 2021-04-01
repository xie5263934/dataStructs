package com.test.packages;

/**
 * 这种使用回溯思想的算法，是一个指数级的算法，其实也是一种暴力穷举的算法，我们把所有的情况都列出来，然后用一个变量去记录我们得到的极值，
 * 如果当前遍历过程的极值大于了已经记录的那个值，那么就把极值更新成当前遍历的这个值，
 * 其实我们可以在脑海中把整个过程想象成一颗递归树，那么对于处理每个节点也就是处理每个物品的时候我们都会有两个选择，也就是两个分叉，
 * 要么把物品放进去，要么把物品不放进去。
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
