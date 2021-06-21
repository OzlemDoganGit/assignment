package com.kry.assignment.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kry.assignment.entity.ServiceInfo;
import com.kry.assignment.request.AddServiceRequest;
import com.kry.assignment.request.DeleteServiceRequest;
import com.kry.assignment.request.UpdateServiceRequest;
import com.kry.assignment.service.ServiceInt;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/service")
@CrossOrigin
@RequiredArgsConstructor
@EnableScheduling
public class ServiceController {

	@Autowired
	private final ServiceInt service;


	@PostMapping()
	@ApiOperation(value = "", notes = "Add new service", nickname = "addService")
	public ResponseEntity<ServiceInfo> addService(@Valid @RequestBody AddServiceRequest request) {
		return new ResponseEntity<>(service.save(request), HttpStatus.OK);
	}
	
	@PostMapping("/checkAndUpdateServiceStatus/")
	@Scheduled(fixedRate = 10000)
	@ApiOperation(value = "", notes = "Add new service", nickname = "addService")
	public ResponseEntity<ServiceInfo> checkAndUpdateServiceStatus() {
		service.checkAndUpdateServiceStatus();
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping()
	@ApiOperation(value = "", notes = "Update the Service", nickname = "updateService")
	public ResponseEntity<ServiceInfo> updateServiceById(@RequestBody UpdateServiceRequest request) {
		return new ResponseEntity<>(service.updateService(request), HttpStatus.OK);
	}

	@GetMapping()
	@ApiOperation(value = "", notes = "Get All Services", nickname = "getServices")
	public ResponseEntity<List<ServiceInfo>> getAllServices() {
		return new ResponseEntity<>(service	.getAllServices(), HttpStatus.OK);
	}

	@DeleteMapping("/**")
	@ApiOperation(value = "urlName", notes = "Delete the service", nickname = "deleteService")
	public void deleteServiceById(@RequestBody DeleteServiceRequest request) {
		service.removeService(request.getUrl());
	}

}
