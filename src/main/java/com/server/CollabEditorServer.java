package com.server;

import java.io.IOException;

import com.services.CollabServiceImpl;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class CollabEditorServer {
	public static void main(String[] args) throws IOException, InterruptedException {
		Server server = ServerBuilder.forPort(9090).addService(new CollabServiceImpl()).build();
		server.start();
		server.awaitTermination();
	}
}