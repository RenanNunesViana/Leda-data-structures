package adt.linkedList.set;

import java.util.Arrays;

public class TestWhithMain {
	public static void main(String[] args) {
		SetLinkedList<Integer> set = new SetLinkedListImpl<>();
		SetLinkedList<Integer> set2 = new SetLinkedListImpl<>();
		set.insert(1);
		set.insert(2);
		set.insert(3);
		set.insert(4);
		set.insert(5);
		set.insert(3);
		set.insert(3);
		set.insert(2);
		
		set.removeDuplicates();
		
		////////////////////////////
		set2.insert(6);
		set2.insert(7);
		set2.insert(8);
		set2.insert(9);
		set2.insert(10);
		set2.insert(3);
		set2.insert(4);
		set2.insert(3);
		set2.insert(3);
	
		System.out.println(Arrays.toString(set.union(set2).toArray()));
	}
}
