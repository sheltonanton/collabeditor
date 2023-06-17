package com.utils.operations;

import java.util.LinkedHashSet;
import java.util.Queue;

import com.proto.CollabEditor.Client;
import com.proto.CollabEditor.Operation;

public class DequeOperationThread implements Runnable {
	private static final long TIME_TO_WAIT = 10L;
	private LinkedHashSet<Client> clients = null;
	private Queue<OperationBucket> queue;
	
	public DequeOperationThread(Queue<OperationBucket> queue) {
		this.queue = queue;
		this.clients = new LinkedHashSet<>();
	}
	
	public void push(Operation operation) {
		int count = this.clients.size();
		
		OperationBucket bucket = new OperationBucket(operation, count, TIME_TO_WAIT);
		queue.add(bucket);
	}
	
	public void addClient(Client client) {
		this.clients.add(client);
	}
	
	public void removeClient(Client client) {
		this.clients.remove(client);
	}
	
	public void run() {
		try {
			while(true) {
				if(this.queue.size() == 0) this.wait();
				OperationBucket operationBucket = queue.peek();
				operationBucket.await();
				queue.poll();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
