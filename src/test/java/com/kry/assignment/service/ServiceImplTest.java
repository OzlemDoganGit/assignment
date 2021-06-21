package com.kry.assignment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;

import java.time.Instant;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.kry.assignment.entity.ServiceInfo;
import com.kry.assignment.repository.ServiceInfoRepository;
import com.kry.assignment.request.AddServiceRequest;
import com.kry.assignment.request.Status;
import com.kry.assignment.request.UpdateServiceRequest;
import com.kry.assignment.service.impl.ServiceIntImpl;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class ServiceImplTest {

	@InjectMocks
	private ServiceIntImpl serviceImpl;
	@Mock
	private ServiceInfoRepository serviceInfoRepository;

	@Test
	void testSave() {
		String name = "Test";
		String url = "http://www.abcd.com.tr";
		ServiceInfo repoRes = new ServiceInfo();
		Mockito.when(serviceInfoRepository.save(Mockito.any())).thenReturn(repoRes);
		ServiceInfo result = serviceImpl.save(new AddServiceRequest(name, url));
		assertEquals(repoRes, result);

	}

	@Test
	void testUpdateService() {
		String name = "Test";
		String url = "http://www.abcd.com.tr";
		String status = "OK";
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> serviceImpl.updateService(new UpdateServiceRequest(name, url, status)));
		assertTrue(exception.getMessage().contains("Not a valid URL"));
	}

	@Test
	void testCheckAndUpdateServiceStatus() {
		serviceImpl.checkAndUpdateServiceStatus();
		Mockito.verify(serviceInfoRepository, times(1)).findAll();
	}

	@Test
	void testFindAll() {
		serviceImpl.getAllServices();
		Mockito.verify(serviceInfoRepository, times(1)).findAll();
	}

}
