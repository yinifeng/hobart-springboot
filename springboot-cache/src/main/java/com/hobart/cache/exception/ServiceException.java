package com.hobart.cache.exception;

public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = 5774520667722254971L;

	public ServiceException(String reason) {
		super(reason);
	}
}
