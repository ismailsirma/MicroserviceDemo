package com.sirmam.hr.events;

import com.sirmam.hr.domain.TcKimlikNo;

public abstract class EmployeeEvent {
	private TcKimlikNo kimlikNo;

	public EmployeeEvent(TcKimlikNo kimlikNo) {
		this.kimlikNo = kimlikNo;
	}

	public TcKimlikNo getKimlikNo() {
		return kimlikNo;
	}
	
}
