package com.pingqiu;

public class DivideConquer_max_subarray {

    public int maxSubArray(int[] arr, int start, int end) {
        int mid = start / 2 + end / 2;
        if (end == start) {
            return arr[start];
        }

        int sumLeft = Integer.MIN_VALUE;
        int tempLeft = 0;
        for (int i = mid; i >= start; i--) {
            tempLeft += arr[i];
            if (tempLeft > sumLeft) {
                sumLeft = tempLeft;
            }
        }

        int sumRight = Integer.MIN_VALUE;
        int tempRight = 0;
        for (int i = mid + 1; i <= end; i++) {
            tempRight += arr[i];
            if (tempRight > sumRight) {
                sumRight = tempRight;
            }
        }

        int sumWithMid = sumLeft + sumRight;
        int maxLeft = maxSubArray(arr, start, mid);
        int maxRight = maxSubArray(arr, mid + 1, end);
        return Math.max(sumWithMid, Math.max(maxLeft, maxRight));
    }

}
