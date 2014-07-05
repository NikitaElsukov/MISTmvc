package org.yonko.mist;

import java.io.IOException;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.yonko.mist.jmx.JmxClient;

public class JmxClientMain {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:WEB-INF/spring/JMX-config.xml");
		JmxClient jmxClient = (JmxClient) context.getBean("jmxClient");
		try {
			System.out.println("Количество MBean = " + jmxClient.getMBeanCount());
			try {
				System.out.println(jmxClient.getmBeanServerClient().getAttribute(
						new ObjectName("employee:name=EmployeeController"), "employeesPerPage"));
			} catch (AttributeNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstanceNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedObjectNameException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MBeanException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ReflectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			System.out.println("Ошибка подключения к серверу MBean");
			e.printStackTrace();
		}
	}

}
