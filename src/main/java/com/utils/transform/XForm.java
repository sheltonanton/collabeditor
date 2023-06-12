package com.utils.transform;

import java.util.ArrayList;
import java.util.List;

import com.proto.CollabEditor.Operation;
import com.proto.CollabEditor.Type;

public class XForm {
	public static List<Operation> transform(Operation o1, Operation o2) {
		List<Operation> transformed = new ArrayList<>();

		if (o1.getType() == Type.INSERT && o2.getType() == Type.INSERT) {
			if (o1.getPosition() > o2.getPosition()) {
				o1 = o1.toBuilder().setPosition(o1.getPosition() + o2.getLength()).build();
			} else {
				o2 = o2.toBuilder().setPosition(o2.getPosition() + o1.getLength()).build();
			}
		} else if (o1.getType() == Type.INSERT && o2.getType() == Type.DELETE) {
			if (o1.getPosition() > o2.getPosition()) {
				o1 = o1.toBuilder().setPosition(o1.getPosition() - o2.getLength()).build();
			} else {
				o2 = o2.toBuilder().setPosition(o2.getPosition() + o1.getLength()).build();
			}
		} else if (o1.getType() == Type.DELETE && o2.getType() == Type.INSERT) {
			if (o1.getPosition() > o2.getPosition()) {
				o1 = o1.toBuilder().setPosition(o1.getPosition() + o2.getLength()).build();
			} else {
				o2 = o2.toBuilder().setPosition(o2.getPosition() - o1.getLength()).build();
			}
		} else if (o1.getType() == Type.DELETE && o2.getType() == Type.DELETE) {
			if (o1.getPosition() > o2.getPosition()) {
				o1 = o1.toBuilder().setPosition(o1.getPosition() - o2.getLength()).build();
			} else if (o1.getPosition() < o2.getPosition()) {
				o2 = o2.toBuilder().setPosition(o2.getPosition() - o1.getLength()).build();
			} else {
				o1 = o1.toBuilder().setLength(0).build();
			}
		}

		transformed.add(o1);
		transformed.add(o2);
		return transformed;
	}
}
