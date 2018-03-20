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

Binary Search

```
    private int binarySearch(int[] a, int fromIndex, int toIndex, int key) {
        int low = fromIndex;
        int high = toIndex - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = a[mid];

            if (midVal < key) {
                low = mid + 1;
            } else if (midVal > key) {
                high = mid - 1;
            } else {
                return mid; // key found
            }
        }
        return -(low + 1);  // key not found.
    }

```


Two Pointers  
    双指针问题包含几种情况：  
  * 前向/同向  
    * 窗口，以我的经验，选择end作为外层循环变量比较方便  
    * 快慢  
  * 相向，要选好外层循环用left还是right  
  * 两个数组，每个数组分别有一个指针  
  
同向窗口类的双指针问题：Longest Substring Without Repeating Characters:  
按照九章模板的做法：  
```
    public int lengthOfLongestSubstring(String s) {
        int[] count = new int[256];
        int len = 0;
        int end = 0;
        for(int start = 0; start < s.length(); start++) {
            while(end < s.length() && count[s.charAt(end)] == 0) {
                len = Math.max(len, end + 1 - start);
                count[s.charAt(end)]++;
                end++;
            }
            count[s.charAt(start)] --;
        }
        return len;
    }
```
我自己比较喜欢的做法，我比较喜欢这种情况end作为外层循环变量：  
```
    public int lengthOfLongestSubstring(String s) {
        int[] count = new int[256];
        int start = 0;
        int len = Integer.MIN_VALUE;
        for(int end = 0; end < s.length(); end++) {
            char ch = s.charAt(end);
            while(start < end && count[ch] > 0) {
                --count[s.charAt(start++)];
            }
            count[ch]++;
            len = Math.max(len, end + 1 - start);
        }
        return len == Integer.MIN_VALUE ? 0 : len;
    }
```
    