package adt.linkedList.set;

import adt.linkedList.SingleLinkedListImpl;
import adt.linkedList.SingleLinkedListNode;

public class SetLinkedListImpl<T> extends SingleLinkedListImpl<T> implements SetLinkedList<T> {

	@Override
	public void removeDuplicates() {
		SingleLinkedListNode<T> node = getHead();
		while (!node.isNIL()) {
			SingleLinkedListNode<T> comparableNode = node.getNext();
			SingleLinkedListNode<T> previousComparableNode = node;
			while (!comparableNode.isNIL()) {

				if (node.equals(comparableNode)) {
					previousComparableNode.setNext(comparableNode.getNext());
					comparableNode = comparableNode.getNext();
				} else {
					previousComparableNode = comparableNode;
					comparableNode = comparableNode.getNext();
				}
			}

			node = node.getNext();
		}
	}

	@Override
	public SetLinkedList<T> union(SetLinkedList<T> otherSet) {
		SetLinkedListImpl<T> unitedList = new SetLinkedListImpl<>();
		unitedList.setHead(getHead());

		SingleLinkedListNode<T> otherSetHead = ((SetLinkedListImpl<T>) otherSet).getHead();
		
		if(isEmpty())
			setHead(otherSetHead);
		
		else {
			SingleLinkedListNode<T> lastNode = getHead();
			while (!lastNode.getNext().isNIL())
				lastNode = lastNode.getNext();
			
			lastNode.setNext(otherSetHead);
		}
		
		unitedList.removeDuplicates();
		return unitedList;
	}

	@Override
	public SetLinkedList<T> intersection(SetLinkedList<T> otherSet) {
		SetLinkedListImpl<T> intersectedList = new SetLinkedListImpl<>();

		SingleLinkedListNode<T> otherSetNode = ((SetLinkedListImpl<T>) otherSet).getHead();
		while (!otherSetNode.isNIL()) {
			if (search(otherSetNode.getData()) != null)
				intersectedList.insert(otherSetNode.getData());

			otherSetNode = otherSetNode.getNext();
		}

		intersectedList.removeDuplicates();
		return intersectedList;
	}

	@Override
	public void concatenate(SetLinkedList<T> otherSet) {
		SingleLinkedListNode<T> otherSetsHead = ((SetLinkedListImpl<T>) otherSet).getHead();
		SingleLinkedListNode<T> lastNode = getHead();

		if (isEmpty())
			setHead(otherSetsHead);

		else {
			while (!lastNode.getNext().isNIL())
				lastNode = lastNode.getNext();

			lastNode.setNext(otherSetsHead);
		}
		removeDuplicates();
	}

}
