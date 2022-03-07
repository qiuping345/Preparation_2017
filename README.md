#### Complexity
##### 搜索的时间复杂度：O(答案总数 * 构造每个答案的时间)
举例：Subsets问题，求所有的子集。子集个数一共 2^n，每个集合的平均长度是 O(n) 的，所以时间复杂度为 O(n * 2^n)，同理 Permutations 问题的时间复杂度为：O(n * n!)

##### 动态规划的时间复杂度：O(状态总数 * 计算每个状态的时间复杂度)
举例：triangle，数字三角形的最短路径，状态总数约 O(n^2) 个，计算每个状态的时间复杂度为 O(1)——就是求一下 min。所以总的时间复杂度为 O(n^2)

##### 用分治法解决二叉树问题的时间复杂度：O(二叉树节点个数 * 每个节点的计算时间)
举例：二叉树最大深度。二叉树节点个数为 N，每个节点上的计算时间为 O(1)。总的时间复杂度为 O(N)

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
    private int partition(int[]arr, int start, int end) {
        int pivot = arr[end];
        int index = start;
        for(int i = start; i < end; i++) {
            if(arr[i] < pivot) {
                swap(arr, i, index++);
            }
        }
        swap(arr, end, index);
        return index;
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


Leetcode #33. Search in Rotated Sorted Array 
```
class Solution {
    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while(low <= high) {
            int mid = low + (high - low) / 2;
            if(nums[mid] == target) {
                return mid;
            } else if(nums[mid] < nums[high]) {
                if(target > nums[mid] && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            } else {
                if(target >= nums[low] && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }
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

#### BFS & DFS
'Clone Graph' is a very good question, it could be solved in both BFS & DFS.

##### BFS solution
```
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null) {
            return null;
        }
        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        map.put(node, new UndirectedGraphNode(node.label));
        
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        queue.offer(node);
        
        while(!queue.isEmpty()) {
            UndirectedGraphNode curr = queue.poll();
            UndirectedGraphNode clone = map.get(curr);
            for(UndirectedGraphNode nei : curr.neighbors) {
                if(!map.containsKey(nei)) {
                    map.put(nei, new UndirectedGraphNode(nei.label));
                    queue.offer(nei);
                }
                clone.neighbors.add(map.get(nei));
            }
        }
        return map.get(node);
    }
}
```
##### DFS solution
```
public class Solution {
    HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
    
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null) {
            return null;
        }
        if(map.containsKey(node)) {
            return map.get(node);
        } 

        UndirectedGraphNode cloneNode = new UndirectedGraphNode(node.label);
        map.put(node, cloneNode);
        
        for(UndirectedGraphNode nei : node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(nei));
        }   
        return cloneNode;
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
class Solution {
    private enum Status {
        NOT_VISITED,
        VISIT_IN_PROGRESS,
        VISITED;
    }
    public int[] findOrder(int num, int[][] prereq) {
        List<Integer>[] graph = new List[num];
        Status[] visited = new Status[num];
        List<Integer> result = new LinkedList<Integer>();
        
        for(int i = 0; i < num; i++) {
            graph[i] = new ArrayList<Integer>();
            visited[i] = Status.NOT_VISITED;
        }
        for(int[] edge : prereq) {
            graph[edge[1]].add(edge[0]);
        }
        
        for(int i = 0; i < num; i++) {
            if(visited[i] == Status.NOT_VISITED && !dfs(graph, visited, i, result)) {
                return new int[0];
            }
        }
        if(result.size() != num) {
            return new int[0];
        }
        return result.stream().mapToInt(i -> i).toArray();
    }
    
    private boolean dfs(List<Integer>[] graph, Status[] visited, int index, List<Integer> result) {
        visited[index] = Status.VISIT_IN_PROGRESS;
        for(Integer req : graph[index]) {
            if(visited[req] == Status.VISIT_IN_PROGRESS
                || visited[req] == Status.NOT_VISITED && !dfs(graph, visited, req, result)) {
                return false;
            }
        }
        
        visited[index] = Status.VISITED;
        result.add(0, index);
        return true;
    }
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
#### K closest elements to target in a BST, leetcode #272
Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.
```
class Solution {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> result = new ArrayList<Integer>();
        int ceiling = (int) target + 1;
        int floor = (int) target;
        TreeNode higher = getCeiling(root, ceiling);
        TreeNode lower = getFloor(root, floor);
        while(result.size() < k) {
            if(higher != null && (lower == null || Math.abs(target - higher.val ) < Math.abs(target - lower.val))) {
                result.add(higher.val);
                higher = getCeiling(root, higher.val + 1);
            } else {
                result.add(lower.val);
                lower = getFloor(root, lower.val - 1);
            }
        }
        return result;
    }
    
    private TreeNode getCeiling(TreeNode root, int target) {
        if (root == null) {
            return null;
        }
        if(root.val < target) {
            return getCeiling(root.right, target);
        } else {
            TreeNode node = getCeiling(root.left, target);
            return node == null ? root : node;
        }
    }
    
    private TreeNode getFloor(TreeNode root, int target) {
        if(root == null) {
            return null;
        }
        if(root.val > target) {
            return getFloor(root.left, target);
        } else {
            TreeNode node = getFloor(root.right, target);
            return node == null ? root : node;
        }
    }

}
```

#### Kruskal Algorithm - Minimum Spanning Tree
Kruskal 
1. 把所有的边都放到一个按权重排序的最小堆里
2. 每次从最小堆里拿一个边，看这个边是否构成了一个圈，如果没有就使用这条边，否则就忽略
3. 利用Union Find Set来判断是否构成了一个圈。

例题 LeetCode 1584 https://leetcode.com/problems/min-cost-to-connect-all-points/

You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].

