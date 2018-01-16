package com.pingqiu;

public class EightQueens {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        EightQueens inst = new EightQueens();
        System.out.println(inst.enumerate());
    }
    
    
    private int[] cols = new int[8];
    public int enumerate() {
        return enumerate(cols, 0);
    }
    public int count = 0;
    public int enumerate(int[] cols, int idx) {
        if(idx == 8) {
            return 1;
        }
        int count = 0;
        for(int row = 0; row < 8; row++) {
            if(couldPlace(cols, idx, row)) {
                cols[idx] = row;
                count += enumerate(cols, idx + 1);
            }
        }
        return count;
    }
    
    public boolean couldPlace(int[] cols, int idx, int row) {
        for(int i = 0; i < idx; i++) {
            if(cols[i] == row || Math.abs(cols[i] - row) == Math.abs(idx - i)) {
                return false;
            }
        }
        return true;
    }

}
