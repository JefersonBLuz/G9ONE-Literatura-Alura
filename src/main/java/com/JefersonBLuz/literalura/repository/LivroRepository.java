package com.JefersonBLuz.literalura.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JefersonBLuz.literalura.model.Livro;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    Optional<Livro> findByGutendexId(Integer gutendexId);

    List<Livro> findByIdiomaIgnoreCase(String idioma);
}
