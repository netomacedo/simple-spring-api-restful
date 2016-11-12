package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Autor;
import com.example.repository.AutorRepository;
import com.example.service.exceptions.AutorExistenteException;
import com.example.service.exceptions.AutorNaoEncontradoException;

@Service
public class AutorService {
	
	@Autowired
	private AutorRepository autorRepository;
	
	public List<Autor> listar(){
		return autorRepository.findAll();
	}
	
	public Autor salvar(Autor autor){
		if(autor.getId() != null){
			Autor autorExist = autorRepository.findOne(autor.getId());
			if(autorExist != null){
				throw new AutorExistenteException("Autor já existe");
			}
		}
		return autorRepository.save(autor);
	}
	
	public Autor buscar(Long id){
		Autor autor = autorRepository.findOne(id);
		
		if(autor == null){
			throw new AutorNaoEncontradoException("Autor Não encontrado.");
		}
		
		return autor;
		
	}

}
