package com.pingqiu;

import java.util.LinkedList;
import java.util.List;

public class LongestCommonSubsequence {

    public static int lengthOfLCS(String s1, String s2) {
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        int[][] map = new int[arr1.length + 1][arr2.length + 1];

        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                if (arr1[i] == arr2[j]) {
                    map[i + 1][j + 1] = map[i][j] + 1;
                } else {
                    map[i + 1][j + 1] = Math.max(map[i][j + 1], map[i + 1][j]);
                }
            }
        }
        return map[arr1.length][arr2.length];
    }

    public static String oneOfLCS(String s1, String s2) {
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();

        int[][] map = new int[arr1.length + 1][arr2.length + 1];
        char[][] trace = new char[arr1.length + 1][arr2.length + 1]; // "U", up; "L", left, "T", topleft corner.

        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                if (arr1[i] == arr2[j]) {
                    map[i + 1][j + 1] = map[i][j] + 1;
                    trace[i + 1][j + 1] = 'T';
                } else if (map[i][j + 1] >= map[i + 1][j]) {
                    map[i + 1][j + 1] = map[i][j + 1];
                    trace[i + 1][j + 1] = 'U';
                } else {
                    map[i + 1][j + 1] = map[i + 1][j];
                    trace[i + 1][j + 1] = 'L';
                }
            }
        }

        StringBuffer sb = new StringBuffer();
        int row = arr1.length;
        int col = arr2.length;
        while (row > 0 && col > 0) {
            if (trace[row][col] == 'T') {
                row--;
                col--;
                sb.insert(0, arr1[row]);
            } else if (trace[row][col] == 'U') {
                row--;
            } else if (trace[row][col] == 'L') {
                col--;
            }
        }
        return sb.toString();
    }

    public static List<String> allLCS(String s1, String s2) {
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();

        int[][] map = new int[arr1.length + 1][arr2.length + 1];
        char[][] trace = new char[arr1.length + 1][arr2.length + 1]; // "U", up; "L", left; "B", could go up or left;
                                                                     // "T", topleft corner.

        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                if (arr1[i] == arr2[j]) {
                    map[i + 1][j + 1] = map[i][j] + 1;
                    trace[i + 1][j + 1] = 'T';
                } else if (map[i][j + 1] >= map[i + 1][j]) {
                    map[i + 1][j + 1] = map[i][j + 1];
                    trace[i + 1][j + 1] = map[i][j + 1] == map[i + 1][j] ? 'B' : 'U';
                } else {
                    map[i + 1][j + 1] = map[i + 1][j];
                    trace[i + 1][j + 1] = 'L';
                }
            }
        }

        List<String> list = new LinkedList<String>();
        backtraceLCS(trace, arr1.length, arr2.length, "", s1, s2, list);
        return list;

    }

    public static void backtraceLCS(char[][] trace, int row, int col, String suffix, String s1, String s2,
            List<String> list) {
        if (row == 0 || col == 0) {
            if (!list.contains(suffix)) {
                list.add(suffix);
            }
            return;
        }
        if (trace[row][col] == 'T') {
            row--;
            col--;
            backtraceLCS(trace, row, col, "" + s1.charAt(row) + suffix, s1, s2, list);
        } else if (trace[row][col] == 'U') {
            backtraceLCS(trace, row - 1, col, suffix, s1, s2, list);
        } else if (trace[row][col] == 'L') {
            backtraceLCS(trace, row, col - 1, suffix, s1, s2, list);
        } else if (trace[row][col] == 'B') {
            backtraceLCS(trace, row - 1, col, suffix, s1, s2, list);
            backtraceLCS(trace, row, col - 1, suffix, s1, s2, list);
        }
    }

}
