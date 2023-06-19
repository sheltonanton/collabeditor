package com.utils.operations;

import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import com.proto.CollabEditor.Client;
import com.proto.CollabEditor.Operation;
import com.proto.CollabEditor.StateSpace;
import com.utils.transform.XForm;

public class ServerOperationManager implements OperationsManager {
	private HashMap<String, StateSpace> clients;
	private int local;
	private Queue<OperationBucket> outgoing;
	private DequeOperationThread dequeThread;

	public ServerOperationManager() {
		this.local = 0;
		this.clients = new HashMap<String, StateSpace>();
		this.outgoing = new LinkedBlockingQueue<OperationBucket>();
		this.dequeThread = new DequeOperationThread(this.outgoing);
		
		new Thread(this.dequeThread).start();
	}

	public StateSpace getClientState(String clientId) {
		return this.clients.get(clientId);
	}

	@Override
	public Operation merge(Operation incomingOperation) {
		this.local = this.local + 1;
		this.purgeSentOperations(incomingOperation);
		incomingOperation = this.transformWithOutgoingOperations(incomingOperation);
		StateSpace clientState = this.clients.get(incomingOperation.getClientId());
		clientState = clientState.toBuilder().setC(incomingOperation.getState().getC()).setS(this.local).build();
		incomingOperation = incomingOperation.toBuilder().setState(clientState).build();
		this.dequeThread.push(incomingOperation);
		this.clients.put(incomingOperation.getClientId(), clientState);
		return incomingOperation;
	}

	@Override
	public Operation apply(Operation incomingOperation) {
		return incomingOperation;
	}

	public StateSpace syncClient(Client client) {
		this.dequeThread.addClient(client);
		StateSpace clientState = StateSpace.newBuilder().setC(0).setS(this.local).build();
		clients.put(client.getId(), clientState);
		return clientState;
	}
	
	public void removeClient(Client client) {
		this.dequeThread.removeClient(client);
		clients.remove(client.getId());
	}

	private void purgeSentOperations(Operation incomingOperation) {
		StateSpace incomingState = incomingOperation.getState();
		
		for(OperationBucket outgoingOperationBucket: this.outgoing) {
			if(outgoingOperationBucket.isRemoved(incomingOperation.getClientId())) {
				continue;
			}
			
			Operation outgoingOperation = outgoingOperationBucket.getOperation();
			StateSpace outgoingState = outgoingOperation.getState();
			if (outgoingState.getS() <= incomingState.getS()) {
				outgoingOperationBucket.remove(incomingOperation.getClientId());
			} else {
				break;
			}
		}
	}

	private Operation transformWithOutgoingOperations(Operation incomingOperation) {
		for (OperationBucket outgoingOperationBucket: this.outgoing) {
			if(outgoingOperationBucket.isRemoved(incomingOperation.getClientId())) {
				continue;
			}
			
			Operation outgoingOperation = outgoingOperationBucket.getOperation();

			List<Operation> transformed = XForm.transform(incomingOperation, outgoingOperation);
			incomingOperation = transformed.get(0);
			outgoingOperation = transformed.get(1);

			outgoingOperationBucket.setOperation(outgoingOperation);
		}

		return incomingOperation;
	}
}
