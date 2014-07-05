package org.yonko.mist.service;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.yonko.mist.domain.Employee;

@Service("mailService")
public class EmailServiceImpl implements EmailService{
	
	@Autowired
	private JavaMailSender mailSender;

	@Override
	@Async
	public void sendSimpleEmployeeEmail(String to, Employee employee) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		String employeeName = employee.getEmployeeFIO();
		message.setFrom("nikita.elsukov.sa@mail.ru");
		message.setTo(to);
		message.setSubject("Добавили нового сотрудника: " + employeeName);
		
		message.setText(employeeName + " характеристики: " +
				employee.getBirthDate() + ", " +
				employee.getEmployeeAge() + ", " + 
				employee.getDescription());
		
		mailSender.send(message);
		
	}

	@Override
	@Async
	public void sendAttachmentEmployeeEmail(String to, Employee employee) throws MessagingException {
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
		
		String employeeName = employee.getEmployeeFIO();
		messageHelper.setFrom("nikita.elsukov.sa@mail.ru");
		messageHelper.setTo(to);
		messageHelper.setSubject("Добавили нового сотрудника: " + employeeName);
		
		messageHelper.setText(employeeName + " характеристики:" +
				employee.getBirthDate() + ", " +
				employee.getEmployeeAge() + ", " + 
				employee.getDescription());
		
		ClassPathResource image = new ClassPathResource("resources/doodl.png");
		messageHelper.addAttachment("Doodl.png", image);
		messageHelper.addInline("employee", image);
		mailSender.send(message);
	}
	
	@Override
	@Async
	public void sendRichMessageEmployeeEmail(String to, Employee employee) throws MessagingException {
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
		
		String employeeName = employee.getEmployeeFIO();
		messageHelper.setFrom("nikita.elsukov.sa@mail.ru");
		messageHelper.setTo(to);
		messageHelper.setSubject("Добавили нового сотрудника: " + employeeName);
		
		messageHelper.setText("<html><body><img src='cid:employeeLogo'>" + 
				"<h4>" + employeeName + " характеристики:" + "</h4></br>" +
				"<i>" + employee.getBirthDate() + ", " +
				employee.getEmployeeAge() + ", " + 
				employee.getDescription() + "</i></body></html>", true);
		
		ClassPathResource image = new ClassPathResource("resources/doodl.png");
		messageHelper.addAttachment("Doodl.png", image);
		messageHelper.addInline("employee", image);
		mailSender.send(message);
	}
	
	//@Scheduled(fixedRate = 10000)
	public void sendSheduled() throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
		
		Employee employee = new Employee();
		employee.setBirthDate(new Date());
		employee.setDescription("Тестовое письмо");
		employee.setEmployeeFIO("Сервер");
		employee.setEmployeeAge(10);
		String employeeName = employee.getEmployeeFIO();
		
		messageHelper.setFrom("nikita.elsukov.sa@mail.ru");
		messageHelper.setTo("nikita.elsukov.sa@mail.ru");
		messageHelper.setSubject("Добавили нового сотрудника: " + employeeName);
		
		messageHelper.setText(employeeName + " характеристики:" +
				employee.getBirthDate() + ", " +
				employee.getEmployeeAge() + ", " + 
				employee.getDescription());
		
		ClassPathResource image = new ClassPathResource("resources/doodl.png");
		messageHelper.addAttachment("Doodl.png", image);
		
		mailSender.send(message);
	}

}
