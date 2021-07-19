package com.sirmam.hr.domain;

import com.sirmam.hr.domain.annotation.ValueObject;

@ValueObject
public class BirthYear {
	private final int value;

	private BirthYear(int value) {
		this.value = value;
	}
	
	public static BirthYear valueOf(int value) {
		if(value < 1950)
			throw new IllegalArgumentException("Year must be larger than 1950.");
		
		return new BirthYear(value);
	}

	public int getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + value;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BirthYear other = (BirthYear) obj;
		if (value != other.value)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BirthYear [value=" + value + "]";
	}
	
	
}
