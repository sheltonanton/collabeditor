package com.utils.operations;

import java.util.LinkedList;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.proto.CollabEditor.Operation;
import com.proto.CollabEditor.Client;

public class DequeOperationThreadTest {

	Queue<OperationBucket> queue;
	DequeOperationThread dequeOperation;
	
	@Before
	public void initializeTest() {
		this.queue = Mockito.spy(new LinkedList<OperationBucket>());
		this.dequeOperation = new DequeOperationThread(this.queue);
	}
	
	@Test
	public void testCreation() {
		new DequeOperationThread(this.queue);
	}
	
	@Test
	public void testThreadClosing() {
		Thread thread = new Thread(dequeOperation);
		thread.start();
		dequeOperation.close();
		try {
			thread.join();
		} catch (InterruptedException e) {
			Assert.fail("Interrupted thread execution");
			e.printStackTrace();
		}
	}
	
	@Test
	public void dequeOperationTest() {
		Client client = Client.newBuilder().setId(UUID.randomUUID().toString()).build();
		dequeOperation.addClient(client);
		dequeOperation.push(Operation.newBuilder().build());
		dequeOperation.push(Operation.newBuilder().build());
		
		
		Mockito.verify(this.queue, Mockito.times(2)).add(Mockito.any(OperationBucket.class));
		
		Thread thread = new Thread(dequeOperation);
		thread.start();
		
		CountDownLatch cdl1 = new CountDownLatch(2);
		Mockito.doAnswer((invocation) -> {
			OperationBucket result = (OperationBucket) invocation.callRealMethod();
			cdl1.countDown();
			return result;
		}).when(this.queue).poll();
		
		this.queue.peek().remove(client.getId());
		
		try {
			boolean interrupted = cdl1.await(1L, TimeUnit.SECONDS);
			Assert.assertFalse(interrupted);
		} catch (InterruptedException e) {
			Assert.fail("Interrupted Thread");
		}
		
		Mockito.verify(this.queue, Mockito.times(1)).poll();
		Assert.assertEquals(this.queue.size(), 1);
	}
}

/*

Executor service
how to test a thread execution

*/