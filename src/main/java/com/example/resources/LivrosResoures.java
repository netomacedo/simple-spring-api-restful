package com.example.resources;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.domain.Comentario;
import com.example.domain.Livro;
import com.example.service.LivrosService;

//indica a classe é um resource recurso
@RestController
@RequestMapping(value="/livros")
public class LivrosResoures {
	
	@Autowired
	LivrosService livroService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Livro>> listar(){
		return ResponseEntity.status(HttpStatus.OK).body(livroService.listar());	
	}
	
	//@RequestBody diz ao spring que pegue o que vem na requisição e coloque no obj livro.
	//sem ele nao se tem as informações do objeto livro
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody Livro livro){
		livro = livroService.salvar(livro);
		
		//cabecalho location setado no headers, retornando a uri para acesso
		//ao criar um recurso retornar 201 created e a url de acesso ao recurso criado
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(livro.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	//bind do que vem da uri passando para o banco para pesquisa
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable("id") Long id){
		Livro livro = livroService.buscar(id);	
		//implementando cache na busca
		CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);
		//encapsula o obj seta o status da resposta e coloca o obj no corpo da msg
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl)
				.body(livro);//200 OK
	}
	
	@RequestMapping(value = "/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id){
		
		livroService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody Livro livro, @PathVariable("id") Long id){
		livro.setId(id);	
		livroService.atualizar(livro);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}/comentarios", method=RequestMethod.POST)
	public ResponseEntity<Void> adicionarComentario(@PathVariable("id") Long livroId,@RequestBody Comentario comentario){
		
		//capturar contexto atual de segurança 
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		comentario.setUsuario(auth.getName());
		
		livroService.salvarComentario(livroId, comentario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	@RequestMapping(value="/{id}/comentarios", method=RequestMethod.GET)
	public ResponseEntity<List<Comentario>> listarComentarios(@PathVariable("id") Long livroId){
		
		List<Comentario> comentarios = livroService.listarComentarios(livroId);
		
		return ResponseEntity.status(HttpStatus.OK).body(comentarios);//200 OK
		
	}

}
