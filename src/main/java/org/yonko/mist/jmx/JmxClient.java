package org.yonko.mist.jmx;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;


public class JmxClient {
	
	private MBeanServerConnection mBeanServerClient;

	public MBeanServerConnection getmBeanServerClient() {
		return mBeanServerClient;
	}

	public void setmBeanServerClient(MBeanServerConnection mBeanServerClient) {
		this.mBeanServerClient = mBeanServerClient;
	}
	
	public Integer getMBeanCount() throws IOException {
		return mBeanServerClient.getMBeanCount();
	}
	
	public Set<ObjectName> mBeanNames() throws IOException {
		return mBeanServerClient.queryNames(null, null);
	}

}
