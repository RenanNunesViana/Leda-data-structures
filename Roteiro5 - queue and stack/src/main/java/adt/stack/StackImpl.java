package adt.stack;

public class StackImpl<T> implements Stack<T> {

	private T[] array;
	private int top;

	@SuppressWarnings("unchecked")
	public StackImpl(int size) {
		array = (T[]) new Object[size];
		top = -1;
	}

	@Override
	public T top() {
		if(isEmpty())
			return null;
		return this.array[top];
	}

	@Override
	public boolean isEmpty() {
		return top == -1;
	}

	@Override
	public boolean isFull() {
		return this.array.length == top + 1;
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if(isFull())
			throw new StackOverflowException();
		
		this.top++;
		this.array[this.top] = element;
	}

	@Override
	public T pop() throws StackUnderflowException {
		if(isEmpty())
			throw new StackUnderflowException();
		
		this.top--;
		return this.array[this.top + 1];
	}

}
