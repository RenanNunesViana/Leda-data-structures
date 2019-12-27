package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * The bubble sort algorithm iterates over the array multiple times, pushing big
 * elements to the right by swapping adjacent elements, until the array is
 * sorted.
 */
public class BubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		int lastIndex = rightIndex;
		while(leftIndex < lastIndex) {
			int greatestEIndex = leftIndex;
			for(int j = leftIndex + 1; j <= lastIndex; j++) {
				if(array[greatestEIndex].compareTo(array[j]) < 0)
					greatestEIndex = j;
			}
			Util.swap(array, greatestEIndex, lastIndex);
			lastIndex--;
		}
	}
}
