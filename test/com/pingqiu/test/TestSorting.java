package com.pingqiu.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.pingqiu.PrintUtil;
import com.pingqiu.Sorting;
import com.pingqiu.Util;

public class TestSorting {
    private int[] arrNoDup = new int[] { 3, 5, 1, 2, 4, 7, 11, 9, 0 };
    private int[] arrWithDup =  new int[] { 3, 5, 1, 2, 4, 7, 11, 97, 9, 0, 2, 7, 89 };

    @Test
    public void testInsertionSort_no_duplication() {
        Sorting sorter = new Sorting();
        int[] test = new int[arrNoDup.length];
        System.arraycopy(test, 0, arrNoDup, 0, arrNoDup.length);
        sorter.insertionSort(test);
        assert(Util.isSorted(test));
    }
    
    @Test
    public void testInsertionSort_with_duplication() {
        Sorting sorter = new Sorting();
        int[] test = new int[arrWithDup.length];
        System.arraycopy(test, 0, arrWithDup, 0, arrWithDup.length);
        sorter.insertionSort(test);
        assert(Util.isSorted(test));
    }
    
    @Test
    public void testBubbleSort_no_duplication() {
        Sorting sorter = new Sorting();
        int[] test = new int[arrNoDup.length];
        System.arraycopy(test, 0, arrNoDup, 0, arrNoDup.length);
        sorter.bubbleSort(test);
        assert(Util.isSorted(test));
    }
    
    @Test
    public void testBubbleSort_with_duplication() {
        Sorting sorter = new Sorting();
        int[] test = new int[arrWithDup.length];
        System.arraycopy(test, 0, arrWithDup, 0, arrWithDup.length);
        sorter.bubbleSort(test);
        assert(Util.isSorted(test));
    }

    @Test
    public void testMergeSort_no_duplication() {
        Sorting sorter = new Sorting();
        int[] test = new int[arrNoDup.length];
        System.arraycopy(test, 0, arrNoDup, 0, arrNoDup.length);
        sorter.mergeSort(test);
        assert(Util.isSorted(test));
    }
    
    @Test
    public void testMergeSort_with_duplication() {
        Sorting sorter = new Sorting();
        int[] test = new int[arrWithDup.length];
        System.arraycopy(test, 0, arrWithDup, 0, arrWithDup.length);
        sorter.mergeSort(test);
        assert(Util.isSorted(test));
    }

    @Test
    public void testQuickSort_no_duplication() {
        Sorting sorter = new Sorting();
        int[] test = new int[arrNoDup.length];
        System.arraycopy(test, 0, arrNoDup, 0, arrNoDup.length);
        sorter.quicksort(test);
        assert(Util.isSorted(test));
    }
    
    @Test
    public void testQuickSort_with_duplication() {
        Sorting sorter = new Sorting();
        int[] test = new int[arrWithDup.length];
        System.arraycopy(test, 0, arrWithDup, 0, arrWithDup.length);
        sorter.quicksort(test);
        assert(Util.isSorted(test));
    }

}
