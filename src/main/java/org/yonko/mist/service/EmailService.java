package org.yonko.mist.service;

import javax.mail.MessagingException;

import org.yonko.mist.domain.Employee;

public interface EmailService {
	
	void sendSimpleEmployeeEmail(String to, Employee employee);
	void sendAttachmentEmployeeEmail(String to, Employee employee) throws MessagingException;
	void sendRichMessageEmployeeEmail(String to, Employee employee) throws MessagingException;
	void sendSheduled() throws MessagingException;
}
