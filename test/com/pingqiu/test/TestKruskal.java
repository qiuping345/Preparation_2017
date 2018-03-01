package com.pingqiu.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.pingqiu.Kruskal;

public class TestKruskal {

    @Test
    public void test() {
        Kruskal inst = new Kruskal(9);
        inst.addEdge(0, 1, 4);
        inst.addEdge(0, 7, 8);
        inst.addEdge(1, 2, 8);
        inst.addEdge(1, 7, 11);
        inst.addEdge(2, 3, 7);
        inst.addEdge(2, 5, 4);
        inst.addEdge(2, 8, 2);
        inst.addEdge(3, 4, 9);
        inst.addEdge(3, 5, 14);
        inst.addEdge(4, 5, 10);
        inst.addEdge(5, 6, 2);
        inst.addEdge(6, 7, 1);
        inst.addEdge(6, 8, 6);
        inst.addEdge(7, 8, 7);
        
        List<int[]> mst = inst.genMST();
        for(int[] edge : mst) {
            System.out.println("start: " + edge[0] + ", end: " + edge[1] + ", weight: " + edge[2]);
        }
    }

}
