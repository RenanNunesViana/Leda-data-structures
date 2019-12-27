package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}

	@Override
	public void insertFirst(T element) {
		RecursiveDoubleLinkedListImpl<T> node = new RecursiveDoubleLinkedListImpl<>();
		
		if (isEmpty()) {
			setData(element);
			setPrevious(node);
			node.setNext(this);
			setNext(new RecursiveDoubleLinkedListImpl<T>());
			((RecursiveDoubleLinkedListImpl<T>) getNext()).setPrevious(this);
		}

		else {
			node.setData(this.getData());
			node.setNext(this.getNext());
			node.setPrevious(this);
			this.setData(element);
			this.setNext(node);
		}
	}

	@Override
	public void removeFirst() {
		if (isEmpty())
			return;

		else {
			this.setData(getNext().getData());
			this.setNext(getNext().getNext());
		}
	
	}

	@Override
	public void insert(T element) {
		if(isEmpty()) {
			setNext(new RecursiveDoubleLinkedListImpl<T>());
			setData(element);
			setPrevious(new RecursiveDoubleLinkedListImpl<T>());
		} 
		
		else if(getNext().isEmpty()) {
			getNext().setData(element);
			getNext().setNext(new RecursiveDoubleLinkedListImpl<T>());
			((RecursiveDoubleLinkedListImpl<T>) getNext()).setPrevious(this);
		}
		
		else 
			getNext().insert(element);
	}
	
	@Override
	public void removeLast() {
		if (isEmpty())
			return;

		else if (getNext().isEmpty()) {
			setNext(null);
			setData(null);
		}

		else {
			((RecursiveDoubleLinkedListImpl<T>) getNext()).removeLast();
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
