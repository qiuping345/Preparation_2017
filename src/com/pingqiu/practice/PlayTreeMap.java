package com.pingqiu.practice;

import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class PlayTreeMap {

    public static void main(String[] args) {
        TreeMap<String, Integer> map = new TreeMap<String, Integer>();
        map.put("asdf", 7);
        map.put("iio", 8);
        map.put("llsls", 10);
        map.put("qpqp", 12);
        map.put("qqdks", 454);
        map.put("opqiqj", 3434);
        map.put("llkka", 998);

        String key = map.firstKey();
        while (key != null) {
            System.out.println("" + key + ", " + map.get(key));
            key = map.higherKey(key);
        }

        System.out.println("===================================");

        String rkey = map.lastKey();
        while(rkey != null) {
            System.out.println("" + rkey + ", " + map.get(rkey));
            rkey = map.lowerKey(rkey);
        }

        System.out.println("===================================");

        String midKey = "llsls";
        SortedMap<String, Integer> headmap = map.headMap(midKey);
        Set<String> keyset = headmap.keySet();
        Iterator<String> iter = keyset.iterator();
        while(iter.hasNext()) {
            String k = iter.next();
            if(k != null) {
                System.out.println("key: " + k + ", value: " + headmap.get(k));
            }
        }

        System.out.println("===================================");

        SortedMap<String, Integer> tailmap = map.tailMap(midKey);
        Set<String> tailkeyset = tailmap.keySet();
        Iterator<String> tailiter = tailkeyset.iterator();
        while(tailiter.hasNext()) {
            String k = tailiter.next();
            if(k != null) {
                System.out.println("key: " + k + ", value: " + tailmap.get(k));
            }
        }		

        System.out.println("===================================");

        Set<String> nkeyset = map.keySet();
        Iterator<String> nki = nkeyset.iterator();
        while(nki.hasNext()) {
            System.out.println("key : " + nki.next());
        }

        System.out.println("===================================");

        Set<String> nnkeyset = map.navigableKeySet();
        Iterator<String> nnki = nnkeyset.iterator();
        while(nnki.hasNext()) {
            System.out.println("key : " + nnki.next());
        }

        System.out.println("===================================");

        Set<String> dkeyset = map.descendingKeySet();
        Iterator<String> dki = dkeyset.iterator();
        while(dki.hasNext()) {
            System.out.println("key : " + dki.next());
        }

    }

}
