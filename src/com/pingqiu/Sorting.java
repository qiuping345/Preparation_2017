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
			arr[j + 1] = key;
		}
	}

}
