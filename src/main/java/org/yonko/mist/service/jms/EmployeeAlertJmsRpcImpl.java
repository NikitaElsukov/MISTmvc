package org.yonko.mist.service.jms;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.yonko.mist.domain.Employee;

@Service("alertService")
public class EmployeeAlertJmsRpcImpl implements EmployeeAlertService {

	private JavaMailSender mailSender;
	private String alertEmailAddress;
	
	public EmployeeAlertJmsRpcImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public EmployeeAlertJmsRpcImpl(JavaMailSender mailSender, String emailAddress) {
		this.mailSender = mailSender;
		this.alertEmailAddress = emailAddress;
	}
	@Override
	public void sendEmployeeAlert(Employee employee) {
	/*	SimpleMailMessage message = new SimpleMailMessage();
		String employeeFIO = employee.getEmployeeFIO();
		message.setFrom("noreply@mist.org");
		message.setTo(alertEmailAddress);
		message.setSubject("Новый сотрудник: " + employeeFIO);
		message.setText(employeeFIO + "\n Возраст:" + employee.getEmployeeAge());
		mailSender.send(message);*/
		System.out.println("Привет JMS!");
		
	}

	@Override
	public Employee getAlert() {
		// TODO Auto-generated method stub
		return null;
	}

}
