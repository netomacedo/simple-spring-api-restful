package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long>{

}
