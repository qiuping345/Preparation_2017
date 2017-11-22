package com.pingqiu;

public class LongestPalindromeSubsequence {
	/**
	 * find the longest palindrome subsequence.
	 * @param s
	 * @return
	 */
    public int longestPalindromeSubseq(String s) {
        int max = 0;
        String reversed = new StringBuffer(s).reverse().toString();
        return lengthLCS(s, reversed);
    }
    public int lengthLCS(String s1, String s2) {
        int[][] dp = new int[s1.length()+1][s2.length()+1];
        for(int i = 0; i < s1.length(); i++){
            for(int j = 0; j < s2.length(); j++){
                if(s1.charAt(i) == s2.charAt(j)) {
                    dp[i+1][j+1] = dp[i][j] + 1;
                } else {
                    dp[i+1][j+1] = Math.max(dp[i+1][j], dp[i][j+1]);
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }
}
