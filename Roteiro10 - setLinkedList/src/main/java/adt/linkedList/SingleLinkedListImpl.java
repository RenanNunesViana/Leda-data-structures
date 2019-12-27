
package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return this.head.isNIL();
	}

	@Override
	public int size() {
		SingleLinkedListNode<T> node = this.head;
		int size = 0;
		while (!node.isNIL()) {
			size++;
			node = node.next;
		}
		return size;
	}

	@Override
	public T search(T element) {
		SingleLinkedListNode<T> node = this.head;
		while (!node.isNIL()) {
			if (node.getData().equals(element))
				return element;

			node = node.next;
		}

		return null;
	}

	@Override
	public void insert(T element) {
		SingleLinkedListNode<T> node = this.head;
		
		while (!node.isNIL())
			node = node.getNext();
		
		node.setData(element);
		node.setNext(new SingleLinkedListNode<T>());
	}

	@Override
	public void remove(T element) {
		SingleLinkedListNode<T> node = this.head;
		while(!node.isNIL()) {
			if(node.getData().equals(element)) {
				node.setData(node.getNext().getData());
				node.setNext(node.getNext().getNext());
				return;
			}
			
			node = node.getNext();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		T[] arr = (T[]) new Comparable[size()];
		SingleLinkedListNode<T> node = this.head;
		int i = 0;
		while(!node.isNIL()) {
			arr[i] = node.getData();
			i++;
			node = node.getNext();
		}
		
		return arr;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
