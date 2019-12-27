package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends
		AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressLinearProbingImpl(int size,
			HashFunctionClosedAddressMethod method) {
		super(size);
		hashFunction = new HashFunctionLinearProbing<T>(size, method);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if(isFull())
			return;
		
		HashFunctionLinearProbing<T> function = (HashFunctionLinearProbing<T>) this.hashFunction;
		int probe = 0;
		int hashIndex = function.hash(element, probe);
		
		while(this.table[hashIndex] != null) {
			if(this.table[hashIndex].equals(deletedElement)) {
				this.table[hashIndex] = element;
				this.elements++;
				return;
			}
			
			if(probe == this.table.length)
				break;
			
			probe++;
			this.COLLISIONS++;
			hashIndex = function.hash(element, probe);
		}
		
		if(this.table[hashIndex] == null) {
			this.table[hashIndex] = element;
			this.elements++;
			return;
		}
		
	}

	@Override
	public void remove(T element) {
		HashFunctionLinearProbing<T> function = (HashFunctionLinearProbing<T>) this.hashFunction;
		int probe = 0;
		int hashIndex = function.hash(element, probe);

		while(this.table[hashIndex] != null) {
			if(this.table[hashIndex].equals(element)) {
				this.table[hashIndex] = deletedElement;
				this.elements--;
				return;
			}
			
			if(probe == this.table.length)
				break;
			
			probe++;
			hashIndex = function.hash(element, probe);
		}
	
	}

	@Override
	public T search(T element) {
		HashFunctionLinearProbing<T> function = (HashFunctionLinearProbing<T>) this.hashFunction;
		int probe = 0;
		int hashIndex = function.hash(element, probe);

		while(this.table[hashIndex] != null) {
			if(this.table[hashIndex].equals(element))
				return element;
			
			if(probe == this.table.length)
				break;
			
			probe++;
			hashIndex = function.hash(element, probe);
		}
		
		return null;
	}

	@Override
	public int indexOf(T element) {
		HashFunctionLinearProbing<T> function = (HashFunctionLinearProbing<T>) this.hashFunction;
		int probe = 0;
		int hashIndex = function.hash(element, probe);
		
		while(this.table[hashIndex] != null) {
			if(this.table[hashIndex].equals(element))
				return function.hash(element, probe);
			
			if(probe == this.table.length)
				break;
			
			probe++;
			hashIndex = function.hash(element, probe);
		}
		
		return -1;
	}

}
