package adt.bst;

import java.util.ArrayList;
import java.util.List;

import adt.bt.BTNode;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return auxHeight(getRoot());
	}

	public int auxHeight(BSTNode<T> node) {
		if (node.isEmpty())
			return -1;

		int leftHeight = auxHeight((BSTNode<T>) node.getLeft());
		int rightHeight = auxHeight((BSTNode<T>) node.getRight());

		return Math.max(leftHeight, rightHeight) + 1;
	}

	@Override
	public BSTNode<T> search(T element) {
		return auxSearch(element, getRoot());
	}

	private BSTNode<T> auxSearch(T element, BSTNode<T> node) {
		if (node.isEmpty() || node.getData().equals(element))
			return node;

		else if (element.compareTo(node.getData()) > 0)
			return auxSearch(element, (BSTNode<T>) node.getRight());

		else
			return auxSearch(element, (BSTNode<T>) node.getLeft());
	}

	@Override
	public void insert(T element) {
		auxInsert(element, getRoot(), new BSTNode<T>());
	}

	private void auxInsert(T element, BSTNode<T> node, BSTNode<T> parent) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
			node.setParent(parent);
		}
		else if (element.compareTo(node.getData()) > 0)
			auxInsert(element, (BSTNode<T>) node.getRight(), node);

		else if (element.compareTo(node.getData()) < 0)
			auxInsert(element, (BSTNode<T>) node.getLeft(), node);
	}

	@Override
	public BSTNode<T> maximum() {
		return auxMaximum(getRoot());
	}

	protected BSTNode<T> auxMaximum(BSTNode<T> node) {
		if (node.isEmpty())
			return null;

		if (node.getRight().isEmpty())
			return node;

		return auxMaximum((BSTNode<T>) node.getRight());
	}

	@Override
	public BSTNode<T> minimum() {
		return auxMinimum(getRoot());
	}

	protected BSTNode<T> auxMinimum(BSTNode<T> node) {
		if (node.isEmpty())
			return null;

		if (node.getLeft().isEmpty())
			return node;

		return auxMinimum((BSTNode<T>) node.getLeft());

	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = search(element);
		if (node.isEmpty())
			return null;

		if (element.equals(getRoot().getData()) && node.getRight().isEmpty())
			return null;

		T parentElement = node.getParent().getData();

		if (parentElement.compareTo(node.getData()) > 0) {
			if (node.getRight().isEmpty())
				return (BSTNode<T>) node.getParent();

			else
				return auxMinimum((BSTNode<T>) node.getRight());
		} else {
			if (node.getRight().isEmpty()) {
				BSTNode<T> parentNode = (BSTNode<T>) node.getParent();
				while (parentNode.getData().compareTo(parentNode.getParent().getData()) > 0) {
					if (parentNode.getData().equals(getRoot().getData()))
						return null;

					parentNode = (BSTNode<T>) parentNode.getParent();
				}

				return (BSTNode<T>) parentNode.getParent();

			} else
				return auxMinimum((BSTNode<T>) node.getRight());
		}
	}

	@Override
	public BSTNode<T> predecessor(T element) {

		BSTNode<T> node = search(element);
		if (node.isEmpty())
			return null;

		T parentElement = node.getParent().getData();

		if (parentElement.compareTo(node.getData()) > 0) {
			if (node.getLeft().isEmpty()) {
				BSTNode<T> parentNode = (BSTNode<T>) node.getParent();
				while (!parentNode.getData().equals(getRoot().getData())) {
					if (parentNode.getData().compareTo(parentNode.getParent().getData()) < 0)
						parentNode = (BSTNode<T>) parentNode.getParent();
					else {
						return (BSTNode<T>) parentNode.getParent();
					}

				}
				return null;
			}

			else
				return auxMaximum((BSTNode<T>) node.getLeft());
		} else {
			if (node.getLeft().isEmpty())
				return (BSTNode<T>) node.getParent();

			else
				return auxMaximum((BSTNode<T>) node.getLeft());
		}

	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);

		if (!node.isEmpty()) {
			if (node.isLeaf()) {
				node.setData(null);
				node.setLeft(null);
				node.setRight(null);
			}

			else if (hasOneChild(node)) {
				if (!isRoot(node)) {
					if (isLeftChild(node)) {
						if (!node.getLeft().isEmpty()) {
							node.getParent().setLeft(node.getLeft());
							node.getLeft().setParent(node.getParent());
						} else {
							node.getParent().setLeft(node.getRight());
							node.getRight().setParent(node.getParent());
						}
					} else {
						if (!node.getLeft().isEmpty()) {
							node.getParent().setRight(node.getLeft());
							node.getLeft().setParent(node.getParent());
						} else {
							node.getParent().setRight(node.getRight());
							node.getRight().setParent(node.getParent());
						}
					}
				} else {
					if (!node.getLeft().isEmpty())
						this.root = (BSTNode<T>) node.getLeft();

					else
						this.root = (BSTNode<T>) node.getRight();

				}
			}

			else {
				if (!isRoot(node)) {
					BSTNode<T> parent = (BSTNode<T>) node.getParent();
					if (isRightChild(node)) {
						parent.setRight(node.getRight());
						auxMaximum((BSTNode<T>) parent.getLeft()).setRight(node.getLeft());
						;
					} else {
						parent.setLeft(node.getLeft());
						auxMinimum((BSTNode<T>) parent.getRight()).setLeft(node.getRight());
					}

				} else {
					BSTNode<T> rightNode = (BSTNode<T>) node.getRight();
					node.setData(rightNode.getData());
					node.setRight(rightNode.getRight());
					BSTNode<T> maxFromLeftNode = auxMaximum((BSTNode<T>) node.getLeft());
					maxFromLeftNode.setRight(rightNode.getLeft());
					rightNode.getLeft().setParent(maxFromLeftNode);
				}
			}	
		}
	}
	
	protected boolean hasOneChild(BSTNode<T> node) {
		if (!node.isEmpty()) {
			if (node.getLeft().isEmpty() && !node.getRight().isEmpty())
				return true;
			else if (!node.getLeft().isEmpty() && node.getRight().isEmpty())
				return true;
		}

		return false;
	}

	protected boolean isLeftChild(BSTNode<T> node) {
		if (node.getParent().getData().compareTo(node.getData()) > 0)
			return true;

		return false;
	}

	protected boolean isRightChild(BSTNode<T> node) {
		if (node.getParent().getData().compareTo(node.getData()) < 0)
			return true;

		return false;
	}

	protected boolean isRoot(BSTNode<T> node) {
		return getRoot().getData().equals(node.getData());
	}


	@SuppressWarnings("unchecked")
	@Override
	public T[] preOrder() {
		List<T> list = new ArrayList<>();
		preOrderSupport(list, getRoot());
		return list.toArray((T[]) new Comparable[size()]);
	}

	private void preOrderSupport(List<T> list, BTNode<T> node) {
		if (!node.isEmpty()) {
			list.add(node.getData());
			preOrderSupport(list, node.getLeft());
			preOrderSupport(list, node.getRight());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] order() {
		List<T> list = new ArrayList<>(size());
		orderSupport(list, getRoot());
		return list.toArray((T[]) new Comparable[size()]);
	}

	private void orderSupport(List<T> list, BTNode<T> node) {
		if (!node.isEmpty()) {
			orderSupport(list, node.getLeft());
			list.add(node.getData());
			orderSupport(list, node.getRight());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] postOrder() {
		List<T> list = new ArrayList<>();
		postOrderSupport(list, getRoot());
		return list.toArray((T[]) new Comparable[size()]);
	}

	private void postOrderSupport(List<T> list, BTNode<T> node) {
		if (!node.isEmpty()) {
			postOrderSupport(list, node.getLeft());
			postOrderSupport(list, node.getRight());
			list.add(node.getData());
		}
	}

	/**
	 * This method is already implemented using recursion. You must understand how
	 * it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
