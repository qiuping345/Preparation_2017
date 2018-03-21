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
    