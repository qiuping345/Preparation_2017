package com.pingqiu.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.pingqiu.CountOfValueInSortedArray;

public class TestCountOfValueInSortedArray {

    @Test
    public void test() {
        CountOfValueInSortedArray inst = new CountOfValueInSortedArray();
        int[] arr = {1,1,1,2,2,2,2,3,3,5};
        assert(0 == inst.getCount(arr, 0));
        assert(3 == inst.getCount(arr, 1));
        assert(4 == inst.getCount(arr, 2));
        assert(2 == inst.getCount(arr, 3));
        assert(0 == inst.getCount(arr, 4));
        assert(1 == inst.getCount(arr, 5));
        assert(0 == inst.getCount(arr, 7));

    
    }

}
