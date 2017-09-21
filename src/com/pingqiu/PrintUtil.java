package com.pingqiu;

public class PrintUtil {

    public static void printArray(int[] arr) {
        if (arr != null) {
            for (int i : arr) {
                System.out.print(i + ", ");
            }
        }
        System.out.println(" ");
    }
}
