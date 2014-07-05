package org.yonko.mist;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.yonko.mist.domain.Employee;
import org.yonko.mist.service.EmailService;
import org.yonko.mist.service.EmailServiceImpl;

public class TestMail {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:WEB-INF/spring/root-context.xml");
		EmailService emailService = (EmailService) context.getBean("mailService");
		Employee employee = new Employee();
		employee.setBirthDate(new Date());
		employee.setDescription("Тестовое письмо");
		employee.setEmployeeFIO("Сервер");
		employee.setEmployeeAge(10);
		emailService.sendSimpleEmployeeEmail("takahasi10@mail.ru", employee);
	}
}
