package com.JefersonBLuz.literalura.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JefersonBLuz.literalura.model.Autor;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    Optional<Autor> findByNomeIgnoreCase(String nome);

    List<Autor> findByAnoNascimentoLessThanEqualAndAnoFalecimentoIsNull(Integer ano);

    List<Autor> findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqual(Integer anoNascimento, Integer anoFalecimento);
}
