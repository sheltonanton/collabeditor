package com.services;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

import com.proto.CollabEditor.Client;
import com.proto.CollabEditor.Operation;
import com.proto.CollabEditor.StateSpace;
import com.proto.CollabEditor.Type;
import com.proto.CollabServiceGrpc.CollabServiceImplBase;
import com.utils.operations.ServerOperationManager;

import io.grpc.stub.StreamObserver;

public class CollabServiceImpl extends CollabServiceImplBase {
	private LinkedHashMap<String, StreamObserver<Operation>> observers;
	private ServerOperationManager operationsManager;
	private StringBuilder builder;

	public CollabServiceImpl() {
		this.observers = new LinkedHashMap<>();
		this.operationsManager = new ServerOperationManager();
		this.builder = new StringBuilder();
	}

	@Override
	public StreamObserver<Operation> send(StreamObserver<Operation> _responseObserver) {
		return new StreamObserver<Operation>() {
			@Override
			public void onNext(Operation operation) {
				operation = operationsManager.merge(operation);
				for (Entry<String, StreamObserver<Operation>> entry : observers.entrySet()) {
					String clientId = entry.getKey();
					if (!clientId.equals(operation.getClientId())) {
						StateSpace clientState = operationsManager.getClientState(clientId);
						clientState = StateSpace.newBuilder().setC(clientState.getC()).setS(operation.getState().getS())
								.build();
						entry.getValue().onNext(operation.toBuilder().setState(clientState).build());
					}
				}
				if (operation.getType() == Type.INSERT) {
					builder.insert(operation.getPosition(), operation.getMessage());
				} else if (operation.getType() == Type.DELETE) {
					builder.delete(operation.getPosition(), operation.getPosition() + operation.getLength());
				}
			}

			@Override
			public void onError(Throwable t) {

			}

			@Override
			public void onCompleted() {
				System.out.println("Closed a client connection");
			}
		};
	}

	@Override
	public void sync(Client client, StreamObserver<Operation> responseObserver) {
		this.observers.put(client.getId(), responseObserver);
		StateSpace clientState = this.operationsManager.syncClient(client);

		String document = this.builder.toString();
		int length = document.length();
		Operation syncOperation = Operation.newBuilder().setMessage(document).setType(Type.INSERT).setPosition(0)
				.setLength(length).setState(clientState).setClientId(client.getId()).setSync(true).build();
		responseObserver.onNext(syncOperation);
	}
	
	@Override
	public void close(Client client, StreamObserver<Client> responseObserver) {
		this.observers.get(client.getId()).onCompleted();
		this.observers.remove(client.getId());
		this.operationsManager.removeClient(client);
		
		responseObserver.onNext(client);
		responseObserver.onCompleted();
	}
}