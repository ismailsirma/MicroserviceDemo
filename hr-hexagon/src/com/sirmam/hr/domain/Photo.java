package com.sirmam.hr.domain;

import java.util.Objects;

import com.sirmam.hr.domain.annotation.ValueObject;

@ValueObject
public class Photo {
	private final byte[] data;

	private Photo(byte[] data) {
		this.data = data;
	}
	
	public static Photo Of(byte[] data) {
		Objects.requireNonNull(data);
		return new Photo(data);
	}

	public byte[] getData() {
		return data;
	}
	
	
}
