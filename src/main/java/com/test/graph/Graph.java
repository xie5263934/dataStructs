package com.test.graph;

import java.util.Arrays;
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
        Arrays.fill(pre, -1);
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

    /**
     * 深度遍历
     * 全局变量found用来标识是否找到了目标顶点，用来控制所有的递归方法的返回
     * visited用来标识当前图中的节点是否被访问过，如果当前节点已经被访问过了，就跳过当前节点，否则就将标识设置成true，然后对当前节顶点做处理
     * pre用来存储顶点的前驱节点，也就是某个顶点是从哪个顶点访问过来的，用来存储它的访问路径上的前一个顶点，在初始化的时候需要设置成一个边界之外
     * 的值，这样在后面才好用来做边界判断,
     * 最后递归的调用查找方法，直到找到目标顶点或者是到达边界
     *
     * @param s
     * @param t
     */
    public void dfs(int s, int t) {
        /**
         * 如果目标顶点和起始顶点一样，直接返回
         */
        if (s == t) {
            return;
        }
        /**
         * 将全局的标识设置成false，开始查找操作
         */
        found = false;
        /**
         * visited数组用来存储当前顶点的访问标识，如果是true表示已经被访问过了，就不需要再访问了。
         * 在初始化的时候，需要将起始顶点设置成true，标识起始顶点已经访问过了
         */
        boolean[] visited = new boolean[v];
        visited[s] = true;
        /**
         * pre数组用来存储当前被访问的顶点的前驱顶点，用来记录当前顶点是从哪个顶点访问过来的。
         * 在初始化的时候，将其所有值设置成边界值，这样在后续使用的时候方便做边界判断
         */
        int[] pre = new int[v];
        Arrays.fill(pre, -1);
        /**
         * 从起始顶点到目标顶点的查找
         */
        recdfs(s, t, visited, pre);
        print(pre, s, t);
    }

    private void recdfs(int s, int t, boolean[] visited, int[] pre) {
        /**
         * 如果全局的查找标识是true，表示已经找到了目标顶点，那么不用执行当前方法了，直接返回
         */
        if (found) {
            return;
        }
        /**
         * 如果当前顶点就是目标顶点，那么表示找到了目标顶点了，那么将全局查找标识设置成true，并且返回
         */
        if (s == t) {
            found = true;
            return;
        }
        /**
         * 首先第一步将当前节点的访问标识设置成true，表示当前节点已经被访问过了
         */
        visited[s] = true;
        /**
         * 依次访问当前顶点直接连接的所有顶点，查找目标顶点
         */
        for (int i = 0; i < store[s].size(); i++) {
            /**
             * 依次取出当前顶点的直接连接的顶点，然后判断直接连接的那个顶点有没有被访问过，如果没有被访问过，那么设置直接连接顶点的前驱是当前顶点,
             * 并且以直接连接的那个顶点作为起点继续递归往后往后查找目标顶点
             */
            int q = store[s].get(i);
            if (!visited[q]) {
                pre[q] = s;
                recdfs(q, t, visited, pre);
            }
        }
    }
}
