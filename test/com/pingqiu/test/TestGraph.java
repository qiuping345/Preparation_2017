package com.pingqiu.test;

import com.pingqiu.Graph;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestGraph {
    Graph graphTopoSort;
    Graph graph;
    
    @Before
    public void setup() {
        //* this is the graph of Introduction to Algorithm, $22.4 Figure 22.7
        graphTopoSort = new Graph(9, true);
        graphTopoSort.addEdge(0, 1);
        graphTopoSort.addEdge(1, 2);
        graphTopoSort.addEdge(0, 3);
        graphTopoSort.addEdge(3, 2);
        graphTopoSort.addEdge(5, 6);
        graphTopoSort.addEdge(6, 3);
        graphTopoSort.addEdge(5, 7);
        graphTopoSort.addEdge(8, 7);
        graphTopoSort.addEdge(6, 7);
        
        graph = new Graph(7, false);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 5);
        graph.addEdge(0, 6);
        graph.addEdge(5, 3);
        graph.addEdge(5, 4);
        graph.addEdge(3, 4);
        graph.addEdge(4, 6);
    }
    
    
    @Test
    public void testDFS() {
        List<Integer> dr = graph.dfs(0); 
        for(Integer i : dr) {
            System.out.print(" " + i);
        }        
    }
    
    @Test
    public void testTopologicalSorting() {
        List<Integer> tr = graphTopoSort.topologicalSorting();
        for(Integer i : tr) {
            System.out.print(" " + i);
        }
    }
    
    

}
