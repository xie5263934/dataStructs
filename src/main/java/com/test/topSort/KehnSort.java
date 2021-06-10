package com.test.topSort;

import java.util.LinkedList;

/**
 * @Auth 45208
 * @Date 6/11/2021
 * 通过kehn实现top排序，本质还是使用的贪婪算法，首先我们定义，如果b文件依赖a文件，那么a文件必须先于b文件执行，那么在我们的有向无环图中，我们就有两个节点a,b，并且还有一条从a指向b的有向边，并且这个时候
 * 我们称a的入度是0，b的入度是1，那么我们可以先对入度为0的节点进行编译，编译入度为0的节点之后，那么依赖a的节点的入度都要减去1，直到那些节点的入度减为0的时候，那些节点也可以被编译了。
 **/
public class KehnSort {

    int v;
    LinkedList<Integer> adj[];

    public KehnSort(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    /**
     * 表示有两个顶点，并且从s到j有一个有向边
     *
     * @param s
     * @param j
     */
    public void add(int s, int j) {
        adj[s].add(j);
    }

    public void sort(int v) {
        int[] inDegree = new int[v];
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < adj[i].size(); j++) {
                /**
                 * 表示从i到w有一个有向边
                 */
                int w = adj[i].get(j);
                inDegree[w]++;
            }
        }
        /**
         * 首先将入度为0放入到队列中，然后进行访问，访问之后从队列中删除，解决重复访问的问题
         */
        for (int i = 0; i < v; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        /**
         * 使用队列存放入度为0的节点，然后进行遍历
         */
        while (!queue.isEmpty()) {
            /**
             * 首先对入度为0的遍历打印
             */
            int i = queue.remove();
            System.out.println("--->" + i);
            /**
             * 将依赖当前节点的所有节点的入度都减去1
             */
            for (int j = 0; j < adj[i].size(); j++) {
                int w = adj[i].get(j);
                inDegree[w]--;
                /**
                 * 如果某个节点的入度变成0了，那么将其加入到队列中进行访问
                 */
                if (inDegree[w] == 0) {
                    queue.add(w);
                }
            }
        }
    }

    public static void main(String[] args) {
        KehnSort kehnSort = new KehnSort(5);
        kehnSort.add(1, 2);
        kehnSort.add(1, 4);
        kehnSort.add(2, 3);
        kehnSort.sort(5);
    }
}
