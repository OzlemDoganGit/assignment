package com.kry.assignment.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.kry.assignment.entity.ServiceInfo;
import com.kry.assignment.request.AddServiceRequest;
import com.kry.assignment.request.DeleteServiceRequest;
import com.kry.assignment.request.Status;
import com.kry.assignment.service.impl.ServiceIntImpl;

@ExtendWith(MockitoExtension.class)
public class ServiceControllerTest {

	@InjectMocks
	ServiceController serviceController;

	@Mock
	ServiceIntImpl service;


	@Test
	void testGetService() throws Exception {
		List<ServiceInfo> result = new ArrayList<>();
		ServiceInfo serviceInfo = ServiceInfo.builder().name("serviceA").url("https://abcd.com").status(Status.OK)
				.build();
		result.add(serviceInfo);
		Mockito.when(service.getAllServices()).thenReturn(result);
		ResponseEntity<List<ServiceInfo>> response = serviceController.getAllServices();
		assertNotNull(response);

	}

	@Test
	void testAddService() throws Exception {
		String name = "Test";
		String url = "https://abcd.com";
		Mockito.when(service.save(new AddServiceRequest(name, url))).thenReturn(Mockito.any());
		ResponseEntity<ServiceInfo> response = serviceController.addService(new AddServiceRequest(name, url));
		assertNotNull(response);

	}

	@Test
	void testInvalidUrl() throws Exception {
		String name = "Test";
		String url = "/abcd.com";
		ResponseEntity<ServiceInfo> response = serviceController.addService(new AddServiceRequest(name, url));
		assertNotNull(response);
	}

	@Test
	void testRemoveService() throws Exception {
		String url = "https://abcd.com";
		service.removeService(url);
		serviceController.deleteServiceById(new DeleteServiceRequest(url));
	}
}
