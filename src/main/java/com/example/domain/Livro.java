package com.example.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
public class Livro {
	//essa anotação da integração spring jackson só mostra valores no JSON quando nao forem nullos
	//jsckson esta fazendo a serialização dos retornos inputs
	@JsonInclude(Include.NON_NULL)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonInclude(Include.NON_NULL)
	@NotEmpty(message="O campo nome deve ser preenchido!")
	private String nome;
	
	@JsonInclude(Include.NON_NULL)
	@JsonFormat(pattern = "dd/MM/yyyy")
	@NotNull(message="Data de Publicação é obrigatório!")
	private Date dataPublicacao;
	
	@JsonInclude(Include.NON_NULL)
	@NotNull(message="Campo editora é obrigatório!")
	private String editora;
	
	@JsonInclude(Include.NON_NULL)
	@NotNull(message="Campo resumo é obrigatório!")
	@Size(max=1500, message="Resumo deve ter no máximo 1500 caracteres")
	private String resumo;
	
	@JsonInclude(Include.NON_EMPTY)
	@OneToMany(mappedBy="livro")
	private List<Comentario> comentarios;
	
	@ManyToOne
	@JoinColumn(name = "AUTOR_ID")
	@JsonInclude(Include.NON_NULL)
	private Autor autor;
	
	public Livro(){
		
	}
	
	public Livro(String nome){
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(Date dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	
	

}
