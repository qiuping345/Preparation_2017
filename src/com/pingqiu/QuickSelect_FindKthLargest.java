package com.pingqiu;

public class QuickSelect_FindKthLargest {
    public int findKthLargest(int[] nums, int k) {
        return findKthLargest(nums, 0, nums.length - 1, k);
    }
    
    public int findKthLargest(int[] nums, int start, int end, int k) {
        int pivot = partition(nums, start, end);
        int idx = pivot - start + 1;
        if(k == idx) {
            return nums[pivot];
        } else if(k > idx) {
            return findKthLargest(nums, pivot + 1, end, k - idx);
        } else {
            return findKthLargest(nums, start, pivot - 1, k);
        }
    }
    
    private int partition(int[] nums, int start, int end) {
        int s = start - 1;
        for(int i = start; i < end; i++) {
            if(nums[i] >= nums[end]) {
                swap(nums, ++s, i);
            }
        }
        swap(nums, end, ++s);
        return s;
    }
    
    private void swap(int[] nums, int idx1, int idx2) {
        int t = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = t;
    }
}