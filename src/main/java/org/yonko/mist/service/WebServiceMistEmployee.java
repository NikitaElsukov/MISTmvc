package org.yonko.mist.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
//import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.yonko.mist.domain.Employee;

@Component
@WebService(serviceName = "MistWebService")
public class WebServiceMistEmployee { //extends SpringBeanAutowiringSupport - ѕри использовании класса
																			//вне контейнера Spring
	@Autowired
	@Qualifier("mistEmployeeService")
	EmployeeService mistEmployeeService;
	
	@WebMethod
	void addEmployee(Employee employee) {
		mistEmployeeService.addEmployee(employee);
	}
	
	@WebMethod
	void removeEmployee(Integer id) {
		mistEmployeeService.removeEmployee(id);
	}
	
	@WebMethod
	List<Employee> getAllEmployees() {
		return mistEmployeeService.getAllEmployees();
	}
	
	@WebMethod
	Employee getEmployeeById(Integer id) {
		return mistEmployeeService.getEmployeeById(id);
	}
	
	@WebMethod
	void updateDescription(Employee employee) {
		mistEmployeeService.updateDescription(employee);
	}
	
	@WebMethod
	List<Employee> searchEmployee(String fio) {
		return mistEmployeeService.searchEmployee(fio);
	}

}
