package com.pingqiu.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.pingqiu.GraphArrayImpl;

public class TestGraphArrayImpl {

    @Test
    public void test() {
        GraphArrayImpl graph = new GraphArrayImpl(7);
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 3, 30);
        graph.addEdge(1, 2, 50);
        graph.addEdge(2, 4, 10);
        graph.addEdge(3, 4, 60);
        graph.addEdge(6, 5, 25);

        // graph.dijkstra(1);
        // graph.bfs();

        // graph.dfs(0);
        // graph.dfs(5);
        graph.dfs(6);
    }

}
