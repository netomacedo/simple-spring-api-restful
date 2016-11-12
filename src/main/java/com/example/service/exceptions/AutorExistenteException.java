package com.example.service.exceptions;

public class AutorExistenteException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AutorExistenteException(String msg){
		super(msg);
	}
	
	public AutorExistenteException(String msg, Throwable causa){
		super(msg,causa);
	}
}
