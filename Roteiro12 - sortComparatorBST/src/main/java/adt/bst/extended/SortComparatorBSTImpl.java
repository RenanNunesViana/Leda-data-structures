package adt.bst.extended;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.BTNode;

/**
 * Implementacao de SortComparatorBST, uma BST que usa um comparator interno em suas funcionalidades
 * e possui um metodo de ordenar um array dado como parametro, retornando o resultado do percurso
 * desejado que produz o array ordenado. 
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements SortComparatorBST<T> {

	private Comparator<T> comparator;
	
	public SortComparatorBSTImpl(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}

	@Override
	public void insert(T element) {
		this.insert(this.root, element, new BSTNode<T>());
	}

	private void insert(BSTNode<T> node, T element, BSTNode<T> parent) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
			node.setParent(parent);
		} else if (this.comparator.compare(element, node.getData()) < 0) {
			insert((BSTNode<T>) node.getLeft(), element, node);
		} else if (this.comparator.compare(element, node.getData()) > 0) {
			insert((BSTNode<T>) node.getRight(), element, node);
		}
	}
	
	@Override
	public T[] sort(T[] array) {
		while(!isEmpty())
			remove(this.root.getData());
		
		for(T element : array) {
			this.insert(element);
		}
		
		return this.order();
	}

	@Override
	public T[] reverseOrder() {
			List<T> list = new ArrayList<>(this.size());
			reverseOrder(list, this.root);
			return list.toArray((T[]) new Comparable[this.size()]);
		}

		private void reverseOrder(List<T> list, BTNode<T> node) {
			if (!node.isEmpty()) {
				reverseOrder(list, node.getRight());
				list.add(node.getData());
				reverseOrder(list, node.getLeft());
			}
		}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

}