The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.

Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path between any two points.

```
class Solution {
    private static class Edge {
        public int start;
        public int end;
        public int distance;
        public Edge(int start, int end, int distance) {
            this.start = start;
            this.end = end;
            this.distance = distance;
        }
    }
    
    int[] parent;
    private void union(int i, int j) {
        int pi = find(i);
        int pj = find(j);
        if(pi != pj) {
            parent[pj] = pi;
        }
    }
    private int find(int i) {
        while(parent[i] != i) {
            i = parent[i];
        }
        return i;
    }
    
    public int minCostConnectPoints(int[][] points) {
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>((a, b) -> a.distance - b.distance);
        parent = new int[points.length];
        for(int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        for(int i = 0; i < points.length - 1; i++) {
            for(int j = i + 1; j < points.length; j++) {
                int dist = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                pq.offer(new Edge(i, j, dist));
            }
        }
        int result = 0;
        while(!pq.isEmpty()) {
            Edge min = pq.poll();
            int pstart = find(min.start);
            int pend = find(min.end);
            if(pstart != pend) {
                union(min.start, min.end);
                result += min.distance;
            }
        }
        
        return result;
    }
}
```
 
#### Prim Algorithm

TO BE ADDED


#### Dijkstra Algorithm

Leetcode Network Delay Time https://leetcode.com/problems/network-delay-time/


You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi), where ui is the source node, vi is the target node, and wi is the time it takes for a signal to travel from source to target.

We will send a signal from a given node k. Return the time it takes for all the n nodes to receive the signal. If it is impossible for all the n nodes to receive the signal, return -1.


```
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        int[][] graph = new int[n+1][n+1];
        for(int i = 0; i <= n; i++) {
            Arrays.fill(graph[i], Integer.MAX_VALUE);
            graph[i][i] = 0;
        }
        for(int[] time : times) {
            graph[time[0]][time[1]] = time[2];
        }
        int[] distance = new int[n+1]; // the distance to origin k
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[k] = 0;
        
        Set<Integer> settled = new HashSet<Integer>();
        settled.add(k);
        
        for(int i = 1; i <= n; i++) {
            distance[i] = graph[k][i];
        }
        
        for(int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            int next = k;
            // find the vertex not in 'settled' and with the minimum distance to k
            for(int j = 1; j <= n; j++) {
                if(!settled.contains(j) && distance[j] < min) {
                    min = distance[j];
                    next = j;
                }
            }
            settled.add(next);
            // update the distance to all verteices not in 'settled'
            for(int j = 1; j <= n; j++) {
                if(!settled.contains(j) && graph[next][j] < Integer.MAX_VALUE) {
                    distance[j] = Math.min(distance[j], distance[next] + graph[next][j]);
                }
            }
        }

        int max = -1;
        for(int i = 1; i <= n; i++) {
            if(distance[i] == Integer.MAX_VALUE) {
                return -1;
            }
            max = Math.max(max, distance[i]);
        }
        return max;        
    }
}
```

#### BST Iterator
BST iterator, 就是一种非递归的中序遍历，分步执行。所有的iterator都可以也应该在调用next()之前把数据准备好。
```aidl
class BSTIterator {
    Stack<TreeNode> stack = new Stack();
    TreeNode curr = null;

    public BSTIterator(TreeNode root) {
        collectAllTheLeft(root);
    }
    
    private void collectAllTheLeft(TreeNode node) {
        while(node != null) {
            stack.push(node);
            node = node.left;
        }
    }
    
    public int next() {
        TreeNode top = stack.pop();
        collectAllTheLeft(top.right);
        return top.val;
    }
    
    public boolean hasNext() {
        return !stack.empty();
    }
}
```
#### Nested List Iterator
所有的iterator都可以也应该在调用next()之前把数据准备好。
```aidl
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {
    Stack<NestedInteger> stack = new Stack();
    public NestedIterator(List<NestedInteger> nestedList) {
        yank(nestedList);
        yankUntilIntegerOnTop();
    }
    
    private void yank(List<NestedInteger> list) {
        ListIterator<NestedInteger> iter = list.listIterator(list.size());
        while(iter.hasPrevious()) {
            stack.push(iter.previous());
        }
    }
    
    private void yankUntilIntegerOnTop(){
        while(!stack.empty() && !stack.peek().isInteger()) {
            yank(stack.pop().getList());
        }
    }

    @Override
    public Integer next() {
        NestedInteger ni = stack.pop();
        yankUntilIntegerOnTop();
        return ni.getInteger();
    }

    @Override
    public boolean hasNext() {
        return !stack.empty();
    }
}
```
