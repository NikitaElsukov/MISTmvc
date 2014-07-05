package org.yonko.mist.service;

import java.util.List;

import org.yonko.mist.domain.Employee;
import org.yonko.mist.domain.Passport;

public interface EmployeeService {
	
	void addEmployee(Employee employee);
	void removeEmployee(Integer id);
	List<Employee> getAllEmployees();
	Employee getEmployeeById(Integer id);
	void updateDescription(Employee employee);
	List<Employee> searchEmployee(String fio);
	public void updatePassport(Passport passport);
	public Passport getPassportById(Integer id);
	public List<Passport> getAllPassports();	
	public void removePassport(Integer id);
	public void addPassport(Passport passport);

}
