package com.kry.assignment.request;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateServiceRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3937260473224286325L;
	private String url;
	@NotEmpty(message = "VALID_NAME")
	private String name;
	private String status;
}
