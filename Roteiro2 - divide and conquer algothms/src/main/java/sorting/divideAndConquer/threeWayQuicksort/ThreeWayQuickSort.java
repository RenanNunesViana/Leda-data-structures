package sorting.divideAndConquer.threeWayQuicksort;

import sorting.AbstractSorting;
import util.Util;

public class ThreeWayQuickSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * No algoritmo de quicksort, selecionamos um elemento como pivot,
	 * particionamos o array colocando os menores a esquerda do pivot 
	 * e os maiores a direita do pivot, e depois aplicamos a mesma estrategia 
	 * recursivamente na particao a esquerda do pivot e na particao a direita do pivot. 
	 * 
	 * Considerando um array com muitoe elementos repetidos, a estrategia do quicksort 
	 * pode ser otimizada para lidar de forma mais eficiente com isso. Essa melhoria 
	 * eh conhecida como quicksort tree way e consiste da seguinte ideia:
	 * - selecione o pivot e particione o array de forma que
	 *   * arr[l..i] contem elementos menores que o pivot
	 *   * arr[i+1..j-1] contem elementos iguais ao pivot.
	 *   * arr[j..r] contem elementos maiores do que o pivot. 
	 *   
	 * Obviamente, ao final do particionamento, existe necessidade apenas de ordenar
	 * as particoes contendo elementos menores e maiores do que o pivot. Isso eh feito
	 * recursivamente. 
	 **/
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if(leftIndex < rightIndex) {
			int p1 = firstPartition(array, leftIndex, rightIndex);
			int p2 = secondPartition(array, leftIndex, p1);
			
			sort(array, p1, rightIndex);
			sort(array, leftIndex, p2);
		}
	}
	
	private int firstPartition(T[] arr, int l, int r) {
		T pivot = arr[l];
		
		int z = r; // Index of bigger elements
		for(int i = r; i > l; i--) {
			if(arr[i].compareTo(pivot) > 0) {
				Util.swap(arr, z, i);
				z--;
			}
		}
		
		if(arr[z].compareTo(pivot) < 0)
			Util.swap(arr, z, l);
		
		return z;
	}
	
	private int secondPartition(T[] arr, int l, int r) {
		T pivot = arr[r];
		
		int z = l; // Index of smaller elements
		for(int i = l; i < r; i++) {
			if(arr[i].compareTo(pivot) < 0) {
				Util.swap(arr, i, z);
				z++;
			}
		}
		
		if(arr[z].compareTo(pivot) > 0)
			Util.swap(arr, z, r);
		
		return z;
	}

}
