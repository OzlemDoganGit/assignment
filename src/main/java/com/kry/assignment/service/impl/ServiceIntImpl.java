package com.kry.assignment.service.impl;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kry.assignment.entity.ServiceInfo;
import com.kry.assignment.repository.ServiceInfoRepository;
import com.kry.assignment.request.AddServiceRequest;
import com.kry.assignment.request.Status;
import com.kry.assignment.request.UpdateServiceRequest;
import com.kry.assignment.service.ServiceInt;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
public class ServiceIntImpl implements ServiceInt {

	@Autowired
	ServiceInfoRepository serviceInfoRepo;

	@Override
	public ServiceInfo save(AddServiceRequest request) {
		if (isValidURL(request.getUrl())) {
			return serviceInfoRepo.save(ServiceInfo.builder().name(request.getName()).url(request.getUrl())
					.addedTime(Instant.now()).status(Status.OK).build());
		} else {
			throw new IllegalArgumentException("Not a valid URL");
		}

	}

	private boolean isValidURL(String url) {
		String[] schemes = { "http", "https" };
		UrlValidator urlValidator = new UrlValidator(schemes);
		return urlValidator.isValid(url);
	}

	@Override
	public ServiceInfo updateService(UpdateServiceRequest request) {
		if (isValidURL(request.getUrl())) {
			Optional<ServiceInfo> serviceInfo = serviceInfoRepo.findById((request.getUrl()));
			if (serviceInfo.isPresent()) {
				ServiceInfo updatedServiceInfo = updateServiceInfo(request, serviceInfo.get());
				return serviceInfoRepo.save(updatedServiceInfo);
			} else {
				throw new IllegalArgumentException("Service does not exists");
			}
		} else {
			throw new IllegalArgumentException("Not a valid URL");
		}
	}

	private ServiceInfo updateServiceInfo(UpdateServiceRequest request, ServiceInfo serviceInfo) {
		serviceInfo.setName(request.getName());
		serviceInfo.setUrl(request.getUrl());
		serviceInfo.setStatus(Status.valueOf(request.getStatus()));
		serviceInfo.setUpdatedTime(Instant.now());
		return serviceInfo;
	}

	@Override
	public void removeService(String id) {
		Optional<ServiceInfo> serviceInfo = serviceInfoRepo.findById(id);
		if (serviceInfo.isPresent()) {
			serviceInfoRepo.delete(serviceInfo.get());
		} else {
			throw new IllegalArgumentException("Service does not exists");
		}
	}

	@Override
	public void checkAndUpdateServiceStatus() {
		List<ServiceInfo> serviceInfoList = serviceInfoRepo.findAll();
		OkHttpClient client = new OkHttpClient();
		serviceInfoList.stream().forEach(e -> {
			Request request = new Request.Builder().url(e.getUrl()).build();
			Call call = client.newCall(request);
			call.enqueue(new Callback() {
				public void onResponse(Call call, Response response) throws IOException {
					e.setStatus(Status.OK);
					e.setUpdatedTime(Instant.now());
					serviceInfoRepo.save(e);
				}

				public void onFailure(Call call, IOException exp) {
					e.setStatus(Status.FAIL);
					serviceInfoRepo.save(e);
				}
			});
		});
	}

	@Override
	public List<ServiceInfo> getAllServices() {
		return serviceInfoRepo.findAll();
	}

}
