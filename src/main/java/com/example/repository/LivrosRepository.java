package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.Livro;

public interface LivrosRepository extends JpaRepository<Livro, Long>{

}
