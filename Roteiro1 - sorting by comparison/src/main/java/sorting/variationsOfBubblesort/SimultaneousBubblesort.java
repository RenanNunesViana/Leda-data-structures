package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * This algorithm applies two bubblesorts simultaneously. In a same iteration, a
 * bubblesort pushes the greatest elements to the right and another bubblesort
 * pushes the smallest elements to the left. At the end of the first iteration,
 * the smallest element is in the first position (index 0) and the greatest
 * element is the last position (index N-1). The next iteration does the same
 * from index 1 to index N-2. And so on. The execution continues until the array
 * is completely ordered.
 */
public class SimultaneousBubblesort<T extends Comparable<T>> extends
		AbstractSorting<T> {
	public void sort(T[] array, int leftIndex, int rightIndex) {
		int i = leftIndex;
		int j = rightIndex;
		while(i < j) {
			int smallestElementI = i;
			int biggestElementI = j;
			for(int w = i + 1; w <= rightIndex; w++) {
				if(array[smallestElementI].compareTo(array[w]) > 0)
					smallestElementI = w;
			}
			Util.swap(array, smallestElementI, i);
			
			for(int z = j - 1; z >= leftIndex; z--) {
				if(array[biggestElementI].compareTo(array[z]) < 0)
					biggestElementI = z;
			}
			Util.swap(array, biggestElementI, j);
			i++;
			j--;
		}
	}
}
