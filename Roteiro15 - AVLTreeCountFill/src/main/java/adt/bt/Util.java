package adt.bt;

import adt.bst.BSTNode;

public class Util {

	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * 
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		BTNode<T> pivot = node.getRight();
		node.setRight(pivot.getLeft());
		pivot.setLeft(node);
		pivot.setParent(node.getParent());

		if (!node.getParent().isEmpty()) {
			if (node.getParent().getData().compareTo(node.getData()) > 0)
				node.getParent().setLeft(pivot);
			else if (node.getParent().getData().compareTo(node.getData()) < 0)
				node.getParent().setRight(pivot);
		}
		node.setParent(pivot);
		return (BSTNode<T>) pivot;
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * 
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		BTNode<T> pivot = node.getLeft();
		node.setLeft(pivot.getRight());
		pivot.setRight(node);
		pivot.setParent(node.getParent());

		if (!node.getParent().isEmpty()) {
			if (node.getParent().getData().compareTo(node.getData()) > 0)
				node.getParent().setLeft(pivot);
			else if (node.getParent().getData().compareTo(node.getData()) < 0)
				node.getParent().setRight(pivot);
		}

		node.setParent(pivot);
		return (BSTNode<T>) pivot;
	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}
