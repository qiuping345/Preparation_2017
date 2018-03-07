Quick Select
```
    /**
     * Quick select
     * 
     * Best case, O(N)
     * Average case, O(N)
     * Worst case, O(N^2)
	 *
	 * Find K-th smallest element in an unsorted array.
     */
    public int kthSmallest(int k, int[] nums) {
        if(nums == null || k > nums.length){
            return Integer.MIN_VALUE;
        }
        k--;
        int begin = 0;
        int end = nums.length - 1;
        while(begin < end) {
            int p = partition(nums, begin, end);
            if (p < k) {
                begin = p + 1;
            } else if (p > k){
                end = p - 1;
            } else {
                return nums[k];
            }
        }
        
        return nums[k];
    }
    //* partition in quick sort or quick select.
    public int partition(int[] arr, int start, int end) {
        int pivot = arr[end];
        int s = start - 1;
        for(int i = start; i < end; i++) {
            if(arr[i] <= pivot) {
                swap(arr, ++s, i);
            }
        }
        swap(arr, ++s, end);
        return s;
    } 
```