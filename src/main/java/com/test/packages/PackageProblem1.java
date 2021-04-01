package com.test.packages;

/**
 * 思路同上一个程序，只是我们通过这个递归树会发现有很多重复计算的情况，浪费了大量的算力和时间，所以我们使用备忘录模式，对于其中的每一种状态，我们都进行一次记录，如果已经计算过的
 * 我们就不必再重新计算直接返回就行了，而这里的状态就是每个i和cw的组合
 * @Auth 45208
 * @Date 3/7/2021
 **/
public class PackageProblem1 {
    private int maxW = Integer.MIN_VALUE; //放到背包中的物品的最大总重量
    private int [] weights = new int [] {2,2,4,6,3};//每个物品的重量
    private int n = 5;//物品的个数
    private int w = 9;//背包能够承受的最大重量
    private boolean [][] mem= new boolean[5][10];//用来记录递归过程中已经处理过的节点，其实回溯过程，我们可以想象成在一颗树上遍历的过程，只不过在遍历每个节点的过程中，我们可以有多个选择，
    //可以选择处理某些事情，或者不处理某些事情。

    public void f(int i, int cw){
        if(cw==w|| i==n){
            if(cw>maxW){
                maxW = cw;
            }
            return;
        }
        if(mem[i][cw]){
            return;
        }
        mem[i][cw]=true;
        f(i+1,cw);
        if(cw+weights[i]<=w){
            f(i+1,cw+weights[i]);
        }
    }

    public  int getMaxW(){
        return this.maxW;
    }
}
