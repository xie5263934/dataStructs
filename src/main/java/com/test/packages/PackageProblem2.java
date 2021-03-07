package com.test.packages;

/**
 * @Auth 45208
 * @Date 3/7/2021
 **/
public class PackageProblem2 {
    private int [] weights = new int [] {2,2,4,6,3};//每个物品的重量
    private int n = 5;//物品的个数
    private int w = 9;//背包能够承受的最大重量
    private boolean [][] status= new boolean[5][10];//([n][w+1])我们使用一个二维数组来存储每个物品处理的状态，无论物品是否加入，都需要将物品的状态
    //记录在其中，然后当我们开始装下一个物品的时候，我们就可以通过当前物品的状态，推导出下个物品的状态

    public void handle(){
       status[0][0]=true;
       if(weights[0]<=w){
           status[0][weights[0]]=true;
       }
       for(int i=1; i<n; i++){ //从第二个背包开始，依次处理每个物品的状态,整个过程，处理的就是状态转移的过程
           for(int j =0; j<=w; j++){ //处理当前物品如果不加入背包的状态,当前物品的状态是由上个物品的状态推导出来的，当我们当前物品不加入
               //背包的时候，我们通过上次物品的状态，以及当前物品不加入背包的情况，就能够推导出当前物品不加入背包的状态
               if(status[i-1][j]){
                   status[i][j]=status[i-1][j];
               }
           }
           for(int j = 0; j<=w-weights[i]; j++){ //处理物品加入背包的情况，当前物品的状态是由上个物品的状态推导出来的，当我们的物品加入
               //背包的时候，我们通过上次物品的状态，以及当前物品加入背包的情况，就能够推导出当前物品加入背包的状态，但是注意这里结束的条件是
               //w-weights[i]，原因是，我们决定将当前物品放入背包，那么背包中的物品的重量是不能过超过w-weights[i]的，所以我们只能对上个状态
               //中，背包的重量是w-weights[i]以及以下的状态才能处理，否则就超过背包的重量了。
               if(status[i-1][j]){
                   status[i][j+weights[i]]=status[i-1][j];
               }
           }
       }
       for(int i=w; i>=0; i--){ //我们只需要遍历最后一个物品的状态就行了。
           if(status[n-1][i]){
               System.out.println(i);
           }
       }
    }
}
