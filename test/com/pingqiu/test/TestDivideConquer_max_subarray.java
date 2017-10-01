package com.pingqiu.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.pingqiu.DivideConquer_max_subarray;

public class TestDivideConquer_max_subarray {

    @Test
    public void test() {
        DivideConquer_max_subarray inst = new DivideConquer_max_subarray();
        int[] test = new int[] {13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};
        int result = inst.maxSubArray(test, 0, test.length - 1);
        assert(result == 43);
    }

}
