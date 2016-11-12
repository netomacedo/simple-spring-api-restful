package com.example.service.exceptions;

public class AutorNaoEncontradoException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AutorNaoEncontradoException(String msg){
		super(msg);
	}
	
	public AutorNaoEncontradoException(String msg, Throwable causa){
		super(msg,causa);
	}
}
