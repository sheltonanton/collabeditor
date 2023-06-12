package com.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.54.1)",
    comments = "Source: com/proto/CollabEditor.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class CollabServiceGrpc {

  private CollabServiceGrpc() {}

  public static final String SERVICE_NAME = "com.proto.CollabService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.proto.CollabEditor.Operation,
      com.proto.CollabEditor.Operation> getSendMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "send",
      requestType = com.proto.CollabEditor.Operation.class,
      responseType = com.proto.CollabEditor.Operation.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.proto.CollabEditor.Operation,
      com.proto.CollabEditor.Operation> getSendMethod() {
    io.grpc.MethodDescriptor<com.proto.CollabEditor.Operation, com.proto.CollabEditor.Operation> getSendMethod;
    if ((getSendMethod = CollabServiceGrpc.getSendMethod) == null) {
      synchronized (CollabServiceGrpc.class) {
        if ((getSendMethod = CollabServiceGrpc.getSendMethod) == null) {
          CollabServiceGrpc.getSendMethod = getSendMethod =
              io.grpc.MethodDescriptor.<com.proto.CollabEditor.Operation, com.proto.CollabEditor.Operation>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "send"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.proto.CollabEditor.Operation.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.proto.CollabEditor.Operation.getDefaultInstance()))
              .setSchemaDescriptor(new CollabServiceMethodDescriptorSupplier("send"))
              .build();
        }
      }
    }
    return getSendMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.proto.CollabEditor.Client,
      com.proto.CollabEditor.Client> getSyncMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "sync",
      requestType = com.proto.CollabEditor.Client.class,
      responseType = com.proto.CollabEditor.Client.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.proto.CollabEditor.Client,
      com.proto.CollabEditor.Client> getSyncMethod() {
    io.grpc.MethodDescriptor<com.proto.CollabEditor.Client, com.proto.CollabEditor.Client> getSyncMethod;
    if ((getSyncMethod = CollabServiceGrpc.getSyncMethod) == null) {
      synchronized (CollabServiceGrpc.class) {
        if ((getSyncMethod = CollabServiceGrpc.getSyncMethod) == null) {
          CollabServiceGrpc.getSyncMethod = getSyncMethod =
              io.grpc.MethodDescriptor.<com.proto.CollabEditor.Client, com.proto.CollabEditor.Client>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "sync"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.proto.CollabEditor.Client.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.proto.CollabEditor.Client.getDefaultInstance()))
              .setSchemaDescriptor(new CollabServiceMethodDescriptorSupplier("sync"))
              .build();
        }
      }
    }
    return getSyncMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CollabServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CollabServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CollabServiceStub>() {
        @java.lang.Override
        public CollabServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CollabServiceStub(channel, callOptions);
        }
      };
    return CollabServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CollabServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CollabServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CollabServiceBlockingStub>() {
        @java.lang.Override
        public CollabServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CollabServiceBlockingStub(channel, callOptions);
        }
      };
    return CollabServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CollabServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CollabServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CollabServiceFutureStub>() {
        @java.lang.Override
        public CollabServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CollabServiceFutureStub(channel, callOptions);
        }
      };
    return CollabServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default io.grpc.stub.StreamObserver<com.proto.CollabEditor.Operation> send(
        io.grpc.stub.StreamObserver<com.proto.CollabEditor.Operation> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getSendMethod(), responseObserver);
    }

    /**
     */
    default void sync(com.proto.CollabEditor.Client request,
        io.grpc.stub.StreamObserver<com.proto.CollabEditor.Client> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSyncMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service CollabService.
   */
  public static abstract class CollabServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return CollabServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service CollabService.
   */
  public static final class CollabServiceStub
      extends io.grpc.stub.AbstractAsyncStub<CollabServiceStub> {
    private CollabServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CollabServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CollabServiceStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.proto.CollabEditor.Operation> send(
        io.grpc.stub.StreamObserver<com.proto.CollabEditor.Operation> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getSendMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public void sync(com.proto.CollabEditor.Client request,
        io.grpc.stub.StreamObserver<com.proto.CollabEditor.Client> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSyncMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service CollabService.
   */
  public static final class CollabServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<CollabServiceBlockingStub> {
    private CollabServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CollabServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CollabServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.proto.CollabEditor.Client sync(com.proto.CollabEditor.Client request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSyncMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service CollabService.
   */
  public static final class CollabServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<CollabServiceFutureStub> {
    private CollabServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CollabServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CollabServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.proto.CollabEditor.Client> sync(
        com.proto.CollabEditor.Client request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSyncMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SYNC = 0;
  private static final int METHODID_SEND = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SYNC:
          serviceImpl.sync((com.proto.CollabEditor.Client) request,
              (io.grpc.stub.StreamObserver<com.proto.CollabEditor.Client>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEND:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.send(
              (io.grpc.stub.StreamObserver<com.proto.CollabEditor.Operation>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getSendMethod(),
          io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
            new MethodHandlers<
              com.proto.CollabEditor.Operation,
              com.proto.CollabEditor.Operation>(
                service, METHODID_SEND)))
        .addMethod(
          getSyncMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.proto.CollabEditor.Client,
              com.proto.CollabEditor.Client>(
                service, METHODID_SYNC)))
        .build();
  }

  private static abstract class CollabServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CollabServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.proto.CollabEditor.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CollabService");
    }
  }

  private static final class CollabServiceFileDescriptorSupplier
      extends CollabServiceBaseDescriptorSupplier {
    CollabServiceFileDescriptorSupplier() {}
  }

  private static final class CollabServiceMethodDescriptorSupplier
      extends CollabServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    CollabServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (CollabServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CollabServiceFileDescriptorSupplier())
              .addMethod(getSendMethod())
              .addMethod(getSyncMethod())
              .build();
        }
      }
    }
    return result;
  }
}
