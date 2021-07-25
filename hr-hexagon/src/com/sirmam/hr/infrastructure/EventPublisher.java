package com.sirmam.hr.infrastructure;

import com.sirmam.hr.events.EmployeeEvent;

public interface EventPublisher {

	void publish(EmployeeEvent event);

}
