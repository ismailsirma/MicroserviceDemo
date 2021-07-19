package com.sirmam.hr.domain;

import java.util.Objects;

import com.sirmam.hr.domain.annotation.ValueObject;

@ValueObject
public class Money {
	private final double value;
	private final FiatCurrency currency;
	private Money(double value, FiatCurrency currency) {
		this.value = value;
		this.currency = currency;
	}
	
	public static Money of(double value, FiatCurrency currency) {
		if(value < 0.0)
			throw new IllegalArgumentException("Value must be zero or positive");
		Objects.requireNonNull(currency, "Currency must be given");
		return new Money(value, currency);
	}
	
	public static Money of(double value) {
		return Money.of(value, FiatCurrency.TRY);
	}
	
	public double getValue() {
		return value;
	}

	public FiatCurrency getCurrency() {
		return currency;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		long temp;
		temp = Double.doubleToLongBits(value);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Money other = (Money) obj;
		if (currency != other.currency)
			return false;
		if (Double.doubleToLongBits(value) != Double.doubleToLongBits(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Money [value=" + value + ", currency=" + currency + "]";
	}
	
	
}
