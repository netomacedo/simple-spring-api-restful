package com.example.domain;

public class DetalhesError {
	
	private String titulo;
	
	private Long status;
	
	private Long timestamp;
	
	private String msgDesenvolvedor;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getMsgDesenvolvedor() {
		return msgDesenvolvedor;
	}

	public void setMsgDesenvolvedor(String msgDesenvolvedor) {
		this.msgDesenvolvedor = msgDesenvolvedor;
	}

}
