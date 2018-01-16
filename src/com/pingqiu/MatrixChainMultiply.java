package com.pingqiu;

public class MatrixChainMultiply {

    public int minCalc(int[] dim) {
        int count = dim.length - 1;
        int[][] m = new int[count][count];
        int[][] last = new int[count][count]; // records the divide point

        // row of ith matrix is dimensions[i]; col of ith matrix is dimensions[i + 1].

        for (int len = 2; len <= count; len++) {
            for (int start = 0; start < count + 1 - len; start++) {
                int end = start + len - 1;
                m[start][end] = Integer.MAX_VALUE;
                for (int dividePoint = start; dividePoint < end; dividePoint++) {
                    int c = m[start][dividePoint] + m[dividePoint + 1][end]
                            + dim[start] * dim[dividePoint + 1] * dim[end + 1];
                    if (c < m[start][end]) {
                        m[start][end] = c;
                        last[start][end] = dividePoint;
                    }
                }
            }
        }
        System.out.println(printSolution(last, 0, count - 1));
        return m[0][count - 1];
    }

    public String printSolution(int[][] arr, int i, int j) {
        if (i == j) {
            return " " + i;
        } else {
            return "(" + printSolution(arr, i, arr[i][j]) + printSolution(arr, arr[i][j] + 1, j) + ")";
        }
    }

}