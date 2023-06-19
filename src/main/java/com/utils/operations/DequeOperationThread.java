package com.utils.operations;

import java.util.LinkedHashSet;
import java.util.Queue;
import java.util.concurrent.Semaphore;

import com.proto.CollabEditor.Client;
import com.proto.CollabEditor.Operation;

public class DequeOperationThread implements Runnable {
	private volatile boolean running = true;
	private static final long TIME_TO_WAIT = 10L;
	private LinkedHashSet<String> clients = null;
	private Queue<OperationBucket> queue;
	private Semaphore semaphore;
	
	public DequeOperationThread(Queue<OperationBucket> queue) {
		this.queue = queue;
		this.clients = new LinkedHashSet<>();
		this.running = true;
		this.semaphore = new Semaphore(0);
	}
	
	public void push(Operation operation) {
		OperationBucket bucket = new OperationBucket(operation, clients, TIME_TO_WAIT);
		queue.add(bucket);
		if(this.semaphore.availablePermits() == 0) semaphore.release();
	}
	
	public void addClient(Client client) {
		this.clients.add(client.getId());
	}
	
	public void removeClient(Client client) {
		this.clients.remove(client.getId());
	}
	
	public void deque() throws InterruptedException {
		while(true) {
			while(running && this.queue.size() == 0) {
				try {
					this.semaphore.acquire();
					System.out.println("Acquired semaphore");
				}catch(InterruptedException e) {
					System.out.println("Interrupted Exception");
				}
			}
			if(!running) break;
			OperationBucket operationBucket = queue.peek();
			operationBucket.await();
			OperationBucket removed = queue.poll();
			System.out.println("Removed Operation: " + removed);
		}
	}
	
	public void run() {
		try {
			this.deque();
		} catch (InterruptedException e) {
			e.printStackTrace();
			running = false;
		}
	}
	
	public void close() {
		this.running = false;
		this.semaphore.release();
	}
}
