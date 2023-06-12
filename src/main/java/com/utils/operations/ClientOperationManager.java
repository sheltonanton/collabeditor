package com.utils.operations;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.proto.CollabEditor.Operation;
import com.proto.CollabEditor.StateSpace;
import com.utils.transform.XForm;

public class ClientOperationManager implements OperationsManager {
	private int local;
	private int server;
	private Queue<OperationBucket> outgoing;

	public ClientOperationManager(int server) {
		this.local = 0;
		this.server = server;
		this.outgoing = new LinkedList<OperationBucket>();
	}

	@Override
	public Operation merge(Operation incomingOperation) {
		this.purgeSentOperations(incomingOperation);
		incomingOperation = this.transformWithOutgoingOperations(incomingOperation);
		this.server = this.server + 1;
		return incomingOperation;
	}

	@Override
	public Operation apply(Operation incomingOperation) {
		Operation outgoingOperation = this.applyWithoutStateChange(incomingOperation);
		this.local = this.local + 1;
		return outgoingOperation;
	}

	private Operation applyWithoutStateChange(Operation incomingOperation) {
		Operation.Builder builder = Operation.newBuilder();
		builder.setMessage(incomingOperation.getMessage());
		builder.setType(incomingOperation.getType());
		builder.setPosition(incomingOperation.getPosition());
		builder.setLength(incomingOperation.getLength());
		builder.setClientId(incomingOperation.getClientId());

		StateSpace.Builder stateSpaceBuilder = StateSpace.newBuilder();
		stateSpaceBuilder.setC(local);
		stateSpaceBuilder.setS(server);

		builder.setState(stateSpaceBuilder.build());

		Operation outgoingOperation = builder.build();
		this.outgoing.add(new OperationBucket(outgoingOperation));

		return outgoingOperation;
	}

	public void setServerState(int serverState) {
		this.server = serverState;
	}

	private void purgeSentOperations(Operation incomingOperation) {
		StateSpace incomingState = incomingOperation.getState();
		while (true) {
			Operation outgoingOperation = this.outgoing.peek().getOperation();
			StateSpace outgoingState = outgoingOperation.getState();
			if (outgoingState.getC() <= incomingState.getC()) {
				this.outgoing.poll();
			} else {
				break;
			}
		}
	}

	private Operation transformWithOutgoingOperations(Operation incomingOperation) {
		int size = this.outgoing.size();
		for (int i = 0; i < size; i++) {
			OperationBucket outgoingOperationBucket = this.outgoing.peek();
			Operation outgoingOperation = outgoingOperationBucket.getOperation();

			List<Operation> transformed = XForm.transform(incomingOperation, outgoingOperation);
			incomingOperation = transformed.get(0);
			outgoingOperation = transformed.get(1);

			outgoingOperationBucket.setOperation(outgoingOperation);
		}

		return incomingOperation;
	}
}
