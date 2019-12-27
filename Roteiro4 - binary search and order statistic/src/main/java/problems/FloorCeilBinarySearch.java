package problems;

/**
 * Calcula o floor e ceil de um numero em um array usando a estrategia de busca
 * binaria.
 * 
 * Restricoes: - Algoritmo in-place (nao pode usar memoria extra a nao ser
 * variaveis locais) - O tempo de seu algoritmo deve ser O(log n).
 * 
 * @author Adalberto
 *
 */
public class FloorCeilBinarySearch implements FloorCeil {

	@Override
	public Integer floor(Integer[] array, Integer x) {
		if (x.compareTo(array[0]) < 0)
			return null;
		else if (x.compareTo(array[array.length - 1]) > 0)
			return array[array.length - 1];
		else
			return ceilBinarySearch(array, 0, array.length - 1, x, false);
	}

	@Override
	public Integer ceil(Integer[] array, Integer x) {
		if (x.compareTo(array[array.length - 1]) > 0)
			return null;
		else if (x.compareTo(array[0]) < 0)
			return array[0];
		else
			return ceilBinarySearch(array, 0, array.length - 1, x, true);
	}

	private Integer ceilBinarySearch(Integer[] arr, int l, int r, Integer x, boolean isCeil) {
		int middle = (l + r) / 2;
		if (l <= r) {
			if (arr[middle].equals(x))
				return x;
			if (arr[middle].compareTo(x) > 0)
				return ceilBinarySearch(arr, l, middle - 1, x, isCeil);
			else
				return ceilBinarySearch(arr, middle + 1, r, x, isCeil);
		}
		if(isCeil)
			return arr[l];
		
		return arr[r];
	}
}
