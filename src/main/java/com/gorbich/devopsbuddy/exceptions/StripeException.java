package com.gorbich.devopsbuddy.exceptions;

public class StripeException extends RuntimeException {
	
	public StripeException (Throwable e) {
		super(e);
	}

}
