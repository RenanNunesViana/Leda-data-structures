package adt.queue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentDLLQueueTest {

	Queue<Integer> queue1;
	Queue<Integer> queue2;
	
	private void buildingQueues(){
		queue1 = new QueueDoubleLinkedListImpl<>(5);
		queue2 = new QueueDoubleLinkedListImpl<>(3);
	}
	
	@Before
	public void setUp() throws Exception {
		buildingQueues();
		
		queue1.enqueue(5);
		queue1.enqueue(10);
		queue1.enqueue(30);
		queue1.enqueue(15);
		queue1.enqueue(2);
	}
	
	@Test
	public void testEmpty() {
		Assert.assertTrue(queue2.isEmpty());
		Assert.assertFalse(queue1.isEmpty());
	}
	
	@Test
	public void testFull() {
		Assert.assertTrue(queue1.isFull());
		Assert.assertFalse(queue2.isFull());
	}
	
	@Test(expected = QueueOverflowException.class)
	public void testEnqueue() throws QueueOverflowException {
		Assert.assertTrue(queue2.isEmpty());
		queue2.enqueue(10);
		Assert.assertEquals(new Integer(10), queue2.head());
		queue2.enqueue(15);
		Assert.assertEquals(new Integer(10), queue2.head());
		queue2.enqueue(199);
		Assert.assertEquals(new Integer(10), queue2.head());
		queue2.enqueue(33);
	}

	@Test(expected = QueueUnderflowException.class)
	public void testDequeue() throws QueueUnderflowException{
		Assert.assertEquals(new Integer(5), queue1.head());
		Assert.assertEquals(new Integer(5), queue1.dequeue());
		Assert.assertEquals(new Integer(10), queue1.head());
		Assert.assertEquals(new Integer(10), queue1.dequeue());
		Assert.assertEquals(new Integer(30), queue1.head());
		Assert.assertEquals(new Integer(30), queue1.dequeue());
		Assert.assertEquals(new Integer(15), queue1.head());
		Assert.assertEquals(new Integer(15), queue1.dequeue());
		Assert.assertEquals(new Integer(2), queue1.head());
		Assert.assertEquals(new Integer(2), queue1.dequeue());
		queue1.dequeue();
	}
}
