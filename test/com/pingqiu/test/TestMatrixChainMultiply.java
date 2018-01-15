package com.pingqiu.test;

import static org.junit.Assert.*;

import org.junit.Test;
import com.pingqiu.MatrixChainMultiply;

public class TestMatrixChainMultiply {

    @Test
    public void testMinCalc() {
        MatrixChainMultiply inst = new MatrixChainMultiply();
        assertEquals(7500, inst.minCalc(new int[] {10, 100, 5, 50}));
        assertEquals(15125, inst.minCalc(new int[] {30, 35, 15, 5, 10, 20, 25}));
        assertEquals(7125, inst.minCalc(new int[] {35, 15, 5, 10, 20}));
        assertEquals(2010, inst.minCalc(new int[] {5, 10, 3, 12, 5, 50, 6}));    
    }

}
