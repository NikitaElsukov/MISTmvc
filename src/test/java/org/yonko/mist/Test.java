package org.yonko.mist;

import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.yonko.mist.dao.EmployeeDAO;
import org.yonko.mist.dao.MistEmployeeDAO;
import org.yonko.mist.domain.Employee;
import org.yonko.mist.domain.Passport;
import org.yonko.mist.service.EmployeeService;
import org.yonko.mist.service.MistEmployeeService;

public class Test {

	
	public static void main(String[] args) {
		ApplicationContext context = new FileSystemXmlApplicationContext("C:/Java_Development/spring_workspace/MISTmvc/src/main/webapp/WEB-INF/spring/root-context.xml");
		EmployeeService employeeService = (EmployeeService) context.getBean("mistEmployeeService");
		ArrayList<Employee> employees = (ArrayList<Employee>) employeeService.getAllEmployees();
		for (Employee e : employees) 
			System.out.println(e);
		//employeeService.removeEmployee(7);
		Passport passport = employeeService.getPassportById(1);
		System.out.println(passport.getIssued());
	}
}
