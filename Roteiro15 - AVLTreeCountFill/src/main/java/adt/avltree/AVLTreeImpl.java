package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Implementacao de uma arvore AVL
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		if (!node.isEmpty()) {
			int invariant = auxHeight((BSTNode<T>) node.getLeft()) - auxHeight((BSTNode<T>) node.getRight());
			return invariant;
		}
		return 0;
	}

	private String caseAnalysis(BSTNode<T> node) {
		if (!node.isEmpty()) {
			int invariant = calculateBalance(node);
			if (invariant > 1) {
				int leftInvariant = calculateBalance((BSTNode<T>) node.getLeft());
				if (leftInvariant >= 0)
					return "LL";
				else
					return "LR";
			}
			if (invariant < -1) {
				int rightInvariant = calculateBalance((BSTNode<T>) node.getRight());
				if (rightInvariant <= 0)
					return "RR";
				else
					return "RL";
			}
			return "BALANCED";
		}
		return "EMPTYNODE";
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		if (node != null && !node.isEmpty()) {
			String theCase = caseAnalysis(node);
			switch (theCase) {
			case "LL":
				if (isRoot(node))
					this.root = Util.rightRotation(node);
				else
					Util.rightRotation(node);
				break;
			case "RR":
				if (isRoot(node))
					this.root = Util.leftRotation(node);
				else
					Util.leftRotation(node);
				break;
			case "LR":
				Util.leftRotation((BSTNode<T>) node.getLeft());
				if (isRoot(node))
					this.root = Util.rightRotation(node);
				else
					Util.rightRotation(node);
				break;
			case "RL":
				Util.rightRotation((BSTNode<T>) node.getRight());
				if (isRoot(node))
					this.root = Util.leftRotation(node);
				else
					Util.leftRotation(node);
			}
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();

		while (!parent.isEmpty()) {
			rebalance(parent);
			parent = (BSTNode<T>) parent.getParent();
		}
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
		} else {
			if (element.compareTo(node.getData()) > 0)
				auxInsert(element, (BSTNode<T>) node.getRight(), node);

			else if (element.compareTo(node.getData()) < 0)
				auxInsert(element, (BSTNode<T>) node.getLeft(), node);

			rebalance(node);
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
				rebalanceUp(node);
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
				rebalanceUp(node);
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
			rebalanceUp(node);
		}
	}

}
