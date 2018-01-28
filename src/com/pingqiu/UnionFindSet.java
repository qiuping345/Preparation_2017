package com.pingqiu;

public class UnionFindSet {
    private int[] id;    // access to component id (site indexed)
    private int components;   //number of components
    
    public UnionFindSet(int n) {
        components = n;
        id = new int[n];
        for(int i = 0; i < n; i++) {
            id[i] = i;
        }
    }
    
    public int count() {
        return components;
    }
    
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }
    
    public int find(int arg) {
        return slowFind(arg);
        //return quickFind(arg);
    }
    
    public void union(int p, int q) {
        quickUnion(p, q);
        //slowUnion(p, q);
    }
    
    public int slowFind(int arg) {
        while(arg != id[arg]) {
            arg = id[arg];
        }
        return arg;
    }
    
    public void quickUnion(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i != j) {
            id[i] = j;
            components--;
        }
    }
    
    public int quickFind(int arg) {
        return id[arg];
    }
    
    public void slowUnion(int p, int q) {
        int pId = find(p);
        int qId = find(q);
        
        if(pId != qId) {
            for(int i = 0; i < id.length; i++) {
                if(id[i] == pId) {
                    id[i] = qId;
                }
            }
            components--;
        }        
    }
    
}
