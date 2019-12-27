package adt.avltree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adt.bst.BSTNode;
import adt.bt.Util;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	public AVLCountAndFillImpl() {

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

	@Override
	protected void rebalance(BSTNode<T> node) {
		if (node != null && !node.isEmpty()) {
			String theCase = caseAnalysis(node);
			switch (theCase) {
			case "LL":
				if (isRoot(node))
					this.root = Util.rightRotation(node);
				else
					Util.rightRotation(node);

				this.LLcounter++;
				break;
			case "RR":
				if (isRoot(node))
					this.root = Util.leftRotation(node);
				else
					Util.leftRotation(node);
				this.RRcounter++;
				break;
			case "LR":
				Util.leftRotation((BSTNode<T>) node.getLeft());
				if (isRoot(node))
					this.root = Util.rightRotation(node);
				else
					Util.rightRotation(node);
				this.LRcounter++;
				break;
			case "RL":
				Util.rightRotation((BSTNode<T>) node.getRight());
				if (isRoot(node))
					this.root = Util.leftRotation(node);
				else
					Util.leftRotation(node);
				this.RLcounter++;
			}
		}
	}

	@Override
	protected void rebalanceUp(BSTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();

		while (!parent.isEmpty()) {
			rebalance(parent);
			parent = (BSTNode<T>) parent.getParent();
		}
	}

	@Override
	public int LLcount() {
		return LLcounter;
	}

	@Override
	public int LRcount() {
		return LRcounter;
	}

	@Override
	public int RRcount() {
		return RRcounter;
	}

	@Override
	public int RLcount() {
		return RLcounter;
	}

	@Override
	public void fillWithoutRebalance(T[] array) {
		Arrays.sort(array);
		List<T> elements = new ArrayList<>();
		for (T element : array)
			elements.add(element);
		List<List<T>> matrix = new ArrayList<>();
		
		catchBinary(0, elements.size() - 1, 0, elements, matrix);

		for(List<T> level : matrix) {
			for(T element : level)
				insert(element);
		}
	}

	public void catchBinary(int left, int right, int level, List<T> elements, List<List<T>> matrix) {
		if (left <= right) {
			int middle = (left + right) / 2;
			if(matrix.size() > level)
				matrix.get(level).add(elements.get(middle));
			else {
				matrix.add(new ArrayList<T>());
				matrix.get(level).add(elements.get(middle));
			}
			catchBinary(left, middle - 1, ++level, elements, matrix);
			catchBinary(middle + 1, right, level, elements, matrix);
		}
	}
}