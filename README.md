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
    ˫ָ������������������  
  * ǰ��/ͬ��  
    * ���ڣ����ҵľ��飬ѡ��end��Ϊ���ѭ�������ȽϷ���  
    * ����  
  * ����Ҫѡ�����ѭ����left����right  
  * �������飬ÿ������ֱ���һ��ָ��  
  
ͬ�򴰿����˫ָ�����⣺Longest Substring Without Repeating Characters:  
���վ���ģ���������  
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
���Լ��Ƚ�ϲ�����������ұȽ�ϲ���������end��Ϊ���ѭ��������  
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
    