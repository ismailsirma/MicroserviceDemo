package com.sirmam.hr.events;

import com.sirmam.hr.domain.TcKimlikNo;

public class EmployeeHiredEvent extends EmployeeEvent {

	public EmployeeHiredEvent(TcKimlikNo kimlikNo) {
		super(kimlikNo);
	}
	
}
