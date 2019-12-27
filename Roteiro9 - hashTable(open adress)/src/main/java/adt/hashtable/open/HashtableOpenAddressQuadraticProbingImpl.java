package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable> extends AbstractHashtableOpenAddress<T> {

   public HashtableOpenAddressQuadraticProbingImpl(int size, HashFunctionClosedAddressMethod method, int c1, int c2) {
      super(size);
      hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
      this.initiateInternalTable(size);
   }

   @Override
   public void insert(T element) {
      if (isFull())
         return;

      HashFunctionQuadraticProbing<T> function = (HashFunctionQuadraticProbing<T>) this.hashFunction;
      int probe = 0;
      int hashIndex = function.hash(element, probe);

      while (this.table[hashIndex] != null) {
         if (this.table[hashIndex].equals(deletedElement)) {
            this.table[hashIndex] = element;
            this.elements++;
            return;
         }

         if (probe == this.table.length)
            break;

         probe++;
         this.COLLISIONS++;
         hashIndex = function.hash(element, probe);
      }

      if (this.table[hashIndex] == null) {
         this.table[hashIndex] = element;
         this.elements++;
         return;
      }

   }

   @Override
   public void remove(T element) {
      HashFunctionQuadraticProbing<T> function = (HashFunctionQuadraticProbing<T>) this.hashFunction;
      int probe = 0;
      int hashIndex = function.hash(element, probe);

      while (this.table[hashIndex] != null) {
         if (this.table[hashIndex].equals(element)) {
            this.table[hashIndex] = deletedElement;
            this.elements--;
            return;
         }

         if (probe == this.table.length)
            break;

         probe++;
         hashIndex = function.hash(element, probe);
      }

   }

   @Override
   public T search(T element) {
      HashFunctionQuadraticProbing<T> function = (HashFunctionQuadraticProbing<T>) this.hashFunction;
      int probe = 0;
      int hashIndex = function.hash(element, probe);

      while (this.table[hashIndex] != null) {
         if (this.table[hashIndex].equals(element))
            return element;

         if (probe == this.table.length)
            break;

         probe++;
         hashIndex = function.hash(element, probe);
      }

      return null;
   }

   @Override
   public int indexOf(T element) {
      HashFunctionQuadraticProbing<T> function = (HashFunctionQuadraticProbing<T>) this.hashFunction;
      int probe = 0;
      int hashIndex = function.hash(element, probe);

      while (this.table[hashIndex] != null) {
         if (this.table[hashIndex].equals(element))
            return function.hash(element, probe);

         if (probe == this.table.length)
            break;

         probe++;
         hashIndex = function.hash(element, probe);
      }

      return -1;
   }

}
