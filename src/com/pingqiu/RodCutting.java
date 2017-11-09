package com.pingqiu;

public class RodCutting {

	/**
	 * 
	 * @param prices array of the price, index is the length, value is the price. expecting the value of index 0 to be 0. always like {0, non-zero ...}
	 * @param len  the length of the raw rod
	 * @return max revenue
	 */
	public int cutRodMaxRevenueBottomUp(int[] prices, int len) {
		int[] revenue = new int[len + 1];
		for(int i = 1; i <= len; i++) {
			for(int j = 1; i >= j && j < prices.length; j++) {
				revenue[i] = Math.max(revenue[i], prices[j] + revenue[i - j]);
			}
		}
		return revenue[len];
	}
	
	public void cutRodMaxRevenueBottomUpWithSolution(int[] prices, int len) {
		int[] revenue = new int[len + 1];
		int[] solution = new int[len + 1];
		for (int i = 1; i <= len; i++) {
			for (int j = 1; i >= j && j < prices.length; j++) {
				if (prices[j] + revenue[i - j] > revenue[i]) {
					revenue[i] = prices[j] + revenue[i - j];
					solution[i] = j;
				}
			}
		}
		
		for(int i = 1; i <= len; i++) {
			System.out.print("max revenue for raw rod of length " + i + " is " + revenue[i] + ", solution is :");
			int left = i;
			while(left > 0) {
				System.out.print(solution[left] + ",");
				left -= solution[left];
			}
			System.out.println();
		}
	}
	
	public int cutRodMaxRevenueBottomUpWithCost(int[] prices, int len, int costEachCut) {
		int[] revenue = new int[len + 1];
		for(int i = 1; i <= len; i++) {
			for(int j = 1; i >= j && j < prices.length; j++) {
				revenue[i] = Math.max(revenue[i], prices[j] + revenue[i - j] - (i == j ? 0 : costEachCut));
			}
		}
		return revenue[len];
	}
	
	public int cutRodMaxRevenueTopBottomMem(int[] prices, int len) {
		int[] revenue = new int[len + 1];
		return cutRodMaxRevenueTopBottomMemAux(prices, len, revenue);
	}
	
	public int cutRodMaxRevenueTopBottomMemAux(int[] prices, int len, int[] revenue) {
		if (revenue[len] != 0) {
			return revenue[len];
		}
		
		for(int j = 1; len >= j && j < prices.length; j++) {
			revenue[len] = Math.max(revenue[len], prices[j] + cutRodMaxRevenueTopBottomMemAux(prices, len - j, revenue));
		}
		return revenue[len];
	}
	
}
