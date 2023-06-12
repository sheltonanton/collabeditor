package com.utils.operations;

import com.proto.CollabEditor.Operation;

public class OperationBucket {
	private Operation operation = null;

	OperationBucket(Operation operation) {
		this.operation = operation;
	}

	Operation getOperation() {
		return this.operation;
	}

	void setOperation(Operation operation) {
		this.operation = operation;
	}
}