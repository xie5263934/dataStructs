package com.test.packages;

/**
 * @Auth 45208
 * @Date 3/7/2021
 **/
public class PackageProblem3 {
    private int [] weights = new int [] {2,2,4,6,3};//每个物品的重量
    private int n = 5;//物品的个数
    private int w = 9;//背包能够承受的最大重量
    private boolean [] status= new boolean[10];//这里，我们使用一个一维数组来记录当前阶段的状态，因为在推导下个阶段状态的时候，只需要当前阶段的状态就够了，并不需要再之前阶段
    //的状态，并且在推导出下个阶段的状态之后，当前阶段的状态就没用了，所以被覆盖了也无所谓，我们最终只需要保留最后一个阶段的状态就行了。

    public void handle(){
       status[0]=true;
       if(weights[0]<=w){
           status[weights[0]]=true;
       }
       for(int i=1; i<n; i++){ //从第二个物品开始，依次处理每个阶段的状态
           for(int j = w-weights[i]; j>=0;j--){ //这里就是推导当前阶段的状态，但是这里j的开始条件是w-weights[i]其实原因就是因为我们将当前物品加入背包的话，那么之前阶段中，
               //我们只能选择那些状态中背包的重量在w-weights[i]以及以下的状态中来推导，否则就会导致超过了背包的重量，而且这里从大到小是因为，我们这里数组中记录的是上个阶段的状态，
               //我们需要根据上个阶段的状态来推导当前阶段的状态，所以我们从后往前处理，当我们处理后面的过程中，前面的数据我们还没有处理过，就是上个阶段的数据。否则的话，我们从前往后处理
               //的过程中，因为当我们处理后面的状态的过程中，会使用到前面的数据，但是前面的数据已经是被处理过了的，已经是存的当前阶段的状态，所以导致后面的状态结算就不正确了。重复计算了
               //当前物品的重量。
               if(status[j]){
                   status[j+weights[i]]=true;
               }
           }
       }
       for(int i=w; i>=0; i--){ //我们只需要遍历最后一个物品的状态就行了。
           if(status[i]){
               System.out.println(i);
           }
       }
    }
}
