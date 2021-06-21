package com.kry.assignment.request;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddServiceRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6412041266800261251L;
	@NotEmpty(message = "VALID_NAME")
	private String name;
	private String url;
}
