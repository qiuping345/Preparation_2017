package com.pingqiu;

import java.util.HashMap;
import java.util.Random;
import java.util.TreeMap;

public class ReservoirSamplingTest {

    public static void main(String[] args) {
        ReservoirSamplingTest inst = new ReservoirSamplingTest();
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        int div = 100;
        for (int i : inst.sampling(100)) {
            System.out.println(i / div);
            map.put(i/div, 1+map.getOrDefault(i/div, 0));
        }
        
        for(Integer key : map.keySet()) {
            System.out.println("" + key + " : " + map.get(key));
        }
    }

    private int[] pool; //all data
    private final int N = 100000; //data size
    private Random random = new Random();


    public ReservoirSamplingTest() {
        pool = new int[N];
        for(int i = 0; i < pool.length; i++) {
            pool[i] = i;
        }
    }

    public int[] sampling(int K) {
        int[] result = new int[K];
        for(int i = 0; i < K; i++) {
            result[i] = pool[i];
        }
        for(int i = K + 1; i < pool.length; i++) {
            int idx = random.nextInt(i + 1);
            if(idx < K) {
                result[idx] = pool[i];
            }
        }
        return result;
    }


}