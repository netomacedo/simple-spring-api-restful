package com.example.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.domain.DetalhesError;
import com.example.service.exceptions.AutorExistenteException;
import com.example.service.exceptions.AutorNaoEncontradoException;
import com.example.service.exceptions.LivroNaoEncontradoException;

/*Classe Manipulador de exceptions, 
 * intercepta tipos de exceptions que possam acontecer*/
@ControllerAdvice
public class ResourceExceptionHandler {
	
	//Qualquer lugar do código que lance uma LivroNaoEncontradoException será tratado aqui
	@ExceptionHandler(LivroNaoEncontradoException.class)
	public ResponseEntity<DetalhesError> handleLivroNaoEncontradoException
		(LivroNaoEncontradoException e, HttpServletRequest request){
		
		DetalhesError error = new DetalhesError();
		error.setStatus(404l);
		error.setTitulo("O livro não pode ser encontrado");
		error.setMsgDesenvolvedor("http://socialbooksapi.com/404");
		error.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(AutorExistenteException.class)
	public ResponseEntity<DetalhesError> handleAutorExistenteException
		(AutorExistenteException e, HttpServletRequest request){
		
		DetalhesError error = new DetalhesError();
		error.setStatus(409l);
		error.setTitulo("O autor já existe!!!");
		error.setMsgDesenvolvedor("http://socialbooksapi.com/409");
		error.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}
	
	@ExceptionHandler(AutorNaoEncontradoException.class)
	public ResponseEntity<DetalhesError> handleAutorNaoEncontradoException
		(AutorNaoEncontradoException e, HttpServletRequest request){
		
		DetalhesError error = new DetalhesError();
		error.setStatus(409l);
		error.setTitulo("O autor não encontrado!!!");
		error.setMsgDesenvolvedor("http://socialbooksapi.com/404");
		error.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<DetalhesError> handleDataIntegrityViolationException
		(DataIntegrityViolationException e, HttpServletRequest request){
		
		DetalhesError error = new DetalhesError();
		error.setStatus(400l);
		error.setTitulo("Requisição inválida!!!");
		error.setMsgDesenvolvedor("http://socialbooksapi.com/400");
		error.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<DetalhesError> handleHttpMessageNotReadableException
		(HttpMessageNotReadableException e, HttpServletRequest request){
		
		DetalhesError error = new DetalhesError();
		error.setStatus(400l);
		error.setTitulo("Formatação JSON inválida!!!");
		error.setMsgDesenvolvedor("http://socialbooksapi.com/406");
		error.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(error);
	}

}
