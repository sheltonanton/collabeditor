package com.utils.operations;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.proto.CollabEditor.Operation;

public class OperationBucket {
	private Operation operation = null;
	private CountDownLatch cdl = null;
	private long timeToWait, currentTime;

	public OperationBucket(Operation operation, int count, long timeToWait) {
		this.operation = operation;
		this.timeToWait = timeToWait;
		this.currentTime = System.currentTimeMillis();
		this.cdl = new CountDownLatch(count);
	}

	public Operation getOperation() {
		return this.operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}
	
	public void countDown() {
		this.cdl.countDown();
	}
	
	public boolean await() throws InterruptedException {
		long timeToWait = Math.max(0, this.timeToWait - ((System.currentTimeMillis() - this.currentTime) / 1000L));
		return this.cdl.await(timeToWait, TimeUnit.SECONDS);
	}
}