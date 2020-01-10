package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratÃ©gia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o mÃ¡ximo inteiro presente no array a ser ordenado.
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
			Integer countArrayLength = getHigherNumber(array) + 1;
			Integer[] countArray = new Integer[countArrayLength];
			Integer[] auxArray = new Integer[array.length];
			
			for(int i = 0; i < countArrayLength; i++)
				countArray[i] = 0;
			
			for(Integer index : array)
				countArray[index]++;
				
			for(int i = 1; i < countArrayLength; i++)
				countArray[i] += countArray[i - 1];
			
			for(Integer number : array) {
				auxArray[countArray[number] - 1] = number;
				countArray[number]--;
			}
			
			for(int i = 0; i < array.length; i++) {
				array[i] = auxArray[i];
			}
	}
	
	private Integer getHigherNumber(Integer[] arr){
		Integer result = 0;
		for(Integer integer : arr) {
			if(integer.compareTo(result) > 0)
				result = integer;
		}
		return result;
	}

}
