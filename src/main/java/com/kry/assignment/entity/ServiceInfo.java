package com.kry.assignment.entity;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.kry.assignment.request.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceInfo {
	@Id
	private String url;	
	private String name;
	private Status status;
	private Instant addedTime;
	private Instant updatedTime;

}
