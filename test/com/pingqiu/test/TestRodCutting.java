package com.pingqiu.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import com.pingqiu.RodCutting;

public class TestRodCutting {

	private static final int[] prices = new int[] {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};	
	private RodCutting inst = new RodCutting();
	@Test
	public void testCutRodMaxRevenueBottomUp() {
		assertEquals(13, inst.cutRodMaxRevenueBottomUp(prices, 5));
		assertEquals(10, inst.cutRodMaxRevenueBottomUp(prices, 4));
		assertEquals(73, inst.cutRodMaxRevenueBottomUp(prices, 25));
		assertEquals(108, inst.cutRodMaxRevenueBottomUp(prices, 37));
		assertEquals(253, inst.cutRodMaxRevenueBottomUp(prices, 85));
		assertEquals(385, inst.cutRodMaxRevenueBottomUp(prices, 129));
		assertEquals(698, inst.cutRodMaxRevenueBottomUp(prices, 233));
	}
	
	@Test
	public void testCutRodMaxRevenueBottomUpWitCost() {
		assertEquals(13, inst.cutRodMaxRevenueBottomUpWithCost(prices, 5, 0));				
		assertEquals(10, inst.cutRodMaxRevenueBottomUpWithCost(prices, 4, 0));
		assertEquals(73, inst.cutRodMaxRevenueBottomUpWithCost(prices, 25, 0));
		
		assertEquals(12, inst.cutRodMaxRevenueBottomUpWithCost(prices, 5, 1));				
		assertEquals(11, inst.cutRodMaxRevenueBottomUpWithCost(prices, 5, 2));
		assertEquals(9, inst.cutRodMaxRevenueBottomUpWithCost(prices, 4, 1));
		assertEquals(9, inst.cutRodMaxRevenueBottomUpWithCost(prices, 4, 2));
		assertEquals(30, inst.cutRodMaxRevenueBottomUpWithCost(prices, 10, 1));
		assertEquals(30, inst.cutRodMaxRevenueBottomUpWithCost(prices, 10, 2));
		assertEquals(70, inst.cutRodMaxRevenueBottomUpWithCost(prices, 25, 1));
		assertEquals(67, inst.cutRodMaxRevenueBottomUpWithCost(prices, 25, 2));
		assertEquals(65, inst.cutRodMaxRevenueBottomUpWithCost(prices, 25, 3));
	}
	
	@Test
	public void testCutRodMaxRevenueTopBottomMem() {
		assertEquals(13, inst.cutRodMaxRevenueTopBottomMem(prices, 5));
		assertEquals(10, inst.cutRodMaxRevenueTopBottomMem(prices, 4));
		assertEquals(73, inst.cutRodMaxRevenueTopBottomMem(prices, 25));
		assertEquals(108, inst.cutRodMaxRevenueTopBottomMem(prices, 37));
		assertEquals(253, inst.cutRodMaxRevenueTopBottomMem(prices, 85));
		assertEquals(385, inst.cutRodMaxRevenueTopBottomMem(prices, 129));
		assertEquals(698, inst.cutRodMaxRevenueTopBottomMem(prices, 233));
	}
	
	@Test
	public void testCutRodMaxRevenueBottomUpWithSolution () {
		inst.cutRodMaxRevenueBottomUpWithSolution(prices, 5);
		inst.cutRodMaxRevenueBottomUpWithSolution(prices, 4);
		inst.cutRodMaxRevenueBottomUpWithSolution(prices, 25);
		inst.cutRodMaxRevenueBottomUpWithSolution(prices, 37);
		inst.cutRodMaxRevenueBottomUpWithSolution(prices, 85);
		inst.cutRodMaxRevenueBottomUpWithSolution(prices, 129);
		inst.cutRodMaxRevenueBottomUpWithSolution(prices, 233);
	}
}
