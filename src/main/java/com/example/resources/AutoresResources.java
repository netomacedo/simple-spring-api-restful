package com.example.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.domain.Autor;
import com.example.service.AutorService;

@Controller
@RequestMapping("/autores")
public class AutoresResources {
	
	@Autowired
	private AutorService autorService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Autor>> listar(){
		return ResponseEntity.status(HttpStatus.OK).body(autorService.listar());
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody Autor autor){
		autor = autorService.salvar(autor);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(autor.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable("id") Long id){
		Autor autor = autorService.buscar(id);		
		return ResponseEntity.status(HttpStatus.OK).body(autor);//200 OK
	}

}
