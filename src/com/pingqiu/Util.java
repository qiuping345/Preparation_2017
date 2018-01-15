package com.pingqiu;

public class Util {

    public static void swap(int[] arr, int i, int j) {
        int k = arr[i];
        arr[i] = arr[j];
        arr[j] = k;
    }
    
    public static boolean isSortedAscend(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isSortedDescend(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i - 1]) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isSorted(int[] arr) {
        return isSortedAscend(arr) || isSortedDescend(arr);
    }
    
    public static void printFirstNElements(int[] arr, int n) {
    	for(int i = 0; i < n && i < arr.length; i++) {
    		System.out.print(" " + arr[i]);
    	}
    	System.out.println();
    }
}

