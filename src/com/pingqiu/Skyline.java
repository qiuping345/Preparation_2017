package com.pingqiu;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

class Skyline {
	public static void main(String[] args) {
		Skyline inst = new Skyline();
		int[][] positions = new int[][] {
			new int[] {1,2},
			new int[] {2,3},
			new int[] {6,1}
		};
		List<Integer> result = inst.fallingSquares(positions);
		for(Integer i : result) {
			System.out.print("  " + i);
		}
	}
	
    public static class Metadata {
        public int length, height;
    }
    TreeMap<Integer, Metadata> map = new TreeMap<Integer, Metadata>();
    
    public List<Integer> fallingSquares(int[][] positions) {
        
        for(int[] sq: positions) {
            int left = sq[0];
            int length = sq[1];
            
            Map<Integer, Metadata> submap = new HashMap<Integer, Metadata> (map.subMap(left, true, left + length, false));
            Integer floorKey = map.floorKey(left);
            if(floorKey != null) {
                Metadata val = map.get(floorKey);
                if(floorKey + val.length > left) {
                    submap.put(floorKey, val);
                }
            }
            
            int maxHeight = 0;
            Set<Integer> keyset = submap.keySet();
            Iterator<Integer> iter = keyset.iterator();
            while (iter.hasNext()) {
                Integer key = iter.next();
                Metadata meta = map.get(key);
                maxHeight = Math.max(maxHeight, meta.height);
                
                if(key >= left && key + meta.length <= left + length) {
                    map.remove(key);
                } else if (left > key && key + meta.length > left) {
                    Metadata m = map.get(key);
                    m.length = left - key;
                } else if (left + length > key && left + length < key + meta.length) {
                    Metadata m = map.get(key);
                    m.length = key + meta.length - left - length;
                    map.remove(key);
                    map.put(left + length, m);
                }
            }
            
            Metadata newm = new Metadata();
            newm.length = length;
            newm.height = maxHeight + length;
            map.put(left, newm);
        }
        
        List<Integer> result = new LinkedList<Integer>();
        for(int[] sq: positions) {
            Integer floor = map.floorKey(sq[0]);
            if (floor == null) {
                result.add(0);
            } else {
                Metadata meta = map.get(floor);
                if(floor + meta.length >= sq[0]) {
                    result.add(meta.height);
                } else {
                    result.add(0);
                }
            }
        }
        return result;
    }
}