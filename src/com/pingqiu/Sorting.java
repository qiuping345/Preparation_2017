package com.pingqiu;

public class Sorting {

	public static void main(String[] args) {
		Sorting sorter = new Sorting();
		
		int[] test = new int[] {3, 5, 1, 2, 4, 7, 11, 9, 0};
		sorter.insertionSort(test);
		PrintUtil.printArray(test);
		
		int[] test2 = new int[] {5, 2, 4, 6, 1, 3};
		sorter.insertionSort(test2);
		PrintUtil.printArray(test2);

		int[] test3 = new int[] {42, 9, 17, 54, 602, -3, 54, 999, -11};
		sorter.bubbleSort(test3);
		PrintUtil.printArray(test3);
		
		int[] test4 = new int[] {42, 9, 17, 54, 602, -3, 54, 999, -11};
		sorter.mergeSort(test4);
		PrintUtil.printArray(test4);
		
	}
	
	
	public void insertionSort(int[] arr) {
		if(arr == null || arr.length < 2) {
			return;
		}
		
		for(int i = 1; i < arr.length; i++) {
			int key = arr[i];
			int j = i - 1;
			for(; j >= 0 && arr[j] > key; j--) {
				arr[j + 1] = arr[j];
			}
			arr[j + 1] = key; // take care it's 'arr[j+1]', it's failed the inner for loop condition.
		}
	}
	
	public void mergeSort(int[] arr) {
		mergeSort(arr, 0, arr.length);
	}
	
	public void mergeSort(int[] arr, int start, int end) {
		int mid = start / 2 + end / 2;
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
					Util.swap(arr, j, j + 1);
				}
			}
		}
	}

}
