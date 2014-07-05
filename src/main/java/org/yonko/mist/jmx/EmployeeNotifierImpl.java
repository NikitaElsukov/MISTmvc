package org.yonko.mist.jmx;

import javax.management.Notification;

import org.springframework.jmx.export.annotation.ManagedNotification;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.jmx.export.notification.NotificationPublisher;
import org.springframework.jmx.export.notification.NotificationPublisherAware;
import org.springframework.stereotype.Component;

@Component
@ManagedResource("employee:name=EmployeeNotifier")
@ManagedNotification(notificationTypes = "EmployeeNotifier.AnotherEmployee", name = "TODO")
public class EmployeeNotifierImpl implements 
	NotificationPublisherAware, EmployeeNotifier{
	
	private NotificationPublisher notificationPublisher;

	@Override
	public void setNotificationPublisher(NotificationPublisher notificationPublisher) {
		this.notificationPublisher = notificationPublisher;
	}
	
	@Override
	public void notifiAddAnotherEmployee() {
		notificationPublisher.sendNotification(
				new Notification(
						"EmployeeNotifier.AnotherEmployee", this, 0));
	}
}
