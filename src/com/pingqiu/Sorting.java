package com.pingqiu;

import static com.pingqiu.Util.swap;

public class Sorting {

    /**
     * Insertion Sort
     * @param arr
     */
    public void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            for (; j >= 0 && arr[j] > key; j--) {
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = key; // take care it's 'arr[j+1]', it's failed the inner for loop condition.
        }
    }

    /**
     * Merge Sort
     * @param arr
     */
    public void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length);
    }

    public void mergeSort(int[] arr, int start, int end) {
        int mid = start + (end - start) / 2;
        if (end - start < 2) {
            return;
        }

        int[] arrLeft = new int[mid - start];
        int[] arrRight = new int[end - mid];
        System.arraycopy(arr, start, arrLeft, 0, arrLeft.length);
        System.arraycopy(arr, mid, arrRight, 0, arrRight.length);

        mergeSort(arrLeft, 0, arrLeft.length);
        mergeSort(arrRight, 0, arrRight.length);

        int iArr = start;
        int iArrLeft = 0;
        int iArrRight = 0;

        while (iArr < end) {
            if (iArrLeft < arrLeft.length && iArrRight < arrRight.length) {
                arr[iArr++] = arrLeft[iArrLeft] < arrRight[iArrRight] ? arrLeft[iArrLeft++] : arrRight[iArrRight++];
            } else if (iArrLeft < arrLeft.length) {
                arr[iArr++] = arrLeft[iArrLeft++];
            } else {
                arr[iArr++] = arrRight[iArrRight++];
            }
        }
    }

    public void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    /**
     * Quick Sort
     * @param arr
     */
    public void quicksort(int[] arr) {
        quicksort(arr, 0, arr.length - 1);
    }

    private void quicksort(int[] arr, int start, int end) {
        if (start < end) {
            int p = partition(arr, start, end);
            quicksort(arr, start, p - 1);
            quicksort(arr, p + 1, end);
        }
    }

    private int partition(int[] nums, int start, int end) {
        int s = start - 1;
        for (int i = start; i < end; i++) {
            if (nums[i] <= nums[end]) {
                swap(nums, ++s, i);
            }
        }
        swap(nums, ++s, end);
        return s;
    }

    /**
     * Heap Sort
     * @param arr
     */
    public void heapSort(int[] arr) {
        if(arr == null || arr.length == 0) {
            return;
        }
        for(int i = arr.length / 2; i >= 0; i--) {
            siftDown(arr, i, arr.length);
        }
        for(int i = arr.length - 1; i > 0; i--) {
            swap(arr, i, 0);
            siftDown(arr, 0, i);
        }
    }

    private void siftDown(int[] arr, int i, int len) {
        if(i >= len / 2) {
            return;
        }

        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int max = right;

        if (max >= len || arr[left] > arr[max]) {
            max = left;
        }

        if(arr[i] < arr[max]) {
            swap(arr, i , max);
            siftDown(arr, max, len);
        }
    }
}
