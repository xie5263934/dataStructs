package com.test.dy;

/**
 * @Auth 45208
 * @Date 6/6/2021
 * 回溯算法实现求硬币问题，假如我们有三种面值的硬币，分别是1元，3元，5元，现在求我要凑够9元钱，求最少个硬币数量能够凑够9元。
 **/
public class BackTraceCoin {
    private int [] coins = new int[]{1,3,5};
    private int min = Integer.MAX_VALUE;

    public void getLeasetCoin(int num,int [] conis, int total,int sum){
        if(sum>total){
            return;
        }else if(sum==total){
            if(num < min){
                min=num;
            }
            return;
        }
        for(int i = 0; i< conis.length ; i++){
            getLeasetCoin(num+1,conis,total,sum+conis[i]);
        }
    }

    public static void main(String [] args){
        BackTraceCoin backTraceCoin = new BackTraceCoin();
        backTraceCoin.getLeasetCoin(0,backTraceCoin.coins,9,0);
        System.out.println(backTraceCoin.min);
    }
}
