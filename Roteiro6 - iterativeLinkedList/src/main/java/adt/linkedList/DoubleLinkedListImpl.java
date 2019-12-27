package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	@Override
	public void insertFirst(T element) {
		DoubleLinkedListNode<T> firstNode = new DoubleLinkedListNode<T>(element, (DoubleLinkedListNode<T>) getHead(),
				new DoubleLinkedListNode<T>());
		
		((DoubleLinkedListNode<T>)getHead()).setPrevious(firstNode);
		
		if(isEmpty())
			setLast(firstNode);
		
		setHead(firstNode);
	}

	@Override
	public void removeFirst() {
		if(isEmpty())
			return;
		
		DoubleLinkedListNode<T> secondNode = (DoubleLinkedListNode<T>) getHead().getNext();

		secondNode.setPrevious(((DoubleLinkedListNode<T>)getHead()).getPrevious());
		setHead(secondNode);
	}

	@Override
	public void removeLast() {
		if(isEmpty())
			return;
		
		DoubleLinkedListNode<T> lastNode = getLast().getPrevious();

		if(size() == 1)
			setHead(lastNode);
		
		setLast(lastNode);
		getLast().setNext(new DoubleLinkedListNode<T>());
	}

	@Override
	public void insert(T element) {
		DoubleLinkedListNode<T> lastNode = new DoubleLinkedListNode<T>(element, new DoubleLinkedListNode<T>(),
				getLast());

		if (isEmpty())
			setHead(lastNode);
		
		else
			getLast().setNext(lastNode);
		
		setLast(lastNode);
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
