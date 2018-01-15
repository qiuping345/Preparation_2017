package com.pingqiu;

import java.util.Arrays;

public class Leet_410 {
	public static void main(String[] args) {
		Leet_410 inst = new Leet_410();
		System.out.println(inst.splitArray(new int[] {7,2,5,10,8}, 2));
	}

    public int splitArray(int[] nums, int m) {
        long[][] dp = new long[nums.length+1][m+1];
        for(int start = nums.length - 1; start >= 0; start--) {
        	Arrays.fill(dp[start], 1, dp[start].length, Long.MAX_VALUE);
        }
        for(int segs = 1; segs <= m; segs++){
        	for(int start = nums.length - segs; start >= 0; start--) {
        		int temp = 0;
        		for(int end = start; end + segs - 1 < nums.length ; end++) {
        			temp += nums[end];
                    //System.out.println("start : " + start + ", end: " + end + ", segs: " + segs +", sum[end+1] - sum[start] :" + (sum[end+1] - sum[start]) + ", dp[end + 1][segs-1]: " + dp[end + 1][segs-1]);
                    long result = Math.max(temp,  dp[end + 1][segs-1]);
                    dp[start][segs] = Math.min(dp[start][segs], result);
                }
            }
        }
        
        for(int i = 0; i < dp.length; i++) {
        	PrintUtil.printArray(dp[i]);
        }
        return (int) dp[0][m];
    }
}