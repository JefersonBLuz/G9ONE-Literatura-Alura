package com.JefersonBLuz.literalura.service;

import com.JefersonBLuz.literalura.dto.AutorDTO;
import com.JefersonBLuz.literalura.dto.LivroDTO;
import com.JefersonBLuz.literalura.model.Autor;
import com.JefersonBLuz.literalura.model.Livro;
import com.JefersonBLuz.literalura.repository.AutorRepository;
import com.JefersonBLuz.literalura.repository.LivroRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class CatalogoLivrosService {

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    public CatalogoLivrosService(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    @Transactional
    public Livro adicionarLivro(LivroDTO livroDTO) {
        try {
            if (livroDTO.id() == null) {
                throw new RuntimeException("Livro recebido da API sem identificador.");
            }

            var livroExistente = livroRepository.findByGutendexId(livroDTO.id());
            if (livroExistente.isPresent()) {
                return livroExistente.get();
            }

            AutorDTO primeiroAutor = (livroDTO.authors() != null && !livroDTO.authors().isEmpty())
                    ? livroDTO.authors().getFirst()
                    : null;

            Autor autor = obterOuCriarAutor(primeiroAutor);

            String idioma = "desconhecido";
            if (livroDTO.languages() != null && !livroDTO.languages().isEmpty()) {
                idioma = livroDTO.languages().getFirst();
            }

            Livro livro = new Livro();
            livro.setGutendexId(livroDTO.id());
            livro.setTitulo(livroDTO.title() == null ? "Sem titulo" : livroDTO.title());
            livro.setIdioma(idioma);
            livro.setDownloads(livroDTO.downloadCount());
            livro.setAutor(autor);

            return livroRepository.save(livro);
        } catch (DataAccessException e) {
            throw new RuntimeException("Erro ao salvar livro/autor no banco de dados.", e);
        }
    }

    private Autor obterOuCriarAutor(AutorDTO autorDTO) {
        String nome = autorDTO == null || autorDTO.name() == null || autorDTO.name().isBlank()
                ? "Autor desconhecido"
                : autorDTO.name();

        return autorRepository.findByNomeIgnoreCase(nome)
                .map(autor -> atualizarDadosDoAutor(autor, autorDTO))
                .orElseGet(() -> criarAutor(autorDTO, nome));
    }

    private Autor atualizarDadosDoAutor(Autor autor, AutorDTO autorDTO) {
        if (autorDTO != null) {
            if (autor.getAnoNascimento() == null && autorDTO.birthYear() != null) {
                autor.setAnoNascimento(autorDTO.birthYear());
            }
            if (autor.getAnoFalecimento() == null && autorDTO.deathYear() != null) {
                autor.setAnoFalecimento(autorDTO.deathYear());
            }
            return autorRepository.save(autor);
        }
        return autor;
    }

    private Autor criarAutor(AutorDTO autorDTO, String nome) {
        Autor novoAutor = new Autor();
        novoAutor.setNome(nome);
        if (autorDTO != null) {
            novoAutor.setAnoNascimento(autorDTO.birthYear());
            novoAutor.setAnoFalecimento(autorDTO.deathYear());
        }
        return autorRepository.save(novoAutor);
    }

    public List<Livro> listarTodos() {
        try {
            return livroRepository.findAll();
        } catch (DataAccessException e) {
            throw new RuntimeException("Erro ao listar livros no banco de dados.", e);
        }
    }

    public List<Livro> listarPorIdioma(String idioma) {
        try {
            return livroRepository.findByIdiomaIgnoreCase(idioma);
        } catch (DataAccessException e) {
            throw new RuntimeException("Erro ao listar livros por idioma no banco de dados.", e);
        }
    }

    public List<Autor> listarAutores() {
        try {
            return autorRepository.findAll();
        } catch (DataAccessException e) {
            throw new RuntimeException("Erro ao listar autores no banco de dados.", e);
        }
    }

    public List<Autor> listarAutoresVivosNoAno(int ano) {
        try {
            List<Autor> vivosSemAnoFalecimento = autorRepository.findByAnoNascimentoLessThanEqualAndAnoFalecimentoIsNull(ano);
            List<Autor> vivosComAnoFalecimento = autorRepository
                    .findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqual(ano, ano);

            Map<Long, Autor> unicos = new LinkedHashMap<>();
            vivosSemAnoFalecimento.forEach(autor -> unicos.put(autor.getId(), autor));
            vivosComAnoFalecimento.forEach(autor -> unicos.put(autor.getId(), autor));

            return List.copyOf(unicos.values());
        } catch (DataAccessException e) {
            throw new RuntimeException("Erro ao listar autores vivos no banco de dados.", e);
        }
    }
}
