package com.sirmam.hr.domain;

import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;

import com.sirmam.hr.domain.annotation.ValueObject;

// Value Object > no identity, immutable
@ValueObject
public class TcKimlikNo {
	private final String value;

	private static final Map<String, TcKimlikNo> identityCache = new WeakHashMap<>();
	
	private TcKimlikNo(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	public static TcKimlikNo valueOf(String value) {
		// validation
		if(!isValid(value))
			throw new IllegalArgumentException("This is not a valid identity no!");
		// object pooling -> caching 
		var tcKimlikNo = identityCache.get(value);
		if(Objects.nonNull(tcKimlikNo))
			return tcKimlikNo;
		
		tcKimlikNo = new TcKimlikNo(value);
		identityCache.put(value,tcKimlikNo);
		
		return tcKimlikNo;
	}

	private static boolean isValid(String value) {
		// TODO Auto-generated method stub
		 if (value == null) 
			 return false;
	       
		 if(!value.matches("^\\d{11}$")) {
			 return false;
		 }
		 
		 int[] digits = new int[11];
		 for(int i = 0; i < digits.length; i++) {
			 digits[i] = value.charAt(i) - '0';
		 }
		 
		 int x = digits[0];
		 int y = digits[1];
		 for(int i = 1; i < 5 ; i++) {
			 x += digits[2* i];
		 }
		 for(int i = 2; i < 4 ; i++) {
			 y += digits[2*  i - 1];
		 }		 
		 int c1 = 7 * x - y;
		 if(c1 % 10 != digits[9]) {
			 return false;
		 }
		 int c2 = 0;
		 for(int i = 0; i < 10 ; ++i) {
			 c2 += digits[i];
		 }	
		 if(c2 % 10 != digits[10]) {
			 return false;
		 }
		 return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		TcKimlikNo other = (TcKimlikNo) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TcKimlikNo [value=" + value + "]";
	}
	
	
	
}
