package com.test.graph;

import org.junit.Test;

/**
 * @Auth:jinrun.xie
 * @Date:2021/2/3
 **/
public class GraphTest {

    @Test
    public void testBfs() {
        Graph graph = new Graph(8);
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);
        graph.addEdge(5, 7);
        graph.addEdge(6, 7);
        graph.bfs(0,6);
    }
}
