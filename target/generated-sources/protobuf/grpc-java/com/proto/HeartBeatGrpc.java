package com.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.54.1)",
    comments = "Source: com/proto/CollabEditor.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class HeartBeatGrpc {

  private HeartBeatGrpc() {}

  public static final String SERVICE_NAME = "com.proto.HeartBeat";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.proto.CollabEditor.Client,
      com.proto.CollabEditor.Client> getSignalMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "signal",
      requestType = com.proto.CollabEditor.Client.class,
      responseType = com.proto.CollabEditor.Client.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.proto.CollabEditor.Client,
      com.proto.CollabEditor.Client> getSignalMethod() {
    io.grpc.MethodDescriptor<com.proto.CollabEditor.Client, com.proto.CollabEditor.Client> getSignalMethod;
    if ((getSignalMethod = HeartBeatGrpc.getSignalMethod) == null) {
      synchronized (HeartBeatGrpc.class) {
        if ((getSignalMethod = HeartBeatGrpc.getSignalMethod) == null) {
          HeartBeatGrpc.getSignalMethod = getSignalMethod =
              io.grpc.MethodDescriptor.<com.proto.CollabEditor.Client, com.proto.CollabEditor.Client>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "signal"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.proto.CollabEditor.Client.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.proto.CollabEditor.Client.getDefaultInstance()))
              .setSchemaDescriptor(new HeartBeatMethodDescriptorSupplier("signal"))
              .build();
        }
      }
    }
    return getSignalMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static HeartBeatStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HeartBeatStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HeartBeatStub>() {
        @java.lang.Override
        public HeartBeatStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HeartBeatStub(channel, callOptions);
        }
      };
    return HeartBeatStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static HeartBeatBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HeartBeatBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HeartBeatBlockingStub>() {
        @java.lang.Override
        public HeartBeatBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HeartBeatBlockingStub(channel, callOptions);
        }
      };
    return HeartBeatBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static HeartBeatFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HeartBeatFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HeartBeatFutureStub>() {
        @java.lang.Override
        public HeartBeatFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HeartBeatFutureStub(channel, callOptions);
        }
      };
    return HeartBeatFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void signal(com.proto.CollabEditor.Client request,
        io.grpc.stub.StreamObserver<com.proto.CollabEditor.Client> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSignalMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service HeartBeat.
   */
  public static abstract class HeartBeatImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return HeartBeatGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service HeartBeat.
   */
  public static final class HeartBeatStub
      extends io.grpc.stub.AbstractAsyncStub<HeartBeatStub> {
    private HeartBeatStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HeartBeatStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HeartBeatStub(channel, callOptions);
    }

    /**
     */
    public void signal(com.proto.CollabEditor.Client request,
        io.grpc.stub.StreamObserver<com.proto.CollabEditor.Client> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSignalMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service HeartBeat.
   */
  public static final class HeartBeatBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<HeartBeatBlockingStub> {
    private HeartBeatBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HeartBeatBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HeartBeatBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.proto.CollabEditor.Client signal(com.proto.CollabEditor.Client request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSignalMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service HeartBeat.
   */
  public static final class HeartBeatFutureStub
      extends io.grpc.stub.AbstractFutureStub<HeartBeatFutureStub> {
    private HeartBeatFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HeartBeatFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HeartBeatFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.proto.CollabEditor.Client> signal(
        com.proto.CollabEditor.Client request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSignalMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SIGNAL = 0;

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
        case METHODID_SIGNAL:
          serviceImpl.signal((com.proto.CollabEditor.Client) request,
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
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getSignalMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.proto.CollabEditor.Client,
              com.proto.CollabEditor.Client>(
                service, METHODID_SIGNAL)))
        .build();
  }

  private static abstract class HeartBeatBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    HeartBeatBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.proto.CollabEditor.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("HeartBeat");
    }
  }

  private static final class HeartBeatFileDescriptorSupplier
      extends HeartBeatBaseDescriptorSupplier {
    HeartBeatFileDescriptorSupplier() {}
  }

  private static final class HeartBeatMethodDescriptorSupplier
      extends HeartBeatBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    HeartBeatMethodDescriptorSupplier(String methodName) {
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
      synchronized (HeartBeatGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new HeartBeatFileDescriptorSupplier())
              .addMethod(getSignalMethod())
              .build();
        }
      }
    }
    return result;
  }
}
