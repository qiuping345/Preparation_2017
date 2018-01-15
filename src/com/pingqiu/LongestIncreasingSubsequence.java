package com.pingqiu;

import java.util.Arrays;

public class LongestIncreasingSubsequence {

    public int lengthOfLIS_nlgn(int[] nums) {            
        int[] dp = new int[nums.length];
        int len = 0;

        for(int x : nums) {
            int i = Arrays.binarySearch(dp, 0, len, x);
            if(i < 0) {
            	i = -(i + 1);
            }
            dp[i] = x;
            if(i == len) {
            	len++;
            }
            
            Util.printFirstNElements(dp, len);
        }

        return len;
    }
    
    
    public int lengthOfLIS_square(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int[] map = new int[len];
        Arrays.fill(map, 1);
        int result = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i-1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    map[i] = Math.max(map[i], map[j] + 1);
                }
            }
            result = Math.max(result, map[i]);
        }
        return result;
    }
    
	
}
