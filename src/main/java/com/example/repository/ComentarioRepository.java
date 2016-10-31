package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.Comentario;

public interface ComentarioRepository  extends JpaRepository<Comentario, Long>{

}
