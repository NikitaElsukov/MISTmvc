package org.yonko.mist.service.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.JmsUtils;
import org.springframework.stereotype.Service;
import org.yonko.mist.domain.Employee;

@Service
public class EmployeeAlertServiceImpl implements EmployeeAlertService {
	
	@Autowired
	private JmsTemplate jmsTemplate;

	@Override
	public void sendEmployeeAlert(final Employee employee) {
		jmsTemplate.send(new MessageCreator() {   //jmsTemplate определяет приемник по умолчанию в 
			@Override							  // файле определения бина в activemq-config.xml
			public Message createMessage(Session session) throws JMSException {
				return session.createObjectMessage(employee);
			}
		});
	}

	@Override
	public Employee getAlert() {
		try {
			ObjectMessage object = (ObjectMessage) jmsTemplate.receive();
			return (Employee) object.getObject();
		} catch (JMSException e) {
			throw JmsUtils.convertJmsAccessException(e);
		}
	}

}
