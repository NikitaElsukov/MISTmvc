package org.yonko.mist.jmx;

import javax.management.Notification;
import javax.management.NotificationListener;

public class EmployeeNotificationListener implements NotificationListener{

	@Override
	public void handleNotification(Notification notification, Object handback) {
		System.out.println(notification.getMessage());
	}

}
