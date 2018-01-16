package com.pingqiu;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class GraphArrayImpl {

    int numVertex;
    int[][] edges;

    public GraphArrayImpl(int numVertex) {
        this.numVertex = numVertex;
        edges = new int[numVertex][numVertex];
        for (int i = 0; i < numVertex; i++) {
            for (int j = 0; j < numVertex; j++) {
                edges[i][j] = i == j ? 0 : Integer.MAX_VALUE;
            }
        }
    }

    public void addEdge(int start, int end, int weight) {
        if (start < 0 || start >= numVertex || end < 0 || end >= numVertex) {
            return;
        }

        edges[start][end] = weight;
    }

    public static class Vertex {
        public int id;
        public int depth;
        public int parent;
        public int startTime;
        public int endTime;

        public Vertex(int id, int d, int p) {
            this.id = id;
            depth = d;
            parent = p;
        }
    }

    public void bfs() {
        HashMap<Integer, Vertex> map = new HashMap<Integer, Vertex>();
        HashSet<Integer> visited = new HashSet<Integer>();
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(0);
        visited.add(0);
        map.put(0, new Vertex(0, 0, -1));
        while (!queue.isEmpty()) {
            Integer u = queue.remove();
            for (int adj = 0; adj < numVertex; adj++) {
                if (adj != u && edges[u][adj] != Integer.MAX_VALUE && !visited.contains(adj)) {
                    visited.add(adj);
                    queue.add(adj);
                    map.put(adj, new Vertex(adj, map.get(u).depth + 1, u));
                }
            }
            // do some summarization on this vertex 'u'
        }

        for (Vertex v : map.values()) {
            System.out.println("id: " + v.id + ", depth : " + v.depth + ", parent: " + v.parent);
        }
    }

    private int time = 0;

    public void dfs(int i) {
        time = 0;
        HashMap<Integer, Vertex> map = new HashMap<Integer, Vertex>();
        HashSet<Integer> visited = new HashSet<Integer>();

        dfsVisit(i, 0, -1, visited, map);

        for (Vertex v : map.values()) {
            System.out.println("id: " + v.id + ", depth : " + v.depth + ", parent: " + v.parent + ", start: "
                    + v.startTime + ", end: " + v.endTime);
        }
    }

    public void dfsVisit(int v, int depth, int parent, HashSet<Integer> visited, HashMap<Integer, Vertex> map) {
        visited.add(v);
        Vertex vertex = map.getOrDefault(v, new Vertex(v, depth, parent));
        vertex.startTime = ++time;
        for (int adj = 0; adj < numVertex; adj++) {
            if (adj != v && edges[v][adj] != Integer.MAX_VALUE && !visited.contains(adj)) {
                dfsVisit(adj, depth + 1, v, visited, map);
            }
        }
        vertex.endTime = ++time;
        map.put(v, vertex);
    }

    public void dijkstra(int v) {
        int[] path = new int[numVertex];
        int[] distance = new int[numVertex];
        System.arraycopy(edges[v], 0, distance, 0, numVertex);
        Set<Integer> chosen = new HashSet<Integer>();
        chosen.add(new Integer(v));
        for (int i = 0; i < numVertex; i++) {
            if (i != v && distance[i] != Integer.MAX_VALUE) {
                path[i] = v;
            }
        }

        for (int i = 0; i < numVertex; i++) {
            int min = Integer.MAX_VALUE;
            int temp = v;
            for (int j = 0; j < numVertex; j++) {
                if (!chosen.contains(new Integer(j)) && distance[j] < min) {
                    temp = j;
                    min = distance[j];
                }
            }

            chosen.add(new Integer(temp)); // newly added vertex.

            System.out.println("seq : " + i + ", chosen add: " + temp);
            printArray(path);
            printArray(distance);

            for (int j = 0; j < numVertex; j++) {
                if (!chosen.contains(new Integer(j)) && edges[temp][j] < Integer.MAX_VALUE
                        && distance[temp] + edges[temp][j] < distance[j]) { // if the newly added node made some
                                                                            // difference?
                    distance[j] = distance[temp] + edges[temp][j];
                    path[j] = temp;
                }
            }
        }

        printArray(path);
        printArray(distance);

    }

    public void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print("  " + array[i]);
        }
        System.out.println("");
    }

    public static void main(String[] args) {
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