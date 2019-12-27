package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}


	@Override
	public boolean isEmpty() {
		return getData() == null;
	}

	@Override
	public int size() {
		if(isEmpty()) return 0;
		
		return 1 + getNext().size();
	}

	@Override
	public T search(T element) {
		if(isEmpty()) return null;
		
		if(element.equals(getData())) return getData();
		
		return getNext().search(element);
	}

	@Override
	public void insert(T element) {
		if(isEmpty()) {
			setNext(new RecursiveSingleLinkedListImpl<T>());
			setData(element);
		} else getNext().insert(element);
	}

	@Override
	public void remove(T element) {
		if(isEmpty()) return;
		
		if(element.equals(getData())) {
			setData(getNext().getData());
			setNext(getNext().getNext());
			return;
		}
		
		getNext().remove(element);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		T[] arr = (T[]) new Comparable[size()];
		
		return toArrayAux(arr, 0);
		
	}
	
	private T[] toArrayAux(T[] arr, int i) {
		if(isEmpty()) return arr;
		
		arr[i] = getData();
		
		return getNext().toArrayAux(arr, i + 1);
		
		
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
