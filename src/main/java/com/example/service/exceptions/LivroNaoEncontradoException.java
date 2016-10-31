package com.example.service.exceptions;

public class LivroNaoEncontradoException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LivroNaoEncontradoException(String msg){
		super(msg);
	}
	
	public LivroNaoEncontradoException(String msg, Throwable causa){
		super(msg,causa);
	}
}
