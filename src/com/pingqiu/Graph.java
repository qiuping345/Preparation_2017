package com.pingqiu;

//Java program to print DFS traversal from a given given graph
import java.io.*;
import java.util.*;

//This class represents a directed graph using adjacency list
//representation
public class Graph {
    private final boolean directed;
    private final int vertices;
    private HashSet<Integer>[] adjacency;

    public Graph(int v, boolean directedGraph) {
        vertices = v;
        directed = directedGraph;
        adjacency = new HashSet[vertices];
        for (int i = 0; i < vertices; ++i) {
            adjacency[i] = new HashSet<Integer>();
        }
    }

    public boolean addEdge(int v, int w) {
        if(v < vertices && w < vertices) {
            adjacency[v].add(w);
            if(!directed) {
                adjacency[w].add(v);
            }
            return true;
        } else {
            return false;
        }
    }
    
    public List<Integer> dfs(int start) {
        List<Integer> visitSequence = new ArrayList<Integer>();
        boolean[] visited = new boolean[vertices];
        dfsUtil(start, visited, visitSequence);
        return visitSequence;
    }
    
    public void dfsUtil(int v, boolean[] visited, List<Integer> visitSequence) {
        if(visited[v]) {
            return;
        }
        
        visited[v] = true;
        visitSequence.add(v);
        
        for (Integer adj : adjacency[v]) {
            if (!visited[adj]) {
                dfsUtil(adj, visited, visitSequence);
            }
        }
    }
    
    public List<Integer> topologicalSorting() {
        List<Integer> visitSequence = new ArrayList<Integer>();
        boolean[] visited = new boolean[vertices];
        for(int i = 0; i < vertices; i++) {
            dfsTopoUtil(i, visited, visitSequence);
        }
        return visitSequence;
    }
    
    public void dfsTopoUtil(int v, boolean[] visited, List<Integer> visitSequence) {
        if(visited[v]) {
            return;
        }
        
        visited[v] = true;
        
        for (Integer adj : adjacency[v]) {
            if (!visited[adj]) {
                dfsTopoUtil(adj, visited, visitSequence);
            }
        }
        
        visitSequence.add(0, v);
    }
}
