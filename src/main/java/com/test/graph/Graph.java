package com.test.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 图的存储可以使用两种存储方式
 * 邻接矩阵的方式，底层使用一个二维数组来实现如果顶点i到j有一条边，那么[i][j]就设置为1，邻接矩阵存储的方式直观，简单，并且访问方便，但是比较浪费存储空间，特别是稀疏举证
 * 邻接表的存储方式，非常像使用拉链法解决冲突的散列表的形式，在链表中存储了当前顶点连接的顶点
 *
 * @Auth:jinrun.xie
 * @Date:2021/2/3
 **/
public class Graph {
    private int v;
    private LinkedList<Integer>[] store;
    /**
     * 全局变量，用来标识在递归查找中是否找到了目标顶点，如果找到目标顶点，就将当前值设置成ture，这样不继续下面的递归操作，而是直接返回
     */
    private boolean found;

    public Graph(int v) {
        this.v = v;
        this.store = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            store[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s, int t) {
        store[s].add(t);
        store[t].add(s);
    }

    /**
     * breadth-first-search 广度遍历，就是像地毯式的层层跌进的遍历，遍历完当前这一层之后，再遍历下一层
     *
     * @param s
     * @param t
     */
    public void bfs(int s, int t) {
        /**
         * 如果st是同一个订单，那么直接返回
         */
        if (s == t) {
            return;
        }
        /**
         * visited用来存储当前节点是否被遍历的标识，如果被遍历了就设置成ture，这样再下次如果又遍历到当前节点的时候，就跳过当前节点
         * 在初始化的时候，将当前这个出发顶点设置成已经遍历过了，因为是起点，我们认为起点一开始就是被遍历过的顶点
         */
        boolean[] visited = new boolean[this.v];
        visited[s] = true;
        /**
         * queue用来存储已经被访问过，但是它相连的顶点还没有被访问的顶点，因为我们是按照层级在访问，当我们在访问第K层的时候，我们必须要等到第K层剩余的节点都访问完成了，
         * 我们才能继续访问第K+1层，所以我们必须要把第K层的所有节点存放在里面，之后访问第K+1层的时候，从里面获取到某个第K层的顶点，然后通过这个顶点去获取和访问与它相连的第K+1层的顶点
         * 在初始化的时候，我们将出发顶点设置到队列中，因为是起点，表示下一次要访问与起始顶点直接相连的下一层的顶点
         */
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        /**
         * 用来存储当前节点的上个节点，其实就是用来记录路径
         * 在初始化的时候，将所有节点的上个节点初始化为一个不可能的节点，这样作为边际，方便后续的判断和处理
         */
        int[] pre = new int[this.v];
        for (int i = 0; i < v; i++) {
            pre[i] = -1;
        }
        /**
         * 判断队列中是否还有节点，如果有节点，那么就访问这些节点直接相连的下个层级的节点。
         */
        while (!queue.isEmpty()) {
            /**
             * 取出当前顶点,然后依次遍历当前直接关联的顶点
             * 如果关联的顶点没有被访问过，那么就设置关联顶点的前驱是当前顶点
             * 判断关联顶点是否是目标顶点，如果是就打印出路径，并且直接结束流程
             * 如果不是目标顶点，那么就设置关联被访问过，并且将关联顶点加入到队列中
             */
            int w = queue.poll();
            for (int i = 0; i < store[w].size(); i++) {
                int q = store[w].get(i);
                if (!visited[q]) {
                    pre[q] = w;
                    if (q == t) {
                        print(pre, s, t);
                        return;
                    }
                    visited[q] = true;
                    queue.add(q);
                }
            }
        }
    }

    public void print(int pre[], int s, int t) {
        if (pre[t] != -1 && t != s) {
            print(pre, s, pre[t]);
        }
        System.out.println(t + "->");
    }

    public void dfs(int s, int t) {

    }
}
