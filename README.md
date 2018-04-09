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

Topological Sorting. Course Schedule, Leetcode #210

DFS solution, including:
1. checking cycle
2. get the sorting result.

```
    public int[] findOrder(int numCourses, int[][] prereq) {
        List<Integer>[] adjacency = new List[numCourses];
        for(int i = 0; i < numCourses; i++){
            adjacency[i] = new ArrayList<Integer>();
        }
        for(int[] dep : prereq) {
            adjacency[dep[1]].add(dep[0]);
        }
        
        List<Integer> visitSequence = new ArrayList<Integer>();
        int[] visited = new int[numCourses];  // 0, not visted; 1, being visted; 2, visited.
        
        for(int i = 0; i < numCourses; i++) {
            if(visited[i] == 0 && !dfsTopoUtil(adjacency, i, visited, visitSequence)){
                return new int[0];
            }
        }
        
        int[] result = new int[numCourses];
        for(int i = 0; i < numCourses; i++) {
            result[i] = visitSequence.get(i);
        }
        
        return result;
    }

    public boolean dfsTopoUtil(List<Integer>[] adjacency, int v, int[] visited, List<Integer> visitSequence) {
        visited[v] = 1;
        
        for (Integer adj : adjacency[v]) {
            if (visited[adj] == 1) {  // cycle found
                return false;
            } else if (visited[adj] == 0) {
                if(!dfsTopoUtil(adjacency, adj, visited, visitSequence)){
                    return false;
                }
            }
        }
        
        visited[v] = 2;
        visitSequence.add(0, v);
        return true;
    }
```
BFS, solution:
```
    public boolean canFinish(int vertices, int[][] prereq) {
        int[] indegree = new int[vertices];
        int[] result = new int[vertices];
        List<Integer>[] graph = new List[vertices];
        for (int i = 0; i < vertices; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        initGraph(prereq, indegree, graph);
        return bfs(indegree, graph);
    }

    private void initGraph(int[][] prereq, int[] indegree, List<Integer>[] graph) {
        for(int[] dep : prereq) {
            graph[dep[1]].add(dep[0]);
            indegree[dep[0]]++;
        }
    }
    
    private boolean bfs(int[] indegree, List<Integer>[] graph) {
        int index = 0;
        int[] result = new int[graph.length];
        Queue<Integer> queue = new LinkedList<Integer>();
        
        for(int i = 0; i < indegree.length; i++) {
            if(indegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        while(!queue.isEmpty()) {
            Integer i = queue.poll();
            result[index++] = i;
            for(Integer nei : graph[i]) {
                if(--indegree[nei] == 0) {
                    queue.offer(nei);
                }
            }
        }
        return indegree.length == index;
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
 按照九章模板的做法:

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
    
Backtracking
回溯法在递归基础上，额外需要注意的是它有三个步骤：
1. Choose
2. Explore
3. Unchoose  

这道题是LintCode #152, Combinations， 需要注意的是去重，不要Permute. [1, 2]和[2, 1]是同一个组合。

```
    public List<List<Integer>> combine(int n, int k) {
        // write your code here
        boolean[] visited = new boolean[n];
        List<List<Integer>> result = new ArrayList<>();
        combine(k, 0, visited, new ArrayList<Integer>(), result);
        return result;
    }
    
    private void combine(int k, int index, boolean[] visited, List<Integer> prefix, List<List<Integer>> result) {
        if(index == k) {
            result.add(prefix);
            return;
        }
        //这里要选择比上一个选择的数字要大的，这样来避免重复
        int start = prefix.size() == 0 ? 0 : prefix.get(prefix.size() - 1);
        for(int i = start ; i < visited.length; i++) {
            if(!visited[i]) {
                //1. Choose
                visited[i] = true;
                
                //2. Explore
                List<Integer> nl = new ArrayList<Integer>(prefix);
                nl.add(i+1);
                combine(k, index+1, visited, nl, result);
                
                //3. Unchoose
                visited[i] = false;
            }
        }
    }
```