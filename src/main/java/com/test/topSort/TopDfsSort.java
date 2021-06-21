package com.test.topSort;

import java.util.LinkedList;

/**
 * @Auth 45208
 * @Date 6/21/2021
 * 使用深度遍历的方式，实现topo排序，其中的思路是，我们有一个正常的图的邻接表，其中s->t，表示s要先于t执行，因为s的入度为0，所以t依赖于s
 * 那么我们使用原来的邻接表，构建一个逆邻接表，其中t->s，表示t依赖于s，那么需要s先编译，有了逆邻接表，那么我们可以使用深度遍历的方式,
 * 先遍历我们依赖的节点
 **/
public class TopDfsSort {
    int v;
    LinkedList<Integer> adj[];

    public TopDfsSort(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void add(int i, int j) {
        adj[i].add(j);
    }

    public void topoSortByDFS() {  // 先构建逆邻接表，边s->t表示，s依赖于t，t先于s
        LinkedList<Integer> inverseAdj[] = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            // 申请空间
            inverseAdj[i] = new LinkedList<>();
        }
        for (int i = 0; i < v; ++i) {
            // 通过邻接表生成逆邻接表
            for (int j = 0; j < adj[i].size(); ++j) {
                int w = adj[i].get(j); // i->w
                inverseAdj[w].add(i); // w->i
            }
        }
        boolean[] visited = new boolean[v];
        for (int i = 0; i < v; ++i) {
            // 深度优先遍历图
            if (visited[i] == false) {
                visited[i] = true;
                dfs(i, inverseAdj, visited);
            }
        }
    }

    private void dfs(
            int vertex, LinkedList<Integer> inverseAdj[], boolean[] visited) {
        for (int i = 0; i < inverseAdj[vertex].size(); ++i) {
            int w = inverseAdj[vertex].get(i);
            if (visited[w] == true) continue;
            visited[w] = true;
            dfs(w, inverseAdj, visited);
        }
        // 先把vertex这个顶点可达的所有顶点都打印出来之后，再打印它自己
        System.out.print("->" + vertex);
    }

    public static void main(String[] args) {
        TopDfsSort topDfsSort = new TopDfsSort(4);
        topDfsSort.add(0, 1);
        topDfsSort.add(0, 3);
        topDfsSort.add(3, 2);
        topDfsSort.topoSortByDFS();
    }

}
