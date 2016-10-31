package com.example.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.domain.Comentario;
import com.example.domain.Livro;
import com.example.repository.ComentarioRepository;
import com.example.repository.LivrosRepository;
import com.example.service.exceptions.LivroNaoEncontradoException;

@Service
public class LivrosService {
	
	@Autowired
	private LivrosRepository livroRepository;
	
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	public List<Livro> listar(){
		return livroRepository.findAll();
		
	}
	
	public Livro buscar(Long id){
		Livro livro = livroRepository.findOne(id);
		
		if(livro == null){
			throw new LivroNaoEncontradoException("O livro não foi encontrado");
		}
		
		return livro;
	}
	
	public Livro salvar(Livro livro){
		livro.setId(null);//garantir que está salvando
		return livroRepository.save(livro);
	}
	
	public void delete(Long id){
		try {
			livroRepository.delete(id);
		} catch (EmptyResultDataAccessException e) {
			throw new LivroNaoEncontradoException("Livro não pode ser encontrado");
		}
	}
	
	public void atualizar(Livro livro){
		verificaSeExiste(livro);
		livroRepository.save(livro);	
	}
	
	private void verificaSeExiste(Livro livro){
		buscar(livro.getId());		
	}
	
	public Comentario salvarComentario(Long livroID, Comentario comentario){
		Livro livro = buscar(livroID);
		comentario.setLivro(livro);
		comentario.setData(new Date());
		
		return comentarioRepository.save(comentario);
		
	}
	
	public List<Comentario> listarComentarios(Long livroId){
		Livro livro = buscar(livroId);
		
		return livro.getComentarios();
		
	}
}
