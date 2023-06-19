package com.utils.operations;

import java.util.HashSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.proto.CollabEditor.Operation;

public class OperationBucket {
	private Operation operation = null;
	private CountDownLatch cdl = null;
	private long timeToWait, currentTime;
	private HashSet<String> clientIds = null;
	
	public OperationBucket(Operation operation) {
		this.operation = operation;
	}

	public OperationBucket(Operation operation, HashSet<String> clients, long timeToWait) {
		this.operation = operation;
		this.timeToWait = timeToWait;
		this.currentTime = System.currentTimeMillis();
		this.clientIds = new HashSet<String>(clients);
		this.clientIds.remove(operation.getClientId());
		this.cdl = new CountDownLatch(this.clientIds.size());
	}

	public Operation getOperation() {
		return this.operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}
	
	public void remove(String clientId) {
		if(this.clientIds.contains(clientId)) {
			this.clientIds.remove(clientId);
			this.cdl.countDown();
		}
	}
	
	public boolean isRemoved(String clientId) {
		return !this.clientIds.contains(clientId);
	}
	
	public boolean await() throws InterruptedException {
		long timeToWait = Math.max(0, this.timeToWait - ((System.currentTimeMillis() - this.currentTime) / 1000L));
		return this.cdl.await(timeToWait, TimeUnit.SECONDS);
	}
}