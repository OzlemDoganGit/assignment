package com.kry.assignment.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.kry.assignment.request.Status;

public class ServiceInfoTest {

	@Test
	public void testServiceInfo() {
		ServiceInfo serviceInfo = new ServiceInfo();
		serviceInfo.setName("Test");
		serviceInfo.setStatus(Status.OK);
		serviceInfo.setUrl("http://www.abcd.com.tr");
		assertEquals("Test", serviceInfo.getName());
		assertTrue(serviceInfo.getUrl().equals("http://www.abcd.com.tr"));
		assertTrue(serviceInfo.getStatus().equals(Status.OK));

	}
}
