package com.JefersonBLuz.literalura.service;

import com.JefersonBLuz.literalura.dto.AutorDTO;
import com.JefersonBLuz.literalura.dto.LivroDTO;
import com.JefersonBLuz.literalura.entidades.AutorCatalogo;
import com.JefersonBLuz.literalura.entidades.LivroCatalogo;

import java.util.ArrayList;
import java.util.List;

public class CatalogoLivrosService {

    private final List<LivroCatalogo> livrosBuscados = new ArrayList<>();

    public void adicionarLivro(LivroDTO livroDTO) {
        String autor = "Autor desconhecido";
        Integer autorAnoNascimento = null;
        Integer autorAnoFalecimento = null;
        if (livroDTO.authors() != null && !livroDTO.authors().isEmpty()) {
            AutorDTO primeiroAutor = livroDTO.authors().getFirst();
            autor = primeiroAutor.name();
            autorAnoNascimento = primeiroAutor.birthYear();
            autorAnoFalecimento = primeiroAutor.deathYear();
        }

        String idioma = "Idioma desconhecido";
        if (livroDTO.languages() != null && !livroDTO.languages().isEmpty()) {
            idioma = livroDTO.languages().getFirst();
        }

        LivroCatalogo livro = new LivroCatalogo(
                livroDTO.title(),
                autor,
                autorAnoNascimento,
                autorAnoFalecimento,
                idioma,
                livroDTO.downloadCount()
        );
        livrosBuscados.add(livro);
    }

    public List<LivroCatalogo> listarTodos() {
        return List.copyOf(livrosBuscados);
    }

    public List<LivroCatalogo> listarPorIdioma(String idioma) {
        return livrosBuscados.stream()
                .filter(livro -> livro.idioma() != null)
                .filter(livro -> livro.idioma().equalsIgnoreCase(idioma))
                .toList();
    }

    public List<AutorCatalogo> listarAutores() {
        return livrosBuscados.stream()
                .map(livro -> new AutorCatalogo(
                        livro.autor(),
                        livro.autorAnoNascimento(),
                        livro.autorAnoFalecimento()
                ))
                .distinct()
                .toList();
    }

    public List<AutorCatalogo> listarAutoresVivosNoAno(int ano) {
        return listarAutores().stream()
                .filter(autor -> autor.anoNascimento() != null && autor.anoNascimento() <= ano)
                .filter(autor -> autor.anoFalecimento() == null || autor.anoFalecimento() >= ano)
                .toList();
    }
}
