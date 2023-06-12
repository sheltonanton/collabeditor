package com.utils.operations;

import com.proto.CollabEditor.Operation;

public interface OperationsManager {
	public Operation merge(Operation o);

	public Operation apply(Operation o);
}
