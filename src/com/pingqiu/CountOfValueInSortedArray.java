package com.pingqiu;

public class CountOfValueInSortedArray {
    
    public int binarySearchOnFirstAppearance(int[] arr, int fromIndex, int toIndex, int key) {
        int low = fromIndex;
        int high = toIndex - 1;
        
        while(low <= high) {
            int mid = (low + high) >>> 1;
            if (arr[mid] < key) {
                low = mid + 1;
            } else if (arr[mid] > key) {
                high = mid - 1;
            } else if (mid > 0 && arr[mid - 1] == key) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return - low - 1;
    }
    
    
    public int getCount(int[] arr, int key) {
        int index = binarySearchOnFirstAppearance(arr, 0, arr.length, key);
        if(index < 0) {
            return 0;
        }
        int indexBigger = binarySearchOnFirstAppearance(arr, 0, arr.length, key + 1);
        if(indexBigger < 0) {
            indexBigger = - indexBigger - 1;
        }
        return indexBigger - index;
    }

}
