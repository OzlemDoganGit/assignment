package com.kry.assignment.service;

import java.util.List;

import com.kry.assignment.entity.ServiceInfo;
import com.kry.assignment.request.AddServiceRequest;
import com.kry.assignment.request.UpdateServiceRequest;

public interface ServiceInt {

	public ServiceInfo save(AddServiceRequest request);

	public ServiceInfo updateService(UpdateServiceRequest request);

	public void removeService(String id);

	public void checkAndUpdateServiceStatus();
	
	public List<ServiceInfo> getAllServices();

}
