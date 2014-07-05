package org.yonko.mist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yonko.mist.dao.EmployeeDAO;
import org.yonko.mist.domain.Employee;
import org.yonko.mist.domain.Passport;

@Service
public class MistEmployeeService implements EmployeeService {
	
	@Autowired
	private EmployeeDAO employeeDAO;

	@Transactional
	public void addEmployee(Employee employee) {
		employeeDAO.addEmployee(employee);
	}

	@Transactional
	public void removeEmployee(Integer id) {
		employeeDAO.removeEmployee(id);
	}

	@Transactional
	public List<Employee> getAllEmployees() {
		return employeeDAO.getAllEmployees();
	}

	@Transactional
	public Employee getEmployeeById(Integer id) {
		return employeeDAO.getEmployeeById(id);
	}

	@Transactional
	public void updateDescription(Employee employee) {
		employeeDAO.updateDescription(employee);
	}

	@Transactional
	public List<Employee> searchEmployee(String fio) {
		return employeeDAO.searchEmployee(fio);
	}

	@Transactional
	public void addPassport(Passport passport) {
		employeeDAO.addPassport(passport);
	}
	
	@Transactional
	public Passport getPassportById(Integer id) {
		return employeeDAO.getPassportById(id);
	}

	@Transactional
	public List<Passport> getAllPassports() {
		return employeeDAO.getAllPassports();
	}

	@Transactional
	public void removePassport(Integer id) {
		employeeDAO.removePassport(id);
	}

	@Transactional
	public void updatePassport(Passport passport) {
		employeeDAO.updatePassport(passport);
	}
}
