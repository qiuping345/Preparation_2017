package com.pingqiu;

public class BinarySearch {
    /**
     * 
     * @param nums    the integer array, sorted ascending.
     * @param target  the integer to find from the 'nums' array
     * @return  the index of target in 'nums' array, return a minus number otherwise.
     */
    public static int binarySearch(int[] nums, int target) {
        
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int midVal = nums[mid];
            if (midVal < target) {
                low = mid + 1;
            } else if (midVal > target) {
                high = mid - 1;
            } else {
                return mid; // key found
            }
        }
        return -(low + 1);  // key not found.
    }
}
