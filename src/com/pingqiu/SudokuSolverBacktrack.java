package com.pingqiu;

public class SudokuSolverBacktrack {

    public void solveSudoku(char[][] board) {
        solve(board, 0);
    }

    private boolean solve(char[][] board, int position) {
        if(position == 9 * 9) {
            return true;
        }
        int row = position / 9;
        int col = position % 9;
        if (board[row][col] != '.') {
            return solve(board, position + 1);
        }
        for(char ch = '1'; ch <= '9'; ch++) {
            if(isValid(board, row, col, ch)) {
                board[row][col] = ch;
                if(solve(board, position + 1)) {
                    return true;
                }
                board[row][col] = '.';
            }
        }
        return false;
    }

    private boolean isValid(char[][] board, int row, int col, char ch) {
        int top = row - row % 3;
        int left = col - col % 3;
        for(int i = 0; i < 9; i++) {
            if(board[row][i] == ch) {
                return false;
            }
            if(board[i][col] == ch) {
                return false;
            }
            int r = top + i / 3;
            int c = left + i % 3;
            if(board[r][c] == ch) {
                return false;
            }
        }
        return true;
    }
}