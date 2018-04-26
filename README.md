#### Quick Select
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

#### Binary Search

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

#### Binary Tree Inorder travsersal, iterative
```
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        if(root == null) {
            return list;
        }
        TreeNode curr = root;
        while(!stack.empty() || curr != null) {
            if(curr == null) {
                TreeNode n = stack.pop();
                list.add(n.val);
                curr = n.right;
            } else {
                stack.push(curr);
                curr = curr.left;
            }
        }
        return list;
    }
```

#### Binary Search Tree Iterator
```
public class BSTIterator {
    TreeNode curr = null;
    Stack<TreeNode> stack = new Stack<TreeNode>();
    public BSTIterator(TreeNode root) {
        curr = root;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.empty() || curr != null;
    }

    /** @return the next smallest number */
    public int next() {
        while(hasNext()) {
            if(curr == null) {
                TreeNode n = stack.pop();
                curr = n.right;
                return n.val;
            } else {
                stack.push (curr);
                curr = curr.left;
            }
        }
        return Integer.MIN_VALUE;
    }
}
```
#### Topological Sorting
Course Schedule, Leetcode #210

##### DFS solution
Including:
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
##### BFS solution:
BFS的时候，需要注意的是，visited数组是遍历的时候为避免重复访问而引入的，不是任何时候都需要visited数组来确保不会重复访问某一个节点。重要的不是避免重复访问同一个节点，重要的是什么时候把满足条件的节点放入queue里，并且保证那个while(!queue.isEmpty())不会是死循环。
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

 #### Two Pointers
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

##### 3 sum with dup elements but no dup in result  
Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.  
**Note:** The solution set must not contain duplicate triplets.  
**Example:** Given array nums = [-1, 0, 1, 2, -1, -4],  
A solution set is:  
[  
&nbsp;&nbsp;&nbsp;[-1, 0, 1],  
&nbsp;&nbsp;&nbsp;[-1, -1, 2]  
]  

```
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        if(nums == null || nums.length == 0) {
            return result;
        }
        
        Arrays.sort(nums);
        
        for(int i = 0; i < nums.length - 2 && nums[i] <= 0; i++) {
            if(i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            int left = i + 1;
            int right = nums.length - 1;
            
            while(left < right ) {
                int sum = nums[left] + nums[right];
                if(sum == -nums[i]) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    
                    // go to the next different element comparing to the nums[left]
                    left++;
                    while(left < nums.length && nums[left] == nums[left - 1]) {
                        left++;
                    }
                } else if(sum < -nums[i]) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }
```
    
#### Backtracking
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