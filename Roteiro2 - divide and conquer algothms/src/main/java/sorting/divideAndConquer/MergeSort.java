package sorting.divideAndConquer;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if(leftIndex < rightIndex) {
			int middle = (leftIndex + rightIndex) / 2;
			
			sort(array, leftIndex, middle);
			sort(array,middle + 1, rightIndex);
			merge(array, leftIndex, rightIndex);
		}
	}
	
	private void merge(T[] arr, int l, int r) {
		int middle = (l + r) / 2;
		int n1 = middle - l + 1;
		int n2 = r - middle;
		
		T[] arr1 = (T[]) new Comparable[n1];
		T[] arr2 = (T[]) new Comparable[n2];
		
		for(int i = 0; i < n1; i++)
			arr1[i] = arr[i + l];
		
		for(int i = 0; i < n2; i++)
			arr2[i] = arr[i + middle + 1];
		
		int i = 0;
		int j = 0;
		int w = l;
		
		while(i < arr1.length && j < arr2.length) {
			if(arr1[i].compareTo(arr2[j]) < 0) {
				arr[w] = arr1[i];
				i++;
			}else {
				arr[w] = arr2[j];
				j++;
			}
			w++;
		}
		
		while(i < arr1.length) {
			arr[w] = arr1[i];
			i++;
			w++;
		}
		
		while(j < arr2.length) {
			arr[w] = arr2[j];
			j++;
			w++;
		}
	}
}
