package com.client;

import java.util.UUID;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import com.proto.CollabEditor.Client;
import com.proto.CollabEditor.Operation;
import com.proto.CollabEditor.Type;
import com.proto.CollabServiceGrpc;
import com.proto.CollabServiceGrpc.CollabServiceBlockingStub;
import com.proto.CollabServiceGrpc.CollabServiceStub;
import com.utils.operations.ClientOperationManager;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class CollabEditorClient implements DocumentListener {
	Client client;
	CollabServiceStub collabService;
	CollabServiceBlockingStub collabServiceBlocking;
	ClientOperationManager operationManager;
	Document document;
	StreamObserver<Operation> serverStream;

	public CollabEditorClient(Document document) {
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090).usePlaintext().build();
		this.collabServiceBlocking = CollabServiceGrpc.newBlockingStub(channel);
		this.collabService = CollabServiceGrpc.newStub(channel);
		this.client = Client.newBuilder().setId(UUID.randomUUID().toString()).build();
		this.operationManager = new ClientOperationManager(-1);
		this.document = document;
	}

	public void run() throws BadLocationException {
		this.client = this.collabServiceBlocking.sync(this.client);
		operationManager.setServerState(client.getServerState());
		document.insertString(0, client.getDocument(), null);
		CollabEditorClient self = this;

		StreamObserver<Operation> request = this.collabService.send(new StreamObserver<Operation>() {
			@Override
			public void onNext(Operation operation) {
				operation = operationManager.merge(operation);
				if (operation.getType() == Type.INSERT) {
					try {
						document.removeDocumentListener(self);
						document.insertString(operation.getPosition(), operation.getMessage(), null);
						document.addDocumentListener(self);
					} catch (BadLocationException e) {

					}
				} else if (operation.getType() == Type.DELETE) {
					try {
						document.removeDocumentListener(self);
						document.remove(operation.getPosition(), operation.getLength());
						document.addDocumentListener(self);
					} catch (BadLocationException e) {
						e.printStackTrace();
					}
				}
			}

			@Override
			public void onError(Throwable t) {
				System.out.println(t.getMessage());
			}

			@Override
			public void onCompleted() {
				System.out.println("Stream Closed");
			}
		});

		this.serverStream = request;
		document.addDocumentListener(this);
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		int position = e.getOffset();
		int length = e.getLength();
		try {
			String string = this.document.getText(position, length);
			Operation operation = Operation.newBuilder().setMessage(string).setType(Type.INSERT).setPosition(position)
					.setLength(length).setClientId(this.client.getId()).setSequenceId(0).build();

			operation = this.operationManager.apply(operation);
			this.serverStream.onNext(operation);
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		int position = e.getOffset();
		int length = e.getLength();
		Operation operation = Operation.newBuilder().setMessage("").setType(Type.DELETE).setPosition(position)
				.setLength(length).setClientId(this.client.getId()).setSequenceId(0).build();
		operation = this.operationManager.apply(operation);
		this.serverStream.onNext(operation);
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
	}

	public static void main(String[] args) {
		JFrame application = new JFrame("Collaborative Document Editor");
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTextArea t = new JTextArea();
		Document document = t.getDocument();

		JScrollPane scrollPane = new JScrollPane(t);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		application.add(scrollPane);
		application.setSize(500, 400);
		application.setResizable(true);
		application.setVisible(true);

		try {
			new CollabEditorClient(document).run();
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
}

class ClientStreamObserver implements StreamObserver<Client> {

	@Override
	public void onNext(Client value) {
		throw new Error("Unimplemented Error");
	}

	@Override
	public void onError(Throwable t) {
		System.out.println(t.getMessage());
	}

	@Override
	public void onCompleted() {
		System.out.println("Completed");
	}

}
