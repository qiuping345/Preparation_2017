package com.pingqiu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Kruskal {
    List<int[]> edges;
    int vertices = 0;
    
    public Kruskal(int n) {
        vertices = n;
        edges = new ArrayList<int[]>();
    }
    
    public void addEdge(int p1, int p2, int weight) {
        int[] arr = new int[] {p1, p2, weight};
        edges.add(arr);
    }
    
    public List<int[]> genMST() {
        UnionFindSet uf = new UnionFindSet(vertices);
        List<int[]> result = new ArrayList<int[]>();
        Collections.sort(edges, new Comparator<int[]>() {
            @Override
            public int compare(int[] arg1, int[] arg2) {
                return arg1[2] - arg2[2];
            }
        });
        for(int[] edge : edges) {
            int sId = uf.find(edge[0]);
            int eId = uf.find(edge[1]);
            if(sId != eId) {
                result.add(edge);
                uf.union(edge[0], edge[1]);
            }
        }
        return result;
    }
    

}
