package com.utils.operations;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.proto.CollabEditor.Client;
import com.proto.CollabEditor.Operation;
import com.proto.CollabEditor.StateSpace;
import com.utils.transform.XForm;

public class ServerOperationManager implements OperationsManager {
	private HashMap<String, StateSpace> clients;
	private int local;
	private Queue<OperationBucket> outgoing;

	public ServerOperationManager() {
		this.local = 0;
		this.clients = new HashMap<String, StateSpace>();
		this.outgoing = new LinkedList<OperationBucket>();
	}

	public StateSpace getClientState(String clientId) {
		return this.clients.get(clientId);
	}

	@Override
	public Operation merge(Operation incomingOperation) {
		this.purgeSentOperations(incomingOperation);
		incomingOperation = this.transformWithOutgoingOperations(incomingOperation);
		StateSpace clientState = this.clients.get(incomingOperation.getClientId());
		clientState = clientState.toBuilder().setC(incomingOperation.getState().getC()).setS(local).build();
		this.clients.put(incomingOperation.getClientId(), clientState);
		this.local = this.local + 1;
		return incomingOperation;
	}

	@Override
	public Operation apply(Operation incomingOperation) {
		return incomingOperation;
	}

	public Client syncClient(Client client) {
		clients.put(client.getId(), StateSpace.newBuilder().setC(0).setS(this.local).build());
		client = client.toBuilder().setServerState(this.local).setPriority(this.clients.size()).build();
		return client;
	}

	private void purgeSentOperations(Operation incomingOperation) {
		StateSpace incomingState = incomingOperation.getState();
		while (this.outgoing.size() > 0) {
			Operation outgoingOperation = this.outgoing.peek().getOperation();
			StateSpace outgoingState = outgoingOperation.getState();
			if (outgoingState.getS() < incomingState.getS()) {
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
