package adt.stack;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StudentDLLStackTest {
	StackDoubleLinkedListImpl<Integer> stack1;
	StackDoubleLinkedListImpl<Integer> stack2;
	
	private void buildingStacks() {
		stack1 = new StackDoubleLinkedListImpl<>(5);
		stack2 = new StackDoubleLinkedListImpl<>(3);
	}
	
	@Before
	public void setUp() throws StackOverflowException {
		buildingStacks();
		stack1.push(1);
		stack1.push(2);
		stack1.push(3);
		stack1.push(4);
		stack1.push(5);
	}
	
	@Test
	public void testIsFull() {
		assertTrue(stack1.isFull());
		assertFalse(stack2.isFull());
	}
	
	@Test
	public void testIsEmpty() {
		assertTrue(stack2.isEmpty());
		assertFalse(stack1.isEmpty());
	}
	
	@Test(expected = StackUnderflowException.class)
	public void testPop() throws StackUnderflowException {
		assertEquals(new Integer(5), stack1.top());
		assertEquals(new Integer(5), stack1.pop());
		assertEquals(new Integer(4), stack1.top());
		assertEquals(new Integer(4), stack1.pop());
		assertEquals(new Integer(3), stack1.top());
		assertEquals(new Integer(3), stack1.pop());
		assertEquals(new Integer(2), stack1.top());
		assertEquals(new Integer(2), stack1.pop());
		assertEquals(new Integer(1), stack1.top());
		assertEquals(new Integer(1), stack1.pop());
		stack1.pop();
	}
	
	@Test(expected = StackOverflowException.class)
	public void testPush() throws StackOverflowException {
		stack2.push(1);
		assertEquals(new Integer(1), stack2.top());
		stack2.push(2);
		assertEquals(new Integer(2), stack2.top());
		stack2.push(3);
		assertEquals(new Integer(3), stack2.top());
		stack2.push(4);
	}
}
