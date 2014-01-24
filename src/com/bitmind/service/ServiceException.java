package com.bitmind.service;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String serviceMessage;

	public String getServiceMessage() {
		return serviceMessage;
	}

	public void setServiceMessage(String serviceMessage) {
		this.serviceMessage = serviceMessage;
	}

	public ServiceException(String serviceMessage) {
		super();
		this.serviceMessage = serviceMessage;
	}

}
