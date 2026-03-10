package com.JefersonBLuz.literalura.service;

import com.JefersonBLuz.literalura.dto.LivroDTO;
import com.JefersonBLuz.literalura.entidades.LivroCatalogo;

import java.util.ArrayList;
import java.util.List;

public class CatalogoLivrosService {

    private final List<LivroCatalogo> livrosBuscados = new ArrayList<>();

    public void adicionarLivro(LivroDTO livroDTO) {
        String autor = "Autor desconhecido";
        if (livroDTO.authors() != null && !livroDTO.authors().isEmpty()) {
            autor = livroDTO.authors().getFirst().name();
        }

        String idioma = "Idioma desconhecido";
        if (livroDTO.languages() != null && !livroDTO.languages().isEmpty()) {
            idioma = livroDTO.languages().getFirst();
        }

        LivroCatalogo livro = new LivroCatalogo(
                livroDTO.title(),
                autor,
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
}
