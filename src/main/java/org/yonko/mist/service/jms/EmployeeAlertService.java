package org.yonko.mist.service.jms;

import org.yonko.mist.domain.Employee;

public interface EmployeeAlertService {
	
	void sendEmployeeAlert(Employee employee);
	
	Employee getAlert();
}
